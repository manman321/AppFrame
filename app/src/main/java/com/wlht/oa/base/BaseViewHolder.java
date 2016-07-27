package com.wlht.oa.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;


public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {
    public Context mContext;

    public BaseViewHolder(View v) {
        super(v);
        mContext = v.getContext();
//        ViewUtil.autoFind(this, v);//id与name一致
        initView(v);
    }

    public abstract void initView(View view);

        /**
         * ViewHolder的Type，同时也是它的LayoutId
         *
         * @return
         */
    public abstract int getType();

    /**
     * 绑定ViewHolder
     *
     * @return
     */
    public abstract void onBindViewHolder(View view, T obj);

}
