package com.bdcor.pip.web.reserve.filter;

import java.util.Date;

import com.bdcor.pip.core.persistence.domain.BaseFilter;
import com.bdcor.pip.core.utils.Securitys;

public class PipCommReserveFilter extends BaseFilter  {

//     private  String   id;
//     private  String  patientName;
//     private  String  patientId;
//     private  String  projectId;
//	 private  Date vTime;     //患者计划到达时间
//     private  Date  planTime;   //计划时间
//     private String jisuandate;
//     private  String  planTime2;
//     private  Date  realTime;
//     private  String  realTime2;
//     private  String  version;
//     private  String exitReseach;
//     private  String    counts;
//     private  String result;
//	private  String remark;
//	private  String color;   //计算后的颜色
//	private  String lccCode;
//	private  String  lccName;
//	private  String  mobile;
//	private  String  relationMobile;
//	private  String time;  //计划时间
//	private  String status;
//	public String lcccode;// 医院过滤条件 @RequestParam(value = "lcccode")
//	public String pid; // 患者pid过滤条件 @RequestParam(value = "pid")
//	public String pname; // 患者姓名过滤条件@RequestParam(value = "pname")

    private String rq;// 所选日期过滤条件 @RequestParam(value = "rq")
    private String lcccode;
    private String status;
    private String pname;
    private String pid;
    private String startdate;
    private String enddate;
    private String userid = Securitys.getUserId();
    private String ismsg;
    private String rightday; // 标识是否查看当天数据  由status字段决定赋值

    private String patientId;
    private String suifangType;
    private String wcqk;
    private String belongGroup;

    public String getWcqk() {
        return wcqk;
    }

    public void setWcqk(String wcqk) {
        this.wcqk = wcqk;
    }

    public String getSuifangType() {
        return suifangType;
    }

    public void setSuifangType(String suifangType) {
        this.suifangType = suifangType;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getIsmsg() {
        return ismsg;
    }

    public void setIsmsg(String ismsg) {
        this.ismsg = ismsg;
    }

    public String getRightDay() {
        return rightday;
    }

    public void setRq(String rq) {
        this.rq = rq;
    }

    public void setLcccode(String lcccode) {
        this.lcccode = lcccode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if("0".equals(status)){
            this.rightday = "1";
        }else{
            this.rightday = null;
            if (status.equals("1")) {
                this.status = "red";
            } else if (status.equals("2")) {
                this.status = "yel";
            } else {
                this.status = null;
            }
        }
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getRq() {
        return rq;
    }

    public String getLcccode() {
        return lcccode;
    }

    public String getPname() {
        return pname;
    }

    public String getPid() {
        return pid;
    }

    public String getStartdate() {
        return startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public String getUserid() {
        return userid;
    }

    public String getBelongGroup() {
        return belongGroup;
    }

    public void setBelongGroup(String belongGroup) {
        this.belongGroup = belongGroup;
    }

    //    public String getColor() {
//		return color;
//	}
//
//
//
//	public void setColor(String color) {
//		this.color = color;
//	}
//
//
//
//
//     public PipCommReserveFilter(){}
//
//
//
//	public String getId() {
//		return id;
//	}
//
//
//
//	public void setId(String id) {
//		this.id = id;
//	}
//
//
//
//	public String getPatientName() {
//		return patientName;
//	}
//
//
//
//	public void setPatientName(String patientName) {
//		this.patientName = patientName;
//	}
//
//
//
//	public String getPatientId() {
//		return patientId;
//	}
//
//
//
//	public void setPatientId(String patientId) {
//		this.patientId = patientId;
//	}
//
//
//
//	public String getProjectId() {
//		return projectId;
//	}
//
//
//
//	public void setProjectId(String projectId) {
//		this.projectId = projectId;
//	}
//
//
//
//	public Date getPlanTime() {
//		return planTime;
//	}
//
//
//
//	public void setPlanTime(Date planTime) {
//		this.planTime = planTime;
//	}
//
//
//
//	public String getPlanTime2() {
//		return planTime2;
//	}
//
//
//
//	public void setPlanTime2(String planTime2) {
//		this.planTime2 = planTime2;
//	}
//
//
//
//
//	public Date getRealTime() {
//		return realTime;
//	}
//
//
//
//	public void setRealTime(Date realTime) {
//		this.realTime = realTime;
//	}
//
//
//
//	public String getRealTime2() {
//		return realTime2;
//	}
//
//
//
//	public void setRealTime2(String realTime2) {
//		this.realTime2 = realTime2;
//	}
//
//
//
//	public String getVersion() {
//		return version;
//	}
//
//
//
//	public void setVersion(String version) {
//		this.version = version;
//	}
//
//
//
//	public String getExitReseach() {
//		return exitReseach;
//	}
//
//
//
//	public void setExitReseach(String exitReseach) {
//		this.exitReseach = exitReseach;
//	}
//
//
//
//	public String getCounts() {
//		return counts;
//	}
//
//
//
//	public void setCounts(String counts) {
//		this.counts = counts;
//	}
//
//
//
//	public String getStatus() {
//		return status;
//	}
//
//
//
//	public void setStatus(String status) {
//		this.status = status;
//	}
//
//
//
//	public String getResult() {
//		return result;
//	}
//
//
//
//	public void setResult(String result) {
//		this.result = result;
//	}
//
//
//
//	public String getRemark() {
//		return remark;
//	}
//
//
//
//	public void setRemark(String remark) {
//		this.remark = remark;
//	}
//
//
//
//	public String getLccCode() {
//		return lccCode;
//	}
//
//
//
//	public void setLccCode(String lccCode) {
//		this.lccCode = lccCode;
//	}
//
//
//
//	public String getLccName() {
//		return lccName;
//	}
//
//
//
//	public void setLccName(String lccName) {
//		this.lccName = lccName;
//	}
//
//
//
//	public String getMobile() {
//		return mobile;
//	}
//
//
//
//	public void setMobile(String mobile) {
//		this.mobile = mobile;
//	}
//
//
//
//	public String getRelationMobile() {
//		return relationMobile;
//	}
//
//
//
//	public void setRelationMobile(String relationMobile) {
//		this.relationMobile = relationMobile;
//	}
//
//
//
//	public String getTime() {
//		return time;
//	}
//
//
//
//	public String getJisuandate() {
//		return jisuandate;
//	}
//
//
//
//	public void setJisuandate(String jisuandate) {
//		this.jisuandate = jisuandate;
//	}
//
//
//
//	public void setTime(String time) {
//		this.time = time;
//	}
//
//
//
//	public Date getvTime() {
//		return vTime;
//	}
//
//
//
//	public void setvTime(Date vTime) {
//		this.vTime = vTime;
//	}
}
