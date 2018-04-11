package com.bdcor.pip.web.material.supp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.material.supp.domain.BooldExcelVo;
import com.bdcor.pip.web.material.supp.domain.PipMmsScmarchives;
import com.bdcor.pip.web.material.supp.domain.PipMmsScmarchivesExample;
import com.bdcor.pip.web.material.supp.filter.PipMmsScmarchivesFilter;

@MyBatisRepository
public interface PipMmsScmarchivesMapper {
	int countByExample(PipMmsScmarchivesExample example);

	int deleteByExample(PipMmsScmarchivesExample example);

	int deleteByPrimaryKey(Short id);

	int insert(PipMmsScmarchives record);

	int insertSelective(PipMmsScmarchives record);

	int selectByPjIdAndArchNoAndPagCode(@Param("bev") BooldExcelVo bev,
			@Param("projectId") String projectId);

	List<PipMmsScmarchives> selectByExample(PipMmsScmarchivesExample example);

	PipMmsScmarchives selectByPrimaryKey(Short id);

	int updateByExampleSelective(@Param("record") PipMmsScmarchives record,
			@Param("example") PipMmsScmarchivesExample example);

	int updateByExample(@Param("record") PipMmsScmarchives record,
			@Param("example") PipMmsScmarchivesExample example);

	int updateByPrimaryKeySelective(PipMmsScmarchives record);

	int updateByPrimaryKey(PipMmsScmarchives record);

	/**
	 * description:
	 * 
	 * @author yangfeng
	 * @param filter
	 * @return
	 * @update 2015-10-29
	 */
	List<PipMmsScmarchives> selectByFilter(PipMmsScmarchivesFilter filter);

	/**
	 * 
	 * description:根据箱号获取采血包号
	 * 
	 * @author yangfeng
	 * @param filter
	 * @return
	 * @update 2015-11-27
	 */
	List<PipMmsScmarchives> selectSimpleByFilter(PipMmsScmarchivesFilter filter);

	/**
	 * description:
	 * 
	 * @author yangfeng
	 * @param ids
	 * @param state
	 * @update 2015-11-2
	 */
	void updateState(@Param("ids") String ids, @Param("state") String state);

	public List<PipMmsScmarchives> archivesNoAllNoUse();
	/**
	 * 
	 * description:  通过采血包编码更新采血包状态
	 * @author yangfeng  
	 * @param current_projectId
	 * @param resutlStr
	 * @param string   
	 * @update 2016年1月25日
	 */
	void updateStateByBloodCode(@Param("projectId")String projectId,@Param("bloodpackageCode") String bloodpackageCode, @Param("packageState") String state);

    int scheduleChangeState();
}