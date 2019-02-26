/**
 * 
 */
package com.mapscience.core.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 *说明：
 *<p> </p>
 * 书写者 @author  WCF
 * 创建时间 2018年12月3日
 *
 */
public abstract class ReflectUtil {
	/**
	 * 对象的getter、setter操作
	 * 
	 * @param obj
	 * @param fieldName
	 * @param fieldVal
	 * @param type
	 * @return
	 */
	protected static Object operate(Object obj, String fieldName, Object fieldVal, String type) {
		Object ret = fieldVal;
		try {
			// 获得对象类型
			Class<? extends Object> classType = obj.getClass();
			// 获得对象的所有属性
			Field fields[] = ClassUtil.getFields(obj);
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				if (field.getName().equals(fieldName)) {

					String firstLetter = fieldName.substring(0, 1).toUpperCase(); // 获得和属性对应的getXXX()方法的名字
					if ("set".equals(type)) {
						String setMethodName = "set" + firstLetter + fieldName.substring(1); // 获得和属性对应的getXXX()方法
						Method setMethod = classType.getMethod(setMethodName, new Class[] { field.getType() }); // 调用原对象的getXXX()方法
						ret = setMethod.invoke(obj, new Object[] { fieldVal });
					}
					if ("get".equals(type)) {
						String getMethodName = "get" + firstLetter + fieldName.substring(1); // 获得和属性对应的setXXX()方法的名字
						Method getMethod = classType.getMethod(getMethodName, new Class[] {});
						ret = getMethod.invoke(obj, new Object[] {});
					}
					return ret;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * <pre>
	 * 给对象赋值,需要提供字段的setter方法
	 * 日期时间格式 Date.toString()-> Thu Dec 17 11:44:39 CST 2015
	 * @param object
	 * @param fieldName
	 * @param fieldVal
	 * </pre>
	 */
	public static void setValue(Object object, String fieldName, Object fieldVal) {
		Field field = getDeclaredField(object, fieldName);
		if (field == null) {
			throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
		}
		String type = field.getType().toString().toLowerCase();
		fieldVal = convertVlaue(type, fieldVal);
		operate(object, fieldName, fieldVal, "set");
	}
	

	/**
	 * 设置对象的属性值(暴力设值)
	 * 
	 * @param object
	 * @param fieldName
	 * @param value
	 */
	public static void set(Object object, String fieldName, Object value) {
		Field field = getDeclaredField(object, fieldName);
		if (field == null) {
			throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
		}
		int mod = makeAccessible(field);
		try {
			String type = field.getType().toString().toLowerCase();
			value = convertVlaue(type, value);
			field.set(object, value);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} finally {
           closeAccessible(field, mod);
       }
	}
	
	/**
	 * 通过对象的getter方法获取指定属性值
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	public static Object getValue(Object obj, String fieldName) {
		return operate(obj, fieldName, null, "get");
	}


	/**
	 * 获取对象的属性值(暴力获取)
	 * 
	 * @param object
	 * @param fieldName
	 * @return
	 */
	public static Object get(Object object, String fieldName) {
		Field field = getDeclaredField(object, fieldName);
		if (field == null) {
			throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
		}
		int mod = makeAccessible(field);
		Object result = null;
		try {
			result = field.get(object);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} finally {
           closeAccessible(field, mod);
       }
		return result;
	}

	/**
	 * 指定获取对象字段对象
	 * 
	 * @param object
	 * filedName
	 * @return
	 */
	public static Field getDeclaredField(Object object, String fieldName) {
		Class<?> clazz = (object instanceof Class<?> ? (Class<?>) object : object.getClass());
		for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
			try {
				return clazz.getDeclaredField(fieldName);
			} catch (Throwable e) {
				// 注意：如果这里的异常打印或者往外抛, 则就不会执行clazz = clazz.getSuperclass();
			}
		}
		return null;
	}
	
	public static Field[] getDeclaredFields(Object object){
		Class<?> clazz = (object instanceof Class<?> ? (Class<?>) object : object.getClass());
		List<Field> fields = new ArrayList<Field>();
		for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
			try {
				fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
			} catch (Throwable e) {
				// 注意：如果这里的异常打印或者往外抛, 则就不会执行clazz = clazz.getSuperclass();
			}
		}
		return (Field[]) fields.toArray(new Field[]{});
	}

	/**
	 * 根据名称获取方法对象
	 * 
	 * @param object
	 * @param methodName
	 * @param parameterTypes
	 * @return
	 */
	protected static Method getDeclaredMethod(Object object, String methodName, Class<?>[] parameterTypes) {
		Class<?> clazz = (object instanceof Class<?> ? (Class<?>) object : object.getClass());
		for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
			try {
				return clazz.getDeclaredMethod(methodName, parameterTypes);
			} catch (NoSuchMethodException e) {
				// 注意：如果这里的异常打印或者往外抛, 则就不会执行clazz = superClass.getSuperclass();
			}
		}
		return null;
	}

