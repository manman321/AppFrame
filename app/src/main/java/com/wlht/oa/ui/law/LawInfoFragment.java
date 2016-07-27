package com.wlht.oa.ui.law;/**
 * Created by hr on 16/6/29.
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
import com.wlht.oa.adapter.LawInfoAdapter;
import com.wlht.oa.base.BaseFragment;
import com.wlht.oa.base.BaseTitleFragment;
import com.wlht.oa.bean.LawInfo;
import com.wlht.oa.decoration.DividerItemMiniDecoration;

public class LawInfoFragment extends BaseFragment {
    RecyclerView mRecyclerView;
    PtrFrameLayout mPtrFrame;
    LawInfoAdapter mAdapter;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recyclerview, null, false);
    }


    @Override
    public void initView(View view) {
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
        mRecyclerView.setAdapter(mAdapter = new LawInfoAdapter());
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
                mHasLoadedOnce = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPtrFrame.refreshComplete();

                        LawInfo lawInfo = new LawInfo();
                        lawInfo.title = "中华人民共和国法律纲要";
                        lawInfo.time = "2012-12-12";
                        mAdapter.addData(lawInfo);
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
        isPrepared = true;
        lazyLoad();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            default:
                break;
        }
    }


    protected boolean isVisible ;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint())
        {
            isVisible = true;
            onVisible();
        }else
        {
            isVisible = false;
            onInvisible();
        }
    }

    protected void onVisible()
    {
        lazyLoad();
    }


    protected void onInvisible()
    {
    }


    private boolean isPrepared;
    /**
     * 是否已被加载过一次，第二次就不再去请求数据了
     */
    private boolean mHasLoadedOnce;
    public void lazyLoad()
    {
        if (!isPrepared || !isVisible || mHasLoadedOnce) {
            return;
        }
        mPtrFrame.autoRefresh();
    }

}
