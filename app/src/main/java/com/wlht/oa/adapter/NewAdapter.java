package com.wlht.oa.adapter;/**
 * Created by hr on 16/6/23.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wlht.oa.R;
import com.wlht.oa.bean.News;

public class NewAdapter extends BaseRecyclerAdapter<News, NewAdapter.SimpleViewHolder> {

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        return new SimpleViewHolder(inflate(parent, R.layout.list_cell_news));
    }

    @Override
    public void onBind(NewAdapter.SimpleViewHolder viewHolder, int RealPosition, News data) {
        super.onBind(viewHolder, RealPosition, data);

    }


    class SimpleViewHolder extends RecyclerView.ViewHolder {
        public ImageView iconIv;
        public TextView titleTv;
        public TextView contentTv;
        public TextView timeTv;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            iconIv = (ImageView) itemView.findViewById(R.id.iconIv);
            titleTv = (TextView) itemView.findViewById(R.id.titleTv);
            contentTv = (TextView) itemView.findViewById(R.id.contentTv);
            timeTv = (TextView) itemView.findViewById(R.id.timeTv);
        }
    }


}
