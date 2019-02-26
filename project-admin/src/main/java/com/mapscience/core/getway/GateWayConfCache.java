/**
 * 
 */
package com.mapscience.core.getway;

import com.google.common.collect.Maps;
import com.mapscience.core.getway.annotation.GatewayConfig;
import com.mapscience.core.getway.exception.CacheException;
import com.mapscience.core.util.ClassUtil;
import com.mapscience.core.util.ObjectUtil;
import com.mapscience.core.util.common.StringUtil;
import org.springframework.beans.factory.BeanInitializationException;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *说明：
 *<p>对外开放api缓存服务 </p>
 * 书写者 @author  WCF
 * 创建时间 2018年12月3日
 *
 */
public class GateWayConfCache extends CommonCache{
	/** 缓存可以 */
	public static final String KEY = "gateWay.conf.cache";
	
	/** 扫描配置包名称，多个以逗号分隔 */
	private String packageNames;
	
	/** 网关执行类配置映射 */
	private static final Map<String, Method> CONF_MAPPING = Maps.newHashMap();
	
	public GateWayConfCache() {
		super(KEY);
	}
	
	/**
	 * @param key
	 */
	public GateWayConfCache(String key) {
		super(key);
	}

	/* (non-Javadoc)
	 * @see com.framework.cache.impl.CommonCache#load()
	 */
	@Override
	protected void load() throws CacheException {
		if(ObjectUtil.isEmpty(this.packageNames)){
			return;
		}
		String[] packNames = StringUtil.delimitedToArray(this.packageNames);
		List<Class<?>> classes = new ArrayList<Class<?>>();
		for (String packageName : packNames) {
			classes.addAll(ClassUtil.getClass(packageName));
		}
		if(ObjectUtil.isEmpty(classes)){
			return;
		}
		
		// 遍历指定包中的类
		for (Class<?> clazz : classes) {
			// 获取类的所有方法
			Method[] methods = clazz.getMethods();
			// 获取method寻找网关配置
			for (Method method : methods) {
				GatewayConfig annotation = method.getAnnotation(GatewayConfig.class);
				if(null == annotation){
					continue;
				}
				String antMethodName = annotation.value().name().toLowerCase().replaceAll("_",".");
				annotation.value().setMethod(antMethodName);
				// 获取版本号
				String[] versions = annotation.version();
				for (String version : versions) {
					if(ObjectUtil.isNotEmpty(version)){
						String gateWayKey = antMethodName + "." + version;
						// 判断映射中是否存在
						if(CONF_MAPPING.containsKey(gateWayKey)){
							throw new BeanInitializationException("The gateway configuration already exists.[" + method.toString() + "]");
						}
						// 添加网关配置
						CONF_MAPPING.put(gateWayKey, method);
					}
				}
			}
		}
		packNames = null;
		classes = null;
	}

	/* (non-Javadoc)
	 * @see com.framework.cache.Cache#getData()
	 */
	@Override
	public Object getData() {
		
		return null;
	}

	public void setPackageNames(String packageNames) {
		this.packageNames = packageNames;
	}
	
	/**
	 * <pre>
	 * <b>通过网关配置获取执行方法.</b>
	 * @param gateWaymv method.version
	 * @return
	 * </per>
	 */
	public static Method getWayMethod(String gateWaymv){
		return CONF_MAPPING == null ? null : CONF_MAPPING.get(gateWaymv);
	}
}
