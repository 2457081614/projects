package com.meession.market.answer.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.meession.market.answer.dao.IAnswerDao;
import com.meession.market.answer.entity.Answer;
import com.meession.market.common.support.dao.DaoSupport;

@Repository
public class AnswerDao extends DaoSupport  implements IAnswerDao {

	@Override
	public void deleteBatch(List<Long> ids) {
		getDaoTemplate().delete(Answer.class, ids);
		
	}

	@Override
	public void updateAnswer(Answer answer) {
	getDaoTemplate().update(answer);
	}


	@Override
	public Answer findAnswerById(long id) {
		return getDaoTemplate().find(Answer.class, id);
	}

	

	@Override
	public List<Answer> findAll() {
		
		return getDaoTemplate().list(Answer.class);
	}

	@Override
	public void addAnswer(Answer answer) {
		getDaoTemplate().save(answer);
	}

	@Override
	public void deleteById(long id) {
		getDaoTemplate().delete(Answer.class, id);
		
	}

}
