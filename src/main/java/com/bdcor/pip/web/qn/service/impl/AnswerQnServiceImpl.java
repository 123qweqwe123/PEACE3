package com.bdcor.pip.web.qn.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.xmlbeans.XmlOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bdcor.pip.client.vo.paper.Paper;
import com.bdcor.pip.client.vo.paper.QuestionC;
import com.bdcor.pip.client.vo.paper.QuestionGroup;
import com.bdcor.pip.client.vo.paper.QuestionOption;
import com.bdcor.pip.client.vo.paper.Result;
import com.bdcor.pip.client.vo.paper.ResultOption;
import com.bdcor.pip.client.xml.model.TempletQuestionDocumentBean.Question;
import com.bdcor.pip.client.xml.model.TempletQuestionSetDocumentBean.QuestionSet;
import com.bdcor.pip.client.xml.model.TempletQuestionnaireDocumentBean;
import com.bdcor.pip.client.xml.model.TempletQuestionnaireDocumentBean.Questionnaire;
import com.bdcor.pip.client.xml.model.result.ResultDoctypeDocumentBean.Doctype;
import com.bdcor.pip.client.xml.model.result.ResultQuestionnaireDocumentBean;
import com.bdcor.pip.core.utils.CustomizedPropertyPlaceholderConfigurer;
import com.bdcor.pip.core.utils.GenerateKey;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.common.dao.ComboxMapper;
import com.bdcor.pip.web.data.filter.PatientFilter;
import com.bdcor.pip.web.material.supp.dao.PipMmsScmarchivesMapper;
import com.bdcor.pip.web.qn.dao.AnswerQnDao;
import com.bdcor.pip.web.qn.dao.UqsEventDao;
import com.bdcor.pip.web.qn.filter.ScmSearchFilter;
import com.bdcor.pip.web.qn.filter.ScmSearchFilterNoBase;
import com.bdcor.pip.web.qn.service.AnswerQnService;

import antlr.StringUtils;
import bsh.StringUtil;

@Service
public class AnswerQnServiceImpl implements AnswerQnService {
	
	@Autowired
	private AnswerQnDao answerQnDao;
	@Autowired
	private UqsEventDao uqsEventDao;
	@Autowired
	private ComboxMapper comboxDao;
	@Autowired
	private PipMmsScmarchivesMapper pipMmsScmarchivesMapper;
	
