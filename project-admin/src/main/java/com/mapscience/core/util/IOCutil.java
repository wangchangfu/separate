/**
 * 
 */
package com.mapscience.core.util;

import com.mapscience.core.util.common.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;

import java.util.Locale;


/**
 *说明：
 *<p> </p>
 * 书写者 @author  WCF
 * 创建时间 2018年12月4日
 *
 */
public class IOCutil implements ApplicationContextAware{
	 // 当前是否已经初始化.
    protected static Boolean inited = false;

    /**
     * Spring ApplicationContext对象实例, 即IOC上下文.
     */
    protected static ApplicationContext context;

    /**
     * 日志记录器.
     */
    protected static final Logger logger = LoggerFactory.getLogger(IOCutil.class);

    /**
     * 受保护的构造方法, 防止外部构建对象实例.
     */
    protected IOCutil() {
        super();
    }

    /**
     * 初始化IOC, 负责装载Spring配置的所有的 Properties 配置属性, 并本地持有.
     */
    protected void init() {
        synchronized (inited) {
            if (inited) {
                logger.warn("IocUtil is inited !!!");
                return;
            }
            inited = true;
            logger.info(LogUtil.PREFIX2 + "IocUtil init ...");
        }
    }

    /**
     * 获取 指定 Context中 beanType对应的Bean实例.<br/>
     * 如果通过beanType在context中没有找或者 ApplicationContext没有正确初始化时,<br/>
     * 该函数都将返回null同时不会抛出异常信息.
     * 
     * @param <T>
     * @param beanType ioc中对应bean的类型.
     * @return Object Spring Bean的实例
     */
    public static <T> T getBean(Class<T> beanType) {
        if (null == beanType) {
            return null;
        }
        try {
            return context.getBean(beanType);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取 Spring Context中 beanName对应的Bean实例并转换为具体的类型.<br/>
     * 如果通过beanName在context中没有找或者 ApplicationContext没有正确初始化时,<br/>
     * 该函数都将返回null同时不会抛出异常信息.
     * 
     * @param <T>
     * @param beanName Bean名称.
     * @return Object Bean实例.
     */
    @SuppressWarnings("unchecked")
	public static <T> T getBean(String beanName) {
        if (!StringUtil.hasText(beanName)) {
            return null;
        }
        try {
            return (T) context.getBean(beanName);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取 指定 Context中 beanName对应的Bean实例.<br/>
     * 如果通过beanName在context中没有找或者 ApplicationContext没有正确初始化时,<br/>
     * 该函数都将返回null同时不会抛出异常信息.
     * 
     * @param context spring ApplicationContext 实例
     * @param beanName ioc中对应bean的名称
     * @return Object Spring Bean的实例
     */
    public static Object getBean(ApplicationContext context, String beanName) {
        try {
            return context.getBean(beanName);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取 Spring Context中 beanName对应的Bean实例的 Class.<br/>
     * 如果与给定名字相应的bean定义没有被找到, 将返回 null.
     * 
     * @param beanName Bean名称.
     * @return Class 注册对象的类型.
     */
    public static Class<?> getBeanType(String beanName) {
        if (!StringUtil.hasText(beanName)) {
            return null;
        }
        try {
            return context.getType(beanName);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 如果BeanFactory包含一个与所给名称匹配的bean定义, 则返回true.<br/>
     * 如果与给定名字相应的bean定义没有被找到, 将返回 null.
     * 
     * @param beanName 配置中定义的beanName.
     * @return Boolean 存在则返回 true; 无则返回 false.
     */
    public static Boolean isExistsBean(String beanName) {
        if (!StringUtil.hasText(beanName)) {
            return null;
        }
        try {
            return context.containsBean(beanName);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 判断以给定名字注册的bean定义是否为 singleton Bean.<br/>
     * 如果与给定名字相应的bean定义没有被找到, 将返回 null.
     * 
     * @param beanName 配置中定义的beanName.
     * @return Boolean 是则返回 true; 不是则返回 false.
     */
    public static Boolean isSingletonBean(String beanName) {
        if (!StringUtil.hasText(beanName)) {
            return null;
        }

        try {
            return context.isSingleton(beanName);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据本地化配置进行翻译.
     * 
     * @param name
     * @param args
     * @param locale
     * @return String
     */
    public static String getMessage(String name, Object args[], Locale locale) {
        try {
            return context.getMessage(name, args, locale);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 返回 Spring ApplicationContext对象实例.
     * 
     * @return ApplicationContext Spring ApplicationContex对象实例.
     */
    public static ApplicationContext getContext() {
        return context;
    }

    /**
     * 返回 Spring Context Environment 对象实例.
     * 
     * @return Environment Spring Context Environment对象实例.
     */
    public static Environment getEnvironment() {
        return context.getEnvironment();
    }

    /**
     * 设置 Spring ApplicationContex对象实例.
     * 
     * @param context Spring ApplicationContex对象实例.
     * @see ApplicationContextAware
     *      #setApplicationContext(org.springframework.context.ApplicationContext)
     */
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        IOCutil.context = context;
    }
}
