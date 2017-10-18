package com.meession.market.question.service;

import javax.annotation.Resource;

import org.junit.Test;

import com.meession.market.AbstractSpringTest;
import com.meession.market.question.entity.Question;

public class QuestionServiceTest extends AbstractSpringTest{
	@Resource
	private IQuestionService questionService;
	
	@Test
	public void testUpdate(){
		Question question = questionService.findById(2);
		System.err.println(question);
		question.setPurpose("解放思想");
		questionService.updateQuestion(question);
	}
	
}
