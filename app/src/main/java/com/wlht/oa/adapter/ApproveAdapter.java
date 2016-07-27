package com.wlht.oa.adapter;/**
 * Created by hr on 16/6/27.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wlht.oa.R;
import com.wlht.oa.bean.approve.Approve;

import java.util.ArrayList;

public class ApproveAdapter extends BaseRecyclerAdapter<Approve, ApproveAdapter.SimpleViewHolder> {

    boolean isShowCheckBox = false;
    boolean isShowDel = false;

    public interface OnDataChangedListener
    {
        void onDataChanged(ArrayList<Approve> data);
    }

    public OnDataChangedListener onDataChangedListener;


    public ApproveAdapter(boolean showCheckBox,boolean isShowDel)
    {
        super();
        isShowCheckBox = showCheckBox;
        this.isShowDel = isShowDel;
    }
    private ArrayList<Approve> mCheckedList = new ArrayList<>();

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        SimpleViewHolder viewHolder = new SimpleViewHolder(inflate(parent, R.layout.list_cell_audit));
        viewHolder.checkBox.setVisibility(isShowCheckBox?View.VISIBLE:View.GONE);
        viewHolder.delIv.setVisibility(isShowDel?View.VISIBLE:View.GONE);
        return viewHolder;
    }

    @Override
    public void onBind(final ApproveAdapter.SimpleViewHolder viewHolder, int RealPosition,final Approve data) {
        super.onBind(viewHolder, RealPosition, data);
        Picasso.with(viewHolder.itemView.getContext()).load(data.logoUrl).into(viewHolder.photoIv);
        viewHolder.nameTv.setText(data.userName);
//        viewHolder.auditTv.setText(data.audit);
        viewHolder.typeTv.setText(data.typeName);
        viewHolder.timeTv.setText(data.time);
        viewHolder.checkBox.setChecked(mCheckedList.contains(data));



        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mCheckedList.add(data);
                } else {
                    mCheckedList.remove(data);
                }
                if (onDataChangedListener != null)
                    onDataChangedListener.onDataChanged(mCheckedList);
            }
        });

        viewHolder.delIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCheckedList.remove(data);
                delData(data);
                if (onDataChangedListener != null)
                    onDataChangedListener.onDataChanged(mCheckedList);
            }
        });
    }

    public ArrayList<Approve> getSelected()
    {
        return mCheckedList;
    }

    public void setCheckedList(ArrayList<Approve> list)
    {
        mCheckedList.addAll(list);
    }




    public class SimpleViewHolder extends RecyclerView.ViewHolder {
        public ImageView photoIv;
        public TextView nameTv;
        public TextView auditTv;
        public TextView typeTv;
        public TextView timeTv;
        public ImageView statusIv;
        public CheckBox checkBox;
        public ImageView delIv;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            checkBox = (CheckBox)itemView.findViewById(R.id.checkbox);
            photoIv = (ImageView) itemView.findViewById(R.id.photoIv);
            nameTv = (TextView) itemView.findViewById(R.id.nameTv);
            auditTv = (TextView) itemView.findViewById(R.id.auditTv);
            typeTv = (TextView) itemView.findViewById(R.id.typeTv);
            timeTv = (TextView) itemView.findViewById(R.id.timeTv);
            statusIv = (ImageView) itemView.findViewById(R.id.statusIv);
            delIv = (ImageView)itemView.findViewById(R.id.delIv);
        }
    }
}
