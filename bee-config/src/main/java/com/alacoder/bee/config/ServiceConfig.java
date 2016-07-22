/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-config
 * @Title: ServiceConfig.java
 * @Package com.bee.config
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月21日 下午3:29:43
 * @version V1.0
 */

package com.alacoder.bee.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alacoder.bee.config.annotation.Parameter;

/**
 * @ClassName: ServiceConfig
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月21日 下午3:29:43
 *
 */

public class ServiceConfig<T>  extends AbstractServiceConfig {

    private static final long   serialVersionUID = 3033787999037024738L;


    // 接口类型
    private String              interfaceName;

    private Class<?>            interfaceClass;

    // 接口实现类引用
    private T                   ref;

    // 服务名称
    private String              path;

    public T getRef() {
        return ref;
    }

    public void setRef(T ref) {
        this.ref = ref;
    }

    @Parameter(excluded = true)
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
