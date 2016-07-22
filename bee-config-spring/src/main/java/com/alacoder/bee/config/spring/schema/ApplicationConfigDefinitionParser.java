/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 *
 * 
 * @project_name: bee-config-spring
 * @Title: BeeBeanDefinitionParser.java
 * @Package com.bee.config.spring.schema
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月21日 下午4:45:22
 * @version V1.0
 */

package com.alacoder.bee.config.spring.schema;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.beans.factory.xml.XmlReaderContext;
import org.springframework.core.env.Environment;
import org.w3c.dom.Element;

import com.alacoder.bee.common.logger.Logger;
import com.alacoder.bee.common.logger.LoggerFactory;

/**
 * @ClassName: BeeBeanDefinitionParser
 * @Description: 
 * @project_name: bee-config-spring
 * @author jimmy.zhong
 * @date 2016年7月21日 下午4:45:22
 *
 */

public class ApplicationConfigDefinitionParser implements BeanDefinitionParser {
	
	private static final Logger logger = LoggerFactory.getLogger(ApplicationConfigDefinitionParser.class);
	
	private final Class<?> beanClass;
	
	private final boolean required;
	
	public ApplicationConfigDefinitionParser(Class<?> beanClass,boolean required) {
		this.beanClass = beanClass;
		this.required = required;
	}

	@Override
	public BeanDefinition parse(Element element, ParserContext parserContext) {
		RootBeanDefinition beanDefinition = new RootBeanDefinition();
		beanDefinition.setBeanClass(beanClass);
		beanDefinition.setLazyInit(false);
		beanDefinition = setId(element,parserContext,beanDefinition);
		
		return beanDefinition;
	}
	
	private RootBeanDefinition setId(Element element, ParserContext parserContext,final RootBeanDefinition beanDefinition){
		String id = getId(element,parserContext,beanDefinition);
		if(id!=null&&id.length() >0){
			if(parserContext.getRegistry().containsBeanDefinition(id)){
				throw new IllegalStateException(" Duplication spring bean id " + id);
			}
			parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);
		}
		
		return beanDefinition;
	}
	
	private String getId(Element element, ParserContext parserContext,RootBeanDefinition beanDefinition){
		String id = element.getAttribute("id");
		if((id == null || id.length() == 0) && required){
			String generatedBeanName = element.getAttribute("name");
			if( generatedBeanName == null || generatedBeanName.length() == 0){
				generatedBeanName = element.getAttribute("interface");
			}
			if( generatedBeanName == null || generatedBeanName.length() == 0){
				generatedBeanName = beanClass.getName();
			}
			id = generatedBeanName;
			int counter = 1;
			while(parserContext.getRegistry().containsBeanDefinition(id)){
				id = generatedBeanName + (counter++);
			}
		}
		
		return id;
	}
}
