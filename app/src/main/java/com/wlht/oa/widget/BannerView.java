package com.wlht.oa.widget;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.Scroller;

import com.lsjwzh.widget.recyclerviewpager.LoopRecyclerViewPager;
import com.wlht.oa.adapter.BannerAdapter;
import com.wlht.oa.adapter.BaseRecyclerAdapter;
import com.wlht.oa.bean.Banner;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by hr on 16/6/14.
 */
public class BannerView extends FrameLayout
{

    private LoopRecyclerViewPager mBannerView;
    private BannerAdapter mBannerAdapter;

    public BannerView(Context context) {
        super(context);
        initView();
    }

    public BannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public BannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView()
    {
        mBannerView = new LoopRecyclerViewPager(getContext());
        mBannerView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        addView(mBannerView);
        initBanner();
    }


    private static final int duration = 5000;
    static class MyHandler extends Handler {}
    static class BannerRunnable implements Runnable
    {
        WeakReference<BannerView> weakReference;
        public BannerRunnable(BannerView homeFragment)
        {
            weakReference = new WeakReference<>(homeFragment);
        }

        @Override
        public void run() {
            BannerView homeFragment = weakReference.get();
            if (homeFragment != null)
            {
                int pos = homeFragment.mBannerView.getActualCurrentPosition();
                pos++;
                if (pos >= homeFragment.mBannerAdapter.getItemCount())pos = 0;
                homeFragment.mBannerView.smoothScrollToPosition(pos);

                homeFragment.mBannerHandler.postDelayed(this,duration);
            }
        }
    }
    private Handler mBannerHandler = new MyHandler();
    private BannerRunnable mBannerRunnable;

    protected void initBanner()
    {
        LinearLayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);
        mBannerView.setTriggerOffset(0.15f);
        mBannerView.setFlingFactor(0f);
        mBannerView.setLayoutManager(layout);
        mBannerView.setAdapter(mBannerAdapter = new BannerAdapter(getContext()));
        mBannerView.setHasFixedSize(true);
    }


    public void setData(ArrayList<Banner> data)
    {
        mBannerAdapter.addDatas(data);
    }


    public void startTurning()
    {
        if (null == mBannerRunnable)
        {
            mBannerRunnable = new BannerRunnable(this);
        }
        mBannerHandler.removeCallbacks(mBannerRunnable);
        mBannerHandler.postDelayed(mBannerRunnable, duration);
    }

    public void stopTurning()
    {
        mBannerHandler.removeCallbacks(mBannerRunnable);
    }


    public void setOnItemClickListener(BaseRecyclerAdapter.OnItemClickListener listener)
    {
        mBannerAdapter.setOnItemClickListener(listener);
    }



}
