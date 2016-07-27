package com.wlht.oa.base;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.squareup.otto.Bus;
import com.wlht.oa.AppContext;
import com.wlht.oa.AppManager;
import com.wlht.oa.Constants;
import com.wlht.oa.R;
import com.wlht.oa.bean.Result;
import com.wlht.oa.ui.MainActivity;
import com.wlht.oa.util.DialogHelp;
import com.wlht.oa.util.NetworkProber;
import com.wlht.oa.util.TDevice;
import com.wlht.oa.widget.CommonToast;
import com.wlht.oa.widget.EmptyLayout;

import java.lang.ref.WeakReference;

import in.srain.cube.app.CubeFragment;
import in.srain.cube.app.CubeFragmentActivity;
import in.srain.cube.views.ptr.PtrClassicDefaultFooter;
import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrFrameLayout;


/**
 * baseActionBar Activity
 * 
 * @author FireAnt（http://my.oschina.net/LittleDY）
 * @created 2014年9月25日 上午11:30:15 引用自：tonlin
 */
public abstract class BaseActivity extends CubeFragmentActivity implements View.OnClickListener
{
    public static final String INTENT_ACTION_EXIT_APP = "INTENT_ACTION_EXIT_APP";
    private boolean _isVisible;
    private ProgressDialog _waitDialog;
    protected LayoutInflater mInflater;
    protected Context mContext;
    public CubeFragment mCubeFragment;

    public static Bus Bus = new Bus();

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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(mContext).unregisterReceiver(mNetWorkReceiver);
        TDevice.hideSoftKeyboard(getCurrentFocus());
//        ButterKnife.reset(this);
    }

    protected int rootLayoutView()
    {
        return R.layout.base_fragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
        mContext = this;
        mInflater = getLayoutInflater();
        onBeforeSetContentLayout();
//        if (getLayoutId() != 0) {
        View rootView = mInflater.inflate(rootLayoutView(), null, false);
        initBaseView(rootView);
        setContentView(rootView);
//        }
//        ButterKnife.inject(this);
        initView();
        initEvent();
        initData();
        _isVisible = true;
    }

    protected EmptyLayout mEmptyLayout;

    protected View mNetworkStatusHintView;


    void initBaseView(View rootView)
    {
        mNetworkStatusHintView = rootView.findViewById(R.id.networkStatusHintView);
        if (mNetworkStatusHintView != null)mNetworkStatusHintView.setOnClickListener(mNetworkStatusClickListener);
        LocalBroadcastManager.getInstance(mContext).registerReceiver(mNetWorkReceiver, new IntentFilter(Constants.INTENT_ACTION_NETWORK_STATUS_CHANGED));

        FrameLayout containerLayout = (FrameLayout)rootView.findViewById(R.id.contentContainer);
        mEmptyLayout = (EmptyLayout)rootView.findViewById(R.id.error_layout);
        mEmptyLayout.setOnLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEmptyLayout.setErrorType(EmptyLayout.NETWORK_LOADING);
                new MyHandler().postDelayed(new MyRunnable(mEmptyLayout),500);
            }
        });
        if (getLayoutId() != 0)
            mInflater.inflate(getLayoutId(), containerLayout, true);
//        View view = createView();
//        view.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
//        containerLayout.addView(view);
//        initView();
    }

//    protected View createView(){
//        return mInflater.inflate(getLayoutId(), null, false);
//    }


    /**
     * 互斥操作,
     * 当使用databingding的时候,重写onBeforeSetContentLayout
     * 当不使用databinding的时候重写getLayoutId方法
     */
    protected void onBeforeSetContentLayout() {}

    protected int getLayoutId(){return 0;}

    protected abstract void initView();
    protected abstract void initEvent();
    protected abstract void initData();



    protected View inflateView(int resId) {
        return mInflater.inflate(resId, null);
    }




    public void showToast(int msgResid, int icon, int gravity) {
        showToast(getString(msgResid), icon, gravity);
    }

    public void showToast(String message)
    {
        if (message == null || message.trim().length() == 0)return;
        showToast(message, 0, 0);
    }

    public void showToast(String message, int icon, int gravity) {
        CommonToast toast = new CommonToast(this);
        toast.setMessage(message);
        toast.setMessageIc(icon);
        toast.setLayoutGravity(gravity);
        toast.show();
    }


    @Override
    protected String getCloseWarning() {
        return getString(in.srain.cube.R.string.cube_mints_exit_tip);
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.contentContainer;
    }


    public void onError(Result result)
    {
        if (result != null && result.getMsg() != null && result.getMsg().trim().length() != 0)
        {
            showToast(result.getMsg());
        }
    }

    @Override
    public void finish() {
        super.finish();
        if (!(this instanceof MainActivity))
        {
            overridePendingTransition(R.anim.page, R.anim.page_right_slide_out);
        }
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.page_right_slide_in, R.anim.page);
    }


    final public void setDefaultFooter(PtrFrameLayout ptrFrameLayout)
    {
        PtrClassicDefaultFooter footer = new PtrClassicDefaultFooter(mContext);
        ptrFrameLayout.setFooterView(footer);
        ptrFrameLayout.addPtrUIHandler(footer);
    }

    final public void setDefaultHeader(PtrFrameLayout ptrFrameLayout)
    {
        PtrClassicDefaultHeader header = new PtrClassicDefaultHeader(mContext);
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
                    if (mEmptyLayout != null)mEmptyLayout.setErrorType(NetworkProber.checkNet(mContext) ? EmptyLayout.HIDE_LAYOUT : EmptyLayout.NETWORK_ERROR);

                }else
                {
                    if (mEmptyLayout != null && NetworkProber.checkNet(mContext))mEmptyLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
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
    protected void onResume() {
        super.onResume();
//        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        MobclickAgent.onPause(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
    }
}
