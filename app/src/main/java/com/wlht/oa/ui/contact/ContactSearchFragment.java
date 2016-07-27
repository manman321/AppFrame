package com.wlht.oa.ui.contact;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wlht.oa.adapter.BaseRecyclerAdapter;
import com.wlht.oa.adapter.Contact4Adapter;
import com.wlht.oa.adapter.expandRecyclerviewadapter.StickyRecyclerHeadersDecoration;
import com.wlht.oa.base.BaseSearchFragment;
import com.wlht.oa.bean.Contact;
import com.wlht.oa.pinyin.CharacterParser;
import com.wlht.oa.pinyin.PinyinComparator2;
import com.wlht.oa.util.TLog;
import com.wlht.oa.widget.DividerDecoration;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by hr on 16/6/27.
 */
public class ContactSearchFragment extends BaseSearchFragment
{
    protected Contact4Adapter mAdapter;
    private CharacterParser characterParser;
    protected ArrayList<Contact> mDatas = new ArrayList<>();


    @Override
    public void initView(View view) {
        super.initView(view);

        characterParser = CharacterParser.getInstance();

        int orientation = LinearLayoutManager.VERTICAL;
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), orientation, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new Contact4Adapter(getContext(),new ArrayList<Contact>());


        mRecyclerView.setAdapter(mAdapter);
        final StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(mAdapter);
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
        super.initEvent(view);

        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, Object data) {

                TLog.log(data.toString());
            }
        });

    }



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
    protected void search() {
        clearFocus();

        ArrayList<Contact> list = new ArrayList<>();
        list.add(new Contact("市场部", "张一"));


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
        mDatas.addAll(list);
        Collections.sort(list, new PinyinComparator2(false));
        mAdapter.addDatas(mDatas);
    }
}
