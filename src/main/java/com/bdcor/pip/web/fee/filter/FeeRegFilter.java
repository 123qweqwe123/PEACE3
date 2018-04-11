package com.bdcor.pip.web.fee.filter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

import com.bdcor.pip.core.persistence.domain.BaseFilter;

public class FeeRegFilter extends BaseFilter {
	
	private Date bg_date;//开始时间
    private Date end_date;//结束时间
    private String projectName;//项目名称
    private String departId;//部门ID
    private String reg_type;//注册类型   
    
	public Date getBg_date() {
		return bg_date;
	}
	public void setBg_date(Date bg_date) {
		this.bg_date = bg_date;
	}
	public Date getEnd_date() {
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
		Date c = null;
		try {
			c = DateUtils.parseDate(formatDate.format( this.end_date) + " 23:59:59",  "yyyy-MM-dd HH:mm:ss");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getDepartId() {
		return departId;
	}
	public void setDepartId(String departId) {
		this.departId = departId;
	}
	public String getReg_type() {
		return reg_type;
	}
	public void setReg_type(String reg_type) {
		this.reg_type = reg_type;
	}
   
}
