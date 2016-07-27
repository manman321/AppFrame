package com.wlht.oa.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wlht.oa.KV;
import com.wlht.oa.R;
import com.wlht.oa.adapter.BaseRecyclerAdapter;
import com.wlht.oa.adapter.SettingAdapter;
import com.wlht.oa.base.BaseTitleFragment;
import com.wlht.oa.decoration.DividerItemMiniDecoration;
import com.wlht.oa.ui.settings.SettingFragment;
import com.wlht.oa.util.TDevice;

import java.util.ArrayList;

/**
 * Created by hr on 16/6/12.
 */
public class MeFragment extends BaseTitleFragment {

    private SettingAdapter mAdapter;
    protected ImageView mHeadImageView;
    protected TextView  mNameTextView;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflateView(R.layout.fragment_me);
    }


    @Override
    public void initNavBar() {
        mTitleHeaderBar.getLeftViewContainer().setVisibility(View.GONE);
        mTitleHeaderBar.setTitle("我");

//        <TextView
//        android:layout_alignParentRight="true"
//        android:textColor="@color/white"
//        android:id="@+id/settings"
//        android:layout_gravity="end"
//        android:gravity="center"
//        android:text="设置"
//        android:layout_width="48dp"
//        android:layout_height="match_parent" />

        TextView settings = new TextView(getContext());
        settings.setTextColor(Color.WHITE);
        settings.setText("设置");
        settings.setGravity(Gravity.CENTER_VERTICAL | Gravity.END);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStack(SettingFragment.class,null);
            }
        });


        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int) TDevice.dpToPixel(48), ViewGroup.LayoutParams.MATCH_PARENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,RelativeLayout.TRUE);
        params.rightMargin = (int)TDevice.dpToPixel(16);
        mTitleHeaderBar.getRightViewContainer().addView(settings, params);


//        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);

    }


    public void initView(View view)
    {
        super.initView(view);
        mHeadImageView = (ImageView)view.findViewById(R.id.imageView);
        mNameTextView = (TextView)view.findViewById(R.id.name);


        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new DividerItemMiniDecoration(getActivity(), DividerItemMiniDecoration.VERTICAL_LIST));
        recyclerView.setAdapter(mAdapter = new SettingAdapter(Gravity.CENTER_VERTICAL));
    }

    @Override
    public void initEvent(View view) {

    }


    public void initData()
    {
        ArrayList<KV<String,String>> list = new ArrayList<>();
        list.add(new KV<String, String>("部门","市场部"));
        list.add(new KV<String, String>("职务","业务员"));
        list.add(new KV<String, String>("手机", "15351235044"));
        list.add(new KV<String, String>("V网", "6813"));
        list.add(new KV<String, String>("座机", "68606123"));
        mAdapter.addDatas(list);

        mNameTextView.setText("何荣");



    }


    protected void initEvent()
    {
        mHeadImageView.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.imageView:

                break;

        }
    }

    @Override public boolean processBackPressed() {return false;}

}
