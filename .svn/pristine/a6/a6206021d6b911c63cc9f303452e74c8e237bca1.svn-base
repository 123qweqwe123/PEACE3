package com.bdcor.pip.web.data.service;

import java.io.File;

import com.bdcor.pip.client.vo.paper.Paper;
import com.bdcor.pip.client.vo.paper.Result;
import com.bdcor.pip.client.xml.model.TempletQuestionnaireDocumentBean;
import com.bdcor.pip.data.xml.vo.Questionnaire;

public interface UqsQuestionnaireService {

	public void save(Questionnaire qn)throws Exception;
	
	public Paper getPaper(String projectId , File f); 
	
	public Result getResult(String projectId , String patientId, String item);

	public String checkIdError(Questionnaire qn);
	
}
