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
import com.bdcor.pip.web.material.supp.dao.PipMmsExscmdetalMapper;
import com.bdcor.pip.web.material.supp.domain.PipMmsExscmdetal;
import com.bdcor.pip.web.material.supp.domain.PipMmsExscmdetalExample;
import com.bdcor.pip.web.material.supp.domain.PipMmsExscmdetalExample.Criteria;
import com.bdcor.pip.web.material.supp.filter.PipMmsExscmdetalFilter;
import com.bdcor.pip.web.material.supp.service.PipMmsExscmdetalService;

/**  
 * description:  
 * @author yangfeng 创建时间：2015-11-4         
 */
@Service
@Transactional
public class PipMmsExscmdetalServiceImpl implements PipMmsExscmdetalService {
	@Autowired
	private PipMmsExscmdetalMapper mapper;

	/**
	 * @see com.bdcor.pip.web.material.supp.service.PipMmsExscmdetalService#list(com.bdcor.pip.web.material.supp.filter.PipMmsExscmdetalFilter)  
	 */
	@Override
	public List<PipMmsExscmdetal> list(PipMmsExscmdetalFilter filter) {
		return mapper.selectByFilter(filter);
	}
	
	/*@Override
	public List<PipMmsExscmdetal> list(PipMmsExscmdetalFilter filter) {
		PipMmsExscmdetalExample example = new PipMmsExscmdetalExample();
		Criteria criteria = example.createCriteria();
		criteria.andExorderNoEqualTo(filter.getExorderNo());
		criteria.andProjectIdEqualTo(Securitys.getUser().getCurrent_projectId());
		return mapper.selectByExample(example);
	}*/
	
}

