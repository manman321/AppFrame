package com.wlht.oa.ui.test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wlht.oa.R;
import com.wlht.oa.base.BaseTitleFragment;

/**
 * Created by hr on 16/6/21.
 */
public class TestFragment extends BaseTitleFragment
{

    @Override
    public void initNavBar() {

    }

    @Override
    public void initEvent(View view) {

    }

    @Override
    public void initData() {

    }

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflateView(R.layout.fragment_password_modify);
    }

}
