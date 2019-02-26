package com.mapscience.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




/**
 * <pre>
 * <b>Class 操作辅助工具.</b>
 * <b>Description:</b> 在DateUtil类中, 关于几个名词的定义: 
 *    日期, 例如: 2014-01-01;
 *    时间, 例如: 08:30:42;
 *    时间轴, 例如: 08:30:42.001;
 *    日期时间, 例如: 2014-01-01 08:30:42;
 *    日期时间轴, 例如: 2014-01-01 08:30:42.001.
 *    
 * </pre>
 */
@SuppressWarnings("all")
public abstract class ClassUtil {

	/** Jar包文件名的格式. */
	private static final Pattern PATTERN = Pattern.compile("([0-9][0-9\\.\\-]*)\\.jar");

	/** 日志记录器. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ClassUtil.class);

	/**
	 * 受保护的构造方法, 防止外部构建对象实例.
	 */
	protected ClassUtil() {
		super();
	}

	/**
	 * <pre>
	 * 获取对象数组中每个元素的Class类型并组装成Class数组返回, 
	 * 如果 params == null 或者  params.lenght == 0,  则返回 new Class[0];
	 * </pre>
	 * 
	 * @param params
	 *            参数值数组.
	 * @return Class[].
	 */
	public static Class<?>[] getClass(Object... params) {
		Class<?>[] clazzs = new Class[0];
		if (null != params && params.length >= 1) {
			clazzs = new Class[params.length];
			for (int i = 0; i < params.length; i++) {
				clazzs[i] = params[i].getClass();
			}
		}
		return clazzs;
	}

