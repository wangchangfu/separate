package com.mapscience.core.util;

import com.alibaba.fastjson.annotation.JSONField;
import com.mapscience.core.util.common.StringUtil;

import java.io.Serializable;

/**
 * <pre>
 * <b>接口响应数据 封装模型.</b>
 * <b>Description:</b> 
 *    
 * <b>Author:</b> WCF
 * <b>Date:</b> 
 * <b>Copyright:</b> Copyright ©2006-2015  Technology Co., Ltd. All rights reserved.
 * <b>Changelog:</b>
 *   Ver   Date                  Author              Detail
 *   ----------------------------------------------------------------------
 *   1.0   2014-01-01 10:00:01   wcf             new file.
 * </pre>
 */
public class Result implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
     * 结果代码, 一般默认：200 为成功, -200为异常.
     */
    protected int code;

    /**
     * 结果对象, 当success==true, data方可有效.
     */
    protected Object data = new Object();
    
    /***
     * 数据总数
     */
    protected int count;

    /**
     * 结果消息.
     */
    protected String message;

    /**
     * 异常信息实例.
     */
    @JSONField(serialize = false)
    protected Throwable cause;
    
    /** 数据校验值. */
    @JSONField(serialize = false)
    protected String mac;
    
    /**
     * 构造方法
     */
    public Result() {
        this.code = 200;
    }
    
    public Result(final Message message){
    	this(message.CODE, message.MESSAGE);
    }
    
    public Result(final Message message, Object data){
    	this(message.CODE, data, message.MESSAGE, null);
    }

    /**
     * 构造方法
     * 
     * @param code 响应状态码
     */
    public Result(final int code) {
        this(code, null, null, null);
    }

    /**
     * 构造方法, 用于正常
     * 
     *  result 结果对象
     */
    public Result(final Object data) {
        this(200, data, null, null);
    }

    /**
     * 构造方法, 用于正常
     * 
     * @param code 响应状态码
     * result 结果对象
     */
    public Result(final int code, final Object data) {
        this(code, data, null, null);
    }

    /**
     * 构造方法, 用于失败
     * 
     * @param code 响应状态码
     * @param message
     */
    public Result(final int code, final String message) {
        this(code, null, message, null);
    }

    /**
     * 构造方法, 用于失败.
     * 
     * @param code 响应状态码
     * @param message
     * @param cause
     */
    public Result(final int code, final String message, final Throwable cause) {
        this(code, null, message, cause);
    }

    /**
     * 构造方法, 用于自定义结果信息.
     * 
     * @param code
     * @param data
     * @param message
     * @param cause
     */
    public Result(final int code, final Object data, final String message, final Throwable cause) {
        super();
        this.data = data;
        this.code = code;
        this.message = message;
        this.cause = cause;
    }

    /**
     * 获取 结果代码.
     * 
     * @return
     */
    public int getCode() {
        return code;
    }

    /**
     * 设置 结果代码, 具有覆盖性.
     * 
     *  message
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * 返回 结果对象.
     * 
     * @return Object
     */
    public Object getData() {
        return data;
    }

    /**
     * 返回 结果对象并转为指定类型.
     * 
     * @param <T> 指定类型
     * @param clazz 指定类型
     * @return T
     */
    public <T> T getData(final Class<T> clazz) {
        return (null != data ? clazz.cast(data) : null);
    }

    /**
     * 设置结果.
     * 
     * @param data
     */
    public void setData(final Object data) {
        this.data = data;
    }

    /**
     * 设置 结果消息, 具有覆盖性.
     * 
     * message
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置 结果消息, 具有覆盖性.
     * 
     * @param message
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * 在原有message 前面追加结果信息.
     * 
     * @param message
     */
    public void prependMessage(final String message) {
        this.message = (StringUtil.hasLength(message) ? message : "") + ", " + this.message;
    }

    /**
     * 在原有message 后面附加结果信息.
     * 
     * @param message
     */
    public void appendMessage(final String message) {
        this.message = this.message + ", " + (StringUtil.hasLength(message) ? message : "");
    }

    /**
     * 获取 异常实例.
     * 
     * @return
     */
    public Throwable getCause() {
        return cause;
    }
    
    /**
     * <pre>
     * <b>判断是否成功.</b>
     * <p>this.code == 200.</p>
     * @return
     * </pre>
     */
    public boolean isSuccess(){
    	return this.code == 200;
    }

    /**
     * 设置 异常实例
     * 
     * @param cause
     */
    public void setCause(final Throwable cause) {
        this.cause = cause;
    }

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	

}
