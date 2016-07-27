package com.wlht.oa.widget;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.wlht.oa.util.TDevice;

/**
 * Created by hr on 16/6/21.
 */
public class ClearEditText extends EditText
{

    protected ImageView clearIv;

    public ClearEditText(Context context) {
        super(context);
        init();
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    protected void initClearImageView()
    {
        clearIv = new ImageView(getContext());
        clearIv.setVisibility(GONE);
        clearIv.setImageResource(android.R.drawable.ic_menu_close_clear_cancel);
        FrameLayout layout = (FrameLayout)getParent();
        if (layout == null)throw new RuntimeException("ClearEditText的父控件必须为FrameLayout");

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams((int) TDevice.dpToPixel(20),(int)TDevice.dpToPixel(20));
        params.gravity = (Gravity.CENTER_VERTICAL|Gravity.END);
        params.rightMargin = (int)TDevice.dpToPixel(6);
        layout.addView(clearIv,params);

        clearIv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setText("");
            }
        });
    }


    private void init()
    {
        setTextColor(Color.BLACK);
        setSingleLine(true);
        setGravity(Gravity.CENTER_VERTICAL);

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (clearIv == null)
                {
                    initClearImageView();
                    getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        });




        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (clearIv != null)
                    clearIv.setVisibility(s.length() > 0 ? View.VISIBLE : View.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



    }




}
