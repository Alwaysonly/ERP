package com.huige.erp.common.dto;

public class ResponseResult<T> extends BaseResponseResult<T>{

    public ResponseResult() {
    }

    public ResponseResult(int code, String message) {
        super.code = code;
        super.message = message;
    }

    public ResponseResult(int code, String message, T data) {
        super.code = code;
        super.message = message;
        super.data = data;
    }

    public ResponseResult(T data) {
        super.data = data;
    }

    @Override
    public String toString() {
        return "ResponseResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
