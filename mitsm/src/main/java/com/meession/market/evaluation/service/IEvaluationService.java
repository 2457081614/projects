package com.meession.market.evaluation.service;

import java.util.List;

import com.meession.market.evaluation.entity.Evaluation;

public interface  IEvaluationService {
	public void addEvaluation(Evaluation evaluation);
	public Evaluation findById(long id);
	public List<Evaluation> findAll();
	
	public void delete(long id);
	public void delete(List<Long> ids);
	public void update(Evaluation evaluation);

}
