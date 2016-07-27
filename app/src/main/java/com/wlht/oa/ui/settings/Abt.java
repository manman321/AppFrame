package com.wlht.oa.ui.settings;/**
 * Created by hr on 16/6/23.
 */

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wlht.oa.base.BaseTitleFragment;

public class Abt extends BaseTitleFragment {


    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(0, null, false);
    }

    @Override
    public void initNavBar() {

    }

    @Override
    public void initView(View view) {
        super.initView(view);
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
