/**
 * 
 */
package com.mapscience.core.getway;

import com.alibaba.fastjson.annotation.JSONField;
import com.mapscience.core.getway.exception.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *说明：
 *<p>通用缓存服务 </p>
 * 书写者 @author  WCF
 * 创建时间 2018年12月3日
 *
 */
public abstract class CommonCache implements Cache{


    /** 唯一Key. */
    protected String key;

    /** 总记录数. */
    protected long size;

    /** 备注. */
    protected String remark;

    /** 初始化缓存的时间(毫秒). */
    protected Long initTimes;

    /** 刷新缓存时间(毫秒). */
    protected Long refreshTimes;

    /** 刷新缓存数据耗时(毫秒). */
    protected Long usedTimes;

    /** 定义数据读写所锁. */
    protected ReadWriteLock LOCK = new ReentrantReadWriteLock();

    /** 日志记录器. */
    protected Logger LOGGER = LoggerFactory.getLogger(CommonCache.class);

    /**
     * 构造方法.
     * 
     * @param key 缓存器唯一Key.
     */
    public CommonCache(String key) {
        super();
        this.key = key;
        LOGGER.debug("instance " + this.toString() + " cacheKey:" + this.key);
    }

    @Override
    public void init() throws CacheException {
        // 数据初始化时，需要进行线程同步锁定.
        LOCK.writeLock().lock();
        try {
            LOGGER.info(this.toString() + " cacheKey:" + this.key + " init ...");
            // 设置缓存初始化的时间.
            this.initTimes = System.currentTimeMillis();
            // 执行加载数据
            this.load();
            // 计算缓存初始化总计耗时时长, 单位: 毫秒.
            this.usedTimes = System.currentTimeMillis() - this.initTimes;
        } finally {
            LOCK.writeLock().unlock();
        }
    }

    @Override
    public void refresh() throws CacheException {
        // 数据刷新（覆盖）时，需要进行线程同步锁定.
        LOCK.writeLock().lock();
        try {
            LOGGER.info(this.toString() + " cacheKey:" + this.key + " refresh ...");
            this.size = 0L;
            // 设置设置缓存初始化的时间.
            this.initTimes = System.currentTimeMillis();
            // 设置缓存刷新的时间.
            this.refreshTimes = System.currentTimeMillis();
            // 执行重新加载数据
            this.load();
            // 计算缓存刷新总计耗时时长, 单位: 毫秒.
            this.usedTimes = System.currentTimeMillis() - this.refreshTimes;
        } finally {
            LOCK.writeLock().unlock();
        }
    }

    /**
     * 加载缓存数据.
     * 
     * @throws CacheException
     */
    protected abstract void load() throws CacheException;

    @Override
    public void clean() throws CacheException {
        // 数据刷新（覆盖）时，需要进行线程同步锁定.
        LOCK.writeLock().lock();
        try {
            LOGGER.info(this.toString() + " cacheKey:" + this.key + " clean ...");
            this.size = 0L;
            // 设置缓存刷新的时间.
            this.refreshTimes = System.currentTimeMillis();
            // 计算缓存刷新总计耗时时长, 单位: 毫秒.
            this.usedTimes = System.currentTimeMillis() - this.refreshTimes;
        } finally {
            LOCK.writeLock().unlock();
        }
    }

    @Override
    public void destroy() throws CacheException {
        // 数据刷新（覆盖）时，需要进行线程同步锁定.
        LOCK.writeLock().lock();
        try {
            LOGGER.info(this.toString() + " cacheKey:" + this.key + " destroy ...");
        } finally {
            LOCK.writeLock().unlock();
        }
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public long getSize() {
        return this.size;
    }

    @Override
    @JSONField(format = "yyyy-MM-dd hh:mm:ss.SSS")
    public Date getInitTimes() {
        return new Date(this.initTimes);
    }

    @Override
    @JSONField(format = "yyyy-MM-dd hh:mm:ss.SSS")
    public Date getRefreshTimes() {
        return (null == this.refreshTimes ? null : new Date(this.refreshTimes));
    }

    @Override
    public Long getUsedTimes() {
        return this.usedTimes;
    }
}
