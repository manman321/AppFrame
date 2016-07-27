package com.wlht.oa.ui.settings;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wlht.oa.AppContext;
import com.wlht.oa.KV;
import com.wlht.oa.R;
import com.wlht.oa.adapter.BaseRecyclerAdapter;
import com.wlht.oa.adapter.SettingAdapter;
import com.wlht.oa.base.BaseTitleFragment;
import com.wlht.oa.decoration.DividerItemMiniDecoration;
import com.wlht.oa.ui.user.LoginFragment;

import java.util.ArrayList;

/**
 * Created by hr on 16/6/16.
 */
public class SettingFragment extends BaseTitleFragment
{
    private SettingAdapter mAdapter;
    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflateView(R.layout.fragment_settings);
    }




    @Override
    public void initNavBar() {
        mTitleHeaderBar.setTitle("设置");
    }


    public void initView(View view)
    {
        super.initView(view);
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new DividerItemMiniDecoration(getActivity(), DividerItemMiniDecoration.VERTICAL_LIST));
        recyclerView.setAdapter(mAdapter = new SettingAdapter());
    }

    @Override
    public void initEvent(View view) {
        find(R.id.logout).setOnClickListener(this);

        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, Object data) {
                switch (position) {
                    case 0:
                        getContext().pushFragmentToBackStack(AccountSafeFragment.class, null);
                        break;
                    case 3:
                        getContext().pushFragmentToBackStack(AboutFragment.class, null);
                        break;
                    case 4:
                        getContext().pushFragmentToBackStack(SignatureFragment.class,null);
                        break;
                }
            }
        });
    }

    public void initData()
    {

        ArrayList<KV<String,String>> list = new ArrayList<>();
        list.add(new KV<String, String>("账号与安全",""));
        list.add(new KV<String, String>("提醒设置",""));
        list.add(new KV<String, String>("客户热线", "15351235044"));
        list.add(new KV<String, String>("关于", ""));
        list.add(new KV<String, String>("签章",""));
        list.add(new KV<String, String>("检查更新", "1.0"));
        mAdapter.clear();
        mAdapter.addDatas(list);

    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.logout)
        {
//            popTopFragment();
            AppContext.getInstance().cleanLoginInfo();
            getContext().pushFragmentToBackStack(LoginFragment.class,null);
        }
    }
}
