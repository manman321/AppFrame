package com.wlht.oa.base.util.helper;

import com.wlht.oa.base.net.HttpResponseFunc;
import com.wlht.oa.base.net.ServerResponseFunc;
import com.wlht.oa.bean.Result;

import java.util.ArrayList;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by baixiaokang on 16/5/6.
 */
public class RxSchedulers {
    public static <T> Observable.Transformer<T, T> io_main() {
        return tObservable -> tObservable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public static <T> Observable.Transformer<Result<T>, T> error_handle_io_main() {
        return tObservable -> tObservable
                .map(new ServerResponseFunc<>())
                .onErrorResumeNext(new HttpResponseFunc<>())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public static <T extends ArrayList<T>> Observable.Transformer<Result<T>, T> error_handle_io_main_list() {
        return tObservable -> tObservable
                .map(new ServerResponseFunc<>())
                .onErrorResumeNext(new HttpResponseFunc<>())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


//    .map(new ServerResponseFunc<>())
//            .onErrorResumeNext(new HttpResponseFunc<>())

}