	public static List<Class<?>> getClass(String packageName) {
		// 第一个class类的集合
		List<Class<?>> classes = new ArrayList<Class<?>>();
		// 是否循环迭代
		boolean recursive = true;
		// 获取包的名字 并进行替换
		String packageDirName = packageName.replace('.', '/');
		// 定义一个枚举的集合 并进行循环来处理这个目录下的things
		Enumeration<URL> dirs;
		try {
			dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
			// 循环迭代下去
			while (dirs.hasMoreElements()) {
				// 获取下一个元素
				URL url = dirs.nextElement();
				// 得到协议的名称
				String protocol = url.getProtocol();
				// 如果是以文件的形式保存在服务器上
				if ("file".equals(protocol)) {
					// 获取包的物理路径
					String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
					// 以文件的方式扫描整个包下的文件 并添加到集合中
					findAndAddClassesInPackageByFile(packageName, filePath, recursive, classes);
				} else if ("jar".equals(protocol)) {
					// 如果是jar包文件
					// 定义一个JarFile
					JarFile jar;
					try {
						// 获取jar
						jar = ((JarURLConnection) url.openConnection()).getJarFile();
						// 从此jar包 得到一个枚举类
						Enumeration<JarEntry> entries = jar.entries();
						// 同样的进行循环迭代
						while (entries.hasMoreElements()) {
							// 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
							JarEntry entry = entries.nextElement();
							String name = entry.getName();
							// 如果是以/开头的
							if (name.charAt(0) == '/') {
								// 获取后面的字符串
								name = name.substring(1);
							}
							// 如果前半部分和定义的包名相同
							if (name.startsWith(packageDirName)) {
								int idx = name.lastIndexOf('/');
								// 如果以"/"结尾 是一个包
								if (idx != -1) {
									// 获取包名 把"/"替换成"."
									packageName = name.substring(0, idx).replace('/', '.');
								}
								// 如果可以迭代下去 并且是一个包
								if ((idx != -1) || recursive) {
									// 如果是一个.class文件 而且不是目录
									if (name.endsWith(".class") && !entry.isDirectory()) {
										// 去掉后面的".class" 获取真正的类名
										String className = name.substring(packageName.length() + 1, name.length() - 6);
										try {
											// 添加到classes
											classes.add(Class.forName(packageName + '.' + className));
										} catch (ClassNotFoundException e) {
											e.printStackTrace();
										}
									}
								}
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return classes;
	}
	
	/**
	 *<pre>
	 *<b>获取指定注解的方法集合.</b>
	 *<b>Description:</b> 
	 *    
	 *<b>Author:</b> zhouguangyong@typay.me
	 *<b>Date:</b> 2016-5-30 下午04:54:53
	 *@param <T>
	 *@param packageName
	 *@param annotationClass
	 *@return List<T>
	 *</pre>
	 */
	public static List<Method> getMethodAnnotations(String packageName, Class<?> annotationClass){
		if(null == annotationClass){
			return null;
		}
		List<Class<?>> tokenClass = ClassUtil.getClass(packageName);
		if(ObjectUtil.isEmpty(tokenClass)){
			return null;
		}
		List<Method> methods = new ArrayList<Method>();
		for (Class<?> clazz : tokenClass) {
			Method[] _methods = clazz.getDeclaredMethods();
			next:
			for (Method method : _methods) {
				Annotation[] annotations = method.getAnnotations();
				for (Annotation annotation : annotations) {
					if(annotation.annotationType() == annotationClass){
						methods.add(method);
						continue next;
					}
				}
			}
		}
		return methods;
	}
	

	/**
	 * 以文件的形式来获取包下的所有Class
	 * 
	 * @param packageName
	 * @param packagePath
	 * @param recursive
	 * @param classes
	 */
	public static void findAndAddClassesInPackageByFile(String packageName, String packagePath, final boolean recursive,
			List<Class<?>> classes) {
		// 获取此包的目录 建立一个File
		File dir = new File(packagePath);
		// 如果不存在或者 也不是目录就直接返回
		if (!dir.exists() || !dir.isDirectory()) {
			return;
		}
		// 如果存在 就获取包下的所有文件 包括目录
		File[] dirfiles = dir.listFiles(new FileFilter() {
			// 自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
			public boolean accept(File file) {
				return (recursive && file.isDirectory()) || (file.getName().endsWith(".class"));
			}
		});
		// 循环所有文件
		for (File file : dirfiles) {
			// 如果是目录 则继续扫描
			if (file.isDirectory()) {
				findAndAddClassesInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), recursive, classes);
			} else {
				// 如果是java类文件 去掉后面的.class 只留下类名
				String className = file.getName().substring(0, file.getName().length() - 6);
				try {
					// 添加到集合中去
					classes.add(Class.forName(packageName + '.' + className));
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 根据提供的类的全名, 即"包路径" + "类名" 装载对应的class.
	 * 
	 * @param clazz
	 *            "包路径" + "类名".
	 * @return Class<?>
	 */
	public static Class<?> loadClass(String clazz) {
		Class<?> _clazz = null;
		try {
			// 装载对应的类.
			_clazz = Class.forName(clazz);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return _clazz;
	}

	/**
	 * <pre>
	 * 将简单类型转换为基本类型, 
	 * 例如： 提供type = "int" 或者 type = "integer" 或者 type = "java.lang.Int" 
	 *       或者 type = "java.lang.Integer",  则返回对应的  "java.lang.Integer". 
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

	/**
	 * <pre>
	 * 根据 "包名.类名" 获取对应的实例对象, 
	 * 如果 "包名.类名" == null 或者 == "" 或者 == "", 则返回 null;
	 * 如果 提供的 "包名.类名" 参数错误、不存在或者无法访问默认无参构造方法时, 均返回 null.
	 * </pre>
	 * 
	 * @param clazz
	 *            "包名.类名"的字符串, 例如:org.utils.ObjectUtil.
	 * @return Object 对象实例.
	 */
	public static Object instance(String clazz) {
		if (null == clazz || "".equals(clazz.trim())) {
			return null;
		}

		// 装载对应的类.
		Class<?> _clazz = loadClass(clazz);
		// 调用的默认的无参构造方法实例化.
		return instance(_clazz);
	}

	/**
	 * <pre>
	 * 根据 Class 获取对应的实例对象, 
	 * 如果提供的 clazz 错误、不存在或者无法访问默认无参构造方法时, 均返回 null.
	 * </pre>
	 * 
	 * @param clazz
	 *            类的驱动器, 例如:org.utils.ObjectUtil.class.
	 * @return Object 对象实例.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T instance(Class<T> clazz) {
		if (null == clazz) {
			return null;
		}

		try {
			// 获取所有的造函数.
			Constructor<T>[] constructors = (Constructor<T>[]) clazz.getDeclaredConstructors();
			for (Constructor<T> constructor : constructors) {
				// 循环查找无参构造函数.
				if (0 == constructor.getGenericParameterTypes().length) {
					// 获取默认的访问控制修饰符, 默认将其都打开.
					boolean isAccessible = constructor.isAccessible();
					constructor.setAccessible(true);
					// 调用的默认的无参构造方法实例化.
					T object = constructor.newInstance(new Object[] {});
					constructor.setAccessible(isAccessible);
					return clazz.cast(object);
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * <pre>
	 * 根据 "包名.类名" 获取对应的实例对象, 
	 * 如果 "包名.类名" == null 或者 == "" 或者 == "", 则返回 null;
	 * 如果 提供的 "包名.类名" 参数错误、不存在或者无法访问构造方法以及参数列表错误时, 均返回 null.
	 * </pre>
	 * 
	 * @param clazz
	 *            "包名.类名"的字符串, 例如:org.utils.ObjectUtil.
	 * @param params
	 *            构造方法对应的参数列表, 注意参数追加的顺序.
	 * @return Object 对象实例.
	 */
	public static Object instance(String clazz, Object... params) {
		if (null == clazz || "".equals(clazz.trim()) || null == params) {
			return null;
		}

		// 装载对应的类.
		Class<?> _clazz = loadClass(clazz);
		// 通过构造方法以及对应的参数列表创建对象的实例.
		return instance(_clazz, params);
	}

	/**
	 * <pre>
	 * 根据 Class 获取对应的实例对象, 
	 * 如果 提供的 clazz 参数错误、不存在或者无法访问构造方法以及参数列表错误时, 均返回 null.
	 * </pre>
	 * 
	 * @param clazz
	 *            类的驱动器, 例如:org.utils.ObjectUtil.class.
	 * @param params
	 *            构造方法对应的参数列表, 注意参数追加的顺序.
	 * @return Object 对象实例.
	 */
	public static <T> T instance(Class<T> clazz, Object... params) {
		if (null == clazz) {
			return null;
		}

		try {
			// 获取参数列表的每个参数对应的参数类型并组装成数组
			Class<?>[] parameterTypes = getClass(params);
			// 根据提供参数列表的类型数组, 获取类类驱动器对应的构造方法
			Constructor<T> constructor = clazz.getConstructor(parameterTypes);
			// 通过构造方法以及对应的参数列表创建对象的实例
			return (T) constructor.newInstance(params);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 在每个jar包中挑一个一定会加载的类，加上重复类检查. <br/>
	 * eg: static { DuplicateUtil.check(Xxx.class); }
	 * 
	 * @param cls
	 */
	public static void duplicate(Class<?> cls) {
		String path = cls.getName().replace('.', '/') + ".class";
		duplicate(path);
	}

	/**
	 * @param path
	 */
	public static void duplicate(String path) {
		try {
			// 在ClassPath搜文件
			Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(path);
			Set<String> files = new HashSet<String>();
			while (urls.hasMoreElements()) {
				URL url = urls.nextElement();
				if (url != null) {
					String file = url.getFile();
					if (file != null && file.length() > 0) {
						files.add(file);
					}
				}
			}
			// 如果有多个，就表示重复
			if (files.size() > 1) {
				LOGGER.error("Duplicate class " + path + " in " + files.size() + " jar " + files);
			}
		} catch (Throwable e) { // 防御性容错
			LOGGER.error(e.getMessage(), e);
		}
	}

	/**
	 * 获取指定类的版本号
	 * 
	 * @param clazz
	 * @return
	 */
	public static String version(Class<?> clazz) {
		return version(clazz, "1.0");
	}

	/**
	 * 获取指定类的版本号
	 * 
	 * @param clazz
	 * @param defaultVersion
	 * @return
	 */
	public static String version(Class<?> clazz, String defaultVersion) {
		try {
			// 首先查找MANIFEST.MF规范中的版本号
			String version = clazz.getPackage().getImplementationVersion();
			if (version == null || version.length() == 0) {
				version = clazz.getPackage().getSpecificationVersion();
			}
			if (version == null || version.length() == 0) {
				// 如果MANIFEST.MF规范中没有版本号，基于jar包名获取版本号
				String file = clazz.getProtectionDomain().getCodeSource().getLocation().getFile();
				if (file != null && file.length() > 0 && file.endsWith(".jar")) {
					Matcher matcher = PATTERN.matcher(file);
					while (matcher.find() && matcher.groupCount() > 0) {
						version = matcher.group(1);
					}
				}
			}
			// 返回版本号，如果为空返回缺省版本号
			return version == null || version.length() == 0 ? defaultVersion : version;
		} catch (Throwable e) { // 防御性容错
			// 忽略异常，返回缺省版本号
			LOGGER.error(e.getMessage(), e);
			return defaultVersion;
		}
	}

	/**
	 * <pre>
	 * 循环向上转型, 获取对象的DeclaredField字段.
	 * 如果找不到该字段或错误, 则返回 null.
	 * </pre>
	 * 
	 * @param obj
	 *            对象.
	 * @param fieldName
	 *            字段名.
	 * @return Field 属性对象.
	 */
	public static Field getField(Object obj, String fieldName) {
		Class<?> clazz = (obj instanceof Class<?> ? (Class<?>) obj : obj.getClass());
		for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
			try {
				return clazz.getDeclaredField(fieldName);
			} catch (Throwable e) {
				// 注意：如果这里的异常打印或者往外抛, 则就不会执行clazz = clazz.getSuperclass();
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * <pre>
	 * 循环向上转型, 获取对象的DeclaredField字段集合.
	 * 如果找不到该字段或错误, 则返回 [].
	 * </pre>
	 * 
	 * @param obj
	 *            字段名.
	 * @return Field[] 属性对象集合.
	 */
	public static Field[] getFields(Object obj){
		Class<?> clazz = (obj instanceof Class<?> ? (Class<?>) obj : obj.getClass());
		List<Field> fields = new ArrayList<Field>();
		for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
			try {
				fields.addAll(Arrays.asList(clazz.getDeclaredFields())) ;
			} catch (Throwable e) {
				// 注意：如果这里的异常打印或者往外抛, 则就不会执行clazz = clazz.getSuperclass();
				e.printStackTrace();
			}
		}
		return fields.toArray(new Field[]{});
	}

	/**
	 * <pre>
	 * 直接暴力读取对象的属性值, 
	 * 忽略 private/protected 修饰符, 不经过 getter方法,
	 * 如果找不到该字段或错误, 则返回 null.
	 * </pre>
	 * 
	 * @param obj
	 *            对象.
	 * @param fieldName
	 *            属性名.
	 * @return Object 属性值.
	 */
	public static Object getValue(Object obj, String fieldName) {
		// 根据 对象和属性名通过反射 获取 Field对象
		Field field = getField(obj, fieldName);
		if (null != field) {
			// 抑制Java对其的检查
			boolean accessible = field.isAccessible();
			// 抑制Java对属性进行检查,主要是针对私有属性而言
			field.setAccessible(true);
			try {
				// 获取 object 中 field 所代表的属性值
				return field.get(obj);
			} catch (Throwable e) {
				// 如果这里的异常打印或者往外抛，则就不会执行field.setAccessible(true),
				e.printStackTrace();
			} finally {
				field.setAccessible(accessible);
			}
		}
		return null;
	}

	/**
	 * 获取指定文件名的输入流.
	 * 
	 * @param fileName
	 * @return InputStream
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static InputStream getInputStream(final String fileName) {
		return (InputStream) AccessController.doPrivileged(new PrivilegedAction() {
			public Object run() {
				ClassLoader threadCL = getContextClassLoader();
				if (threadCL != null) {
					return threadCL.getResourceAsStream(fileName);
				} else {
					return ClassLoader.getSystemResourceAsStream(fileName);
				}
			}
		});
	}

	/**
	 * Return the thread context class loader if available. Otherwise return
	 * null. The thread context class loader is available for JDK 1.2 or later,
	 * if certain security conditions are met.
	 * 
	 * @exception RuntimeException
	 *                if a suitable class loader cannot be identified.
	 */
	private static ClassLoader getContextClassLoader() {
		ClassLoader classLoader = null;
		if (classLoader == null) {
			try {
				// Are we running on a JDK 1.2 or later system?
				Method method = Thread.class.getMethod("getContextClassLoader", null);
				// Get the thread context class loader (if there is one)
				try {
					classLoader = (ClassLoader) method.invoke(Thread.currentThread(), null);
				} catch (IllegalAccessException e) {
					; // ignore
				} catch (InvocationTargetException e) {
					/**
					 * RuntimeException is thrown by 'invoke' when the method
					 * being invoked (getContextClassLoader) throws an
					 * exception.getContextClassLoader() throws
					 * SecurityException when the context class loader isn't an
					 * ancestor of the calling class's class loader, or if
					 * security permissions are restricted. In the first case
					 * (not related), we want to ignore and keep going. We
					 * cannot help but also ignore the second with the logic
					 * below, but other calls elsewhere (to obtain a class
					 * loader) will trigger this exception where we can make a
					 * distinction.
					 */
					if (e.getTargetException() instanceof SecurityException) {
						; // ignore
					} else {
						// Capture 'e.getTargetException()' exception for
						// details
						// alternate: log 'e.getTargetException()', and pass
						// back 'e'.
						throw new RuntimeException("Unexpected InvocationTargetException", e.getTargetException());
					}
				}
			} catch (NoSuchMethodException e) {
				// Assume we are running on JDK 1.1
				; // ignore
			}
		}
		if (classLoader == null) {
			classLoader = ClassUtil.class.getClassLoader();
		}
		// Return the selected class loader
		return classLoader;
	}
}
