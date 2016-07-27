package com.wlht.oa.adapter;
import android.content.Context;
import com.wlht.oa.bean.approve.Template;
public class CateAdapter extends BaseStickyAdapter<Template>{
    public CateAdapter(Context context) {
        super(context);
        isHasFooter = 0;
    }

    @Override
    public long getHeaderId(int position) {
        return getItem(position).categoryName.hashCode();
    }

}
