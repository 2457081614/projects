package com.meession.market.evaluation.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.meession.market.evaluation.dao.IEvaluationDao;
import com.meession.market.evaluation.entity.Evaluation;
import com.meession.market.evaluation.service.IEvaluationService;


@Repository
public class EvaluationService implements IEvaluationService{

	@Resource
	private IEvaluationDao evaluationDao;

	@Override
	public void addEvaluation(Evaluation evaluation) {
			evaluationDao.save(evaluation);
	}

	@Override
	public Evaluation findById(long id) {
		return evaluationDao.find(id);
	}

	@Override
	public List<Evaluation> findAll() {
		return evaluationDao.findAll();
	}

	@Override
	public void delete(long id) {
		evaluationDao.delete(id);
	}

	@Override
	public void delete(List<Long> ids) {
		evaluationDao.delete(ids);
	}

	@Override
	public void update(Evaluation evaluation) {
		evaluationDao.update(evaluation);
	}

	public IEvaluationDao getEvaluationDao() {
		return evaluationDao;
	}

	public void setEvaluationDao(IEvaluationDao evaluationDao) {
		this.evaluationDao = evaluationDao;
	}
	
	
	
}
