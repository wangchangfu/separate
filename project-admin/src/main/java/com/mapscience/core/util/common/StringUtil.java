package com.mapscience.core.util.common;

import com.mapscience.core.util.ObjectUtil;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * <pre>
 * <b>系统操作 辅助工具.</b>
 * <b>Description:</b> 
 *    
 */
public abstract class StringUtil {

    /** 常量, UTF-8 编码. */
    public static final String ENCODING = "UTF-8";

    /** 常量, 字符集编码, 默认: UTF-8. */
    public static final Charset CHARSET = Charset.forName(ENCODING);

    /** 常量, 空字符串: "". */
    public static final String EMPTY_STR = "";

    /** 常量, 字符串NULL转义表达式: "&ltNULL&gt". */
    public static final String NULL_STR = "<NULL>";

    /** 常量, 空的二进制数组, new byte[] {} */
    public static final byte[] EMPTY_BYTE = new byte[] {};

    /** 字符串默认分割符: 英文逗号 ",". */
    public static final String SEPARATOR = ",";

    /**
     * 受保护的构造方法, 防止外部构建对象实例.
     */
    protected StringUtil() {
        super();
    }

    // -------------
    // ------------- General convenience methods for working with Strings
    /**
     * <pre>
     * 检查给出的字符串是否有长度.
     * 
     * 如果给出的字符串为null 或 "", 则返回false.
     * 例如: 
     * StringUtil.hasLength(null)   == false;
     * StringUtil.hasLength("")     == false;
     * StringUtil.hasLength(" ")    == true;
     * StringUtil.hasLength("　")   == true;
     * StringUtil.hasLength("abc")  == true;
     * StringUtil.hasLength("abc ") == true;
     * StringUtil.hasLength(" abc") == true;
     * </pre>
     * 
     * @param cs 给出的字符串.
     * @return boolean 是否有长度.
     */
    public static boolean hasLength(CharSequence cs) {
        // 主要判断字符串对象不为null, 同时其字符长度大于等于 1, 则返回true
        return (null != cs && 1 <= cs.length());
    }

