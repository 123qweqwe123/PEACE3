package com.bdcor.pip.web.spem.domain;

import java.util.Date;


/**
 * 冻存盒
 */
public class SpemTypeVo  {
    
    private String box_type;
    
    private String  lcc_code;

    private String fridge_id;
    
    private String project_id;

    private Date create_date;
    
    private String create_by;

	public String getBox_type() {
		return box_type;
	}

	public void setBox_type(String box_type) {
		this.box_type = box_type;
	}

	public String getLcc_code() {
		return lcc_code;
	}

	public void setLcc_code(String lcc_code) {
		this.lcc_code = lcc_code;
	}

	public String getFridge_id() {
		return fridge_id;
	}

	public void setFridge_id(String fridge_id) {
		this.fridge_id = fridge_id;
	}

	public String getProject_id() {
		return project_id;
	}

	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getCreate_by() {
		return create_by;
	}

	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}
    
   
}