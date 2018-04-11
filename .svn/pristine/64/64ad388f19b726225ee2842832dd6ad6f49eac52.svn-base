package com.bdcor.pip.web.qn.dao;

import java.util.List;
import java.util.Map;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.qn.filter.PatientReportFilter;
import org.apache.ibatis.annotations.Param;


@MyBatisRepository
public interface PatientReportDao {
	
	List<Map<String, String>> getPatientReportList(PatientReportFilter filter);

	List<Map<String, Object>> getDeadCount(PatientReportFilter filter);

	List<Map<String, Object>> getGroupState(@Param("lccCode")String lccCode);
}