    /**
     * <pre>
     * 检查给出的字符串是否至少拥有一个非空格（包括中文空格）字符.
     * 
     * 如果给出的字符串为null 或 "", 则返回false.
     * 例如: 
     * StringUtil.hasText(null)    == false;
     * StringUtil.hasText("")      == false;
     * StringUtil.hasText(" ")     == false;
     * StringUtil.hasText("　")    == false;
     * StringUtil.hasText("abc")   == true;
     * StringUtil.hasText("abc")   == true;
     * StringUtil.hasText(" abc ") == true;
     * </pre>
     * 
     * @param str 给出的字符串.
     * @return boolean 是否至少拥有一个非空格（包括中文空格）字符.
     * @see Character#isWhitespace
     */
    public static boolean hasText(CharSequence str) {
        int len = 0;
        if (null == str || 0 == (len = str.length())) {
            return false;
        }

        // 依次判断每个字符是否为空格（中文）, 如果为空格则返回false.
        for (int i = 0; i < len; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    // -------------
    // ------------- Convenience methods for working with formatted Strings
    /**
     * <pre>
     * 将字符串的首字母变为大写, 其他字符不变.
     * 如果字符串为null 或 长度==0, 则直接返回该字符串.
     * </pre>
     * 
     * @param str 给出的字符串, 可以为null.
     * @return String 变换后的字符串.
     */
    public static String capitalize(String str) {
        if (null == str || 0 == str.length()) {
            return str;
        }

        StringBuffer sb = new StringBuffer(str.length());
        sb.append(Character.toUpperCase(str.charAt(0)));
        sb.append(str.substring(1));
        return sb.toString();
    }

    /**
     * <pre>
     * 将字符串的首字母变为小写, 其他字符不变.
     * 如果字符串为null 或 长度==0, 则直接返回该字符串.
     * </pre>
     * 
     * @param str 给出的字符串, 可以为null.
     * @return String 变换后的字符串.
     */
    public static String uncapitalize(String str) {
        if (null == str || 0 == str.length()) {
            return str;
        }

        StringBuffer sb = new StringBuffer(str.length());
        sb.append(Character.toLowerCase(str.charAt(0)));
        sb.append(str.substring(1));
        return sb.toString();
    }

    /**
     * <pre>
     * 检查给出的两个字符串是否内容（忽略大小写）相同, 
     * 任意一个字符串为Null时, 返回false.
     * 
     * 如：
     * StringUtil.equalsWith("", "")       = true
     * StringUtil.equalsWith(" ", " ")     = true
     * StringUtil.equalsWith("", " ")      = false
     * StringUtil.equalsWith("abc", "abc") = true
     * StringUtil.equalsWith("aBc", "AbC") = true
     * StringUtil.equalsWith("aBc", null)  = false
     * </pre>
     * 
     * @param str1 给出第一个的字符串. 如果为null, 则返回false.
     * @param str2 给出第二个的字符串. 如果为null, 则返回false.
     * @return boolean 是否内容相同.
     */
    public static boolean equals(String str1, String str2) {
        return equals(str1, str2, true);
    }

    /**
     * <pre>
     * 检查给出的两个字符串是否内容相同,  验证时指定是否区分大小写, 
     * 当任意一个字符串为Null时, 返回false.
     * 
     * 如：
     * StringUtil.equalsWith("aBc", "AbC", fasle) = fasle
     * StringUtil.equalsWith("aBc", null, true)   = false
     * </pre>
     * 
     * @param str1 给出的第一个字符串. 如果为null, 则返回false.
     * @param str2 给出的第二个字符串. 如果为null, 则返回false.
     * @param ignoreCase 是否忽略字符大小写进行匹配, true:区分大小写；false:忽略大小写.
     * @return boolean 是否内容相同.
     */
    public static boolean equals(String str1, String str2, boolean ignoreCase) {
        if (null == str1 || null == str2) {
            return false;
        }

        // 判断是否需要忽略大小写进行比较
        if (ignoreCase) {
            str1 = str1.toLowerCase(Locale.ENGLISH);
            str2 = str2.toLowerCase(Locale.ENGLISH);
        }
        return str1.equals(str2);
    }

    /**
     * <pre>
     * 检查给出的字符串是否以特定的字符前缀（忽略大小写）开始.
     * 
     * 如果给出的字符串为null 或 "", 则返回false, 为""时是因长度小于前缀;
     * 如果特定的前缀为null 或 "", 则返回false;
     * 如果给出的字符串长度小于特定的字符前缀长度时, 则返回false.
     * 例如: 
     * StringUtil.startsWith("abc", null)   == false;
     * StringUtil.startsWith("abc", " ")    == false;
     * StringUtil.startsWith("abc", "　")   == false;
     * StringUtil.startsWith("abc", "aBc ") == false;
     * StringUtil.startsWith("abc", " aBc") == false;
     * StringUtil.startsWith("abc", "")     == true;
     * StringUtil.startsWith("abc", "abc")  == false;
     * </pre>
     * 
     * @param str 给出的字符串.
     * @param prefix 特定的前缀.
     * @return boolean 是否为前缀.
     * @see String#startsWith
     */
    public static boolean startsWith(String str, String prefix) {
        return startsWith(str, prefix, false);
    }

    /**
     * <pre>
     * 检查给出的字符串是否以特定的字符前缀开始, 验证时需指定是否区分大小写.
     * 
     * 如果给出的字符串为null 或 "", 则返回false, 为""时是因长度小于前缀;
     * 如果特定的前缀为null 或 "", 则返回false;
     * 如果给出的字符串长度小于特定的字符前缀长度时, 则返回false.
     * 例如: 
     * StringUtil.startsWith(" abc", "aBc", true) == false;
     * StringUtil.startsWith("abc", null, true)   == false;
     * StringUtil.startsWith("abc", " ", true)    == false;
     * StringUtil.startsWith("abc", "　", true)   == false;
     * StringUtil.startsWith("abc", "", true)     == true;
     * StringUtil.startsWith("abc", "abc", true)  == true;
     * </pre>
     * 
     * @param str 给出的字符串.
     * @param prefix 特定的前缀.
     * @param ignoreCase 是否忽略字符大小写进行匹配, true: 区分大小写; false: 忽略大小写.
     * @return boolean 是否为前缀.
     * @see String#startsWith
     */
    public static boolean startsWith(String str, String prefix, boolean ignoreCase) {
        if (null == str || null == prefix) {
            return false;
        }
        if (str.startsWith(prefix)) {
            return true;
        }
        if (str.length() < prefix.length()) {
            return false;
        }

        // 截取被检查字符串头部与前缀长度相等长度的字符串.
        String sub = str.substring(0, prefix.length());
        // 判断是否需要忽略大小写进行比较.
        if (ignoreCase) {
            sub = sub.toLowerCase(Locale.ENGLISH);
            prefix = prefix.toLowerCase(Locale.ENGLISH);
        }
        // 判断截取的头部字符串部分是否与前缀相同, 即为此前缀开始的
        return sub.equals(prefix);
    }

    /**
     * <pre>
     * 检查给出的字符串是否以特定的字符后缀（忽略大小写）结尾.
     * 
     * 如果给出的字符串为null 或 "", 则返回false, 为""时是因长度小于后缀;
     * 如果特定的后缀为null 或 "", 则返回false;
     * 如果给出的字符串长度小于特定的字符后缀长度时, 则返回false.
     * 例如: 
     * StringUtil.endsWith("abc", "")     == true;
     * StringUtil.endsWith("aBc", "aBc")  == true);
     * StringUtil.endsWith("abc", " ")    == false;
     * StringUtil.endsWith("abc", "　")   == false;
     * StringUtil.endsWith("abc", "aBc ") == false;
     * </pre>
     * 
     * @param str 给出的字符串.
     * @param suffix 特定的后缀.
     * @return boolean 是否为后缀.
     * @see String#endsWith
     */
    public static boolean endsWith(String str, String suffix) {
        return endsWith(str, suffix, false);
    }

    /**
     * <pre>
     * 检查给出的字符串是否以特定的字符后缀结尾, 验证时需指定是否区分大小写.
     * 
     * 如果给出的字符串为null 或 "", 则返回false, 为""时是因长度小于后缀;
     * 如果特定的后缀为null 或 "", 则返回false;
     * 如果给出的字符串长度小于特定的字符后缀长度时, 则返回false.
     * 例如: 
     * StringUtil.startsWith(" abc", "aBc", true) == false;
     * StringUtil.startsWith("abc", null, true)   == false;
     * StringUtil.startsWith("abc", " ", true)    == false;
     * StringUtil.startsWith("abc", "　", true)   == false;
     * StringUtil.startsWith("abc ", "aBc", true) == true;
     * StringUtil.startsWith("abc", "", true)     == true;
     * StringUtil.startsWith("abc", "abc", true)  == true;
     * </pre>
     * 
     * @param str 给出的字符串.
     * @param suffix 特定的后缀.
     * @param ignoreCase 是否忽略字符大小写进行匹配, true: 区分大小写; false: 忽略大小写.
     * @return boolean 是否为后缀.
     * @see String#endsWith
     */
    public static boolean endsWith(String str, String suffix, boolean ignoreCase) {
        if (null == str || null == suffix) {
            return false;
        }
        if (str.endsWith(suffix)) {
            return true;
        }
        if (str.length() < suffix.length()) {
            return false;
        }
        // 截取被检查字符串末尾与后缀长度相等长度的字符串.
        String sub = str.substring(str.length() - suffix.length());
        // 判断是否需要忽略大小写进行比较
        if (ignoreCase) {
            sub = sub.toLowerCase(Locale.ENGLISH);
            suffix = suffix.toLowerCase(Locale.ENGLISH);
        }
        // 判断截取的结尾字符串部分是否与后缀相同, 即为此后缀开始的
        return sub.equals(suffix);
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
     * <pre>
     * 检查给出的字符串中含有关键词（特定的子串）（忽略大小写）出现的次数.
     * 
     * 如果任何一个参数为null或长度为0时, 则直接返回0.
     * 例如: 
     * StringUtil.countKey(null, "Bc")        = 0
     * StringUtil.countKey("abc", null)       = 0
     * StringUtil.countKey("abc", "Bc")       = 0
     * StringUtil.countKey("abclcbcdc", "bc") = 2
     * </pre>
     * 
     * @param str 给出的字符串.
     * @param fix 特定的子串.
     * @return int 出现的次数
     */
    public static int countKey(String str, String fix) {
        return countKey(str, fix, false);
    }

    /**
     * <pre>
     * 检查给出的字符串中含有关键词（特定的子串）出现的次数, 统计时需指定是否区分大小写.
     * 
     * 如果任何一个参数为null或长度为0时, 则直接返回0.
     * 例如: 
     * StringUtil.countKey(null, "Bc", true)        = 0
     * StringUtil.countKey("abc", null, true)       = 0
     * StringUtil.countKey("abc", "Bc", true)       = 0
     * StringUtil.countKey("abclcBcdc", "bc", true) = 1
     * </pre>
     * 
     * @param str 给出的字符串.
     * @param fix 特定的子串.
     * @param ignoreCase 是否忽略字符大小写进行匹配, true: 区分大小写; false: 忽略大小写.
     * @return int 出现的次数
     */
    public static int countKey(String str, String fix, boolean ignoreCase) {
        if (null == str || null == fix || 0 == str.length() || fix.length() == 0) {
            return 0;
        }

        // 判断是否需要忽略大小写进行统计
        if (ignoreCase) {
            str = str.toLowerCase(Locale.ENGLISH);
            fix = fix.toLowerCase(Locale.ENGLISH);
        }
        int count = 0, index = 0, pos = 0;
        while ((index = str.indexOf(fix, pos)) != -1) {

            ++count;
            pos = index + fix.length();
        }
        return count;
    }

    /**
     * <pre>
     * 将给出的字符串中关键词（特定的子串）全部替换为另一个关键词（特定的子串）.
     * 
     * 如果给出的字符串为null, 则直接返回 给出的字符串;
     * 如果被匹配关键词（特定的子串）和替换关键词（特定的子串）, 任何一个为null时, 则直接返回 null.
     * 例如:
     * StringUtil.replace(null, "Aa", "")               = null
     * StringUtil.replace("", "Aa", "")                 = ""
     * StringUtil.replace("Aa", null, "")               = "Aa"
     * StringUtil.replace("Aa", "", null)               = "Aa"
     * StringUtil.replace("Aa", "", "a")                = "Aa"
     * StringUtil.replace("abcccacbcsercb", "bc", "--") = "a--ccac--sercb"
     * StringUtil.replace("abcccacbcsercb", "bc", "")   = "accacsercb"
     * </pre>
     * 
     * @param str 给出的字符串.
     * @param oldPattern 被匹配关键词（特定的子串）
     * @param newPattern 替换关键词（特定的子串）
     * @return String 替换后的字符串
     */
    public static String replace(String str, String oldPattern, String newPattern) {
        if (null == str || null == oldPattern || null == newPattern || oldPattern.length() == 0) {
            return str;
        }

        int pos = 0;
        int patLen = oldPattern.length();
        int index = str.indexOf(oldPattern);
        StringBuffer sb = new StringBuffer();
        while (index >= 0) {
            sb.append(str.substring(pos, index));
            sb.append(newPattern);
            pos = index + patLen;
            index = str.indexOf(oldPattern, pos);
        }
        sb.append(str.substring(pos));
        return sb.toString();
    }
    
    /**
	 * <pre>
	 * <b>将带有EL表达式字符串解析成参数中的值.</b>
	 * 例：str = 这是${text}数据, params = {text: "测试"} return 这是测试数据
	 * @param elStr
	 * @param params
	 * @return
	 * </per>
	 */
	public static String replaceEl(String elStr, Map<String, String> params){
		return replaceEl(elStr, params, "${", "}");
	}
	/**
	 * <pre>
	 * <b>将带有EL表达式字符串解析成参数中的值.</b>
	 * 例：str = 这是${text}数据, params = {text: "测试"} return 这是测试数据
	 * @param elStr
	 * @param params
	 * @param startSeparator 开始分隔符 eq:##
	 * @param endSeparator 结束分隔符 eq:##
	 * @return
	 * </per>
	 */
	public static String replaceEl(String elStr, Map<String, String> params, String startSeparator, String endSeparator){
		if(ObjectUtil.isEmpty(elStr)){
			return null;
		}
		String result = "";
		String tempStr = elStr;
		while(true){
			int beginIndex = tempStr.indexOf(startSeparator);
			int endIndex = tempStr.indexOf(endSeparator);
			if(beginIndex == -1 && endIndex == -1){
				return result;
			}
			result += tempStr.substring(0, beginIndex);
			if(params.containsKey(tempStr.substring(beginIndex + startSeparator.length(), endIndex))){
				result += params.get(tempStr.substring(beginIndex + startSeparator.length(), endIndex));
			}
			tempStr = tempStr.substring(endIndex + endSeparator.length());
			if(tempStr.indexOf(startSeparator) == -1){
				result += tempStr;
				break;
			}
		}
		return result;
	}

	/**
	 * <pre>
	 * <b>将带有EL表达式字符串解析成参数中的值.</b>
	 * 例：str = 这是${text}数据, params = {text: "测试"} return 这是测试数据
	 * @param elStr
	 * @param
	 * @return
	 * </per>
	 */
	public static String replaceEl(String elStr, String...values){
		if(ObjectUtil.isEmpty(elStr)){
			return null;
		}
		String result = "";
		String tempStr = elStr;
		int index = 0;
		while(true){
			int beginIndex = tempStr.indexOf("${");
			int endIndex = tempStr.indexOf("}");
			if(beginIndex == -1 && endIndex == -1){
				return result;
			}
			result += tempStr.substring(0, beginIndex);
			result += values[index];
			index++;
			tempStr = tempStr.substring(endIndex + 1);
			if(tempStr.indexOf("${") == -1){
				result += tempStr;
				break;
			}
		}
		return result;
	}
	
	/**
	 * <pre>
	 * <b>将字符串转成unicode.</b>
	 * @param str 待转字符串
	 * @return unicode字符串
	 * </per>
	 */
	public static String convert(String str) {
		str = (str == null ? "" : str);
		String tmp;
		StringBuffer sb = new StringBuffer(1000);
		char c;
		int i, j;
		sb.setLength(0);
		for (i = 0; i < str.length(); i++) {
			c = str.charAt(i);
			sb.append("\\u");
			j = (c >>> 8); // 取出高8位
			tmp = Integer.toHexString(j);
			if (tmp.length() == 1)
				sb.append("0");
			sb.append(tmp);
			j = (c & 0xFF); // 取出低8位
			tmp = Integer.toHexString(j);
			if (tmp.length() == 1)
				sb.append("0");
			sb.append(tmp);

		}
		return (new String(sb));
	}

    /**
     * <pre>
     * 将给出的字符串中关键词（特定的子串）部分清洗掉, 
     * 任何一个参数为null时, 则直接返回给出的字符串. 
     * 
     * StringUtil.replace("abcccacbcsercb", "bc") = "accacsercb"
     * StringUtil.replace("abcccacbcsercb", "mm") = "abcccacbcsercb"
     * </pre>
     * 
     * @param str 给出的字符串. 如果为null, 则返回null.
     * @param pattern 被匹配关键词（特定的子串）.
     * @return String 替换后的字符串.
     */
    public static String delete(String str, String pattern) {
        return replace(str, pattern, "");
    }

    /**
     * <pre>
     * 将给出的字符串种的每个字符与需要匹配删除的字符集合进行比对清洗, 
     * 任何一个参数为null时, 则直接返回给出的字符串. 
     * 
     * StringUtil.deleteChar("abcccacbcsercb", "bc") = "aaser"
     * StringUtil.deleteChar("abcccacbcsercb", "mm") = "abcccacbcsercb"
     * </pre>
     * 
     * @param str 给出的字符串.
     * @param chars 需要从字符串中删除的字符集合.
     * @return String 删除匹配字符后的字符串.
     */
    public static String deleteChar(String str, String chars) {
        if (null == str || null == chars) {
            return str;
        }

        StringBuffer out = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (chars.indexOf(c) == -1) {
                out.append(c);
            }
        }
        return out.toString();
    }

    /**
     * <pre>
     * 将给出的字符串用特定的分隔符字符串中的字符拆分成字符串数组. 
     * 使用StringTokenizer类. 
     * trim每个拆分后的结果字符串, 忽略空的拆分结果字符串.
     * </pre>
     * 
     * @param str 给出的字符串.
     * @param separators 分隔符字符集合, 其中的每个字符都是分隔符.
     * @return List<String> 拆分后的字符串集合.
     * @see StringTokenizer
     * @see String#trim
     * @see #
     */
    public static List<String> tokenize(String str, String separators) {
        return tokenize(str, separators, true, true);
    }

    /**
     * <pre>
     * 将给出的字符串用特定的分隔符字符串中的字符拆分成字符串数组.
     * 使用StringTokenizer类.
     * </pre>
     * 
     * @param str 给出的字符串.
     * @param separators 分隔符字符集合, 其中的每个字符都是分隔符.
     * @param trimTokens 是否需要trim拆分后的结果.
     * @param ignoreEmptyTokens 是否忽略空的拆分结果.
     * @return List<String> 拆分后的字符集合.
     * @see StringTokenizer
     * @see String#trim
     * @see #
     */
    public static List<String> tokenize(String str, String separators, boolean trimTokens, boolean ignoreEmptyTokens) {
        StringTokenizer st = new StringTokenizer(str, separators);
        List<String> tokens = new ArrayList<String>();

        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if (trimTokens) {
                token = token.trim();
            }
            if (!ignoreEmptyTokens || token.length() > 0) {
                tokens.add(token);
            }
        }
        return tokens;
    }

    /**
     * <pre>
     * 将一个字符串集合对象变换为字符串数组对象.
     * 
     * 如果提供的集合为null, 则直接返回null.
     * </pre>
     * 
     * @param collection 字符串集合对象, 可以为null.
     * @return String[] 字符串数组对象, 有可能为null.
     */
    public static String[] toArray(Collection<String> collection) {
        if (null == collection) {
            return null;
        }

        String[] array = new String[collection.size()];
        return (String[]) collection.toArray(array);
    }

    /**
     * 将以逗号为分隔符的字符串拆分成字符串数组.
     * 
     * @param str 以逗号分隔的字符串, 可以为null.
     * @return String[] 拆分后的字符串数组.
     */
    public static String[] delimitedToArray(String str) {
        return delimitedToArray(str, SEPARATOR);
    }

    /**
     * <pre>
     * 将给出的字符串用特定的分隔符拆分成字符串数组.
     *  
     * 如果给出的字符串为null, 则返回String[0];
     * 如果分隔符为null, 则返回以源字符串为唯一元素的字符串数组;
     * 如果分隔符为"", 则将源字符串按照每个字符拆分成字符串数组;
     * 否则, 用分隔符拆分源字符串.
     * </pre>
     * 
     * @param str 给出的字符串, 可以为null.
     * @param separator 分隔符可以为null和"".
     * @return 拆分后的字符串数组.
     * @see #
     */
    public static String[] delimitedToArray(String str, String separator) {
        if (null == str) {
            return new String[0];
        }
        if (null == separator) {
            return new String[] { str };
        }

        List<String> result = new ArrayList<String>();
        if ("".equals(separator)) {
            for (int i = 0; i < str.length(); i++) {
                result.add(str.substring(i, i + 1));
            }
        } else {
            int index = 0;
            int delIndex = 0;
            while ((delIndex = str.indexOf(separator, index)) != -1) {
                result.add(str.substring(index, delIndex));
                index = delIndex + separator.length();
            }
            if (str.length() > 0 && index <= str.length()) {
                // Add rest of String, but not in case of empty input.
                result.add(str.substring(index));
            }
        }
        return toArray(result);
    }

    /**
     * <pre>
     * 将以逗号为分隔符的字符串拆分成集合对象（Set&lt;String&gt;） 将忽略重复的元素.
     *  
     * 如果给出的字符串为null, 则返回Set[0].
     * </pre>
     * 
     * @param str 以逗号为分隔符的字符串, 可以为null.
     * @return Set&lt;String&gt; 集合对象实例.
     */
    public static Set<String> delimitedToSet(String str) {
        return delimitedToSet(str, SEPARATOR);
    }

    /**
     * <pre>
     * 将以逗号为分隔符的字符串拆分成集合对象（Set&lt;String&gt;） 将忽略重复的元素.
     *  
     * 如果给出的字符串为null, 则返回Set[0];
     * 如果分隔符为null, 则返回以源字符串为唯一元素的字符串数组;
     * 如果分隔符为"", 则将源字符串按照每个字符拆分成字符串数组;
     * 否则, 用分隔符拆分源字符串.
     * </pre>
     * 
     * @param str 以逗号为分隔符的字符串, 可以为null.
     * @param separator 分隔符可以为null和"".
     * @return Set&lt;String&gt; 集合对象实例.
     */
    public static Set<String> delimitedToSet(String str, String separator) {
        Set<String> set = new TreeSet<String>();
        String[] tokens = delimitedToArray(str, separator);

        for (int i = 0; i < tokens.length; i++) {
            set.add(tokens[i]);
        }
        return set;
    }

    /**
     * 将字符串数组组装成以逗号（","）为分隔符的字符串
     * 
     * @param array 字符串数组
     * @return 组装后的字符串
     */
    public static String toDelimitedString(Object[] array) {
        return toDelimitedString(array, SEPARATOR);
    }

    /**
     * <pre>
     * 将对象数组组装成以特定分隔符分隔的字符串.使用toString方法获得字符串
     * </pre>
     * 
     * @param arr 对象数组
     * @param separator 分隔符
     * @return String 组装后的字符串
     */
    public static String toDelimitedString(Object[] arr, String separator) {
        if (null == arr) {
            return "";
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) {
                sb.append(separator);
            }
            sb.append(arr[i]);
        }
        return sb.toString();
    }

