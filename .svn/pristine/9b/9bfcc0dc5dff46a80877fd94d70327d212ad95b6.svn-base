package com.bdcor.pip.web.data.dao;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.data.domain.PipCommPatientNewjoin;
import com.bdcor.pip.web.data.domain.PipCommPatientNewjoinExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
@MyBatisRepository
public interface PipCommPatientNewjoinMapper {
    int countByExample(PipCommPatientNewjoinExample example);

    int deleteByExample(PipCommPatientNewjoinExample example);

    int deleteByPrimaryKey(String patientId);

    int insert(PipCommPatientNewjoin record);

    int insertSelective(PipCommPatientNewjoin record);

    List<PipCommPatientNewjoin> selectByExample(PipCommPatientNewjoinExample example);

    PipCommPatientNewjoin selectByPrimaryKey(String patientId);

    int updateByExampleSelective(@Param("record") PipCommPatientNewjoin record, @Param("example") PipCommPatientNewjoinExample example);

    int updateByExample(@Param("record") PipCommPatientNewjoin record, @Param("example") PipCommPatientNewjoinExample example);

    int updateByPrimaryKeySelective(PipCommPatientNewjoin record);

    int updateByPrimaryKey(PipCommPatientNewjoin record);

    int insertGroup(Map<String,String> m);

    String getNewPatientid(@Param("lccCode")String lccCode);
}