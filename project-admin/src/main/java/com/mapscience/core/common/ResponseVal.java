package com.mapscience.core.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 响应信息
 *
 * @param <T>
 */
@ApiModel(value = "返回值")
public class ResponseVal<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	@ApiModelProperty(value = "状态码")
    private int code;
	@ApiModelProperty(value = "信息")
    private String msg;
	@ApiModelProperty(value = "数据")
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

    public ResponseVal(String msg, T data) {
        super();
        this.code = 0;
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
