package com.bdcor.pip.web.data.service.impl;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.xmlbeans.XmlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bdcor.pip.client.ClientInfo;
import com.bdcor.pip.client.file.filter.XmlFileFilter;
import com.bdcor.pip.client.vo.paper.Paper;
import com.bdcor.pip.client.vo.paper.QuestionC;
import com.bdcor.pip.client.vo.paper.QuestionGroup;
import com.bdcor.pip.client.vo.paper.Result;
import com.bdcor.pip.client.xml.model.TempletQuestionnaireDocumentBean;
import com.bdcor.pip.client.xml.model.result.ResultQuestionnaireDocumentBean;
import com.bdcor.pip.core.utils.StringUtils;
import com.bdcor.pip.data.xml.vo.Attribute;
import com.bdcor.pip.data.xml.vo.Option;
import com.bdcor.pip.data.xml.vo.Question;
import com.bdcor.pip.data.xml.vo.QuestionSet;
import com.bdcor.pip.data.xml.vo.Questionnaire;
import com.bdcor.pip.data.xml.vo.Table;
import com.bdcor.pip.data.xml.vo.Tr;
import com.bdcor.pip.data.xml.vo.Th;
import com.bdcor.pip.web.data.dao.UqsAnswerMapper;
import com.bdcor.pip.web.data.dao.UqsAttributeMapper;
import com.bdcor.pip.web.data.dao.UqsOptionMapper;
import com.bdcor.pip.web.data.dao.UqsQuestionMapper;
import com.bdcor.pip.web.data.dao.UqsQuestionSetMapper;
import com.bdcor.pip.web.data.dao.UqsQuestionnaireMapper;
import com.bdcor.pip.web.data.domain.UqsAnswer;
import com.bdcor.pip.web.data.domain.UqsAnswerExample;
import com.bdcor.pip.web.data.domain.UqsAttribute;
import com.bdcor.pip.web.data.domain.UqsOption;
import com.bdcor.pip.web.data.domain.UqsQuestion;
import com.bdcor.pip.web.data.domain.UqsQuestionSet;
import com.bdcor.pip.web.data.domain.UqsQuestionnaire;
import com.bdcor.pip.web.data.service.UqsQuestionnaireService;

@Service
public class UqsQuestionnaireServiceImpl implements UqsQuestionnaireService {

	@Autowired
	private UqsAttributeMapper uqsAttributeDao;
	
	@Autowired
	private UqsOptionMapper uqsOptionDao;
	
	@Autowired
	private UqsQuestionMapper uqsQuestionDao;
	
	@Autowired
	private UqsQuestionSetMapper uqsQuestionSetDao;
	
	@Autowired
	private UqsQuestionnaireMapper uqsQuestionnaireDao;
	
	@Autowired
	private UqsAnswerMapper uqsAnswerMapper;
	
