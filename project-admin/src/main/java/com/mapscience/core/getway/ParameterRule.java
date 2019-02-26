package com.mapscience.core.getway;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
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
