/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-common
 * @Title: Compiler.java
 * @Package com.alacoder.bee.common.compiler
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月28日 下午5:57:20
 * @version V1.0
 */

package com.alacoder.bee.common.compiler;

import com.alacoder.bee.common.extension.SPI;



/**
 * Compiler. (SPI, Singleton, ThreadSafe)
 * 
 * @author william.liangf
 */
@SPI("javassist")
public interface Compiler {

	/**
	 * Compile java source code.
	 * 
	 * @param code Java source code
	 * @param classLoader 
	 * @return Compiled class
	 */
	Class<?> compile(String code, ClassLoader classLoader);

}
