/**
 * 
 */
package com.mapscience.core.getway;

import com.mapscience.core.getway.exception.CacheException;

import java.util.Date;


/**
 *说明：
 *<p>缓存服务接口 </p>
 * 书写者 @author  WCF
 * 创建时间 2018年12月3日
 *
 */
public interface Cache {

	
	 /**
     * 初始化方法.
     * 
     * CacheException
     */
    void init() throws CacheException;

    /**
     * 刷新缓存数据.
     * 
     *  CacheException
     */
    void refresh() throws CacheException;

    /**
     * 清空缓存数据.
     * 
     *  CacheException
     */
    void clean() throws CacheException;

    /**
     * 缓存销毁处理.
     */
    void destroy() throws CacheException;

    /**
     * 获取缓存中所有的数据.
     * 
     * @return Object
     */
    Object getData();

    /**
     * 获取缓存的唯一Key.
     * 
     * @return String
     */
    String getKey();

    /**
     * 获取缓存当前容量.
     * 
     * @return int
     */
    long getSize();

    /**
     * 返回 初始化缓存的时间(毫秒).
     * 
     * @return Date 初始化缓存的时间.
     */
    public Date getInitTimes();

    /**
     * 返回 刷新缓存时间(毫秒).
     * 
     * @return Date 刷新缓存时间.
     */
    public Date getRefreshTimes();

    /**
     * 返回 刷新缓存数据耗时(毫秒).
     * 
     * @return Long 刷新缓存数据耗时(毫秒).
     */
    public Long getUsedTimes();

}
