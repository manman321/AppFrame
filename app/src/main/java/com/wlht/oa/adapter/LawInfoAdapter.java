package com.wlht.oa.adapter;/**
 * Created by hr on 16/6/29.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wlht.oa.R;
import com.wlht.oa.bean.LawInfo;

public class LawInfoAdapter extends BaseRecyclerAdapter<LawInfo, LawInfoAdapter.SimpleViewHolder> {

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        return new SimpleViewHolder(inflate(parent, R.layout.list_cell_law_info));
    }

    @Override
    public void onBind(LawInfoAdapter.SimpleViewHolder viewHolder, int RealPosition, LawInfo data) {
        super.onBind(viewHolder, RealPosition, data);
        viewHolder.titleTv.setText(data.title);
        viewHolder.timeTv.setText(data.time);
    }

    public void fillData(View view,LawInfo data)
    {

        /**
         * 获取本来的名字,
         * 获取对应的字段,
         * 通过字段的注解,
         * 转换成目标参数,
         * 通过注解设置给目标
         * 优点
         * 1.普通的adapter类全部抽象成通用和同意的逻辑了。
         *   是否可以使用成通用模板或者一个通用的类来解决。
         *   或者直接通过Process来自动生成adapter
         *
         * 2.填充数据的逻辑部分置后到bean模型中,
         *
         * 3.界面到数据的映射
         * ->通过约定的方式完成(初期)
         * ->通过配置的方式完成
         */
    }



    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTv;
        public TextView timeTv;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.titleTv);
            timeTv = (TextView) itemView.findViewById(R.id.timeTv);
        }
    }
}
