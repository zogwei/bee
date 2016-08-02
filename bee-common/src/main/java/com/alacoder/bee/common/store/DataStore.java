/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-common
 * @Title: DataStore.java
 * @Package com.alacoder.bee.common.store
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月29日 下午4:43:54
 * @version V1.0
 */

package com.alacoder.bee.common.store;

import java.util.Map;

import com.alacoder.bee.common.extension.SPI;

/**
 * @ClassName: DataStore
 * @Description: TODO
 * @author jimmy.zhong
 * @date 2016年7月29日 下午4:43:54
 *
 */

@SPI("simple")
public interface DataStore {

    /**
     * return a snapshot value of componentName
     */
    Map<String,Object> get(String componentName);

    Object get(String componentName, String key);

    void put(String componentName, String key, Object value);

    void remove(String componentName, String key);

}
