package com.wlht.oa.adapter;/**
 * Created by hr on 16/6/23.
 */

import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wlht.oa.KV;
import com.wlht.oa.R;

public class SettingAdapter extends BaseRecyclerAdapter<KV<String, String>, SettingAdapter.SimpleViewHolder> {

    private int gravity = Gravity.CENTER_VERTICAL|Gravity.END;

    public SettingAdapter()
    {}

    public SettingAdapter(int gravity)
    {
        this.gravity = gravity;
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        return new SimpleViewHolder(inflate(parent, R.layout.list_cell_setting));
    }

    @Override
    public void onBind(SettingAdapter.SimpleViewHolder viewHolder, int RealPosition, KV<String, String> data) {
        super.onBind(viewHolder, RealPosition, data);
        viewHolder.name.setText(data.key);
        viewHolder.value.setText(data.value);
    }


    class SimpleViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView value;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            value = (TextView) itemView.findViewById(R.id.value);
            value.setGravity(gravity);
        }
    }
}
