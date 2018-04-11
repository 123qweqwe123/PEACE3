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
import com.bdcor.pip.web.material.supp.dao.PipMmsDefstorehouseMapper;
import com.bdcor.pip.web.material.supp.domain.PipMmsDefstorehouse;
import com.bdcor.pip.web.material.supp.domain.PipMmsDefstorehouseExample;
import com.bdcor.pip.web.material.supp.domain.PipMmsDefstorehouseExample.Criteria;
import com.bdcor.pip.web.material.supp.service.PipMmsDefstorehouseService;

/**
 * description:
 * 
 * @author yangfeng 创建时间：2015-10-29
 */
@Service
@Transactional
public class PipMmsDefstorehouseServiceImpl implements
		PipMmsDefstorehouseService {
	@Autowired
	PipMmsDefstorehouseMapper pipMmsDefstorehouseMapper;

	@Override
	public List<PipMmsDefstorehouse> getAllHouseListByLccCode(
			PipMmsDefstorehouse pmdf) {
		return pipMmsDefstorehouseMapper.getAllHouseListByLccCode(pmdf);
	}

	@Override
	public List<PipMmsDefstorehouse> listAndLimit() {
		return pipMmsDefstorehouseMapper.listAndLimit(Securitys.getUserId());
	}

	public List<PipMmsDefstorehouse> getOneHouseListByLccCode(
			PipMmsDefstorehouse pmdf) {
		return pipMmsDefstorehouseMapper.getOneHouseListByLccCode(pmdf);

	}

	/**
	 * @see com.bdcor.pip.web.material.supp.service.PipMmsDefstorehouseService#getAllHouseListByLccCode(java.lang.String)
	 */
	@Override
	public List<PipMmsDefstorehouse> getAllHouseListByLccCode(String lccCode) {
		PipMmsDefstorehouseExample example = new PipMmsDefstorehouseExample();
		Criteria criteria = example.createCriteria();
		criteria.andLccCodeEqualTo(lccCode);
		return pipMmsDefstorehouseMapper.selectByExample(example);
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.bdcor.pip.web.material.supp.service.PipMmsDefstorehouseService#listAndLimit(boolean)
	 */
	@Override
	public List<PipMmsDefstorehouse> listAndLimitLcc() {
		return pipMmsDefstorehouseMapper.listAndLimitLcc(Securitys.getUserId(),
				Securitys.getUser().getLccCode());
	}

}
