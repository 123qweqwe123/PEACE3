package com.bdcor.pip.web.data.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.data.filter.PatientNameModifyFilter;

@MyBatisRepository
public interface PatientNameModifyMapper {

	List<Map<String, Object>> list(PatientNameModifyFilter filter);

	Map<String, Object> getLogMap(@Param("patientId")String patientId,@Param("qnId")String qnId);

	void updateAnswerPatientName(@Param("patientId")String patientId,@Param("newPatientName")String newPatientName);

	void insertDrop(Map<String, String> dropMap);

}