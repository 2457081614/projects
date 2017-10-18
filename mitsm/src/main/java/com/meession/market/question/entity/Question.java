package com.meession.market.question.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;

import com.meession.market.common.RDU;
import com.meession.market.task.entity.Task;

@Entity
@Table(name = "Question")
public class Question {
	
	public static final String FIRST_STAGE = "第一阶段";
	public static final String SECOND_STAGE = "第二阶段";
	public static final String THIRD_STAGE = "第三阶段";
	public static final String FOURTH_STAGE = "第四阶段";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "question_id")
	private long id;
	/**
	 * 问题内容
	 */
	private String questionContent;
	/**
	 * 问题阶段
	 */
	private String stage;
	/**
	 * 问题意图
	 */
	private String purpose;
	
	
//	@ManyToOne(targetEntity = Task.class)
//	@JoinColumn(name = "task_id",
//		referencedColumnName = "task_id")
//	private Task task;
	
	public void setId(long id) {
		this.id = id;
	}
	public long getId() {
		return id;
	}
	public String getQuestionContent() {
		return questionContent;
	}
	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
//	public void setTask(Task task) {
//		this.task = task;
//	}
//	public Task getTask() {
//		return task;
//	}
	
	
	public static Question cloneQusetion(Question srcQuestion){
		Question newQuestion = new Question();
		newQuestion.setPurpose(srcQuestion.getPurpose());
		newQuestion.setQuestionContent(srcQuestion.getQuestionContent());
		newQuestion.setStage(srcQuestion.getStage());
		return newQuestion;
	}
	
	public static Question mock(){
		Question  question =new Question();
		question.setQuestionContent(RDU.randomQuestion());
		question.setPurpose(RDU.randomPurpose());
		question.setStage(RDU.randomStage());
		return question;
		
	}
	public static List<Question> mock(int size) {
		List<Question> questions = new ArrayList<Question>();
		for (int i = 0; i < size; i++) {
			questions.add(Question.mock());
		}
		return questions;
	}
	
	public static List<Question> getQuestions(String[][] sourceData) {
		List<Question> list = new ArrayList<>();
		if (sourceData != null && sourceData.length > 0) {
			int questionContent = 0, stage = 0, purpose = 0;
			for (int i = 0; i < sourceData[0].length; i++) {
				String s = sourceData[0][i];
				if (s != null && !"".equals(s.trim())) {
					if (s.contains("问题内容") || s.contains("内容"))
						questionContent = i;
					else if (s.contains("阶段") || s.contains("问题阶段"))
						stage = i;
					else if (s.contains("意图") || s.contains("目的"))
						purpose = i;
					
				}
			}
			Question question = null;
			for (int i = 1; i < sourceData.length; i++) {
				String[] s = sourceData[i];
				int j = 0;
				for (j = 0; s != null && j < s.length && StringUtils.isBlank(s[j]); j++);
				if (j < s.length) {
					 question=new Question();
					 question.setQuestionContent(sourceData[i][questionContent]);
					 question.setPurpose(sourceData[i][purpose]);
					 question.setStage(sourceData[i][stage]);
					list.add(question);
				}
			}
			System.gc();
		}

		return list;
	}
	@Override
	public String toString() {
		return "Question [id=" + id + ", questionContent=" + questionContent + ", stage=" + stage + ", purpose="
				+ purpose;
	}
	
	
	
	
}
