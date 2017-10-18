package com.meession.market.answer.dao;

import java.util.List;

import com.meession.market.answer.entity.Answer;
import com.meession.market.common.support.dao.IDaoSupport;
import com.meession.market.staff.entity.Staff;


public interface IAnswerDao extends IDaoSupport {
	
	
	
	
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public  void deleteBatch(List<Long> ids);
	
	
	
	/**
	 * 修改
	 * 
	 */
	public void updateAnswer(Answer answer);
	/**
	 * 添加问题到数据库
	 * 
	 */
	public void addAnswer(Answer answer);
	/**
	 * 删除
	 * 
	 */
	

	/**
	 * 通过id查寻
	 * @param id
	 * @return
	 */
	public Answer findAnswerById(long id);
	
	public List<Answer> findAll();
	public void deleteById(long id);
}
