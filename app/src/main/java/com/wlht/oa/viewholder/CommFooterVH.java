package com.wlht.oa.viewholder;


import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wlht.oa.R;
import com.wlht.oa.base.BaseViewHolder;


public class CommFooterVH extends BaseViewHolder<Object> {
    TextView tvState;
    public static final int LAYOUT_TYPE = R.layout.list_footer_view;
    public CommFooterVH(View view) {
        super(view);
    }

    @Override
    public int getType() {
        return LAYOUT_TYPE;
    }

    @Override
    public void onBindViewHolder(View view, Object o) {
        boolean isHasMore = (null == o ? false : true);
//        tvState.setVisibility(isHasMore?View.GONE:View.VISIBLE);
//        progressbar.setVisibility(isHasMore ? View.VISIBLE : View.GONE);
        tvState.setText(isHasMore ? "正在加载" : "已经到底");
    }

    public void initView(View view) {
        tvState = (TextView) view.findViewById(R.id.tv_state);
    }
}