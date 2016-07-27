package com.wlht.oa.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.wlht.oa.KV;
import com.wlht.oa.KVO;
import com.wlht.oa.R;
import com.wlht.oa.widget.HomeItem;

/**
 * Created by hr on 16/6/13.
 */
public class HomeAdapter extends BaseRecyclerAdapter<KVO<Integer,String,Class>,RecyclerView.ViewHolder>
{

    protected Context mContext;
    protected LayoutInflater mInflater;
    int space = 0;
    int columnCount = 3;

    protected int mItemWh = 0;
    public HomeAdapter(Context context)
    {
        mContext = context;
        mInflater = LayoutInflater.from(context);

        int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        space = context.getResources().getDimensionPixelSize(R.dimen.dp1);
        screenWidth -= ((columnCount - 1) * space);
        mItemWh = screenWidth / columnCount;
    }


    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        HomeItem item = new HomeItem(mContext,mItemWh);
        return new RecyclerView.ViewHolder(item){};

    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, KVO<Integer, String,Class> data) {
        super.onBind(viewHolder, RealPosition, data);


//        ImageView imageView = (ImageView)viewHolder.itemView.findViewById(R.id.imageView);
//        TextView  textView  = (TextView)viewHolder.itemView.findViewById(R.id.title);
//        imageView.getLayoutParams().width = (int)(mItemWh * 0.5f);
//        imageView.getLayoutParams().height = (int)(mItemWh * 0.5f);
//        viewHolder.itemView.getLayoutParams().height = mItemWh;
////        viewHolder.itemView.getLayoutParams().width = mItemWh;
//
//        Picasso.with(viewHolder.itemView.getContext()).load(data.key).into(imageView);
//        textView.setText(data.value);


        HomeItem homeItem = (HomeItem)viewHolder.itemView;
        homeItem.setDrawableWithText(data.key,data.value);
        homeItem.setTag(data);
    }

//    public class HeaderViewHolder extends BaseRecyclerAdapter.Holder {
//        public HeaderViewHolder(View itemView) {
//            super(itemView);
//        }
//    }

}
