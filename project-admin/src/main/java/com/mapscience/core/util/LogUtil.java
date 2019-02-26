package com.mapscience.core.util;

import com.mapscience.core.util.common.StringUtil;

import java.util.Collection;


/**
 * <pre>
 * <b>通用日志辅助工具.</b>
 * <b>Description:</b> 主要提供如下: 
 *    1、提供对日志输出进行做特殊标示;
 *    2、对需要日志输入的内容进行格式化转换操作;
 *    
 * </pre>
 */
public abstract class LogUtil {

    /**
     * KEY排版的宽度.
     */
    public static final int KEY_WIDTH = 16;

    /**
     * 分隔线: "|-------------...".
     */
    public static final String LINE = "|-----------------------------------------------------------------------------------------------------------";
    /**
     * 分隔线: "--------------...".
     */
    public static final String LINE2 = "------------------------------------------------------------------------------------------------------------";
    /**
     * 分隔线: "|   ----------...".
     */
    public static final String LINE3 = "|   --------------------------------------------------------------------------------------------------------";

    /**
     * 模块标记: "| ".
     */
    public static final String PREFIX1 = "| ";
    /**
     * 功能标记: "| @ ".
     */
    public static final String PREFIX2 = "| @ ";
    /**
     * 功能标记: "|   ".
     */
    public static final String PREFIX3 = "|   ";
    /**
     * 功能标记: "|     ".
     */
    public static final String PREFIX4 = "|     ";
    /**
     * 功能标记: "|       ".
     */
    public static final String PREFIX5 = "|       ";

    /**
     * 受保护的构造方法, 防止外部构建对象实例.
     */
    protected LogUtil() {
        super();
    }

    /**
     * 输出字符串（String）的调试信息.
     * 
     * @param name 属性名称.
     * @param source 对应参数实例.
     * @return String 字符串形式的信息.
     */
    public static String debugInfo(String name, Object source) {
        return debugInfo(name, source, KEY_WIDTH);
    }

    /**
     * 输出字符串（String）的调试信息.
     * 
     * @param name 属性名称.
     * @param source 对应参数实例.
     * @param nameLength 属性名称自动补充的长度.
     * @return String 字符串形式的信息.
     */
    public static String debugInfo(String name, Object source, int nameLength) {
        return StringUtil.keepLen(name, nameLength, " ", true) + "= " + ObjectUtil.toString(source, StringUtil.NULL_STR);
    }

    /**
     * 输出字符串（String）的调试信息.
     * 
     * @param name 属性名称.
     * @param source 对应参数实例.
     * @return String 字符串形式的信息.
     */
    public static String debugInfo(String name, Collection<?> source) {
        return debugInfo(name, source, KEY_WIDTH);
    }

    /**
     * 输出字符串（String）的调试信息.
     * 
     * @param name 属性名称.
     * @param source 对应参数实例.
     * @param nameLength 属性名称自动补充的长度.
     * @return String 字符串形式的信息.
     */
    public static String debugInfo(String name, Collection<?> source, int nameLength) {
        return StringUtil.keepLen(name, nameLength, " ", true) + "= " + ObjectUtil.toString(source, "{}");
    }
}
