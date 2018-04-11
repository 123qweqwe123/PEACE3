package com.bdcor.pip.web.spem.filter;

import java.util.HashMap;
import java.util.Map;

import com.bdcor.pip.core.persistence.domain.BaseFilter;

/**
 * 公用条件查询类
 */
public class TubeFilter extends BaseFilter{
	
	
	private String boxId; // 冻存盒ID
	
	private String waybillno; // 运单号   
	
	
	private String boxType; // 冻存盒ID
	
	private String lccid; // 冻存盒ID
	
	private String projectid; // 冻存盒ID
	
	private String[]boxCodes;
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

   
    protected TubeFilter(TubeFilter example) {
        this.orderByClause = example.orderByClause;
        this.condition = example.condition;
        this.distinct = example.distinct;
       
    } 
    
    

    public String getBoxType() {
		return boxType;
	}



	public void setBoxType(String boxType) {
		this.boxType = boxType;
	}



	public String getWaybillno() {
		return waybillno;
	}

	public void setWaybillno(String waybillno) {
		this.waybillno = waybillno;
	}

	public TubeFilter() {
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
    public TubeFilter put(String condition, Object value) {
        this.condition.put(condition, value);
        return (TubeFilter) this;
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



	public String[] getBoxCodes() {
		return boxCodes;
	}



	public void setBoxCodes(String[] boxCodes) {
		this.boxCodes = boxCodes;
	}

    
}