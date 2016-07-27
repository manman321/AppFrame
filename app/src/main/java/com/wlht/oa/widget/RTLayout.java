package com.wlht.oa.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * Created by hr on 16/6/13.
 */
public class RTLayout extends FrameLayout
{

    public RTLayout(Context context) {
        super(context);
    }

    public RTLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RTLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                System.out.println("RTLayout---dispatchTouchEvent---ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("RTLayout---dispatchTouchEvent---ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                System.out.println("RTLayout---dispatchTouchEvent---ACTION_CANCEL");
                break;

        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                System.out.println("RTLayout---onTouchEvent---ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("RTLayout---onTouchEvent---ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                System.out.println("RTLayout---onTouchEvent---ACTION_CANCEL");
                break;
        }
        return true;
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                System.out.println("RTLayout---onInterceptTouchEvent---ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("RTLayout---onInterceptTouchEvent---ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                System.out.println("RTLayout---onInterceptTouchEvent---ACTION_CANCEL");
                break;
        }
        return super.onInterceptTouchEvent(event);
    }
}
