package com.wlht.oa.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wlht.oa.R;


/**
 * Created by hr on 16/4/18.
 */
public class ViewPageTabItem extends FrameLayout
{

    private ImageView icon;
    private TextView  title;

    public ViewPageTabItem(Context context) {
        super(context);
        init();
    }

    public ViewPageTabItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ViewPageTabItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        setBackgroundColor(Color.TRANSPARENT);
        View view = inflate(getContext(), R.layout.base_viewpage_fragment_tab_item2,null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.gravity = Gravity.CENTER;
        addView(view,params);

        icon = (ImageView)view.findViewById(R.id.icon_iv);
        title = (TextView)view.findViewById(R.id.tab_title);

    }

    private int mSelectedIconRes = -1;
    private int mNormalIconRes = -1;
    private int mSelectedTxtColor = -1;
    private int mNormalTxtColor = -1;

    public void setResource(int selectedIconRes,int normalIconRes,int selectedTxtColor,int normalTxtColor)
    {
        mSelectedIconRes = selectedIconRes;
        mNormalIconRes = normalIconRes;
        mNormalTxtColor = normalTxtColor;
        mSelectedTxtColor = selectedTxtColor;

        if (mNormalIconRes != -1)
        {
            icon.setImageResource(mNormalIconRes);
        }else {
            icon.setVisibility(View.GONE);
        }
        title.setTextColor(mNormalTxtColor);

        setSelected(false);
    }

    public void setText(String text)
    {
        title.setText(text);
    }

    public void setSelected(boolean selected)
    {
        if (mSelectedIconRes != -1)
        {
            icon.setVisibility(View.VISIBLE);
            icon.setImageResource(selected ? mSelectedIconRes:mNormalIconRes);
        }
        title.setTextColor(selected ? mSelectedTxtColor:mNormalTxtColor);



    }


}
