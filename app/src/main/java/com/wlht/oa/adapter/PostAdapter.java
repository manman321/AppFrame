package com.wlht.oa.adapter;/**
 * Created by hr on 16/6/29.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wlht.oa.AppContext;
import com.wlht.oa.R;
import com.wlht.oa.bean.Comment;
import com.wlht.oa.bean.Post;
import com.wlht.oa.bean.Praise;
import com.wlht.oa.ui.PostFragment;
import com.wlht.oa.util.StringUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class PostAdapter extends BaseRecyclerAdapter<Post, PostAdapter.SimpleViewHolder> {

    WeakReference<PostFragment> weakReference;
    public PostAdapter(PostFragment postFragment)
    {
        this.weakReference = new WeakReference<PostFragment>(postFragment);
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        return new SimpleViewHolder(inflate(parent, R.layout.list_cell_post));
    }

    @Override
    public void onBind(PostAdapter.SimpleViewHolder viewHolder, int RealPosition, Post data) {
        super.onBind(viewHolder, RealPosition, data);
        viewHolder.fillData(data);
    }


    public void updateSingleData(Post post)
    {
        notifyItemChanged(mDatas.indexOf(post));
    }

    public class SimpleViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout photoIv;
        public TextView nameTv;
        public TextView descriptionTv;
        public TextView timeTv;
        public LinearLayout praiseLL;
        public ImageView praiseIv;
        public TextView praiseTv;
        public LinearLayout commentLL;
        public LinearLayout praiseContainerLL;
        public TextView praiseNamesTv;
        public TextView commentTv;

        public boolean isPraiseContainsMe = false;

        protected Post data;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            photoIv = (LinearLayout) itemView.findViewById(R.id.photoIv);
            nameTv = (TextView) itemView.findViewById(R.id.nameTv);
            descriptionTv = (TextView) itemView.findViewById(R.id.descriptionTv);
            timeTv = (TextView) itemView.findViewById(R.id.timeTv);
            praiseLL = (LinearLayout) itemView.findViewById(R.id.praiseLL);
            praiseIv = (ImageView) itemView.findViewById(R.id.praiseIv);
            praiseTv = (TextView) itemView.findViewById(R.id.praiseTv);
            commentLL = (LinearLayout) itemView.findViewById(R.id.commentLL);
            praiseContainerLL = (LinearLayout) itemView.findViewById(R.id.praiseContainerLL);
            praiseNamesTv = (TextView) itemView.findViewById(R.id.praiseNamesTv);
            commentTv = (TextView) itemView.findViewById(R.id.commentTv);


            praiseLL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (data == null)return;
                    //取消点赞
                    if (isPraiseContainsMe)
                    {
                        if (data.praises == null)
                        {
                            data.praises = new ArrayList<Praise>();
                        }

                        Praise remove = null;
                        for (Praise praise : data.praises)
                        {
                           if(AppContext.getInstance().getLoginUid().equals(praise.uid))
                           {
                               remove = praise;
                               break;
                           }
                        }
                        if (remove != null)data.praises.remove(remove);

                    }else //点赞
                    {
                        if (data.praises == null)
                        {
                            data.praises = new ArrayList<Praise>();
                        }

                        Praise praise = new Praise();
                        praise.uid = AppContext.getInstance().getLoginUid();
                        praise.name = "haha";
                        data.praises.add(praise);

                        fillData(data);
                    }
                }
            });

            commentLL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //弹出评论对话框
                    if (weakReference.get() != null && data != null)
                    {
                        weakReference.get().comment(data);
                    }
//
//                    if (data.comments == null)
//                    {
//                        data.comments = new ArrayList<Comment>();
//                    }
//
//                    Comment comment = new Comment();
//                    comment.name = "hha";
//                    comment.content = "wahahhahhah";
//                    data.comments.add(comment);
//                    fillData(data);
                }
            });

        }


        public void fillData(Post data)
        {
            if (data == null)return;
            this.data = data;
            nameTv.setText(data.name);
            descriptionTv.setText(data.content);
            timeTv.setText(data.time);

            //显示赞的内容
            if (data.praises == null || data.praises.size() == 0)
            {
                praiseContainerLL.setVisibility(View.GONE);
                isPraiseContainsMe = false;

            }else
            {
                praiseContainerLL.setVisibility(View.VISIBLE);
                StringBuffer buffer = new StringBuffer();
                for (Praise value : data.praises)
                {
//                    if (AppContext.getInstance().isLogin() && value.uid.equals(AppContext.getInstance().getLoginUid()))
//                        isPraiseContainsMe = true;

                    buffer.append(value.name).append(",");
                }
                praiseNamesTv.setText(buffer.substring(0,buffer.length()-1));
            }


            //改变点赞的按钮状态
            praiseIv.setImageResource(isPraiseContainsMe ? R.drawable.icon_praise_press : R.drawable.icon_praise_normal);
            praiseTv.setText(isPraiseContainsMe?"取消点赞":"点赞");


            if (data.comments == null || data.comments.size() == 0)
            {
                commentTv.setVisibility(View.GONE);
            }else
            {
                commentTv.setVisibility(View.VISIBLE);

                StringBuffer buffer = new StringBuffer();
                for (Comment value : data.comments)
                {
                    buffer.append(value.name);
                    if (!StringUtils.isEmpty(value.toName))
                    {
                        buffer.append("回复");
                        buffer.append(value.toName);
                    }
                    buffer.append(":").append(value.content).append("\n");
                }
                commentTv.setText(buffer.substring(0, buffer.length() - 1));
            }
        }


    }
}
