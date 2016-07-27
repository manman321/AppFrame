package com.wlht.oa.base;

import android.view.View;

import com.wlht.oa.R;
import com.wlht.oa.util.NetworkProber;
import com.wlht.oa.widget.EmptyLayout;

import in.srain.cube.mints.base.TitleHeaderBar;

/**
 * Created by hr on 16/4/1.
 */
public abstract class BaseTitleActivity extends BaseActivity
{
    protected TitleHeaderBar mTitleHeaderBar;
//    protected FrameLayout mContentContainer;

//    public EmptyLayout emptyLayout()
//    {
//        return (EmptyLayout)findViewById(R.id.error_layout);
//    }


    @Override
    protected int rootLayoutView() {
        return R.layout.base_content_frame_with_title_header;
    }

    @Override
    void initBaseView(View rootView) {
        mTitleHeaderBar = (TitleHeaderBar) rootView.findViewById(R.id.content_frame_title_header);
        if (enableDefaultBack()) {
            mTitleHeaderBar.setLeftOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!processBackPressed()) {
                        doReturnBack();
                    }
                }
            });
        } else {
            mTitleHeaderBar.getLeftViewContainer().setVisibility(View.INVISIBLE);
        }
        super.initBaseView(rootView);
    }

//    //禁止重写改方法
//    @Override
//    final protected int getLayoutId() {
//        return super.getLayoutId();
//    }
//
//    @Override
//    protected void onBeforeSetContentLayout() {
//        initViews();
//    }
//
//
//
//
//    protected int getFrameLayoutId() {
//        return R.layout.base_content_frame_with_title_header;
//    }
//
//    protected TitleHeaderBar getTitleHeaderBar() {
//        return (TitleHeaderBar) findViewById(R.id.content_frame_title_header);
//    }
//
//    protected FrameLayout getContentContainer() {
//        return (FrameLayout) findViewById(R.id.contentContainer);
//    }

//    protected void initViews() {
//        super.setContentView(getFrameLayoutId());
//        mTitleHeaderBar = getTitleHeaderBar();
//        mContentContainer = getContentContainer();
//        if (enableDefaultBack()) {
//            mTitleHeaderBar.setLeftOnClickListener(new View.OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//                    if (!processBackPressed()) {
//                        doReturnBack();
//                    }
//                }
//            });
//        } else {
//            mTitleHeaderBar.getLeftViewContainer().setVisibility(View.INVISIBLE);
//        }
//
//        int layoutResID = layoutId();
//        if (layoutResID != 0)
//        {
//            View view = LayoutInflater.from(this).inflate(layoutResID, null);
//            view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//            mContentContainer.addView(view);
//        }
//    }

    protected boolean enableDefaultBack() {
        return true;
    }

//    protected int layoutId(){
//        return 0;
//    }


//    @Override
//    public void setContentView(int layoutResID) {
//        View view = LayoutInflater.from(this).inflate(layoutResID, null);
//        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        mContentContainer.addView(view);
//    }

//    public void setContentViewSupper(int layoutResID) {
//        super.setContentView(layoutResID);
//    }

    protected void setHeaderTitle(int id) {
        mTitleHeaderBar.getTitleTextView().setText(id);
    }

    protected void setHeaderTitle(String title) {
        mTitleHeaderBar.setTitle(title);
    }




    public boolean hubCheckNetwork()
    {
        boolean isConnected =  NetworkProber.checkNet(mContext);
        mEmptyLayout.setErrorType(isConnected? EmptyLayout.NETWORK_LOADING:EmptyLayout.NETWORK_ERROR);
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
            mEmptyLayout.setErrorType(NetworkProber.checkNet(mContext) ? EmptyLayout.HIDE_LAYOUT : EmptyLayout.NETWORK_ERROR);
        }
    }

}
