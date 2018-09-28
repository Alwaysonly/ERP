package com.huige.erp.common.dto;

/**
 * @Auther Z.xichao
 * @Create 2018-1-10
 * @Comments
 */
public class BaseResponseResult<T> {

    public final static int SUCCESS = 200;
    public final static int REDIRECT = 301;
    public final static int ERROR = 404;
    public final static int ERROR_PARM = 400;
    public final static int ERROR_AUTH = 401;
    public final static int FAILURE = 500;

    protected int code =  200;
    protected String message = "success";
    protected T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