	private static final SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");
	@Override
	@Cacheable(value="paperCache",key="#path")
	public Paper getPaperByPath(String path) {
		TempletQuestionnaireDocumentBean questionnaire = null;
		try {
			File f = new File(path);
			questionnaire = (TempletQuestionnaireDocumentBean)TempletQuestionnaireDocumentBean.Factory.parse(new FileInputStream(f));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Paper paper = null;
		if(questionnaire!=null){
			Questionnaire qn = questionnaire.getQuestionnaire();
			paper = new Paper(qn);
			for(QuestionSet qset:qn.getQuestionSetArray()){
				QuestionGroup qg = new QuestionGroup(qset);
				//循环试题
				for(Question q:qset.getQuestionArray()){
					qg.getQs().add(new QuestionC(q));
				}
				paper.getQGroup().add(qg);
				Collections.sort(qg.getQs(),new Comparator<QuestionC>() {
					@Override
					public int compare(QuestionC o1, QuestionC o2) {
						if(o1.getOrder()<=o2.getOrder())return -1;
						return 1;
					}
				});
			}
			Collections.sort(paper.getQGroup(),new Comparator<QuestionGroup>() {
				@Override
				public int compare(QuestionGroup o1, QuestionGroup o2) {
					if(o1.getOrder()<=o2.getOrder())return -1;
					return 1;
				}
			});
		}
		return paper;
	}

	@Override
	public Map<String, String> getAnswerLogMap(String uqsCode,String patientId) {
		Map<String,String> map = new HashMap<String, String>();
		map.put("projectId", Securitys.getCurrentProject());
		map.put("uqsCode", uqsCode);
		map.put("patientId", patientId);
		return answerQnDao.getAnswerLogMap(map);
	}

	@Override
	public String getDictName(Paper qn, String questionSetId,
			String questionId, String resultId, String resutlStr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(
			propagation = Propagation.REQUIRED ,
			isolation = Isolation.DEFAULT,
			rollbackFor = Exception.class
		)
	public void saveQn(HttpServletRequest request,Paper paper)throws Exception{
		Result r = new Result(request,paper);
		//保存问卷文件
		String filePath = CustomizedPropertyPlaceholderConfigurer.getContextProperty("uqs_result_path")+"/"
						+new SimpleDateFormat("yyyyMMdd").format(new Date())+"/";
		if(!new File(filePath).exists()){
			new File(filePath).mkdirs();
		}
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date endTime = new Date();
		filePath += r.getPatientID()+"_"+r.getUQSVersion()+new Date().getTime()+".xml";
		Map<String,Object> AnswerQnLog = new HashMap<String, Object>();
		AnswerQnLog.put("projectId",Securitys.getCurrentProject());
		AnswerQnLog.put("lccCode",Securitys.getUser().getLccCode());
		AnswerQnLog.put("patientId",r.getPatientID());
		AnswerQnLog.put("uqsVersion",r.getUQSVersion());
		AnswerQnLog.put("startTime",sf.parse(r.getUQSBeginTime()));
		AnswerQnLog.put("endTime",endTime);
		AnswerQnLog.put("createBy",r.getOperaterID());
		AnswerQnLog.put("uqsFile", filePath);
		AnswerQnLog.put("state", request.getParameter("tempSave")==null?1:2);
				
		answerQnDao.insertAnswerQnLog(AnswerQnLog);
		
		saveFile(r,filePath);
		
		if(r.getOptions() == null || r.getOptions().size()==0 || request.getParameter("tempSave")!=null){
			return;
		}
		
		String paperLabel = paper.getPaperVersion().substring(4,7);
		String col_BLOOD_TEST="";
		String o_BLOOD_TEST="";
		if(paperLabel.equals("001")){
			col_BLOOD_TEST="BLOOD_TEST";
			o_BLOOD_TEST="4_1_1,4_1_3";
		}else if(paperLabel.equals("002")){
			col_BLOOD_TEST="BLOOD_TEST6";
			o_BLOOD_TEST="3_3_1";
		}else if(paperLabel.equals("003")){
			col_BLOOD_TEST="BLOOD_TEST12";
			o_BLOOD_TEST="3_3_1";
		}else if(paperLabel.equals("004")){
			col_BLOOD_TEST="BLOOD_TEST18";
			o_BLOOD_TEST="3_3_1";
		}else if(paperLabel.equals("005")){
			col_BLOOD_TEST="BLOOD_TEST24";
			o_BLOOD_TEST="3_3_1";
		}
		
		Map<String,Object> patientMap = new HashMap<String, Object>();
		patientMap.put("PATIENT_ID", r.getPatientID());
		for(ResultOption o:r.getOptions()){
			Map<String,Object> aMap = new HashMap<String, Object>();
			aMap.put("projectId",Securitys.getCurrentProject());
			aMap.put("patientId",r.getPatientID());
			aMap.put("uqsVersion",r.getUQSVersion());
			aMap.put("qnSetId",o.getQuestionSetId());
			aMap.put("questionId",o.getQuestionId());
			aMap.put("optionId",o.getResultId());
			aMap.put("answer",o.getResutlStr());
			aMap.put("createDate",endTime);
			aMap.put("lccCode",Securitys.getUser().getLccCode());
			answerQnDao.insertAnswer(aMap);
			//判断采血包是否被使用，如果使用的话，更新采血包的信息
			if("1".equals(o.getResultId()) && "3".equals(o.getQuestionId()) && "4".equals(o.getQuestionSetId())){
				if(!org.apache.commons.lang.StringUtils.isEmpty(o.getResutlStr())){
					pipMmsScmarchivesMapper.updateStateByBloodCode(Securitys.getUser().getCurrent_projectId(),o.getResutlStr(), "1");
				}
			}
			String oFullId=o.getQuestionSetId()+"_"+o.getQuestionId()+"_"+o.getResultId();
			if(paperLabel.equals("001")){
				if("2_3_1".equals(oFullId) && o.getResutlStr().trim().length()>0){//手机
					patientMap.put("MOBILE",o.getResutlStr());
				}else if("2_2_1".equals(oFullId) && o.getResutlStr().trim().length()>0){//固定电话
					patientMap.put("PHONE",o.getResutlStr());
				}else if("1_4_1".equals(oFullId) && o.getResutlStr().trim().length()>0){//身份证号
					patientMap.put("ID_NUMBER",o.getResutlStr());
				}else if("1_2_1".equals(oFullId)){//性别男
					patientMap.put("SEX",1);
				}else if("1_2_2".equals(oFullId)){//性别女
					patientMap.put("SEX",2);
				}else if("2_4_1".equals(oFullId)){//第一联系人
					patientMap.put("LINK_MAN1",o.getResutlStr());
				}else if("2_5_1".equals(oFullId)){//第一联系人与本人关系
					patientMap.put("LINK_MAN1_RELATION","父母");
				}else if("2_5_2".equals(oFullId)){//第一联系人与本人关系
					patientMap.put("LINK_MAN1_RELATION","配偶");
				}else if("2_5_3".equals(oFullId)){//第一联系人与本人关系
					patientMap.put("LINK_MAN1_RELATION","子女");
				}else if("2_5_4".equals(oFullId)){//第一联系人与本人关系
					patientMap.put("LINK_MAN1_RELATION","兄弟姐妹");
				}else if("2_5_5".equals(oFullId)){//第一联系人与本人关系
					patientMap.put("LINK_MAN1_RELATION",o.getResutlStr());
				}else if("2_6_1".equals(oFullId)){//第一联系人手机
					patientMap.put("LINK_MAN1_MOBILE",o.getResutlStr());
				}
			}
			
			//问卷只能答一次，不能修改,如果问卷能修改，此处需要改动
			if(o_BLOOD_TEST.indexOf(o.getQuestionSetId()+"_"+o.getQuestionId()+"_"+o.getResultId())>-1){
				Map<String,String> updateBloodMap = new HashMap<String, String>();
				updateBloodMap.put("col",col_BLOOD_TEST);
				updateBloodMap.put("value","1");
				updateBloodMap.put("patientId",r.getPatientID());
				answerQnDao.updatePatientBlood(updateBloodMap);
			}
		}
		
		if(paperLabel.equals("001")){
			answerQnDao.updatePatientInfo(patientMap);//更新性别、手机、电话、身份证
			
			Map<String,Map<String,String>> eventTemMap = getEventTemMap(paper);
			for(ResultOption o:r.getOptions()){
				String optionFullId = o.getQuestionSetId()+"_"+o.getQuestionId()+"_"+o.getResultId();
				if(eventTemMap.get(optionFullId)==null)continue;
				
				String c=eventTemMap.get(optionFullId).get("CONTRAST_CODE");
				Map<String,Object> insertEventMap = new HashMap<String, Object>();
				if(c==null){
					if(eventTemMap.get(optionFullId).get("EVENT_NAME")==null){
						insertEventMap.put("EVENT_NAME",getOptionName(paper, o.getQuestionSetId(),o.getQuestionId(),o.getResultId()));
					}else{
						insertEventMap.put("EVENT_NAME",eventTemMap.get(optionFullId).get("EVENT_NAME"));
					}
				}else if(c.trim().equals("1")){
					//其他选项不管选什么都生成事件
					if(o.getResutlStr().split("_").length!=2)continue;
					String eName=comboxDao.getICDNameById(o.getResutlStr().split("_")[1].trim());
					if(eName==null || eName.trim().length()==0)continue;
					insertEventMap.put("EVENT_NAME",eName);
				}else if(c.trim().equals("2")){
					if(!o.getResutlStr().trim().equals(eventTemMap.get(optionFullId).get("EVENT_NAME")))continue;
					insertEventMap.put("EVENT_NAME",o.getResutlStr().trim());
				}else if(c.trim().equals("3")){
					if(o.getResutlStr().trim().equals(""))continue;
					insertEventMap.put("EVENT_NAME",o.getResutlStr().trim());
				}
				insertEventMap.put("LCC_CODE",Securitys.getUser().getLccCode());
				insertEventMap.put("PATIENT_ID",r.getPatientID());
				insertEventMap.put("IN_HOS_DATE",getValuebyOptionId(r.getOptions(),eventTemMap.get(optionFullId).get("IN_HOS_DATE"),true));
				insertEventMap.put("OUT_HOS_DATE",getValuebyOptionId(r.getOptions(),eventTemMap.get(optionFullId).get("OUT_HOS_DATE"),true));
				insertEventMap.put("HOS_NAME",getValuebyOptionId(r.getOptions(),eventTemMap.get(optionFullId).get("IN_HOS_NAME"),false));
				insertEventMap.put("QUESTIONNAIRE_ID",paper.getPaperVersion().substring(0,7).replaceAll("\\.", ""));
				insertEventMap.put("PROJECT_ID",Securitys.getCurrentProject());
				insertEventMap.put("IS_DEATH",Integer.parseInt(eventTemMap.get(optionFullId).get("ISDEATH")));
				insertEventMap.put("CODE_LABEL","1".equals(eventTemMap.get(optionFullId).get("ISDEATH"))?0:1);
				insertEventMap.put("EVENT_TYPE",1);
				insertEventMap.put("UQS_NO",optionFullId);
				insertEventMap.put("CREATE_BY",Securitys.getUserId());
				
				uqsEventDao.insertEvent(insertEventMap);
			}
			
		}
		
		//TODO:暂时写死 只做001问卷,以后会有更新，加问卷时必须改
		if(request.getParameter("planDate")!=null){
			Map<String,Object> planDateMap = new HashMap<String, Object>();
			planDateMap.put("PROJECT_ID",Securitys.getCurrentProject());
			planDateMap.put("PATIENT_ID",r.getPatientID());
			
			if(paperLabel.equals("001")){
				planDateMap.put("FIRST_DATE",endTime);
				planDateMap.put("SIX_PLAN_DATE",sf1.parse(request.getParameter("planDate").trim()));
				planDateMap.put("NOW_REMARK",request.getParameter("planRemark"));
				
				Map<String,Object> pdMap = answerQnDao.getDatePatient(planDateMap);
				if(pdMap==null){
					answerQnDao.insertPlanDate(planDateMap);
				}else{
					answerQnDao.updatePlanDate(planDateMap);
					pdMap.put("ID",GenerateKey.getKey(GenerateKey.PREFIX_COMMON));
					pdMap.put("DATE_TYPE",1);
					answerQnDao.insertDataRemark(pdMap);
				}
			}else if(paperLabel.equals("013")){
				planDateMap.put("SIX_REAL_DATE",endTime);
				planDateMap.put("TWELVE_PLAN_DATE",sf1.parse(request.getParameter("planDate").trim()));
				answerQnDao.updatePlanDate(planDateMap);
			}
		}
		
		if(paperLabel.equals("001") || paperLabel.equals("013") ){
			Map<String,Object> updateLastDatePMap = new HashMap<String, Object>();
			updateLastDatePMap.put("PATIENT_ID",r.getPatientID());
			updateLastDatePMap.put("LAST_FOLLOWVIEW_DATE", endTime);
			answerQnDao.updateLastDate(updateLastDatePMap);
		}
	}
	
	private Object getValuebyOptionId(List<ResultOption> list, String optionFullId,boolean toDate){
		if(optionFullId==null || optionFullId.trim().length()==0)return null;
		SimpleDateFormat dateSf = new SimpleDateFormat("yyyy-MM-dd");
		for(ResultOption o : list){
			if((o.getQuestionSetId()+"_"+o.getQuestionId()+"_"+o.getResultId()).equals(optionFullId)){
				if(o.getResutlStr()==null && o.getResutlStr().trim().length()==0)return null;
				if(toDate){
					try{
						return dateSf.parse(o.getResutlStr().trim());
					}catch (Exception e) {
						return null;
					}
				}else{
					return o.getResutlStr().trim();
				}
				
			}
		}
		return null;
	}
	
	private Object getOptionName(Paper paper, String questionSetId,
			String questionId, String resultId) {
		for(QuestionGroup qg : paper.getQGroup()){
			if(qg.getQs()!=null && qg.getId().equals(questionSetId)){
				for(QuestionC q:qg.getQs()){
					if(q.getOptions()!=null && q.getId().equals(questionId)){
						for(QuestionOption o:q.getOptions()){
							if(o.getId().equals(resultId)){
								return o.getDisplayname();
							}
						}
					}
				}
			}
		}
		return null;
	}

	private void saveFile(Result r,String filePath)throws Exception{
		ResultQuestionnaireDocumentBean questionD = ResultQuestionnaireDocumentBean.Factory.newInstance();
		com.bdcor.pip.client.xml.model.result.ResultQuestionnaireDocumentBean.Questionnaire q3 = com.bdcor.pip.client.xml.model.result.ResultQuestionnaireDocumentBean.Questionnaire.Factory.newInstance();
		Doctype doctype = q3.addNewDoctype();
	
		doctype.setProjectID(r.getProjectID());
		doctype.setProjectName(r.getProjectName());
		doctype.setUQSID(r.getUQSID());
		doctype.setUQSName(r.getUQSName());
		doctype.setUQSVersion(r.getUQSVersion());
		doctype.setVersionCreateDate(r.getVersionCreateDate());
		doctype.setUQSBeginTime(r.getUQSBeginTime());
		doctype.setUQSEndDate(r.getUQSEndDate());
		doctype.setUQSTimeZone(r.getUQSTimeZone());
		doctype.setOperaterID(r.getOperaterID());
		doctype.setOperaterName(r.getOperaterName());
		doctype.setHospitalCode(r.getHospitalCode());
		doctype.setHospitalName(r.getHospitalName());
		doctype.setPatientID(r.getPatientID());
		doctype.setPersonID(r.getPersonID());
		doctype.setPatientName(r.getPatientName());
		doctype.setPatientCode(r.getPatientCode());
		doctype.setUQSIsHold(r.getUQSIsHold());
		doctype.setUQSRemark(r.getUQSRemark());
	
		List<com.bdcor.pip.client.xml.model.result.ResultResultDocumentBean.Result> resultsList = new ArrayList<com.bdcor.pip.client.xml.model.result.ResultResultDocumentBean.Result>();
		for(ResultOption ro1:r.getOptions()){
			com.bdcor.pip.client.xml.model.result.ResultResultDocumentBean.Result ro = q3.addNewResult();
			ro.setQuestionset(ro1.getQuestionSetId());
			ro.setQuestionId(ro1.getQuestionId());
			ro.setOptionid(ro1.getResultId());
			ro.setAnswer(ro1.getResutlStr());
			resultsList.add(ro);
		}
		q3.setResultArray(resultsList.toArray(new com.bdcor.pip.client.xml.model.result.ResultResultDocumentBean.Result[0]));
		questionD.setQuestionnaire(q3);
		
		XmlOptions options = new XmlOptions();
		options.setSavePrettyPrint();
		// 这里使用默认名空间
		options.setUseDefaultNamespace();

		//生成xml文件
		questionD.save(new File(filePath),options);
		//加密
	//	new XmlFileFilter().encryptFile(filePath);
	}

	@Override
	public List<Map<String, Object>> getPatientList(PatientFilter filter) {
		filter.setProjectId(Securitys.getCurrentProject());
		filter.setUserId(Securitys.getUserId());
		return answerQnDao.getPatientList(filter);
	}

	@Override
	public List<Map<String, Object>> exportPatientList(PatientFilter filter) {
		filter.setProjectId(Securitys.getCurrentProject());
		filter.setUserId(Securitys.getUserId());
		return answerQnDao.exportPatientList(filter);
	}
	
	private Map<String,Map<String,String>> getEventTemMap(Paper p){
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("questionnaireId", p.getPaperVersion().substring(0,7).replaceAll("\\.", ""));
		paramMap.put("isUse",1);
		
		List<Map<String,String>> temMapList = uqsEventDao.getEventTemMapList(paramMap);
		Map<String,Map<String,String>> returnMap = new HashMap<String,Map<String,String>>();
		if(temMapList != null && temMapList.size()>0){
			for(Map<String,String> map : temMapList){
				returnMap.put(map.get("OPTIONFULLID"),map);
			}
		}
		return returnMap;
	}

	@Override
	public Map<String, String> getAnswerLogMap_MIN(String uqsCode,String patientId,String logId) {
		Map<String,String> map = new HashMap<String, String>();
		map.put("projectId", Securitys.getCurrentProject());
		map.put("uqsCode", uqsCode);
		map.put("patientId", patientId);
		map.put("logId", logId);
		return answerQnDao.getAnswerLogMap_MIN(map);
	}

	@Override
	@Transactional(
			propagation = Propagation.REQUIRED ,
			isolation = Isolation.DEFAULT,
			rollbackFor = Exception.class
		)
	public void saveQn_MIN(HttpServletRequest request, Paper paper, String logId)throws Exception {
		if(logId==null||logId.trim().length()==0){
			logId=null;
		}else{
			logId=logId.trim();
		}
		Result r = new Result(request,paper);
		//保存问卷文件
		String filePath = CustomizedPropertyPlaceholderConfigurer.getContextProperty("uqs_result_path")+"/"
						+new SimpleDateFormat("yyyyMMdd").format(new Date())+"/";
		if(!new File(filePath).exists()){
			new File(filePath).mkdirs();
		}
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date endTime = new Date();
		filePath += r.getPatientID()+"_"+r.getUQSVersion()+new Date().getTime()+".xml";
		Map<String,Object> AnswerQnLog = new HashMap<String, Object>();
		AnswerQnLog.put("projectId",Securitys.getCurrentProject());
		AnswerQnLog.put("lccCode",Securitys.getUser().getLccCode());
		AnswerQnLog.put("patientId",r.getPatientID());
		AnswerQnLog.put("uqsVersion",r.getUQSVersion());
		AnswerQnLog.put("startTime",sf.parse(r.getUQSBeginTime()));
		AnswerQnLog.put("endTime",endTime);
		AnswerQnLog.put("createBy",r.getOperaterID());
		AnswerQnLog.put("uqsFile", filePath);
		String uqsTypeName_label = request.getParameter("uqsTypeName");
		if(uqsTypeName_label==null){
			AnswerQnLog.put("uqsTypeName", null);
		}else if(uqsTypeName_label.trim().equals("1")){
			AnswerQnLog.put("uqsTypeName","首次随访");
		}else if(uqsTypeName_label.trim().equals("事件")){
			AnswerQnLog.put("uqsTypeName","事件");
		}else if(uqsTypeName_label.trim().equals("6")||uqsTypeName_label.trim().equals("12")||
				uqsTypeName_label.trim().equals("18")||uqsTypeName_label.trim().equals("24")){
			AnswerQnLog.put("uqsTypeName", request.getParameter("uqsTypeName").trim()+"月随访");
		}
		if(logId==null){
			answerQnDao.insertAnswerQnLog_MIN(AnswerQnLog);
		}else{
			AnswerQnLog.put("logId", logId);
			answerQnDao.updateAnswerQnLog_MIN(AnswerQnLog);
			
			Map<String,String> AnswerToDropMap = new HashMap<String,String>();
			AnswerToDropMap.put("projectId",Securitys.getCurrentProject());
			AnswerToDropMap.put("patientId",r.getPatientID());
			AnswerToDropMap.put("uqsVersion",r.getUQSVersion());
			AnswerToDropMap.put("logId",logId);
			answerQnDao.answerToDropMap(AnswerToDropMap);
			answerQnDao.clearUqsAnswer(AnswerToDropMap);
		}
				
		saveFile(r,filePath);
		
		for(ResultOption o:r.getOptions()){
			Map<String,Object> aMap = new HashMap<String, Object>();
			aMap.put("projectId",Securitys.getCurrentProject());
			aMap.put("patientId",r.getPatientID());
			aMap.put("uqsVersion",r.getUQSVersion());
			aMap.put("qnSetId",o.getQuestionSetId());
			aMap.put("questionId",o.getQuestionId());
			aMap.put("optionId",o.getResultId());
			aMap.put("answer",o.getResutlStr());
			aMap.put("logId",AnswerQnLog.get("logId"));
			aMap.put("createDate",endTime);
			aMap.put("lccCode",Securitys.getUser().getLccCode());
			answerQnDao.insertAnswer_MIN(aMap);
		}
		Map<String,Map<String,String>> eventTemMap = getEventTemMap(paper);
		if(logId == null){
			for(ResultOption o:r.getOptions()){
				String optionFullId = o.getQuestionSetId()+"_"+o.getQuestionId()+"_"+o.getResultId();
				if(eventTemMap.get(optionFullId)==null)continue;
				
				String c=eventTemMap.get(optionFullId).get("CONTRAST_CODE");
				Map<String,Object> insertEventMap = new HashMap<String, Object>();
				if(c==null){
					if(eventTemMap.get(optionFullId).get("EVENT_NAME")==null){
						insertEventMap.put("EVENT_NAME",getOptionName(paper, o.getQuestionSetId(),o.getQuestionId(),o.getResultId()));
					}else{
						insertEventMap.put("EVENT_NAME",eventTemMap.get(optionFullId).get("EVENT_NAME"));
					}
				}else if(c.trim().equals("1")){
					//其他选项不管选什么都生成事件
					if(o.getResutlStr().split("_").length!=2)continue;
					String eName=comboxDao.getICDNameById(o.getResutlStr().split("_")[1].trim());
					if(eName==null || eName.trim().length()==0)continue;
					insertEventMap.put("EVENT_NAME",eName);
				}else if(c.trim().equals("2")){
					if(!o.getResutlStr().trim().equals(eventTemMap.get(optionFullId).get("EVENT_NAME")))continue;
					insertEventMap.put("EVENT_NAME",o.getResutlStr().trim());
				}else if(c.trim().equals("3")){
					if(o.getResutlStr().trim().equals(""))continue;
					insertEventMap.put("EVENT_NAME",o.getResutlStr().trim());
				}
				insertEventMap.put("LCC_CODE",Securitys.getUser().getLccCode());
				insertEventMap.put("PATIENT_ID",r.getPatientID());
				insertEventMap.put("IN_HOS_DATE",getValuebyOptionId(r.getOptions(),eventTemMap.get(optionFullId).get("IN_HOS_DATE"),true));
				insertEventMap.put("OUT_HOS_DATE",getValuebyOptionId(r.getOptions(),eventTemMap.get(optionFullId).get("OUT_HOS_DATE"),true));
				insertEventMap.put("HOS_NAME",getValuebyOptionId(r.getOptions(),eventTemMap.get(optionFullId).get("IN_HOS_NAME"),false));
				insertEventMap.put("QUESTIONNAIRE_ID",paper.getPaperVersion().substring(0,7).replaceAll("\\.", ""));
				insertEventMap.put("PROJECT_ID",Securitys.getCurrentProject());
				insertEventMap.put("IS_DEATH",Integer.parseInt(eventTemMap.get(optionFullId).get("ISDEATH")));
				insertEventMap.put("CODE_LABEL","1".equals(eventTemMap.get(optionFullId).get("ISDEATH"))?0:1);
				insertEventMap.put("EVENT_TYPE",1);
				insertEventMap.put("logId",AnswerQnLog.get("logId"));
				insertEventMap.put("UQS_NO",optionFullId);
				insertEventMap.put("CREATE_BY",Securitys.getUserId());
				uqsEventDao.insertEvent(insertEventMap);
			}
		}else{
			for(ResultOption o:r.getOptions()){
				String optionFullId = o.getQuestionSetId()+"_"+o.getQuestionId()+"_"+o.getResultId();
				if(eventTemMap.get(optionFullId)!=null){
					Map<String,Object> updateEventMap = new HashMap<String, Object>();
					updateEventMap.put("logId",logId);
					updateEventMap.put("UQS_NO", optionFullId);
					updateEventMap.put("IN_HOS_DATE",getValuebyOptionId(r.getOptions(),eventTemMap.get(optionFullId).get("IN_HOS_DATE"),true));
					updateEventMap.put("OUT_HOS_DATE",getValuebyOptionId(r.getOptions(),eventTemMap.get(optionFullId).get("OUT_HOS_DATE"),true));
					updateEventMap.put("HOS_NAME",getValuebyOptionId(r.getOptions(),eventTemMap.get(optionFullId).get("IN_HOS_NAME"),false));
					uqsEventDao.updateEvent(updateEventMap);
					uqsEventDao.updateUsrFile(updateEventMap);
				}
			}
		}
		
		String paperLabel = paper.getPaperVersion().substring(4,7);
		if(paperLabel.equals("006")){
			//更新死亡状态
			uqsEventDao.updatePDead(r.getPatientID());
		}
			
	}

	@Override
	public Map<String, Object> checkBloodNo(String bloodNo,String lccCode) {
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("projectId", Securitys.getCurrentProject());
		if(lccCode==null){
			paramMap.put("lccCode", Securitys.getUser().getLccCode());
		}else{
			paramMap.put("lccCode", lccCode);
		}
		paramMap.put("bloodNo", bloodNo);
		return answerQnDao.checkBloodNo(paramMap);
	}

	@Override
	public List<Map<String, Object>> listBlood(ScmSearchFilter filter)
	{
		return answerQnDao.listBlood(filter);
	}
	@Override
	public List<Map<String, Object>> listBlood(ScmSearchFilterNoBase filter)
	{
		return answerQnDao.listBloodNoPage(filter);
	}

	@Override
	public boolean isExistUqs(Map<String, Object> param)
	{
		int count =answerQnDao.selectUqsLog(param);
		if(count>0) return true;
		return false;
	}
}
