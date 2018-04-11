package com.bdcor.pip.web.data.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.data.domain.PipCommRcc;
import com.bdcor.pip.web.data.filter.PipCommRccFilter;

@MyBatisRepository
public interface PipCommRccMapper {

	public List<PipCommRcc> queryRccList(PipCommRccFilter pcrf);

	public PipCommRcc selectPipCommRcc(@Param("rccCode") String rccCode,
			@Param("projectId") String projectId);

	public void insert(PipCommRcc pipCommRcc);

	/**
	 * description:
	 * 
	 * @author yangfeng
	 * @param map
	 * @update 2015-10-14
	 */
	public void updateByRccCode(PipCommRcc pipCommRcc);

	/**
	 * description:
	 * 
	 * @author yangfeng
	 * @param rccCode
	 * @param current_projectId
	 * @update 2015-10-16
	 */
	public void deleteByRccCode(@Param("rccCode") String rccCode,
			@Param("projectId") String projectId);
}
