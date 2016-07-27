package com.wlht.oa.ui.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.wlht.oa.AppConfig;
import com.wlht.oa.AppContext;
import com.wlht.oa.AppManager;
import com.wlht.oa.R;
import com.wlht.oa.base.BaseTitleFragment;
import com.wlht.oa.bean.User;
import com.wlht.oa.ui.MainActivity;

import java.lang.ref.WeakReference;

/**
 * Created by hr on 16/6/21.
 */
public class LoginFragment extends BaseTitleFragment
{

    protected EditText accountEt,passwordEt;
    protected boolean mCloseWarned = false;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflateView(R.layout.fragment_login);
    }

    @Override
    public void initNavBar() {
        mTitleHeaderBar.getLeftViewContainer().setVisibility(View.GONE);
        setHeaderTitle("登录");
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        accountEt = find(R.id.account);
        passwordEt = find(R.id.password);
    }

    @Override
    public void initEvent(View view) {
        find(R.id.login).setOnClickListener(this);
    }

    @Override
    public void initData() {

    }



    /**
     * 当没有登录的时候,默认加载这个登录页面,
     * 回退栈中只有一个元素,采用默认规则即可
     * @return
     */
    @Override
    public boolean processBackPressed() {
        if (mCloseWarned)
        {
            AppManager.getAppManager().finishAllActivity();
        }else
        {
            mCloseWarned = true;
            Toast.makeText(getContext(), getResources().getString(in.srain.cube.R.string.cube_mints_exit_tip), Toast.LENGTH_SHORT).show();
            new Thread(new MyRun(this)).start();
        }
        return true;
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.login)
        {
            /*
             * 当登录成功之后,将栈顶的元素弹出,
             * 并且将首页的一些信息显示出来。
             */
            hideKeyboardForCurrentFocus();
            AppContext.getInstance().saveUserInfo(new User());
            ((MainActivity)getContext()).showView();
            getContext().popExceptTop();
            popTopFragment(null);
        }
    }

    private static class MyRun implements Runnable
    {
        WeakReference<LoginFragment> weakReference;
        public MyRun(LoginFragment cubeFragmentActivity)
        {
            weakReference = new WeakReference<LoginFragment>(cubeFragmentActivity);
        }
        @Override
        public void run() {

            try {
                Thread.sleep(600);
                if (weakReference.get() != null)
                {
                    weakReference.get().mCloseWarned = false;
                }
            }catch (Exception e){}

        }
    }

}
