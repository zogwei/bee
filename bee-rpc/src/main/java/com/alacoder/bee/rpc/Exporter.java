/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-rpc
 * @Title: Exporter.java
 * @Package com.alacoder.bee.rpc
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月28日 下午2:44:41
 * @version V1.0
 */

package com.alacoder.bee.rpc;

/**
 * @ClassName: Exporter
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月28日 下午2:44:41
 *
 */

public interface Exporter<T> {

	Invoker<T> getInvoker();
	
	
	void unexport();
}
