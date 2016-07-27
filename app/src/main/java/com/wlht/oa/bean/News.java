package com.wlht.oa.bean;

import java.io.Serializable;

/**
 * Created by hr on 16/6/23.
 */
public class News implements Serializable {

    public String imageUrl;
    public String title;
    public String content;
    public String time;


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
