package com.bdcor.pip.client.vo.paper;

import com.bdcor.pip.client.xml.model.result.ResultResultDocumentBean;


/**
 * 结果项
 * @author Administrator
 *
 */
public class ResultOption {
	private String questionSetId;
	private String questionId;
	private String resultId;
	private String resutlStr;
	
	public ResultOption(ResultResultDocumentBean.Result r){
		this.questionId = r.getQuestionId();
		this.questionSetId = r.getQuestionset();
		this.resultId = r.getOptionid()+"";
		this.resutlStr = r.getAnswer();
	}
	
	public ResultOption(String questionSetId,String questionId,String resultId,String resutlStr){
		this.questionSetId = questionSetId;
		this.questionId = questionId;
		this.resultId = resultId;
		this.resutlStr = resutlStr;
	}
	
	public ResultOption(String requestKey,String requestValue){
		String[] ids = requestKey.split("_");
		if(ids.length==2){
			this.questionSetId = ids[0].substring(6);
			this.questionId = ids[1];
			this.resultId = requestValue;
			this.resutlStr = "";
		}else{
			this.questionSetId = ids[0].substring(6);
			this.questionId = ids[1];
			this.resultId = ids[2];
			this.resutlStr = requestValue;
		}
	}
	public String getResutlStr() {
		return resutlStr;
	}

	public void setResutlStr(String resutlStr) {
		this.resutlStr = resutlStr;
	}

	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getResultId() {
		return resultId;
	}
	public void setResultId(String resultId) {
		this.resultId = resultId;
	}

	public String getQuestionSetId() {
		return questionSetId;
	}

	public void setQuestionSetId(String questionSetId) {
		this.questionSetId = questionSetId;
	}

	
}
