package com.meession.market.task.service;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import com.meession.market.AbstractSpringTest;
import com.meession.market.task.dao.ITaskDao;
import com.meession.market.task.entity.Task;

public class TaskServiceTest extends AbstractSpringTest{
	
	@Resource
	private ITaskDao taskDao;
	
	@Test
	public void testFind() throws Exception{
		Task task1 = taskDao.findTaskById(1);
		Task task2 = new Task();
		BeanUtils.copyProperties(task2, task1);
		task2.setId(0);
		task2.setTaskDescription("哈哈哈");
		taskDao.update(task2);
		
	}
	

}
