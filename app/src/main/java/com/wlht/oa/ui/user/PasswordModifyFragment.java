package com.wlht.oa.ui.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.wlht.oa.R;
import com.wlht.oa.base.BaseTitleFragment;

/**
 * Created by hr on 16/6/21.
 */
public class PasswordModifyFragment extends BaseTitleFragment
{
    protected EditText password,newPassword;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflateView(R.layout.fragment_password_modify);
    }


    @Override
    public void initNavBar() {
        setHeaderTitle("修改密码");
    }

    @Override
    public void initView(View view) {
        super.initView(view);

        password = (EditText)view.findViewById(R.id.password);
        newPassword = (EditText)view.findViewById(R.id.newPassword);


    }

    @Override
    public void initEvent(View view) {
        view.findViewById(R.id.commit).setOnClickListener(this);
    }

    @Override
    public void initData() {

    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.commit)
        {
            hideKeyboardForCurrentFocus();

            String password = this.password.getText().toString();
            String newPassword = this.newPassword.getText().toString();


            if (password.length() < 6 || password.length() > 20 || newPassword.length() < 6 || newPassword.length() > 20)
            {
                showToast("密码长度应为6~20位");
                return;
            }

            popTopFragment();


        }
    }
}
