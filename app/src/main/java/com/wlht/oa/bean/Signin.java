package com.wlht.oa.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by hr on 16/6/27.
 */
public class Signin implements Serializable {



    public String address;
    public float lat;
    public float lng;
    public String time;
    public String description;
    public String mapImageUrl;
    public ArrayList<String> attachImageUrls;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMapImageUrl() {
        return mapImageUrl;
    }

    public void setMapImageUrl(String mapImageUrl) {
        this.mapImageUrl = mapImageUrl;
    }

    public ArrayList<String> getAttachImageUrls() {
        return attachImageUrls;
    }

    public void setAttachImageUrls(ArrayList<String> attachImageUrls) {
        this.attachImageUrls = attachImageUrls;
    }
}
