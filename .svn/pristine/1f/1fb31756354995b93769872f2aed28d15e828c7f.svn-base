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

import com.bdcor.pip.web.quality.domain.PipExpImplementPerson;
import com.bdcor.pip.web.quality.filter.PipExpImplementPersonFilter;

/**
 * description:
 * 
 * @author yangfeng 创建时间：2015-11-11
 */
public interface PipExpImplementPersonService {

	/**
	 * description: 根据单位code和项目id查询相关人员
	 * 
	 * @author yangfeng
	 * @param filter
	 * @return
	 * @update 2015-11-11
	 */
	List<PipExpImplementPerson> listByExample(PipExpImplementPersonFilter filter);

	/**
	 * description:  
	 * @author yangfeng  
	 * @param filter
	 * @return   
	 * @update 2015-11-11
	 */
	PipExpImplementPerson selectByLccCodeUserCode(PipExpImplementPerson filter);

	/**
	 * description:  
	 * @author yangfeng  
	 * @param pipExpImplementPerson   
	 * @update 2015-11-11
	 */
	void save(PipExpImplementPerson pipExpImplementPerson);

	/**
	 * description:  
	 * @author yangfeng  
	 * @param ids   
	 * @update 2015-11-11
	 */
	void deleteBatch(String ids);

	/**
	 * description:  通过外键查找数据
	 * @author yangfeng  
	 * @param id
	 * @return   
	 * @update 2015-11-13
	 */
	List<PipExpImplementPerson> selectByFk(String fkId);

}
