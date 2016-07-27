package com.wlht.oa.ui.contact;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wlht.oa.R;
import com.wlht.oa.adapter.BaseRecyclerAdapter;
import com.wlht.oa.adapter.Contact$Adapter;
import com.wlht.oa.adapter.ContactAdapter;
import com.wlht.oa.adapter.expandRecyclerviewadapter.StickyRecyclerHeadersDecoration;
import com.wlht.oa.base.BaseListFragment;
import com.wlht.oa.base.BaseTitleFragment;
import com.wlht.oa.base.BaseTitleListFragment;
import com.wlht.oa.bean.Contact;
import com.wlht.oa.pinyin.CharacterParser;
import com.wlht.oa.pinyin.PinyinComparator2;
import com.wlht.oa.util.TDevice;
import com.wlht.oa.viewholder.CategoryVH;
import com.wlht.oa.widget.DividerDecoration;
import com.wlht.oa.widget.SideBar;
import com.wlht.oa.widget.TouchableRecyclerView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by hr on 16/6/12.
 */
public class ContactTestFragment extends BaseTitleListFragment<Contact> {

    private SideBar mSideBar;
//    private TouchableRecyclerView mRecyclerView;
    private CharacterParser characterParser;
//    private Contact$Adapter mAdapter;
    protected ArrayList<Contact> mDatas = new ArrayList<>();


    String[] imgs = new String[]{
            "http://img3.duitang.com/uploads/item/201604/21/20160421193915_mP2Lr.thumb.700_0.jpeg",
            "http://img3.duitang.com/uploads/item/201604/26/20160426001415_teGBZ.jpeg",
            "http://img3.duitang.com/uploads/item/201511/08/20151108200624_cQE2t.thumb.700_0.jpeg",
            "http://img3.duitang.com/uploads/item/201508/16/20150816132356_Gs4AU.jpeg",
            "http://img3.duitang.com/uploads/item/201509/19/20150919211347_BcmZA.jpeg",
            "http://www.bz55.com/uploads/allimg/121019/1-121019100228.jpg",
            "http://www.wed114.cn/jiehun/uploads/allimg/160426/39_160426110624_1.jpg",
            "http://www.wed114.cn/jiehun/uploads/allimg/160426/39_160426110624_2.jpg",

    };


    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflateView(R.layout.fragment_contract);
    }

    @Override
    public void initNavBar() {
        mTitleHeaderBar.getLeftViewContainer().setVisibility(View.GONE);
        mTitleHeaderBar.setTitle("通讯录");

        TextView settings = new TextView(getContext());
        settings.setTextColor(Color.WHITE);
        settings.setText("列表");
        settings.setGravity(Gravity.CENTER_VERTICAL | Gravity.END);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAdapter == null) return;
                TextView textView = (TextView) v;
                if ("列表".equals(textView.getText().toString())) {
                    textView.setText("部门");
                    Collections.sort(mDatas, new PinyinComparator2(true));
                    getAdapter().setSortType(ContactAdapter.SORT_LETTER);
                    setData(mDatas);
                } else {
                    textView.setText("列表");
                    Collections.sort(mDatas, new PinyinComparator2(false));
                    getAdapter().setSortType(ContactAdapter.SORT_DEPT);
                    setData(mDatas);
                }
            }
        });


        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int) TDevice.dpToPixel(48), ViewGroup.LayoutParams.MATCH_PARENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,RelativeLayout.TRUE);
        params.rightMargin = (int)TDevice.dpToPixel(16);
        mTitleHeaderBar.getRightViewContainer().addView(settings, params);


    }


    public Contact$Adapter getAdapter()
    {
        return (Contact$Adapter)mAdapter;
    }

    @Override
    public boolean isRefreshable() {
        return false;
    }

    @Override
    public void initAdapter() {
        mAdapter = new Contact$Adapter(getActivity());
    }

    public void initView(View view)
    {
        super.initView(view);
        characterParser = CharacterParser.getInstance();
        mSideBar = (SideBar) view.findViewById(R.id.contact_sidebar);
        mSideBar.setTextView((TextView) view.findViewById(R.id.contact_dialog));


//        int orientation = LinearLayoutManager.VERTICAL;
//        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), orientation, false);
//        mRecyclerView.setLayoutManager(layoutManager);
//        mRecyclerView.setAdapter(mAdapter);





    }

    @Override
    public void initRecyclerView(View view) {
        mRecyclerView = (TouchableRecyclerView)view.findViewById(R.id.contact_member);
        super.initRecyclerView(view);

        final StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(getAdapter());
        mRecyclerView.addItemDecoration(headersDecor);
        mRecyclerView.addItemDecoration(new DividerDecoration(getContext()));

        //   setTouchHelper();
        mAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                headersDecor.invalidateHeaders();
            }
        });
    }

    @Override
    public void initEvent(View view) {
        view.findViewById(R.id.searchBar).setOnClickListener(this);
        mAdapter.setOnItemClickListener((position, data) -> getContext().pushFragmentToBackStack(ContactDetailFragment.class, data));
        mSideBar.setOnTouchingLetterChangedListener(s -> {
            if (mAdapter != null) {
                getAdapter().closeOpenedSwipeLayoutWithAnim();
            }
            int position = getAdapter().getPositionForSection(s.charAt(0));
            if (position != -1) {
                mRecyclerView.scrollToPosition(position);
            }
        });
    }


    public void initData()
    {


        ArrayList<Contact> list = new ArrayList<>();
        list.add(new Contact("市场部","张一"));
        list.add(new Contact("市场部","李一"));
        list.add(new Contact("市场部","王一"));
        list.add(new Contact("市场部","麻一"));


        list.add(new Contact("运营部","王二"));
        list.add(new Contact("运营部","张二"));
        list.add(new Contact("运营部","李麻子"));

        list.add(new Contact("财务部","王四"));
        list.add(new Contact("财务部","张四"));
        list.add(new Contact("财务部","李四"));

        list.add(new Contact("售后部","王五"));
        list.add(new Contact("售后部", "张五"));
        list.add(new Contact("售后部", "李五"));


        for (int i = 0; i < list.size();i++)
        {
            Contact contact = list.get(i);


            contact.imageUrl = imgs[i%imgs.length];


            String sortString = characterParser.getSelling(contact.name).substring(0, 1).toUpperCase();
            if (sortString.matches("[A-Z]")) {
                contact.setSortLetters(sortString.toUpperCase());
            } else {
                contact.setSortLetters("#");
            }

            String deptString = characterParser.getSelling(contact.dept).toUpperCase();
            contact.setSortDept(deptString);
//            if (deptString.matches("[A-Z]")) {
//                contact.setSortDept(deptString.toUpperCase());
//            } else {
//                contact.setSortDept("#");
//            }

        }

        Collections.sort(list, new PinyinComparator2(false));
        mDatas.addAll(list);

        setParam("include", "creater")
        .setData(list);


//        setData(mDatas);
//        mAdapter.addDatas(list);
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId())
        {
            case R.id.searchBar:
                getContext().pushFragmentToBackStack(ContactSearchFragment.class,null);
                break;
        }
    }

    @Override
    public void onLeave() {
//        mRecyclerView.setVisibility(View.GONE);
    }

    @Override public boolean processBackPressed() {return false;}

}
