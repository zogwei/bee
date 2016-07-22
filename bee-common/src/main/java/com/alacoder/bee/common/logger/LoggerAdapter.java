/**
 * 版权声明：bee 版权所有 违者必究 2015
 * Copyright: Copyright (c) 2015 
 *
 * 
 * @project_name: bee-common
 * @Title: LoggerAdapter.java
 * @Package com.bee.config
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月21日 上午10:31:40
 * @version V1.0
 */

package com.alacoder.bee.common.logger;

import java.io.File;

/**
 * 日志输出器供给器
 */
public interface LoggerAdapter {
	
	/**
	 * 获取日志输出器
	 *
	 * @param key 分类键
	 * @return 日志输出器, 后验条件: 不返回null.
	 */
	Logger getLogger(Class<?> key);

	/**
	 * 获取日志输出器
	 *
	 * @param key 分类键
	 * @return 日志输出器, 后验条件: 不返回null.
	 */
	Logger getLogger(String key);
	
	/**
	 * 设置输出等级
	 * 
	 * @param level 输出等级
	 */
	void setLevel(Level level);
	
	/**
	 * 获取当前日志等级
	 * 
	 * @return 当前日志等级
	 */
	Level getLevel();
	
	/**
	 * 获取当前日志文件
	 * 
	 * @return 当前日志文件
	 */
	File getFile();
	
	/**
	 * 设置输出日志文件
	 * 
	 * @param file 输出日志文件
	 */
	void setFile(File file);

}