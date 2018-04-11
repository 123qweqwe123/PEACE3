/**
 * JAVACC DEMO 1.0
 * @copy right zbxsoft company All rights reserved. 
 * @Package com.bdcor.pip.web.quality.service.impl 
 */
package com.bdcor.pip.web.quality.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.httpclient.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.quality.dao.PipExpImplementMapper;
import com.bdcor.pip.web.quality.domain.PipExpImplement;
import com.bdcor.pip.web.quality.domain.PipExpImplementExample;
import com.bdcor.pip.web.quality.domain.PipExpImplementExample.Criteria;
import com.bdcor.pip.web.quality.filter.PipExpImplementFilter;
import com.bdcor.pip.web.quality.service.PipExpImplementService;

/**
 * description:
 * 
 * @author yangfeng 创建时间：2015-11-10
 */
@Service
@Transactional
public class PipExpImplementServiceImpl implements PipExpImplementService {

	@Autowired
	private PipExpImplementMapper mapper;

	/**
	 * @see com.bdcor.pip.web.quality.service.PipExpImplementService#listByExample(com.bdcor.pip.web.quality.filter.PipExpImplementFilter)
	 */
	@Override
	public List<PipExpImplement> listByExample(PipExpImplementFilter filter)
			throws Exception {
		PipExpImplementExample example = new PipExpImplementExample();
		Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmpty(filter.getLccCode())) {
			criteria.andLccCodeLike("%" + filter.getLccCode() + "%");
		}
		if (null != filter.getStartSDate()) {
			criteria.andImplementDateGreaterThanOrEqualTo(filter.getStartSDate());
		}
		if (null != filter.getEndSDate()) {
			criteria.andImplementDateLessThanOrEqualTo(filter.getEndSDate());
		}
		return mapper.selectByExample(example);
	}
	/**
	 * @see com.bdcor.pip.web.quality.service.PipExpImplementService#listByExample(com.bdcor.pip.web.quality.filter.PipExpImplementFilter)
	 */
	@Override
	public List<PipExpImplement> listByFilter(PipExpImplementFilter filter)
			throws Exception {
		return mapper.listByFilter(filter);
	}
	/**
	 * @see com.bdcor.pip.web.quality.service.PipExpImplementService#selectByExample(java.lang.String)
	 */
	@Override
	public PipExpImplement selectByExample(String id) throws Exception {
		PipExpImplement selectByPrimaryKey = mapper.selectByPrimaryKey(id);
		selectByPrimaryKey.setImplementStartDateStr(DateUtil.formatDate(
				selectByPrimaryKey.getImplementStartDate(), "yyyy-MM-dd"));
		selectByPrimaryKey.setImplementEndDateStr(DateUtil.formatDate(
				selectByPrimaryKey.getImplementEndDate(), "yyyy-MM-dd"));
		return selectByPrimaryKey;
	}

	/**
	 * 
	 * @see com.bdcor.pip.web.quality.service.PipExpImplementService#save(com.bdcor.pip.web.quality.domain.PipExpImplement)
	 */
	@Override
	public void save(PipExpImplement implement) throws Exception {
		implement.setCreateBy(Securitys.getUserId());
		implement.setCreateDate(new Date());
		implement.setProjectId(Securitys.getUser().getCurrent_projectId());
		mapper.insert(implement);
	}

	/**
	 * 
	 * @see com.bdcor.pip.web.quality.service.PipExpImplementService#update(com.bdcor.pip.web.quality.domain.PipExpImplement)
	 */
	@Override
	public void update(PipExpImplement pipExpImplement) throws Exception {
		pipExpImplement.setUpdateBy(Securitys.getUserId());
		pipExpImplement.setUpdateDate(new Date());
		mapper.updateByPrimaryKeySelective(pipExpImplement);
	}

	/**
	 * @see com.bdcor.pip.web.quality.service.PipExpImplementService#delete(java.lang.String)
	 */
	@Override
	public void delete(String id) throws Exception {
		mapper.deleteByPrimaryKey(id);
	}
}
