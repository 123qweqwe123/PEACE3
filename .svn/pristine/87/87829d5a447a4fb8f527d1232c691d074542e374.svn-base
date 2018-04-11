package com.bdcor.pip.web.qn.controller;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bdcor.pip.client.tools.JsonMapper;
import com.bdcor.pip.client.vo.paper.Paper;
import com.bdcor.pip.client.vo.paper.Result;
import com.bdcor.pip.client.vo.paper.ResultOption;
import com.bdcor.pip.client.xml.model.result.ResultQuestionnaireDocumentBean;
import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.common.service.ComboxService;
import com.bdcor.pip.web.qn.domain.BloodTestPatientVo;
import com.bdcor.pip.web.qn.filter.BloodTestFilter;
import com.bdcor.pip.web.qn.filter.EventFilter;
import com.bdcor.pip.web.qn.service.AnswerQnService;
import com.bdcor.pip.web.qn.service.BloodTestService;

@Controller
@RequestMapping("qn/bloodTestMgt") 
public class BloodTestMgtController {
	
	@Autowired
	private BloodTestService bloodTestService;
	
	@Autowired
	private ComboxService comboxService;
	
	@Autowired
	private AnswerQnService answerQnService;
	
	/**
	 * 血检报告管理首页
	 * @return
	 */
	@RequestMapping
	public String init(HttpServletRequest request){
		String result = request.getParameter("answerQnResult");
		request.setAttribute("answerQnResult",result);
		return "qn/bloodTestMgt/list";
	}
	
	/**
	 * 事件管理_人员列表
	 */
	@RequestMapping(value = "getBloodTestPatientList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JqgridResponse<BloodTestPatientVo> getBloodTestPatientList(BloodTestFilter filter) { 
		List<BloodTestPatientVo> data = bloodTestService.getEventPatientList(filter);
		JqgridResponse<BloodTestPatientVo> response = JqgridResponseContext.getJqgridResponse();
		response.setRows(data);
		return response;
	}
	
	/**
	 * 查看上报列表
	 */
	@RequestMapping(value="openmodalShowBloodTestList")
	public String openmodalShowBloodTestList(HttpServletRequest request){
		request.setAttribute("patientId",request.getParameter("patientId"));
		request.setAttribute("uqsCode",request.getParameter("uqsCode"));
		return "qn/bloodTestMgt/showBloodTestList";
	}
	

