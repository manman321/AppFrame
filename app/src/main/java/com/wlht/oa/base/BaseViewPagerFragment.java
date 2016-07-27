package com.wlht.oa.base;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wlht.oa.R;
import com.wlht.oa.adapter.ViewPageFragmentAdapter;
import com.wlht.oa.widget.PagerSlidingTabStrip;


/**
 * 带有导航条的基类
 * 
 * @author FireAnt（http://my.oschina.net/LittleDY）
 * @created 2014年11月6日 下午4:59:50
 * 
 */
public abstract class BaseViewPagerFragment extends BaseFragment {

    protected PagerSlidingTabStrip mTabStrip;
    protected ViewPager mViewPager;
    protected ViewPageFragmentAdapter mTabsAdapter;
//    protected EmptyLayout mErrorLayout;


    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflateView(R.layout.base_viewpage_fragment);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);

        mTabStrip = (PagerSlidingTabStrip) view.findViewById(R.id.pager_tabstrip);

        mViewPager = (ViewPager) view.findViewById(R.id.pager);

//        mErrorLayout = (EmptyLayout) view.findViewById(R.id.error_layout);

        mTabsAdapter = new ViewPageFragmentAdapter(getChildFragmentManager(),
                mTabStrip, mViewPager);
        setScreenPageLimit();
        onSetupTabAdapter(mTabsAdapter);
    }

    @Override
    public void onLeave() {
        super.onLeave();
        try {
            if (mViewPager.getCurrentItem() == 0)
            {
                ((BaseFragment)mTabsAdapter.getItem(0)).onLeave();
            }
        }catch (Exception e){e.printStackTrace();}

    }

    @Override
    public void onBack() {
        super.onBack();
        try {
            if (mViewPager.getCurrentItem() == 0)
            {
                ((BaseFragment)mTabsAdapter.getItem(0)).onBack();
            }
        }catch (Exception e){e.printStackTrace();}
    }

    protected void setScreenPageLimit() {
    }


    protected abstract void onSetupTabAdapter(ViewPageFragmentAdapter adapter);
}