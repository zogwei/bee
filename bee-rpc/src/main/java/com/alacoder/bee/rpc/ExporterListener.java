/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-rpc
 * @Title: ExporterListener.java
 * @Package com.alacoder.bee.rpc
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月28日 下午3:31:50
 * @version V1.0
 */

package com.alacoder.bee.rpc;

import com.alacoder.bee.common.extension.SPI;

/**
 * @ClassName: ExporterListener
 * @Description: TODO
 * @author jimmy.zhong
 * @date 2016年7月28日 下午3:31:50
 *
 */
@SPI
public interface ExporterListener {

	void exported(Exporter<?> exporter) throws RpcException;
	
	void unexported(Exporter<?> exporter) throws RpcException;
	
}
