package com.bdcor.pip.web.qn.dao;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.qn.domain.PipCommEvent1;
import com.bdcor.pip.web.qn.domain.PipCommEvent1Example;
import com.bdcor.pip.web.qn.domain.PipCommEventExportVO;
import com.bdcor.pip.web.qn.domain.PipCommEventVO1;
import com.bdcor.pip.web.qn.filter.EventCheckFilter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface PipCommEvent1Mapper {
    int countByExample(PipCommEvent1Example example);

    int deleteByExample(PipCommEvent1Example example);

    int insert(PipCommEvent1 record);

    int insertSelective(PipCommEvent1 record);

    List<PipCommEvent1> selectByExample(PipCommEvent1Example example);

    int updateByExampleSelective(@Param("record") PipCommEvent1 record, @Param("example") PipCommEvent1Example example);

    int updateByExample(@Param("record") PipCommEvent1 record, @Param("example") PipCommEvent1Example example);

    List<PipCommEventVO1>  selectByFilter(EventCheckFilter filter);

    List<PipCommEventExportVO>  select4export(EventCheckFilter filter);
}