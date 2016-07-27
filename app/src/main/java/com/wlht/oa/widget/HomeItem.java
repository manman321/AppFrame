package com.wlht.oa.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.wlht.oa.R;

/**
 * Created by hr on 16/6/12.
 */
public class HomeItem extends FrameLayout
{
    private static final float factor = 1.05f;
    private int mWh = 0;
    private ImageView mImageView;
    private TextView mTextView;
    private OnItemClickListener listener;
    public HomeItem(Context context,int wh)
    {
        super(context);
        mWh = wh;
//        setLayoutParams(new FrameLayout.LayoutParams(wh,wh));
        setBackgroundColor(Color.WHITE);
        init();
    }

    public void setListener(OnItemClickListener listener)
    {
        this.listener = listener;
    }

    private void init()
    {

        int topMargin = (int)(mWh * 0.15f);
        mImageView = new ImageView(getContext());

        mTextView = new TextView(getContext());

        int imageWh = (int)(mWh * 0.5f);

        FrameLayout.LayoutParams imageParams = new FrameLayout.LayoutParams(imageWh,imageWh);
        imageParams.topMargin = topMargin;
        imageParams.leftMargin = (int)(mWh * 0.25f);
        mImageView.setLayoutParams(imageParams);


        int textHeight = (int)((mWh - imageWh - topMargin) * 0.5f);
        FrameLayout.LayoutParams textParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,textHeight);
        textParams.gravity = Gravity.CENTER_HORIZONTAL;
        textParams.topMargin = (int)((mWh - imageWh - topMargin - textHeight) * 0.5f) + topMargin + imageWh;
        mTextView.setTextColor(Color.BLACK);

        addView(mImageView);
        addView(mTextView, textParams);
        setClickable(true);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                setBackgroundResource(R.color.c_E6E6E6);
                scaleViewAnimation(mImageView, factor);
                break;
            case MotionEvent.ACTION_CANCEL:
                setBackgroundColor(Color.WHITE);
                scaleViewAnimation(mImageView, 1);
                break;
            case MotionEvent.ACTION_UP:
                setBackgroundColor(Color.WHITE);
                scaleViewAnimation(mImageView, 1);
//                if (listener != null && touchInView(HomeItem.this,(int)event.getRawX(),(int)event.getRawY())){
//                    listener.onItemClick(HomeItem.this);
//                }
                break;
        }
        return super.onTouchEvent(event);
    }

    private Rect mChangeImageBackgroundRect = null;
    private boolean touchInView(View view, int x, int y) {
        if (null == mChangeImageBackgroundRect) {
            mChangeImageBackgroundRect = new Rect();
        }
        view.getDrawingRect(mChangeImageBackgroundRect);
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        mChangeImageBackgroundRect.left = location[0];
        mChangeImageBackgroundRect.top = location[1];
        mChangeImageBackgroundRect.right = mChangeImageBackgroundRect.right + location[0];
        mChangeImageBackgroundRect.bottom = mChangeImageBackgroundRect.bottom + location[1];
        return mChangeImageBackgroundRect.contains(x, y);
    }

//    private boolean touchInView(View view,int x,int y)
//    {
//        Rect rect = new Rect();
//        view.getDrawingRect(rect);
//
//        int[] location = new int[2];
//        view.getLocationOnScreen(location);
//
//        rect.top = location[0];
//        rect.left = location[1];
//       return rect.contains(x,y);
//
//    }





    public void setDrawableWithText(int res,String text)
    {
        mImageView.setImageResource(res);
        mTextView.setText(text);
    }

    protected int dp2px(Context context, int dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }



    /**
     * 缩放动画
     *
     * @param value
     */
    private void scaleViewAnimation(View view, float value) {
        view.animate().scaleX(value).scaleY(value).setDuration(80).start();
    }


    public interface OnItemClickListener{
        void onItemClick(HomeItem homeItem);
    }


}
