package com.wlht.oa.bean.flow;

import java.io.Serializable;

public class Form  implements Serializable {
    private String id;
    private String name;
    private String type;
    private int srot;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSrot() {
        return srot;
    }

    public void setSrot(int srot) {
        this.srot = srot;
    }
}
