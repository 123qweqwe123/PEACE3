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

import com.bdcor.pip.web.material.supp.domain.PipMmsScmarchives;
import com.bdcor.pip.web.material.supp.filter.PipMmsScmarchivesFilter;

/**
 * description:
 * 
 * @author yangfeng 创建时间：2015-10-29
 */

public interface PipMmsScmarchivesService {

	/**
	 * description:
	 * 
	 * @author yangfeng
	 * @param filter
	 * @return
	 * @update 2015-10-29
	 */
	List<PipMmsScmarchives> list(PipMmsScmarchivesFilter filter);

	/**
	 * 
	 * description:查询库存非0的
	 * 
	 * @author yangfeng
	 * @param filter
	 * @return
	 * @update 2015-11-27
	 */
	List<PipMmsScmarchives> listSimple(PipMmsScmarchivesFilter filter);

	/**
	 * description: 修改档案表的状态
	 * 
	 * @author yangfeng
	 * @param ids
	 * @param state
	 * @update 2015-11-2
	 */
	void changeState(String ids, String state) throws Exception;

}
