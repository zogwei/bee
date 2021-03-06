/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-config
 * @Title: AbstractServiceConfig.java
 * @Package com.bee.config
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月21日 下午3:30:27
 * @version V1.0
 */

package com.alacoder.bee.config;

import java.util.Arrays;
import java.util.List;

import com.alacoder.bee.common.Constants;
import com.alacoder.bee.config.annotation.Parameter;

/**
 * @ClassName: AbstractServiceConfig
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月21日 下午3:30:27
 *
 */

public abstract class AbstractServiceConfig extends AbstractInterfaceConfig {

    private static final long      serialVersionUID = 1L;

    // 服务版本
    protected String               version;

    // 服务分组
    protected String               group;

    // 服务是否已经deprecated
    protected Boolean              deprecated;

    // 延迟暴露
    protected Integer              delay;

    // 是否暴露
    protected Boolean              export;

    // 权重
    protected Integer              weight;

    // 应用文档
    protected String               document;

    // 在注册中心上注册成动态的还是静态的服务
    protected Boolean              dynamic;

    // 是否使用令牌
    protected String               token;

    // 访问日志
    protected String               accesslog;

    // 允许执行请求数
    private Integer                executes;

    protected List<ProtocolConfig> protocols;

    // 是否注册
    private Boolean                register;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        checkKey("version", version);
        this.version = version;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        checkKey("group", group);
        this.group = group;
    }

	public Integer getDelay() {
		return delay;
	}

	public void setDelay(Integer delay) {
	    this.delay = delay;
	}

    public Boolean getExport() {
        return export;
    }

    public void setExport(Boolean export) {
        this.export = export;
    }
    
    public Integer getWeight() {
        return weight;
    }
    
    public void setWeight(Integer weight) {
        this.weight = weight;
    }
    
    @Parameter(escaped = true)
    public String getDocument() {
        return document;
    }
    
    public void setDocument(String document) {
        this.document = document;
    }

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
	    checkName("token", token);
		this.token = token;
	}
	
	public void setToken(Boolean token) {
        if (token == null) {
            setToken((String) null);
        } else {
            setToken(String.valueOf(token));
        }
    }

    public Boolean isDeprecated() {
        return deprecated;
    }

    public void setDeprecated(Boolean deprecated) {
        this.deprecated = deprecated;
    }

    public Boolean isDynamic() {
        return dynamic;
    }

    public void setDynamic(Boolean dynamic) {
        this.dynamic = dynamic;
    }

    public List<ProtocolConfig> getProtocols() {
        return protocols;
    }

    @SuppressWarnings({ "unchecked" })
    public void setProtocols(List<? extends ProtocolConfig> protocols) {
        this.protocols = (List<ProtocolConfig>)protocols;
    }

    public ProtocolConfig getProtocol() {
        return protocols == null || protocols.size() == 0 ? null : protocols.get(0);
    }

    public void setProtocol(ProtocolConfig protocol) {
        this.protocols = Arrays.asList(new ProtocolConfig[] {protocol});
    }

    public String getAccesslog() {
        return accesslog;
    }

    public void setAccesslog(String accesslog) {
        this.accesslog = accesslog;
    }
    
    public void setAccesslog(Boolean accesslog) {
        if (accesslog == null) {
            setAccesslog((String) null);
        } else {
            setAccesslog(String.valueOf(accesslog));
        }
    }

    public Integer getExecutes() {
        return executes;
    }
    
    public void setExecutes(Integer executes) {
        this.executes = executes;
    }

    @Parameter(key = Constants.SERVICE_FILTER_KEY, append = true)
    public String getFilter() {
        return super.getFilter();
    }

    @Parameter(key = Constants.EXPORTER_LISTENER_KEY, append = true)
    public String getListener() {
        return super.getListener();
    }

    @Override
    public void setListener(String listener) {
//        checkMultiExtension(ExporterListener.class, "listener", listener);
        super.setListener(listener);
    }

    public Boolean isRegister() {
        return register;
    }

    public void setRegister(Boolean register) {
        this.register = register;
        if (Boolean.FALSE.equals(register)) {
            setRegistry(new RegistryConfig(RegistryConfig.NO_AVAILABLE));
        }
    }
}
