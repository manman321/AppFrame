package com.wlht.oa.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.wlht.oa.widget.PagerSlidingTabStrip;
import com.wlht.oa.widget.ViewPageTabItem;

/**
 * Created by hr on 16/6/27.
 */
public class CommonViewPagerFragmentAdapter extends ViewPageFragmentAdapter {
    public CommonViewPagerFragmentAdapter(FragmentManager fm, PagerSlidingTabStrip pageStrip, ViewPager pager) {
        super(fm, pageStrip, pager);
    }


    @Override
    protected void addFragment(ViewPageInfo info) {
//        super.addFragment(info);
        if (info  == null)return;
        ViewPageTabItem v = new ViewPageTabItem(mContext);
        v.setText(info.title);
        v.setResource(info.mSelectedIconRes,info.mNormalIconRes,info.mSelectedTextColor,info.mNormalTextColor);
        mPagerStrip.addTab(v);
        mTabs.add(info);
        notifyDataSetChanged();
    }
}
