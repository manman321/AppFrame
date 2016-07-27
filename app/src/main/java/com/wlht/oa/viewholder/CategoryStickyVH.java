package com.wlht.oa.viewholder;


import android.view.View;
import android.widget.TextView;

import com.wlht.oa.R;
import com.wlht.oa.base.BaseViewHolder;
import com.wlht.oa.bean.approve.Template;


public class CategoryStickyVH extends BaseViewHolder<Template> {
    TextView tvState;
    public static final int LAYOUT_TYPE = R.layout.list_cell_contact_header;
    public CategoryStickyVH(View view) {
        super(view);
    }

    @Override
    public int getType() {
        return LAYOUT_TYPE;
    }

    @Override
    public void onBindViewHolder(View view, Template o) {
        if (o != null)tvState.setText(o.categoryName);
    }

    public void initView(View view) {
        tvState = (TextView) view.findViewById(R.id.textView);
    }
}