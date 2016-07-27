package com.wlht.oa.bean.flow;

import java.io.Serializable;

public class Event  implements Serializable {
    private String submitBefore;
    private String submitAfter;
    private String backBefore;
    private String backAfter;

    public String getSubmitBefore() {
        return submitBefore;
    }

    public void setSubmitBefore(String submitBefore) {
        this.submitBefore = submitBefore;
    }

    public String getSubmitAfter() {
        return submitAfter;
    }

    public void setSubmitAfter(String submitAfter) {
        this.submitAfter = submitAfter;
    }

    public String getBackBefore() {
        return backBefore;
    }

    public void setBackBefore(String backBefore) {
        this.backBefore = backBefore;
    }

    public String getBackAfter() {
        return backAfter;
    }

    public void setBackAfter(String backAfter) {
        this.backAfter = backAfter;
    }
}
