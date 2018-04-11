package com.bdcor.pip.web.data.service.impl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.xmlbeans.XmlOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bdcor.pip.client.vo.paper.Result;
import com.bdcor.pip.client.vo.paper.ResultOption;
import com.bdcor.pip.client.xml.model.result.ResultQuestionnaireDocumentBean;
import com.bdcor.pip.client.xml.model.result.ResultDoctypeDocumentBean.Doctype;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.data.util.CryptoUtil;
import com.bdcor.pip.web.data.dao.PatientNameModifyMapper;
import com.bdcor.pip.web.data.dao.PipCommPatientMapper;
import com.bdcor.pip.web.data.domain.PipCommPatient;
import com.bdcor.pip.web.data.filter.PatientNameModifyFilter;
import com.bdcor.pip.web.data.service.PatientNameModifyService;

@Service
public class PatientNameModifyServiceImpl implements PatientNameModifyService {
	
	@Autowired
	PatientNameModifyMapper patientNameModifyDao;
	
	@Autowired
	PipCommPatientMapper pipCommPatientDao;

	@Override
	public List<Map<String, Object>> list(PatientNameModifyFilter filter) {
		filter.setProjectId(Securitys.getCurrentProject());
		return patientNameModifyDao.list(filter);
	}

	@Override
	@Transactional(
			propagation = Propagation.REQUIRED ,
			isolation = Isolation.DEFAULT,
			rollbackFor = Exception.class
		)
	public void save(String patientId,String oldPatientName, String newPatientName) throws Exception {
		Map<String,String> dropMap = new HashMap<String, String>();
		dropMap.put("patientId", patientId);
		dropMap.put("remark", "改名字"+oldPatientName+"为"+newPatientName);
		dropMap.put("createBy", Securitys.getUserId());
		patientNameModifyDao.insertDrop(dropMap);
		
		
		PipCommPatient p = new PipCommPatient();
		p.setProjectId(Securitys.getCurrentProject());
		p.setPatientId(patientId);
		p.setPatientName(newPatientName);
		
		pipCommPatientDao.updateByPrimaryKeySelective(p);
		
		Map<String,Object> logMap = patientNameModifyDao.getLogMap(patientId,"004001");
		if(logMap == null || logMap.get("UQS_FILE")==null)return;
		
		//答过问卷的情况
		String xmlPath = logMap.get("UQS_FILE").toString().trim();
		File xml = new File(xmlPath);
		int i = xmlPath.lastIndexOf("/");
		
		String dropDirPath = xmlPath.substring(0,i+1)+"drop/";
		File dropDir = new File(dropDirPath);
		if(!dropDir.exists()){
			dropDir.mkdirs();
		}
		
		String dropXmlPath = dropDirPath+xmlPath.substring(i+1);
		File dropXml = new File(dropXmlPath);
		if(!dropXml.exists()){
			dropXml.createNewFile();
			FileUtils.copyFile(xml, dropXml);
		}
		
		patientNameModifyDao.updateAnswerPatientName(patientId,newPatientName);
		
		BufferedReader br = null;
		Result r = null;
		try{
			br = new BufferedReader(new FileReader(xml));
			StringBuffer buffer = new StringBuffer();
	  	   	String line = "";
	  	   	while ((line = br.readLine()) != null){
	  	   		buffer.append(line);
	  	   	}
	  	   	r = new Result(ResultQuestionnaireDocumentBean.Factory.parse(new ByteArrayInputStream(buffer.toString().getBytes())).getQuestionnaire());
		}finally{
			if(br != null)br.close();
		}
		
		for(ResultOption o : r.getOptions()){
			if(o.getQuestionSetId().equals("1") && o.getQuestionId().equals("1") && o.getResultId().equals("1")){
				o.setResutlStr(newPatientName);
				break;
			}
		}
		saveFile(r,xmlPath);
		
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
	}
	

}
