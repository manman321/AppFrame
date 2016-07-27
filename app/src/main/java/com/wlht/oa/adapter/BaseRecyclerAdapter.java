package com.wlht.oa.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public abstract class BaseRecyclerAdapter<T,VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;

    protected ArrayList<T> mDatas = new ArrayList<>();

    private View mHeaderView;


    public T getItem(int index)
    {
        return mDatas.get(index);
    }

    public int indexOf(T data)
    {
        return mDatas.indexOf(data);
    }


    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }


    public void clearHeaderView()
    {
        mHeaderView = null;
        notifyItemRemoved(0);
    }

    public View getHeaderView() {
        return mHeaderView;
    }

    public void addData(T data)
    {
        mDatas.add(data);
        notifyDataSetChanged();
    }

    public void delData(T data)
    {
        mDatas.remove(data);
        notifyDataSetChanged();
    }

    public void addDatas(ArrayList<T> datas) {
        if (datas == null || datas.size() == 0)return;
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    public ArrayList<T> getDatas()
    {
        return new ArrayList<>(mDatas);
    }


    public void clear()
    {
        mDatas.clear();
    }

    @Override
    public int getItemViewType(int position) {
        if(mHeaderView == null) return TYPE_NORMAL;
        if(position == 0) return TYPE_HEADER;
        return TYPE_NORMAL;
    }




    @Override
    public VH onCreateViewHolder(ViewGroup parent, final int viewType) {
        if(mHeaderView != null && viewType == TYPE_HEADER) return (VH)(new Holder(mHeaderView));
        return onCreate(parent, viewType);
    }


    @Override
    public void onBindViewHolder(VH viewHolder, int position) {
        if(getItemViewType(position) == TYPE_HEADER) return;
        final int pos = getRealPosition(viewHolder);
        final T data = mDatas.get(pos);
        onBind(viewHolder, pos, data);
//        if(mListener != null) {
//            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mListener.onItemClick(pos, data);
//                }
//            });
//        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if(manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == TYPE_HEADER
                            ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

    @Override
    public void onViewAttachedToWindow(VH holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if(lp != null
                && lp instanceof StaggeredGridLayoutManager.LayoutParams
                && holder.getLayoutPosition() == 0) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
            p.setFullSpan(true);
        }
    }

    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }

    @Override
    public int getItemCount() {
        return mHeaderView == null ? mDatas.size() : mDatas.size() + 1;
    }

    public abstract <VH extends RecyclerView.ViewHolder>VH onCreate(ViewGroup parent, final int viewType);

    public static View inflate(ViewGroup parent,int layout)
    {
        return LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
    }

    protected int res(){return 0;}

    public void onBind(VH viewHolder, final int RealPosition, final T data){
        if (mListener != null)
        {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null)
                    {
                        mListener.onItemClick(RealPosition,data);
                    }
                }
            });
        }
    }

    public class Holder extends RecyclerView.ViewHolder {
        public Holder(View itemView) {
            super(itemView);
        }
    }

    protected OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        mListener = listener;
    }

    public interface OnItemClickListener<T> {
        void onItemClick(int position, T data);
    }

    public static  <T extends View>T  find(View view,int res)
    {
        return (T)view.findViewById(res);
    }

}