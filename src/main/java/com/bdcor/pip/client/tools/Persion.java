package com.bdcor.pip.client.tools;


import java.util.ArrayList;
import java.util.List;


public class Persion {

	private String Screening;
	
	private List<BoolData> boolDatas = new ArrayList<BoolData>();

	public String getScreening() {
		return Screening;
	}

	public void setScreening(String screening) {
		Screening = screening;
	}

	public List<BoolData> getBoolDatas() {
		return boolDatas;
	}

	public void setBoolDatas(List<BoolData> boolDatas) {
		this.boolDatas = boolDatas;
	}
	
}
