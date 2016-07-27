package com.wlht.oa.bean.approve;

import java.io.Serializable;

/**
 * Created by hr on 16/7/13.
 * 种类:日常类、行政类、财务、人事、创建新
 */
public class Category implements Serializable {

    public String id;
    public String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}