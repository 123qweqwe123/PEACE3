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
import com.bdcor.pip.web.material.supp.dao.PipMmsScmarchivesMapper;
import com.bdcor.pip.web.material.supp.domain.PipMmsScmarchives;
import com.bdcor.pip.web.material.supp.filter.PipMmsScmarchivesFilter;
import com.bdcor.pip.web.material.supp.service.PipMmsScmarchivesService;

/**
 * description:
 * 
 * @author yangfeng 创建时间：2015-10-29
 */
@Transactional
@Service
public class PipMmsScmarchivesServiceImpl implements PipMmsScmarchivesService {

	@Autowired
	private PipMmsScmarchivesMapper mapper;

	/**
	 * @see com.bdcor.pip.web.material.supp.service.PipMmsScmarchivesService#list(com.bdcor.pip.web.material.supp.filter.PipMmsScmarchivesFilter)
	 */
	@Override
	public List<PipMmsScmarchives> list(PipMmsScmarchivesFilter filter) {
		filter.setUserId(Securitys.getUserId());
		filter.setProjectId(Securitys.getUser().getCurrent_projectId());
		return mapper.selectByFilter(filter);
	}

	@Override
	public List<PipMmsScmarchives> listSimple(PipMmsScmarchivesFilter filter) {
		filter.setUserId(Securitys.getUserId());
		filter.setProjectId(Securitys.getUser().getCurrent_projectId());
		return mapper.selectSimpleByFilter(filter);
	}

	/**
	 * 修改档案表的状态 1 使用 2 未使用 3 报损 4 过期
	 */
	@Override
	public void changeState(String ids, String state) throws Exception {
		mapper.updateState(ids, state);
	}


	public void scheduleChangeState(){
		mapper.scheduleChangeState();
	}
}
