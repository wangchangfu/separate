package com.mapscience.core.util;

import com.mapscience.core.util.common.StringUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 

 * <b>对象操作 辅助工具.</b>
 * <b>Description:</b> 主要提供如下: 
 *    1、判断对象是否 null==obj 或者 obj!=null;
 *    2、将对象转换为指定基本类型对应的封装实例;

* <p>Copyright: Copyright (c) 2018</p>  

* <p>Company: zzs</p>  

* @author wcf  

* @date 2018年6月25日  

* @version 1.0
 */
public abstract class ObjectUtil {

    /**
     * 受保护的构造方法, 防止外部构建对象实例.
     */
    protected ObjectUtil() {
        super();
    }

    /**
     * 判断给定的参数对象是否为null或者内部元素为空;<br/>
     * 当null==obj或0==size或0==length, 则直接返回 true.
     * 
     * @param obj 对象实例.
     * @return boolean true:为空; false:不为空.
     */
    public static boolean isEmpty(Object obj) {
        return 0 == getSize(obj);
    }
    
    /**
     * 判断给定的参数对象是否为null或者内部元素为空;<br/>
     * 当null==obj或0==size或0==length, 则直接返回 true.
     * 
     * @param obj 对象实例.
     * @return boolean true:为空; false:不为空.
     */
    public static boolean isEmpty(Object... obj) {
    	if(obj.length == 0){
    		return true;
    	}
    	for (Object object : obj) {
			if(!isEmpty(object)) {
				return false;
			}
		}
    	return true;
    }
    
    /**
     * 判断给定的参数对象是否不为null或者内部元素不为空;<br/>
     * 当null==obj或0==size或0==length, 则直接返回 false.
     * 
     * @param obj 对象实例.
     * @return boolean true:不为空; false:为空.
     */
    public static boolean isNotEmpty(Object obj){
    	return 0 != getSize(obj);
    }

    /**
     * 获取对象内部元素的个数;<br/>
     * 当null==obj, 则直接返回 0;<br/>
     * 当obj为集合对象（Collection、Map）, 则返回 size;<br/>
     * 当obj数组或字符串, 则返回 length; 当obj为其他类型, 则返回 1.<br/>
     * 
     * @param obj 对象实例.
     * @return int 对象内部元素的个数.
     */
    public static int getSize(Object obj) {
        // 如果对象为空, 则返回0
        if (null == obj) {
            return 0;
        }
        // 对象的类型为字符串, 则返回字符串的length
        else if (obj instanceof String) {
            return String.valueOf(obj).length();
        }
        // 对象的类型为Collection, 则返回size
        else if (obj instanceof Collection<?>) {
            return ((Collection<?>) obj).size();
        }
        // 对象的类型为Map, 则返回size
        else if (obj instanceof Map<?, ?>) {
            return ((Map<?, ?>) obj).size();
        }
        // 对象的类型为数组, 则返回数组的length
        else if (obj.getClass().isArray()) {
            return ((Object[]) obj).length;
        }
        // 其他类型, 则返回1
        else {
            return 1;
        }
    }

    /**
     * 清空对象内容（集合的元素）;<br/>
     * 当null==obj, 则直接返回 0;<br/>
     * 当obj为集合对象（Collection、Map）, 则返回 size;<br/>
     * 当obj数组或字符串, 则返回 length; 当obj为其他类型, 则返回 1.<br/>
     * 
     * @param obj 对象实例.
     */
    public static void clear(Object obj) {
        // 如果对象为空
        if (null == obj) {

        }
        // 对象的类型为字符串
        else if (obj instanceof String) {
            obj = "";
        }
        // 对象的类型为StringBuffer
        else if (obj instanceof StringBuffer) {
            ((StringBuffer) obj).setLength(0);
        }
        // 对象的类型为Collection
        else if (obj instanceof Collection<?>) {
            ((Collection<?>) obj).clear();
        }
        // 对象的类型为Map
        else if (obj instanceof Map<?, ?>) {
            ((Map<?, ?>) obj).clear();
        }
        // 对象的类型为数组
        else if (obj.getClass().isArray()) {

        }
        // 其他类型
        else {

        }
    }

    /**
     * 获取对象16进制的HashCode;<br/>
     * 当null==obj, 则直接返回 null.
     * 
     * @param obj 对象实例.
     * @return String 对象16进制的HashCode.
     */
    public static String getHex(Object obj) {
        if (null == obj) {
            return null;
        }

        int hash = System.identityHashCode(obj);
        return Integer.toHexString(hash);
    }

