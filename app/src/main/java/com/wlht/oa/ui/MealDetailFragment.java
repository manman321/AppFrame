package com.wlht.oa.ui;/**
 * Created by hr on 16/6/30.
 */

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.nineoldandroids.animation.ObjectAnimator;
import com.wlht.oa.R;
import com.wlht.oa.base.BaseTitleFragment;

public class MealDetailFragment extends BaseTitleFragment {
    FrameLayout mBreakfastFL;


    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_meal_detail, null, false);
    }

    @Override
    public void initNavBar() {
        setHeaderTitle("用餐预约");
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        mBreakfastFL = (FrameLayout) view.findViewById(R.id.breakfastFL);
        ObjectAnimator.ofFloat(mBreakfastFL, "translationY", 0, -800).setDuration(500).start();
    }

    @Override
    public void initEvent(View view) {

    }

    @Override
    public void initData() {

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
