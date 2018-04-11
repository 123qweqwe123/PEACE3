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
import java.util.Set;

import com.bdcor.pip.web.material.supp.domain.BooldExcelVo;
import com.bdcor.pip.web.material.supp.domain.PipMmsImscmmaster;
import com.bdcor.pip.web.material.supp.filter.PipMmsImscmmasterFilter;

public interface PipMmsImscmmasterService {
	public List<PipMmsImscmmaster> list(PipMmsImscmmasterFilter filter);

	public List<PipMmsImscmmaster> list();

	public void insert(PipMmsImscmmaster pmi);

	public void insertImscmdetalAndStockAndScmarchivesAndImscmmaster(
			Set<String> setBox, List<BooldExcelVo> excleList, String imorderNo,
			String stockCode, String stockName);

}