    /**
     * 克隆对象(深度克隆);<br/>
     * 当null==obj, 则直接返回null.
     * 
     * @param <T> 指定克隆的接收类型.
     * @param obj 对象实例.
     * @return Object 对象副本.
     */
    @SuppressWarnings("unchecked")
    public static <T> T clone(Object obj) {
        if (null == obj) {
            return null;
        }

        Object _obj = null;
        ByteArrayInputStream bi = null;
        ObjectInputStream oi = null;
        ObjectOutputStream oo = null;
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        try {
            // 源对象
            oo = new ObjectOutputStream(bo);
            oo.writeObject(obj);
            bi = new ByteArrayInputStream(bo.toByteArray());
            oi = new ObjectInputStream(bi);
            // 目标对象
            _obj = oi.readObject();
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        } finally {
            IoUtil.close(oi, bi, oo, bo);
        }
        return (T) _obj;
    }

    /**
     * 将对象转换成Character类型;<br/>
     * 当null==obj, 则直接返回null.
     * 
     * @param obj 对象实例.
     * @return Character类型值.
     */
    public static Character toChar(Object obj) {
        return toChar(obj, null);
    }

    /**
     * 将对象转换成Character类型;<br/>
     * 当null==obj, 则直接返回 _default.
     * 
     * @param obj 对象实例.
     * @param _default 缺省值.
     * @return Character类型值.
     */
    public static Character toChar(Object obj, Character _default) {
        if (null == obj) {
            return _default;
        }
        String str = String.valueOf(obj);
        if (0 == str.length()) {
            return _default;
        }
        return str.charAt(0);
    }

    /**
     * 将对象转换成String类型;<br/>
     * 当null==obj, 则直接返回"".
     * 
     * @param obj 对象实例.
     * @return String类型值.
     */
    public static String toString(Object obj) {
        return toString(obj, "");
    }

    /**
     * 将对象转换成String类型;<br/>
     * 当null==obj, 则直接返回 _default.
     * 
     * @param obj 对象实例.
     * @param _default 缺省值.
     * @return String类型值.
     */
    public static String toString(Object obj, String _default) {
        return (null == obj) ? _default : String.valueOf(obj);
    }

    /**
     * 将对象转换成Byte类型;<br/>
     * 当null==obj, 则直接返回 null.
     * 
     * @param obj 对象实例.
     * @return Byte类型值.
     */
    public static Byte toByte(Object obj) {
        return toByte(obj, null);
    }

    /**
     * 将对象转换成Byte类型;<br/>
     * 当null==obj, 则直接返回 _default.
     * 
     * @param obj 对象实例.
     * @param _default 缺省值.
     * @return Byte类型值.
     */
    public static Byte toByte(Object obj, Byte _default) {
        return (null == obj) ? _default : Byte.valueOf(String.valueOf(obj));
    }

    /**
     * 将对象转换成Boolean类型;<br/>
     * 当null==obj, 则直接返回 null.
     * 
     * @param obj 对象实例.
     * @return Boolean类型值.
     */
    public static Boolean toBoolean(Object obj) {
        return toBoolean(obj, null);
    }

    /**
     * 将对象转换成Boolean类型;<br/>
     * 当null==obj, 则直接返回 _default.
     * 
     * @param obj 对象实例.
     * @param _default 缺省值.
     * @return Boolean类型值.
     */
    public static Boolean toBoolean(Object obj, Boolean _default) {
        return (null == obj) ? _default : Boolean.valueOf(String.valueOf(obj));
    }

    /**
     * 将对象转换成Short类型;<br/>
     * 当null==obj, 则直接返回 null.
     * 
     * @param obj 对象实例.
     * @return Short类型值.
     */
    public static Short toShort(Object obj) {
        return toShort(obj, null);
    }

    /**
     * 将对象转换成Short类型;<br/>
     * 当null==obj, 则直接返回 _default.
     * 
     * @param obj 对象实例.
     * @param _default 缺省值.
     * @return Short类型值.
     */
    public static Short toShort(Object obj, Short _default) {
        return (null == obj) ? _default : Short.valueOf(String.valueOf(obj));
    }

    /**
     * 将对象转换成Integer类型;<br/>
     * 当null==obj, 则直接返回 null.
     * 
     * @param obj 对象实例.
     * @return Integer类型值.
     */
    public static Integer toInteger(Object obj) {
        return toInteger(obj, null);
    }

    /**
     * 将对象转换成Integer类型;<br/>
     * 当null==obj, 则直接返回 _default.
     * 
     * @param obj 对象实例.
     * @param _default 缺省值.
     * @return Integer类型值.
     */
    public static Integer toInteger(Object obj, Integer _default) {
        return (null == obj) ? _default : Integer.valueOf(String.valueOf(obj));
    }

