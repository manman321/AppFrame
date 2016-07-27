package com.wlht.oa.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.implments.SwipeItemRecyclerMangerImpl;
import com.daimajia.swipe.interfaces.SwipeAdapterInterface;
import com.daimajia.swipe.interfaces.SwipeItemMangerInterface;
import com.daimajia.swipe.util.Attributes;
import com.wlht.oa.R;
import com.wlht.oa.adapter.expandRecyclerviewadapter.StickyRecyclerHeadersAdapter;
import com.wlht.oa.base.BaseListFragment;
import com.wlht.oa.base.BaseViewHolder;
import com.wlht.oa.util.TLog;
import com.wlht.oa.viewholder.CommFooterVH;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by hr on 16/7/26.
 */
public abstract class BaseStickyAdapter<T> extends CoreAdapter<T>
        implements StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder>, SwipeItemMangerInterface, SwipeAdapterInterface {

    public SwipeItemRecyclerMangerImpl mItemManger = new SwipeItemRecyclerMangerImpl(this);

    public Class<? extends BaseViewHolder> mStickyClass;
    public int mStickyType;

    private Context mContext;
    public BaseStickyAdapter(Context context)
    {
        mContext = context;
    }

    public void setStickyView(Class<? extends BaseViewHolder<T>> cla) {
        try {
            BaseViewHolder mIVH = ((BaseViewHolder) (cla.getConstructor(View.class)
                    .newInstance(new View(mContext))));
            mStickyClass = cla;
            mStickyType = mIVH.getType();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        try {
           return mStickyClass.getConstructor(View.class).newInstance(
                            LayoutInflater.from(parent.getContext()).inflate(
                                    mStickyType, parent, false));
        } catch (Exception e) {
            TLog.log("ViewHolderException", "onCreateViewHolder十有八九是xml写错了,哈哈");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        Object value =  position + 1 == getItemCount()
                ? (isHasMore ? new Object(): null)
                : (isHasHader == 1 && position == 0 ? mHeadData : mItemList.get(position - isHasHader));
        ((BaseViewHolder) holder).onBindViewHolder(holder.itemView,value);
    }

    @Override
    public void openItem(int position) {
        mItemManger.openItem(position);
    }

    @Override
    public void closeItem(int position) {
        mItemManger.closeItem(position);
    }

    @Override
    public void closeAllExcept(SwipeLayout layout) {
        mItemManger.closeAllExcept(layout);
    }

    @Override
    public void closeAllItems() {
        mItemManger.closeAllItems();
    }

    @Override
    public List<Integer> getOpenItems() {
        return mItemManger.getOpenItems();
    }

    @Override
    public List<SwipeLayout> getOpenLayouts() {
        return mItemManger.getOpenLayouts();
    }

    @Override
    public void removeShownLayouts(SwipeLayout layout) {
        mItemManger.removeShownLayouts(layout);
    }

    @Override
    public boolean isOpen(int position) {
        return mItemManger.isOpen(position);
    }

    @Override
    public Attributes.Mode getMode() {
        return mItemManger.getMode();
    }

    @Override
    public void setMode(Attributes.Mode mode) {
        mItemManger.setMode(mode);
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }


}
