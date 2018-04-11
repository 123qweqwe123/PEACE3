package com.bdcor.pip.web.data.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.data.domain.PipCommPatientHistory;
import com.bdcor.pip.web.data.domain.PipCommPatientHistoryExample;
import com.bdcor.pip.web.data.filter.PatientFilter;

@MyBatisRepository
public interface PipCommPatientHistoryMapper {
    int countByExample(PipCommPatientHistoryExample example);

    int deleteByExample(PipCommPatientHistoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PipCommPatientHistory record);

    int insertSelective(PipCommPatientHistory record);

    List<PipCommPatientHistory> selectByExample(PipCommPatientHistoryExample example);

    PipCommPatientHistory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PipCommPatientHistory record, @Param("example") PipCommPatientHistoryExample example);

    int updateByExample(@Param("record") PipCommPatientHistory record, @Param("example") PipCommPatientHistoryExample example);

    int updateByPrimaryKeySelective(PipCommPatientHistory record);

    int updateByPrimaryKey(PipCommPatientHistory record);
    
    List<PipCommPatientHistory> selectForPager(PatientFilter filter);
}