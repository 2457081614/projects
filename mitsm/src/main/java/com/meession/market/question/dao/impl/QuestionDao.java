package com.meession.market.question.dao.impl;



import java.util.List;

import org.springframework.stereotype.Repository;

import com.meession.market.common.support.dao.DaoSupport;
import com.meession.market.question.dao.IQuestionDao;
import com.meession.market.question.entity.Question;


@Repository
public class QuestionDao extends DaoSupport implements IQuestionDao {
    
	@Override
	public void save(Question question) {
		getDaoTemplate().save(question);
	}

	@Override
	public void deleteById(Long id) {
		getDaoTemplate().delete(Question.class, id);
		
	}

	@Override
	public void update(Question question) {
		getDaoTemplate().update(question);
		
	}

	@Override
	public Question finfById(long id) {
		
		return getDaoTemplate().find(Question.class, id);
	}

	@Override
	public List<Question> getAllQuestion() {
		
		return getDaoTemplate().list(Question.class);
	}

	@Override
	public void deleteBatchById(List<Long> ids) {
		getDaoTemplate().delete(Question.class, ids);
		
	}

	
}
