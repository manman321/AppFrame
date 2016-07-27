package com.wlht.oa.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wlht.oa.R;
import com.wlht.oa.util.TDevice;

import java.util.ArrayList;

/**
 * Created by hr on 16/7/12.
 */
public class InfoViewRow extends FrameLayout {

    protected int width;
    protected int height;

    public InfoViewRow(Context context) {
        super(context);
        init();
    }

    public InfoViewRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public InfoViewRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init()
    {
        width =(int)(TDevice.getScreenWidth()*0.25f);
        height=(int)TDevice.dpToPixel(44);
    }






    protected View horizontalLine(int width)
    {
        View line = new View(getContext());
        line.setBackgroundColor(Color.BLACK);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(width,2);
        params.topMargin = height;
        line.setLayoutParams(params);
        return line;
    }



    protected TextView createTextView()
    {
        TextView textView = new TextView(getContext());
        textView.setTextSize(13);
        textView.setTextColor(Color.BLACK);
        textView.setGravity(Gravity.CENTER);
        textView.setSingleLine(true);
        textView.setLayoutParams(new LinearLayout.LayoutParams(width,height));
        return textView;
    }

    protected View verticalLine()
    {
        View line = new View(getContext());
        line.setBackgroundColor(Color.BLACK);
        line.setLayoutParams(new LinearLayout.LayoutParams(2,height));
        return line;
    }

    protected ImageView deleteImageView()
    {
        ImageView imageView = new ImageView(getContext());
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams((int)TDevice.dpToPixel(25),(int)(TDevice.dpToPixel(25)));
        params.leftMargin = (int)TDevice.getScreenWidth() - (int)TDevice.dpToPixel(30);
        params.topMargin = (int)TDevice.dpToPixel(10);
        params.rightMargin = (int)TDevice.dpToPixel(5);
        imageView.setLayoutParams(params);
        imageView.setImageResource(R.drawable.renwu_choiceicn_del);
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Click",Toast.LENGTH_LONG).show();
            }
        });

        return imageView;
    }

    protected ArrayList<TextView> mViews = new ArrayList<>();

    protected LinearLayout row(int column)
    {
        LinearLayout row = new LinearLayout(getContext());
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mViews.clear();
        for (int i = 0; i < column;i++)
        {
            TextView textView =  createTextView();
            row.addView(textView);
            mViews.add(textView);
            if (i != column - 1)
            {
                row.addView(verticalLine());
            }
        }
        return row;
    }


    public ArrayList<TextView> getViews()
    {
        return mViews;
    }



    public void initWithColumn(int column)
    {
        removeAllViews();
        LinearLayout row = row(column);
        View line =  horizontalLine(column * (width + 2) - 2);
        delete = deleteImageView();
        delete.setVisibility(GONE);
        addView(row);
        addView(line);
        addView(delete);

        for (int i = 0; i < column;i++)
        {
            TextView textView = mViews.get(i);
            textView.setText(String.format("%d", i + 1));
        }
    }

    public void toggleDeleteVisibility()
    {
        if (delete != null)delete.setVisibility(delete.getVisibility() == GONE? VISIBLE:GONE);
    }

    public void setOnDeleteClick(View.OnClickListener onClickListener)
    {
        if (delete != null)delete.setOnClickListener(onClickListener);
    }

    protected View delete = null;
    public void scrollX(int x)
    {
        if (delete == null)return;

        FrameLayout.LayoutParams params =  (FrameLayout.LayoutParams)delete.getLayoutParams();

        int leftMargin = (int)TDevice.getScreenWidth() - (int)TDevice.dpToPixel(30);
        params.leftMargin = leftMargin + x;
        params.topMargin = (int)TDevice.dpToPixel(10);

        delete.setLayoutParams(params);


    }




}
