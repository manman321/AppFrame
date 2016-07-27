package com.wlht.oa.bean;

import com.wlht.oa.base.BaseEntity;
import com.wlht.oa.net.Api;

import java.io.Serializable;
import java.util.Map;

import okhttp3.RequestBody;
import rx.Observable;

/**
 * Created by hr on 16/7/25.
 */
public class Value extends BaseEntity.ListBean implements Serializable {
//    public String id;
    public String value;

//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public void setParam(Map<String, String> param) {
        super.setParam(param);

    }

    @Override
    public Observable getPageAt(int page) {
        param.put("page", String.format("%d", page));
//        String json = "{\"page\":\"1\",\"name\":\"张三\"}";
//        RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        return Api.getInstance().movieService.data(param);
    }
}
