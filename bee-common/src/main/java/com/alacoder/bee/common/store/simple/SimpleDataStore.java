/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-common
 * @Title: SimpleDataStore.java
 * @Package com.alacoder.bee.common.store.simple
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月29日 下午4:44:29
 * @version V1.0
 */

package com.alacoder.bee.common.store.simple;

/**
 * @ClassName: SimpleDataStore
 * @Description: TODO
 * @author jimmy.zhong
 * @date 2016年7月29日 下午4:44:29
 *
 */

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.alacoder.bee.common.store.DataStore;

public class SimpleDataStore implements DataStore {

    // <组件类名或标识, <数据名, 数据值>>
    private ConcurrentMap<String, ConcurrentMap<String, Object>> data =
        new ConcurrentHashMap<String, ConcurrentMap<String,Object>>();

    public Map<String, Object> get(String componentName) {
        ConcurrentMap<String, Object> value = data.get(componentName);
        if(value == null) return new HashMap<String, Object>();

        return new HashMap<String, Object>(value);
    }

    public Object get(String componentName, String key) {
        if (!data.containsKey(componentName)) {
            return null;
        }
        return data.get(componentName).get(key);
    }

    public void put(String componentName, String key, Object value) {
        Map<String, Object> componentData = data.get(componentName);
        if(null == componentData) {
            data.putIfAbsent(componentName, new ConcurrentHashMap<String, Object>());
            componentData = data.get(componentName);
        }
        componentData.put(key, value);
    }

    public void remove(String componentName, String key) {
        if (!data.containsKey(componentName)) {
            return;
        }
        data.get(componentName).remove(key);
    }

}
