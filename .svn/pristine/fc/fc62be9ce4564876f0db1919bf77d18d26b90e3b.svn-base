package com.bdcor.pip.web.qn.dao;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.qn.domain.PipUqsAnswerqnLog;
import com.bdcor.pip.web.qn.domain.PipUqsAnswerqnLogExample;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bdcor.pip.web.qn.filter.AnswerQnLogFilter;
import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public interface PipUqsAnswerqnLogMapper {
    int countByExample(PipUqsAnswerqnLogExample example);

    int deleteByExample(PipUqsAnswerqnLogExample example);

    int insert(PipUqsAnswerqnLog record);

    int insertSelective(PipUqsAnswerqnLog record);

    List<PipUqsAnswerqnLog> selectByExample(PipUqsAnswerqnLogExample example);

    int updateByExampleSelective(@Param("record") PipUqsAnswerqnLog record, @Param("example") PipUqsAnswerqnLogExample example);

    int updateByExample(@Param("record") PipUqsAnswerqnLog record, @Param("example") PipUqsAnswerqnLogExample example);

    List<PipUqsAnswerqnLog> listData(AnswerQnLogFilter filter);

    List<Map<String,Object>> getData(AnswerQnLogFilter filter);

    Map<String,Object> getPatientInfoById(String pid);

    List<Map<String,String>> getUqsData(@Param("pid") String patientid , @Param("qnid")String uqs_version,@Param("uqsfile")String uqsfile);

    List<Map<String,Object>> getPatientInfo(@Param("pid")String patient_id , @Param("proj_id")String project_id);

	Date getStartHosDate(Map<String, Object> pMap);
}