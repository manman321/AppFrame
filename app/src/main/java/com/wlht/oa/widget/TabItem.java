package com.wlht.oa.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.wlht.oa.R;

/**
 * Created by Administrator on 2016/6/6.
 */
public class TabItem extends FrameLayout
{

    public TabItem(Context context) {
        super(context);
        init();
    }

    public TabItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TabItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private View readDotIv,unreadRly;
    private TextView unreadTv;

    private RadioButton itemRb;
    private CheckBox itemCb;
    private void init(){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_tab_item,null,false);
        addView(view);
        readDotIv = view.findViewById(R.id.readdotIv);
        unreadRly = view.findViewById(R.id.unreadRly);
        unreadTv  = (TextView)view.findViewById(R.id.unreadTv);
        itemRb = (RadioButton)view.findViewById(R.id.itemRb);
        itemCb = (CheckBox)view.findViewById(R.id.itemCb);
    }

    public void setSelected(boolean selected)
    {
        itemRb.setSelected(selected);
        itemCb.setSelected(selected);
    }

    public void setDrawableWithText(int res,String text)
    {
        itemCb.setBackgroundResource(res);
        itemRb.setText(text);
    }

}
