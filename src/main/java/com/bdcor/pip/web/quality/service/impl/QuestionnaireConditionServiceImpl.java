package com.bdcor.pip.web.quality.service.impl;

import java.util.List;	

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdcor.pip.web.quality.dao.QuestionnaireConditionDao;
import com.bdcor.pip.web.quality.domain.QuestionnaireCondition;
import com.bdcor.pip.web.quality.filter.QuestionnaireConditionFilter;
import com.bdcor.pip.web.quality.service.QuestionnaireConditionService;

@Service
@Transactional
public class QuestionnaireConditionServiceImpl implements QuestionnaireConditionService {

	@Autowired
	private QuestionnaireConditionDao questionnaireConditionDao;
	
	@Override
	public List<QuestionnaireCondition> getQuestionnaireList(
			QuestionnaireConditionFilter filter) throws Exception {
		return questionnaireConditionDao.getQuestionnaireList(filter);
	}
	
}
