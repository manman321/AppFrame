package com.wlht.oa.bean;

import java.io.Serializable;

/**
 * Created by hr on 16/6/16.
 */
public class Message implements Serializable {
    public String imageUrl;
    public String title;
    public String content;
    public String time;
    public String type;//根据消息的类型来决定,跳转到哪一个具体的页面
    public String sourceId;//消息产生的源头的id,跳转到具体页面的时候,使用
    public String id;





    public Message(){}

    public Message(String imageUrl,String title,String content,String time)
    {
        this.imageUrl = imageUrl;
        this.title = title;
        this.content = content;
        this.time = time;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
