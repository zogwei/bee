/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-config
 * @Title: ArgumentConfig.java
 * @Package com.alacoder.bee.config
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月27日 下午4:11:55
 * @version V1.0
 */

package com.alacoder.bee.config;

import java.io.Serializable;

import com.alacoder.bee.config.annotation.Parameter;

/**
 * @ClassName: ArgumentConfig
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月27日 下午4:11:55
 *
 */

public class ArgumentConfig implements Serializable {

    private static final long serialVersionUID = -2165482463925213595L;

    //arugment index -1 represents not set
    private Integer index = -1;

    //argument type
    private String  type;
    
    //callback interface
    private Boolean callback;

    public void setIndex(Integer index) {
        this.index = index;
    }
    @Parameter(excluded = true)
    public Integer getIndex() {
        return index;
    }
    @Parameter(excluded = true)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCallback(Boolean callback) {
        this.callback = callback;
    }

    public Boolean isCallback() {
        return callback;
    }

}