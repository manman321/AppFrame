package com.wlht.oa.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wlht.oa.R;
import com.wlht.oa.base.BaseTitleFragment;
import com.wlht.oa.ui.user.PasswordModifyFragment;

/**
 * Created by hr on 16/6/22.
 */
public class AccountSafeFragment extends BaseTitleFragment
{

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflateView(R.layout.fragment_settings_account_safe);
    }

    @Override
    public void initNavBar() {
        setHeaderTitle("账号与安全");
    }

    @Override
    public void initView(View view) {
        super.initView(view);

    }

    @Override
    public void initEvent(View view) {

        find(R.id.passwordModify).setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.passwordModify)
        {
            getContext().pushFragmentToBackStack(PasswordModifyFragment.class,null);
        }
    }
}
