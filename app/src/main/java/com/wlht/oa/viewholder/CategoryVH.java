package com.wlht.oa.viewholder;


import android.view.View;
import android.widget.TextView;

import com.wlht.oa.R;
import com.wlht.oa.base.BaseViewHolder;
import com.wlht.oa.bean.approve.Template;


public class CategoryVH extends BaseViewHolder<Template> {
    TextView textView;
    public static final int LAYOUT_TYPE = R.layout.list_cell_category;
    public CategoryVH(View view) {
        super(view);
    }

    @Override
    public int getType() {
        return LAYOUT_TYPE;
    }

    @Override
    public void onBindViewHolder(View view, Template o) {
        if (o != null)textView.setText(o.title);
    }

    public void initView(View view) {
        textView = (TextView) view.findViewById(R.id.textView);
    }
}