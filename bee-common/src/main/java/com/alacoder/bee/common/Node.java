/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-common
 * @Title: Node.java
 * @Package com.alacoder.bee.common
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月28日 下午4:02:57
 * @version V1.0
 */

package com.alacoder.bee.common;

/**
 * @ClassName: Node
 * @Description: TODO
 * @author jimmy.zhong
 * @date 2016年7月28日 下午4:02:57
 *
 */

public interface Node {

	URL getUrl();
	
	boolean isAvailable();
	
	void destroy();
}
