package com.wlht.oa.base;

import android.app.Activity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import com.wlht.oa.Constants;
import com.wlht.oa.R;
import com.wlht.oa.adapter.CoreAdapter;
import com.wlht.oa.base.exception.ApiException;
import com.wlht.oa.base.net.ErrorSubscriber;
import com.wlht.oa.base.util.helper.RxSchedulers;
import com.wlht.oa.widget.EmptyLayout;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by hr on 16/7/25.
 */
public abstract class BaseListFragment<T extends BaseEntity.ListBean> extends BaseFragment
{
    protected PtrFrameLayout ptrFrameLayout;
    protected RecyclerView mRecyclerView;
    protected LinearLayoutManager mLayoutManager;
    protected CoreAdapter<T> mAdapter;
    private int begin = 0;
    private boolean isRefreshable = true, isHasHeadView = false, isEmpty = false;
    private T model;
    public RxManager mRxManager = new RxManager();
    private Map<String, String> param = new HashMap<>();


    @Override
    public void onDestroy() {
        super.onDestroy();
        mRxManager.clear();
    }



    @Override
    public void initView(View view) {
        isRefreshable = isRefreshable();
        ptrFrameLayout = (PtrFrameLayout)view.findViewById(R.id.ptr_frame);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        initAdapter();
        initRecyclerView(view);
        initRefresh();
        mRxManager.on(Constants.EVENT_DEL_ITEM, (arg0) -> mAdapter.removeItem((Integer) arg0));
        mRxManager.on(Constants.EVENT_UPDATE_ITEM, (arg0) -> mAdapter.upDateItem(((UpDateData) arg0).i, ((UpDateData) arg0).oj));
        mEmptyLayout.setOnLayoutClickListener(v -> ptrFrameLayout.autoRefresh());
    }

    public boolean isRefreshable()
    {
        return true;
    }

    public void initAdapter()
    {
        mAdapter = new CoreAdapter<>();
    }


    public void initRecyclerView(View view)
    {
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
    }

    public void initRefresh()
    {
        if (!isRefreshable)return;


        setDefaultHeader(ptrFrameLayout);
        //防止之前设置成为了enable
        ptrFrameLayout.setEnabled(isRefreshable);
        //头部刷新
        ptrFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                reFetch();
            }
        });
        //加载更多

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            protected int lastVisibleItem;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (mRecyclerView.getAdapter() != null
                        && newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == mRecyclerView.getAdapter()
                        .getItemCount() && mAdapter.isHasMore)
                    fetch();
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int arg0, int arg1) {
                super.onScrolled(recyclerView, arg0, arg1);
                //如果这个地方LayoutManager 不是LinearLayoutManager就会出错,how to fix it
                /**
                 * 解决方式,使用PtrFrameLayout的loadMore方法来完成加载更多的操作
                 * 如果使用loadMore那么CoreAdapter的内容也需要修改
                 */
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
            }
        });
    }


    /**
     * 子类方法调用,设置
     */

    public void refresh()
    {
        if (isRefreshable)
        {
            new MyHandler().postDelayed(() -> ptrFrameLayout.autoRefresh(),500);
        }else
        {
            reFetch();
        }
    }

    public BaseListFragment setIsRefreshable(boolean isRefreshable) {
        this.isRefreshable = isRefreshable;
        ptrFrameLayout.setEnabled(isRefreshable);
        return this;
    }


    public BaseListFragment setHeadView(Class<? extends BaseViewHolder> cla) {
        if (cla == null) {
            isHasHeadView = false;
            this.mAdapter.setHeadViewType(0, cla, null);
        } else
            try {
                Object obj = getContext().getIntent().getSerializableExtra(Constants.HEAD_DATA);
                int mHeadViewType = cla.getConstructor(View.class)
                        .newInstance(new View(getContext())).getType();
                this.mAdapter.setHeadViewType(mHeadViewType, cla, obj);
                isHasHeadView = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        return this;
    }

    public BaseListFragment setFooterView(Class<? extends BaseViewHolder> cla) {
        this.begin = 0;
        try {
            int mFooterViewType = cla.getConstructor(View.class)
                    .newInstance(new View(getContext())).getType();
            this.mAdapter.setFooterViewType(mFooterViewType, cla);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }


    public BaseListFragment setView(Class<? extends BaseViewHolder<T>> cla) {
        try {
            BaseViewHolder mIVH = ((BaseViewHolder) (cla.getConstructor(View.class)
                    .newInstance(new View(getContext()))));
            int mType = mIVH.getType();
            this.model = ((Class<T>) ((ParameterizedType) (cla
                    .getGenericSuperclass())).getActualTypeArguments()[0])
                    .newInstance();// 根据类的泛型类型获得model的实例
            this.mAdapter.setViewType(mType, cla);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public void setEmpty() {
        if (!isHasHeadView && !isEmpty) {
            isEmpty = true;
            mEmptyLayout.setErrorType(EmptyLayout.NODATA);
//            ll_emptyview.setVisibility(View.VISIBLE);
            ptrFrameLayout.setVisibility(View.GONE);
        }
    }


    public BaseListFragment setParam(String key, String value) {
        this.param.put(key, value);
        return this;
    }

    public BaseListFragment setData(List<T> datas) {
        if (isEmpty) {
            mEmptyLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
            ptrFrameLayout.setVisibility(View.VISIBLE);
        }
        mAdapter.setBeans(datas, 1);
        return this;
    }

    /**
     * 封装的分页
     */


    private void reFetch() {
        this.begin = 0;
//        ptrFrameLayout.setRefreshing(true);
        fetch();
    }

    private void fetch() {
        begin++;
        if (isEmpty) {
            mEmptyLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
            ptrFrameLayout.setVisibility(View.VISIBLE);
        }
        if (model == null) {
            Log.e("model", "null");
            return;
        }
        model.setParam(param);

        /**
         * 可能会存在bug
         */
        mRxManager.add(model.getPageAt(begin)
                .compose(RxSchedulers.error_handle_io_main())
                .subscribe(new ErrorSubscriber(){
                    @Override
                    public void onCompleted() {
                        hubHide();
                        ptrFrameLayout.refreshComplete();
                    }

                    @Override
                    public void onNext(Object subjects) {
                        if (subjects instanceof ArrayList)
                        {
                            isEmpty = false;
                            mAdapter.setBeans((ArrayList) subjects, begin);
                            if (begin == 1 && (subjects == null || ((ArrayList)subjects).size() == 0))
                                setEmpty();
                        }
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        ptrFrameLayout.refreshComplete();
                        isEmpty = false;
                        setEmpty();
                        showToast(ex.message);
                    }
                }));
    }

    public class UpDateData {
        public int i;
        public T oj;

        public UpDateData(int i, T oj) {
            this.i = i;
            this.oj = oj;
        }
    }


}
