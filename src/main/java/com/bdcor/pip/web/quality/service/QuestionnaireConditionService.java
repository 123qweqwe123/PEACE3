package com.bdcor.pip.web.quality.service;

import java.util.List;		

import com.bdcor.pip.web.quality.domain.QuestionnaireCondition;
import com.bdcor.pip.web.quality.filter.QuestionnaireConditionFilter;

public interface QuestionnaireConditionService {
	List<QuestionnaireCondition> getQuestionnaireList(QuestionnaireConditionFilter filter) throws Exception;
}

