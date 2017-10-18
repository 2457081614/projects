package com.meession.market.answer.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.meession.market.common.RDU;

@Entity
@Table(name = "Answer")
public class Answer {
	/**
	 * 数据库唯一表示符
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "answer_id")
	private long id;
	/**
	 * 问卷的答案内容
	 */
	@Column(name = "answer_content",length = 65530)
	private String answerContent;
	/**
	 * 客户的id
	 */
	private long customer_id;
	/**
	 * 答案所对应的任务的id
	 */
	private long task_id;

	/**
	 * 当前answer对应题目的id
	 */
	private long question_id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAnswerContent() {
		return answerContent;
	}

	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}

	public long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(long customer_id) {
		this.customer_id = customer_id;
	}

	public long getTask_id() {
		return task_id;
	}

	public void setTask_id(long task_id) {
		this.task_id = task_id;
	}

	public long getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(long question_id) {
		this.question_id = question_id;
	}

	public static Answer mock() {
		Answer answer = new Answer();
		answer.setAnswerContent(RDU.randomAnswer());

		return answer;

	}

	public static List<Answer> mock(int size) {
		List<Answer> answers = new ArrayList<Answer>();
		for (int i = 0; i < size; i++) {
			answers.add(Answer.mock());
		}
		return answers;
	}

	@Override
	public String toString() {
		return "Answer [id=" + id + ", answerContent=" + answerContent + ", customer_id=" + customer_id + ", task_id="
				+ task_id + ", question_id=" + question_id + "]";
	}

}
