package com.wlht.oa.ui;/**
 * Created by hr on 16/6/24.
 */

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wlht.oa.R;
import com.wlht.oa.adapter.NewsAdapter;
import com.wlht.oa.base.BaseTitleFragment;
import com.wlht.oa.bean.News;
import com.wlht.oa.decoration.DividerItemMiniDecoration;

public class NewsFragment extends BaseTitleFragment {
    RecyclerView mRecyclerView;
    PtrFrameLayout mPtrFrame;
    NewsAdapter mAdapter;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recyclerview, null, false);
    }

    @Override
    public void initNavBar() {

        setHeaderTitle("新闻");
    }

    @Override
    public void initView(View view) {
        super.initView(view);
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
        mRecyclerView.setAdapter(mAdapter = new NewsAdapter());
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

        News news = new News();
        news.setImageUrl("http:");
        news.setTitle("新消息");
        news.setContent("wahahahha");
        news.setTime("2012-12-12");


        mAdapter.addData(news);
        mAdapter.addData(news);

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
