package com.wlht.oa.ui;/**
 * Created by hr on 16/6/23.
 */

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wlht.oa.R;
import com.wlht.oa.adapter.NoticeAdapter;
import com.wlht.oa.base.BaseTitleFragment;
import com.wlht.oa.bean.Notice;

import in.srain.cube.views.ptr.PtrFrameLayout;

public class NoticeFragment extends BaseTitleFragment {

    private PtrFrameLayout ptrFrame;
    private RecyclerView recyclerView;
    NoticeAdapter mAdapter;



    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notice, null, false);
    }

    @Override
    public void initNavBar() {
        setHeaderTitle("公告");
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        ptrFrame = (PtrFrameLayout) view.findViewById(R.id.ptr_frame);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(mAdapter = new NoticeAdapter());




    }

    @Override
    public void initEvent(View view) {

    }

    @Override
    public void initData() {
        Notice notice = new Notice();
        notice.title = "哈哈";
        notice.content = "好吧,你说的都有道理诶";
        notice.accept = "公司全体";
        notice.time = "20小时";

        mAdapter.clear();
        mAdapter.addData(notice);
        mAdapter.addData(notice);

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
