package com.wlht.oa.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.wlht.oa.R;
import com.wlht.oa.bean.flow.Flow;
import com.wlht.oa.bean.flow.Line;
import com.wlht.oa.bean.flow.Position;
import com.wlht.oa.bean.flow.Step;
import com.wlht.oa.util.TDevice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by hr on 16/7/13.
 */
public class FlowView extends FrameLayout
{
    private Flow mFlow;
    public FlowView(Context context) {
        super(context);
        init();
    }

    public FlowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FlowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }



    private void init()
    {
        left = Integer.MAX_VALUE;
        top = Integer.MAX_VALUE;
        right = 0;
        bottom = 0;
    }


    private ArrayList<TextView> mButtons = new ArrayList<>();
    private int left,top,right,bottom;





    private static final float SCALE = 2;
    private static final int PADDING = 20;
    private HashMap<String,Position> mMapping = new HashMap<>();
    public void initWithFlow(Flow flow)
    {
        mFlow = flow;
        List<Step> steps = mFlow.getSteps();

        //剔除无用的节点(步骤)  无用表明,发起者的角色,在整个流程中的位置。


        for (int i = 0; i < steps.size();i++)
        {
            Step step = steps.get(i);
            Position position = step.getPosition();
            if (left > position.getX())left = position.getX();
            if (top > position.getY())top = position.getY();

        }


        for (int i = 0; i < steps.size();i++)
        {
            Step step = steps.get(i);
            Position position = step.getPosition();

            mMapping.put(step.getId(),position);

            TextView textView = new TextView(getContext());
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams((int)(position.getWidth() * SCALE),(int)(position.getHeight() * SCALE));
            params.topMargin = (int)((position.getY() - top) * SCALE);
            params.leftMargin = (int)((position.getX() - left) * SCALE);
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(Color.BLACK);
            textView.setTextSize(8);
            textView.setLayoutParams(params);

            textView.setText(step.getName());

            textView.setBackgroundResource(R.color.main_color);

            int temp = (int)(((position.getX() + position.getWidth() - left) * SCALE));
            if (right < temp)right = temp;
            temp = (int)(((position.getY() + position.getHeight() - top) * SCALE));
            if (bottom < temp)bottom = temp;

            mButtons.add(textView);
            addView(textView);
        }

//        MyCanvas view = new MyCanvas(getContext());
//        addView(view);
//        view.init(0, 0, 200, 200);


        ArrowView arrowView = new ArrowView(getContext());


        List<Line> lines = mFlow.getLines();
        for (int i = 0; i < lines.size();i++)
        {
            Line line = lines.get(i);
            Position start = mMapping.get(line.getFrom());
            Position end   = mMapping.get(line.getTo());

            int color = ArrowView.Arrow.FINISH_COLOR;
            if (end.getX() < (start.getX() + start.getWidth()))//第二个点的x位置 小于第一个
            {
                if (end.getY() < start.getY())//第二点的Y 大于第一点  表明第二点在上方位置
                {
                    arrowView.addLine(
                            (int)(SCALE * (start.getX() -left + start.getWidth())),
                            (int)(SCALE * (start.getY() - top + end.getHeight() * 0.5f)),
                            (int)(SCALE * (end.getX() - left + end.getWidth() * 0.5f)),
                            (int)(SCALE * (end.getY() - top + end.getHeight())),color);
                }else
                {
                    arrowView.addLine(
                            (int)(SCALE * (start.getX() -left + start.getWidth())),
                            (int)(SCALE * (start.getY() - top + end.getHeight() * 0.5f)),
                            (int)(SCALE * (end.getX() - left + end.getWidth() * 0.5f)),
                            (int)(SCALE * (end.getY() - top)),color);
                }
            }else
            {
                arrowView.addLine(
                        (int)(SCALE * (start.getX() -left + start.getWidth())),
                        (int)(SCALE * (start.getY() - top + end.getHeight() * 0.5f)),
                        (int)(SCALE * (end.getX() - left)),
                        (int)(SCALE * (end.getY() - top + end.getHeight() * 0.5f)),color);
            }
        }


//        right *=1.5f;
//        bottom *=1.5f;

        if (right < (int)TDevice.getScreenWidth())right = (int)TDevice.getScreenWidth();
        setPadding(PADDING, PADDING, PADDING, PADDING);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(right + PADDING * 2,bottom + PADDING * 2);
        setLayoutParams(params);

        FrameLayout.LayoutParams arrowViewParams = new FrameLayout.LayoutParams(right ,bottom);
        addView(arrowView, arrowViewParams);
//        arrowView.setBackgroundResource(R.color.arrow_color);
    }
}
