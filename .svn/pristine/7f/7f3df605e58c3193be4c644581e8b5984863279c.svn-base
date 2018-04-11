package com.bdcor.pip.web.quality.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.quality.domain.PipExpPlan;
import com.bdcor.pip.web.quality.domain.PipExpPlanExample;
import com.bdcor.pip.web.quality.filter.PipExpPlanFilter;
@MyBatisRepository
public interface PipExpPlanMapper {
    int countByExample(PipExpPlanExample example);

    int deleteByExample(PipExpPlanExample example);

    int deleteByPrimaryKey(String id);

    int insert(PipExpPlan record);

    int insertSelective(PipExpPlan record);

    List<PipExpPlan> selectByExample(PipExpPlanExample example);

    PipExpPlan selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PipExpPlan record, @Param("example") PipExpPlanExample example);

    int updateByExample(@Param("record") PipExpPlan record, @Param("example") PipExpPlanExample example);

    int updateByPrimaryKeySelective(PipExpPlan record);

    int updateByPrimaryKey(PipExpPlan record);

	/**
	 * description:  
	 * @author yangfeng  
	 * @param ids   
	 * @update 2015-11-5
	 */
	void deleteBatch(@Param("ids")String ids);

	/**
	 * description:  
	 * @author yangfeng  
	 * @param filter
	 * @return   
	 * @update 2015-11-5
	 */
	List<PipExpPlan> list(PipExpPlanFilter filter);
}