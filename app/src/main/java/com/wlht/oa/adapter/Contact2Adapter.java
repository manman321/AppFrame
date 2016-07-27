package com.wlht.oa.adapter;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wlht.oa.R;
import com.wlht.oa.bean.Contact;

import java.util.Collections;

/**
 * Created by hr on 16/6/14.
 */
public class Contact2Adapter extends BaseRecyclerAdapter<Contact,Contact2Adapter.SimpleViewHolder> implements onMoveAndSwipedListener
{


    private Context mContext;
    private LayoutInflater mInflater;
    public Contact2Adapter(Context context)
    {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }


    protected ItemTouchHelper helper;
    public Contact2Adapter(Context context, ItemTouchHelper helper)
    {
        this(context);
        this.helper = helper;
    }



    @Override
    public void onBind(Contact2Adapter.SimpleViewHolder viewHolder, int RealPosition, Contact data) {
        super.onBind(viewHolder, RealPosition, data);
        SimpleViewHolder holder = viewHolder;
        holder.name.setText(data.name);
        holder.description.setText(String.format("%s/%s",data.dept,data.post));
        Picasso.with(mContext).load(data.imageUrl).into(holder.imageView);
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_cell_contact2,parent,false);
        return new SimpleViewHolder(view);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mDatas,fromPosition,toPosition);
        notifyItemMoved(fromPosition,toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {

        mDatas.remove(position);
        notifyItemRemoved(position);

    }

    public  class SimpleViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView name;
        public TextView description;
        public SimpleViewHolder(View view) {
            super(view);
            imageView = (ImageView)view.findViewById(R.id.imageView);
            name = (TextView) view.findViewById(R.id.name);
            description = (TextView)view.findViewById(R.id.description);

            imageView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN)
                    {
                        helper.startDrag(SimpleViewHolder.this);
                    }
                    return false;
                }
            });
        }
    }
}
