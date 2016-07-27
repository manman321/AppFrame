package com.wlht.oa.bean;

import com.wlht.oa.base.BaseEntity;

import java.io.Serializable;

/**
 * Created by hr on 16/6/15.
 */
public class Contact extends BaseEntity.ListBean
{
    public String name;
    public String dept;
    public String phone;
    public String post;
    public String imageUrl;
    public String status;

    public String stickyTitle;


    public Contact(){}

    public Contact(String dept, String name)
    {
        this.name = name;
        this.dept = dept;
        this.status = "出勤";
    }

    public Contact(String dept, String name,String status)
    {
        this.name = name;
        this.dept = dept;
        this.status = status;
    }

    public String sortDept;
    public String sortLetters;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSortDept() {
        return sortDept;
    }

    public void setSortDept(String sortDept) {
        this.sortDept = sortDept;
    }

    public String getSortLetters() {
        return sortLetters;
    }

    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getStickyTitle() {
        return stickyTitle;
    }

    public void setStickyTitle(String stickyTitle) {
        this.stickyTitle = stickyTitle;
    }
}
