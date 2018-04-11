package com.bdcor.pip.web.spem.domain;

import java.io.Serializable;

/**
 * 冻存盒
 */
public class ScmStaticVo implements Serializable {
    private static final long serialVersionUID = 10086L;

    public  String id;
    private String lccName;
    private Integer boxCount_total;
    private Integer boxCount_center;
    private Integer boxCount_lcc;
    private Integer tubeCount_total;
    private Integer tubeCount_center;
    private Integer tubeCount_lcc;
    private Integer wasteTubeCount;
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
	public Integer getBoxCount_total() {
		return boxCount_total;
	}
	public void setBoxCount_total(Integer boxCount_total) {
		this.boxCount_total = boxCount_total;
	}
	public Integer getBoxCount_center() {
		return boxCount_center;
	}
	public void setBoxCount_center(Integer boxCount_center) {
		this.boxCount_center = boxCount_center;
	}
	public Integer getBoxCount_lcc() {
		return boxCount_lcc;
	}
	public void setBoxCount_lcc(Integer boxCount_lcc) {
		this.boxCount_lcc = boxCount_lcc;
	}
	public Integer getTubeCount_total() {
		return tubeCount_total;
	}
	public void setTubeCount_total(Integer tubeCount_total) {
		this.tubeCount_total = tubeCount_total;
	}
	public Integer getTubeCount_center() {
		return tubeCount_center;
	}
	public void setTubeCount_center(Integer tubeCount_center) {
		this.tubeCount_center = tubeCount_center;
	}
	public Integer getTubeCount_lcc() {
		return tubeCount_lcc;
	}
	public void setTubeCount_lcc(Integer tubeCount_lcc) {
		this.tubeCount_lcc = tubeCount_lcc;
	}
	public Integer getWasteTubeCount() {
		return wasteTubeCount;
	}
	public void setWasteTubeCount(Integer wasteTubeCount) {
		this.wasteTubeCount = wasteTubeCount;
	}
    
}