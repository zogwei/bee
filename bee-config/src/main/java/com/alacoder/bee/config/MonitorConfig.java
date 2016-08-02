/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-config
 * @Title: MonitorConfig.java
 * @Package com.bee.config
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月21日 下午2:35:45
 * @version V1.0
 */

package com.alacoder.bee.config;

import java.util.Map;

import com.alacoder.bee.config.annotation.Parameter;

/**
 * @ClassName: MonitorConfig
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月21日 下午2:35:45
 *
 */

public class MonitorConfig extends AbstractConfig {
	
	private static final long serialVersionUID = -1184681514659198203L;
	
	private String protocol;
	
	private String address;

    private String username;

    private String password;

	private String group;

    private String version;

    // 自定义参数
    private Map<String, String> parameters;

    // 是否为缺省
    private Boolean isDefault;
    
    public MonitorConfig() {
    }

    public MonitorConfig(String address) {
        this.address = address;
    }

    @Parameter(excluded = true)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Parameter(excluded = true)
    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    @Parameter(excluded = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Parameter(excluded = true)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        checkParameterName(parameters);
        this.parameters = parameters;
    }

    public Boolean isDefault() {
        return isDefault;
    }

    public void setDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

}