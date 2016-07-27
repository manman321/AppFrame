package com.wlht.oa.bean.flow;

import java.io.Serializable;

public class TitleField  implements Serializable {
    private String link;
    private String table;
    private String field;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}