	/**
	 * 调用对象的方法,指定方法名参数列表类型以及参数列表
	 * 
	 * @param object
	 *            调用对象
	 * @param methodName
	 *            调用方法
	 * @param parameterTypes
	 *            参数类型集合
	 * @param parameters
	 *            参数列表集合
	 * @return
	 * @throws InvocationTargetException
	 */
	public static Object invokeMethod(Object object, String methodName, Class<?>[] parameterTypes, Object[] parameters)
			throws InvocationTargetException {
		Method method = getDeclaredMethod(object, methodName, parameterTypes);
		if (method == null) {
			throw new IllegalArgumentException("Could not find method [" + methodName + "] on target [" + object + "]");
		}
		int mod = makeAccessible(method);
		method.setAccessible(true);
		try {
			return method.invoke(object, parameters);
		} catch (IllegalAccessException e) {
		} finally {
           closeAccessible(method, mod);
       }
		return null;
	}

	/**
	 * <pre>
	 * 将对象值转换成指定类型的值
	 * 日期时间格式 Date.toString()-> Thu Dec 17 11:44:39 CST 2015
	 * </pre>
	 * @param type
	 *            属性类型
	 * @param fieldVal
	 *            属性值
	 * @return
	 */
	public static Object convertVlaue(String type, Object fieldVal) {
		if(fieldVal == null){
			return fieldVal;
		}
		type = type.toLowerCase(Locale.ENGLISH);
		if (type.endsWith("char") || type.endsWith("character")) {
			fieldVal = String.valueOf(fieldVal).charAt(0);
		} else if (type.endsWith("string")) {
			fieldVal = String.valueOf(fieldVal);
		} else if (type.endsWith("boolean")) {
			fieldVal = Boolean.valueOf(String.valueOf(fieldVal));
		} else if (type.endsWith("byte")) {
			fieldVal = Byte.valueOf(String.valueOf(fieldVal));
		} else if (type.endsWith("short")) {
			fieldVal = Short.valueOf(String.valueOf(fieldVal));
		} else if (type.endsWith("int") || type.endsWith("integer")) {
			fieldVal = Integer.valueOf(String.valueOf(fieldVal));
		} else if (type.endsWith("long")) {
			fieldVal = Long.valueOf(String.valueOf(fieldVal));
		} else if (type.endsWith("float")) {
			fieldVal = Float.valueOf(String.valueOf(fieldVal));
		} else if (type.endsWith("double")) {
			fieldVal = Double.valueOf(String.valueOf(fieldVal));
		} else if (type.endsWith("biginteger")) {
			fieldVal = BigInteger.valueOf(Long.valueOf(String.valueOf(fieldVal)));
		} else if (type.endsWith("bigdecimal")) {
			fieldVal = BigDecimal.valueOf(Double.valueOf(String.valueOf(fieldVal)));
		} else if (type.endsWith("date")) {
			try {
				fieldVal = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US).parse(String.valueOf(fieldVal));
			} catch (ParseException e) {
			}
		}
		return fieldVal;
	}

	/**
    * 打开属性的可访问权限.
    * 
    * @param field
    * @return int 原始的访问控制
    */
   private static int makeAccessible(Field field) {
       // 获取原始的访问权限.
       int mod = field.getModifiers();
       // 如果不是public, 则需要打开可访问.
       if (!Modifier.isPublic(mod)) {
           field.setAccessible(true);
       }

       // 如果是final, 则需要打开可写.
       if (Modifier.isFinal(mod)) {
           try {
               Field mfield = Field.class.getDeclaredField("modifiers");
               mfield.setAccessible(true);
               mfield.setInt(field, field.getModifiers() & ~Modifier.FINAL);
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
       return mod;
   }
   
   /**
    * 打开方法的可访问权限.
    * 
    * field
    * @return int 原始的访问控制
    */
	protected static int makeAccessible(Method method){
		int mod = method.getModifiers();
		 // 如果不是public, 则需要打开可访问.
       if (!Modifier.isPublic(mod)) {
       	method.setAccessible(true);
       }
       // 如果是final, 则需要打开可写.
       if (Modifier.isFinal(mod)) {
           try {
               Field mfield = Field.class.getDeclaredField("modifiers");
               mfield.setAccessible(true);
               mfield.setInt(method, method.getModifiers() & ~Modifier.FINAL);
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
       return mod;
	}
   
	/**
    * 关闭方法的可访问权限.
    * 
    * field
    * @param mod
    */
	protected static void closeAccessible(Method method, int mod) {
       // 如果不是public, 则需要关闭可访问.
       if (!Modifier.isPublic(mod)) {
       	method.setAccessible(false);
       }

       // 如果不是final, 则需要关闭可写.
       if (Modifier.isStatic(mod)) {
           try {
               Field mfield = Field.class.getDeclaredField("modifiers");
               mfield.setAccessible(true);
               mfield.setInt(method, method.getModifiers() | Modifier.FINAL);
               mfield.setAccessible(false);
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
   }
	
	/**
    * 关闭属性的可访问权限.
    * 
    * @param field
    * @param mod
    */
	protected static void closeAccessible(Field field, int mod) {
       // 如果不是public, 则需要关闭可访问.
       if (!Modifier.isPublic(mod)) {
           field.setAccessible(false);
       }

       // 如果不是final, 则需要关闭可写.
       if (Modifier.isStatic(mod)) {
           try {
               Field mfield = Field.class.getDeclaredField("modifiers");
               mfield.setAccessible(true);
               mfield.setInt(field, field.getModifiers() | Modifier.FINAL);
               mfield.setAccessible(false);
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
   }
	

	/**
	 * <pre>
	 * 将简单类型转换为基本类型, 
	 * 例如： 提供
	 * type = "int" || "integer"
	 * type = "char" || "character"
	 * type = "boolean"
	 * type = "short"
	 * type = "long"
	 * type = "float"
	 * type = "double"
	 * type = "biginteger"
	 * type = "bigdecimal"
	 * 如果 type == null 或者 == "" 或者  == " ",  则返回 null.
	 * </pre>
	 * 
	 * @param type
	 *            java类名称.
	 * @return String 基本类名称.
	 */
	public static String getType(String type) {
		type = type.toLowerCase(Locale.ENGLISH);
		if (type.endsWith("char") || type.endsWith("character")) {
			return "java.lang.Character";
		} else if (type.endsWith("string")) {
			return "java.lang.String";
		} else if (type.endsWith("boolean")) {
			return "java.lang.Boolean";
		} else if (type.endsWith("byte")) {
			return "java.lang.Byte";
		} else if (type.endsWith("short")) {
			return "java.lang.Short";
		} else if (type.endsWith("int") || type.endsWith("integer")) {
			return "java.lang.Integer";
		} else if (type.endsWith("long")) {
			return "java.lang.Long";
		} else if (type.endsWith("float")) {
			return "java.lang.Float";
		} else if (type.endsWith("double")) {
			return "java.lang.Double";
		} else if (type.endsWith("biginteger")) {
			return "java.lang.Double";
		} else if (type.endsWith("bigdecimal")) {
			return "java.lang.Double";
		} else {
			return null;
		}
	}
}