	@RequestMapping(value = "showBloodTestList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JqgridResponse<Map<String,Object>> showReportList(BloodTestFilter filter) { 
		List<Map<String,Object>> data = bloodTestService.showBloodTestList(filter);
		JqgridResponse<Map<String,Object>> response = JqgridResponseContext.getJqgridResponse();
		response.setRows(data);
		return response;
	}
	
	
	/**
	 * 补报事件(弹出页面)
	 */
	@RequestMapping(value="openmodalAnswerQn")
	public String openmodalAnswerQn(HttpServletRequest request,Model model,@RequestParam("uqsCode")String uqsCode,@RequestParam("patientId")String patientId){
		String logId=request.getParameter("logId");
		String uqsTypeName=request.getParameter("uqsTypeName");
		if(logId==null || logId.trim().length()==0){
			logId = null;
		}
		if(uqsTypeName==null || uqsTypeName.trim().length()==0){
			uqsTypeName = null;
		}
		Map<String,String> answerLogMap = answerQnService.getAnswerLogMap_MIN(uqsCode,patientId,logId);
		if(answerLogMap == null){
			model.addAttribute("errorMsg","用户ID不存在");
			return "qn/bloodTestMgt/bloodTest";
		}
		
		String uqsVersion = null;
		if(answerLogMap.get("UQSVERSION")==null || answerLogMap.get("UQSVERSION").trim().length()==0){
			uqsVersion = Securitys.getCurrentProject()+"."+uqsCode+".001";
		}else{
			uqsVersion = answerLogMap.get("UQSVERSION");
		}
		
		if(answerLogMap.get("uqsTypeName")!=null){
			if(answerLogMap.get("uqsTypeName").startsWith("首次")){
				uqsTypeName="1";
			}else if(answerLogMap.get("uqsTypeName").startsWith("6月")){
				uqsTypeName="6";
			}else if(answerLogMap.get("uqsTypeName").startsWith("末次")){// 12月随访修改为末次随访
				uqsTypeName="12";
			}else if(answerLogMap.get("uqsTypeName").startsWith("18月")){
				uqsTypeName="18";
			}else if(answerLogMap.get("uqsTypeName").startsWith("24月")){
				uqsTypeName="24";
			}
		}
		String path = request.getSession().getServletContext().getRealPath("/")+"WEB-INF/classes/paper/UQS_"+uqsVersion.replaceAll("\\.","_")+".xml";
		//String path = "D:\\gener-dev\\workspace\\CHINAPEACE3-web\\src\\main\\resources"+"/paper/UQS_"+uqsVersion.replaceAll("\\.","_")+".xml";
		Paper qn = answerQnService.getPaperByPath(path);
		  if(qn == null){
			  model.addAttribute("errorMsg","问卷模版不存在");
			  return "qn/bloodTestMgt/bloodTest";
		  }
		  
		  SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  String UQSBeginTime = sf.format(new Date());
		  
		  String resultPath = answerLogMap.get("UQSFILE");
		  Result r = null;
		  if(resultPath != null && resultPath.trim().length()>0){
			  try{
					r = new Result(ResultQuestionnaireDocumentBean.Factory.parse(new FileInputStream(resultPath)).getQuestionnaire());
			  }catch (Exception e) {
			  }
		  }
		  
		  Map<String,String> map = new HashMap<String,String>();
		  if(r != null && r.getOptions() != null){
				if(r.getOptions() != null){
					for(ResultOption ro:r.getOptions()){
						String dictName = comboxService.getDictName(qn,ro.getQuestionSetId(),ro.getQuestionId(),ro.getResultId(),ro.getResutlStr());
						if(dictName == null){
							map.put(ro.getQuestionSetId()+"_"+ro.getQuestionId()+"_"+ro.getResultId(),ro.getResutlStr());
						}else{
							map.put(ro.getQuestionSetId()+"_"+ro.getQuestionId()+"_"+ro.getResultId(),dictName);
							map.put("dict"+ro.getQuestionSetId()+"_"+ro.getQuestionId()+"_"+ro.getResultId(),ro.getResutlStr());
						}
					}
				}
		  }
		  
		  model.addAttribute("paper",qn);
		  model.addAttribute("resultOptionMap",map);
		  model.addAttribute("resultOptionMapJson",JsonMapper.objectToJson(map));
		  
		  model.addAttribute("UQSBeginTime",UQSBeginTime);
		  model.addAttribute("uqsVersion",uqsVersion);
		  model.addAttribute("patientId",patientId);
		  model.addAttribute("logId",logId);
		  model.addAttribute("uqsTypeName",uqsTypeName);
		  
		  if(request.getParameter("onlyShow")!=null){
			  return "qn/eventMgt/showReport";
		  }
		  
		  return "qn/bloodTestMgt/bloodTest";
	}
	
	/**
	 * 提交血检结果问卷
	 * @param request
	 * @return
	 */
	@RequestMapping(value="saveQn")
	public String saveQn(HttpServletRequest request,RedirectAttributes attr){
		String uqsVersion = request.getParameter("uqsVersion");
		String patientId = request.getParameter("patientId");
		String logId = request.getParameter("logId");
		if(uqsVersion==null || uqsVersion.length()<10 || patientId==null){
			attr.addAttribute("answerQnResult",2);
			return "redirect:/";
		}
		
		uqsVersion = uqsVersion.replaceAll("_",".");
		
		String path = request.getSession().getServletContext().getRealPath("/")+"WEB-INF/classes/paper/UQS_"+uqsVersion.replaceAll("\\.","_")+".xml";
		//String path = "D:\\gener-dev\\workspace\\CHINAPEACE3-web\\src\\main\\resources"+"/paper/UQS_"+qnVersion.replaceAll("\\.","_")+".xml";
		Paper qn = answerQnService.getPaperByPath(path);
		if(qn == null){
			attr.addAttribute("answerQnResult",2);
			return "redirect:/";
		}
  
		Map<String,String> answerLogMap = answerQnService.getAnswerLogMap(uqsVersion.substring(4, 7),patientId);
		if(answerLogMap == null){
			attr.addAttribute("answerQnResult",2);
			return "redirect:/";
		}
		
		try{
			answerQnService.saveQn_MIN(request,qn,logId);
			attr.addAttribute("answerQnResult",1);
		}catch (Exception e) {
			e.printStackTrace();
			attr.addAttribute("answerQnResult",2);
		}
		return "redirect:/";
	}
	
}
