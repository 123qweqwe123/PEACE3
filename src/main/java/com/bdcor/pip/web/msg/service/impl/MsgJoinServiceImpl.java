/**
 * JAVACC DEMO 1.0
 * @copy right zbxsoft company All rights reserved. 
 * @Package com.bdcor.pip.web.msg.service.impl 
 */

package com.bdcor.pip.web.msg.service.impl;

import com.bdcor.pip.client.vo.paper.*;
import com.bdcor.pip.client.xml.model.TempletQuestionDocumentBean.Question;
import com.bdcor.pip.client.xml.model.TempletQuestionSetDocumentBean.QuestionSet;
import com.bdcor.pip.client.xml.model.TempletQuestionnaireDocumentBean;
import com.bdcor.pip.client.xml.model.TempletQuestionnaireDocumentBean.Questionnaire;
import com.bdcor.pip.client.xml.model.result.ResultDoctypeDocumentBean.Doctype;
import com.bdcor.pip.client.xml.model.result.ResultQuestionnaireDocumentBean;
import com.bdcor.pip.core.utils.CustomizedPropertyPlaceholderConfigurer;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.common.dao.ComboxMapper;
import com.bdcor.pip.web.data.dao.PipCommPatientMapper;
import com.bdcor.pip.web.data.domain.PipCommPatient;
import com.bdcor.pip.web.data.domain.PipCommPatientKey;
import com.bdcor.pip.web.material.supp.dao.PipMmsScmarchivesMapper;
import com.bdcor.pip.web.msg.dao.MsgSendDao;
import com.bdcor.pip.web.msg.dao.PatientGroupDao;
import com.bdcor.pip.web.msg.domain.PatientGroupVo;
import com.bdcor.pip.web.msg.domain.PipMsgSend;
import com.bdcor.pip.web.msg.service.MsgJoinService;
import com.bdcor.pip.web.msg.service.MsgSendRuleService;
import com.bdcor.pip.web.qn.dao.AnswerQnDao;
import com.bdcor.pip.web.qn.dao.UqsEventDao;
import com.bdcore.webservice.client.MsgClient;
import com.bdcore.webservice.client.SendResultCallback;
import com.bdcore.webservice.client.bean.MsgOfSend;
import org.apache.commons.lang3.StringUtils;
import org.apache.xmlbeans.XmlOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**  
 * description:  
 * @author yangfeng 创建时间：2016年6月13日         
 */
@Service
@Transactional
public class MsgJoinServiceImpl implements MsgJoinService 
{
	@Autowired
	private AnswerQnDao answerQnDao;
	@Autowired
	private PipCommPatientMapper pipCommPatientMapper;
	@Autowired
	private MsgSendRuleService msgSendRuleService;
	@Autowired
	private PatientGroupDao patientGroupDao;
	@Autowired
	private UqsEventDao uqsEventDao;
	@Autowired
	private ComboxMapper comboxDao;
	@Autowired
	private PipMmsScmarchivesMapper pipMmsScmarchivesMapper;
	@Autowired
	private MobileClient mobileClient;
	@Autowired
	private MsgSendDao msgSendDao;
	
	final Logger logger= LoggerFactory.getLogger(MsgJoinServiceImpl.class);
	
