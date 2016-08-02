/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-common
 * @Title: ExtensionFactory.java
 * @Package com.alacoder.bee.common.extension
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月28日 下午5:54:58
 * @version V1.0
 */

package com.alacoder.bee.common.extension;

/**
 * @ClassName: ExtensionFactory
 * @Description: TODO
 * @author jimmy.zhong
 * @date 2016年7月28日 下午5:54:58
 *
 */

@SPI
public interface ExtensionFactory {

    /**
     * Get extension.
     * 
     * @param type object type.
     * @param name object name.
     * @return object instance.
     */
    <T> T getExtension(Class<T> type, String name);

}

