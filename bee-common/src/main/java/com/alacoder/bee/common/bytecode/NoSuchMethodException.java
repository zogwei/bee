/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-common
 * @Title: NoSuchMethodException.java
 * @Package com.alacoder.bee.common.bytecode
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月29日 下午2:15:42
 * @version V1.0
 */

package com.alacoder.bee.common.bytecode;

/**
 * @ClassName: NoSuchMethodException
 * @Description: TODO
 * @author jimmy.zhong
 * @date 2016年7月29日 下午2:15:42
 *
 */

public class NoSuchMethodException extends RuntimeException
{
	private static final long serialVersionUID = -2725364246023268766L;

	public NoSuchMethodException()
	{
		super();
	}

	public NoSuchMethodException(String msg)
	{
		super(msg);
	}
}