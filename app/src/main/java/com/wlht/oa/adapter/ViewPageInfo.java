package com.wlht.oa.adapter;

import android.os.Bundle;

public final class ViewPageInfo {

	public final String tag;
    public final Class<?> clss;
    public final Bundle args;
    public final String title;

    public  int mSelectedIconRes = -1;
    public  int mNormalIconRes= -1;

    public  int mSelectedTextColor= -1;
    public  int mNormalTextColor= -1;
    public ViewPageInfo(String _title, String _tag, Class<?> _class, Bundle _args) {
    	title = _title;
        tag = _tag;
        clss = _class;
        args = _args;
    }


    public ViewPageInfo(String title, String tag, Class<?> clss, Bundle args, int mSelectedIconRes, int mNormalIconRes, int mSelectedTextColor, int mNormalTextColor) {
        this.tag = tag;
        this.clss = clss;
        this.args = args;
        this.title = title;
        this.mSelectedIconRes = mSelectedIconRes;
        this.mNormalIconRes = mNormalIconRes;
        this.mSelectedTextColor = mSelectedTextColor;
        this.mNormalTextColor = mNormalTextColor;
    }
}