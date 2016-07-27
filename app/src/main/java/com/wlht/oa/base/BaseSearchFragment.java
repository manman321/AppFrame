package com.wlht.oa.base;/**
 * Created by hr on 16/6/27.
 */

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.wlht.oa.R;
import com.wlht.oa.base.BaseTitleFragment;

import in.srain.cube.views.ptr.PtrFrameLayout;

public abstract class BaseSearchFragment extends BaseFragment {
    public RecyclerView mRecyclerView;
    public PtrFrameLayout mPtrFrame;
    public TextView mCancelTv;
    public EditText mSearchEt;


    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, null, false);
    }

    @Override
    public void initView(View view) {
        mSearchEt = (EditText) view.findViewById(R.id.searchEt);
        mCancelTv = (TextView) view.findViewById(R.id.cancelTv);
        mPtrFrame = (PtrFrameLayout) view.findViewById(R.id.ptr_frame);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

    }

    @Override
    public void initEvent(View view) {

        mCancelTv.setOnClickListener(this);


        mSearchEt.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    search();
                }
                return false;
            }
        });

        mSearchEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH)
//                if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)
                {
                    search();
                }
                return true;
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.cancelTv:
                getContext().popTopFragment(null);
                break;
            default:
                break;
        }
    }


    protected abstract void search();

}
