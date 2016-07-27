package com.wlht.oa.bean;

import java.io.Serializable;

/**
 * Created by hr on 16/6/29.
 */
public class Praise implements Serializable {
    public String id;
    public String uid;
    public String name;
    public String time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
