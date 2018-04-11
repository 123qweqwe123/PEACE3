package com.bdcor.pip.web.pro.promgt.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdcor.pip.core.exception.ServiceException;
import com.bdcor.pip.core.utils.GenerateKey;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.pro.promgt.dao.EvaluationDao;
import com.bdcor.pip.web.pro.promgt.domain.Evaluation;
import com.bdcor.pip.web.pro.promgt.filter.LccFilter;
import com.bdcor.pip.web.pro.promgt.service.EvaluationService;

@Service
@Transactional
public class EvaluationServiceImpl implements EvaluationService {

	@Autowired
	private EvaluationDao evaluationDao;

	@Override
	public List<Evaluation> getAllEvaluations(LccFilter filter) {
		filter.setUserId(Securitys.getUserId());
		return evaluationDao.getAllEvaluations(filter);
	}

	@Override
	public Evaluation getEvaluationById(String id) {
		return evaluationDao.getEvaluationById(id);
	}

	@Override
	public void addEvaluation(Evaluation eval) {
		try{
			eval.setId(GenerateKey.getKey(GenerateKey.PREFIX_PROJECT));
			eval.setCreateBy(Securitys.getUserId());
			eval.setCreateDate(new Date());
			evaluationDao.addEvaluation(eval);
		}catch(Exception e){
			throw new ServiceException("添加单位评价失败！", e);
		}
	}

	@Override
	public void updateEvaluation(Evaluation eval) {
		try{
			eval.setUpdateBy(Securitys.getUserId());
			eval.setUpdateDate(new Date());
			evaluationDao.updateEvaluation(eval);
		}catch(Exception e){
			throw new ServiceException("更新单位评价失败！", e);
		}
	}

	@Override
	public void delete(String id) {
		try{
			evaluationDao.delete(id);
		}catch(Exception e){
			throw new ServiceException("删除单位评价失败！", e);
		}
	}

	@Override
	public Boolean checkLccNameExists(String lccCode) {
		Evaluation eval = evaluationDao.checkLccNameExists(lccCode);
		if(eval==null) return false;
		return true;
	}
}
