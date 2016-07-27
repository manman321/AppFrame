package com.wlht.oa.adapter;/**
 * Created by hr on 16/7/14.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wlht.oa.R;
import com.wlht.oa.bean.approve.TemplateField;
import com.wlht.oa.widget.InfoViewRow;

import java.util.ArrayList;
import java.util.List;

public class TemplateFieldAdapter extends BaseRecyclerAdapter<ArrayList<TemplateField>, TemplateFieldAdapter.SimpleViewHolder> {

    private int columns = 0;
    public TemplateFieldAdapter(int column)
    {
        this.columns = column;
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        InfoViewRow infoViewRow =  new InfoViewRow(parent.getContext());
        infoViewRow.initWithColumn(columns);
        return new SimpleViewHolder(infoViewRow);
    }

    @Override
    public void onBind(TemplateFieldAdapter.SimpleViewHolder viewHolder, int RealPosition, ArrayList<TemplateField> data) {
        super.onBind(viewHolder, RealPosition, data);
        InfoViewRow infoViewRow = (InfoViewRow)viewHolder.itemView;
        List<TextView> views = infoViewRow.getViews();
        for (int i = 0; i < views.size();i++)
        {
            views.get(i).setText(data.get(i).data);
        }
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder
    {
        public SimpleViewHolder(View itemView) {
            super(itemView);
        }
    }


}
