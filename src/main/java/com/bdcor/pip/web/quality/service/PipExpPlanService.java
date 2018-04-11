/**
 * JAVACC DEMO 1.0
 * @copy right zbxsoft company All rights reserved. 
 * @Package com.bdcor.pip.web.quality.service 
 */
/**
 * JAVACC DEMO 1.0
 * @copy right zbxsoft company All rights reserved. 
 * @Package com.bdcor.pip.web.quality.service  
 */

package com.bdcor.pip.web.quality.service;

import java.util.List;

import com.bdcor.pip.web.quality.domain.PipExpPlan;
import com.bdcor.pip.web.quality.filter.PipExpPlanFilter;
/**  
 * description:  
 * @author yangfeng 创建时间：2015-11-5         
 */
public interface PipExpPlanService {
	public List<PipExpPlan> list(PipExpPlanFilter filter);
	public List<PipExpPlan> listByExample(PipExpPlanFilter filter);
	public void insert(PipExpPlan pipExpPlan) throws Exception;
	public void update(PipExpPlan pipExpPlan)throws Exception;
	public void delete(String id)throws Exception;
	public void deleteBatch(String ids) throws Exception;
	public PipExpPlan selectExpPlan(String id);
}

