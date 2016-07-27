package com.wlht.oa.ui;/**
 * Created by hr on 16/6/30.
 */

import android.animation.TimeInterpolator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kogitune.activity_transition.fragment.FragmentTransitionLauncher;
import com.nineoldandroids.animation.ObjectAnimator;
import com.wlht.oa.R;
import com.wlht.oa.base.BaseTitleFragment;
import com.wlht.oa.bean.Food;
import com.wlht.oa.util.TDevice;
import com.wlht.oa.util.TLog;
import com.wlht.oa.widget.CardView;

import java.util.ArrayList;

import in.srain.cube.app.CubeFragment;

public class MealFragment extends BaseTitleFragment {
    CardView mDinnerFL;
    CardView mLunchFL;
    CardView mBreakfastFL;

    protected TextView saveBtn;


    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_meal, null, false);
    }

    @Override
    public void initNavBar() {
        setHeaderTitle("预约就餐");
        saveBtn = createRightBtn("保存");
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (saveBtn.getAlpha() < 0.8)
                {
                    save();
                }
            }
        });
        saveBtn.setAlpha(0);

    }

    @Override
    public void initView(View view) {
        super.initView(view);
        mBreakfastFL = (CardView) view.findViewById(R.id.breakfastFL);
        mLunchFL = (CardView) view.findViewById(R.id.lunchFL);
        mDinnerFL = (CardView) view.findViewById(R.id.dinnerFL);

        mLunchFL.setIconAndTxt(R.drawable.icon_meal_lunch_normal,"午餐");
        mDinnerFL.setIconAndTxt(R.drawable.icon_meal_dinner_normal,"晚餐");



        mBreakfastFL.setInterpolator(interpolator);
        mLunchFL.setInterpolator(interpolator);
        mDinnerFL.setInterpolator(interpolator);

    }

    TimeInterpolator interpolator = new Interpolator() {
        @Override
        public float getInterpolation(float input) {
            int isShowNum = isShowCardViewNum();
            if (isShowNum > 1)return input;
            if (isShowNum == 1 && saveBtn.getAlpha() < 1.0)
            {
                saveBtn.setAlpha(input);
            }else if (isShowNum == 0)
            {
                saveBtn.setAlpha((1-input));
            }
            return input;
        }
    };



    private int isShowCardViewNum()
    {
        int i = 0;
        if (mDinnerFL.isShowExpand())
        {
            i++;
        }

        if (mLunchFL.isShowExpand())
        {
            i++;
        }

        if (mBreakfastFL.isShowExpand())
        {
            i++;
        }

        return i;
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

    };

    @Override
    public void initData() {

        ArrayList<Food> list = new ArrayList<>();
        for (int i = 0;i < imgs.length;i++)
        {
            Food food = new Food();
            food.imageUrl = imgs[i];
            food.name = String.format("事物%d",i);
            list.add(food);
        }

        mBreakfastFL.setFoods(list);
        mLunchFL.setFoods(list);
        mDinnerFL.setFoods(list);
    }


    protected void save()
    {
        if (mDinnerFL.isShowExpand())
        {
            save(mDinnerFL);
            return;
        }

        if (mLunchFL.isShowExpand())
        {
            save(mLunchFL);
            return;
        }

        if (mBreakfastFL.isShowExpand())
        {
            save(mBreakfastFL);
            return;
        }
    }

    protected void save(CardView cardView)
    {

    }


    @Override
    public void onClick(final View v) {
        super.onClick(v);
        switch (v.getId()) {
            default:

                break;
        }
    }

}
