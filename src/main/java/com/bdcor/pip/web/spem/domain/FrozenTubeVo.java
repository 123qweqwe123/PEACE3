package com.bdcor.pip.web.spem.domain;

import java.io.Serializable;

/**
 * 冻存盒
 */
public class FrozenTubeVo implements Serializable {
    private static final long serialVersionUID = 10086L;

    public String id;
    private String lccName;
    private String fridgeName;
    private String boxCode;
    private String tubeType;
    private String tubeCode;
    private String bloodCode;
    private String boxRowNo;
    private String boxColNo;
    private Integer isHemolysis;
    private Integer isLipid;
    private Integer isEmpty;
    
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLccName() {
		return lccName;
	}
	public void setLccName(String lccName) {
		this.lccName = lccName;
	}
	public String getBoxCode() {
		return boxCode;
	}
	public void setBoxCode(String boxCode) {
		this.boxCode = boxCode;
	}
	public String getTubeType() {
		return tubeType;
	}
	public void setTubeType(String tubeType) {
		this.tubeType = tubeType;
	}
	public String getTubeCode() {
		return tubeCode;
	}
	public void setTubeCode(String tubeCode) {
		this.tubeCode = tubeCode;
	}
	public String getBoxRowNo() {
		return boxRowNo;
	}
	public void setBoxRowNo(String boxRowNo) {
		this.boxRowNo = boxRowNo;
	}
	public String getBoxColNo() {
		return boxColNo;
	}
	public void setBoxColNo(String boxColNo) {
		this.boxColNo = boxColNo;
	}
	public Integer getIsHemolysis() {
		return isHemolysis;
	}
	public void setIsHemolysis(Integer isHemolysis) {
		this.isHemolysis = isHemolysis;
	}
	public Integer getIsLipid() {
		return isLipid;
	}
	public void setIsLipid(Integer isLipid) {
		this.isLipid = isLipid;
	}
	public Integer getIsEmpty() {
		return isEmpty;
	}
	public void setIsEmpty(Integer isEmpty) {
		this.isEmpty = isEmpty;
	}
	public String getFridgeName() {
		return fridgeName;
	}
	public void setFridgeName(String fridgeName) {
		this.fridgeName = fridgeName;
	}
	public String getBloodCode() {
		return bloodCode;
	}
	public void setBloodCode(String bloodCode) {
		this.bloodCode = bloodCode;
	}

}