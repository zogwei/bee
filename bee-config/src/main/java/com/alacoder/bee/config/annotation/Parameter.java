/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-config
 * @Title: Parameter.java
 * @Package com.bee.config.annotation
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月21日 下午2:46:46
 * @version V1.0
 */

package com.alacoder.bee.config.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: Parameter
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月21日 下午2:46:46
 *
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Parameter {

    String key() default "";
    
    boolean required() default false;
    
    boolean excluded() default false;

    boolean escaped() default false;
    
    boolean attribute() default false;

    boolean append() default false;
    
}
