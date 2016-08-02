/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-common
 * @Title: NoSuchPropertyException.java
 * @Package com.alacoder.bee.common.bytecode
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月29日 下午2:15:59
 * @version V1.0
 */

package com.alacoder.bee.common.bytecode;

/**
 * @ClassName: NoSuchPropertyException
 * @Description: TODO
 * @author jimmy.zhong
 * @date 2016年7月29日 下午2:15:59
 *
 */

public class NoSuchPropertyException extends RuntimeException
{
	private static final long serialVersionUID = -2725364246023268766L;

	public NoSuchPropertyException()
	{
		super();
	}

	public NoSuchPropertyException(String msg)
	{
		super(msg);
	}
}