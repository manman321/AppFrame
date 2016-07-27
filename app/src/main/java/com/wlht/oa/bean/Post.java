package com.wlht.oa.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by hr on 16/6/29.
 */
public class Post implements Serializable {

    public String imageUrl;
    public String name;
    public String content;
    public String time;
    public ArrayList<Praise> praises;
    public ArrayList<Comment> comments;


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public ArrayList<Praise> getPraises() {
        return praises;
    }

    public void setPraises(ArrayList<Praise> praises) {
        this.praises = praises;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (imageUrl != null ? !imageUrl.equals(post.imageUrl) : post.imageUrl != null)
            return false;
        if (name != null ? !name.equals(post.name) : post.name != null) return false;
        if (content != null ? !content.equals(post.content) : post.content != null) return false;
        return !(time != null ? !time.equals(post.time) : post.time != null);

    }

    @Override
    public int hashCode() {
        int result = imageUrl != null ? imageUrl.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }
}
