package com.mapscience.core.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <pre>
 * <b>HTTP 请求的方法枚举.</b>
 * </pre>
 */
public enum Method {

    /** 1, "OPTIONS" */
    OPTIONS(1, "OPTIONS"),

    /** 2, "HEAD" */
    HEAD(2, "HEAD"),

    /** 3, "GET" */
    GET(3, "GET"),

    /** 4, "POST" */
    POST(4, "POST"),

    /** 5, "PUT" */
    PUT(5, "PUT"),

    /** 6, "DELETE" */
    DELETE(6, "DELETE"),

    /** 7, "TRACE" */
    TRACE(7, "TRACE");

    /** Key. */
    private int key;

    /** 名称. */
    private String label;

    /**
     * 构造方法.
     * 
     * @param key
     * @param label
     */
    private Method(int key, String label) {
        this.key = key;
        this.label = label;
    }

    /**
     * 转换对应枚举.
     * 
     * @param key
     * @return Method
     */
    public static Method format(Integer key) {
        if (null != key) {
            for (Method item : Method.values()) {
                if (key == item.key) {
                    return item;
                }
            }
        }
        return null;
    }

    /**
     * 获取清单.
     * 
     * @return Map<Object, String>
     */
    public static Map<Object, String> toMap() {
        Map<Object, String> map = new LinkedHashMap<Object, String>();

        for (Method item : Method.values()) {
            map.put(item.key, item.label);
        }

        return map;
    }

    /**
     * 获取二维数组.
     * 
     * @return Object[]
     */
    public static Object[] toArray() {
        Method[] values = Method.values();
        Object[] array = new Object[values.length];

        for (int i = 0; i < values.length; i++) {
            array[i] = new Object[] { values[i].key, values[i].label };
        }

        return array;
    }
}