	private static final SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private static final SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 
	 * TODO  实现类 
	 * @see com.bdcor.pip.web.msg.service.MsgJoinService#saveQn(javax.servlet.http.HttpServletRequest, com.bdcor.pip.client.vo.paper.Paper)
	 */
	@Transactional(
			propagation = Propagation.REQUIRED ,
			isolation = Isolation.DEFAULT,
			rollbackFor = Exception.class
		)
	public void saveQn(HttpServletRequest request,Paper paper) throws Exception{
		Result r = new Result(request,paper);
		//保存问卷文件
		String fileDirPath = CustomizedPropertyPlaceholderConfigurer.getContextProperty("uqs_result_path")+"/"
						+new SimpleDateFormat("yyyyMMdd").format(new Date())+"/";
		if(!new File(fileDirPath).exists()){
			new File(fileDirPath).mkdirs();
		}
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date endTime = new Date();
		String filePath = fileDirPath + r.getPatientID()+"_"+r.getUQSVersion()+new Date().getTime()+".xml";
		Map<String,Object> answerQnLog = new HashMap<String, Object>();
		answerQnLog.put("projectId",Securitys.getCurrentProject());
		answerQnLog.put("lccCode",request.getParameter("lccCode"));
		answerQnLog.put("patientId",r.getPatientID());
		answerQnLog.put("uqsVersion",r.getUQSVersion());
		answerQnLog.put("startTime",sf.parse(r.getUQSBeginTime()));
		answerQnLog.put("endTime",endTime);
		answerQnLog.put("createBy",r.getOperaterID());
		answerQnLog.put("uqsFile", filePath);
		answerQnLog.put("state", request.getParameter("tempSave")==null?1:2);
				
		if(r.getOptions() == null || r.getOptions().size()==0 || request.getParameter("tempSave")!=null){
			answerQnDao.insertAnswerQnLog(answerQnLog);
			saveFile(r,filePath);
			return;
		}else{
			String paperLabel = r.getUQSVersion().substring(4,7);
			List<Map<String,Object>> answerList = new ArrayList<Map<String,Object>>();
			
			PipCommPatient p = new PipCommPatient();
			p.setProjectId(Securitys.getCurrentProject());
			p.setPatientId(r.getPatientID());
			if( !"014".equals(paperLabel)){
                p.setIsJoinMsg("1");
            }
			Boolean urineTest=null,monthSmoke=null;//默认尿检阴性，近一个月未抽烟
			
			Map<String,Object> pInfoMap = new HashMap<String,Object>();
			pInfoMap.put("projectId", Securitys.getCurrentProject());
			pInfoMap.put("patientId", r.getPatientID());
			int is_diabetes =2;
			for(ResultOption o:r.getOptions()){
				Map<String,Object> aMap = new HashMap<String, Object>();
				aMap.put("projectId",Securitys.getCurrentProject());
				aMap.put("patientId",r.getPatientID());
				aMap.put("qnSetId",o.getQuestionSetId());
				aMap.put("questionId",o.getQuestionId());
				aMap.put("optionId",o.getResultId());
				aMap.put("answer",o.getResutlStr());
				aMap.put("createDate",endTime);
				aMap.put("lccCode",request.getParameter("lccCode"));
				answerList.add(aMap);

				String oFullId=o.getQuestionSetId()+"_"+o.getQuestionId()+"_"+o.getResultId();
				if(paperLabel.equals("002")){
					if("4_1_1".equals(oFullId)){
						Map<String,String> updateBloodMap = new HashMap<String, String>();
						updateBloodMap.put("col","BLOOD_TEST6");
						updateBloodMap.put("value","1");
						updateBloodMap.put("patientId",r.getPatientID());
						answerQnDao.updatePatientBlood(updateBloodMap);
					}
				}else if(paperLabel.equals("015")){
                    if("4_1_1".equals(oFullId)){
                        Map<String,String> updateBloodMap = new HashMap<String, String>();
                        updateBloodMap.put("col","BLOOD_TEST12");
                        updateBloodMap.put("value","1");
                        updateBloodMap.put("patientId",r.getPatientID());
                        answerQnDao.updatePatientBlood(updateBloodMap);
                    }
                } else if(paperLabel.equals("010")){
					if("2_3_1".equals(oFullId))p.setMobile(o.getResutlStr());
					else if(oFullId.startsWith("5_10_")){
						p.setIsXjgsbs(o.getResultId());
						pInfoMap.put("medicalHis_MI", o.getResultId());
					}
				}else if(paperLabel.equals("011")){
					if(oFullId.startsWith("1_5_"))urineTest=false;
					if(oFullId.startsWith("10_1_"))monthSmoke=false;
					
					if("1_5_2".equals(oFullId)){
						urineTest=true;//尿检阳性
					}else if("10_1_4".equals(oFullId)){
						monthSmoke=true;//近1个月在吸烟
					}else if(oFullId.startsWith("8_14_")){
						pInfoMap.put("medicalInsurance", o.getResultId());
					}else if("11_4_1".equals(oFullId)){
						p.setMobile(o.getResutlStr());
					}else if("1_1_1".equals(oFullId)){
						Map<String,String> updateBloodMap = new HashMap<String, String>();
						updateBloodMap.put("col","BLOOD_TEST6");
						updateBloodMap.put("value","1");
						updateBloodMap.put("patientId",r.getPatientID());
						answerQnDao.updatePatientBlood(updateBloodMap);
					}
					
					if("1".equals(o.getResultId()) && "3".equals(o.getQuestionId()) && "1".equals(o.getQuestionSetId())){
						if(!org.apache.commons.lang.StringUtils.isEmpty(o.getResutlStr())){
							pipMmsScmarchivesMapper.updateStateByBloodCode(Securitys.getUser().getCurrent_projectId(),o.getResutlStr(), "1");
						}
					}
				}else if(paperLabel.equals("012")){
					if("1_13_1".equals(oFullId)){
						is_diabetes = 1;
					}
				}else if( "014".equals(paperLabel) ){ //末次随访
					if( "4_1_1".equals(oFullId) ){
						Map<String,String> updateBloodMap = new HashMap<String, String>();
						updateBloodMap.put("col","BLOOD_TEST12");
						updateBloodMap.put("value","1");
						updateBloodMap.put("patientId",r.getPatientID());
						answerQnDao.updatePatientBlood(updateBloodMap);
					}

					// 更新采血包状态
					if("1".equals(o.getResultId()) && "4".equals(o.getQuestionId()) && "4".equals(o.getQuestionSetId())){
						if(!org.apache.commons.lang.StringUtils.isEmpty(o.getResutlStr())){
							pipMmsScmarchivesMapper.updateStateByBloodCode(Securitys.getUser().getCurrent_projectId(),o.getResutlStr(), "1");
						}
					}
				}
			}
			
			if(paperLabel.equals("002")){
				p.setIsJoinMsg("2");
				p.setLastFollowviewDate(sf1.format(endTime));
				pipCommPatientMapper.updateMsgPatient(p);
				
				pInfoMap.put("sixRealDate", endTime);
				pInfoMap.put("twelvePlanDate",sf2.parse(request.getParameter("planDate").trim()));
				pipCommPatientMapper.updatePatientDate(pInfoMap);
				pipCommPatientMapper.updatePatientInfo(pInfoMap);
			}else if(paperLabel.equals("015")){
                p.setIsJoinMsg("2");
                p.setLastFollowviewDate(sf1.format(endTime));
                pipCommPatientMapper.updateMsgPatient(p);
            }else if(paperLabel.equals("010")){
				
				pipCommPatientMapper.updateMsgPatient(p);
				pipCommPatientMapper.updatePatientInfo(pInfoMap);
				
			}else if(paperLabel.equals("011")){
				if(urineTest!=null ||  monthSmoke!=null){
					p.setIsSmoking("2");
				}
				if(urineTest!=null&&urineTest || monthSmoke!=null&&monthSmoke){
					p.setIsSmoking("1");
				}
				
				PatientGroupVo patientGroupVo = pipCommPatientMapper.getPatientGroupVo(r.getPatientID());
				if(patientGroupVo!=null && patientGroupVo.getIs_diabetes()==1){
					patientGroupVo.setMedicalInsurance(Integer.parseInt(pInfoMap.get("medicalInsurance").toString()));
					patientGroup(patientGroupVo);
					p.setIsSubscribe("1");
                    PipCommPatientKey key= new PipCommPatientKey();
                    key.setProjectId(Securitys.getUser().getCurrent_projectId());
                    key.setPatientId(r.getPatientID());
                    PipCommPatient patient = pipCommPatientMapper.selectByPrimaryKey(key);
					msgSendRuleService.addSendMsg(patient,patientGroupVo.getGroupStr()+"_M006");
				}


				p.setLastFollowviewDate(sf1.format(endTime));
				pInfoMap.put("sixRealDate", endTime);
				pInfoMap.put("twelvePlanDate", sf2.parse(request.getParameter("planDate").trim()));
				pipCommPatientMapper.updatePatientDate(pInfoMap);
				pipCommPatientMapper.updateMsgPatient(p);
				pipCommPatientMapper.updatePatientInfo(pInfoMap);
				
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
			}else if(paperLabel.equals("012")){
				PatientGroupVo patientGroupVo = pipCommPatientMapper.getPatientGroupVo(r.getPatientID());
				patientGroupVo.setIs_diabetes(is_diabetes);
				patientGroup(patientGroupVo);
				if(request.getParameter("systemDiabetes")!=null){
					p.setIsSystemDiabetes(1);
				}else{
					p.setIsSystemDiabetes(2);
				}
				p.setIsSubscribe("1");
				pipCommPatientMapper.updateMsgPatient(p);
				
				PipCommPatientKey key= new PipCommPatientKey();
				key.setProjectId(Securitys.getUser().getCurrent_projectId());
				key.setPatientId(r.getPatientID());
				PipCommPatient patient = pipCommPatientMapper.selectByPrimaryKey(key);
				msgSendRuleService.addSendMsg(patient,patientGroupVo.getGroupStr()+"_M006");

			}else if( paperLabel.equals("014") ) {
				// 末次随访事件处理  按要求直接照扒011问卷的处理方式
				Map<String, Map<String, String>> eventTemMap = getEventTemMap(paper);
				for (ResultOption o : r.getOptions()) {
					String optionFullId = o.getQuestionSetId() + "_" + o.getQuestionId() + "_" + o.getResultId();
					if (eventTemMap.get(optionFullId) == null) continue;

					String c = eventTemMap.get(optionFullId).get("CONTRAST_CODE");
					Map<String, Object> insertEventMap = new HashMap<String, Object>();
					if (c == null) {
						if (eventTemMap.get(optionFullId).get("EVENT_NAME") == null) {
							insertEventMap.put("EVENT_NAME", getOptionName(paper, o.getQuestionSetId(), o.getQuestionId(), o.getResultId()));
						} else {
							insertEventMap.put("EVENT_NAME", eventTemMap.get(optionFullId).get("EVENT_NAME"));
						}
					} else if (c.trim().equals("1")) {
						//其他选项不管选什么都生成事件
						if (o.getResutlStr().split("_").length != 2) continue;
						String eName = comboxDao.getICDNameById(o.getResutlStr().split("_")[1].trim());
						if (eName == null || eName.trim().length() == 0) continue;
						insertEventMap.put("EVENT_NAME", eName);
					} else if (c.trim().equals("2")) {
						if (!o.getResutlStr().trim().equals(eventTemMap.get(optionFullId).get("EVENT_NAME"))) continue;
						insertEventMap.put("EVENT_NAME", o.getResutlStr().trim());
					} else if (c.trim().equals("3")) {
						if (o.getResutlStr().trim().equals("")) continue;
						insertEventMap.put("EVENT_NAME", o.getResutlStr().trim());
					}
					insertEventMap.put("LCC_CODE", Securitys.getUser().getLccCode());
					insertEventMap.put("PATIENT_ID", r.getPatientID());
					insertEventMap.put("IN_HOS_DATE", getValuebyOptionId(r.getOptions(), eventTemMap.get(optionFullId).get("IN_HOS_DATE"), true));
					insertEventMap.put("OUT_HOS_DATE", getValuebyOptionId(r.getOptions(), eventTemMap.get(optionFullId).get("OUT_HOS_DATE"), true));
					insertEventMap.put("HOS_NAME", getValuebyOptionId(r.getOptions(), eventTemMap.get(optionFullId).get("IN_HOS_NAME"), false));
					insertEventMap.put("QUESTIONNAIRE_ID", paper.getPaperVersion().substring(0, 7).replaceAll("\\.", ""));
					insertEventMap.put("PROJECT_ID", Securitys.getCurrentProject());
					insertEventMap.put("IS_DEATH", Integer.parseInt(eventTemMap.get(optionFullId).get("ISDEATH")));
					insertEventMap.put("CODE_LABEL", "1".equals(eventTemMap.get(optionFullId).get("ISDEATH")) ? 0 : 1);
					insertEventMap.put("EVENT_TYPE", 1);
					insertEventMap.put("UQS_NO", optionFullId);
					insertEventMap.put("CREATE_BY", Securitys.getUserId());
					uqsEventDao.insertEvent(insertEventMap);
				}

				// 末次随访结束即停止发送短信  更改参与短信字段  同时更新随访时间(last_followview_date)
				p.setIsSubscribe("2");
				p.setLastFollowviewDate(sf.format(endTime));
				pipCommPatientMapper.updateMsgPatient(p);
			}
			answerQnDao.insertAnswerQnLog(answerQnLog);

			// 完成末次随访时（014）发送一条结束感谢短信
			if( "014".equals(paperLabel) && request.getParameter("tempSave")==null){
				String mobile = request.getParameter("OPTION2_3_1");
				sendWelMsg(r.getPatientID(), mobile, "M010");
			}

			for(Map<String,Object> m : answerList){
				m.put("uqsVersion", r.getUQSVersion());
				answerQnDao.insertAnswer(m);
			}
			
			saveFile(r,filePath);
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

	public int getOldYear(Date date){
		Date now = new Date();
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(now);
    	int a = calendar.get(Calendar.YEAR);
    	calendar.setTime(date);
    	int b = calendar.get(Calendar.YEAR);
    	return a-b;
	}
	/**
	 * 
	 * description: 在本地保存一份记录文档   
	 * @author yangfeng  
	 * @param r
	 * @param filePath
	 * @throws Exception   
	 * @update 2016年6月13日
	 */
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
	public Map<String, String> getAnswerLogMap(String uqsCode,String patientId) {
		Map<String,String> map = new HashMap<String, String>();
		map.put("projectId", Securitys.getCurrentProject());
		map.put("uqsCode", uqsCode);
		map.put("patientId", patientId);
		return answerQnDao.getAnswerLogMap(map);
	}
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
	
	@PostConstruct
	public void initPatientGroupVoOldsum(){
		if(PatientGroupVo.oldsum_t1_g1_age1!=null)return;
		List<Map<String,Object>> oldSumMapList = patientGroupDao.getOldSumMapList(1);
		for(Map<String,Object> m : oldSumMapList){
			if("1".equals(m.get("group").toString())){
				PatientGroupVo.oldsum_t1_g1_sex1 = ((BigDecimal) m.get("sex1")).doubleValue();
				PatientGroupVo.oldsum_t1_g1_sex2 = ((BigDecimal) m.get("sex2")).doubleValue();
				
				PatientGroupVo.oldsum_t1_g1_age1 = ((BigDecimal) m.get("age1")).doubleValue();
				PatientGroupVo.oldsum_t1_g1_age2 = ((BigDecimal) m.get("age2")).doubleValue();
				PatientGroupVo.oldsum_t1_g1_age3 = ((BigDecimal) m.get("age3")).doubleValue();
				
				PatientGroupVo.oldsum_t1_g1_mi1 = ((BigDecimal) m.get("mi1")).doubleValue();
				PatientGroupVo.oldsum_t1_g1_mi2 = ((BigDecimal) m.get("mi2")).doubleValue();
				
				PatientGroupVo.oldsum_t1_g1_edu1 = ((BigDecimal) m.get("edu1")).doubleValue();
				PatientGroupVo.oldsum_t1_g1_edu2 = ((BigDecimal) m.get("edu2")).doubleValue();
				PatientGroupVo.oldsum_t1_g1_edu3 = ((BigDecimal) m.get("edu3")).doubleValue();
				
				PatientGroupVo.oldsum_t1_g1_medInsure1 = ((BigDecimal) m.get("medInsure1")).doubleValue();
				PatientGroupVo.oldsum_t1_g1_medInsure2 = ((BigDecimal) m.get("medInsure2")).doubleValue();
				PatientGroupVo.oldsum_t1_g1_medInsure3 = ((BigDecimal) m.get("medInsure3")).doubleValue();
			}else{
				PatientGroupVo.oldsum_t1_g2_sex1 = ((BigDecimal) m.get("sex1")).doubleValue();
				PatientGroupVo.oldsum_t1_g2_sex2 = ((BigDecimal) m.get("sex2")).doubleValue();
				
				PatientGroupVo.oldsum_t1_g2_age1 = ((BigDecimal) m.get("age1")).doubleValue();
				PatientGroupVo.oldsum_t1_g2_age2 = ((BigDecimal) m.get("age2")).doubleValue();
				PatientGroupVo.oldsum_t1_g2_age3 = ((BigDecimal) m.get("age3")).doubleValue();
				
				PatientGroupVo.oldsum_t1_g2_mi1 = ((BigDecimal) m.get("mi1")).doubleValue();
				PatientGroupVo.oldsum_t1_g2_mi2 = ((BigDecimal) m.get("mi2")).doubleValue();
				
				PatientGroupVo.oldsum_t1_g2_edu1 = ((BigDecimal) m.get("edu1")).doubleValue();
				PatientGroupVo.oldsum_t1_g2_edu2 = ((BigDecimal) m.get("edu2")).doubleValue();
				PatientGroupVo.oldsum_t1_g2_edu3 = ((BigDecimal) m.get("edu3")).doubleValue();
				
				PatientGroupVo.oldsum_t1_g2_medInsure1 = ((BigDecimal) m.get("medInsure1")).doubleValue();
				PatientGroupVo.oldsum_t1_g2_medInsure2 = ((BigDecimal) m.get("medInsure2")).doubleValue();
				PatientGroupVo.oldsum_t1_g2_medInsure3 = ((BigDecimal) m.get("medInsure3")).doubleValue();
			}
		}
		
		
		oldSumMapList = patientGroupDao.getOldSumMapList(0);
		for(Map<String,Object> m : oldSumMapList){
			if("1".equals(m.get("group").toString())){
				PatientGroupVo.oldsum_t2_g1_sex1 = ((BigDecimal) m.get("sex1")).doubleValue();
				PatientGroupVo.oldsum_t2_g1_sex2 = ((BigDecimal) m.get("sex2")).doubleValue();
				
				PatientGroupVo.oldsum_t2_g1_age1 = ((BigDecimal) m.get("age1")).doubleValue();
				PatientGroupVo.oldsum_t2_g1_age2 = ((BigDecimal) m.get("age2")).doubleValue();
				PatientGroupVo.oldsum_t2_g1_age3 = ((BigDecimal) m.get("age3")).doubleValue();
				
				PatientGroupVo.oldsum_t2_g1_mi1 = ((BigDecimal) m.get("mi1")).doubleValue();
				PatientGroupVo.oldsum_t2_g1_mi2 = ((BigDecimal) m.get("mi2")).doubleValue();
				
				PatientGroupVo.oldsum_t2_g1_edu1 = ((BigDecimal) m.get("edu1")).doubleValue();
				PatientGroupVo.oldsum_t2_g1_edu2 = ((BigDecimal) m.get("edu2")).doubleValue();
				PatientGroupVo.oldsum_t2_g1_edu3 = ((BigDecimal) m.get("edu3")).doubleValue();
				
				PatientGroupVo.oldsum_t2_g1_medInsure1 = ((BigDecimal) m.get("medInsure1")).doubleValue();
				PatientGroupVo.oldsum_t2_g1_medInsure2 = ((BigDecimal) m.get("medInsure2")).doubleValue();
				PatientGroupVo.oldsum_t2_g1_medInsure3 = ((BigDecimal) m.get("medInsure3")).doubleValue();
			}else{
				PatientGroupVo.oldsum_t2_g2_sex1 = ((BigDecimal) m.get("sex1")).doubleValue();
				PatientGroupVo.oldsum_t2_g2_sex2 = ((BigDecimal) m.get("sex2")).doubleValue();
				
				PatientGroupVo.oldsum_t2_g2_age1 = ((BigDecimal) m.get("age1")).doubleValue();
				PatientGroupVo.oldsum_t2_g2_age2 = ((BigDecimal) m.get("age2")).doubleValue();
				PatientGroupVo.oldsum_t2_g2_age3 = ((BigDecimal) m.get("age3")).doubleValue();
				
				PatientGroupVo.oldsum_t2_g2_mi1 = ((BigDecimal) m.get("mi1")).doubleValue();
				PatientGroupVo.oldsum_t2_g2_mi2 = ((BigDecimal) m.get("mi2")).doubleValue();
				
				PatientGroupVo.oldsum_t2_g2_edu1 = ((BigDecimal) m.get("edu1")).doubleValue();
				PatientGroupVo.oldsum_t2_g2_edu2 = ((BigDecimal) m.get("edu2")).doubleValue();
				PatientGroupVo.oldsum_t2_g2_edu3 = ((BigDecimal) m.get("edu3")).doubleValue();
				
				PatientGroupVo.oldsum_t2_g2_medInsure1 = ((BigDecimal) m.get("medInsure1")).doubleValue();
				PatientGroupVo.oldsum_t2_g2_medInsure2 = ((BigDecimal) m.get("medInsure2")).doubleValue();
				PatientGroupVo.oldsum_t2_g2_medInsure3 = ((BigDecimal) m.get("medInsure3")).doubleValue();
			}
		}
		
	}

	@Transactional(
			propagation = Propagation.REQUIRED ,
			isolation = Isolation.DEFAULT,
			rollbackFor = Exception.class
		)
	public synchronized void patientGroup(PatientGroupVo gpVo)throws Exception{
		//校验参数
		if(gpVo==null || gpVo.getPatientId()==null || gpVo.getPatientId().trim().length()==0 
		  || gpVo.getSex()<1 || gpVo.getSex()>2 || gpVo.getAge_grade()<1 || gpVo.getAge_grade()>3
		  || gpVo.getMedicalHis_MI()<1 || gpVo.getMedicalHis_MI()>2
		  || gpVo.getEdu_level()<1 || gpVo.getEdu_level()>3
		  || gpVo.getMedicalInsurance()<1 || gpVo.getMedicalInsurance()>3){
			throw new Exception("PARAM ERROR");
		}
		gpVo.setPatientId(gpVo.getPatientId().trim());
		
		if(gpVo.getIs_diabetes()==1){
			gpVo.setGroup1_weight(1);
		}else{
			gpVo.setGroup1_weight(1);
		}
		
		Map<String,BigDecimal> groupMap = patientGroupDao.getGroupMap(gpVo);
		if(groupMap.get("GROUP1").doubleValue()<=groupMap.get("GROUP2").doubleValue()){
			gpVo.setBelong_group(1);
		}else{
			gpVo.setBelong_group(2);
		}
		patientGroupDao.insert(gpVo);
		if(gpVo!=null && gpVo.getIs_diabetes()==1){
			gpVo.setGroupStr("1"+gpVo.getBelong_group());
		}else{
			gpVo.setGroupStr("0"+gpVo.getBelong_group());
		}
		patientGroupDao.changeBelongGroup(gpVo);
	}
	
	@Override
	public boolean isExistUqs(Map<String, Object> param)
	{
		int count =answerQnDao.selectUqsLog(param);
		if(count>0) return true;
		return false;
	}
	@Override
	public String getCurrentVersion(String versionStart) {
		return answerQnDao.getCurrentVersion(versionStart);
	}
	@Override
	public List<Map<String, String>> getUqsAnswer(String patientId,String uqsLabel){
		Map<String,Object> pMap = new HashMap<String, Object>();
		pMap.put("projectId", Securitys.getCurrentProject());
		pMap.put("patientId", patientId);
		pMap.put("uqsLabel", uqsLabel);
		return answerQnDao.getUqsAnswer(pMap);
	}

	@Override
	public int sendWelMsg(String patientId, String mobile,String msgTypeCode) {
		// 结束类短信 不需要进行下面的判断
		if (!"M010".equals(msgTypeCode)) {
			//判断该号码是否发送过欢迎短信
			int c  = msgSendDao.getCountByMobileAndType(mobile,msgTypeCode);
			if(c>0){
				return 2;
			}
		}
		
		PipCommPatientKey key= new PipCommPatientKey();
		key.setProjectId(Securitys.getUser().getCurrent_projectId());
		key.setPatientId(patientId);
		
		PipCommPatient patient = pipCommPatientMapper.selectByPrimaryKey(key);
		patient.setMobile(mobile);
		PipMsgSend pipMsgSend = msgSendRuleService.addSendMsg(patient,msgTypeCode);
		
		MsgClient client = mobileClient.getClient();
		MsgOfSend msg = new MsgOfSend();
		msg.setLinkId(pipMsgSend.getId());
		msg.setTel(mobile);
		msg.setContent(pipMsgSend.getMsgName());
		
		final Map<String,String> msgMap = new HashMap<String, String>();
		msgMap.put("ID", pipMsgSend.getId());
		msgMap.put("MOBILE", mobile);
		
		client.send(msg, new SendResultCallback() {
			@Override
			public void callback(int result, String resultMsg, MsgOfSend msg) {
				if(result == 1){ // 调用接口成功
					logger.info("调用短信发送接口成功");
					msgMap.put("STATE","1");
					msgSendDao.updateStateById(msgMap);
				}
				else{
					logger.info("调用短信发送接口失败，调用返回值："+result);
					msgMap.put("STATE","2");
					msgSendDao.updateStateById(msgMap);
				}
			}
		});
		return 1;
		
	}

	@Override
	public int getSendCount(String patientId, String mobile, String msgTypeCode) {
		return msgSendDao.getCountByMobileAndType(mobile,msgTypeCode);
	}

	public Map<String,Object> checkGroupInfo(String pid){
		Map<String,Object> m = new HashMap<String,Object>();
		List<String> msgList = new ArrayList<String>();
		if(pid == null || "".equals(pid)){
			m.put("status",false);
			m.put("msg","随访者PATIENT_ID信息缺失无法保存，请联系中心系统管理员!");
			return m;
		}
		PatientGroupVo gpVo = pipCommPatientMapper.getPatientGroupVo(pid);
		//校验参数
		if(	gpVo==null
				|| gpVo.getPatientId()==null || gpVo.getPatientId().trim().length()==0)
		{
			m.put("status",false);
			m.put("msg","随访者信息缺失无法保存，请联系中心系统管理员!");
			return m;
		}
		if( gpVo.getSex()<1 || gpVo.getSex()>2 ){
			m.put("status",false);
			msgList.add("性别");
		}
		if( gpVo.getAge_grade()<1 || gpVo.getAge_grade()>3 ){
			m.put("status",false);
			msgList.add("年龄");
		}
		if( gpVo.getMedicalHis_MI()<1 || gpVo.getMedicalHis_MI()>2 ){
			m.put("status",false);
			msgList.add("是否心梗");
		}
		if( gpVo.getEdu_level()<1 || gpVo.getEdu_level()>3 ){
			m.put("status",false);
			msgList.add("教育水平");
		}
//		if( gpVo.getMedicalInsurance()<1 || gpVo.getMedicalInsurance()>3 ){
//			m.put("status",false);
//			msgList.add("医保类型");
//		}
		if(m.size() == 0 && m.get("status") == null){
			m.put("status",true);
			return m;
		}
		m.put("msg","受访者"+Arrays.toString(msgList.toArray()).replace("[","").replace("]","")+"缺失无法保存，请联系中心系统管理员!");
		return m;
	}
	
//	public static void main(String[] args) {
//		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//		MsgJoinServiceImpl s = context.getBean(MsgJoinServiceImpl.class);
//		s.testGroup();
//
////		int begin = 100000001;
////		for(int i=0;i<61;i++){
////			String st = "insert into PIP_COMM_PATIENT(PATIENT_ID,IS_DIABETES)values('"+(i+begin)+"',1);";
////			System.out.println(st);
////		}
//	}

	// 获取患者所属类别 方便判断页面跳转url
	public String getProcesstype(String patientId){
		String pid = StringUtils.isBlank(patientId) ? patientId = "1" : patientId;
		Map<String,Object> m = pipCommPatientMapper.getInfoByid(pid);

		if( m == null  ){
			return "";
		}else if( m.get("PROCESS_TYPES") == null ){
			return "";
		}else{
			return m.get("PROCESS_TYPES").toString();
		}
	}


	/**
	 * 获取病人详细信息
	 * @param patientId
	 * @return
	 */
	public Map<String,Object> getPatientInfo(String patientId){
		return pipCommPatientMapper.getInfoByid(patientId);
	}

}

