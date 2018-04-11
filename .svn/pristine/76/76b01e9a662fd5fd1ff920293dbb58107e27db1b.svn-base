package com.bdcor.pip.web.quality.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import java.util.Arrays;

import com.bdcor.pip.client.vo.paper.*;
import com.bdcor.pip.core.excelExport.lang.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdcor.pip.client.xml.model.TempletQuestionDocumentBean.Question;
import com.bdcor.pip.client.xml.model.TempletQuestionSetDocumentBean.QuestionSet;
import com.bdcor.pip.client.xml.model.TempletQuestionnaireDocumentBean;
import com.bdcor.pip.client.xml.model.TempletQuestionnaireDocumentBean.Questionnaire;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.dict.utils.DictUtils;
import com.bdcor.pip.web.quality.dao.PreviewQuestionnaireDao;
import com.bdcor.pip.web.quality.domain.PatientQn;
import com.bdcor.pip.web.quality.filter.PatientQnFilter;
import com.bdcor.pip.web.quality.service.PreviewQuestionnaireService;

@Service
@Transactional
public class PreviewQuestionnaireServiceImpl implements PreviewQuestionnaireService {

	@Autowired
	private PreviewQuestionnaireDao previewQuestionnaireDao;

	@Override
	public List<PatientQn> getPatientQnList(PatientQnFilter filter) {
		filter.setProjectId(Securitys.getUser().getCurrent_projectId());
		filter.setUserId(Securitys.getUserId());
		return previewQuestionnaireDao.getPatientQnList(filter);
	}

	@Override
	public List<Map<String, Object>> getQnList(Map<String, String> map) {
		return previewQuestionnaireDao.getQnList(map);
	}

	@Override
	@Cacheable(value="paperCache",key="#path") 
	public Paper getPaperByPath(String path) {
		TempletQuestionnaireDocumentBean questionnaire = null;
		try {
			
			File f = new File(path);
			String a = Securitys.getUser().getCurrent_projectId();
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
	public Map<String, String> getAnswerMap(String qnVersion,String patientId,String logId) {
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("version", "004.002.001".equals(qnVersion) ? "004.013.001" : qnVersion );
		paramMap.put("patientId", patientId);
		paramMap.put("projectId", Securitys.getUser().getCurrent_projectId());
		if(logId != null && !logId.trim().equals("-1"))paramMap.put("logId",logId);
		if(qnVersion.substring(4,7).equals("007"))paramMap.put("min", "_MIN");
		Map<String,String> returnMap = new HashMap<String,String>();
		List<Map<String,String>> list = previewQuestionnaireDao.getAnswerList(paramMap);
		
		if(list != null && list.size()>0){
			for(Map<String,String> map:list){
				if(map.get("answer")==null)map.put("answer","");
				returnMap.put(map.get("id"), map.get("answer"));
			}
		}
		return returnMap;
	}

    /**
     *  根据问卷配置填充问卷字典
     * @param qn
     * @param resultOptionMap
     * @return
     */
    @Override
    public Map<String, String> getAnswerWithDict(Paper qn, Map<String, String> resultOptionMap) {
        Iterator it = resultOptionMap.entrySet().iterator();
        Map<String,Map<String,String>> res = new HashMap<String,Map<String,String>>();
        while(it.hasNext()){
            Map.Entry entry = (Map.Entry)it.next();
            String[] ids = entry.getKey().toString().split("_");
            System.out.println(Arrays.toString(ids));
            String newKey = ids[0]+"_"+ ids[1].substring(0,ids[1].length() > 3 ? ids[1].length()- 3:ids[1].length());
            if(res.containsKey(newKey)){
                res.get(newKey).put(entry.getKey().toString(),entry.getValue().toString());
            }else{
                Map<String, String> m = new HashMap<String, String>();
                m.put(entry.getKey().toString(),entry.getValue().toString());
                res.put(newKey,m);
            }
        }

        for (QuestionGroup qo : qn.getQGroup()) {
            for(QuestionC qc : qo.getQs()){
                for(QuestionOption qp : qc.getOptions()){
                    if( qp.getCode() != null  && !"".equals(qp.getCode()) ){
                        if(res.containsKey(qo.getId()+"_"+qc.getId())){
                            Map map = res.get(qo.getId()+"_"+qc.getId());
                            Iterator ite = map.entrySet().iterator();
                            while(ite.hasNext()){
                                Map.Entry me = (Map.Entry)ite.next();
                                resultOptionMap.put(me.getKey().toString(),
                                        getDictValue(qp.getCode(),me.getValue().toString()));
                            }

                        }
                    }
                }
            }
        }
        return resultOptionMap;
    }

    private String getDictValue(String dictCode,String vCode){
		dictCode = dictCode.trim().toUpperCase();
		
		Map<String,String> pMap = new HashMap<String,String>();
		pMap.put("dictCode",dictCode);
		pMap.put("vCode",vCode);
		String dictName = "";
		if(dictCode.startsWith("PUB.")){
			dictName = previewQuestionnaireDao.getDictName_PUB(pMap);
		}
		if(dictCode.startsWith("DISTRICT.")){
			dictName = previewQuestionnaireDao.getDictName_DISTRICT(pMap);
		}
		if(dictCode.startsWith("PI.")){
			dictName = previewQuestionnaireDao.getDictName_PI(pMap);
		}
		if(dictCode.startsWith("ICD.")){
			dictName = previewQuestionnaireDao.getDictName_ICD(pMap);
		}
		dictName = (dictName == null) ? vCode : dictName;
		return dictName;
	}

	
}
