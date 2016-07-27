package com.wlht.oa.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.squareup.picasso.Picasso;
import com.wlht.oa.R;
import com.wlht.oa.bean.Contact;

import java.util.ArrayList;

public class Contact3Adapter extends RecyclerSwipeAdapter<Contact3Adapter.SimpleViewHolder> {

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        SwipeLayout swipeLayout;
        public ImageView imageView;
        public TextView name;
        public TextView description;
        public View bottomWrapper;
        public SimpleViewHolder(View itemView) {
            super(itemView);
            swipeLayout = (SwipeLayout) itemView.findViewById(R.id.swipe);
            swipeLayout.setClickToClose(true);
            imageView = (ImageView)itemView.findViewById(R.id.imageView);
            name = (TextView) itemView.findViewById(R.id.name);
            description = (TextView)itemView.findViewById(R.id.description);
            bottomWrapper = itemView.findViewById(R.id.bottom_wrapper);
        }
    }

    private Context mContext;
    private ArrayList<Contact> mDataset;

    //protected SwipeItemRecyclerMangerImpl mItemManger = new SwipeItemRecyclerMangerImpl(this);

    public Contact3Adapter(Context context)
    {
        this.mContext = context;
        mDataset = new ArrayList<>();
    }

    public Contact3Adapter(Context context, ArrayList<Contact> objects) {
        this(context);
        this.mDataset.addAll(objects);
    }


    public void addDatas(ArrayList<Contact> datas)
    {
        mDataset.clear();
        mDataset.addAll(datas);
        notifyDataSetChanged();
    }



    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cell_contact3, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SimpleViewHolder viewHolder, final int position) {
        Contact data = mDataset.get(position);
        viewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
        viewHolder.swipeLayout.removeView(viewHolder.bottomWrapper);
        viewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Left,viewHolder.bottomWrapper,new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));


        viewHolder.swipeLayout.addSwipeListener(new SimpleSwipeListener() {
            @Override
            public void onOpen(SwipeLayout layout) {
                YoYo.with(Techniques.Tada).duration(500).delay(100).playOn(layout.findViewById(R.id.trash));
            }
        });
        viewHolder.name.setText(data.name);
        viewHolder.description.setText(String.format("%s/%s", data.dept, data.post));
        Picasso.with(mContext).load(data.imageUrl).into(viewHolder.imageView);

        mItemManger.bindView(viewHolder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }
}
