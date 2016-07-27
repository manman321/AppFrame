package com.wlht.oa.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.wlht.oa.R;
import com.wlht.oa.bean.Notice;

/**
 * Created by hr on 16/6/22.
 */
public class NoticeAdapter extends BaseRecyclerAdapter<Notice,NoticeAdapter.SimpleViewHolder> {

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        return new SimpleViewHolder(inflate(parent,R.layout.list_cell_notice));
    }

    @Override
    public void onBind(NoticeAdapter.SimpleViewHolder viewHolder, int RealPosition, Notice data) {
        super.onBind(viewHolder, RealPosition, data);
        SimpleViewHolder holder = viewHolder;
        holder.titleTv.setText(data.getTitle());
        holder.acceptTv.setText(data.getAccept());
        holder.contentTv.setText(data.getContent());
        holder.timeTv.setText(data.getTime());
        holder.unreadView.setVisibility(data.isRead?View.GONE:View.VISIBLE);
    }


    class SimpleViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTv;
        public TextView contentTv;
        public TextView acceptTv;
        public TextView timeTv;
        public TextView unreadView;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.titleTv);
            contentTv = (TextView) itemView.findViewById(R.id.contentTv);
            acceptTv = (TextView) itemView.findViewById(R.id.acceptTv);
            timeTv = (TextView) itemView.findViewById(R.id.timeTv);
            unreadView = (TextView) itemView.findViewById(R.id.unreadView);
        }
    }
}
