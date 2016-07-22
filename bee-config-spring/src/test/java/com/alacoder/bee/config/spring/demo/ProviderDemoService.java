/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 *
 * 
 * @project_name: bee-config-spring
 * @Title: ProviderDemoService.java
 * @Package com.alacoder.bee.demo
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月21日 下午8:36:19
 * @version V1.0
 */

package com.alacoder.bee.config.spring.demo;

/**
 * @ClassName: ProviderDemoService
 * @Description: TODO
 * @project_name: bee-config-spring
 * @author jimmy.zhong
 * @date 2016年7月21日 下午8:36:19
 *
 */

public interface ProviderDemoService {

	public String provideMethod1(String param);
	
	public Integer provideMethod2(String param);
}
