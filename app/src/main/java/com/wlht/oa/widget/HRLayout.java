package com.wlht.oa.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * Created by hr on 16/6/13.
 */
public class HRLayout extends FrameLayout
{

    public HRLayout(Context context) {
        super(context);
    }

    public HRLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HRLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                System.out.println("HRLayout---dispatchTouchEvent---ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("HRLayout---dispatchTouchEvent---ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                System.out.println("HRLayout---dispatchTouchEvent---ACTION_CANCEL");
                break;

        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                System.out.println("HRLayout---onTouchEvent---ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("HRLayout---onTouchEvent---ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                System.out.println("HRLayout---onTouchEvent---ACTION_CANCEL");
                break;
        }
        return false;
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                System.out.println("HRLayout---onInterceptTouchEvent---ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("HRLayout---onInterceptTouchEvent---ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                System.out.println("HRLayout---onInterceptTouchEvent---ACTION_CANCEL");
                break;
        }
        return super.onInterceptTouchEvent(event);

    }
}
