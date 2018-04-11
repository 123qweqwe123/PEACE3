/**
 * JAVACC DEMO 1.0
 * @copy right zbxsoft company All rights reserved. 
 * @Package com.bdcor.pip.web.data.service 
 */
/**
 * JAVACC DEMO 1.0
 * @copy right zbxsoft company All rights reserved. 
 * @Package com.bdcor.pip.web.data.service  
 */

package com.bdcor.pip.web.data.service;

import java.util.List;

import com.bdcor.pip.dict.domain.DictCommDTO;
import com.bdcor.pip.web.data.domain.PipCommEventDictfile;
import com.bdcor.pip.web.data.filter.PipCommEventDictfileFilter;

/**
 * description:
 * 
 * @author yangfeng 创建时间：2015-11-28
 */
public interface PipCommEventDictfileService {

	/**
	 * description:通过事件code查询
	 * 
	 * @author yangfeng
	 * @param filter
	 * @return
	 * @update 2015-11-28
	 */
	List<PipCommEventDictfile> list(PipCommEventDictfileFilter filter);
	List<PipCommEventDictfile> listByFilter(PipCommEventDictfileFilter filter);

	/**
	 * 
	 * description:通过dictCode查找事件分类
	 * 
	 * @author yangfeng
	 * @param dictCode
	 * @return
	 * @update 2015-11-28
	 */
	List<DictCommDTO> getDictHisList(String dictCode);

	/**
	 * description:通过id查找一条记录
	 * 
	 * @author yangfeng
	 * @param id
	 * @return
	 * @update 2015-11-28
	 */
	PipCommEventDictfile getEventFileById(String id);

	/**
	 * description: 插入一条记录
	 * 
	 * @author yangfeng
	 * @param pipCommEventDictfile
	 * @update 2015-11-28
	 */
	void add(PipCommEventDictfile pipCommEventDictfile) throws Exception;

	/**
	 * description: 通过id修改一条记录
	 * 
	 * @author yangfeng
	 * @param pipCommEventDictfile
	 * @update 2015-11-28
	 */
	void update(PipCommEventDictfile pipCommEventDictfile) throws Exception;

	/**
	 * 
	 * description:通过id查出一条记录
	 * 
	 * @author yangfeng
	 * @param id
	 * @update 2015-11-28
	 */
	void deletById(String id);
}
