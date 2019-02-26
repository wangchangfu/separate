package com.mapscience.core.util;

/**
 * <pre>
 * <b>HTTP请求常用头消息.</b>
 * </pre>
 */
public abstract class Header {

    // 默认的内容类型: text/plain
    public static final String DEFAULT_CONTENTTYPE = "text/plain";

    // 可接收数据、内容类型: */*
    // */*
    public static final String DEFAULT_ACCEPT = "*/*";

    // 可接收数据、内容类型: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
    public static final String DEFAULT_ACCEPT_TEXT = "text/html,application/json,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8";

    // 支持的数据压缩算法: gzip,deflate,sdch
    public static final String DEFAULT_ACCEPT_ENCODING = "gzip,deflate,sdch";

    // 可接收语言内容: zh-CN,zh;q=0.8,en;q=0.6
    public static final String DEFAULT_ACCEPT_LANGUAGE = "zh-CN,zh;q=0.8,en;q=0.6";

    // 缓存控制: no-cache
    public static final String DEFAULT_CACHE_CONTROL = "no-cache";

    // Socket连接保持方式: keep-alive
    public static final String DEFAULT_CONNECTION = "Keep-Alive";

    // 客户端浏览器类型:
    // Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like
    // Gecko) Chrome/33.0.1720.0 Safari/537.36
    public static final String DEFAULT_USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1720.0 Safari/537.36";
}
