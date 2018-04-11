package com.bdcor.pip.web.quality.filter;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.bdcor.pip.core.persistence.domain.BaseFilter;

public class QuestionnaireConditionFilter extends BaseFilter {
	private String lccCode;
	
	private String lccName;
	@DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endDate;

	public String getLccCode() {
		return lccCode;
	}

	public void setLccCode(String lccCode) {
		this.lccCode = lccCode;
	}

	public String getLccName() {
		return lccName;
	}

	public void setLccName(String lccName) {
		this.lccName = lccName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}



    
}
