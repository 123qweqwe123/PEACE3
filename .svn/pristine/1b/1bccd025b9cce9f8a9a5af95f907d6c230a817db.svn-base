package com.bdcor.pip.web.spem.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Transient;

public class IceBoxRegVo {  
   
    private String id;

    
    private String projectid; 
    
    private String projectName; 
    
    

	private String lccid;
    
    private String user_name;
    
    private String spemType;
    
        
     
    
    public String getSpemType() {
		return spemType;
	}


	public void setSpemType(String spemType) {
		this.spemType = spemType;
	}


	public String getUser_name() {
		return user_name;
	}


	public void setUser_name(String user_name) {
		this.user_name = user_name;
	} 


	public String getLcc_name() {
		return lcc_name;
	} 


	public void setLcc_name(String lcc_name) {
		this.lcc_name = lcc_name;
	}

	@Transient
	private String lcc_name; 

    
    private String iceboxName;

    
    private int capacity;

   
    private Date regTime;
    
    private String regTimeStr; 
     
    private int stock;   
     
    private String fridge_spc;    //规格
      
    private String fridge_brand;  //品牌 
    
    private String add_date;  // 更新时间
    
    private String isExsitWayBill;// 是否有运单正在处理

     
    @Transient
    private float proportion;   //百分比
    
    
    public String getProjectName() {
		return projectName;
	}


	public void setProjectName(String projectName) {
		this.projectName = projectName;
	} 
    
	
	
    
    public String getAdd_date() {
		return add_date;
	}


	public void setAdd_date(String add_date) {
		this.add_date = add_date;
	}


	public String getIsExsitWayBill() {
		return isExsitWayBill;
	}


	public void setIsExsitWayBill(String isExsitWayBill) {
		this.isExsitWayBill = isExsitWayBill;
	}


	public String getFridge_spc() {
		return fridge_spc;
	}


	public void setFridge_spc(String fridge_spc) {
		this.fridge_spc = fridge_spc;
	}


	public String getFridge_brand() {
		return fridge_brand;
	}


	public void setFridge_brand(String fridge_brand) {
		this.fridge_brand = fridge_brand;
	}


	public float getProportion() {
		return proportion;
	} 


	public void setProportion(float proportion) {
		this.proportion = proportion;
	}


	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}


	public String getRegTimeStr() {
		return regTimeStr;
	}


	public void setRegTimeStr(String regTimeStr) {
		this.regTimeStr = regTimeStr;
	}

	private String inputer;

    
    public String getId() {
        return id;
    }

   
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    
    public String getProjectid() {
        return projectid;
    }

    
    public void setProjectid(String projectid) {
        this.projectid = projectid == null ? null : projectid.trim();
    }

    
    public String getLccid() {
        return lccid;
    }

    
    public void setLccid(String lccid) {
        this.lccid = lccid == null ? null : lccid.trim();
    }

   
    public String getIceboxName() {
        return iceboxName;
    }

   
    public void setIceboxName(String iceboxName) {
        this.iceboxName = iceboxName == null ? null : iceboxName.trim();
    }

    
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Date getRegTime() {
        return regTime;
    }

    
    public void setRegTime(Date regTime) {
    	
    	SimpleDateFormat format = new  SimpleDateFormat("yyyy-MM-dd");
		this.regTimeStr=format.format(regTime);
        this.regTime = regTime;
    }

    
    public String getInputer() {
        return inputer;
    }

    
    public void setInputer(String inputer) {
        this.inputer = inputer == null ? null : inputer.trim();
    }
}