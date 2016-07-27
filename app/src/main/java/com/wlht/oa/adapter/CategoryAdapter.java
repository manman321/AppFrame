package com.wlht.oa.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.wlht.oa.R;
import com.wlht.oa.adapter.expandRecyclerviewadapter.StickyRecyclerHeadersAdapter;
import com.wlht.oa.bean.approve.Template;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerSwipeAdapter<CategoryAdapter.SimpleViewHolder>
        implements StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder> {


    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public SimpleViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }
    }

    private Context mContext;
    private ArrayList<Template> mDataset;
    protected BaseRecyclerAdapter.OnItemClickListener onItemClickListener;

    public CategoryAdapter(Context context)
    {
        this.mContext = context;
        mDataset = new ArrayList<>();
    }

    public CategoryAdapter(Context context, ArrayList<Template> objects) {
        this(context);
        this.mDataset.addAll(objects);
    }


    public void addDatas(ArrayList<Template> datas)
    {
        mDataset.clear();
        mDataset.addAll(datas);
        notifyDataSetChanged();
    }



    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cell_category, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SimpleViewHolder viewHolder, final int position) {
        Template data = mDataset.get(position);
        viewHolder.textView.setText(data.title);

        if(onItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(position, getItem(position));
                }
            });

        }

    }


    public Template getItem(int position)
    {
        return mDataset.get(position);
    }


    @Override
    public long getHeaderId(int position) {
        return getItem(position).categoryName.hashCode();
//        return sortType == SORT_STATUS?getItem(position).categoryName.hashCode() : getItem(position).categoryName;
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cell_contact_header, parent, false);
        return new RecyclerView.ViewHolder(view) {};
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        TextView textView = (TextView) holder.itemView;
        Template contact = getItem(position);
        textView.setText(contact.categoryName);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }

    public void setOnItemClickListener(BaseRecyclerAdapter.OnItemClickListener onItemClickListener)
    {
        this.onItemClickListener = onItemClickListener;
    }


}
