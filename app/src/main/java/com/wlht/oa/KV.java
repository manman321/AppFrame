package com.wlht.oa;

import java.io.Serializable;

/**
 * Created by hr on 16/6/12.
 */
public class KV<K,V> implements Serializable
{
    public K key;
    public V value;

    public KV(K k,V v)
    {
        key = k;
        value = v;
    }


    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
