/**
 * JAVACC DEMO 1.0
 * @copy right zbxsoft company All rights reserved. 
 * @Package com.bdcor.pip.web.qn.filter 
 */

package com.bdcor.pip.web.qn.filter;

import java.util.Date;
/**  
 * description:  
 * @author yangfeng 创建时间：2016年1月21日         
 */
public class ScmSearchFilterNoBase
{
	private String projectId; //项目id
	private String lccCode; //医院id
	private String lccName;//医院名称
	private String patientId ; //患者ID
	private String patientName; //患者名称
	private String answer; //采样编号
	private Date createDate;//问卷提交时间
	private String sfType;//随访类型
	private String state;  //--样本状态
	private String optionId;
	private String questionId;
	private String questionsetId;
	
	private String examinationStartDate; //问卷开始时间
	private String examinationEndDate; //问卷结束时间
	private String boxCode ;
	public void setBoxCode(String boxCode)
	{
		this.boxCode = boxCode;
	}
	public String getBoxCode()
	{
		return boxCode;
	}
	public String getExaminationEndDate()
	{
		return examinationEndDate;
	}
	public void setExaminationEndDate(String examinationEndDate)
	{
		this.examinationEndDate = examinationEndDate;
	}
	public String getExaminationStartDate()
	{
		return examinationStartDate;
	}
	public void setExaminationStartDate(String examinationStartDate)
	{
		this.examinationStartDate = examinationStartDate;
	}
	public String getProjectId()
	{
		return projectId;
	}
	public void setProjectId(String projectId)
	{
		this.projectId = projectId;
	}
	public String getPatientId()
	{
		return patientId;
	}
	public void setPatientId(String patientId)
	{
		this.patientId = patientId;
	}
	public String getPatientName()
	{
		return patientName;
	}
	public void setPatientName(String patientName)
	{
		this.patientName = patientName;
	}
	public String getOptionId()
	{
		return optionId;
	}
	public void setOptionId(String optionId)
	{
		this.optionId = optionId;
	}
	public String getQuestionId()
	{
		return questionId;
	}
	public void setQuestionId(String questionId)
	{
		this.questionId = questionId;
	}
	public String getQuestionsetId()
	{
		return questionsetId;
	}
	public void setQuestionsetId(String questionsetId)
	{
		this.questionsetId = questionsetId;
	}
	public String getLccCode()
	{
		return lccCode;
	}
	public void setLccCode(String lccCode)
	{
		this.lccCode = lccCode;
	}
	public String getLccName()
	{
		return lccName;
	}
	public void setLccName(String lccName)
	{
		this.lccName = lccName;
	}
	public String getAnswer()
	{
		return answer;
	}
	public void setAnswer(String answer)
	{
		this.answer = answer;
	}
	public Date getCreateDate()
	{
		return createDate;
	}
	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}
	public String getSfType()
	{
		return sfType;
	}
	public void setSfType(String sfType)
	{
		this.sfType = sfType;
	}
	public String getState()
	{
		return state;
	}
	public void setState(String state)
	{
		this.state = state;
	}
	
}

