package com.wlht.oa.bean;

import java.io.Serializable;

/**
 * Created by hr on 16/6/29.
 */
public class Comment implements Serializable
{
    /**
     * 评论者的名字
     */
    public String name;

    /**
     * 回复xx用户
     */
    public String toName;

    public String content;
    public String time;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
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
