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

import com.bdcor.pip.web.material.supp.domain.PipMmsImscmdetal;
import com.bdcor.pip.web.material.supp.filter.PipMmsImscmdetalFilter;

/**
 * description:
 * 
 * @author yangfeng 创建时间：2015-10-28
 */

public interface PipMmsImscmdetalService {

	public List<PipMmsImscmdetal> list(PipMmsImscmdetalFilter filter);
	
	//通过箱号查询是否已经入库过此箱号
	public int selectArchivesNo(PipMmsImscmdetal pmi);
	
	
}
