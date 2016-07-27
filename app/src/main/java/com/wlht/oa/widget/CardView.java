package com.wlht.oa.widget;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.ObjectAnimator;
import com.wlht.oa.R;
import com.wlht.oa.adapter.BaseRecyclerAdapter;
import com.wlht.oa.adapter.FoodAdapter;
import com.wlht.oa.adapter.NewsAdapter;
import com.wlht.oa.bean.Food;
import com.wlht.oa.decoration.DividerGridItemDecoration;
import com.wlht.oa.decoration.DividerItemMiniDecoration;
import com.wlht.oa.util.TLog;

import java.util.ArrayList;

/**
 * Created by hr on 16/7/1.
 */
public class CardView extends FrameLayout implements View.OnClickListener
{

    protected View containerView;
    protected View headerView;

    private TextView descriptionTv;
    private ImageView iconIv;
    private TextView nameTv;
    private TextView addTv;
    private TextView yuyueTv;
    private TextView minTv;
    private RecyclerView recyclerView;

    private FoodAdapter mAdapter;

    public CardView(Context context) {
        super(context);
        init();
    }

    public CardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    protected boolean isAnimated = false;

    protected float translationY = 0;


    protected void init()
    {
        final LinearLayout view = (LinearLayout)LayoutInflater.from(getContext()).inflate(R.layout.view_card,null,false);
        addView(view);


        headerView = findViewById(R.id.headerView);
        descriptionTv = (TextView) findViewById(R.id.descriptionTv);
        iconIv = (ImageView) findViewById(R.id.iconIv);
        nameTv = (TextView) findViewById(R.id.nameTv);
        containerView = findViewById(R.id.container);
        addTv = (TextView) findViewById(R.id.addTv);
        yuyueTv = (TextView) findViewById(R.id.yuyueTv);
        minTv = (TextView) findViewById(R.id.minTv);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        headerView.setOnClickListener(this);
        addTv.setOnClickListener(this);
        minTv.setOnClickListener(this);


        GridLayoutManager manager = new GridLayoutManager(getContext(),3);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new DividerGridItemDecoration(getContext(), false));
        recyclerView.setAdapter(mAdapter = new FoodAdapter());


        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, Object data) {
                Food food = (Food)data;
                food.isSelected = !food.isSelected;
                mAdapter.notifyItemChanged(mAdapter.indexOf(food));
            }
        });


    }


    public void setIconAndTxt(int res,String text)
    {
        iconIv.setImageResource(res);
        nameTv.setText(text);
    }

    public void setFoods(ArrayList<Food> list)
    {
        mAdapter.clear();
        mAdapter.addDatas(list);
    }


    private void executeAnimation()
    {
        ObjectAnimator animator = null;

        if (isAnimated)
        {
            isAnimated = false;
            animator =  ObjectAnimator.ofFloat(CardView.this, "translationY", -translationY, 0);
        }else
        {
            translationY = getY();
            animator =  ObjectAnimator.ofFloat(CardView.this, "translationY", 0, -translationY);
            isAnimated = true;
        }
        final LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)containerView.getLayoutParams();
        animator.setInterpolator(new AccelerateDecelerateInterpolator() {
            @Override
            public float getInterpolation(float input) {
                float output = super.getInterpolation(input);
                if (interpolator != null)interpolator.getInterpolation(input);

                int height = 0;
                if (isAnimated) {
                    height = (int) (input * translationY);
                } else {
                    height = (int) ((1 - input) * translationY);
                }

                TLog.log("Height Changed :" + height);
                params.height = height;

                containerView.setLayoutParams(params);

                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
                layoutParams.height = height + headerView.getHeight();
                setLayoutParams(layoutParams);
                return output;
            }
        });

        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }
        });

        animator.setDuration(300).start();
    }

    protected TimeInterpolator interpolator;

    public void setInterpolator(TimeInterpolator interpolator)
    {
        this.interpolator = interpolator;
    }

    public boolean isShowExpand()
    {
        return isAnimated;
    }


    protected int yuyueNum = 0;

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.headerView:
                executeAnimation();
                break;

            case R.id.addTv:
                yuyueNum++;
                udpateYuyueTvText();
                break;

            case R.id.minTv:
                if (yuyueNum > 0)yuyueNum--;
                udpateYuyueTvText();
                break;

        }
    }

    protected void udpateYuyueTvText()
    {
        yuyueTv.setText(yuyueNum == 0 ? "未预约" : String.format("%d人预约",yuyueNum));
    }
}
