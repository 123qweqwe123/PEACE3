package com.bdcor.pip.web.qn.dao;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.qn.domain.PipCommEvent;
import com.bdcor.pip.web.qn.domain.PipCommEventExample;
import com.bdcor.pip.web.qn.domain.PipCommEventExportVO;
import com.bdcor.pip.web.qn.domain.PipCommEventVO;
import com.bdcor.pip.web.qn.filter.EventCheckFilter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface PipCommEventMapper {
    int countByExample(PipCommEventExample example);

    int deleteByExample(PipCommEventExample example);

    int insert(PipCommEvent record);

    int insertSelective(PipCommEvent record);

    List<PipCommEvent> selectByExample(PipCommEventExample example);

    int updateByExampleSelective(@Param("record") PipCommEvent record, @Param("example") PipCommEventExample example);

    int updateByExample(@Param("record") PipCommEvent record, @Param("example") PipCommEventExample example);

    List<PipCommEventVO>  selectByFilter(EventCheckFilter filter);

    List<PipCommEventExportVO>  select4export(EventCheckFilter filter);
}