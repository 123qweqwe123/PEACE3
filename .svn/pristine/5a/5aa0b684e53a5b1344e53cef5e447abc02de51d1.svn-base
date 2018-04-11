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

import com.bdcor.pip.web.material.supp.domain.OrderDetal;
import com.bdcor.pip.web.material.supp.domain.PipMmsExscmdetal;
import com.bdcor.pip.web.material.supp.domain.PipMmsExscmmaster;
import com.bdcor.pip.web.material.supp.filter.OrderDetalFilter;
import com.bdcor.pip.web.material.supp.filter.PipMmsExscmmasterFilter;

public interface PipMmsExscmmasterService {
	public List<PipMmsExscmmaster> list(PipMmsExscmmasterFilter filter);

	public List<PipMmsExscmmaster> selectPipMmsExscmmasterByLccCode(
			PipMmsExscmmaster pmem);

	public List<OrderDetal> selectPipMmsExscmmasterByArchivesNo(OrderDetal od);

	public void saveExscmmasterAndDetalUpdateStorck(OrderDetalFilter odf);

	/**
	 * description:
	 * 
	 * @author yangfeng 修改状态 1 出库 2 接受
	 * @param ids
	 * @param state
	 * @update 2015-11-4
	 */
	public void receive(String ids, String state) throws Exception;
	public List<PipMmsExscmmaster> listByIds(String ids) throws Exception;
	public List<PipMmsExscmdetal> getExportDetail(String exorderNo);
}
