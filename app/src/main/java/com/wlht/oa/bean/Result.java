package com.wlht.oa.bean;


import java.io.Serializable;

/**
 * 数据操作结果实体类
 * 
 * @author liux (http://my.oschina.net/liux)
 * @version 1.0
 * @created 2012-3-21
 */
public class Result<T> implements Serializable {

    public static final int NETWORK_ERROR = 100;

    protected String status;

    public int code;

    protected String msg;

    protected int errorType;

    public T data;


    public boolean OK() {
	    return "success".equals(status);
    }

    public boolean hasData()
    {
        return OK() && data != null;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
	return code;
    }

    public void setCode(int code) {
	this.code = code;
    }

    public String getMsg() {
	return msg;
    }

    public void setMsg(String msg) {
	this.msg = msg;
    }


    public int getErrorType() {
        return errorType;
    }

    public void setErrorType(int errorType) {
        this.errorType = errorType;
    }
}
