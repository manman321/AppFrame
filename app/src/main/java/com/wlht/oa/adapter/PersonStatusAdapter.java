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

public class PersonStatusAdapter extends RecyclerSwipeAdapter<PersonStatusAdapter.SimpleViewHolder>
        implements StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder> {


    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView name;
        public TextView dept,post;
        public SimpleViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.imageView);
            name = (TextView) itemView.findViewById(R.id.nameTv);
            dept = (TextView)itemView.findViewById(R.id.deptTv);
            post = (TextView)itemView.findViewById(R.id.postTv);
        }
    }

    private Context mContext;
    private ArrayList<Contact> mDataset;
    public static final int SORT_STATUS = 1;
    public static final int SORT_LETTER = 2;
    protected int sortType = SORT_STATUS;
    CircleTransform circleTransform = new CircleTransform();
    protected BaseRecyclerAdapter.OnItemClickListener onItemClickListener;
    //protected SwipeItemRecyclerMangerImpl mItemManger = new SwipeItemRecyclerMangerImpl(this);

    public PersonStatusAdapter(Context context)
    {
        this.mContext = context;
        mDataset = new ArrayList<>();
    }

    public PersonStatusAdapter(Context context, ArrayList<Contact> objects) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cell_person, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SimpleViewHolder viewHolder, final int position) {
        Contact data = mDataset.get(position);

        Picasso.with(mContext).load(data.imageUrl).transform(circleTransform).into(viewHolder.imageView);
        viewHolder.name.setText(data.name);
        viewHolder.dept.setText(data.dept);
        viewHolder.post.setText(data.post);



        if(onItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(position, getItem(position));
                }
            });

        }

    }


    public Contact getItem(int position)
    {
        return mDataset.get(position);
    }


    @Override
    public long getHeaderId(int position) {

        return getItem(position).getStatus().hashCode();
//        return sortType == SORT_STATUS?getItem(position).getStatus().hashCode() : getItem(position).getSortLetters().charAt(0);
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
        if (sortType == SORT_STATUS)
        {
            textView.setText(contact.stickyTitle);
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
            if (sortType == SORT_STATUS)
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


}
