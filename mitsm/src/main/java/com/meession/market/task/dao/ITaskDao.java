package com.meession.market.task.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.meession.market.task.entity.Task;

@Repository
public interface ITaskDao {
	public void addTask(Task task);

	public void deleteById(long id);
	
	public void delete(List<Long> ids);

	public void update(Task task);

	public Task findTaskById(long id);

	public List<Task> findAll();
}
