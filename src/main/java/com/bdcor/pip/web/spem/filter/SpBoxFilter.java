package com.bdcor.pip.web.spem.filter;

import java.util.HashMap;
import java.util.Map;

import com.bdcor.pip.core.persistence.domain.BaseFilter;

/**
 * 公用条件查询类
 */
public class SpBoxFilter extends BaseFilter{
	
	private String fridgeId;
	private String boxId; //冰箱 冻存盒ID
	
	private String waybillno; // 运单号   
	
	private String lccid; // LCCID
	
	private String projectid; // 项目编码   
	
	private String boxCodes;   // 盒子编码   
	private String box_state; // 盒子状态
	private String box_type; //  盒子类型
	
	private String event_code; //  盒子类型
	
	
	public String getLccid() {
		return lccid;
	}

	public void setLccid(String lccid) {
		this.lccid = lccid;
	}

	public String getProjectid() {
		return projectid;
	}

	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}

	public String getBoxCodes() {
		return boxCodes;
	} 
	
	

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public void setBoxCodes(String boxCodes) {
		this.boxCodes = boxCodes;
	}

	public String getBox_state() {
		return box_state;
	}

	public void setBox_state(String box_state) {
		this.box_state = box_state;
	}

	public String getBox_type() {
		return box_type;
	}

	public void setBox_type(String box_type) {
		this.box_type = box_type;
	}

	/**
     * 存放条件查询值
     */
    private Map<String, Object> condition;     

    /**
     * 是否相异
     */
    protected boolean distinct;  

    /**
     * 排序字段
     */
    protected String orderByClause;

   
    protected SpBoxFilter(SpBoxFilter example) {
        this.orderByClause = example.orderByClause;
        this.condition = example.condition;
        this.distinct = example.distinct;
       
    } 

    public String getWaybillno() {
		return waybillno;
	}

	public void setWaybillno(String waybillno) {
		this.waybillno = waybillno;
	}

	public SpBoxFilter() {
        condition = new HashMap<String, Object>();
    }

    public void clear() {
        condition.clear();
        orderByClause = null;
        distinct = false;
        
    }
    
    public String getBoxId() {
		return boxId;
	}

	public void setBoxId(String boxId) {
		this.boxId = boxId;
	}

	/**
     * @param condition 
	 *            查询的条件名称
	 * @param value
	 *            查询的值
     */
    public SpBoxFilter put(String condition, Object value) {
        this.condition.put(condition, value);
        return (SpBoxFilter) this;
    }

    /**
     * @param orderByClause 
	 *            排序字段
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * @param distinct 
	 *            是否相异
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    @Deprecated
    public void setCondition(Map<String, Object> condition) {
        this.condition = condition;
    }

    @Deprecated
    public Map<String, Object> getCondition() {
        return condition;
    }

	public String getFridgeId() {
		return fridgeId;
	}

	public void setFridgeId(String fridgeId) {
		this.fridgeId = fridgeId;
	}

}