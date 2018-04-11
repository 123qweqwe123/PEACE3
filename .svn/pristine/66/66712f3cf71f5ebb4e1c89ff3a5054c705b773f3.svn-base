/**
 * JAVACC DEMO 1.0
 * @copy right zbxsoft company All rights reserved. 
 * @Package com.bdcor.pip.web.material.supp.service.impl 
 */
/**
 * JAVACC DEMO 1.0
 * @copy right zbxsoft company All rights reserved. 
 * @Package com.bdcor.pip.web.material.supp.service.impl  
 */

package com.bdcor.pip.web.material.supp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.material.supp.dao.PipMmsImscmdetalMapper;
import com.bdcor.pip.web.material.supp.domain.PipMmsImscmdetal;
import com.bdcor.pip.web.material.supp.filter.PipMmsImscmdetalFilter;
import com.bdcor.pip.web.material.supp.service.PipMmsImscmdetalService;

/**
 * description:
 * 
 * @author yangfeng 创建时间：2015-10-28
 */
@Transactional
@Service
public class PipMmsImscmdetalSerivceImpl implements PipMmsImscmdetalService {
	@Autowired
	private PipMmsImscmdetalMapper dao;

	/**
	 * @see com.bdcor.pip.web.material.supp.service.PipMmsImscmdetalService#list(com.bdcor.pip.web.material.supp.filter.PipMmsImscmdetalFilter)
	 */
	@Override
	public List<PipMmsImscmdetal> list(PipMmsImscmdetalFilter filter) {
		filter.setProjectId(Securitys.getUser().getCurrent_projectId());
		return dao.selectByFilter(filter);
	}

	@Override
	public int selectArchivesNo(PipMmsImscmdetal pmi) {
		return dao.selectArchivesNo(pmi);
	}

}
