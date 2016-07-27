/**
 * created by jiang, 12/3/15
 * Copyright (c) 2015, jyuesong@gmail.com All Rights Reserved.
 * *                #                                                   #
 * #                       _oo0oo_                     #
 * #                      o8888888o                    #
 * #                      88" . "88                    #
 * #                      (| -_- |)                    #
 * #                      0\  =  /0                    #
 * #                    ___/`---'\___                  #
 * #                  .' \\|     |# '.                 #
 * #                 / \\|||  :  |||# \                #
 * #                / _||||| -:- |||||- \              #
 * #               |   | \\\  -  #/ |   |              #
 * #               | \_|  ''\---/''  |_/ |             #
 * #               \  .-\__  '-'  ___/-. /             #
 * #             ___'. .'  /--.--\  `. .'___           #
 * #          ."" '<  `.___\_<|>_/___.' >' "".         #
 * #         | | :  `- \`.;`\ _ /`;.`/ - ` : | |       #
 * #         \  \ `_.   \_ __\ /__ _/   .-` /  /       #
 * #     =====`-.____`.___ \_____/___.-`___.-'=====    #
 * #                       `=---='                     #
 * #     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   #
 * #                                                   #
 * #               佛祖保佑         永无BUG              #
 * #                                                   #
 */

package com.wlht.oa.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wlht.oa.R;
import com.wlht.oa.adapter.expandRecyclerviewadapter.StickyRecyclerHeadersAdapter;
import com.wlht.oa.bean.Contact;
import com.wlht.oa.transform.CircleTransform;
import com.wlht.oa.widget.SwipeItemLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiang on 12/3/15.
 * 根据当前权限进行判断相关的滑动逻辑
 */
public class ContactAdapter extends BaseRecyclerAdapter<Contact,ContactAdapter.ContactViewHolder>
        implements StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder> {
    /**
     * 当前处于打开状态的item
     */
    private List<SwipeItemLayout> mOpenedSil = new ArrayList<>();

    private Context mContext;

    public static final int SORT_DEPT = 1;
    public static final int SORT_LETTER = 2;
    protected int sortType = SORT_DEPT;

    CircleTransform circleTransform = new CircleTransform();
//    Letters

    public ContactAdapter(Context ct, ArrayList<Contact> mLists) {
        mContext = ct;
        this.addDatas(mLists);
    }

    public void setSortType(int sortType)
    {
        this.sortType = sortType;
    }



    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_cell_contact, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBind(ContactAdapter.ContactViewHolder viewHolder, int RealPosition, Contact data) {
        super.onBind(viewHolder, RealPosition, data);
        ContactViewHolder holder = viewHolder;
        SwipeItemLayout swipeRoot = holder.swipeLayout;

        swipeRoot.setSwipeAble(true);
        swipeRoot.setDelegate(new SwipeItemLayout.SwipeItemLayoutDelegate() {
            @Override
            public void onSwipeItemLayoutOpened(SwipeItemLayout swipeItemLayout) {
                closeOpenedSwipeItemLayoutWithAnim();
                mOpenedSil.add(swipeItemLayout);
            }

            @Override
            public void onSwipeItemLayoutClosed(SwipeItemLayout swipeItemLayout) {
                mOpenedSil.remove(swipeItemLayout);
            }

            @Override
            public void onSwipeItemLayoutStartOpen(SwipeItemLayout swipeItemLayout) {
                closeOpenedSwipeItemLayoutWithAnim();
            }
        });

//        holder.swipeLayout.addSwipeListener(new SimpleSwipeListener() {
//            @Override
//            public void onOpen(SwipeLayout layout) {
//                YoYo.with(Techniques.Tada).duration(500).delay(100).playOn(layout.findViewById(R.id.trash));
//            }
//        });
        holder.name.setText(data.name);
        holder.description.setText(String.format("%s/%s", data.dept, data.post));
        Picasso.with(mContext)
                .load(data.imageUrl)
                .transform(circleTransform)
                .into(holder.imageView);


    }

//    @Override
//    public void onBindViewHolder(ContactAdapter.ContactViewHolder holder, final int position) {

//        if (getItem(position).getId().equals(OWNER)) {
//            swipeRoot.setSwipeAble(false);
//        } else if (isCreator) {
//            swipeRoot.setSwipeAble(true);
//            swipeRoot.setDelegate(new SwipeItemLayout.SwipeItemLayoutDelegate() {
//                @Override
//                public void onSwipeItemLayoutOpened(SwipeItemLayout swipeItemLayout) {
//                    closeOpenedSwipeItemLayoutWithAnim();
//                    mOpenedSil.add(swipeItemLayout);
//                }
//
//                @Override
//                public void onSwipeItemLayoutClosed(SwipeItemLayout swipeItemLayout) {
//                    mOpenedSil.remove(swipeItemLayout);
//                }
//
//                @Override
//                public void onSwipeItemLayoutStartOpen(SwipeItemLayout swipeItemLayout) {
//                    closeOpenedSwipeItemLayoutWithAnim();
//                }
//            });
//            holder.mDelete.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    ((MainActivity) mContext).deleteUser(position);
//                }
//            });
//        } else {
//            if (mPermission == CommonString.PermissionCode.TEACHER) {
//                if (position != 0) {
//                    if (getItem(position).getProfession().equals(STUDENT)) {
//
//                        swipeRoot.setSwipeAble(true);
//                        swipeRoot.setDelegate(new SwipeItemLayout.SwipeItemLayoutDelegate() {
//                            @Override
//                            public void onSwipeItemLayoutOpened(SwipeItemLayout swipeItemLayout) {
//                                closeOpenedSwipeItemLayoutWithAnim();
//                                mOpenedSil.add(swipeItemLayout);
//                            }
//
//                            @Override
//                            public void onSwipeItemLayoutClosed(SwipeItemLayout swipeItemLayout) {
//                                mOpenedSil.remove(swipeItemLayout);
//                            }
//
//                            @Override
//                            public void onSwipeItemLayoutStartOpen(SwipeItemLayout swipeItemLayout) {
//                                closeOpenedSwipeItemLayoutWithAnim();
//                            }
//                        });
//                        holder.mDelete.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//
//                                ((MainActivity) mContext).deleteUser(position);
//                            }
//                        });
//                    } else {
//                        swipeRoot.setSwipeAble(false);
//                    }
//                } else {
//                    swipeRoot.setSwipeAble(false);
//                }
//            } else {
//                swipeRoot.setSwipeAble(false);
//            }
//        }


//    }

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

    public void closeOpenedSwipeItemLayoutWithAnim() {
        for (SwipeItemLayout sil : mOpenedSil) {
            sil.closeWithAnim();
        }
        mOpenedSil.clear();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {
        SwipeItemLayout swipeLayout;
        public ImageView imageView;
        public TextView name;
        public TextView description;
        public View bottomWrapper;
        public ContactViewHolder(View itemView) {
            super(itemView);
            swipeLayout = (SwipeItemLayout) itemView.findViewById(R.id.swipe);
            imageView = (ImageView)itemView.findViewById(R.id.imageView);
            name = (TextView) itemView.findViewById(R.id.name);
            description = (TextView)itemView.findViewById(R.id.description);
            bottomWrapper = itemView.findViewById(R.id.bottom_wrapper);
            swipeLayout.removeView(bottomWrapper);
        }

    }
}
