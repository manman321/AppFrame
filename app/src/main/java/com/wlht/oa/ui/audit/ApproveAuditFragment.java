package com.wlht.oa.ui.audit;/**
 * Created by hr on 16/6/27.
 */

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wlht.oa.R;
import com.wlht.oa.adapter.ApproveAdapter;
import com.wlht.oa.base.BaseFragment;
import com.wlht.oa.bean.approve.Approve;
import com.wlht.oa.decoration.DividerItemMiniDecoration;
import com.wlht.oa.event.Event;

import java.util.ArrayList;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class ApproveAuditFragment extends BaseFragment {
    RecyclerView mRecyclerView;
    PtrFrameLayout mPtrFrame;
    ApproveAdapter mAdapter;

    boolean isShowCheckBox = false;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_audit_audit, null, false);
    }


    @Override
    public void initView(View view) {
        isShowCheckBox = getArguments().getBoolean("isShowCheckBox");
        mPtrFrame = (PtrFrameLayout) view.findViewById(R.id.ptr_frame);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);


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
                getContext().Bus.post(new Event(ApproveAuditFragment.class,data));
            }
        };
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
    public void initEvent(View view) {

    }

    @Override
    public void initData() {
        Approve audit = new Approve();
        audit.logoUrl="http://";
        audit.userName = "李四";
        audit.typeName = "调休申请单";
        audit.time = "2012-12-12 12:12";

        mAdapter.addData(audit);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            default:
                break;
        }
    }

}
