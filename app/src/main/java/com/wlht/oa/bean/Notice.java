package com.wlht.oa.bean;

import java.io.Serializable;

/**
 * Created by hr on 16/6/22.
 */
public class Notice implements Serializable {

    public String title;//标题
    public String content;//内容
    public String accept;//接受的人或者部门
    public String time;//发起时间
    public boolean isRead;//是否已读,(用户是否已读)
    public String sponsor;//发起人
    public String readNum;//已读人数
    public String unreadNum;//未读人数


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

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public String getReadNum() {
        return readNum;
    }

    public void setReadNum(String readNum) {
        this.readNum = readNum;
    }

    public String getUnreadNum() {
        return unreadNum;
    }

    public void setUnreadNum(String unreadNum) {
        this.unreadNum = unreadNum;
    }
}
