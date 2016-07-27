/*
 * Copyright (C) 2014 Lucas Rocha
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wlht.oa.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wlht.oa.R;
import com.wlht.oa.bean.Banner;

public class BannerAdapter extends BaseRecyclerAdapter<Banner, BannerAdapter.SimpleViewHolder> {
    private final Context mContext;
    public BannerAdapter(Context context) {
        mContext = context;
    }

    @Override
    public void onBind(BannerAdapter.SimpleViewHolder holder, int RealPosition, Banner data) {
        super.onBind(holder, RealPosition, data);
//        SimpleViewHolder holder = (SimpleViewHolder)viewHolder;
        holder.title.setText(data.getTitle());
        Picasso.with(mContext).load(data.imageUrl).into(holder.imageView);

//        holder.title.setText(getItem(RealPosition).toString());
//        final View itemView = holder.itemView;
//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(mContext, "", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public void onBindViewHolder(BannerAdapter.SimpleViewHolder viewHolder,final int pos) {
        if(getItemViewType(pos) == TYPE_HEADER) return;
        final Banner data = getItem(pos);
        onBind(viewHolder, pos, data);

        if(mListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(pos, data);
                }
            });
        }
    }


    @Override
    public BannerAdapter.SimpleViewHolder onCreate(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.list_cell_item, parent, false);
        return new SimpleViewHolder(view);
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public final TextView title;
        public int mPosition;
        public ImageView imageView;
        public SimpleViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            imageView = (ImageView)view.findViewById(R.id.imageView);
        }
    }
}
