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

import oracle.net.aso.f;
import oracle.net.aso.s;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.quality.dao.PipExpImplementProblemMapper;
import com.bdcor.pip.web.quality.domain.PipExpImplementProblem;
import com.bdcor.pip.web.quality.domain.PipExpImplementProblemExample;
import com.bdcor.pip.web.quality.domain.PipExpImplementProblemExample.Criteria;
import com.bdcor.pip.web.quality.filter.PipExpImplementProblemFilter;
import com.bdcor.pip.web.quality.service.PipExpImplementProblemService;

/**  
 * description:  
 * @author yangfeng 创建时间：2015-11-12         
 */
@Service
@Transactional
public class PipExpImplementProblemServiceImpl implements
		PipExpImplementProblemService {
	@Autowired
	private PipExpImplementProblemMapper mapper;
	/**
	 * @see com.bdcor.pip.web.quality.service.PipExpImplementProblemService#insert(com.bdcor.pip.web.quality.domain.PipExpImplementProblem)  
	 */
	@Override
	public void insert(PipExpImplementProblem problem) {
		problem.setCreateBy(Securitys.getUserId());
		problem.setCreateDate(new Date());
		problem.setProjectId(Securitys.getUser().getCurrent_projectId());
		if(!StringUtils.isEmpty(problem.getStatus())){
			if(problem.getStatus().equals("1")){
				problem.setOnStatusDate(new Date());
			}
			if(problem.getStatus().equals("2")){
				problem.setOffStatusDate(new Date());
			}
		}
		mapper.insert(problem);
	}

	/**
	 * @see com.bdcor.pip.web.quality.service.PipExpImplementProblemService#listByExample(com.bdcor.pip.web.quality.filter.PipExpImplementProblemFilter)  
	 */
	@Override
	public List<PipExpImplementProblem> listByExample(
			PipExpImplementProblemFilter filter) {
		return mapper.selectByFilter(filter);
	}

	/**
	 * @see com.bdcor.pip.web.quality.service.PipExpImplementProblemService#update(com.bdcor.pip.web.quality.domain.PipExpImplementProblem)  
	 */
	@Override
	public void update(PipExpImplementProblem problem) {
		problem.setUpdateBy(Securitys.getUserId());
		problem.setUpdateDate(new Date());
		if(!StringUtils.isEmpty(problem.getStatus())){
			if(problem.getStatus().equals("1")){
				problem.setOnStatusDate(new Date());
			}
			if(problem.getStatus().equals("2")){
				problem.setOffStatusDate(new Date());
			}
		}
		mapper.updateByPrimaryKeySelective(problem);
	}

	/**
	 * @see com.bdcor.pip.web.quality.service.PipExpImplementProblemService#delete(java.lang.String)  
	 */
	@Override
	public void delete(String id) {
		mapper.deleteByPrimaryKey(id);
	}

	/**
	 * @see com.bdcor.pip.web.quality.service.PipExpImplementProblemService#deleteBatch(java.lang.String)  
	 */
	@Override
	public void deleteBatch(String ids) {
		mapper.deleteBatch(ids);
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.  
	 * @see com.bdcor.pip.web.quality.service.PipExpImplementProblemService#checkIsExist(java.lang.String)  
	 */
	@Override
	public boolean checkIsExist(String lccUserId) {
		PipExpImplementProblemExample example = new PipExpImplementProblemExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andLccUserIdEqualTo(lccUserId);
		List<PipExpImplementProblem> selectByExample = mapper.selectByExample(example);
		if(selectByExample.size()<=0){
			return false;
		}
		return true;
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.  
	 * @see com.bdcor.pip.web.quality.service.PipExpImplementProblemService#checkIsExistAndNotSelf(java.lang.String, java.lang.String)  
	 */
	@Override
	public boolean checkIsExistAndNotSelf(String lccUserId, String id) {
		PipExpImplementProblemExample example= new PipExpImplementProblemExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdNotEqualTo(id);
		criteria.andLccUserIdEqualTo(lccUserId);
		List<PipExpImplementProblem> selectByExample = mapper.selectByExample(example);
		if(selectByExample.size()<=0){
			return false;
		}
		return true;
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.  
	 * @see com.bdcor.pip.web.quality.service.PipExpImplementProblemService#selectByFk(java.lang.String)  
	 */
	@Override
	public List<PipExpImplementProblem> selectByFk(String implementId) {
		PipExpImplementProblemFilter filter = new PipExpImplementProblemFilter();
		filter.setImplementId(implementId);
		return mapper.selectByFilter(filter);
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.  
	 * @see com.bdcor.pip.web.quality.service.PipExpImplementProblemService#list(com.bdcor.pip.web.quality.filter.PipExpImplementProblemFilter)  
	 */
	@Override
	public List<PipExpImplementProblem> list(PipExpImplementProblemFilter filter) {
		return mapper.selectByFilter(filter);
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.  
	 * @see com.bdcor.pip.web.quality.service.PipExpImplementProblemService#updateStatus(java.lang.String, java.lang.String)  
	 */
	@Override
	public void updateStatus(PipExpImplementProblem  record) {
		mapper.updateByPrimaryKeySelective(record);
	}

}