	@Override
	@Transactional(
			propagation = Propagation.REQUIRES_NEW,
			isolation = Isolation.DEFAULT,
			rollbackFor = Exception.class
		)
	public void save(Questionnaire qn) throws Exception{
		
		UqsQuestionnaire uqn = new UqsQuestionnaire();
		
		uqn.setCreateDate(new Date());
		uqn.setQuestionnaireId(qn.getDoctype().getUqsId());
		uqn.setDisplayName(qn.getTitle());
		uqn.setIsRemoved(new Short("1"));
		uqn.setOrderInProject(1L);
		uqn.setProjectId(qn.getDoctype().getProjectid());
		uqn.setQuestionnaireId(qn.getDoctype().getUqsId());
		uqn.setVersion(qn.getDoctype().getUqsVersion());
		
		this.uqsQuestionnaireDao.insert(uqn);
		
		if ( qn.getQuestionSetList() != null ){
			for ( QuestionSet qs : qn.getQuestionSetList() ){
				UqsQuestionSet uqs = new UqsQuestionSet();
				uqs.setDisplayName(qs.getDisplayname());
				uqs.setOrderInQuestionnaire(qs.getOrder());
				uqs.setProjectId(qn.getDoctype().getProjectid());
				uqs.setQuestionnaireId(qn.getDoctype().getUqsId());
				uqs.setQuestionsetId(qs.getId());
				uqs.setIsRemoved(new Short("0"));
				uqs.setVersion(uqn.getVersion());
				
				this.uqsQuestionSetDao.insert(uqs);
				
				if ( qs.getAttributeList() != null ){
					for ( Attribute a : qs.getAttributeList() ){
						UqsAttribute qa = new UqsAttribute();
						qa.setAttributeId(a.getId());
						qa.setOptionId("0");
						qa.setProjectId(qn.getDoctype().getProjectid());
						qa.setQuestionId("0");
						qa.setQuestionnaireId(qn.getDoctype().getUqsId());
						qa.setQuestionsetId(qs.getId());
						qa.setType(a.getType());
						qa.setValidateobjecttype(a.getValidateobjecttype());
						qa.setValue(a.getValue());
						qa.setValidatetype(a.getValidatetype());
						qa.setVersion(uqn.getVersion());
						try{
							this.uqsAttributeDao.insert(qa);
						}catch (Exception e) {
							System.out.println(qa);
							throw e;
						}
					}
				}
				
				if ( qs.getQuestionList() != null ){
					for ( Question q : qs.getQuestionList()){
						UqsQuestion usq = new UqsQuestion();
						
						usq.setDisplayName(q.getDisplayname());
						//usq.setHelpText(helpText);
						usq.setOrderInQuestionset(q.getOrder());
						usq.setProjectId(qn.getDoctype().getProjectid());
						usq.setQuestionId(q.getId());
						usq.setQuestionnaireId(qn.getDoctype().getUqsId());
						usq.setQuestionsetId(qs.getId());
						usq.setQuestiontypeId(q.getTypeId());
						usq.setQuestionCode(q.getCode());
						usq.setQuestionType(q.getType());
						
						usq.setIsRemoved(new Short("0"));
						
						usq.setVersion(uqn.getVersion());
						
						this.uqsQuestionDao.insert(usq);
				
						System.out.println("==============="+usq.getProjectId() + "\t" + usq.getQuestionnaireId() + "\t" + usq.getQuestionsetId() + "\t" + usq.getQuestionId());
						
						if ( q.getAttributeList() != null ){
							for ( Attribute a : q.getAttributeList() ){
								UqsAttribute qa = new UqsAttribute();
								qa.setAttributeId(a.getId());
								qa.setOptionId("0");
								qa.setProjectId(qn.getDoctype().getProjectid());
								qa.setQuestionId(q.getId());
								qa.setQuestionnaireId(qn.getDoctype().getUqsId());
								qa.setQuestionsetId(qs.getId());
								qa.setType(a.getType());
								qa.setValidateobjecttype(a.getValidateobjecttype());
								qa.setValue(a.getValue());
								qa.setValidatetype(a.getValidatetype());
								qa.setVersion(uqn.getVersion());
								try{
									this.uqsAttributeDao.insert(qa);
								}catch (Exception e) {
									System.out.println(qa);
									throw e;
								}
							}
						}
						
						if ( q.getTableList() != null ){
							for ( Table t : q.getTableList() ){
								if ( t.getTrList() != null ){
									for ( Tr tr : t.getTrList() ){
										if ( tr.getThList() != null ){
											for ( Th h : tr.getThList() ){
												UqsOption uo = new UqsOption();
												
												uo.setDatatypeId(q.getTypeId());
												if ( h.getDisplayName() == null )
													uo.setDisplayName(tr.getDisplayname());
												else
													uo.setDisplayName(tr.getDisplayname()+";"+h.getDisplayName());
												uo.setOptionId(tr.getRownum()+"-"+h.getId());

												uo.setProjectId(qn.getDoctype().getProjectid());
												uo.setQuestionId(q.getId());
												uo.setQuestionnaireId(qn.getDoctype().getUqsId());
												uo.setQuestionsetId(qs.getId());
												uo.setIsChooseable(new Short("1"));
												uo.setIsRemoved(new Short("0"));
												uo.setIsEvent(new Short("0"));
												uo.setRemark(h.getColstyle());
												uo.setDictValue(h.getCode());
												uo.setOptionType(h.getDatatype());
												uo.setOptionStyle(h.getDatastyle());
												uo.setVersion(uqn.getVersion());
												
												this.uqsOptionDao.insert(uo);
												
												if ( h.getAttributeList() != null ){
													for ( Attribute a : h.getAttributeList()){
														UqsAttribute ua = new UqsAttribute();
														
														ua.setAttributeId(a.getId());
														ua.setOptionId(uo.getOptionId());
														ua.setProjectId(qn.getDoctype().getProjectid());
														ua.setQuestionId(q.getId());
														ua.setQuestionnaireId(qn.getDoctype().getUqsId());
														ua.setQuestionsetId(qs.getId());
														ua.setValidateobjecttype(a.getValidateobjecttype());
														ua.setValue(a.getValue());
														ua.setValidatetype(a.getValidatetype());
														ua.setType(a.getType());
														ua.setVersion(uqn.getVersion());
														try{
															this.uqsAttributeDao.insert(ua);
														}catch (Exception e) {
															System.out.println(ua);
															throw e;
														}
													}
													
												}
											}
										}
									}
								}
							}
						}
						
						if ( q.getOptionList() != null ){
							for ( Option o : q.getOptionList() ){
								UqsOption uo = new UqsOption();
								
								uo.setDatatypeId(q.getTypeId());
								uo.setDisplayName(o.getDisplayName());
								uo.setOptionId(o.getId());

								uo.setProjectId(qn.getDoctype().getProjectid());
								uo.setQuestionId(q.getId());
								uo.setQuestionnaireId(qn.getDoctype().getUqsId());
								uo.setQuestionsetId(qs.getId());
								uo.setIsChooseable(new Short("1"));
								uo.setIsRemoved(new Short("0"));
								uo.setIsEvent(new Short("0"));
								uo.setDictValue(o.getCode());
								uo.setOptionType(o.getDatatype());
								uo.setOptionStyle(o.getDatastyle());
								uo.setVersion(uqn.getVersion());
								this.uqsOptionDao.insert(uo);
								
								if ( o.getAttributeList() != null ){
									for ( Attribute a : o.getAttributeList()){
										UqsAttribute ua = new UqsAttribute();
										
										ua.setAttributeId(a.getId());
										ua.setOptionId(o.getId());
										ua.setProjectId(qn.getDoctype().getProjectid());
										ua.setQuestionId(q.getId());
										ua.setQuestionnaireId(qn.getDoctype().getUqsId());
										ua.setQuestionsetId(qs.getId());
										ua.setValidateobjecttype(a.getValidateobjecttype());
										ua.setValue(a.getValue());
										ua.setValidatetype(a.getValidatetype());
										ua.setType(a.getType());
										ua.setVersion(uqn.getVersion());
										this.uqsAttributeDao.insert(ua);
									}
									
								}
							}
						}
						
					}
					
				}
			}
			
		}
	}

