package com.wlht.oa.adapter;/**
 * Created by hr on 16/6/30.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amap.api.services.core.PoiItem;
import com.wlht.oa.R;

public class SigninNearbyAdapter extends BaseRecyclerAdapter<PoiItem, SigninNearbyAdapter.SimpleViewHolder> {

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        return new SimpleViewHolder(inflate(parent, R.layout.list_cell_signin_nearby));
    }

    @Override
    public void onBind(SigninNearbyAdapter.SimpleViewHolder viewHolder, int RealPosition, PoiItem data) {
        super.onBind(viewHolder, RealPosition, data);
        viewHolder.locationTv.setText(data.getSnippet());

    }


    class SimpleViewHolder extends RecyclerView.ViewHolder {
        public TextView locationTv;
        public TextView descriptionTv;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            locationTv = (TextView) itemView.findViewById(R.id.locationTv);
            descriptionTv = (TextView) itemView.findViewById(R.id.descriptionTv);
        }
    }
}
