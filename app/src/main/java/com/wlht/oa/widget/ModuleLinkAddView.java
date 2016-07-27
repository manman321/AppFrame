package com.wlht.oa.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.wlht.oa.R;
import com.wlht.oa.base.BaseActivity;
import com.wlht.oa.ui.audit.ApproveFragment;

/**
 * Created by hr on 16/7/15.
 */
public class ModuleLinkAddView extends FrameLayout implements View.OnClickListener{
    ViewPager mMenuViewpage;
    ImageView mApproveIv;
    RelativeLayout mApproveRtl;
    ImageView mTaskIv;
    RelativeLayout mTaskRtl;
    LinearLayout mLlyMenu;
    ImageView mImageViewChooseLink;
    ImageView mImageViewChooseVoice;
    ImageView mImageViewChoosePic;
    ImageView mImageViewChooseCamera;

    public ModuleLinkAddView(Context context) {
        super(context);
        init();
    }

    public ModuleLinkAddView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ModuleLinkAddView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    protected void init()
    {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.modulelink_layout_bottom,null,false);
        addView(view);
        initView(view);
        initEvent();
    }


    public void initView(View view) {

        mMenuViewpage = (ViewPager) view.findViewById(R.id.menu_viewpage);
        mApproveIv = (ImageView) view.findViewById(R.id.approveIv);
        mApproveRtl = (RelativeLayout) view.findViewById(R.id.approveRtl);
        mTaskIv = (ImageView) view.findViewById(R.id.taskIv);
        mTaskRtl = (RelativeLayout) view.findViewById(R.id.taskRtl);
        mLlyMenu = (LinearLayout) view.findViewById(R.id.lly_menu);
        mImageViewChooseLink = (ImageView) view.findViewById(R.id.imageView_choose_link);
        mImageViewChooseVoice = (ImageView) view.findViewById(R.id.imageView_choose_voice);
        mImageViewChoosePic = (ImageView) view.findViewById(R.id.imageView_choose_pic);
        mImageViewChooseCamera = (ImageView) view.findViewById(R.id.imageView_choose_camera);
    }

    public void initEvent()
    {
        mImageViewChooseCamera.setOnClickListener(this);
        mImageViewChoosePic.setOnClickListener(this);
        mImageViewChooseVoice.setOnClickListener(this);
        mImageViewChooseLink.setOnClickListener(this);
        mTaskRtl.setOnClickListener(this);
        mApproveRtl.setOnClickListener(this);
    }


    public void hide()
    {
        mImageViewChooseLink.setSelected(false);
        mLlyMenu.setVisibility(GONE);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.imageView_choose_camera:

                break;
            case R.id.imageView_choose_pic:

                break;

            case R.id.imageView_choose_voice:

                break;

            case R.id.imageView_choose_link:
                v.setSelected(mLlyMenu.getVisibility() == GONE);
                mLlyMenu.setVisibility(mLlyMenu.getVisibility() == VISIBLE ? GONE :VISIBLE);
                break;

            case R.id.taskRtl:

                break;
            case R.id.approveRtl:
                ((BaseActivity)getContext()).pushFragmentToBackStackWithTag(ApproveFragment.class, true, String.format("%s_true",ApproveFragment.class.getSimpleName()));
                break;
        }
    }
}