	@Override
	public Paper getPaper(String projectId , File f){
		TempletQuestionnaireDocumentBean questionnaire = null;
		try {
			questionnaire = (TempletQuestionnaireDocumentBean)TempletQuestionnaireDocumentBean.Factory.parse(
					new XmlFileFilter(projectId).decryptionFile(f));
		} catch (Exception e) {
			try {
				questionnaire = (TempletQuestionnaireDocumentBean)TempletQuestionnaireDocumentBean.Factory.parse(
				new FileInputStream(f));
			} catch (Exception e1) {
				
			}
		}
		if (questionnaire != null){	
			Paper paper = new Paper(questionnaire.getQuestionnaire());
			//循环 试题组
			for(com.bdcor.pip.client.xml.model.TempletQuestionSetDocumentBean.QuestionSet qset:questionnaire.getQuestionnaire().getQuestionSetArray()){
				QuestionGroup qg = new QuestionGroup(qset);
				//循环试题
				for(com.bdcor.pip.client.xml.model.TempletQuestionDocumentBean.Question q:qset.getQuestionArray()){
					qg.getQs().add(new QuestionC(q));
				}
				paper.getQGroup().add(qg);
			}
			return paper;
		}
		return null;
	}

	/**
	 * 
	 * <doctype ProjectID="001" ProjectName="心血管病高危人群早期筛查与综合干预项目" UQSID="001001" UQSName="基本信息登记表" UQSVersion="001.001.001" VersionCreateDate="2014-08-14 09:18:01" UQSBeginTime="2014-12-08 14:18:20" UQSEndDate="" UQSTimeZone="" OperaterID="US0000000000674" OperaterName="loujing" HospitalCode="" HospitalName="" PatientID="" PersonID="" PatientName="" PatientCode="33050000019" UQSIsHold="1" UQSRemark="第1次暂停时间:2014-12-08 14:19:59,"/>
	 * */
	
