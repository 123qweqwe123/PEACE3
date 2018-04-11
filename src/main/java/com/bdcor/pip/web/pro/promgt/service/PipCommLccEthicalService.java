/**
 * JAVACC DEMO 1.0
 * @copy right zbxsoft company All rights reserved. 
 * @Package com.bdcor.pip.web.pro.promgt.service 
 */
/**
 * JAVACC DEMO 1.0
 * @copy right zbxsoft company All rights reserved. 
 * @Package com.bdcor.pip.web.pro.promgt.service  
 */

package com.bdcor.pip.web.pro.promgt.service;

import java.util.List;

import com.bdcor.pip.web.pro.promgt.filter.EthicalFilter;

/**
 * description:
 * 
 * @author yangfeng 创建时间：2015-10-23
 */
public interface PipCommLccEthicalService {
	List<EthicalFilter> listByLccCode(String lccCode);
}
