package com.wlht.oa.ui.approve;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wlht.oa.R;
import com.wlht.oa.adapter.BaseStickyAdapter;
import com.wlht.oa.adapter.CateAdapter;
import com.wlht.oa.adapter.expandRecyclerviewadapter.StickyRecyclerHeadersDecoration;
import com.wlht.oa.base.BaseListFragment;
import com.wlht.oa.bean.approve.Template;
import com.wlht.oa.decoration.DividerItemMiniDecoration;
import com.wlht.oa.ui.contact.ContactSearchFragment;
import com.wlht.oa.viewholder.CategoryVH;
import com.wlht.oa.viewholder.CategoryStickyVH;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by hr on 16/6/12.
 */
public class CateFragment extends BaseListFragment {

//    private RecyclerView mRecyclerView;
//    private CategoryAdapter mAdapter;
    protected ArrayList<Template> mDatas = new ArrayList<>();


    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflateView(R.layout.fragment_recyclerview);
    }


    @Override
    public boolean isRefreshable() {
        return false;
    }

    @Override
    public void initAdapter() {
        mAdapter = new CateAdapter(getContext());
    }

    @Override
    public void initRecyclerView(View view) {
        super.initRecyclerView(view);
        final StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration((BaseStickyAdapter)mAdapter);
        mRecyclerView.addItemDecoration(headersDecor);
        mRecyclerView.addItemDecoration(new DividerItemMiniDecoration(getContext(),DividerItemMiniDecoration.VERTICAL_LIST));
//        mRecyclerView.addItemDecoration(new DividerDecoration(getContext()));

        //   setTouchHelper();
        mAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                headersDecor.invalidateHeaders();
            }
        });

    }


    //    @Override
//    public void initNavBar() {
//        mTitleHeaderBar.setTitle("审批类型");
//    }




//    public void initView(View view)
//    {
//        super.initView(view);
//        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
//        mAdapter = new CategoryAdapter(getActivity(),new ArrayList<Template>());
//
//
//        int orientation = LinearLayoutManager.VERTICAL;
//        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), orientation, false);
//        mRecyclerView.setLayoutManager(layoutManager);
//
//        mRecyclerView.setAdapter(mAdapter);
//        final StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(mAdapter);
//        mRecyclerView.addItemDecoration(headersDecor);
//        mRecyclerView.addItemDecoration(new DividerItemMiniDecoration(getContext(),DividerItemMiniDecoration.VERTICAL_LIST));
////        mRecyclerView.addItemDecoration(new DividerDecoration(getContext()));
//
//        //   setTouchHelper();
//        mAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
//            @Override
//            public void onChanged() {
//                headersDecor.invalidateHeaders();
//            }
//        });
//
//
//
//
//
//    }

    @Override
    public void initEvent(View view) {
        mAdapter.setOnItemClickListener((position, data) -> getContext().pushFragmentToBackStack(ApproveCreateFragment.class,null));
    }


    public void initData()
    {


        ArrayList<Template> list = new ArrayList<>();
        String[] values = new String[]{"请假单","用车单","差旅费"};
        for (int i = 0; i < values.length;i++)
        {
            Template tpl = new Template();
            tpl.title = values[i];
            tpl.categoryName = "日常类";
            list.add(tpl);
        }

        String[] values2 = new String[]{"出差申请","加班申请","调休申请"};
        for (int i = 0; i < values2.length;i++)
        {
            Template tpl = new Template();
            tpl.title = values2[i];
            tpl.categoryName = "财务类";
            list.add(tpl);
        }


//        HashMap<String,ArrayList<Template>> map = new HashMap<>();
//        for (int i = 0; i < list.size();i++)
//        {
//            Template contact = list.get(i);
//            if (!map.containsKey(contact.categoryName))
//            {
//                map.put(contact.categoryName,new ArrayList<Template>());
//            }
//            map.get(contact.categoryName).add(contact);
//        }


//        for (Map.Entry<String,ArrayList<Template>> entry : map.entrySet())
//        {
//            for (Template contact:entry.getValue())
//            {
//                contact.categoryName = String.format("%s(%d)",contact.categoryName,entry.getValue().size());
//            }
//        }

        mDatas.addAll(list);
        Collections.sort(list, new Comparator<Template>() {
            @Override
            public int compare(Template o1, Template o2) {
                return o1.categoryName.compareTo(o2.categoryName);
            }
        });

        ((BaseStickyAdapter)mAdapter).setStickyView(CategoryStickyVH.class);

        setView(CategoryVH.class)
                .setParam("include", "creater")
                .setData(list);
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

    }

}
