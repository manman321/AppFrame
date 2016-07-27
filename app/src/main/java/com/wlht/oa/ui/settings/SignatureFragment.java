package com.wlht.oa.ui.settings;/**
 * Created by hr on 16/7/18.
 */

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wlht.oa.R;
import com.wlht.oa.base.BaseTitleFragment;
import com.wlht.oa.widget.SignatureView;

public class SignatureFragment extends BaseTitleFragment {


    SignatureView mSignatureView = null;
    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_signature, null, false);
    }

    @Override
    public void initNavBar() {
        setHeaderTitle("签章管理");
        createRightImageView(R.drawable.gou).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = createViewBitmap(mSignatureView);

            }
        });
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        mSignatureView = (SignatureView)view.findViewById(R.id.signatureView);

    }

    @Override
    public void initEvent(View view) {

        view.findViewById(R.id.clearTv).setOnClickListener(this);




    }


    public Bitmap createViewBitmap(View v) {
        Bitmap bitmap = Bitmap.createBitmap(v.getWidth(), v.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        v.draw(canvas);
        return bitmap;
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.clearTv:
                mSignatureView.clear();
                break;
            default:
                break;
        }
    }

}
