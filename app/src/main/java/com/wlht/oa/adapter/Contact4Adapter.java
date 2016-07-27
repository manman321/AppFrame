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
import com.wlht.oa.adapter.expandRecyclerviewadapter.StickyRecyclerHeadersAdapter;
import com.wlht.oa.bean.Contact;
import com.wlht.oa.transform.CircleTransform;

import java.util.ArrayList;

public class Contact4Adapter extends RecyclerSwipeAdapter<Contact4Adapter.SimpleViewHolder>
        implements StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder> {


    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        SwipeLayout swipeLayout;
        public ImageView imageView;
        public TextView name;
        public TextView description;
        public View bottomWrapper,topWrapper;
        public SimpleViewHolder(View itemView) {
            super(itemView);
            swipeLayout = (SwipeLayout) itemView.findViewById(R.id.swipe);
            swipeLayout.setClickToClose(true);
            imageView = (ImageView)itemView.findViewById(R.id.imageView);
            name = (TextView) itemView.findViewById(R.id.name);
            description = (TextView)itemView.findViewById(R.id.description);
            bottomWrapper = itemView.findViewById(R.id.bottom_wrapper);
            topWrapper = itemView.findViewById(R.id.top_wrapper);
        }
    }

    private Context mContext;
    private ArrayList<Contact> mDataset;
    public static final int SORT_DEPT = 1;
    public static final int SORT_LETTER = 2;
    protected int sortType = SORT_DEPT;
    CircleTransform circleTransform = new CircleTransform();
    private SwipeLayout mOpenedSwipeLayout;
    protected BaseRecyclerAdapter.OnItemClickListener onItemClickListener;
    //protected SwipeItemRecyclerMangerImpl mItemManger = new SwipeItemRecyclerMangerImpl(this);

    public Contact4Adapter(Context context)
    {
        this.mContext = context;
        mDataset = new ArrayList<>();
    }

    public Contact4Adapter(Context context, ArrayList<Contact> objects) {
        this(context);
        this.mDataset.addAll(objects);
    }


    public void setSortType(int sortType)
    {
        this.sortType = sortType;
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
        viewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Left, viewHolder.bottomWrapper, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));


        viewHolder.swipeLayout.addSwipeListener(new SimpleSwipeListener() {

//            @Override
//            public void onStartOpen(SwipeLayout layout) {
//                super.onStartOpen(layout);
//                mOpenedSwipeLayout = layout;
//            }

            @Override
            public void onOpen(SwipeLayout layout) {
                YoYo.with(Techniques.Tada).duration(500).delay(100).playOn(layout.findViewById(R.id.trash));
                mOpenedSwipeLayout = layout;
            }

            @Override
            public void onClose(SwipeLayout layout) {
                super.onClose(layout);
                mOpenedSwipeLayout = null;
            }

//            @Override
//            public void onStartClose(SwipeLayout layout) {
//                super.onStartClose(layout);
//            }
        });


        viewHolder.name.setText(data.name);
        viewHolder.description.setText(String.format("%s/%s", data.dept, data.post));
        Picasso.with(mContext).load(data.imageUrl).transform(circleTransform).into(viewHolder.imageView);

        mItemManger.bindView(viewHolder.itemView, position);

        if(onItemClickListener != null) {
            viewHolder.topWrapper.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        onItemClickListener.onItemClick(position, getItem(position));
                }
            });






//            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                }
//            });
        }
    }


    public Contact getItem(int position)
    {
        return mDataset.get(position);
    }


    @Override
    public long getHeaderId(int position) {
        return sortType == SORT_DEPT?getItem(position).getDept().hashCode() : getItem(position).getSortLetters().charAt(0);
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cell_contact_header, parent, false);
        return new RecyclerView.ViewHolder(view) {};
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        TextView textView = (TextView) holder.itemView;
        Contact contact = getItem(position);
        if (sortType == SORT_DEPT)
        {
            textView.setText(contact.dept);
        }else{
            String showValue = String.valueOf(contact.getSortLetters().charAt(0));
            textView.setText(showValue);
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }


    public int getPositionForSection(char section) {
        for (int i = 0; i < getItemCount(); i++) {
            String sortStr = getItem(i).getSortLetters();
            if (sortType == SORT_DEPT)
            {
                sortStr = getItem(i).getSortDept();
            }
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }

    public void setOnItemClickListener(BaseRecyclerAdapter.OnItemClickListener onItemClickListener)
    {
        this.onItemClickListener = onItemClickListener;
    }

    public void closeOpenedSwipeLayoutWithAnim() {
        if (mOpenedSwipeLayout != null)mOpenedSwipeLayout.close(true);
    }
}
