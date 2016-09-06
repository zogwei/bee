/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-common
 * @Title: Adaptive.java
 * @Package com.alacoder.bee.common.extension
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月28日 下午5:56:11
 * @version V1.0
 */

package com.alacoder.bee.common.extension;

/**
 * @ClassName: Adaptive
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月28日 下午5:56:11
 *
 */


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在{@link ExtensionLoader}生成Extension的Adaptive Instance时，为{@link ExtensionLoader}提供信息。
 * 
 * @author ding.lid
 * @export
 * 
 * @see ExtensionLoader
 * @see URL
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Adaptive {
    
    /**
     * 从{@link URL}的Key名，对应的Value作为要Adapt成的Extension名。
     * <p>
     * 如果{@link URL}这些Key都没有Value，使用 用 缺省的扩展（在接口的{@link SPI}中设定的值）。<br>
     * 比如，<code>String[] {"key1", "key2"}</code>，表示
     * <ol>
     * <li>先在URL上找key1的Value作为要Adapt成的Extension名；
     * <li>key1没有Value，则使用key2的Value作为要Adapt成的Extension名。
     * <li>key2没有Value，使用缺省的扩展。
     * <li>如果没有设定缺省扩展，则方法调用会抛出{@link IllegalStateException}。
     * </ol>
     * <p>
     * 如果不设置则缺省使用Extension接口类名的点分隔小写字串。<br>
     * 即对于Extension接口{@code com.alibaba.dubbo.xxx.YyyInvokerWrapper}的缺省值为<code>String[] {"yyy.invoker.wrapper"}</code>
     * 
     * @see SPI#value()
     */
    String[] value() default {};
    
}