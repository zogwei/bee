/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 *
 * 
 * @project_name: bee-config-spring
 * @Title: ProviderDemoServiceImpl.java
 * @Package com.alacoder.bee.demo
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月21日 下午8:37:32
 * @version V1.0
 */

package com.alacoder.bee.config.spring.demo;

import com.alacoder.bee.common.logger.Logger;
import com.alacoder.bee.common.logger.LoggerFactory;
import com.alacoder.bee.config.spring.schema.BeeBeanDefinitionParser;

/**
 * @ClassName: ProviderDemoServiceImpl
 * @Description: TODO
 * @project_name: bee-config-spring
 * @author jimmy.zhong
 * @date 2016年7月21日 下午8:37:32
 *
 */

public class ProviderDemoServiceImpl implements ProviderDemoService {
	
	 private static final Logger logger = LoggerFactory.getLogger(BeeBeanDefinitionParser.class);

	@Override
	public String provideMethod1(String param) {
		logger.info("ProviderDemoServiceImpl provideMethod1 run ");
		return  "provider method ";
	}
	
	
	@Override
	public Integer provideMethod2(String param) {
		
		logger.info("ProviderDemoServiceImpl provideMethod2 run ");
		
		return new Integer(2);
	}

}
