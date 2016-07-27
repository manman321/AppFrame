package com.wlht.oa.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.wlht.oa.R;
import com.wlht.oa.bean.Message;
import com.wlht.oa.transform.CircleTransform;
import com.wlht.oa.transform.RoundedTransformationBuilder;

/**
 * Created by hr on 16/6/16.
 */
public class MessageAdapter extends BaseRecyclerAdapter<Message,MessageAdapter.SimpleViewHolder> {



    CircleTransform circleTransform = new CircleTransform();
    protected Transformation roundedTransformation = new RoundedTransformationBuilder()
            .cornerRadiusDp(14)
            .oval(false)
            .build();
    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cell_message,parent,false);
        return new SimpleViewHolder(view);
    }


    @Override
    public void onBind(MessageAdapter.SimpleViewHolder viewHolder, int RealPosition, Message data) {
        super.onBind(viewHolder, RealPosition, data);
        SimpleViewHolder holder = viewHolder;

        holder.name.setText(data.title);
        holder.time.setText(data.time);
        holder.description.setText(String.format("%s", data.content));
        Picasso.with(viewHolder.imageView.getContext())
                .load(data.imageUrl)
                .transform(roundedTransformation)
                .into(holder.imageView);

    }




    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView name;
        public TextView description;
        public TextView time;
        public SimpleViewHolder(View view) {
            super(view);
            imageView = (ImageView)view.findViewById(R.id.imageView);
            name = (TextView) view.findViewById(R.id.name);
            description = (TextView)view.findViewById(R.id.description);
            time = (TextView)view.findViewById(R.id.time);

        }
    }



}
