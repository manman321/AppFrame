package com.wlht.oa.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.wlht.oa.AppContext;
import com.wlht.oa.Constants;
import com.wlht.oa.R;
import com.wlht.oa.base.exception.ApiException;
import com.wlht.oa.util.NetworkProber;
import com.wlht.oa.widget.EmptyLayout;

import java.lang.ref.WeakReference;

import in.srain.cube.app.CubeFragment;
import in.srain.cube.views.ptr.PtrClassicDefaultFooter;
import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrFrameLayout;


/**
 * 碎片基类
 * 
 * @author FireAnt（http://my.oschina.net/LittleDY）
 * @created 2014年9月25日 上午11:18:46
 * 
 */
public abstract class BaseFragment extends CubeFragment implements View.OnClickListener,View.OnTouchListener{

    public static final int STATE_NONE = 0;
    public static final int STATE_REFRESH = 1;
    public static final int STATE_LOADMORE = 2;
    public static final int STATE_NOMORE = 3;
    public static final int STATE_PRESSNONE = 4;// 正在下拉但还没有到刷新的状态
    public static int mState = STATE_NONE;

    protected View mFragmentView;

    protected LayoutInflater mInflater;

    protected EmptyLayout mEmptyLayout;

    protected View mNetworkStatusHintView;

    public RxManager mRxManager = new RxManager();


    public AppContext getApplication() {
        return (AppContext) getActivity().getApplication();
    }

    private BaseActivity mActivity;
    public BaseActivity getContext() {
        if (mActivity == null)mActivity = (BaseActivity)getActivity();
        return mActivity;
    }

    public static class MyHandler extends Handler{public MyHandler(){}}

    private static class MyRunnable implements Runnable
    {
        WeakReference<EmptyLayout> mEmptyLayout;

        public MyRunnable(EmptyLayout emptyLayout)
        {
            mEmptyLayout = new WeakReference<EmptyLayout>(emptyLayout);
        }
        @Override
        public void run() {
            if (mEmptyLayout.get() == null)return;
            try {
                if (NetworkProber.checkNet(AppContext.getInstance())) {
                    mEmptyLayout.get().setErrorType(EmptyLayout.HIDE_LAYOUT);
                } else {
                    mEmptyLayout.get().setErrorType(EmptyLayout.NETWORK_ERROR);
                }
            }catch (Exception e){e.printStackTrace();}
        }
    }

    public static class AutoRefresh implements Runnable
    {
        WeakReference<PtrFrameLayout> mPtrFrameLayout;
        public AutoRefresh(PtrFrameLayout emptyLayout)
        {
            mPtrFrameLayout = new WeakReference<PtrFrameLayout>(emptyLayout);
        }
        @Override
        public void run() {
            if (mPtrFrameLayout.get() == null)return;
            try {
                mPtrFrameLayout.get().autoRefresh();
            }catch (Exception e){e.printStackTrace();}
        }
    }


    protected void autoRefreshWithDelay(PtrFrameLayout ptrFrameLayout)
    {
        new MyHandler().postDelayed(new AutoRefresh(ptrFrameLayout), 500);
    }

    protected void autoRefreshWithDelay(PtrFrameLayout ptrFrameLayout,long delayMillis)
    {
        new MyHandler().postDelayed(new AutoRefresh(ptrFrameLayout), delayMillis);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mInflater = inflater;
        ViewGroup rootView  = (ViewGroup)inflateView(R.layout.base_fragment);
        initBaseView(rootView, inflater, container, savedInstanceState);
        return rootView;
    }

