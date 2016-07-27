package com.wlht.oa.intf;

import android.support.v7.widget.RecyclerView;

import com.wlht.oa.adapter.BaseRecyclerAdapter;
import com.wlht.oa.base.BaseActivity;

import java.util.ArrayList;

/**
 * Created by hr on 16/6/27.
 */
public interface SearchBarListener<T,VH extends RecyclerView.ViewHolder> {

    String getHintText();
    BaseRecyclerAdapter<T,VH> getAdapter();
    void searchContentChanged(String value);
    ArrayList<T> search();

    void onItemClick(BaseActivity context);


}
