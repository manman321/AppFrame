package com.wlht.oa.ui;/**
 * Created by hr on 16/7/1.
 */

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
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
import com.wlht.oa.adapter.BaseRecyclerAdapter;
import com.wlht.oa.adapter.CarAdapter;
import com.wlht.oa.base.BaseTitleFragment;
import com.wlht.oa.bean.Car;
import com.wlht.oa.decoration.DividerItemMiniDecoration;

public class CarFragment extends BaseTitleFragment {
    RecyclerView mRecyclerView;
    PtrFrameLayout mPtrFrame;
    CarAdapter mAdapter;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recyclerview, null, false);
    }

    @Override
    public void initNavBar() {
        setHeaderTitle("车辆状态");
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
        mRecyclerView.setAdapter(mAdapter = new CarAdapter());
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

                        Car car = new Car();
                        car.no = "川B 00011";
                        car.brand = "别克轿车";
                        car.status = "可用";
                        car.driver = "张三";
                        car.phone = "6123";
                        car.dept = "计财处";

                        mAdapter.addData(car);


                    }
                }, 500);
            }
        });
    }

    @Override
    public void initEvent(View view) {

        mAdapter.setOnItemClickListener((position, data) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            final String[] values = new String[]{"拨打电话","修改资料","修改状态"};
            builder.setItems(values, (dialog, which) -> {

                switch (which)
                {
                    case 0:
                    {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        Uri tel = Uri.parse("tel:" + "135xxxxxxxx");
                        intent.setData(tel);
                        startActivity(intent);
                    }
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                }

//                setVoiceVisibility(false);
//                mSendContentTv.setText(values[which]);
            });
            builder.show();
        });
    }

    @Override
    public void initData() {

        mPtrFrame.autoRefresh();
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
