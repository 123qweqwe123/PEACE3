package com.bdcor.pip.web.pro.promgt.dao;

import java.util.List;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.pro.promgt.domain.Evaluation;
import com.bdcor.pip.web.pro.promgt.filter.LccFilter;

@MyBatisRepository
public interface EvaluationDao {

	List<Evaluation> getAllEvaluations(LccFilter filter);

	Evaluation getEvaluationById(String id);

	void addEvaluation(Evaluation eval);

	void updateEvaluation(Evaluation eval);

	void delete(String id);

	Evaluation checkLccNameExists(String lccCode);

}
