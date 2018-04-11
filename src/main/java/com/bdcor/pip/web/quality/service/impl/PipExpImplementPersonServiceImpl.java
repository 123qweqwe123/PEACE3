/**
 * JAVACC DEMO 1.0
 * @copy right zbxsoft company All rights reserved. 
 * @Package com.bdcor.pip.web.quality.service.impl 
 */
/**
 * JAVACC DEMO 1.0
 * @copy right zbxsoft company All rights reserved. 
 * @Package com.bdcor.pip.web.quality.service.impl  
 */

package com.bdcor.pip.web.quality.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.quality.dao.PipExpImplementPersonMapper;
import com.bdcor.pip.web.quality.domain.PipExpImplementPerson;
import com.bdcor.pip.web.quality.domain.PipExpImplementPersonExample;
import com.bdcor.pip.web.quality.domain.PipExpImplementPersonExample.Criteria;
import com.bdcor.pip.web.quality.filter.PipExpImplementPersonFilter;
import com.bdcor.pip.web.quality.service.PipExpImplementPersonService;

/**
 * description:
 * 
 * @author yangfeng 创建时间：2015-11-11
 */
@Service
@Transactional
public class PipExpImplementPersonServiceImpl implements
		PipExpImplementPersonService {
	@Autowired
	private PipExpImplementPersonMapper mapper;

	/**
	 * @see com.bdcor.pip.web.quality.service.PipExpImplementPersonService#listByExample(com.bdcor.pip.web.quality.filter.PipExpImplementPersonFilter)
	 */
	@Override
	public List<PipExpImplementPerson> listByExample(
			PipExpImplementPersonFilter filter) {
		return mapper.selectByFilter(filter);
	}

	/**
	 * @see com.bdcor.pip.web.quality.service.PipExpImplementPersonService#selectByLccCodeUserCode(com.bdcor.pip.web.quality.domain.PipExpImplementPerson)  
	 */
	@Override
	public PipExpImplementPerson selectByLccCodeUserCode(
			PipExpImplementPerson filter) {
		PipExpImplementPersonExample example= new PipExpImplementPersonExample();
		Criteria criteria = example.createCriteria();
		if(!StringUtils.isEmpty(filter.getLccCode())){
			criteria.andLccCodeEqualTo(filter.getLccCode());
		}
		if(!StringUtils.isEmpty(filter.getLccUserId())){
			criteria.andLccUserIdEqualTo(filter.getLccUserId());
		}
		if(!StringUtils.isEmpty(filter.getPersonType())){
			criteria.andPersonTypeEqualTo(filter.getPersonType());
		}
		criteria.andImplementIdEqualTo(filter.getImplementId());
		criteria.andProjectIdEqualTo(Securitys.getUser().getCurrent_projectId());
		List<PipExpImplementPerson> selectByExample = mapper.selectByExample(example);
		if(selectByExample.size()<=0){
			return null;
		}
		return mapper.selectByExample(example).get(0);
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.  
	 * @see com.bdcor.pip.web.quality.service.PipExpImplementPersonService#save(com.bdcor.pip.web.quality.domain.PipExpImplementPerson)  
	 */
	@Override
	public void save(PipExpImplementPerson pipExpImplementPerson) {
		pipExpImplementPerson.setCreateBy(Securitys.getUserId());
		pipExpImplementPerson.setCreateDate(new Date());
		pipExpImplementPerson.setProjectId(Securitys.getUser().getCurrent_projectId());
		mapper.insert(pipExpImplementPerson);
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.  
	 * @see com.bdcor.pip.web.quality.service.PipExpImplementPersonService#deleteBatch(java.lang.String)  
	 */
	@Override
	public void deleteBatch(String ids) {
		mapper.deleteBatch(ids);
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.  
	 * @see com.bdcor.pip.web.quality.service.PipExpImplementPersonService#selectByFk(java.lang.String)  
	 */
	@Override
	public List<PipExpImplementPerson> selectByFk(String fkId) {
		PipExpImplementPersonFilter filter =new PipExpImplementPersonFilter();
		filter.setImplementId(fkId);
		return mapper.selectByFilter(filter);
	}
}
