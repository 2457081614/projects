package com.meession.market.question.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.meession.market.AbstractSpringTest;
import com.meession.market.question.entity.Question;

public class QuestionDaoTest extends AbstractSpringTest {
	@Resource
	private IQuestionDao questionDao;

	@Test
	public void testAdd() {
		for (int i = 0; i < 5; i++) {
			Question question = Question.mock();
			questionDao.save(question);
		}
	}

	@Test
	public void testFindAll() {
		List<Question> questionList = questionDao.getAllQuestion();
		for (Question question : questionList) {
			System.out.println(question);
		}
	}
}
