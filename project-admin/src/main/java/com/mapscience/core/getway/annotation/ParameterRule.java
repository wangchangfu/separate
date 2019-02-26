/**
 * 
 */
package com.mapscience.core.getway.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target({ FIELD, PARAMETER })
/**
 *说明：
 *<p> 可以验证是否为空、长度、正则表达式</p>
 * 书写者 @author  WCF
 * 创建时间 2018年12月3日
 *
 */
public @interface ParameterRule {
	/** 是否必填: ture=是;false=否 默认true */
	boolean required() default true;

	/** 限制长度. */
	int minLength() default 0;
	
	/** 限制长度. */
	int maxLength() default Integer.MAX_VALUE;
	
	/** 支持正则表达式,优先验证必填. */
	String regEx() default "";

	/** 正则表达式错误消息. */
	String message() default "";

}
