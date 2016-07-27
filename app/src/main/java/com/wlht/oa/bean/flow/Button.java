package com.wlht.oa.bean.flow;

import java.io.Serializable;

public class Button implements Serializable{
    private String id;
    private int sort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
