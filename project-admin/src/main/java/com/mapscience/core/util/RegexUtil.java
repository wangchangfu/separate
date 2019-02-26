package com.mapscience.core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <pre>
 * <b>正则表达式 辅助工具.</b>
 * <b>Description:</b> 
 *    
 * </pre>
 */
public abstract class RegexUtil {
	
	/** URL正则表达式.*/
	public static final String URL_MATCHER = "^(http|https)://([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$";
	
	/** 文件路径表达式. */
	public static final String FILE_MATCHER = "(^([\\/][\\w-]+.*(l)?)*$)|(^([a-zA-Z]\\:|\\\\)\\\\([^\\\\]+\\\\)*[^\\/:*?\"<>|]+\\.*(l)?$)";

    /**
     * 受保护的构造方法, 防止外部构建对象实例.
     */
    protected RegexUtil() {
        super();
    }

    /**
     * 验证给定的字符串是否符合正则表达式的格式要求.
     * 
     * @param str 给定的字符串.
     * @param pattern 指定正则表达式.
     * @return boolean, true:符合; false:不符合.
     */
    public static boolean isMatch(final String str, final Pattern pattern) {
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
    
    /**
     * 验证给定的字符串是否网络URL的格式要求.
     * 
     * @param str 给定的字符串.
     * @return boolean, true:符合; false:不符合.
     */
    public static boolean isUrl(String str){
    	Matcher matcher = Pattern.compile(URL_MATCHER).matcher(str);
        return matcher.matches();
    }
    
    /**
     * 验证给定的字符串是否网络文件目录路径的格式要求.
     * 
     * @param str 给定的字符串.
     * @return boolean, true:符合; false:不符合.
     */
    public static boolean isFilePath(String str){
    	Matcher matcher = Pattern.compile(FILE_MATCHER).matcher(str);
        return matcher.matches();
    }
}