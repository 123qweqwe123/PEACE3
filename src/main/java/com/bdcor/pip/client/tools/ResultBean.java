package com.bdcor.pip.client.tools;



import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bdcor.pip.client.vo.paper.Paper;
import com.bdcor.pip.client.vo.paper.PaperService;
import com.bdcor.pip.client.vo.paper.ResultOption;

public class ResultBean {


	Interviewee interviewee = null;
	public Paper paper = null;
	List<ResultOption> options = null;
	
	public ResultBean(String intervId,String paperid,List<ResultOption> opstions){
		this.init(intervId,paperid,opstions);
	}
	
	public ResultBean(HttpServletRequest request){
		List<ResultOption> opstions = new ArrayList<ResultOption>();
		String intervId = "";
		String paperid = "";
		Enumeration enumer = request.getParameterNames();
		while(enumer.hasMoreElements()){
			String questinId = enumer.nextElement().toString();
			if("intervid".equals(questinId)){
				intervId = request.getParameter(questinId);
				continue;
			}
			if("paperid".equals(questinId)){
				paperid = request.getParameter(questinId);
				continue;
			}
			
			if(questinId.startsWith("OPTION")){
				String anserId = request.getParameter(questinId);
				ResultOption opton = new ResultOption(questinId,anserId);
				opstions.add(opton);
			}
		}
		this.init(intervId,paperid,opstions);
	}
	
	private void init(String interVId,String paperId,List<ResultOption> options){
//		interviewee = InterviewService.getInstance().getById(interVId);
			interviewee = new Interviewee();
			//interviewee.setId(interVId);
			interviewee.setCpIntervCode(interVId);
		
		paper = PaperService.getInstance().getPage(paperId);
		this.options = options;
	}
	
	
	public Result getResult(){
		Result result = new Result(interviewee,paper);
		result.setOptions(options);
		return result;
	}
	
	
}
