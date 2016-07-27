package com.wlht.oa.adapter;/**
 * Created by hr on 16/7/6.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wlht.oa.R;
import com.wlht.oa.bean.Record;

public class CallAdapter extends BaseRecyclerAdapter<Record, CallAdapter.SimpleViewHolder> {

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        return new SimpleViewHolder(inflate(parent, R.layout.list_cell_call));
    }

    @Override
    public void onBind(CallAdapter.SimpleViewHolder viewHolder, int RealPosition, Record data) {
        super.onBind(viewHolder, RealPosition, data);

        viewHolder.nameTv.setText(data.name);


    }


   public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public ImageView iconIv;
        public TextView nameTv;
        public ImageView voiceIv;
        public TextView descriptionTv;
        public TextView timeTv;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            iconIv = (ImageView) itemView.findViewById(R.id.iconIv);
            nameTv = (TextView) itemView.findViewById(R.id.nameTv);
            voiceIv = (ImageView) itemView.findViewById(R.id.voiceIv);
            descriptionTv = (TextView) itemView.findViewById(R.id.descriptionTv);
            timeTv = (TextView) itemView.findViewById(R.id.timeTv);
        }
    }
}