    final void initBaseView(View rootView,LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        mNetworkStatusHintView = rootView.findViewById(R.id.networkStatusHintView);
        if (mNetworkStatusHintView != null)mNetworkStatusHintView.setOnClickListener(mNetworkStatusClickListener);
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(mNetWorkReceiver, new IntentFilter(Constants.INTENT_ACTION_NETWORK_STATUS_CHANGED));

        FrameLayout containerLayout = (FrameLayout)rootView.findViewById(R.id.contentContainer);
        mEmptyLayout = (EmptyLayout)rootView.findViewById(R.id.error_layout);
        mEmptyLayout.setOnLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEmptyLayout.setErrorType(EmptyLayout.NETWORK_LOADING);
                new MyHandler().postDelayed(new MyRunnable(mEmptyLayout),500);
            }
        });
        View view = createView(inflater, container, savedInstanceState);
        mFragmentView = view;
        view.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        containerLayout.addView(view);
        initView(view);
        initEvent(view);
    }


    protected View inflateView(int resId) {
        return mInflater.inflate(resId, null);
    }


    // onTouch事件 将上层的触摸事件拦截
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // 拦截触摸事件，防止泄露下去
        super.onViewCreated(view,savedInstanceState);
        view.setOnTouchListener(this);
    }


    @Override
    public void onResume() {
        super.onResume();
        getContext().Bus.register(this);
//        if (isFirstResume())
        {
            initData();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        getContext().Bus.unregister(this);
    }

    /**
     * make it looks like Activity
     */
    public void onBackPressed() {
        getContext().onBackPressed();
    }

    @Override
    public boolean processBackPressed() {
        popTopFragment();
        return true;
    }

    public void popTopFragment()
    {
        popTopFragment(null);
    }

    public void popTopFragment(Object data)
    {
        try
        {
            int count = getContext().getSupportFragmentManager().getBackStackEntryCount();
            if (count == 1)
            {
                getContext().mCurrentFragment = getContext().mCubeFragment;
            }
        }catch (Exception e){e.printStackTrace();}
        getContext().popTopFragment(data);
    }



    protected boolean enableDefaultBack() {
        return true;
    }

    public abstract void initView(View view);

    public abstract void initEvent(View view);

    public abstract void initData();


    @Override
    public void onClick(View v) {

    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        getContext().overridePendingTransition(R.anim.page_right_slide_in, R.anim.page);
    }



    public void clearFocus()
    {
        View view = getContext().getCurrentFocus();
//        if (view != null)view.clearFocus();
        if (view != null)getContext().hideKeyboardForCurrentFocus();
    }

    public boolean hubCheckNetwork()
    {
        boolean isConnected =  NetworkProber.checkNet(getContext());
        mEmptyLayout.setErrorType(isConnected ? EmptyLayout.NETWORK_LOADING : EmptyLayout.NETWORK_ERROR);
        return isConnected;
    }

    public void hubShow()
    {
//        Class clazz = getClass().getSuperclass();
//        if (clazz == (Class)BaseFragment.class)
//        {
//            if (mEmptyLayout != null)
//            {
//                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams)mEmptyLayout.getLayoutParams();
//                layoutParams.topMargin = (int) TDevice.dpToPixel(44);
//
//            }
//        }
        if (mEmptyLayout != null)mEmptyLayout.setErrorType(EmptyLayout.NETWORK_LOADING);
    }

    public void hubSuccess()
    {
        //do something
        hubHide();
    }

    public void hubHide()
    {
        if (mEmptyLayout != null)
        {
            mEmptyLayout.setErrorType(NetworkProber.checkNet(getContext()) ? EmptyLayout.HIDE_LAYOUT : EmptyLayout.NETWORK_ERROR);
        }
    }

    public void hubJustHide()
    {
        if (mEmptyLayout != null)
        {
            mEmptyLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
        }
    }

    protected boolean loginFilter()
    {
        boolean isLogin = AppContext.getInstance().isLogin();
//        try {
//            if (!isLogin) UIHelper.showLoginActivity(AppManager.getAppManager().currentActivity());
//        }catch (Exception e){e.printStackTrace();}
        return isLogin;
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.out.println("onLowMemory:" + this);
    }


    final public void setDefaultFooter(PtrFrameLayout ptrFrameLayout)
    {
        PtrClassicDefaultFooter footer = new PtrClassicDefaultFooter(getContext());
        ptrFrameLayout.setFooterView(footer);
        ptrFrameLayout.addPtrUIHandler(footer);
    }

    final public void setDefaultHeader(PtrFrameLayout ptrFrameLayout)
    {
        PtrClassicDefaultHeader header = new PtrClassicDefaultHeader(getContext());
        ptrFrameLayout.setDurationToCloseHeader(1500);
        ptrFrameLayout.setHeaderView(header);
        ptrFrameLayout.addPtrUIHandler(header);
        ptrFrameLayout.disableWhenHorizontalMove(true);
    }

    final public void setDefaultHeaderAndFooter(PtrFrameLayout ptrFrameLayout)
    {
        setDefaultHeader(ptrFrameLayout);
        setDefaultFooter(ptrFrameLayout);
    }

    protected boolean isRegisterNetworkStatusChangedReceiver = false;
    protected void registerNetworkStatusChangedReceiver()
    {
        isRegisterNetworkStatusChangedReceiver = true;
    }

    protected void registerNetworkStatusChangedReceiver(View view)
    {
        if (view != null && view.findViewById(R.id.networkStatusHintView) != null)
        {
            if (mNetworkStatusHintView != null)
            {
                mNetworkStatusHintView.setVisibility(View.GONE);
                mNetworkStatusHintView.setOnClickListener(null);
                mNetworkStatusHintView = null;
            }
            mNetworkStatusHintView = view.findViewById(R.id.networkStatusHintView);
            mNetworkStatusHintView.setOnClickListener(mNetworkStatusClickListener);
            mNetworkStatusHintView.setVisibility(AppContext.getInstance().isNetworkConnected() ? View.GONE : View.VISIBLE);
        }
        registerNetworkStatusChangedReceiver();
    }

    private BroadcastReceiver mNetWorkReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Constants.INTENT_ACTION_NETWORK_STATUS_CHANGED)) {
                //子界面是否主动申请注册监听,申请监听后父类会接管网络变化事件,用于统一显示网络状态变化
                //子类没有注册的时候，只有当网络状态改变成为已连接,才会改变子界面
                if (isRegisterNetworkStatusChangedReceiver)
                {
                    if (mNetworkStatusHintView != null)mNetworkStatusHintView.setVisibility(AppContext.getInstance().isNetworkConnected()?View.GONE:View.VISIBLE);
                    if (mEmptyLayout != null)mEmptyLayout.setErrorType(NetworkProber.checkNet(getContext()) ? EmptyLayout.HIDE_LAYOUT : EmptyLayout.NETWORK_ERROR);

                }else
                {
                    if (mEmptyLayout != null && NetworkProber.checkNet(getContext()))mEmptyLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
                }
            }
        }
    };

    private View.OnClickListener mNetworkStatusClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent =  new Intent(Settings.ACTION_SETTINGS);
            startActivity(intent);
        }
    };


    @Override
    public void onDestroy() {
        super.onDestroy();
        mRxManager.clear();
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(mNetWorkReceiver);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    @Override
    public void animationWithOpenFragment(FragmentTransaction fragmentTransaction, CubeFragment fragment) {
        super.animationWithOpenFragment(fragmentTransaction, fragment);
        fragmentTransaction.setCustomAnimations(R.anim.page_fade_in, R.anim.page_fade_out, R.anim.page_fade_in, R.anim.page_fade_out);
//        fragmentTransaction.setCustomAnimations(R.anim.page_right_slide_in,R.anim.page_right_slide_out,R.anim.page_right_slide_in,R.anim.page_right_slide_out);

    }

    public void hideKeyboardForCurrentFocus()
    {
        getContext().hideKeyboardForCurrentFocus();
    }

    public void showToast(String message)
    {
        getContext().showToast(message);
    }


    public static  <T extends View>T  find(View view,int res)
    {
       return (T)view.findViewById(res);
    }

    public <T extends View>T find(int res)
    {
        return (T)mFragmentView.findViewById(res);
    }



    public void onReqStart()
    {
//        mRxManager.clear();
        hubShow();
    }


    public void onReqCompleted()
    {
        hubShow();
    }

    public void onReqError(ApiException ex)
    {
        showToast(ex.message);
    }

}
