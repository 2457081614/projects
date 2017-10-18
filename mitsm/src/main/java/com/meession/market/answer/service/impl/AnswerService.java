package com.meession.market.answer.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.meession.market.answer.dao.IAnswerDao;
import com.meession.market.answer.entity.Answer;
import com.meession.market.answer.service.IAnswerService;

@Repository
public class AnswerService implements IAnswerService {
	@Resource
	private IAnswerDao answerDao;
	
	@Override
	public void deleteBatch(List<Long> ids) {
		answerDao.deleteBatch(ids);
	}

	@Override
	public void addAnswer(Answer answer) {
	answerDao.addAnswer(answer);
		
	}

	@Override
	public void deleteById(long id) {
		answerDao.deleteById(id);
		
	}

	@Override
	public void updateAnswer(Answer answer) {
	answerDao.updateAnswer(answer);
		
	}

	

	@Override
	public Answer findAnswerById(Long id) {
		
		return answerDao.findAnswerById(id);
	}

	@Override
	public List<Answer> findAllAnswers() {
		
		return answerDao.findAll();
	}


	public IAnswerDao getAnswerDao() {
		return answerDao;
	}

	public void setAnswerDao(IAnswerDao answerDao) {
		this.answerDao = answerDao;
	}

	
}
