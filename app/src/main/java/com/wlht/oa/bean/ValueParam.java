package com.wlht.oa.bean;

import java.io.Serializable;

/**
 * Created by hr on 16/7/26.
 */
public class ValueParam implements Serializable {

    public ValueParam(String method, String name, int page) {
        this.method = method;
        this.name = name;
        this.page = page;
    }

    public String method;
    public String name;
    public int page;

    public ValueParam(){}



    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
