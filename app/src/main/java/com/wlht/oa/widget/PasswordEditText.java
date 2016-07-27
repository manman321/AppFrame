package com.wlht.oa.widget;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.wlht.oa.R;
import com.wlht.oa.util.TDevice;

/**
 * Created by hr on 16/6/21.
 */
public class PasswordEditText extends EditText
{
    protected ImageView clearIv;
    protected CheckBox switchPasswordVisibilityIv;
    protected int space = 6;
    protected int width = 20;

    public PasswordEditText(Context context) {
        super(context);
        init();
    }

    public PasswordEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PasswordEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    protected void initClearImageView()
    {
        clearIv = new ImageView(getContext());
        clearIv.setVisibility(GONE);
        clearIv.setImageResource(android.R.drawable.ic_menu_close_clear_cancel);
        clearIv.setBackgroundColor(Color.TRANSPARENT);
        FrameLayout layout = (FrameLayout)getParent();
        if (layout == null)throw new RuntimeException("ClearEditText的父控件必须为FrameLayout");

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams((int) TDevice.dpToPixel(width),(int)TDevice.dpToPixel(width));
        params.gravity = (Gravity.CENTER_VERTICAL|Gravity.END);
        params.rightMargin = (int)TDevice.dpToPixel(width + 2 * space);
        layout.addView(clearIv,params);

        clearIv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setText("");
            }
        });
    }

    protected void initSwitchPasswordVisibilityImageView()
    {
        switchPasswordVisibilityIv = new CheckBox(getContext());
        switchPasswordVisibilityIv.setButtonDrawable(null);
        switchPasswordVisibilityIv.setBackgroundResource(R.drawable.password_switch);
//        switchPasswordVisibilityIv.setImageResource(android.R.drawable.ic_menu_close_clear_cancel);
        FrameLayout layout = (FrameLayout)getParent();
        if (layout == null)throw new RuntimeException("PasswrodEditText的父控件必须为FrameLayout");

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams((int) TDevice.dpToPixel(40),(int)TDevice.dpToPixel(40));
        params.gravity = (Gravity.CENTER_VERTICAL|Gravity.END);
        params.rightMargin = -(int)TDevice.dpToPixel(space);
        layout.addView(switchPasswordVisibilityIv, params);

        switchPasswordVisibilityIv.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                PasswordEditText.this.setTransformationMethod(isChecked ? HideReturnsTransformationMethod.getInstance() : PasswordTransformationMethod.getInstance());
            }
        });
    }


    private void init()
    {
        setTextColor(Color.BLACK);
        setSingleLine(true);
        setBackgroundColor(Color.TRANSPARENT);

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (clearIv == null)
                {
                    initClearImageView();
                    initSwitchPasswordVisibilityImageView();
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
