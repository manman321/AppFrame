package com.wlht.oa.base;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wlht.oa.R;
import com.wlht.oa.util.TDevice;

import in.srain.cube.mints.base.TitleHeaderBar;

/**
 * Created by hr on 16/4/1.
 */
public abstract class BaseTitleFragment extends BaseFragment implements View.OnTouchListener
{
    protected TitleHeaderBar mTitleHeaderBar;

    protected int getFrameLayoutId() {
        return R.layout.base_content_frame_with_title_header;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mInflater = inflater;
        ViewGroup rootView = (ViewGroup) inflater.inflate(getFrameLayoutId(), null);
        mTitleHeaderBar = (TitleHeaderBar) rootView.findViewById(R.id.content_frame_title_header);
        if (enableDefaultBack()) {
            mTitleHeaderBar.setLeftOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hideKeyboardForCurrentFocus();
                    onBackPressed();
                }
            });
        } else {
            mTitleHeaderBar.getLeftViewContainer().setVisibility(View.INVISIBLE);
        }
        initBaseView(rootView,inflater,container,savedInstanceState);
//        FrameLayout containerLayout = (FrameLayout) rootView.findViewById(R.id.contentContainer);
//        View view = createView(inflater, rootView, savedInstanceState);
//        view.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
//        containerLayout.addView(view);
//        initView(view);
        return rootView;
    }


    protected void setHeaderTitle(int id) {
        mTitleHeaderBar.getTitleTextView().setText(id);
    }


    protected void setHeaderTitle(String title) {
        mTitleHeaderBar.getTitleTextView().setText(title);
    }

    public TitleHeaderBar getTitleHeaderBar() {
        return mTitleHeaderBar;
    }


    public abstract void initNavBar();

    public void initView(View view)
    {
        mTitleHeaderBar.setBackgroundResource(R.color.main_color);
//        mTitleHeaderBar.getLeftViewContainer().setVisibility(View.GONE);
        mTitleHeaderBar.getTitleTextView().setTextColor(Color.WHITE);
        initNavBar();
    }

    public TextView createRightBtn()
    {
        TextView  saveBtn = new TextView(getContext());
        saveBtn.setTextColor(Color.WHITE);
        saveBtn.setGravity(Gravity.CENTER_VERTICAL | Gravity.END);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int) TDevice.dpToPixel(48), ViewGroup.LayoutParams.MATCH_PARENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        params.rightMargin = (int)TDevice.dpToPixel(16);
        mTitleHeaderBar.getRightViewContainer().removeAllViews();
        mTitleHeaderBar.getRightViewContainer().addView(saveBtn, params);
        return saveBtn;
    }

    public TextView createRightBtn(String text)
    {
        TextView  saveBtn = new TextView(getContext());
        saveBtn.setText(text);
        saveBtn.setTextColor(Color.WHITE);
        saveBtn.setGravity(Gravity.CENTER_VERTICAL | Gravity.END);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int) TDevice.dpToPixel(48), ViewGroup.LayoutParams.MATCH_PARENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        params.rightMargin = (int)TDevice.dpToPixel(16);
        mTitleHeaderBar.getRightViewContainer().removeAllViews();
        mTitleHeaderBar.getRightViewContainer().addView(saveBtn, params);
        return saveBtn;
    }

    public ImageView createRightImageView(int res)
    {
        ImageView  saveBtn = new ImageView(getContext());
        saveBtn.setImageResource(res);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int) TDevice.dpToPixel(26),(int) TDevice.dpToPixel(26));
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        params.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);

        params.rightMargin = (int)TDevice.dpToPixel(16);
        mTitleHeaderBar.getRightViewContainer().removeAllViews();
        mTitleHeaderBar.getRightViewContainer().addView(saveBtn, params);
        return saveBtn;
    }



}
