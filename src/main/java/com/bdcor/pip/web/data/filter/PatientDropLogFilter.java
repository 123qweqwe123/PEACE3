package com.bdcor.pip.web.data.filter;

import com.bdcor.pip.core.persistence.domain.BaseFilter;

public class PatientDropLogFilter extends BaseFilter {
	
	private String patientId;
	
	private String targetId;
	
	private String sourceId;

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	
	

}
