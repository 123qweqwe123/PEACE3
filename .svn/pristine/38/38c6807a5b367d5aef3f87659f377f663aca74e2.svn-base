/**
 * JAVACC DEMO 1.0
 * @copy right zbxsoft company All rights reserved. 
 * @Package com.bdcor.pip.web.material.supp.service 
 */
/**
 * JAVACC DEMO 1.0
 * @copy right zbxsoft company All rights reserved. 
 * @Package com.bdcor.pip.web.material.supp.service  
 */

package com.bdcor.pip.web.material.supp.service;

import java.util.List;

import com.bdcor.pip.web.material.supp.domain.PipMmsDefstorehouse;

/**
 * description: 库房业务
 * 
 * @author yangfeng 创建时间：2015-10-29
 */
public interface PipMmsDefstorehouseService {

	/**
	 * 
	 * description: 按条件获取库房信息
	 * 
	 * @author yangfeng
	 * @param pmdf
	 * @return
	 * @update 2015-11-9
	 */
	public List<PipMmsDefstorehouse> getAllHouseListByLccCode(
			PipMmsDefstorehouse pmdf);

	/**
	 * 
	 * description: 通过lccCode 获取库房信息
	 * 
	 * @author yangfeng
	 * @param lccCode
	 * @return
	 * @update 2015-11-9
	 */
	public List<PipMmsDefstorehouse> getAllHouseListByLccCode(String lccCode);

	/**
	 * 
	 * description: 获取当前用户具有权限的库房信息
	 * 
	 * @author yangfeng
	 * @return
	 * @update 2015-11-2
	 */
	List<PipMmsDefstorehouse> listAndLimit();

	/**
	 * description:
	 * 
	 * @author yangfeng
	 * @param b
	 * @return
	 * @update 2015-12-9
	 */
	public List<PipMmsDefstorehouse> listAndLimitLcc();
}
