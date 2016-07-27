package com.wlht.oa.viewholder;


import android.view.View;
import android.widget.TextView;

import com.wlht.oa.R;
import com.wlht.oa.base.BaseViewHolder;
import com.wlht.oa.bean.Value;


public class ValueVH extends BaseViewHolder<Value> {
    TextView titleTv;
    TextView idTv;
    public static final int LAYOUT_TYPE = R.layout.list_cell_value;
    public ValueVH(View view) {
        super(view);
    }

    @Override
    public int getType() {
        return LAYOUT_TYPE;
    }

    @Override
    public void onBindViewHolder(View view, Value o) {
        idTv.setText(String.format("%d",o.id));
        titleTv.setText(o.value);
//        boolean isHasMore = (null == o ? false : true);
//        progressbar.setVisibility(isHasMore ? View.VISIBLE : View.GONE);
//        tvState.setText(isHasMore ? "正在加载" : "已经到底");
    }

    public void initView(View view) {
        idTv = (TextView) view.findViewById(R.id.idTv);
        titleTv = (TextView) view.findViewById(R.id.titleTv);
    }
}