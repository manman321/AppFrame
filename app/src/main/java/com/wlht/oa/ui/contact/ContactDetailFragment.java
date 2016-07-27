package com.wlht.oa.ui.contact;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wlht.oa.R;
import com.wlht.oa.base.BaseTitleFragment;
import com.wlht.oa.bean.Contact;

/**
 * Created by hr on 16/6/20.
 */
public class ContactDetailFragment extends BaseTitleFragment
{

    protected ImageView head;
    protected TextView name,phone,dept,vno,duty,email;
    protected View call,msg;


    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflateView(R.layout.fragment_contact_detail);
    }

    @Override
    public void initNavBar() {
        mTitleHeaderBar.setTitle("联系人信息");
    }


    @Override
    public void initView(View view) {
        super.initView(view);

        head = (ImageView)view.findViewById(R.id.head);
        name = (TextView)view.findViewById(R.id.name);
        phone = (TextView)view.findViewById(R.id.phone);

        call = view.findViewById(R.id.call);
        msg  = view.findViewById(R.id.msg);

        dept = (TextView)view.findViewById(R.id.dept);
        vno  = (TextView)view.findViewById(R.id.vno);
        duty = (TextView)view.findViewById(R.id.duty);
        email = (TextView)view.findViewById(R.id.email);



    }

    @Override
    public void initEvent(View view) {
        call.setOnClickListener(this);
        msg.setOnClickListener(this);

    }




    protected Contact mContact;

    @Override
    public void onEnter(Object data) {
        super.onEnter(data);
        if (data instanceof Contact)
        {
            mContact = (Contact)data;
        }

    }

    @Override
    public void initData() {
        if (mContact == null)
        {
            getContext().showToast("初始化联系人信息错误");
            getContext().popTopFragment(null);
            return;
        }

        Picasso.with(getContext()).load(mContact.imageUrl).into(head);
        name.setText(mContact.name);
        phone.setText(mContact.phone);
        dept.setText(mContact.dept);
//        vno.setText(mContact.);





    }


}
