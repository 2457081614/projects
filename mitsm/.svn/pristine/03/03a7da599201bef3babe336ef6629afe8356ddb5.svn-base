package com.meession.market.question.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import com.meession.market.question.dao.IQuestionDao;
import com.meession.market.question.entity.Question;
import com.meession.market.question.service.IQuestionService;

@Repository
public class QuestionService implements IQuestionService {
	@Resource
	private IQuestionDao questionDao;

	@Override
	public void add(Question question) {
		/**
		 * 没有内容的问题会被过滤掉
		 */
		if (!StringUtils.isBlank(question.getQuestionContent())) {
			questionDao.save(question);
		}

	}

	@Override
	public void deleteQuestionById(Long id) {
		questionDao.deleteById(id);

	}

	@Override
	public void updateQuestion(Question question) {
		questionDao.update(question);

	}

	@Override
	public void updateBatch(List<Question> questions) {
		if (questions != null) {
			for (Question q : questions) {
				questionDao.update(q);
			}
		}
	}

	@Override
	public Question findById(long id) {

		return questionDao.finfById(id);
	}

	@Override
	public List<Question> findAllQuestions() {

		return questionDao.getAllQuestion();
	}

	@Override
	public void deleteBatch(List<Long> ids) {
		questionDao.deleteBatchById(ids);
	}

	@Override
	public void addBatch(List<Question> questions) {
		if (questions != null) {
			for (Question question : questions) {
				if (!StringUtils.isBlank(question.getQuestionContent()))
					questionDao.save(question);
			}
		}
	}
}
