package com.bdcor.pip.web.progress.filter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.bdcor.pip.core.persistence.domain.BaseFilter;

public class ProgressFilter extends BaseFilter  {
	
	private String projectId;
	
	private Date start;
	
	private Date end;
	
	private String lccCode;
	
	private String lccName;
	
	private String area_code;
	
	private String type;//1初筛 2高危
	
	private String[] types;//1初筛 2高危
	
	private String typeCode;//1 上传统计未标记  2上传统计标记为红色
	//private List days_;
	
	private String patientId;
	
	private String patientName;
	
	
	private List<String> datalimits;
	
	
	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public List<String> getDatalimits() {
		if (datalimits == null)
			datalimits = new ArrayList<String>();
		return datalimits;
	}

	public void setDatalimits(List<String> datalimits) {
		this.datalimits = datalimits;
	}

	public String getLccCode() {
		return lccCode;
	}

	public String[] getTypes() {
		return types;
	}

	public void setTypes(String[] types) {
		this.types = types;
	}

	public void setLccCode(String lccCode) {
		this.lccCode = lccCode;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		if ( this.end == null )
			return null;
		Date c = null;
		try {
			SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
			String endStr = formatDate.format(end);
			c = DateUtils.parseDate(endStr + " 23:59:59",  "yyyy-MM-dd HH:mm:ss");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c; 
	} 

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getArea_code() {
		if("".equals(area_code)){
			return null;
		}
		return area_code;
	}

	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		if(StringUtils.isBlank(type))
			return;
		String typesTmp[] = type.split(",");
		types = new String[typesTmp.length];
		int i=0;
		for(String type1:typesTmp){
			if(!StringUtils.isBlank(type1))
			{	
				types[i]=type1;
				i++;
			}
		}
		this.type = type;
	}
	
	public List getDays_(){
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		String starDateStr = ft.format(this.start);
		String endDateStr = ft.format(this.end );
		
		Date starDate = null;
		Date endDate = null;
		try {
			starDate = DateUtils.parseDate(starDateStr + " 00:00:00",  "yyyy-MM-dd HH:mm:ss");
			endDate = DateUtils.parseDate(endDateStr + " 00:00:00",  "yyyy-MM-dd HH:mm:ss");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List days_ = new ArrayList();
		
		Calendar c = Calendar.getInstance();
		c.setTime(starDate);
		days_.add(ft.format(starDate));
		
		
		
		
		while(c.getTime().getTime() < endDate.getTime() ){
			c.add(Calendar.DAY_OF_MONTH , 1);
			days_.add(ft.format(c.getTime()));
		}
		return days_;
	}

	public String getLccName() {
		return lccName;
	}

	public void setLccName(String lccName) {
		this.lccName = lccName;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	
}
