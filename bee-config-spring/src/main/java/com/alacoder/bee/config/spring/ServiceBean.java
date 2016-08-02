/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 *
 * 
 * @project_name: bee-config-spring
 * @Title: ServiceBean.java
 * @Package com.alacoder.bee.config.spring
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月21日 下午8:56:18
 * @version V1.0
 */

package com.alacoder.bee.config.spring;

import com.alacoder.bee.common.logger.Logger;
import com.alacoder.bee.common.logger.LoggerFactory;
import com.alacoder.bee.config.ServiceConfig;

/**
 * @ClassName: ServiceBean
 * @Description: TODO
 * @project_name: bee-config-spring
 * @author jimmy.zhong
 * @param <T>
 * @date 2016年7月21日 下午8:56:18
 *
 */

public class ServiceBean<T> extends ServiceConfig<T> {

	private static final long serialVersionUID = -3681994850463374388L;
	private static final Logger logger = LoggerFactory.getLogger(ServiceBean.class);
	
	public void export(){
		logger.info("ServiceBean export begin");
	}
}
