/**
 * 
 */
package com.mapscience.core.getway.exception;


/**
 *说明：
 *<p>通用运行时异常 </p>
 * 书写者 @author  WCF
 * 创建时间 2018年12月3日
 *
 */
public class CommonException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7401732301738346408L;


    /**
     * 异常代码, 默认格式: PREFIX + 错误代码, 如：ER_UNKNOWN、ER_10301
     */
    protected int code = 500;

    /**
     * 引起相关异常的数据对象.
     */
    protected Object data;
    

	/**
     * 构造方法.
     * 
     * @param code 错误代码.
     */
    public CommonException(int code) {
		super();
		this.code = code;
	}

	/**
     * 构造方法.
     * 
     * @param cause 自定义通用异常实例.
     */
    public CommonException(CommonException cause) {
        super(cause);
        if (null != cause) {
            this.code = cause.getCode();
            this.data = cause.getData();
        }
    }

    /**
     * 构造方法.
     * 
     * @param code 错误代码.
     * @param message 自定义错误消息.
     * @param data 引起相关异常的数据对象.
     * @param cause 错误堆栈信息.
     */
    public CommonException(int code, String message, Object data, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.data = data;
    }

    /**
     * 返回 错误代码.
     * 
     * @param key 错误代码.
     */
    public int getCode() {
        return this.code;
    }

    /**
     * 返回 引起相关异常的数据对象.
     * 
     * @param clients 引起相关异常的数据对象.
     */
    public Object getData() {
        return this.data;
    }

    @Override
    public String toString() {
        String message = this.getMessage();
        return this.code + (null != message ? (":" + this.getMessage()) : "") + " ->" + String.valueOf(this.data);
    }
}
