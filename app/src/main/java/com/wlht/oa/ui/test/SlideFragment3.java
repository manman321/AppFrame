package com.wlht.oa.ui.test;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.wlht.oa.R;

/**
 * Created by hr on 16/6/14.
 */
public class SlideFragment3 extends Fragment implements View.OnTouchListener
{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_slide,null,false);
        initView(view);
        return view;
    }



    private View mMenuView,mContentView;
    VelocityTracker mVelocityTracker;
    protected float xDown,xUp,xMove,xPreMobe,velocityX,yDown,yUp,yMove;
    private int screenWidth;
    private static final int SNAP_VELOCITY = 400;
    private boolean menuIsShow = false;
    private boolean wantToScrollView = false;
    private boolean wantToScrollMenu = false;
    //阀值,边缘触发条件,当切仅当 滑动的起点在阀值之内的时候，才可以触发开始滑动。
    private static final int THRESHOLD = 80;

    private int menuWidth;
    FrameLayout.LayoutParams layoutParams;

    private void initView(View view)
    {
        mMenuView = view.findViewById(R.id.menu);
        mContentView = view.findViewById(R.id.content);


        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenWidth = dm.widthPixels;

        layoutParams = (FrameLayout.LayoutParams)mContentView.getLayoutParams();
        layoutParams.width = screenWidth;

        menuWidth = getContext().getResources().getDimensionPixelSize(R.dimen.dp150);


        view.findViewById(R.id.layout).setOnTouchListener(this);
        view.findViewById(R.id.scrollView).setOnTouchListener(this);





    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        acquireVelocityTracker(event);
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                xDown = event.getRawX();
                yDown = event.getRawY();
                xPreMobe = xDown;
                return false;
            case MotionEvent.ACTION_MOVE:
                if (wantToScrollView)
                {
                    if (layoutParams.leftMargin > 0)hideMenu();
                    return false;
                }
                xMove = event.getRawX();
                yMove = event.getRawY();
                if (menuIsShow || isEffectiveSlide())
//                if (menuIsShow || wantToScrollMenu || isEffectiveSlide())
                {
                    isScrollToShowMenu();
                    return true;
                }

                if (Math.abs(xDown - xMove) < Math.abs(yDown - yMove))
                {
                    wantToScrollView = true;
                    return false;
                }
//                else
//                {
//                    wantToScrollMenu = true;
//                }
//                isScrollToShowMenu();
                return false;
//                break;

            case MotionEvent.ACTION_UP:
                if (wantToScrollView)
                {
                    wantToScrollView = false;
                    return false;
                }
                wantToScrollMenu = false;


                xUp = event.getRawX();
                yUp = event.getRawY();
                if (menuIsShow || isEffectiveSlide())
                {
                    isShowMenu();
                }else if (!menuIsShow)
                {
                    hideMenu();
                }
                releaseVelocityTracker();
                return false;

            case MotionEvent.ACTION_CANCEL:
                xUp = event.getRawX();
                yUp = event.getRawY();
                if (!menuIsShow)
                {
                    hideMenu();
                }
//                isShowMenu();
                releaseVelocityTracker();
                return false;
        }
        return true;
    }


    private boolean isEffectiveSlide()
    {
//        if (xDown < THRESHOLD)
//        {
//            if (xPreMobe >= xMove)
//            {
//                return true;
//            }else if (xMove - xDown > THRESHOLD)
//            {
//                return true;
//            }
//        }

        return xDown < THRESHOLD && (xPreMobe >= xMove || xMove - xDown > THRESHOLD);
//        return false;
//       return xMove - xDown > THRESHOLD && xDown < THRESHOLD;
    }

    private void isShowMenu()
    {
        velocityX = getScrollVelocity();
        if (!menuIsShow && xUp - xDown > 0)//显示
        {
            if (xUp - xDown > menuWidth * 0.5 || velocityX > SNAP_VELOCITY)
            {
                //显示
                showMenu();
            }else
            {
                //隐藏
                hideMenu();
            }
        }else if (menuIsShow && xDown - xUp > 0)//隐藏
        {
            if (xDown - xUp > menuWidth * 0.5 || velocityX > SNAP_VELOCITY)
            {
                //隐藏
                hideMenu();
            }else
            {
                //显示
                showMenu();
            }
        }


//        if (wantToShowMenu())
//        {
//            if (shouldShowMenu())
//            {
//                showMenu();
//            }else
//            {
//                hideMenu();
//            }
//        }else if (wantToHideMenu())
//        {
//            if (shouldHideMenu())
//            {
//                hideMenu();
//            }else
//            {
//                showMenu();
//            }
//        }

        //showMenu
        /**
         * 1.当前菜单在显示状态    滑动距离小于一半菜单距离 滑动速度达到要求
         *
         * 2.当前菜单不在显示状态  滑动距离大于一半菜单宽度  滑动速度达到要求
         */

    }

    protected void showMenu()
    {
        new ShowMenuAsyncTask().execute(50);
        menuIsShow = true;
    }

    protected void hideMenu()
    {
        new ShowMenuAsyncTask().execute(-50);
        menuIsShow = false;
    }

    private boolean wantToShowMenu()
    {
        return !menuIsShow && xUp - xDown > 0;
    }

    private boolean wantToHideMenu()
    {
        return menuIsShow && xDown - xUp > 0;
    }

    private boolean shouldShow()
    {
        return (Math.abs(xUp - xDown) > (menuWidth /2)) || velocityX > SNAP_VELOCITY;
    }

    private boolean shouldShowMenu()
    {
        return xUp - xDown > menuWidth /2 || velocityX > SNAP_VELOCITY;
    }

    private boolean shouldHideMenu()
    {
        return xDown - xUp > menuWidth /2 || velocityX > SNAP_VELOCITY;
    }


    private float getScrollVelocity()
    {
        mVelocityTracker.computeCurrentVelocity(1000);
        int velocity = (int)mVelocityTracker.getXVelocity();
        return Math.abs(velocity);
    }




    private void isScrollToShowMenu()
    {
        int distanceX = (int)(xMove - xPreMobe);
        xPreMobe = xMove;

        layoutParams.leftMargin += distanceX;
        if (layoutParams.leftMargin >= menuWidth)layoutParams.leftMargin = menuWidth;
        if (layoutParams.leftMargin <= 0)layoutParams.leftMargin = 0;

        mContentView.setLayoutParams(layoutParams);
    }




    private void acquireVelocityTracker(final MotionEvent event)
    {
        if (null == mVelocityTracker)
        {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
    }

    private void releaseVelocityTracker()
    {
        if (null != mVelocityTracker)
        {
            mVelocityTracker.clear();
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }




    class ShowMenuAsyncTask extends AsyncTask<Integer,Integer,Integer>
    {
//        int leftMargin = ((FrameLayout.LayoutParams)mContentView.getLayoutParams()).leftMargin;

//        final FrameLayout.LayoutParams layoutParams = ((FrameLayout.LayoutParams)mContentView.getLayoutParams());


        @Override
        protected Integer doInBackground(Integer... params) {
            int leftMargin = layoutParams.leftMargin;
            while (true)
            {
                leftMargin += params[0];
                if (params[0] > 0 && leftMargin >= menuWidth)
                {
                    leftMargin = menuWidth;
                    break;
                }else if (params[0] < 0 && leftMargin <= 0)
                {
                    leftMargin = 0;
                    break;
                }
                publishProgress(leftMargin);
                try {
                    Thread.sleep(20);
                }catch (Exception e){}
            }

            return leftMargin;
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            layoutParams.leftMargin = values[0];
            mContentView.setLayoutParams(layoutParams);
        }


        @Override
        protected void onPostExecute(Integer integer) {
            layoutParams.leftMargin = integer;
            mContentView.setLayoutParams(layoutParams);
        }
    }







}
