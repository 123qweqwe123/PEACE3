package com.bdcor.pip.web.pro.promgt.service;

import java.util.List;

import com.bdcor.pip.web.pro.promgt.domain.Evaluation;
import com.bdcor.pip.web.pro.promgt.filter.LccFilter;

public interface EvaluationService {

	List<Evaluation> getAllEvaluations(LccFilter filter);

	Evaluation getEvaluationById(String id);

	void addEvaluation(Evaluation eval);

	void updateEvaluation(Evaluation eval);

	void delete(String id);

	Boolean checkLccNameExists(String lccCode);

}
