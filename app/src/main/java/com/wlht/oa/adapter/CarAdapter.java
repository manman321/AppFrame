package com.wlht.oa.adapter;/**
 * Created by hr on 16/7/1.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wlht.oa.R;
import com.wlht.oa.bean.Car;

public class CarAdapter extends BaseRecyclerAdapter<Car, CarAdapter.SimpleViewHolder> {

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        return new SimpleViewHolder(inflate(parent, R.layout.list_cell_car));
    }

    @Override
    public void onBind(CarAdapter.SimpleViewHolder viewHolder, int RealPosition, Car data) {
        super.onBind(viewHolder, RealPosition, data);

        viewHolder.noTv.setText(data.no);
        viewHolder.brandTv.setText(data.brand);
        viewHolder.statusIv.setText(data.status);
        viewHolder.driverTv.setText(data.driver);
        viewHolder.phoneTv.setText(data.phone);
        viewHolder.deptTv.setText(data.dept);

    }


   public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public TextView noTv;
        public TextView brandTv;
        public TextView statusIv;
        public TextView driverTv;
        public TextView phoneTv;
        public TextView deptTv;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            noTv = (TextView) itemView.findViewById(R.id.noTv);
            brandTv = (TextView) itemView.findViewById(R.id.brandTv);
            statusIv = (TextView) itemView.findViewById(R.id.statusIv);
            driverTv = (TextView) itemView.findViewById(R.id.driverTv);
            phoneTv = (TextView) itemView.findViewById(R.id.phoneTv);
            deptTv = (TextView) itemView.findViewById(R.id.deptTv);
        }
    }
}
