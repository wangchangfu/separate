package com.mapscience.core.common;

import java.io.Serializable;

/**
 * 响应信息
 *
 * @param <T>
 */
public class ResponseVal<T> implements Serializable {
    private int code;
    private String msg;
    private T data;

    /**
     * 带数据返回
     *
     * @param code 返回状态码
     * @param msg  返回描述
     * @param data 返回数据
     */
    public ResponseVal(int code, String msg, T data) {
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    /**
     * 不带数据返回
     *
     * @param code 返回状态码
     * @param msg  返回描述
     */
    public ResponseVal(int code, String msg) {
        this.code = code;
        this.msg = msg;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
