/**
 * JAVACC DEMO 1.0
 * @copy right zbxsoft company All rights reserved. 
 * @Package com.bdcor.pip.web.msg.controller 
 */

package com.bdcor.pip.web.msg.controller;

import com.bdcor.pip.client.tools.JsonMapper;
import com.bdcor.pip.client.vo.paper.Paper;
import com.bdcor.pip.client.vo.paper.QuestionC;
import com.bdcor.pip.client.vo.paper.QuestionGroup;
import com.bdcor.pip.client.vo.paper.Result;
import com.bdcor.pip.client.vo.paper.ResultOption;
import com.bdcor.pip.client.xml.model.result.ResultQuestionnaireDocumentBean;
import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.common.service.ComboxService;
import com.bdcor.pip.web.data.domain.PipCommPatient;
import com.bdcor.pip.web.data.filter.PatientFilter;
import com.bdcor.pip.web.data.service.PatientService;
import com.bdcor.pip.web.msg.service.MsgJoinService;
import com.bdcor.pip.web.qn.service.impl.AnswerQnlogServiceImpl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**  
 * description:   入选
 * @author yangfeng 创建时间：2016年5月9日         
 */
@Controller()
	@RequestMapping("msg/join")
public class MsgJoinController{	
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private AnswerQnlogServiceImpl ansqnService;
	
	@Autowired
	private MsgJoinService msgJoinService;
	
	@Autowired
	private ComboxService comboxService;
	
	@RequestMapping
	public String init(HttpServletRequest request){
		request.setAttribute("patientId",request.getParameter("patientId"));
//		if(Securitys.getUser().getLccCode().equals("99")){
//			return "msg/join/list";
//		}else{
//			return "msg/join/limitList";
//		}
		return "msg/join/limitList";
	}
	
	@RequestMapping(value = "list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JqgridResponse<PipCommPatient> queryPatientList(PatientFilter pf) {
		pf.setProjectId(Securitys.getCurrentProject());
		pf.setUserId(Securitys.getUserId());
		if( StringUtils.isBlank(pf.getProcesstypes()) || ( !"2".equals(pf.getProcesstypes()) )){
            pf.setIsJoinMsg(1);
		}
		List<PipCommPatient> data = patientService.queryPatientList(pf);
		JqgridResponse<PipCommPatient> response = JqgridResponseContext.getJqgridResponse();
		response.setRows(data);
		return response;

	}
	@RequestMapping(value="openmodal/toEdit")
	public String toEdit(@RequestParam(required=false,value="patientId")String patientId,Model model){
		model.addAttribute("patientId", patientId);
		return "msg/join/edit";
	}
	
	@ResponseBody
	@RequestMapping(value="update")
	public Map<String, Boolean> update(PipCommPatient patient){
		Map<String, Boolean> result = new HashMap<String, Boolean>();
		try
		{
			patient.setProjectId(Securitys.getUser().getCurrent_projectId());
			patientService.updateBySelevePrimaryKey(patient);
			result.put("success", true);
		} catch (Exception e)
		{
			e.printStackTrace();
			result.put("success", false);
		}
		
		return result;
	}
	
