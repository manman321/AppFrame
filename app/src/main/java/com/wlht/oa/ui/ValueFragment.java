package com.wlht.oa.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wlht.oa.R;
import com.wlht.oa.base.BaseListFragment;
import com.wlht.oa.bean.Value;
import com.wlht.oa.viewholder.ValueVH;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by hr on 16/7/25.
 */
public class ValueFragment extends BaseListFragment<Value> {

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflateView(R.layout.fragment_recyclerview);
    }

    @Override
    public void initEvent(View view) {

    }

    @Override
    public void initView(View view) {
        super.initView(view);
        setParam("method", "get")
        .setParam("name", "张三");

    }

    @Override
    public void initData() {
        setView(ValueVH.class)
                .setParam("include", "creater")
                .setIsRefreshable(true);
        refresh();

//                .fetch();
    }


}
