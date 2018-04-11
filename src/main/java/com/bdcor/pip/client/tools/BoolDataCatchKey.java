package com.bdcor.pip.client.tools;



public class BoolDataCatchKey implements ICatchKey{

	private String id;
	private String boolType;
	
	public BoolDataCatchKey(String aid,String aboolType){
		this.id = aid;
		this.boolType = aboolType;
	}
	
	@Override
	public String getKey() {
		return id+"#"+boolType;
	}

}
