package com.wlht.oa.ui.audit;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wlht.oa.R;
import com.wlht.oa.adapter.ApproveAdapter;
import com.wlht.oa.adapter.BaseRecyclerAdapter;
import com.wlht.oa.base.BaseSearchFragment;
import com.wlht.oa.bean.approve.Approve;
import com.wlht.oa.decoration.DividerItemMiniDecoration;
import com.wlht.oa.util.TLog;

import java.util.ArrayList;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by hr on 16/6/27.
 */
public class ApproveSearchFragment extends BaseSearchFragment
{

    ApproveAdapter mAdapter;
    boolean isShowCheckBox = false;
    ArrayList<Approve> mSelectedDatas = new ArrayList<>();

    View selectedContainer;
    TextView selectedCntTv;
    TextView selectedOkTv;



    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_approve, null, false);
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        mSearchEt.setHint("搜索");

        selectedContainer = view.findViewById(R.id.selectedContainer);
        selectedCntTv = (TextView)view.findViewById(R.id.selectedCntTv);
        selectedOkTv = (TextView)view.findViewById(R.id.selectedOkTv);
        selectedContainer.setVisibility(isShowCheckBox ? View.VISIBLE : View.GONE);

        initRefresh();
        initRecyclerView();
    }


    protected void initRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(new DividerItemMiniDecoration(getActivity(), DividerItemMiniDecoration.VERTICAL_LIST));
        mRecyclerView.setAdapter(mAdapter = new ApproveAdapter(isShowCheckBox, false));

        mAdapter.onDataChangedListener = new ApproveAdapter.OnDataChangedListener() {
            @Override
            public void onDataChanged(ArrayList<Approve> data) {
                int cnt = data.size();
                selectedCntTv.setText(String.format("已选审批(%d个)",cnt));
                selectedCntTv.setTag(String.format("%d",cnt));
                selectedCntTv.setTextColor(getResources().getColor(cnt == 0 ? R.color.black : R.color.main_color));
                selectedOkTv.setBackgroundResource(cnt == 0 ? R.color.gray : R.color.kaoqing_tv_color_5CA1E6);            }
        };

        mAdapter.setCheckedList(mSelectedDatas);
    }



    protected void initRefresh() {
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
    }


    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void initEvent(View view) {
        super.initEvent(view);
        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, Object data) {
                TLog.log(data.toString());
            }
        });
        selectedOkTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("0".equals(selectedCntTv.getTag()))return;

            }
        });
    }

    @Override
    public void onEnter(Object data) {
        super.onEnter(data);
        if (data instanceof ArrayList)
        {
            mSelectedDatas.addAll((ArrayList<Approve>)data);
        }
    }

    @Override
    protected void search() {

        Approve audit = new Approve();
        audit.logoUrl="http://";
        audit.userName = "张武";
        audit.typeName = "调休申请单";
        audit.time = "2012-12-12 12:12";

        mAdapter.addData(audit);
    }
}
