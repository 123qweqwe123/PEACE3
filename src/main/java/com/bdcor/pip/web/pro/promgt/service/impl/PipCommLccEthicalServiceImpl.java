/**
 * JAVACC DEMO 1.0
 * @copy right zbxsoft company All rights reserved. 
 * @Package com.bdcor.pip.web.pro.promgt.service.impl 
 */
/**
 * JAVACC DEMO 1.0
 * @copy right zbxsoft company All rights reserved. 
 * @Package com.bdcor.pip.web.pro.promgt.service.impl  
 */

package com.bdcor.pip.web.pro.promgt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.pro.promgt.dao.PipCommLccEthicalDao;
import com.bdcor.pip.web.pro.promgt.filter.EthicalFilter;
import com.bdcor.pip.web.pro.promgt.service.PipCommLccEthicalService;

/**  
 * description:  
 * @author yangfeng 创建时间：2015-10-23         
 */
/**
 * description:
 * 
 * @author yangfeng 创建时间：2015-10-23
 */
@Service
@Transactional
public class PipCommLccEthicalServiceImpl implements PipCommLccEthicalService {
	@Autowired
	PipCommLccEthicalDao pipCommLccEthicalDao;

	/**
	 * 
	 * @see com.bdcor.pip.web.pro.promgt.service.PipCommLccEthicalService#listByLccCode(java.lang.String)
	 */
	@Override
	public List<EthicalFilter> listByLccCode(String lccCode) {
		return pipCommLccEthicalDao.listByLccCode(lccCode,Securitys.getUser().getCurrent_projectId());
	}

}
