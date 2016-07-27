package com.wlht.oa.ui.audit;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wlht.oa.R;
import com.wlht.oa.adapter.ApproveAdapter;
import com.wlht.oa.base.BaseTitleFragment;
import com.wlht.oa.bean.approve.Approve;
import com.wlht.oa.decoration.DividerItemMiniDecoration;

import java.util.ArrayList;

/**
 * Created by hr on 16/6/27.
 */
public class ApproveSelectedFragment extends BaseTitleFragment
{
    ApproveAdapter mAdapter;
    boolean isShowCheckBox = false;
    ArrayList<Approve> mSelectedDatas = new ArrayList<>();

    View selectedContainer;
    TextView selectedCntTv;
    TextView selectedOkTv;
    RecyclerView mRecyclerView;

    @Override
    public void initNavBar() {
        setHeaderTitle("已选审批");

        createRightImageView(R.drawable.gou).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().popTopFragment(mSelectedDatas);
            }
        });
    }

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_selected_approve, null, false);
    }



    @Override
    public void initView(View view) {
        super.initView(view);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        selectedContainer = view.findViewById(R.id.selectedContainer);
        selectedCntTv = (TextView)view.findViewById(R.id.selectedCntTv);
        selectedOkTv = (TextView)view.findViewById(R.id.selectedOkTv);
        initRecyclerView();
    }


    protected void initRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(new DividerItemMiniDecoration(getActivity(), DividerItemMiniDecoration.VERTICAL_LIST));
        mRecyclerView.setAdapter(mAdapter = new ApproveAdapter(false, true));

        selectedCntTv.setText(String.format("已选审批(%d个)", mSelectedDatas.size()));
        selectedCntTv.setTextColor(getResources().getColor(R.color.main_color));

        mAdapter.onDataChangedListener = new ApproveAdapter.OnDataChangedListener() {
            @Override
            public void onDataChanged(ArrayList<Approve> data) {
                int cnt = data.size();
                selectedCntTv.setText(String.format("已选审批(%d个)",cnt));
                selectedCntTv.setTag(String.format("%d",cnt));
                selectedCntTv.setTextColor(getResources().getColor(cnt == 0 ? R.color.black : R.color.main_color));
                selectedOkTv.setBackgroundResource(cnt == 0 ? R.color.gray : R.color.kaoqing_tv_color_5CA1E6);
            }
        };

//        mAdapter.setCheckedList(mSelectedDatas);
        mAdapter.addDatas(mSelectedDatas);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent(View view) {
//        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(int position, Object data) {
//                TLog.log(data.toString());
//            }
//        });
//        selectedOkTv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if ("0".equals(selectedCntTv.getTag()))return;
//                getContext().popTopFragment(mSelectedDatas);
//            }
//        });
    }



    @Override
    public void onEnter(Object data) {
        super.onEnter(data);
        if (data instanceof ArrayList)
        {
            mSelectedDatas.addAll((ArrayList<Approve>)data);
        }
    }
}