    /**
     * 将对象转换成Long类型;<br/>
     * 当null==obj, 则直接返回 null.
     * 
     * @param obj 对象实例.
     * @return Long类型值.
     */
    public static Long toLong(Object obj) {
        return toLong(obj, null);
    }

    /**
     * 将对象转换成Long类型;<br/>
     * 当null==obj, 则直接返回 _default.
     * 
     * @param obj 对象实例.
     * @param _default 缺省值.
     * @return Long类型值.
     */
    public static Long toLong(Object obj, Long _default) {
        return isEmpty(obj) ? _default : Long.valueOf(String.valueOf(obj));
    }

    /**
     * 将对象转换成Float类型;<br/>
     * 当null==obj, 则直接返回 null.
     * 
     * @param obj 对象实例.
     * @return Float类型值.
     */
    public static Float toFloat(Object obj) {
        return toFloat(obj, null);
    }

    /**
     * 将对象转换成Float类型;<br/>
     * 当null==obj, 则直接返回 _default.
     * 
     * @param obj 对象实例.
     * @param _default 缺省值.
     * @return Float类型值.
     */
    public static Float toFloat(Object obj, Float _default) {
        return isEmpty(obj) ? _default : Float.valueOf(String.valueOf(obj));
    }

    /**
     * 将对象转换成Double类型;<br/>
     * 当null==obj, 则直接返回 null.
     * 
     * @param obj 对象实例.
     * @return Double类型值.
     */
    public static Double toDouble(Object obj) {
        return toDouble(obj, null);
    }

    /**
     * 将对象转换成Double类型;<br/>
     * 当null==obj, 则直接返回 _default.
     * 
     * @param obj 对象实例.
     * @param _default 缺省值.
     * @return Double类型值.
     */
    public static Double toDouble(Object obj, Double _default) {
        return isEmpty(obj) ? _default : Double.valueOf(String.valueOf(obj));
    }

    /**
     * 将对象转换成BigDecimal类型;<br/>
     * 当null==obj, 则直接返回 null.
     * 
     * @param obj 对象实例.
     * @return BigDecimal类型值.
     */
    public static BigDecimal toBigDecimal(Object obj) {
        return toBigDecimal(obj, null);
    }

    /**
     * 将对象转换成BigDecimal类型;<br/>
     * 当null==obj, 则直接返回 _default.
     * 
     * @param obj 对象实例.
     * @param _default 缺省值.
     * @return BigDecimal类型值.
     */
    public static BigDecimal toBigDecimal(Object obj, BigDecimal _default) {
        String str = String.valueOf(obj);
        return StringUtil.hasText(str) ? new BigDecimal(str) : _default;
    }

    /**
     * 将对象转换成Date类型;<br/>
     * 当null==obj, 则直接返回 null.
     * 
     * @param obj 对象实例.
     * @return Date类型值.
     */
    public static Date toDate(Object obj) {
        return toDate(obj, null);
    }

    /**
     * 将对象转换成Date类型;<br/>
     * 当null==obj, 则直接返回 _default.
     * 
     * @param obj 对象实例.
     * @param _default 缺省值.
     * @return Date类型值.
     */
    public static Date toDate(Object obj, Date _default) {
        String str = String.valueOf(obj);
        Date date = DateUtil.parseDate(str);
        return (null != date ? date : _default);
    }

    /**
     * 将对象转换成Date类型;<br/>
     * 当null==obj, 则直接返回 null.
     * 
     * @param obj 对象实例.
     * @return Date类型值.
     */
    public static Date toDateTime(Object obj) {
        return toDateTime(obj, null);
    }

    /**
     * 将对象转换成Date类型;<br/>
     * 当null==obj, 则直接返回 _default.
     * 
     * @param obj 对象实例.
     * @param _default 缺省值.
     * @return Date类型值.
     */
    public static Date toDateTime(Object obj, Date _default) {
        String str = String.valueOf(obj);
        Date date = DateUtil.parseDatetime(str);
        return (null != date ? date : _default);
    }
    
    /**
     * <pre>
     * <b>将Map.toString()字符串反转成Map实例.</b>
     * {a=转换值, b=1322, c=123convert}==>>>{@link Map}
     * @param mapString
     * @return {@link Map}
     * </per>
     */
	@SuppressWarnings("unchecked")
	public static <K,V> HashMap<K, V> toMap(String mapString) {
		mapString = mapString.substring(1, mapString.length() - 1);
		String[] strs = mapString.split(",");
		HashMap<K, V> map = new HashMap<K, V>();
		for (String string : strs) {
			String key = string.split("=")[0];
			String value = string.split("=")[1];
			map.put((K)key, (V)value);
		}
		return map;
	}
}
