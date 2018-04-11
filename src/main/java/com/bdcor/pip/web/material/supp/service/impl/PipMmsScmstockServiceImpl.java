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
import com.bdcor.pip.web.material.supp.dao.PipMmsScmstockMapper;
import com.bdcor.pip.web.material.supp.domain.PipMmsScmstock;
import com.bdcor.pip.web.material.supp.filter.PipMmsScmstockFilter;
import com.bdcor.pip.web.material.supp.service.PipMmsScmstockService;

/**
 * description:
 * 
 * @author yangfeng 创建时间：2015-10-30
 */

@Transactional
@Service
public class PipMmsScmstockServiceImpl implements PipMmsScmstockService {
	@Autowired
	private PipMmsScmstockMapper mapper;

	/**
	 * @see com.bdcor.pip.web.material.supp.service.PipMmsScmstockService#list(com.bdcor.pip.web.material.supp.filter.PipMmsScmstockFilter)
	 */
	@Override
	public List<PipMmsScmstock> list(PipMmsScmstockFilter filter) {
		filter.setProjectId(Securitys.getUser().getCurrent_projectId());
		filter.setUserId(Securitys.getUserId());
		return mapper.list(filter);
	}
	@Override
	public List<PipMmsScmstock> archivesNoAllNoUse() {
		return mapper.archivesNoAllNoUse();
	}
	@Override
	public List<PipMmsScmstock> archivesNoAllByLccCode() {
		return mapper.archivesNoAllByLccCode(Securitys.getUser().getLccCode());
	}

}
