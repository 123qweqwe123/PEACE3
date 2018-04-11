package com.bdcor.pip.web.qn.service;

import java.util.List;
import java.util.Map;

import com.bdcor.pip.web.qn.filter.PatientReportFilter;


public interface PatientReportService {

	List<Map<String, String>> getPatientReportList(PatientReportFilter filter);

	

}
	