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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdcor.pip.core.utils.DateUtil;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.quality.dao.PipExpPlanMapper;
import com.bdcor.pip.web.quality.domain.PipExpPlan;
import com.bdcor.pip.web.quality.filter.PipExpPlanFilter;
import com.bdcor.pip.web.quality.service.PipExpPlanService;

/**
 * description:
 * 
 * @author yangfeng 创建时间：2015-11-5
 */
@Service
@Transactional
public class PipExpPlanServiceImpl implements PipExpPlanService {
	@Autowired
	private PipExpPlanMapper mapper;

	/**
	 * @see com.bdcor.pip.web.quality.service.PipExpPlanService#list(com.bdcor.pip.web.quality.filter.PipExpPlanFilter)
	 */
	@Override
	public List<PipExpPlan> list(PipExpPlanFilter filter) {
		return mapper.list(filter);
	}

	/**
	 * @see com.bdcor.pip.web.quality.service.PipExpPlanService#insert(com.bdcor.pip.web.quality.domain.PipExpPlan)
	 */
	@Override
	public void insert(PipExpPlan pipExpPlan) throws Exception {
		pipExpPlan.setCreateUserId(Securitys.getUserId());
		pipExpPlan.setCreateDate(new Date());
		mapper.insert(pipExpPlan);
	}

	/**
	 * @see com.bdcor.pip.web.quality.service.PipExpPlanService#update(com.bdcor.pip.web.quality.domain.PipExpPlan)
	 */
	@Override
	public void update(PipExpPlan pipExpPlan) throws Exception {
		mapper.updateByPrimaryKeySelective(pipExpPlan);

	}

	/**
	 * @see com.bdcor.pip.web.quality.service.PipExpPlanService#delete(java.lang.String)
	 */
	@Override
	public void delete(String id) throws Exception {
		mapper.deleteByPrimaryKey(id);
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.bdcor.pip.web.quality.service.PipExpPlanService#deleteBatch(java.lang.String)
	 */
	@Override
	public void deleteBatch(String ids) throws Exception {
		mapper.deleteBatch(ids);
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 */
	@Override
	public PipExpPlan selectExpPlan(String id) {
		PipExpPlan pipExpPlan = mapper.selectByPrimaryKey(id);
		if (pipExpPlan.getPlanStartDate() != null) {
			pipExpPlan.setPlanStartDateStr(DateUtil.dateToString(
					pipExpPlan.getPlanStartDate(), "yyyy-MM-dd"));
		}
		if (pipExpPlan.getPlanEndDate() != null) {
			pipExpPlan.setPlanEndDateStr(DateUtil.dateToString(
					pipExpPlan.getPlanEndDate(), "yyyy-MM-dd"));
		}
		return pipExpPlan;
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.bdcor.pip.web.quality.service.PipExpPlanService#listByExample(com.bdcor.pip.web.quality.filter.PipExpPlanFilter)
	 */
	@Override
	public List<PipExpPlan> listByExample(PipExpPlanFilter filter) {
		/*
		 * PipExpPlanExample example =new PipExpPlanExample(); Criteria criteria
		 * = example.createCriteria();
		 * if(!StringUtils.isEmpty(filter.getLccCode())){
		 * criteria.andLccCodeLike("%"+filter.getLccCode()+"%"); }
		 * if(filter.getStartDate() != null){
		 * criteria.andPlanDateGreaterThanOrEqualTo(filter.getStartDate()); }
		 * if(filter.getEndDate() != null){
		 * criteria.andPlanDateLessThanOrEqualTo(filter.getEndDate()); }
		 */
		return mapper.list(filter);
	}

}
