package com.wlht.oa.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wlht.oa.R;

/**
 * Created by hr on 16/7/18.
 */
public class LauncherActivity extends Activity
{
    ImageView mBackgroundView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBackgroundView = new ImageView(this);
        mBackgroundView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mBackgroundView.setScaleType(ImageView.ScaleType.FIT_XY);
        setContentView(mBackgroundView);
        mBackgroundView.setImageResource(R.drawable.launcher);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(LauncherActivity.this,MainActivity.class));
                finish();
            }
        },2000);
    }

}
