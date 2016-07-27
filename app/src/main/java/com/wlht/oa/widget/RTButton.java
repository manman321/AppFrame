package com.wlht.oa.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * Created by hr on 16/6/13.
 */
public class RTButton extends Button
{
    public RTButton(Context context) {
        super(context);
    }

    public RTButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RTButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                System.out.println("RTButton---dispatchTouchEvent---ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("RTButton---dispatchTouchEvent---ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                System.out.println("RTButton---dispatchTouchEvent---ACTION_CANCEL");
                break;

        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                System.out.println("RTButton---onTouchEvent---ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("RTButton---onTouchEvent---ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                System.out.println("RTButton---onTouchEvent---ACTION_CANCEL");
                break;
        }
        return false;
    }
}
