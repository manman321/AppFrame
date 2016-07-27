package com.wlht.oa.base.net;

import com.wlht.oa.base.exception.ServerException;
import com.wlht.oa.bean.Result;

import java.util.ArrayList;

import rx.functions.Func1;

public class ServerResponseFunc2<T extends ArrayList<T>> implements Func1<Result<T>, T> {
    @Override
    public T call(Result<T> reponse) {
        //对返回码进行判断，如果不是0，则证明服务器端返回错误信息了，便根据跟服务器约定好的错误码去解析异常
        if (reponse.getCode() != 0) {
            //如果服务器端有错误信息返回，那么抛出异常，让下面的方法去捕获异常做统一处理
            throw new ServerException(reponse.getCode(),reponse.getMsg());
        }
        //服务器请求数据成功，返回里面的数据实体
        return reponse.data;
    }
}
