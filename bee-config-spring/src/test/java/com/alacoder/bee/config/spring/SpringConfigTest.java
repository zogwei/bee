/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 *
 * 
 * @project_name: bee-config-spring
 * @Title: SpringConfigTest.java
 * @Package com.alacoder.bee.config.spring
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月21日 下午8:30:05
 * @version V1.0
 */

package com.alacoder.bee.config.spring;

import java.io.IOException;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alacoder.bee.config.spring.demo.ProviderDemoService;

/**
 * @ClassName: SpringConfigTest
 * @Description: 
 * @project_name: bee-config-spring
 * @author jimmy.zhong
 * @date 2016年7月21日 下午8:30:05
 *
 */

public class SpringConfigTest {


	@Test
	public void testSpringConfig() {
		String config = ProviderDemoService.class.getPackage().getName().replace('.', '/')+ "/bee-provider-test.xml";
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(config);
		context.start();
		try {
			ServiceBean beeService =  context.getBean("beeService",ServiceBean.class);
			beeService.export();
			
			ProviderDemoService demoService =  context.getBean("providerdemoService",ProviderDemoService.class);
			demoService.provideMethod1("provide method1");
			
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	public static void main(String[] args) {
		(new SpringConfigTest()).testSpringConfig();
	}

}
