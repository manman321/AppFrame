package com.wlht.oa.bean;

import java.io.Serializable;

/**
 * Created by hr on 16/6/14.
 */
public class Banner implements Serializable
{
    public String imageUrl;
    public String linkUrl;
    public String title;



    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
