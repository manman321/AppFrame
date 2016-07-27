package com.wlht.oa.adapter;

public interface onMoveAndSwipedListener {
    boolean onItemMove(int fromPosition , int toPosition);
    void onItemDismiss(int position);
}