package com.wlht.oa.ui.email;/**
 * Created by hr on 16/6/28.
 */

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.wlht.oa.R;
import com.wlht.oa.base.BaseTitleFragment;

public class EmailFragment extends BaseTitleFragment {
    LinearLayout mDraftEmail;
    LinearLayout mSendEmail;
    LinearLayout mReceiverEmail;
    LinearLayout mWriteEmail;


    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_email, null, false);
    }

    @Override
    public void initNavBar() {
        setHeaderTitle("邮件");
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        mWriteEmail = (LinearLayout) view.findViewById(R.id.writeEmail);
        mReceiverEmail = (LinearLayout) view.findViewById(R.id.receiverEmail);
        mSendEmail = (LinearLayout) view.findViewById(R.id.sendEmail);
        mDraftEmail = (LinearLayout) view.findViewById(R.id.draftEmail);

    }

    @Override
    public void initEvent(View view) {
        mDraftEmail.setOnClickListener(this);
        mSendEmail.setOnClickListener(this);
        mReceiverEmail.setOnClickListener(this);
        mWriteEmail.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            default:
                break;
        }
    }

}
