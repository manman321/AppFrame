package com.wlht.oa.ui.law;/**
 * Created by hr on 16/6/29.
 */

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wlht.oa.R;
import com.wlht.oa.adapter.CommonViewPagerFragmentAdapter;
import com.wlht.oa.adapter.ViewPageFragmentAdapter;
import com.wlht.oa.adapter.ViewPageInfo;
import com.wlht.oa.base.BaseTitleFragment;
import com.wlht.oa.widget.PagerSlidingTabStrip;

public class LawFragment extends BaseTitleFragment {

    ViewPager mViewPager;
    PagerSlidingTabStrip mTabStrip;
    CommonViewPagerFragmentAdapter mTabsAdapter;


    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_law, null, false);
    }

    @Override
    public void initNavBar() {
        setHeaderTitle("法律");
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        mTabStrip = (PagerSlidingTabStrip) view.findViewById(R.id.pager_tabstrip);
        mViewPager = (ViewPager) view.findViewById(R.id.pager);
        mTabsAdapter = new CommonViewPagerFragmentAdapter(getChildFragmentManager(),
                mTabStrip, mViewPager);
        mViewPager.setOffscreenPageLimit(4);
        onSetupTabAdapter(mTabsAdapter);

    }


    protected void onSetupTabAdapter(ViewPageFragmentAdapter adapter) {
//        String[] title = getResources().getStringArray(
//                R.array.news_viewpage_arrays);

        int color = getResources().getColor(R.color.default_text);
        int selectedColor = getResources().getColor(R.color.main_color);
        adapter.addTab(new ViewPageInfo(
                "法律法规",
                "xp",
                LawInfoFragment.class, getBundle(1),
                -1,
                -1,
                selectedColor,
                color));

        adapter.addTab(new ViewPageInfo(
                "典型案例",
                "ch",
                LawInfoFragment.class, getBundle(2),
                -1,
                -1,
                selectedColor,
                color));

        adapter.addTab(new ViewPageInfo(
                "以案说法",
                "pt",
                LawInfoFragment.class, getBundle(3),
                -1,
                -1,
                selectedColor,
                color));

        adapter.addTab(new ViewPageInfo(
                "权威信息",
                "pt",
                LawInfoFragment.class, getBundle(3),
                -1,
                -1,
                selectedColor,
                color));

        adapter.addTab(new ViewPageInfo(
                "办事指南",
                "pt",
                LawInfoFragment.class, getBundle(3),
                -1,
                -1,
                selectedColor,
                color));

    }

    private Bundle getBundle(int newType) {
        Bundle bundle = new Bundle();
        bundle.putInt("BUNDLE_KEY_CATALOG", newType);
        return bundle;
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