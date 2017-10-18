package com.meession.market.question.service;

import java.util.List;

import com.meession.market.question.entity.Question;

public interface IQuestionService {

	public void add(Question question);
	public void addBatch(List<Question> questions);
	public void deleteQuestionById(Long id);
	public void updateQuestion(Question question);
	public void updateBatch(List<Question> questions);
	public Question findById(long id);
	public List<Question> findAllQuestions();
	public void deleteBatch(List<Long> ids);
	
}
