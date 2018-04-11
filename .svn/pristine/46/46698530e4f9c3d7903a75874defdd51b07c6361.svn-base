package com.bdcor.pip.web.common.service.impl;

import com.bdcor.pip.client.vo.paper.Paper;
import com.bdcor.pip.client.vo.paper.QuestionC;
import com.bdcor.pip.client.vo.paper.QuestionGroup;
import com.bdcor.pip.client.vo.paper.QuestionOption;
import com.bdcor.pip.web.common.dao.ComboxMapper;
import com.bdcor.pip.web.common.service.ComboxService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ComboxServiceImpl implements ComboxService {
	
	@Autowired
	private ComboxMapper comboxServiceDao;


	@Override
	public String getDict(String code, String prevCode, String q, int limit) {
		List<Map<String,String>> list = null;
		
		Map<String,Object> paramMap = new HashMap<String, Object>();
		q=q.replaceAll("%|'|\"","#").toUpperCase();
		paramMap.put("code", code);
		paramMap.put("prevCode", prevCode);
		paramMap.put("q", q);
		paramMap.put("limit", limit);
		if(code.startsWith("PUB")){
			list=comboxServiceDao.getDict_PUB(paramMap);
		}else if(code.startsWith("DISTRICT")){
			list=comboxServiceDao.getDict_DISTRICT(paramMap);
		}else if(code.startsWith("ICD")){
			list=comboxServiceDao.getDict_ICD(paramMap);
		}
		
		if(list==null || list.size()==0){
			return "";
		}
		
		String returnStr="";
		for(Map<String,String> map:list){
			if(returnStr.length()>0){
				returnStr += "\n";
			}
			returnStr += map.get("vName")+"|"+map.get("vCode");
		}
		return returnStr;
	}
	@Override
	public String getDictName(Paper qn, String questionSetId,String questionId, String resultId, String resutlStr) {
		if(qn.getQGroup()==null)return null;
		for(QuestionGroup qSet : qn.getQGroup()){
			if(qSet.getId().equals(questionSetId) && qSet.getQs()!=null){
				for(QuestionC q : qSet.getQs()){
					if(q.getId().equals(questionId.substring(0,questionId.length()>3?questionId.length()-3:questionId.length())) && q.getOptions()!=null){
						for(QuestionOption o:q.getOptions()){
							if(o.getId().equals(resultId) && o.getCode()!=null && o.getCode().trim().length()>0){
								return getDictName(o.getCode().trim(),resutlStr);
							}
						}
					}
				}
			}
		}
		return null;
	}
	private String getDictName(String dictCode, String vCode) {
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("dictCode", dictCode);
		paramMap.put("vCode", vCode);
		if(dictCode.startsWith("PUB")){
			return comboxServiceDao.getName_PUB(paramMap);
		}else if(dictCode.startsWith("DISTRICT")){
			return comboxServiceDao.getName_DISTRICT(paramMap);
		}else if(dictCode.startsWith("ICD")){
			return comboxServiceDao.getName_ICD(paramMap);
		}
		return null;
	}
	@Override
	public String getAreaName(String vCode)
	{
		return comboxServiceDao.getAreaName(vCode);
	}


	public String getAutoData( String q , String limit , String type ){

		// 按照病人姓名或者简拼查询
		if(StringUtils.isNotBlank(q)){
			List<Map<String,String>> list = comboxServiceDao.getPatientByName(q.toUpperCase(),limit);

			String returnStr="";
			for(Map<String,String> map:list){
				if(returnStr.length()>0){
					returnStr += "\n";
				}
 				returnStr += map.get("PATIENT_NAME")+"|"+map.get("PATIENT_ID");
			}
			return returnStr;
		}

		return "";
	}
	
	public String getAutoDoctor( String q , String limit , String lccCode ){

		// 按照病人姓名或者简拼查询
		if(StringUtils.isNotBlank(q)){
			List<Map<String,String>> list = comboxServiceDao.getUserByName(q.toUpperCase(),
					limit,lccCode);

			String returnStr="";
			for(Map<String,String> map:list){
				if(returnStr.length()>0){
					returnStr += "\n";
				}
 				returnStr += map.get("NAME")+"|"+map.get("LCC_NAME");
			}
			return returnStr;
		}

		return "";
	}
}
