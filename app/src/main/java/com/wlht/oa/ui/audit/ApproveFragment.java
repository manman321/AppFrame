package com.wlht.oa.ui.audit;/**
 * Created by hr on 16/6/27.
 */

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.otto.Subscribe;
import com.wlht.oa.R;
import com.wlht.oa.adapter.CommonViewPagerFragmentAdapter;
import com.wlht.oa.adapter.ViewPageFragmentAdapter;
import com.wlht.oa.adapter.ViewPageInfo;
import com.wlht.oa.base.BaseTitleFragment;
import com.wlht.oa.bean.approve.Approve;
import com.wlht.oa.event.Event;
import com.wlht.oa.ui.approve.CategoryFragment;
import com.wlht.oa.widget.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.HashMap;

public class ApproveFragment extends BaseTitleFragment{
    RelativeLayout mSearchBar;
    ViewPager mViewPager;
    PagerSlidingTabStrip mTabStrip;
    CommonViewPagerFragmentAdapter mTabsAdapter;

    View selectedContainer;
    TextView selectedCntTv;
    TextView selectedOkTv;
    boolean isShowCheckBox = false;




    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_audit, null, false);
    }

    @Override
    public void initNavBar() {
        setHeaderTitle("我的审批");

        createRightImageView(R.drawable.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStack(CategoryFragment.class, null);
            }
        });

    }

    @Override
    public void initView(View view) {
        super.initView(view);
        mSearchBar = (RelativeLayout) view.findViewById(R.id.searchBar);
        mTabStrip = (PagerSlidingTabStrip) view.findViewById(R.id.pager_tabstrip);
        mViewPager = (ViewPager) view.findViewById(R.id.pager);
        mTabsAdapter = new CommonViewPagerFragmentAdapter(getChildFragmentManager(),
                mTabStrip, mViewPager);
        selectedContainer = view.findViewById(R.id.selectedContainer);
        selectedCntTv = (TextView)view.findViewById(R.id.selectedCntTv);
        selectedOkTv = (TextView)view.findViewById(R.id.selectedOkTv);
        selectedContainer.setVisibility(isShowCheckBox ? View.VISIBLE : View.GONE);
        mViewPager.setOffscreenPageLimit(3);
        onSetupTabAdapter(mTabsAdapter);
    }


    protected void onSetupTabAdapter(ViewPageFragmentAdapter adapter) {
//        String[] title = getResources().getStringArray(
//                R.array.news_viewpage_arrays);

        int color = getResources().getColor(R.color.default_text);
        int selectedColor = getResources().getColor(R.color.main_color);
        adapter.addTab(new ViewPageInfo(
                "我发起的",
                "xp",
                ApproveSendFragment.class, getBundle(1),
                -1,
                -1,
                selectedColor,
                color));

        adapter.addTab(new ViewPageInfo(
                "我审核的",
                "ch",
                ApproveAuditFragment.class, getBundle(2),
                -1,
                -1,
                selectedColor,
                color));

        adapter.addTab(new ViewPageInfo(
                "通知我的",
                "pt",
                ApproveNoticeFragment.class, getBundle(3),
                -1,
                -1,
                selectedColor,
                color));

    }

    private Bundle getBundle(int newType) {
        Bundle bundle = new Bundle();
        bundle.putInt("BUNDLE_KEY_CATALOG", newType);
        bundle.putBoolean("isShowCheckBox",isShowCheckBox);
        return bundle;
    }

    @Override
    public void onEnter(Object data) {
        super.onEnter(data);
        if (data instanceof Boolean) {
            isShowCheckBox = true;
        }
    }

    @Override
    public void initEvent(View view) {
        mSearchBar.setOnClickListener(this);
        selectedCntTv.setOnClickListener(this);
        selectedOkTv.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    protected HashMap<Class,ArrayList<Approve>> mSelectedDatas = new HashMap<>();
    @Subscribe public void onCheckedChanged(Event<ArrayList<Approve>> event)
    {
        mSelectedDatas.put(event.from, event.data);
        int cnt = 0;
        for (ArrayList<Approve> list : mSelectedDatas.values())
        {
            cnt += list.size();
        }
        selectedCntTv.setText(String.format("已选审批(%d个)",cnt));
        selectedCntTv.setTag(String.format("%d",cnt));
        selectedCntTv.setTextColor(getResources().getColor(cnt == 0 ? R.color.black : R.color.main_color));
        selectedOkTv.setBackgroundResource(cnt == 0?R.color.gray:R.color.kaoqing_tv_color_5CA1E6);
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        ArrayList<Approve> list = new ArrayList<>();
        for (ArrayList<Approve> li : mSelectedDatas.values())
        {
            list.addAll(li);
        }
        switch (v.getId()) {
            case R.id.selectedCntTv:
            {
                if ("0".equals(selectedCntTv.getTag()))return;
                getContext().pushFragmentToBackStack(ApproveSelectedFragment.class,list);
            }
                break;

            case R.id.selectedOkTv:
            {
                if ("0".equals(selectedCntTv.getTag()))return;
                getContext().popTopFragment(list);
            }
                break;

            case R.id.searchBar:
            {
                getContext().pushFragmentToBackStack(ApproveSearchFragment.class,list);
            }
                break;
            default:
                break;
        }
    }


}
