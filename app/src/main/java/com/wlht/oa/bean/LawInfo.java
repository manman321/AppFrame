package com.wlht.oa.bean;

import java.io.Serializable;

/**
 * Created by hr on 16/6/29.
 */
public class LawInfo implements Serializable
{
    public String title;
    public String time;
    public String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
