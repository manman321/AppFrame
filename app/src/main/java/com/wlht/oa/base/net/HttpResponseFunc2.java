package com.wlht.oa.base.net;

import com.wlht.oa.base.exception.ExceptionEngine;

import java.util.ArrayList;

import rx.Observable;
import rx.functions.Func1;

public class HttpResponseFunc2<T extends ArrayList<T>> implements Func1<Throwable, Observable<T>> {
    @Override
    public Observable<T> call(Throwable throwable) {
        //ExceptionEngine为处理异常的驱动器
        return Observable.error(ExceptionEngine.handleException(throwable));
    }
}