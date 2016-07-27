package com.wlht.oa.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;

import com.google.gson.Gson;
import com.wlht.oa.R;
import com.wlht.oa.adapter.BaseRecyclerAdapter;
import com.wlht.oa.adapter.TemplateFieldAdapter;
import com.wlht.oa.base.BaseTitleFragment;
import com.wlht.oa.bean.flow.Flow;
import com.wlht.oa.decoration.DividerItemMiniDecoration;
import com.wlht.oa.util.TLog;
import com.wlht.oa.widget.FlowView;
import com.wlht.oa.widget.HScrollView;
import com.wlht.oa.widget.InfoViewRow;
import com.wlht.oa.widget.ScrollListenerHorizontalScrollView;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by hr on 16/7/11.
 */
public class TestFragment extends BaseTitleFragment
{
    ScrollView mScrollView;
    PtrFrameLayout mPtrFrame;
    RecyclerView mRecyclerView;
    InfoViewRow mInfoViewRow,mInfoViewRow2;
    HScrollView mHorizontalScrollView;

    FlowView mFlowView;

    BaseRecyclerAdapter<String,BaseRecyclerAdapter.Holder> mAdapter;

    @Override
    public void initNavBar() {
        setHeaderTitle("测试");
    }

    @Override
    public void initEvent(View view) {

    }

    @Override
    public void initData() {
        InputStream inputStream = getResources().openRawResource(R.raw.json);
        InputStreamReader reader = new InputStreamReader(inputStream);
        Flow flow = new Gson().fromJson(reader, Flow.class);
        mFlowView.initWithFlow(flow);
    }

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflateView(R.layout.fragment_test);
    }

    public void initView(View view) {

        mScrollView = (ScrollView) view.findViewById(R.id.scrollView);
        mPtrFrame = (PtrFrameLayout) view.findViewById(R.id.ptr_frame);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        mInfoViewRow = (InfoViewRow)view.findViewById(R.id.infoViewRow);
        mHorizontalScrollView = (HScrollView)view.findViewById(R.id.horizontalScrollView);
        mInfoViewRow2 = (InfoViewRow)view.findViewById(R.id.infoViewRow2);
        mFlowView = (FlowView)view.findViewById(R.id.flowView);

        mInfoViewRow2.initWithColumn(8);
        mInfoViewRow.initWithColumn(7);
        mHorizontalScrollView.setOnScrollListener(new HScrollView.OnScrollListener() {
            @Override
            public void onScroll(int scrollX) {
                mInfoViewRow.scrollX(scrollX);
                mInfoViewRow2.scrollX(scrollX);
            }
        });

//        mHorizontalScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                TLog.log("scrollX: " + scrollX + "oldScrollX: " + oldScrollX);
//                if (scrollX > oldScrollX)
//                {
//
//                }
//            }
//        });


        setDefaultHeader(mPtrFrame);


        mPtrFrame.setPtrHandler(new PtrDefaultHandler() {

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return super.checkCanDoRefresh(frame, mRecyclerView, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPtrFrame.refreshComplete();
                    }
                }, 500);
            }
        });



        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(new DividerItemMiniDecoration(getActivity(), DividerItemMiniDecoration.VERTICAL_LIST));
        mRecyclerView.setAdapter(mAdapter = new BaseRecyclerAdapter<String, BaseRecyclerAdapter.Holder>() {
            @Override
            public <VH extends RecyclerView.ViewHolder> VH onCreate(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.list_cell_test, null, false);
                return (VH) new Holder(view);
            }
        });


        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 10;i++)
        {
            list.add(String.format("%d",i));
        }

        mAdapter.addDatas(list);




        TemplateFieldAdapter adapter = new TemplateFieldAdapter(10);

        /**
         * 1.直接在创建审批和查看审批的地方,全部显示表单明细项目.
         * 2.直接使用linearlayout来处理,方便同时处理水平方向滑动时,表头和表里面的内容一起滑动的情况
         * 
         */









    }
}
