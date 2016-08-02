/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-common
 * @Title: Activate.java
 * @Package com.alacoder.bee.common.extension
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月28日 下午5:55:50
 * @version V1.0
 */

package com.alacoder.bee.common.extension;

/**
 * @ClassName: Activate
 * @Description: TODO
 * @author jimmy.zhong
 * @date 2016年7月28日 下午5:55:50
 *
 */


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Activate
 * <p />
 * 对于可以被框架中自动激活加载扩展，此Annotation用于配置扩展被自动激活加载条件。
 * 比如，过滤扩展，有多个实现，使用Activate Annotation的扩展可以根据条件被自动加载。
 * <ol>
 * <li>{@link Activate#group()}生效的Group。具体的有哪些Group值由框架SPI给出。
 * <li>{@link Activate#value()}在{@link com.alibaba.dubbo.common.URL}中Key集合中有，则生效。
 * </ol>
 *
 * <p />
 * 底层框架SPI提供者通过{@link com.alibaba.dubbo.common.extension.ExtensionLoader}的{@link ExtensionLoader#getActivateExtension}方法
 * 获得条件的扩展。
 *
 * @author william.liangf
 * @author ding.lid
 * @export
 * @see SPI
 * @see ExtensionLoader
 * @see ExtensionLoader#getActivateExtension(com.alibaba.dubbo.common.URL, String[], String)
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Activate {
    /**
     * Group过滤条件。
     * <br />
     * 包含{@link ExtensionLoader#getActivateExtension}的group参数给的值，则返回扩展。
     * <br />
     * 如没有Group设置，则不过滤。
     */
    String[] group() default {};

    /**
     * Key过滤条件。包含{@link ExtensionLoader#getActivateExtension}的URL的参数Key中有，则返回扩展。
     * <p />
     * 示例：<br/>
     * 注解的值 <code>@Activate("cache,validatioin")</code>，
     * 则{@link ExtensionLoader#getActivateExtension}的URL的参数有<code>cache</code>Key，或是<code>validatioin</code>则返回扩展。
     * <br/>
     * 如没有设置，则不过滤。
     */
    String[] value() default {};

    /**
     * 排序信息，可以不提供。
     */
    String[] before() default {};

    /**
     * 排序信息，可以不提供。
     */
    String[] after() default {};

    /**
     * 排序信息，可以不提供。
     */
    int order() default 0;
}