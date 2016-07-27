package com.wlht.oa.adapter;/**
 * Created by hr on 16/7/1.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wlht.oa.R;
import com.wlht.oa.bean.Food;
import com.wlht.oa.util.TDevice;

public class FoodAdapter extends BaseRecyclerAdapter<Food, FoodAdapter.SimpleViewHolder> {

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        return new SimpleViewHolder(inflate(parent, R.layout.list_cell_meal_food));
    }

    @Override
    public void onBind(FoodAdapter.SimpleViewHolder viewHolder, int RealPosition, Food data) {
        super.onBind(viewHolder, RealPosition, data);

        Picasso.with(viewHolder.itemView.getContext()).load(data.imageUrl).into(viewHolder.imageView);
        viewHolder.gouIv.setVisibility(data.isSelected ? View.VISIBLE : View.GONE);
        viewHolder.nameTv.setText(data.name);


    }


    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public ImageView gouIv;
        public TextView nameTv;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            gouIv = (ImageView) itemView.findViewById(R.id.gouIv);
            nameTv = (TextView)itemView.findViewById(R.id.nameTv);
            itemView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,(int)TDevice.getScreenWidth() /3));
        }
    }
}