    /**
     * 将字符串集合对象组装成以逗号（","）为分隔符的字符串
     * 
     * @param coll 字符串集合对象
     * @return 组装后的字符串
     */
    public static String toDelimitedString(Collection<?> coll) {
        return toDelimitedString(coll, SEPARATOR);
    }

    /**
     * <pre>
     * 将集合对象的每个元素组装成以特定分隔符分隔的字符串.
     * 使用toString方法获得字符串.
     * </pre>
     * 
     * @param coll 集合对象实例, 可以为null.
     * @param separator 分隔符.
     * @return String 组装后的字符串, 一定不是null.
     */
    public static String toDelimitedString(Collection<?> coll, String separator) {
        return toDelimitedString(coll, separator, "", "");
    }

    /**
     * <pre>
     * 将集合对象的每个元素组装成以特定分隔符分隔的字符串,  每个元素可以加上特定的前缀和后缀字符串. 
     * 使用toString方法获得字符串.
     * </pre>
     * 
     * @param coll 集合对象实例, 可以为null.
     * @param separator 分隔符.
     * @param prefix 前缀字符串.
     * @param suffix 后缀字符串.
     * @return String 组装后的字符串, 一定不是null
     */
    public static String toDelimitedString(Collection<?> coll, String separator, String prefix, String suffix) {
        if (null == coll) {
            return "";
        }

        StringBuffer sb = new StringBuffer();
        Iterator<?> iterator = coll.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            if (i > 0) {
                sb.append(separator);
            }
            sb.append(prefix).append(iterator.next()).append(suffix);
            i++;
        }
        return sb.toString();
    }

    /**
     * 确保字符串的长度, 不足时默认采用"0"在首部进行补位.
     * 
     * @param str 原字符串.
     * @param len 验证的长度.
     * @return String
     */
    public static String keepLen(String str, int len) {
        return keepLen(str, len, "0", true);
    }

    /**
     * 确保字符串的长度, 不足时采用指定的字符在首部进行补位.
     * 
     * @param str 原字符串.
     * @param len 验证的长度.
     * @param ch 添加的字符.
     * @return String
     */
    public static String keepLen(String str, int len, String ch) {
        return keepLen(str, len, ch, true);
    }

    /**
     * 确保字符串的长度, 不足时默认采用"0"在进行补位.
     * 
     * @param str 原字符串.
     * @param len 验证的长度.
     * @param append 字符前部追加.
     * @return String
     */
    public static String keepLen(String str, int len, boolean append) {
        return keepLen(str, len, "0", append);
    }

    /**
     * 确保字符串的长度, 不足时采用指定的字符在指定首部或者尾部进行补位.
     * 
     * @param str 原字符串.
     * @param len 验证的长度.
     * @param ch 添加的字符.
     * @param append 字符前部追加.
     * @return String
     */
    public static String keepLen(String str, int len, String ch, boolean append) {
        // 如果提供原始字符串为null, 则默认将其修改为 "";
        if (null == str) {
            str = "";
        } else if (str.length() >= len) {
            return str;
        }

        StringBuffer sb = new StringBuffer(str);
        // 判断是否在末尾继进行补位.
        if (append) {
            while (sb.length() < len) {
                sb.append(ch);
            }
        } else {
            while (sb.length() < len) {
                sb.insert(0, ch);
            }
        }
        return sb.toString();
    }

    /**
     * 字符串格式化的处理;<br/> 当pattern或strs无效, 则直接返回 null.
     * 
     * @param pattern 格式化模板.
     * @param strs 参数变量.
     * @return 格式化结果.
     */
    public static String format(String pattern, Object... strs) {
        return MessageFormat.format(pattern, strs);
    }

    /**
     * 将字符串转换二进制数组, 默认字符集编码: ENCODING = "UTF-8".<br/> 如果被加密字符串为null 或 长度为0时, 则直接返回 null.
     * 
     * @param str 待转换的字符串.
     * @return byte[] 转换后的二进制数组.
     */
    public static byte[] getBytes(String str) {
        return getBytes(str, CHARSET);
    }

    /**
     * 将字符串转为二进制数组, 需要指定具体字符集编码.<br/> 如果被加密字符串为null时, 则直接返回 null;<br/> 如果转换指定的字符集编码为null 或 长度为0时, 则直接返回 null.
     * 
     * @param str 待转换的字符串.
     * @param encoding 指定的字符编码.
     * @return byte[] 转换后的二进制数组.
     */
    public static byte[] getBytes(String str, String encoding) {
        if (null == str || null == encoding || encoding.length() == 0) {
            return null;
        }

        Charset charset = Charset.forName(encoding);
        return getBytes(str, charset);
    }

    /**
     * 将字符串转为二进制数组, 需要指定具体字符集编码.<br/> 如果被加密字符串为null时, 则直接返回 null;<br/> 如果转换指定的字符集编码为null 或 长度为0时, 则直接返回 null.
     * 
     * @param str 待转换的字符串.
     * @param charset 指定的字符编码.
     * @return byte[] 转换后的二进制数组.
     */
    public static byte[] getBytes(String str, Charset charset) {
        if (null == str || null == charset) {
            return null;
        }
        if (0 == str.length()) {
            return EMPTY_BYTE;
        }

        byte[] bytes = null;
        try {
            bytes = str.getBytes(charset);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return bytes;
    }

    /**
     * 将二进制数组转为字符串, 默认字符集编码: ENCODING = "UTF-8".
     * 
     * @param bytes 待转换的二进制数组.
     * @return String 转换后的字符串.
     */
    public static String getString(byte[] bytes) {
        return getString(bytes, CHARSET);
    }

    /**
     * 将二进制数组转为字符串, 需要指定具体字符集编码.<br/> 如果给定的二进制数组为null, 则直接返回 null;<br/> 如果转换指定的字符集编码为null 或 长度为0时, 则直接返回 null.
     * 
     * @param bytes 待转换的二进制数组.
     * @param encoding 指定的字符编码.
     * @return String 转换后的字符串.
     */
    public static String getString(byte[] bytes, String encoding) {
        if (null == bytes || null == encoding || encoding.length() == 0) {
            return null;
        }

        Charset charset = Charset.forName(encoding);
        return getString(bytes, charset);
    }

    /**
     * 将二进制数组转为字符串, 需要指定具体字符集编码.<br/> 如果给定的二进制数组为null, 则直接返回 null;<br/> 如果转换指定的字符集编码为null 或 长度为0时, 则直接返回 null.
     * 
     * @param bytes 待转换的二进制数组.
     * @param charset 指定的字符编码.
     * @return String 转换后的字符串.
     */
    public static String getString(byte[] bytes, Charset charset) {
        if (null == bytes || null == charset) {
            return null;
        }
        if (bytes.length == 0) {
            return "";
        }

        String str = null;
        try {
            str = new String(bytes, charset);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 将字符串按照指定的分隔符分割
     * @param str 目标字符串
     * @param split 分隔符
     * @return 按照分隔符分割后的字符串数组
     */
	public static String[] split(String str, String split) {
		if(!hasText(str)){
			return null;
		}
		if(!hasText(split)){
			return new String[]{str};
		}
		return str.split(split);
	}
	
	/**
	 * <pre>
	 * <b>将文字转为汉语拼音.</b>
	 * @param chinese 要转成拼音的中文
	 * @return
	 * </per>
	 */
	public static String chineseToPinyin(String chinese){
		char[] cl_chars = chinese.trim().toCharArray();
        String hanyupinyin = "";
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 输出拼音全部小写
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
        defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V) ;
        try {
            for (int i=0; i<cl_chars.length; i++){
                if (String.valueOf(cl_chars[i]).matches("[\u4e00-\u9fa5]+")){// 如果字符是中文,则将中文转为汉语拼音
                    hanyupinyin += PinyinHelper.toHanyuPinyinStringArray(cl_chars[i], defaultFormat)[0];
                } else {// 如果字符不是中文,则不转换
                    hanyupinyin += cl_chars[i];
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            
        }
        return hanyupinyin;
	}
}
