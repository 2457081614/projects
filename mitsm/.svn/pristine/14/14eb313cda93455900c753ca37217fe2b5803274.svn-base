package com.meession.market.evaluation.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.meession.market.common.support.dao.DaoSupport;
import com.meession.market.evaluation.dao.IEvaluationDao;
import com.meession.market.evaluation.entity.Evaluation;

@Repository
public class EvaluationDao extends DaoSupport implements IEvaluationDao{

	@Override
	public void save(Evaluation evaluation) {
		 getDaoTemplate().save(evaluation);
	}

	@Override
	public Evaluation find(long id) {
		return getDaoTemplate().find(Evaluation.class, id);
	}

	@Override
	public List<Evaluation> findAll() {
		return getDaoTemplate().list(Evaluation.class);
	}

	@Override
	public void delete(long id) {
		getDaoTemplate().delete(Evaluation.class,id);
	}

	@Override
	public void delete(List<Long> ids) {
		getDaoTemplate().delete(Evaluation.class,ids);
	}

	@Override
	public void update(Evaluation evaluation) {
		getDaoTemplate().update(evaluation);
	}

}
