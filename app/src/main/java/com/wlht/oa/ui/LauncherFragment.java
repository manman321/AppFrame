package com.wlht.oa.ui;/**
 * Created by hr on 16/7/18.
 */

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wlht.oa.R;
import com.wlht.oa.base.BaseFragment;
import com.wlht.oa.ui.user.LoginFragment;

public class LauncherFragment extends BaseFragment {

    ImageView mBackgroundView;
    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBackgroundView = new ImageView(getContext());
        mBackgroundView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mBackgroundView.setScaleType(ImageView.ScaleType.FIT_XY);
        return mBackgroundView;
    }


    @Override
    public void initView(View view) {

    }

    @Override
    public void initEvent(View view) {

    }

    @Override
    public void initData() {
        mBackgroundView.setImageResource(R.drawable.launcher);

        new MyHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getContext().popTopFragment(null);
                getContext().pushFragmentToBackStack(LoginFragment.class, null);
            }
        },2000);
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
