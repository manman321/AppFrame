package com.wlht.oa.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wlht.oa.R;
import com.wlht.oa.adapter.MessageAdapter;
import com.wlht.oa.base.BaseTitleFragment;
import com.wlht.oa.bean.Message;
import com.wlht.oa.decoration.DividerItemMiniDecoration;

import java.util.ArrayList;

import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by hr on 16/6/12.
 */
public class MessageFragment extends BaseTitleFragment{

    protected RecyclerView mRecyclerView;
    protected MessageAdapter mAdapter;
    protected PtrFrameLayout ptrFrameLayout;
    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflateView(R.layout.fragment_message);
    }

    @Override
    public void initNavBar() {
        mTitleHeaderBar.getLeftViewContainer().setVisibility(View.GONE);
        mTitleHeaderBar.setTitle("通知");
    }



    public void initView(View view){

        super.initView(view);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        ptrFrameLayout = (PtrFrameLayout)view.findViewById(R.id.ptr_frame);


        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(new DividerItemMiniDecoration(getActivity(), DividerItemMiniDecoration.VERTICAL_LIST));
        mRecyclerView.setAdapter(mAdapter = new MessageAdapter());

    }

    public void initRefresh()
    {

    }


    @Override
    public void initEvent(View view) {

    }


    String[] imgs = new String[]{
            "http://img3.duitang.com/uploads/item/201604/21/20160421193915_mP2Lr.thumb.700_0.jpeg",
            "http://img3.duitang.com/uploads/item/201604/26/20160426001415_teGBZ.jpeg",
            "http://img3.duitang.com/uploads/item/201511/08/20151108200624_cQE2t.thumb.700_0.jpeg",
            "http://img3.duitang.com/uploads/item/201508/16/20150816132356_Gs4AU.jpeg",
            "http://img3.duitang.com/uploads/item/201509/19/20150919211347_BcmZA.jpeg",
            "http://www.bz55.com/uploads/allimg/121019/1-121019100228.jpg",
            "http://www.wed114.cn/jiehun/uploads/allimg/160426/39_160426110624_1.jpg",
            "http://www.wed114.cn/jiehun/uploads/allimg/160426/39_160426110624_2.jpg",

    };
    public void initData(){

        ArrayList<Message> list = new ArrayList<>();
        list.add(new Message("","通知","已审批","刚刚"));
        list.add(new Message("","通知","被驳回","刚刚"));
        list.add(new Message("","通知","已提交","刚刚"));
        list.add(new Message("","通知","主管已审核","刚刚"));
        list.add(new Message("","通知","任务已完成","刚刚"));
        list.add(new Message("","通知","任务取消","刚刚"));


        list.add(new Message("","通知","已审批","刚刚"));
        list.add(new Message("","通知","被驳回","刚刚"));
        list.add(new Message("","通知","已提交","刚刚"));
        list.add(new Message("","通知","主管已审核","刚刚"));
        list.add(new Message("","通知","任务已完成","刚刚"));
        list.add(new Message("","通知","任务取消","刚刚"));

        list.add(new Message("","通知","已审批","刚刚"));
        list.add(new Message("","通知","被驳回","刚刚"));
        list.add(new Message("","通知","已提交","刚刚"));
        list.add(new Message("","通知","主管已审核","刚刚"));
        list.add(new Message("","通知","任务已完成","刚刚"));
        list.add(new Message("","通知","任务取消","刚刚"));

        for (int i = 0; i < list.size();i++)
        {
            Message message = list.get(i);
            message.imageUrl = imgs[i%imgs.length];
        }

        mAdapter.clear();
        mAdapter.addDatas(list);

    }

    protected void initEvent(){

    }
    @Override public boolean processBackPressed() {return false;}



}
