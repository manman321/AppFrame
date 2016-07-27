package com.wlht.oa;

/**
 * Created by hr on 16/6/30.
 */
public class KVO<K,V,O> extends KV<K,V> {

    public O object;

    public KVO(K k, V v) {
        super(k, v);
    }

    public KVO(K k,V v,O o)
    {
        this(k,v);
        this.object = o;
    }

}
