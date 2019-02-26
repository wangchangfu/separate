/**
 * 
 */
package com.mapscience.core.getway.exception;

/**
 *说明：
 *<p> 缓存服务异常</p>
 * 书写者 @author  WCF
 * 创建时间 2018年12月3日
 *
 */
public class CacheException extends CommonException{


    // 序列化版本标示.
    private static final long serialVersionUID = 1L;

    /**
     * 构造方法.
     * 
     * @param cause 自定义通用异常实例.
     */
    public CacheException(CommonException cause) {
        super(cause);
    }

    /**
     * 构造方法.
     * 
     * @param code 错误代码.
     * @param message 自定义错误消息.
     * @param data 引起相关异常的数据对象.
     * @param cause 错误堆栈信息.
     */
    public CacheException(int code, String message, Object data, Throwable cause) {
        super(code, message, data, cause);
    }

}
