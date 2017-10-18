package com.meession.market.task.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.meession.market.task.dao.ITaskDao;
import com.meession.market.task.entity.Task;
import com.meession.market.task.service.ITaskService;

@Repository
public class TaskService implements ITaskService {

	@Resource
	private ITaskDao taskDao;

	@Override
	public void addTask(Task task) {
		taskDao.addTask(task);
	}

	@Override
	public void deleteTask(long id) {
		taskDao.deleteById(id);
	}

	@Override
	public void deleteTasks(List<Task> tasks) {
		if(tasks != null){
			List<Long> ids = new ArrayList<>();
			for(Task t : tasks){
				ids.add(t.getId());
			}
			taskDao.delete(ids);
		}
	}
	
	@Override
	public void updateTask(Task task) {
		taskDao.update(task);
	}

	@Override
	public Task findById(long id) {
		return taskDao.findTaskById(id);
	}

	@Override
	public List<Task> findAll() {
		return taskDao.findAll();
	}

}
