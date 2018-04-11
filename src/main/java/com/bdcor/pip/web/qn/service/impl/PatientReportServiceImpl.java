package com.bdcor.pip.web.qn.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.qn.dao.PatientReportDao;
import com.bdcor.pip.web.qn.filter.PatientReportFilter;
import com.bdcor.pip.web.qn.service.PatientReportService;

@Service
public class PatientReportServiceImpl implements PatientReportService{
	
	@Autowired
	private PatientReportDao patientReportDao;

	@Override
	public List<Map<String, String>> getPatientReportList(PatientReportFilter filter) {
		filter.setProjectId(Securitys.getCurrentProject());
		List<Map<String, Object>> deadCountList = patientReportDao.getDeadCount(filter);
		List<Map<String, String>> returnList = patientReportDao.getPatientReportList(filter);
		if(deadCountList != null && deadCountList.size()>0 && returnList!=null && returnList.size()>0){
			for(Map<String,Object> deadMap : deadCountList){
				for(Map<String,String> rMap : returnList){
					if(deadMap.get("UQSID").equals(rMap.get("id"))){
						rMap.put("DEADCOUNT",deadMap.get("DEADCOUNT").toString());
					}
				}
			}
		}
		return returnList;
	}

	
}
