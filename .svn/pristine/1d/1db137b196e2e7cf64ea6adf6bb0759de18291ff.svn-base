package com.bdcor.pip.web.fee.filter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

import com.bdcor.pip.core.persistence.domain.BaseFilter;

public class FeeLccRegFilter extends BaseFilter{
    private Date bg_date;
    private Date end_date;
    private String lcc_code; 
    private String projectid; 
     
      
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
	public String getLcc_code() {
		return lcc_code;
	}
	public void setLcc_code(String lcc_code) {
		this.lcc_code = lcc_code;
	}
	public String getProjectid() {
		return projectid;
	}
	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}     
	
}
