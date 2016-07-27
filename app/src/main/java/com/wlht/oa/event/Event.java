package com.wlht.oa.event;

import java.io.Serializable;

/**
 * Created by hr on 16/7/18.
 */
public class Event<T> implements Serializable{
    public Class from;
    public T data;

    public Event(Class from, T data) {
        this.from = from;
        this.data = data;
    }
}
