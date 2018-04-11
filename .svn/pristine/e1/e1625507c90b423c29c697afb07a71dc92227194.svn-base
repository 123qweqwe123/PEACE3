package com.bdcor.pip.web.quality.dao;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.quality.domain.PipExpImplementProblem;
import com.bdcor.pip.web.quality.domain.PipExpImplementProblemExample;
import com.bdcor.pip.web.quality.filter.PipExpImplementPersonFilter;
import com.bdcor.pip.web.quality.filter.PipExpImplementProblemFilter;

import java.util.List;
import org.apache.ibatis.annotations.Param;
@MyBatisRepository
public interface PipExpImplementProblemMapper {
    int countByExample(PipExpImplementProblemExample example);

    int deleteByExample(PipExpImplementProblemExample example);

    int deleteByPrimaryKey(String id);

    int insert(PipExpImplementProblem record);

    int insertSelective(PipExpImplementProblem record);

    List<PipExpImplementProblem> selectByExample(PipExpImplementProblemExample example);

    List<PipExpImplementProblem> selectByFilter(PipExpImplementProblemFilter filter);
    
    PipExpImplementProblem selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PipExpImplementProblem record, @Param("example") PipExpImplementProblemExample example);

    int updateByExample(@Param("record") PipExpImplementProblem record, @Param("example") PipExpImplementProblemExample example);

    int updateByPrimaryKeySelective(PipExpImplementProblem record);

    int updateByPrimaryKey(PipExpImplementProblem record);

	/**
	 * description:  
	 * @author yangfeng  
	 * @param ids   
	 * @update 2015-11-12
	 */
	void deleteBatch(@Param("ids")String ids);
}