package com.huige.erp.common.dto;

/**
 * @Auther Z.xichao
 * @Create 2018-1-3
 * @Comments
 */
public class ResponseResultWithListAll<T> extends BaseResponseResult<T> {

    private long count = 0;

    public ResponseResultWithListAll() {
    }


    public ResponseResultWithListAll(long count, T data) {
        this.count = count;
        this.data = data;
    }

    public ResponseResultWithListAll(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseResultWithListAll(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseResultWithListAll(int code, String message, long count, T data) {
        this.code = code;
        this.message = message;
        this.count = count;
        this.data = data;
    }

    public long getCount() {

        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }


    @Override
    public String toString() {
        return "ResponseResultWithListAll{" +
                "count=" + count +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
