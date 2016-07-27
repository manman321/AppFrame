package com.wlht.oa.adapter;/**
 * Created by hr on 16/6/23.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wlht.oa.R;
import com.wlht.oa.annotation.Around;
import com.wlht.oa.bean.News;
import com.wlht.oa.util.StringUtils;
import com.wlht.oa.util.TLog;

public class NewsAdapter extends BaseRecyclerAdapter<News,NewsAdapter.SimpleViewHolder> {

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        return new SimpleViewHolder(inflate(parent, R.layout.list_cell_news));
    }

    @Override
    public void onBind(SimpleViewHolder viewHolder, int RealPosition, News data) {
        super.onBind(viewHolder, RealPosition, data);
        try {
            if (!StringUtils.isEmpty(data.imageUrl))
            {
                Picasso.with(viewHolder.itemView.getContext())
                        .load(data.imageUrl)
                        .error(R.drawable.load_img_error)
                        .placeholder(R.drawable.loading)
                        .into(viewHolder.iconIv);
            }

            viewHolder.contentTv.setText(data.content);
            viewHolder.titleTv.setText(data.title);
            viewHolder.timeTv.setText(data.time);
        }catch (Exception e){TLog.printStackTrace(e);}

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