	@Override
	public Result getResult(String projectId, String patientId, String item) {
		
		try {
			
			UqsAnswerExample example = new UqsAnswerExample();
			
			example.createCriteria()
				.andProjectIdEqualTo(projectId)
				.andQuestionnaireIdEqualTo(item)
				.andPatientIdEqualTo(patientId);
			
			List<UqsAnswer> list = this.uqsAnswerMapper.selectByExample(example);
			StringBuffer sb = new StringBuffer();
			sb.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?><questionnaire>");
			sb.append("<doctype ProjectID=\"");
			sb.append(projectId);
			sb.append("\" ProjectName=\"\" UQSID=\"");
			sb.append(item);
			sb.append("\" UQSVersion=\"001.001.001\" VersionCreateDate=\"2014-08-14 09:18:01\" HospitalCode=\"\" HospitalName=\"\" PatientID=\"\" PersonID=\"\" PatientName=\"\" PatientCode=\"");
			sb.append(patientId);
			sb.append("\" UQSIsHold=\"1\" />");
			for ( UqsAnswer a : list ){
				
				sb.append("<result ");
				sb.append(" questionset=\"");
				sb.append(a.getQuestionsetId());
				sb.append("\"");
				
				sb.append(" questionId=\"");
				sb.append(a.getQuestionId());
				sb.append("\"");
				
				sb.append(" optionid=\"");
				sb.append(a.getOptionId());
				sb.append("\"");
				
				if (StringUtils.isNotBlank(a.getAnswer())){
					sb.append(" answer=\"");
					sb.append(a.getAnswer());
					sb.append("\"");
				}
				
				sb.append(" />");
				
			}//*/
			sb.append("</questionnaire>");
			String xml = sb.toString();
			ResultQuestionnaireDocumentBean.Questionnaire resultQuestion = ResultQuestionnaireDocumentBean.Factory.parse(new ByteArrayInputStream(xml.getBytes("UTF-8"))).getQuestionnaire();
			return new Result(resultQuestion);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String checkIdError(Questionnaire qn) {
		//map-key set;,seta;,q;,qa;,o;,oa;,r,d,da
		Map<String,String> map1 = new HashMap<String,String>();
		if (qn.getQuestionSetList() != null ){
			for ( QuestionSet qs : qn.getQuestionSetList() ){
				String key1=qs.getId().trim();
				if(key1.equals("0")){
					System.out.println("题组Id为0");
					System.out.println(qs.getDisplayname());
					System.out.println("");
				}
				if(map1.get(key1)!=null){
					System.out.println("题组Id重复:"+key1);
					System.out.println(map1.get(key1));
					System.out.println(qs.getDisplayname());
					System.out.println("");
				}else{
					map1.put(key1,qs.getDisplayname());
				}
				
				if(qs.getAttributeList()!=null){
					Map<String,String> map2 = new HashMap<String,String>();
					for (Attribute a : qs.getAttributeList()){
						String key2=a.getId().trim();
						if(key2.equals("0")){
							System.out.println("题组属性Id为0");
							System.out.println(qs.getDisplayname());
							System.out.println("");
						}
						if(map2.get(key2)!=null){
							System.out.println("题组属性Id重复:"+key1+":"+key2);
							System.out.println(map2.get(key2));
							System.out.println(qs.getDisplayname());
							System.out.println("");
						}else{
							map2.put(key2,qs.getDisplayname());
						}
					}
				}
				
				if ( qs.getQuestionList() != null ){
					Map<String,String> map3 = new HashMap<String,String>();
					for ( Question q : qs.getQuestionList()){
						String key3=q.getId().trim();
						if(key3.equals("0")){
							System.out.println("题Id为0");
							System.out.println(q.getDisplayname());
							System.out.println("");
						}
						if(map3.get(key3)!=null){
							System.out.println("题Id重复:"+key1+"_"+key3);
							System.out.println(map3.get(key3));
							System.out.println(q.getDisplayname());
							System.out.println("");
						}else{
							map3.put(key3,q.getDisplayname());
						}
						
						if ( q.getAttributeList() != null ){
							Map<String,String> map4 = new HashMap<String,String>();
							for ( Attribute a : q.getAttributeList() ){
								String key4=a.getId().trim();
								if(key4.equals("0")){
									System.out.println("题属性Id为0");
									System.out.println(q.getDisplayname());
									System.out.println("");
								}
								if(map4.get(key4)!=null){
									System.out.println("题属性Id重复:"+key1+"_"+key3+":"+key4);
									System.out.println(map4.get(key4));
									System.out.println(q.getDisplayname());
									System.out.println("");
								}else{
									map4.put(key4,q.getDisplayname());
								}
							}
						}
						
						if ( q.getTableList() != null ){
							for ( Table t : q.getTableList() ){
								if ( t.getTrList() != null ){
									Map<String,String> map5 = new HashMap<String,String>();
									for ( Tr tr : t.getTrList() ){
										String key5=tr.getRownum().trim();
										if(key5.equals("0")){
											System.out.println("行rownum为0");
											System.out.println(tr.getDisplayname());
											System.out.println("");
										}
										if(map5.get(key5)!=null){
											System.out.println("行rownum重复:"+key1+"_"+key3+"_"+key5);
											System.out.println(map5.get(key5));
											System.out.println(tr.getDisplayname());
											System.out.println("");
										}else{
											map5.put(key5,tr.getDisplayname());
										}
										
										if (tr.getThList() != null ){
											Map<String,String> map6 = new HashMap<String,String>();
											for ( Th h : tr.getThList() ){
												String key6=h.getId().trim();
												if(key6.equals("0")){
													System.out.println("列Id为0");
													System.out.println(h.getDisplayName());
													System.out.println("");
												}
												if(map6.get(key6)!=null){
													System.out.println("列Id重复:"+key1+"_"+key3+"_"+key5+"-"+key6);
													System.out.println(map6.get(key6));
													System.out.println(h.getDisplayName());
													System.out.println("");
												}else{
													map6.put(key6,h.getDisplayName());
												}
												
												if ( h.getAttributeList() != null ){
													Map<String,String> map7 = new HashMap<String,String>();
													for ( Attribute a : h.getAttributeList()){
														String key7=a.getId().trim();
														if(key7.equals("0")){
															System.out.println("列属性Id为0");
															System.out.println(h.getDisplayName());
															System.out.println("");
														}
														if(map7.get(key7)!=null){
															System.out.println("列属性Id重复:"+key1+"_"+key3+"_"+key5+"-"+key6+":"+key7);
															System.out.println(map7.get(key7));
															System.out.println(h.getDisplayName());
															System.out.println("");
														}else{
															map7.put(key7,h.getDisplayName());
														}
													}
												}
											}
										}
									}
								}
							}
						}
						
						if ( q.getOptionList() != null ){
							Map<String,String> map8 = new HashMap<String,String>();
							for (Option o : q.getOptionList() ){
								String key8=o.getId().trim();
								if(key8.equals("0")){
									System.out.println("选项Id为0");
									System.out.println(o.getDisplayName());
									System.out.println("");
								}
								if(map8.get(key8)!=null){
									System.out.println("选项Id重复:"+key1+"_"+key3+"_"+key8);
									System.out.println(map8.get(key8));
									System.out.println(o.getDisplayName());
									System.out.println("");
								}else{
									map8.put(key8,o.getDisplayName());
								}
								
								if ( o.getAttributeList() != null ){
									Map<String,String> map9 = new HashMap<String,String>();
									for ( Attribute a : o.getAttributeList()){
										String key9=a.getId().trim();
										if(key9.equals("0")){
											System.out.println("选项属性Id为0");
											System.out.println(o.getDisplayName());
											System.out.println("");
										}
										if(map9.get(key9)!=null){
											System.out.println("选项属性Id重复:"+key1+"_"+key3+"_"+key8+":"+key9);
											System.out.println(map9.get(key9));
											System.out.println(o.getDisplayName());
											System.out.println("");
										}else{
											map9.put(key9,o.getDisplayName());
										}
									}
									
								}
							}
						}
						
					}
					
				}
			}
			
		}
		return null;
	}
	
	
}
