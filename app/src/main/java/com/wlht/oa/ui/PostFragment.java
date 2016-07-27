package com.wlht.oa.ui;/**
 * Created by hr on 16/6/29.
 */

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wlht.oa.R;
import com.wlht.oa.adapter.PostAdapter;
import com.wlht.oa.base.BaseTitleFragment;
import com.wlht.oa.bean.Comment;
import com.wlht.oa.bean.Post;
import com.wlht.oa.decoration.DividerItemMiniDecoration;

import java.util.ArrayList;

public class PostFragment extends BaseTitleFragment {
    LinearLayout mCommentLL;
    TextView mCommentBtn;
    EditText mCommentEt;
    RecyclerView mRecyclerView;
    PtrFrameLayout mPtrFrame;
    PostAdapter mAdapter;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_post, null, false);
    }

    @Override
    public void initNavBar() {
        setHeaderTitle("意见栏");
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        mPtrFrame = (PtrFrameLayout) view.findViewById(R.id.ptr_frame);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mCommentLL = (LinearLayout) view.findViewById(R.id.commentLL);
        mCommentEt = (EditText) view.findViewById(R.id.commentEt);
        mCommentBtn = (TextView) view.findViewById(R.id.commentBtn);


        initRefresh();
        initRecyclerView();
    }


    protected void initRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(new DividerItemMiniDecoration(getActivity(), DividerItemMiniDecoration.VERTICAL_LIST));
        mRecyclerView.setAdapter(mAdapter = new PostAdapter(this));


        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 5) {
                    hideComment();
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }

    protected void initRefresh() {
        setDefaultHeader(mPtrFrame);
        mPtrFrame.setPtrHandler(new PtrDefaultHandler() {

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return super.checkCanDoRefresh(frame, mRecyclerView, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPtrFrame.refreshComplete();
                    }
                }, 500);
            }
        });
    }

    @Override
    public void initEvent(View view) {
        mCommentBtn.setOnClickListener(this);
    }

    @Override
    public void initData() {

        Post post = new Post();
        post.name = "张三";
        post.content = "巴拉巴拉";
        post.time = "2012-12-12";

        mAdapter.addData(post);

        Post post1 = new Post();
        post1.name = "李四";
        post1.content = "哇哈哈哈";
        post1.time = "2012-12-13";

        mAdapter.addData(post1);
    }




    protected Post mCurrentCommentPost = null;
    public void comment(Post post)
    {
        mCommentLL.setVisibility(View.VISIBLE);
        mCurrentCommentPost = post;
        mCommentEt.requestFocusFromTouch();
        mCommentEt.requestFocus();
        getContext().showKeyboardAtView(mCommentEt);




    }


    protected void hideComment()
    {
        mCommentEt.clearFocus();
        getContext().hideKeyboardForCurrentFocus();
        mCommentLL.setVisibility(View.GONE);
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.commentBtn:
                /**
                 *
                 */

                if (mCurrentCommentPost.comments == null)
                {
                    mCurrentCommentPost.comments = new ArrayList<Comment>();
                }
                Comment comment = new Comment();
                comment.name = "hha";
                comment.content = "wahahhahhah";
                mCurrentCommentPost.comments.add(comment);
                mAdapter.updateSingleData(mCurrentCommentPost);

                mCommentEt.setText("");

                hideComment();

                break;
            default:
                break;
        }
    }

}
