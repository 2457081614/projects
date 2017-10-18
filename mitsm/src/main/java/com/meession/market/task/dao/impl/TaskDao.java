package com.meession.market.task.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.meession.market.common.support.dao.DaoSupport;
import com.meession.market.task.dao.ITaskDao;
import com.meession.market.task.entity.Task;

@Repository
public class TaskDao extends DaoSupport implements ITaskDao {

	@Override
	public void addTask(Task task) {
		System.err.println("xxxxxxxxx"+task.toString());
		getDaoTemplate().save(task);
	}

	@Override
	public void deleteById(long id) {
		getDaoTemplate().delete(Task.class, id);
	}

	public void delete(List<Long> ids) {
		getDaoTemplate().delete(Task.class, ids);
	}
	
	
	@Override
	public void update(Task task) {
		System.err.println("Daoxxxxx"+task.toString());
		getDaoTemplate().update(task);
		System.err.println("Daoxxxxxxx"+task.toString());
	}

	@Override
	public Task findTaskById(long id) {
	return	getDaoTemplate().find(Task.class, id);
	}

	@Override
	public List<Task> findAll() {
       return   getDaoTemplate().list(Task.class);
	}

}
