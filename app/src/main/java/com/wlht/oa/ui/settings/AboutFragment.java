package com.wlht.oa.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wlht.oa.R;
import com.wlht.oa.base.BaseTitleFragment;

/**
 * Created by hr on 16/6/20.
 */
public class AboutFragment extends BaseTitleFragment
{

    @Override
    public void initNavBar() {
        mTitleHeaderBar.setTitle("关于我们");
    }

    @Override
    public void initEvent(View view) {

    }

    @Override
    public void initData() {

    }

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflateView(R.layout.fragment_about);
    }
}
