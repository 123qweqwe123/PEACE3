/**
 * JAVACC DEMO 1.0
 * @copy right zbxsoft company All rights reserved. 
 * @Package com.bdcor.pip.web.data.service.impl 
 */
/**
 * JAVACC DEMO 1.0
 * @copy right zbxsoft company All rights reserved. 
 * @Package com.bdcor.pip.web.data.service.impl  
 */

package com.bdcor.pip.web.data.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdcor.pip.core.utils.GenerateKey;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.dict.dao.DictManagerDao;
import com.bdcor.pip.dict.domain.DictCommDTO;
import com.bdcor.pip.web.data.dao.PipCommEventDictfileMapper;
import com.bdcor.pip.web.data.domain.PipCommEventDictfile;
import com.bdcor.pip.web.data.domain.PipCommEventDictfileExample;
import com.bdcor.pip.web.data.domain.PipCommEventDictfileExample.Criteria;
import com.bdcor.pip.web.data.filter.PipCommEventDictfileFilter;
import com.bdcor.pip.web.data.service.PipCommEventDictfileService;

/**
 * description:
 * 
 * @author yangfeng 创建时间：2015-11-28
 */
@Service
@Transactional
public class PipCommEventDictfileServiceImpl implements
		PipCommEventDictfileService {
	@Autowired
	private PipCommEventDictfileMapper mapper;
	@Autowired
	private DictManagerDao dao;

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.bdcor.pip.web.data.service.PipCommEventDictfileService#list(com.bdcor.pip.web.data.filter.PipCommEventDictfileFilter)
	 */
	@Override
	public List<PipCommEventDictfile> list(PipCommEventDictfileFilter filter) {
		PipCommEventDictfileExample example = new PipCommEventDictfileExample();
		Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmpty(filter.getEventDictCode())) {
			criteria.andEventDictCodeEqualTo(filter.getEventDictCode());
		}
		return mapper.selectByExample(example);
	}
	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.bdcor.pip.web.data.service.PipCommEventDictfileService#list(com.bdcor.pip.web.data.filter.PipCommEventDictfileFilter)
	 */
	@Override
	public List<PipCommEventDictfile> listByFilter(PipCommEventDictfileFilter filter) {
		return mapper.selectByFilter(filter);
	}
	

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.bdcor.pip.web.data.service.PipCommEventDictfileService#getDictHisList(java.lang.String)
	 */
	@Override
	public List<DictCommDTO> getDictHisList(String dictCode) {
		return dao.getDictHISByCode(dictCode);
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.bdcor.pip.web.data.service.PipCommEventDictfileService#getEventFileById(java.lang.String)
	 */
	@Override
	public PipCommEventDictfile getEventFileById(String id) {
		return mapper.selectByPrimaryKey(id);
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.bdcor.pip.web.data.service.PipCommEventDictfileService#add(com.bdcor.pip.web.data.domain.PipCommEventDictfile)
	 */
	@Override
	public void add(PipCommEventDictfile pipCommEventDictfile) throws Exception {
		pipCommEventDictfile.setId(GenerateKey
				.getKey(GenerateKey.PREFIX_COMMON));
		pipCommEventDictfile.setCreateDate(new Date());
		pipCommEventDictfile.setCreateby(Securitys.getUserId());
		mapper.insert(pipCommEventDictfile);
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.bdcor.pip.web.data.service.PipCommEventDictfileService#update(com.bdcor.pip.web.data.domain.PipCommEventDictfile)
	 */
	@Override
	public void update(PipCommEventDictfile pipCommEventDictfile)
			throws Exception {
		pipCommEventDictfile.setUpdateDate(new Date());
		pipCommEventDictfile.setUpdateby(Securitys.getUserId());
		mapper.updateByPrimaryKeySelective(pipCommEventDictfile);
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.bdcor.pip.web.data.service.PipCommEventDictfileService#deletById(java.lang.String)
	 */
	@Override
	public void deletById(String id) {
		mapper.deleteByPrimaryKey(id);
	}
}
