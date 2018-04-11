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

import com.bdcor.pip.web.quality.domain.PipExpImplementProblem;
import com.bdcor.pip.web.quality.filter.PipExpImplementProblemFilter;

/**  
 * description:  
 * @author yangfeng 创建时间：2015-11-12         
 */
public interface PipExpImplementProblemService {

	/**
	 * description:  
	 * @author yangfeng  
	 * @param problem   
	 * @update 2015-11-12
	 */
	void insert(PipExpImplementProblem problem);

	/**
	 * description:  
	 * @author yangfeng  
	 * @param filter
	 * @return   
	 * @update 2015-11-12
	 */
	List<PipExpImplementProblem> listByExample(
			PipExpImplementProblemFilter filter);

	/**
	 * description:  
	 * @author yangfeng  
	 * @param problem   
	 * @update 2015-11-12
	 */
	void update(PipExpImplementProblem problem);

	/**
	 * description:  
	 * @author yangfeng  
	 * @param id   
	 * @update 2015-11-12
	 */
	void delete(String id);

	/**
	 * description:  
	 * @author yangfeng  
	 * @param ids   
	 * @update 2015-11-12
	 */
	void deleteBatch(String ids);

	/**
	 * description:  
	 * @author yangfeng  
	 * @param lccUserId   
	 * @update 2015-11-13
	 */
	boolean checkIsExist(String lccUserId);

	/**
	 * description:  
	 * @author yangfeng  
	 * @param lccUserId
	 * @param id
	 * @return   
	 * @update 2015-11-13
	 */
	boolean checkIsExistAndNotSelf(String lccUserId, String id);
	
	List<PipExpImplementProblem> selectByFk(String implementId);

	/**
	 * description:  多条件查询
	 * @author yangfeng  
	 * @param filter
	 * @return   
	 * @update 2015-11-17
	 */
	List<PipExpImplementProblem> list(PipExpImplementProblemFilter filter);

	/**
	 * description:  通过id修改事件状态
	 * @author yangfeng  
	 * @param id
	 * @param status   
	 * @update 2015-11-17
	 */
	void updateStatus(PipExpImplementProblem problem);

}

