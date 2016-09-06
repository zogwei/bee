/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-rpc
 * @Title: Invocation.java
 * @Package com.alacoder.bee.rpc
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月28日 下午2:52:17
 * @version V1.0
 */

package com.alacoder.bee.rpc;

import java.util.Map;

/**
 * @ClassName: Invocation
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月28日 下午2:52:17
 *
 */

public interface Invocation {

	String getMethodName();
	
	Class<?>[] getParameterTypes();
	
	Object[] getArguments();
	
	Map<String, String> getAttachments();
	
	String getAttachment(String key);
	
	String getAttachment(String key, String defaultValue);
	
	Invoker<?> getInvoker();
}
