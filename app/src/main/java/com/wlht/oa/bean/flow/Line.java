package com.wlht.oa.bean.flow;

import java.io.Serializable;

public class Line  implements Serializable {
    private String id;
    private String from;
    private String to;
    private String customMethod;
    private String noaccordMsg;
    private String sql;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCustomMethod() {
        return customMethod;
    }

    public void setCustomMethod(String customMethod) {
        this.customMethod = customMethod;
    }

    public String getNoaccordMsg() {
        return noaccordMsg;
    }

    public void setNoaccordMsg(String noaccordMsg) {
        this.noaccordMsg = noaccordMsg;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
