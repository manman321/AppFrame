package com.wlht.oa.bean.flow;

import java.io.Serializable;

public class FieldStatus  implements Serializable {
    private String field;
    private String status;
    private String check;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }
}