	/**
	 * 答卷
	 * @param request
	 * @param model
	 * @param patientId
	 * @return
	 */
	@RequestMapping(value="joinUqs")
	public String joinUqs(HttpServletRequest request,Model model,@RequestParam("uqsCode")String uqsCode,@RequestParam("patientId")String patientId){
		Map<String,String> answerLogMap = msgJoinService.getAnswerLogMap(uqsCode,patientId);
		if(answerLogMap == null){
			model.addAttribute("errorMsg","用户ID不存在");
			return "/WEB-INF/views/msg/join/list.jsp.bak";
		}
		
		String uqsVersion = answerLogMap.get("UQSVERSION");
		if(uqsVersion==null || uqsVersion.trim().length()==0){
			uqsVersion = msgJoinService.getCurrentVersion(Securitys.getCurrentProject()+"."+uqsCode);
		}
		
		String basePath = this.getClass().getResource("/").getPath();
		if(basePath.indexOf(":")>-1){//windows
			basePath = basePath.substring(1);
		}
		String path = basePath+"paper/UQS_"+uqsVersion.replaceAll("\\.","_")+".xml";
		
		Paper qn = msgJoinService.getPaperByPath(path);
		if (qn == null) {
			model.addAttribute("errorMsg", "问卷模版不存在");
			return "/WEB-INF/views/msg/join/list.jsp.bak";
		}
	
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String UQSBeginTime = sf.format(new Date());
	
		String resultPath = answerLogMap.get("UQSFILE");
		Result r = null;
		String paperLabel = uqsVersion.substring(4, 7);
		Map<String, String> map = new HashMap<String, String>();
		if (resultPath != null && resultPath.trim().length() > 0) {
			try {
				r = new Result(ResultQuestionnaireDocumentBean.Factory.parse(
						new FileInputStream(resultPath)).getQuestionnaire());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
//		else {
			if (paperLabel.equals("002")  || paperLabel.equals("015")) {
				List<Map<String, String>> answer001 = msgJoinService.getUqsAnswer(patientId,"001");
				if (answer001 != null && answer001.size() > 0) {
					for (Map<String, String> m : answer001) {
						if(m.get("QUESTIONSET_ID").equals("1")||m.get("QUESTIONSET_ID").equals("2")){
							map.put(m.get("QUESTIONSET_ID") + "_"+ m.get("QUESTION_ID") + "_"+ m.get("OPTION_ID"),
								m.get("ANSWER") == null ? "" : m.get("ANSWER"));

						  if(m.get("QUESTIONSET_ID").equals("2") && m.get("QUESTION_ID").equals("1") && Integer.parseInt(m.get("OPTION_ID").trim())<6){
							  map.put(m.get("QUESTIONSET_ID") + "_"+ m.get("QUESTION_ID") + "_"+ m.get("OPTION_ID"),StringUtils.isEmpty(comboxService.getAreaName(m.get("ANSWER"))) ? m.get("ANSWER") : comboxService.getAreaName(m.get("ANSWER")));
						  }
							// 末次随访手机号要从patient表获取
							if(paperLabel.equals("015") && m.get("QUESTIONSET_ID").equals("2") &&
									m.get("QUESTION_ID").equals("3") ){
								if( StringUtils.isNotBlank(answerLogMap.get("MOBILE")) ){
									map.put("2_3_1",answerLogMap.get("MOBILE"));
								}else{
									map.put("2_3_2","");
								}
							}
						}
					}
				}else{
					map.put("1_1_1",answerLogMap.get("PATIENT_NAME"));
					map.put("1_4_1",answerLogMap.get("ID_NUMBER"));
				}
			}
			//初始化默认值
			if (paperLabel.equals("010")) {
				List<Map<String, String>> answer001 = msgJoinService.getUqsAnswer(patientId,"001");
				if (answer001 != null && answer001.size() > 0) {
					for (Map<String, String> m : answer001) {
						if(m.get("QUESTIONSET_ID").equals("1")||m.get("QUESTIONSET_ID").equals("2")){
							map.put(m.get("QUESTIONSET_ID") + "_"+ m.get("QUESTION_ID") + "_"+ m.get("OPTION_ID"),
								m.get("ANSWER") == null ? "" : m.get("ANSWER"));

						  if(m.get("QUESTIONSET_ID").equals("2") && m.get("QUESTION_ID").equals("1") && Integer.parseInt(m.get("OPTION_ID").trim())<6){
							  map.put(m.get("QUESTIONSET_ID") + "_"+ m.get("QUESTION_ID") + "_"+ m.get("OPTION_ID"),StringUtils.isEmpty(comboxService.getAreaName(m.get("ANSWER"))) ? m.get("ANSWER") : comboxService.getAreaName(m.get("ANSWER")));
						  }
						}
					}
				}

				if("1".equals(answerLogMap.get("IS_DIABETES"))){
					map.put("5_1_1","");
				}else if("2".equals(answerLogMap.get("IS_DIABETES"))){
					map.put("5_1_2","");
				}

				if("1".equals(answerLogMap.get("IS_XJGSBS"))){
					map.put("5_10_1","");
				}else if("2".equals(answerLogMap.get("IS_XJGSBS"))){
					map.put("5_10_2","");
				}

				map.put("5_7_1",answerLogMap.get("GLU"));
				map.put("5_7_2",answerLogMap.get("HBA1C"));
				map.put("5_7_3",answerLogMap.get("GLU_DATE"));
			}

			if (paperLabel.equals("011")) {

			}

			if(paperLabel.equals("012")){
				List<Map<String, String>> answer010 = msgJoinService.getUqsAnswer(patientId,"010");
				if (answer010 != null && answer010.size() > 0) {
					for (Map<String, String> m : answer010) {
						if(m.get("QUESTIONSET_ID").equals("5") && Integer.parseInt(m.get("QUESTION_ID"))<=9)
						map.put("1_"+ m.get("QUESTION_ID") + "_"+ m.get("OPTION_ID"),m.get("ANSWER") == null ? "" : m.get("ANSWER"));
					}
				}

			}
//		}

		 if(uqsCode.equals("011")){
			 String hosDate=ansqnService.getStartHosDate(patientId,"011",answerLogMap.get("STATE"));
			  for(QuestionGroup qg : qn.getQGroup()){
				if(qg.getQs()!=null && qg.getId().equals("3")){
					for(QuestionC q:qg.getQs()){
						if(q.getOptions()!=null && q.getId().equals("1")){
							q.setDisplayname("自"+hosDate.substring(0,4)+"年"+hosDate.substring(4,6)+"月"+hosDate.substring(6)+"日  ，您是否住过院(至少在医院住过一晚)？");
						}
					}
				}
			  }
		  }

		if( uqsCode.equals("014") ){ // 末次随访数据填充

           Map<String, Object> m_pinfo = msgJoinService.getPatientInfo(patientId);
            model.addAttribute("belonggroup",m_pinfo.get("BELONG_GROUP"));
			// 获取6月随访时间  即为末次随访的上次随访时间
			String hosDate=ansqnService.getStartHosDate(patientId,"003","1");
			for(QuestionGroup qg : qn.getQGroup()){
				if(qg.getQs()!=null && qg.getId().equals("6")){
					for(QuestionC q:qg.getQs()){
						if(q.getOptions()!=null && q.getId().equals("1")){
							q.setDisplayname("自"+hosDate.substring(0,4)+"年"+hosDate.substring(4,6)+"月"+hosDate.substring(6)+"日  ，您是否住过院(至少在医院住过一晚)？");
						}
					}
				}
			}

			List<Map<String, String>> answer001 = msgJoinService.getUqsAnswer(patientId,"010");
			if( answer001 == null || answer001.size() == 0 ){
				answer001 = msgJoinService.getUqsAnswer(patientId,"013");
			}
			if (answer001 != null && answer001.size() > 0) {
				for (Map<String, String> m : answer001) {
					if(m.get("QUESTIONSET_ID").equals("1")||m.get("QUESTIONSET_ID").equals("2")){
						map.put(m.get("QUESTIONSET_ID") + "_"+ m.get("QUESTION_ID") + "_"+ m.get("OPTION_ID"),
								m.get("ANSWER") == null ? "" : m.get("ANSWER"));

						if(m.get("QUESTIONSET_ID").equals("2") && m.get("QUESTION_ID").equals("1") && Integer.parseInt(m.get("OPTION_ID").trim())<6){
							map.put(m.get("QUESTIONSET_ID") + "_"+ m.get("QUESTION_ID") + "_"+ m.get("OPTION_ID"),StringUtils.isEmpty(comboxService.getAreaName(m.get("ANSWER"))) ? m.get("ANSWER") : comboxService.getAreaName(m.get("ANSWER")));
						}
						// 末次随访手机号要从patient表获取
						if(paperLabel.equals("014") && m.get("QUESTIONSET_ID").equals("2") &&
								m.get("QUESTION_ID").equals("3") ){
							if( StringUtils.isNotBlank(answerLogMap.get("MOBILE")) ){
								map.put("2_3_1",answerLogMap.get("MOBILE"));
							}else{
								map.put("2_3_2","");
							}
						}
					}
				}
			}else{
				map.put("1_1_1",answerLogMap.get("PATIENT_NAME"));
				map.put("1_4_1",answerLogMap.get("ID_NUMBER"));
				if( StringUtils.isNotBlank(answerLogMap.get("SEX"))){
					map.put("1_2_"+answerLogMap.get("SEX"),"");
				}
				map.put("1_3_1",answerLogMap.get("BIRTHDAY") == null ? "" : answerLogMap.get("BIRTHDAY"));
				if( StringUtils.isNotBlank(answerLogMap.get("PHONE"))){
					map.put("2_2_1",answerLogMap.get("PHONE"));
				}else{
					map.put("2_2_2","");
				}
				if( StringUtils.isNotBlank(answerLogMap.get("MOBILE"))){
					map.put("2_3_1",answerLogMap.get("MOBILE"));
				}else{
					map.put("2_3_2","");
				}
				if( StringUtils.isNotBlank(answerLogMap.get("ADDRESS"))){
					map.put("2_1_7",answerLogMap.get("ADDRESS"));
				}
				if( StringUtils.isNotBlank(answerLogMap.get("LINK_MAN1"))){
					map.put("2_4_1",answerLogMap.get("LINK_MAN1"));
				}
				if( StringUtils.isNotBlank(answerLogMap.get("LINK_MAN1_MOBILE"))){
					map.put("2_6_1",answerLogMap.get("LINK_MAN1_MOBILE"));
				}
				if( StringUtils.isNotBlank(answerLogMap.get("LINK_MAN2"))){
					map.put("2_7_1",answerLogMap.get("LINK_MAN2"));
				}
				if( StringUtils.isNotBlank(answerLogMap.get("LINK_MAN2_MOBILE"))){
					map.put("2_9_1",answerLogMap.get("LINK_MAN2_MOBILE"));
				}
				String relation1 = answerLogMap.get("LINK_MAN1_RELATION");
				if( StringUtils.isNotBlank(relation1) ){
					if( "父母".equals(relation1) ){
						map.put("2_5_1","");
					}else if("配偶".equals(relation1) ){
						map.put("2_5_2","");
					}else if("子女".equals(relation1) ){
						map.put("2_5_3","");
					}else if("兄弟姐妹".equals(relation1) ){
						map.put("2_5_4","");
					}else{
						map.put("2_5_5",relation1);
					}

				}
				String relation2 = answerLogMap.get("LINK_MAN2_RELATION");
				if( StringUtils.isNotBlank(relation2) ){
					if( "父母".equals(relation2) ){
						map.put("2_8_1","");
					}else if("配偶".equals(relation2) ){
						map.put("2_8_2","");
					}else if("子女".equals(relation2) ){
						map.put("2_8_3","");
					}else if("兄弟姐妹".equals(relation2) ){
						map.put("2_8_4","");
					}else{
						map.put("2_8_5",relation2);
					}

				}

			}

			//  设置提示信息 第三题组 选项2 选中时提示
			qn.getQGroup().get(2).getQs().get(0).getOptions().get(1).setAlert("允许在外院检查,给患者报销费用,请协助尽可能完成此项检查");
		}

		if (r != null && r.getOptions() != null) {
            String oldValue = "";
            if (r.getOptions() != null) {
                String id;
                for( ResultOption ro : r.getOptions() ){
                    id = ro.getQuestionSetId()+"_"+ro.getQuestionId()+"_";
                    if( !oldValue.equals(id) ){
                        removeMapByLike(map,id);
                        oldValue = id;
                    }
                }

				for (ResultOption ro : r.getOptions()) {
					String dictName = comboxService.getDictName(qn,ro.getQuestionSetId(), ro.getQuestionId(),ro.getResultId(), ro.getResutlStr());
					if (dictName == null) {
						map.put(ro.getQuestionSetId() + "_"+ ro.getQuestionId() + "_" + ro.getResultId(),ro.getResutlStr());
					} else {
						map.put(ro.getQuestionSetId() + "_"+ ro.getQuestionId() + "_" + ro.getResultId(),dictName);
						map.put("dict" + ro.getQuestionSetId() + "_"+ ro.getQuestionId() + "_" + ro.getResultId(),ro.getResutlStr());
					}
				}
			}
		}
		model.addAttribute("paper", qn);
		model.addAttribute("resultOptionMap", map);
		model.addAttribute("resultOptionMapJson", JsonMapper.objectToJson(map));
	
		model.addAttribute("UQSBeginTime", UQSBeginTime);
		model.addAttribute("uqsVersion", uqsVersion);
		model.addAttribute("patientName", answerLogMap.get("PATIENT_NAME"));
		model.addAttribute("patientId", patientId);
		model.addAttribute("lccCode", answerLogMap.get("LCC_CODE"));

		model.addAttribute("isDiabetes", answerLogMap.get("IS_DIABETES"));
		model.addAttribute("state", answerLogMap.get("STATE") == null ? "" : answerLogMap.get("STATE") );

		if ("1".equals(answerLogMap.get("STATE"))) {
			return "msg/join/showQn";
		}
		return "msg/join/joinUqs";
	}
	
	private int getAge(String birthday) {
		Date nowDate = new Date();
		int nowYear = nowDate.getYear()+1900;int nowM = nowDate.getMonth()+1;int nowDay = nowDate.getDate();
		int bYear = Integer.parseInt(birthday.substring(0,4));
		int bM = Integer.parseInt(birthday.substring(5,7));
		int bDay = Integer.parseInt(birthday.substring(8,10));
		if(nowM>bM)return nowYear-bYear;
		else if(nowM<bM)return nowYear-bYear-1;
		else if(nowDay>=bDay)return nowYear-bYear;
		else return nowYear-bYear-1;
	}

	/**
	 * 暂存问卷
	 * @param request
	 * @return
	 */
	@RequestMapping(value="saveQn")
	@ResponseBody
	public Map<String, Object> saveTempQn(HttpServletRequest request){
		String uqsVersion = request.getParameter("uqsVersion");
		String patientId = request.getParameter("patientId");
		Map<String, Object> result = new HashMap<String, Object>();
		if(uqsVersion==null || uqsVersion.length()<10 || patientId==null){
			result.put("result",2);
			return result;
		}
		
		uqsVersion = uqsVersion.replaceAll("_",".");
		
		String basePath = this.getClass().getResource("/").getPath();
		if(basePath.indexOf(":")>-1){//windows
			basePath = basePath.substring(1);
		}
		String path = basePath+"paper/UQS_"+uqsVersion.replaceAll("\\.","_")+".xml";
		Paper qn = msgJoinService.getPaperByPath(path);
		if(qn == null){
			result.put("result",2);
			return result;
		}
  
		Map<String,String> answerLogMap = msgJoinService.getAnswerLogMap(uqsVersion.substring(4, 7),patientId);
		if(answerLogMap == null){
			result.put("result",2);
			return result;
		}
		if("1".equals(answerLogMap.get("STATE"))){
			result.put("result",7);
			return result;		 
		}
		
		try{
			msgJoinService.saveQn(request,qn);
			result.put("result",1);
		}catch (Exception e) {
			e.printStackTrace();
			result.put("result",2);
		}

		result.put("processtype",msgJoinService.getProcesstype(patientId));

		// 末次随访在处理时需要增加一个参数来区分跳转页面
		if( "004.014.001".equals(uqsVersion) ){
			PatientFilter pf = new PatientFilter();
			pf.setPatientId(patientId);
			pf.setProjectId("004");
			List<PipCommPatient> list = patientService.queryPatientList(pf);
			if(list.size() > 0 && list.get(0).getState002002() != null && list.get(0).getState002002() == 1 ){
				result.put("redirectPath","2"); // 标识为 非干预面访跳转
			}else if("2".equals(msgJoinService.getProcesstype(patientId))){ // 补录人员
				result.put("redirectPath","3"); // 标识为 补录人员跳转
			}else{
				result.put("redirectPath","1"); // 标识为 CHAT研究人员跳转
			}
		}
		return result;
	}
	
	@RequestMapping(value = "checkSendMsg", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity checkSendMsg(@RequestParam("patientId")String patientId,@RequestParam("mobile")String mobile,@RequestParam("msgTypeCode")String msgTypeCode){
		Map<String,Object> res = new HashMap<String, Object>();
		res.put("success", true);
		res.put("type", 2);
		try{
			int c = msgJoinService.getSendCount(patientId,mobile,msgTypeCode);
			if(c>0){
				res.put("type", 1);
			}
		}catch(Exception e){
			res.put("success", false);
			e.printStackTrace();
		}
		return new ResponseEntity(res, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "sendMsg", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity sendMsg(@RequestParam("patientId")String patientId,@RequestParam("mobile")String mobile,@RequestParam("msgTypeCode")String msgTypeCode){
		Map<String,Object> res = new HashMap<String, Object>();
		res.put("success", true);
		try{
			int type = msgJoinService.sendWelMsg(patientId,mobile,msgTypeCode);
			res.put("type", type);
		}catch(Exception e){
			res.put("success", false);
			e.printStackTrace();
		}
		return new ResponseEntity(res, HttpStatus.OK);
	}

	@RequestMapping("checkgroupinfo")
	@ResponseBody
	public ResponseEntity checkGroupInfo(String pid){

		return new ResponseEntity(msgJoinService.checkGroupInfo(pid), HttpStatus.OK);
	}

	//   刨除Map里 模糊查询到的key然后删除key-value
	public void removeMapByLike(Map map , String id){
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            if( entry.getKey().toString().contains(id) ){
                map.remove(entry.getKey());
                iter = map.entrySet().iterator();
            }
        }
    }
}

