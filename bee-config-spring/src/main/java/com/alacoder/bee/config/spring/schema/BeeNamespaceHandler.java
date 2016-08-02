/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 *
 * 
 * @project_name: bee-config-spring
 * @Title: BeeNamespaceHandler.java
 * @Package com.bee.config.spring.schema
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月21日 下午4:42:24
 * @version V1.0
 */

package com.alacoder.bee.config.spring.schema;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

import com.alacoder.bee.config.ApplicationConfig;
import com.alacoder.bee.config.ConsumerConfig;
import com.alacoder.bee.config.ModuleConfig;
import com.alacoder.bee.config.MonitorConfig;
import com.alacoder.bee.config.ProtocolConfig;
import com.alacoder.bee.config.ProviderConfig;
import com.alacoder.bee.config.RegistryConfig;
import com.alacoder.bee.config.spring.AnnotationBean;
import com.alacoder.bee.config.spring.ReferenceBean;
import com.alacoder.bee.config.spring.ServiceBean;

/**
 * @ClassName: BeeNamespaceHandler
 * @Description: 
 * @project_name: bee-config-spring
 * @author jimmy.zhong
 * @date 2016年7月21日 下午4:42:24
 *
 */

public class BeeNamespaceHandler extends NamespaceHandlerSupport {
	
	@Override
	public void init() {
	    registerBeanDefinitionParser("application", new BeeBeanDefinitionParser(ApplicationConfig.class, true));
        registerBeanDefinitionParser("module", new BeeBeanDefinitionParser(ModuleConfig.class, true));
        registerBeanDefinitionParser("registry", new BeeBeanDefinitionParser(RegistryConfig.class, true));
        registerBeanDefinitionParser("monitor", new BeeBeanDefinitionParser(MonitorConfig.class, true));
        registerBeanDefinitionParser("provider", new BeeBeanDefinitionParser(ProviderConfig.class, true));
        registerBeanDefinitionParser("consumer", new BeeBeanDefinitionParser(ConsumerConfig.class, true));
        registerBeanDefinitionParser("protocol", new BeeBeanDefinitionParser(ProtocolConfig.class, true));
        registerBeanDefinitionParser("service", new BeeBeanDefinitionParser(ServiceBean.class, true));
        registerBeanDefinitionParser("reference", new BeeBeanDefinitionParser(ReferenceBean.class, false));
        registerBeanDefinitionParser("annotation", new BeeBeanDefinitionParser(AnnotationBean.class, true));	 
	}

}
