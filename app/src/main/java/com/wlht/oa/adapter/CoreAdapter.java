package com.wlht.oa.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wlht.oa.Constants;
import com.wlht.oa.base.BaseViewHolder;
import com.wlht.oa.util.TLog;
import com.wlht.oa.viewholder.CommFooterVH;

import java.util.ArrayList;
import java.util.List;

public class CoreAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public boolean isHasMore = true;
    public int viewtype, isHasFooter = 0, isHasHader = 0, mHeadViewType;
    public Object mHeadData;
    public Class<? extends BaseViewHolder> mItemViewClass, mHeadViewClass, mFooterViewClass = CommFooterVH.class;
    public int mFooterViewType = CommFooterVH.LAYOUT_TYPE;
    protected List<T> mItemList = new ArrayList<>();

    public void setViewType(int i, Class<? extends BaseViewHolder> cla) {
        this.isHasMore = true;
        this.viewtype = i;
        this.mItemList = new ArrayList<>();
        this.mItemViewClass = cla;
        notifyDataSetChanged();
    }

    public void setHeadViewType(int i, Class<? extends BaseViewHolder> cla, Object data) {
        if (cla == null) {
            this.isHasHader = 0;
        } else {
            this.isHasHader = 1;
            this.mHeadViewType = i;
            this.mHeadViewClass = cla;
            this.mHeadData = data;
        }
    }

    public void setHeadViewData(Object data) {
        this.mHeadData = data;
    }

    public void setFooterViewType(int i, Class<? extends BaseViewHolder> cla) {
        this.mFooterViewType = i;
        this.mFooterViewClass = cla;
        this.mItemList = new ArrayList<>();
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return isHasHader == 1 ?
                (position == 0 ? mHeadViewType: (position + 1 == getItemCount() ? mFooterViewType : viewtype))
                : ((position + 1 == getItemCount() && isHasFooter == 1) ? mFooterViewType : viewtype);
    }

    @Override
    public int getItemCount() {
        if (mItemList.size() == 0) return isHasHader;
        return mItemList.size() + isHasFooter + isHasHader;
    }

    public void setBeans(List<T> datas, int begin) {
        if (datas == null) datas = new ArrayList<>();
        /**
         * 没有Footer的时候,需要hasMore保存为true状态
         * 在onBindViewHolder的时候才能更好的处理数据
         */
        this.isHasMore = isHasFooter == 0 || datas.size() >= Constants.PAGE_COUNT;
        if (begin > 1) {
            this.mItemList.addAll(datas);
        } else {
            this.mItemList = datas;
        }
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        try {
            boolean isFoot = viewType == mFooterViewType;
            return viewType == mHeadViewType
                    ? mHeadViewClass
                    .getConstructor(View.class).newInstance(
                            LayoutInflater.from(parent.getContext()).inflate(
                                    mHeadViewType, parent, false))
                    : (RecyclerView.ViewHolder) (isFoot ? mFooterViewClass : mItemViewClass)
                    .getConstructor(View.class).newInstance(
                            LayoutInflater.from(parent.getContext())
                                    .inflate(
                                            isFoot ? mFooterViewType
                                                    : viewtype, parent,
                                            false));
        } catch (Exception e) {
            TLog.log("ViewHolderException", "onCreateViewHolder十有八九是xml写错了,哈哈");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        //hasMore 和 hasFooter 同步出现
        /**
         * hasFooter  没有footer的时候,hasMore 没有卵用
         * hasMore    有更多,
         *
         * hasFooter决定hasMore,
         * 如果有hasFooter       hasMore==true （加载中） else  加载完成
         * 如果没有hasFooter     无所谓true,false
         *
         * hasMore ?(hasFooter?object:lastElement)  : null
         *
         * hasFooter == false || hasMore ? lastElement :
         *
         * hasHader   有头部
         */

        Object value =  position + 1 == getItemCount()
                ? (isHasMore ? mItemList.get(position - isHasHader): null)
                : (isHasHader == 1 && position == 0 ? mHeadData : mItemList.get(position - isHasHader));


        ((BaseViewHolder) holder).onBindViewHolder(holder.itemView,value);

        //设置了监听,并且当isHasHader存在且pos不为0
        if(mListener != null && !(isHasHader == 1 && position != 0)) {
            holder.itemView.setOnClickListener(v -> mListener.onItemClick(isHasHader == 1?position - 1 : position, value));
        }
    }

    public void removeItem(int position) {
        mItemList.remove(position);
        notifyItemRemoved(position);
    }

    public void upDateItem(int position, T item) {
        mItemList.remove(position);
        mItemList.add(position, item);
        notifyItemChanged(position);
    }

    public void addData(T data)
    {
        mItemList.add(data);
        notifyDataSetChanged();
    }

    public T getItem(int index)
    {
        return mItemList.get(index);
    }

    public int indexOf(T data)
    {
        return mItemList.indexOf(data);
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
                    return getItemViewType(position) == mHeadViewType
                            ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }


    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if(lp != null
                && lp instanceof StaggeredGridLayoutManager.LayoutParams
                && holder.getLayoutPosition() == 0) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
            p.setFullSpan(true);
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



}
