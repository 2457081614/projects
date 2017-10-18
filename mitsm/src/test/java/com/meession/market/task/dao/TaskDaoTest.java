package com.meession.market.task.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;

import com.meession.market.AbstractSpringTest;
import com.meession.market.common.dateutil.DateUtil;
import com.meession.market.customer.dao.ICustomerDao;
import com.meession.market.customer.entity.Customer;
import com.meession.market.parttimestaff.dao.IParttimeStaffDao;
import com.meession.market.question.dao.IQuestionDao;
import com.meession.market.question.entity.Question;
import com.meession.market.staff.dao.IStaffDao;
import com.meession.market.task.dao.ITaskDao;
import com.meession.market.task.entity.Task;

public class TaskDaoTest extends AbstractSpringTest {

	@Resource
	private ITaskDao taskDao;

	@Resource
	private IStaffDao staffDao;

	@Resource
	private IParttimeStaffDao parttimeStaffDao;

	@Resource
	private ICustomerDao customerDao;
	
	@Resource
	private IQuestionDao questionDao;

	@Test
	public void testAddTask() {
		Task task = new Task();
		Set<Customer>customers=new HashSet<Customer>();
		Set<Question>questions=new HashSet<Question>();
		customers.add(customerDao.find(1));
		customers.add(customerDao.find(2));
		customers.add(customerDao.find(3));
		task.setStaff(staffDao.findStaffById(60));
		task.setCustomers(customers);
		task.setParttimeStaff(parttimeStaffDao.findById(4));
		task.setTaskDescription("此次任务主要是完成客户信息收集，以及客户对我们有哪些建议看法");
		for(Question q:questionDao.getAllQuestion()){
			questions.add(q);
		   task.setQuestions(questions);
		}
		task.setAssignedDate(DateUtil.dateToString(new Date()));
		task.setPlannedFinishedDate(DateUtil.dateToString(new Date()));
		task.setFinishedDate(DateUtil.dateToString(new Date()));
		taskDao.addTask(task);
	}
	

	// @Test
	// public void testDeleteTask() {
	//
	// taskDao.deleteById(1);
	// }
	//
	 @Test
	 public void findAllTasks() {
	
	 List<Task> tasks = taskDao.findAll();
	
	 System.out.println(tasks.toString());
	 }

}
