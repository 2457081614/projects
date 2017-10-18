package com.meession.market.customer.view;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;

import com.meession.market.answer.entity.Answer;
import com.meession.market.answer.view.AnswerView;
import com.meession.market.common.dateutil.DateUtil;
import com.meession.market.common.entity.Resumable;
import com.meession.market.common.util.ExcelReader;
import com.meession.market.customer.entity.Customer;
import com.meession.market.customer.service.ICustomerService;
import com.meession.market.parttimestaff.entity.ParttimeStaff;
import com.meession.market.question.entity.Question;
import com.meession.market.question.view.QuestionView;
import com.meession.market.task.entity.Task;
import com.meession.market.task.service.ITaskService;

@ManagedBean
@ViewScoped
public class CustomerView implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{customerService}")
	private ICustomerService customerService;
	/**
	 * 一个对象:当点击查看、修改、删除时，存储要被操作的对象的属性
	 */
	private Customer customer = new Customer();
	/**
	 * 没有被删除的客户列表
	 */
	private List<Customer> customerList;
	/**
	 * 被删除的客户列表
	 */
	private List<Customer> deletedCustomerList;

	/**
	 * 使用过滤条件时得到的若干条符合条件的数据
	 */
	private List<Customer> filteredCustomerList;
	/**
	 * 批量删除时被选中的行
	 */
	private List<Customer> selectedCustomerList;

	@ManagedProperty(value = "#{answerView}")
	private AnswerView answerView;

	private HttpSession session;

	private Task taskInfo = new Task();

	/**
	 * 用来给兼职页面的客户list
	 */
	private List<Customer> customerListForParttimeStaff = new ArrayList<Customer>();

	/**
	 * 问题的id列表
	 */
	private List<Long> answerIdList = new ArrayList<Long>();

	@PostConstruct
	public void init() {
		reSelect();
		/* The below was written by ZY&YLX */
		/* The below was written by ZY&YLX */
		/* The below was written by ZY&YLX */
		session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		/* The upper was written by ZY&YLX */
		/* The upper was written by ZY&YLX */
		/* The upper was written by ZY&YLX */

		/* customerListForParttimeStaff=findCustomersByTask(); */
		/********************************* 新内核 *******************************************/
		oneList.add("");
		answerList = new ArrayList<>();
		allTaskList = taskService.findAll();

		/* initDisables(); */
		/********************************* 新内核 *******************************************/
		initNewCustomer();
	}

	/**
	 * 更新客户信息
	 */
	public void updateCustomer() {
		customerService.update(this.customer);
		showResultTip("修改成功", FacesMessage.SEVERITY_INFO);
	}

	/**
	 * 彻底删除一个客户
	 */
	public void delete() {
		if (customer != null) {
			this.deletedCustomerList.remove(customer);
			customerService.delete(customer.getId());
			showResultTip("已彻底删除该客户", FacesMessage.SEVERITY_INFO);
			if (this.filteredCustomerList != null && this.filteredCustomerList.contains(customer)) {
				this.filteredCustomerList.remove(customer);
			}
		} else {
			showResultTip("删除失败", FacesMessage.SEVERITY_ERROR);
		}
	}

	/**
	 * 彻底删除所有回收站中的客户
	 */
	public void deleteAll() {
		List<Long> ids = new ArrayList<>();
		if (deletedCustomerList != null && deletedCustomerList.size() > 0) {
			for (Customer c : deletedCustomerList) {
				ids.add(c.getId());
			}
			customerService.delete(ids);
			this.deletedCustomerList.clear();
			if (filteredCustomerList != null)
				this.filteredCustomerList.clear();
			showResultTip("已彻底删除回收站中的所有客户", FacesMessage.SEVERITY_INFO);
		} else {
			showResultTip("请至少选择一个客户", FacesMessage.SEVERITY_ERROR);
		}
	}

	/**
	 * 从回收站中恢复一个客户
	 */
	public void recover() {
		if (customer != null) {
			deletedCustomerList.remove(customer);
			if (filteredCustomerList != null && filteredCustomerList.contains(customer)) {
				filteredCustomerList.remove(customer);
			}
			customer.setDeleted(Resumable.UNDELETED);
			customerList.add(customer);
			customerService.update(customer);
			showResultTip("客户" + customer.getName() + "已成功恢复", FacesMessage.SEVERITY_INFO);
		} else {
			showResultTip("恢复失败", FacesMessage.SEVERITY_ERROR);
		}
	}

	/**
	 * 把回收站中全部的客户恢复正常
	 */
	public void recoverAll() {
		if (deletedCustomerList != null && deletedCustomerList.size() > 0) {
			for (Customer c : deletedCustomerList) {
				c.setDeleted(Resumable.UNDELETED);
				customerService.update(c);
				this.customerList.add(c);
			}
			deletedCustomerList.clear();
			if (filteredCustomerList != null)
				filteredCustomerList.clear();
			showResultTip("所有被删除客户已被成功恢复", FacesMessage.SEVERITY_INFO);
		} else {
			showResultTip("请至少选择一个客户", FacesMessage.SEVERITY_ERROR);
		}
	}

	/**
	 * 丢弃一个客户到回收站
	 */
	public void discardCustomer() {
		if (customer != null) {
			customerList.remove(customer);
			if (filteredCustomerList != null && filteredCustomerList.contains(customer)) {
				filteredCustomerList.remove(customer);
			}
			customer.setDeleted(Resumable.DELETED);
			deletedCustomerList.add(customer);
			customerService.update(customer);
			showResultTip("删除成功", FacesMessage.SEVERITY_INFO);
		} else {
			showResultTip("删除失败", FacesMessage.SEVERITY_ERROR);
		}
	}

	/**
	 * 批量丢弃到回收站
	 */
	public void discardBatch() {
		if (selectedCustomerList != null && selectedCustomerList.size() > 0) {
			for (Customer c : selectedCustomerList) {
				this.customerList.remove(c);
				c.setDeleted(Resumable.DELETED);
				customerService.update(c);
				this.deletedCustomerList.add(c);
			}
			selectedCustomerList.clear();
			if (filteredCustomerList != null)
				this.filteredCustomerList.clear();
			showResultTip("删除成功", FacesMessage.SEVERITY_INFO);
		} else {
			showResultTip("请至少选择一个客户", FacesMessage.SEVERITY_ERROR);
		}
	}

	/**
	 * 从没有被删除的客户列表中把所有被删除的客户转移到被删除的客户列表中
	 * 
	 * @param staffList
	 */
	public void removeDeleted(List<Customer> customerList) {
		if (customerList != null) {
			Iterator<Customer> iterator = customerList.iterator();
			while (iterator.hasNext()) {
				Customer c = iterator.next();
				if (c.getDeleted() == Resumable.DELETED) {
					iterator.remove();
					this.deletedCustomerList.add(c);
				}
			}
		}
	}

	/**
	 * 从员工或管理员上传的表格中批量导入客户信息
	 * 
	 * @param event
	 */
	public void addBatch(FileUploadEvent event) {
		try {
			InputStream is = event.getFile().getInputstream();
			int sheetIndex = 0;
			int offsetX = 0;
			int offsetY = 0;
			String[][] sourceData = new ExcelReader(is).readExcel(sheetIndex, offsetX, offsetY);
			customerService.addCustomers(Customer.getCustomers(sourceData));
			showResultTip("批量导入成功", FacesMessage.SEVERITY_INFO);
		} catch (Exception e) {
			String tip = "批量导入失败,请联系相关技术人员";
			showResultTip(tip, FacesMessage.SEVERITY_ERROR);
			e.printStackTrace();
		}
		new Thread(new Runnable() {
			public void run() {
				reSelect();
			}
		}).start();
	}

	/**
	 * 重新查询数据库,更新customerList
	 */
	public void reSelect() {
		customerList = customerService.findAll();
		this.deletedCustomerList = new ArrayList<>();
		removeDeleted(customerList);
	}

	/**
	 * 显示提示信息
	 * 
	 * @param tip
	 *            提示信息
	 * @param s
	 */
	public void showResultTip(String tip, FacesMessage.Severity s) {
		String realText = null;
		final int eachWordSize = 30;
		if (s != null && s == FacesMessage.SEVERITY_ERROR) {
			realText = "<div style='text-align:center;width:" + (tip.length() * eachWordSize)
					+ "px;'><span style='color:red;font-size:18px;'>" + tip + "</span>";
		} else if (s != null && s == FacesMessage.SEVERITY_INFO) {
			realText = "<div style='text-align:center;width:" + (tip.length() * eachWordSize)
					+ "px;'><span style='color:#003a6c;font-size:18px;'>" + tip + "</span>";
		} else if (s != null && s == FacesMessage.SEVERITY_WARN) {
			realText = "<div style='text-align:center;width:" + (tip.length() * eachWordSize)
					+ "px;'><span style='color:orange;font-size:18px;'>" + tip + "</span>";
		} else if (s != null && s == FacesMessage.SEVERITY_FATAL) {
			realText = "<div style='text-align:center;width:" + (tip.length() * eachWordSize)
					+ "px;'><span style='color:red;font-weight:bold;font-size:18px;'>" + tip + "</span>";
		}
		FacesMessage message = new FacesMessage(s, "提示", realText);
		RequestContext.getCurrentInstance().showMessageInDialog(message);
	}

	public ICustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomerService customerService) {
		this.customerService = customerService;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
	}

	public List<Customer> getFilteredCustomerList() {
		return filteredCustomerList;
	}

	public void setFilteredCustomerList(List<Customer> filteredCustomerList) {
		this.filteredCustomerList = filteredCustomerList;
	}

	public List<Customer> getSelectedCustomerList() {
		return selectedCustomerList;
	}

	public void setSelectedCustomerList(List<Customer> selectedCustomerList) {
		this.selectedCustomerList = selectedCustomerList;
	}

	public List<Customer> getDeletedCustomerList() {
		return deletedCustomerList;
	}

	public void setDeletedCustomerList(List<Customer> deletedCustomerList) {
		this.deletedCustomerList = deletedCustomerList;
	}

	/* These were written by ZY&YLX */
	/* These were written by ZY&YLX */
	/* These were written by ZY&YLX */
	/* These were written by ZY&YLX */
	/* These were written by ZY&YLX */

	@ManagedProperty(value = "#{taskService}")
	private ITaskService taskService;

	@ManagedProperty(value = "#{questionView}")
	private QuestionView questionView;
	/**
	 * 当点击查询某个客户信息的时候，这个对象存储要查看的客户的信息
	 */
	private Customer customerInfo = new Customer();

	/**
	 * 客户被访问的历史列表
	 */
	private List<Task> taskList = new ArrayList<Task>();

	/**
	 * 判断是否能够提交
	 */
	private boolean isAvailableToSubmit;
	/**
	 * 前端用来只输出一次的List
	 */
	private List<String> oneList = new ArrayList<String>();
	/**
	 * 记录问题集合的id，方便于存在数据库中
	 */
	private List<Long> questionIdList = new ArrayList<Long>();

	/**
	 * add after 存放回答的答案的list
	 */
	private List<String> answerList = new ArrayList<String>();
	/**
	 * 状态为已下达，未完成的tasklist
	 */
	private List<Task> taskListForParttimeStaff = new ArrayList<Task>();

	/**
	 * 题目列表
	 */
	private List<Question> questionList = new ArrayList<Question>();

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public ITaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(ITaskService taskService) {
		this.taskService = taskService;
	}

	public QuestionView getQuestionView() {
		return questionView;
	}

	public void setQuestionView(QuestionView questionView) {
		this.questionView = questionView;
	}

	public Customer getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(Customer customerInfo) {
		this.customerInfo = customerInfo;
	}

	public List<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}

	public boolean isAvailableToSubmit() {
		return isAvailableToSubmit;
	}

	public void setAvailableToSubmit(boolean isAvailableToSubmit) {
		this.isAvailableToSubmit = isAvailableToSubmit;
	}

	public List<String> getOneList() {
		return oneList;
	}

	public void setOneList(List<String> oneList) {
		this.oneList = oneList;
	}

	public List<Long> getQuestionIdList() {
		return questionIdList;
	}

	public void setQuestionIdList(List<Long> questionIdList) {
		this.questionIdList = questionIdList;
	}

	public List<Customer> getCustomerListForParttimeStaff() {
		return customerListForParttimeStaff;
	}

	public void setCustomerListForParttimeStaff(List<Customer> customerListForParttimeStaff) {
		this.customerListForParttimeStaff = customerListForParttimeStaff;
	}

	public List<String> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<String> answerList) {
		this.answerList = answerList;
	}

	public List<Task> getTaskListForParttimeStaff() {
		return taskListForParttimeStaff;
	}

	public void setTaskListForParttimeStaff(List<Task> taskListForParttimeStaff) {
		this.taskListForParttimeStaff = taskListForParttimeStaff;
	}

	public List<Question> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}

	public AnswerView getAnswerView() {
		return answerView;
	}

	public void setAnswerView(AnswerView answerView) {
		this.answerView = answerView;
	}

	public List<Long> getAnswerIdList() {
		return answerIdList;
	}

	public void setAnswerIdList(List<Long> answerIdList) {
		this.answerIdList = answerIdList;
	}

	/*************************************** 新内核 *******************************/

	/*--------------------------------------New core.Written by FZH-----------------------*/
	/**
	 * 检查员工是否选择了客户
	 */
	public void validateSelectedCustomerList() {
		if (selectedCustomerList == null || selectedCustomerList.isEmpty()) {
			showResultTip("请选择客户", FacesMessage.SEVERITY_ERROR);
		} else {
			RequestContext.getCurrentInstance().execute("PF('dlg_customizedTask').show()");
		}
	}

	/*--------------------------------------New core.Written by FZH-----------------------*/
	/** ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★ */
	/**
	 * 得到登录的兼职的客户列表
	 * 
	 * @return
	 */
	/*
	 * public List<Customer> findCustomerList() { List<Customer> list = new
	 * ArrayList<Customer>(); for (Task t : taskService.findAll()) { if
	 * (t.getParttimeStaff() != null) { final long ptsId =
	 * findTheParttimeStaffId(); if (t.getParttimeStaff().getId() == ptsId &&
	 * Task.ASSIGNED.equals(t.getStatus())) { list.add(t.getCustomer()); } } }
	 * return list; }
	 */

	/**
	 * 找出状态为已下达的任务
	 * 
	 * @return
	 */
	public List<Task> findAssignedTaskList() {
		List<Task> list = new ArrayList<Task>();
		for (Task t : taskService.findAll()) {
			if (t.getStatus().equals(Task.ASSIGNED)) {
				list.add(t);
			}
		}
		return list;
	}

	/**
	 * 通过客户和兼职的id得到相应任务的id 客户的id和兼职的id不需要作为参数
	 * 
	 * @return
	 */
	/*
	 * public long findTaskIdByCustomerIdAndParrtimeStaffId() { long taskId = 0;
	 * taskListForParttimeStaff = findAssignedTaskList(); for (Task task :
	 * taskListForParttimeStaff) { if (this.customerInfo.getId() ==
	 * task.getCustomer().getId() && findTheParttimeStaffId() ==
	 * task.getParttimeStaff().getId()) { taskId = task.getId(); break; } }
	 * return taskId; }
	 */

	/**
	 * 通过登录兼职的id得到相应的任务 （现阶段这个版本比较低 一个兼职最多只能接收一类任务 及数据库中最多有一个为已下达状态的任务）
	 * 
	 * @return
	 */

	private List<Task> allTaskList;
	private Task task;

	public void setTask(Task task) {
		this.task = task;
	}

	public Task getTask() {
		return task;
	}

	/**
	 * 通过客户的id得到问题
	 * 
	 * @param customerId
	 * @return
	 */
	/*
	 * public void findQuestionsByCsId(long customerId) { long parttimeStaffId =
	 * findTheParttimeStaffId(); if (allTaskList != null &&
	 * !allTaskList.isEmpty()) { for (Task task : allTaskList) { if
	 * (task.getParttimeStaff() != null && task.getCustomer() != null) { if
	 * (parttimeStaffId == task.getParttimeStaff().getId() && customerId ==
	 * task.getCustomer().getId() && task.getStatus().equals(Task.ASSIGNED)) {
	 * questionList = task.getQuestions(); this.task = task; } } }
	 * answerView.initAnswerList(customerInfo.getId(), this.task.getId(),
	 * questionList); }
	 */

	/**
	 * 通过指定的客户得到相应的任务
	 * 
	 * @param customer
	 * @return
	 */
	public Task findTaskByCustomer(Customer customer) {
		List<Task> list = findTasksByParttimeStaffId();
		if (list != null && !list.isEmpty()) {
			for (Task t : list) {
				for (Customer customer1 : t.getCustomers()) {
					if (customer1.getId() == customer.getId()) {
						return t;
					}
				}
			}
		}
		return null;
	}

	/************************************************************************************
	 * 8 dxl modify
	 ************************************************************************************/

	/**
	 * 找到答案
	 */
	public void findAnswer() {
		int cnt = 0;
		taskInfo = answerView.getTaskInfo();
		answerList.clear();
		findQuestionByTask();
		if (taskInfo != null && customer != null) {
			for (Answer answer : answerView.getAnswerService().findAllAnswers()) {
				if (answer.getTask_id() == taskInfo.getId() && customerInfo.getId() == answer.getCustomer_id()) {
					cnt++;
					answerList.add(answer.getAnswerContent());
				}
				if (cnt == questionList.size()) {
					break;
				}
			}
		}
	}

	/**
	 * 通过当前任务找到相应的客户
	 * 
	 * @return
	 */
	public List<Customer> findCustomersByTask() {
		findQuestionByTask();
		customerListForParttimeStaff.clear();
		task = answerView.getTaskInfo();
		System.err.println("CustomerView.findCustomersByTask()中当前任务信息：" + task.toString());
		if (task != null && task.getCustomers() != null) {
			for (Customer customer : task.getCustomers()) {
				System.err.println("xxxxxxxx" + customer);
				customerListForParttimeStaff.add(customer);
			}
		}
		return customerListForParttimeStaff;

	}

	/**
	 * 通过customerID找相应的问题
	 * 
	 */
	// public void findQuestionByCustomerId() {
	public void findQuestionByTask() {
		questionList.clear();

		Task task = answerView.getTaskInfo();// 这个任务是兼职用户点击：“客户反馈”-->“相关客户”时的任务，
												// 这样可以用，但是可能后期同时操作的兼职人数多了会出问题

		System.err.println("在CustomerView.findQuestionByTask()中任务的信息:" + task.toString());
		for (Question question : task.getQuestions())
			questionList.add(question);
		System.err.println("xxxx :   " + questionList.size() + "任务ID:  " + task.getId());
		answerView.initAnswerList(customerInfo.getId(), task.getId(), questionList);

		
		/*
		long parttimeStaffId = findTheParttimeStaffId();
		long customerId = customerInfo.getId();
		System.err.println("xxxxx" + parttimeStaffId + "xxxx" + customerId);
		if (allTaskList != null && !allTaskList.isEmpty()) {
			for (Task task : allTaskList) {
				if (task.getParttimeStaff() != null && task.getCustomers() != null) {
					if (parttimeStaffId == task.getParttimeStaff().getId() && Task.ASSIGNED.equals(task.getStatus())) {
						for (Question question : task.getQuestions()) {
							questionList.add(question);
						}
						System.err.println("xxxx :   " + questionList.size() + "任务ID:  " + task.getId());
						answerView.initAnswerList(customerInfo.getId(), task.getId(), questionList);

					}
				}
			}
		}*/

		/*
		 * answerView.initAnswerList(customerInfo.getId(), this.task.getId(),
		 * questionList);
		 */

	}

	/**
	 * 通过兼职的ID找到相应的任务
	 * 
	 * @return
	 */
	public List<Task> findTasksByParttimeStaffId() {
		List<Task> taskList = new ArrayList<>();
		long parttimeStaffId = findTheParttimeStaffId();
		if (allTaskList != null && !allTaskList.isEmpty()) {
			for (Task task : allTaskList) {
				if (task.getParttimeStaff() != null && task.getCustomers() != null) {
					if (parttimeStaffId == task.getParttimeStaff().getId() && Task.ASSIGNED.equals(task.getStatus())) {
						taskList.add(task);
					}
				}
			}
		}
		return taskList;
	}

	/****************************************************************************************
	 * 8 dxl modify end
	 **************************************************************************************/
	/**
	 * 得到登录的兼职的id
	 * 
	 * @return
	 */
	public long findTheParttimeStaffId() {
		Object object = null;
		ParttimeStaff parttimeStaff = null;
		object = session.getAttribute("loginedUser");
		if (object != null && object instanceof ParttimeStaff) {
			parttimeStaff = (ParttimeStaff) object;
			return parttimeStaff.getId();
		}
		return 0;
	}

	/**
	 * 提交指定的客户回答的问题
	 */
	/*
	 * public void submit(Customer customer) {
	 * answerView.saveAnswer(customer.getId()); System.err.println("客户信息：" +
	 * customer.toString()); Task t = findTaskByCustomer(customer); if (t !=
	 * null && answerView.isAvaliableToSubmit() &&
	 * answerView.isAvaliableToSubmitInsaveAnswer()) {
	 * System.err.println("CustomerView.submit()中客户列表的长度：" +
	 * customerListForParttimeStaff.size());
	 * System.err.println("CustomerView.submit()中当前客户信息：" +
	 * customer.toString()); System.err
	 * .println("CustomerView.submit()中当前客户在整个list中的下标：" +
	 * customerListForParttimeStaff.indexOf(customer));
	 * disables.set(customerListForParttimeStaff.indexOf(customer), true);
	 * t.setFinishedDate(DateUtil.dateToString(new Date()));
	 * t.setIsAvaluated(Task.UNAVALUATED); t.setStatus(Task.UNREVISIT);
	 * taskService.updateTask(t); showResultTip("提交成功",
	 * FacesMessage.SEVERITY_INFO); } else { showResultTip("有题目答案为空！提交失败!",
	 * FacesMessage.SEVERITY_ERROR); }
	 * 
	 * // zy // answerView.setAvaliableToSubmit(false); }
	 */

	/**
	 * 得到登录的兼职的所有的客户list
	 * 
	 * @return
	 */
	public List<Customer> findCustomerListByTaskId() {
		List<Customer> list = new ArrayList<Customer>();
		List<Task> taskList = findAssignedTaskList();
		long parttimeStaffId = findTheParttimeStaffId();
		if (taskList.size() != 0) {
			for (Task task : taskList) {
				if (task.getParttimeStaff() != null) {
					if (task.getParttimeStaff().getId() == parttimeStaffId) {
						/* list.add(task.getCustomer()); */
					}
				}
			}
		}
		return list;
	}

	private List<Boolean> disables;

	public void setDisables(List<Boolean> disables) {
		this.disables = disables;
	}

	public List<Boolean> getDisables() {
		return disables;
	}

	/**
	 * 判断这个任务是否已经反馈完成，如果反馈了就返回了true，否则返回false
	 * 
	 * @param task
	 * @return
	 */
	public boolean judgeIsFeedback() {
		Task task = answerView.getTaskInfo();
		// System.out.println("在CustomerView.judgeIsFeedback中任务的信息："+task.toString());
		boolean flag = false;
		if (task.getStatus().equals(Task.ASSIGNED))
			flag = false;
		else if (task.getStatus().equals(Task.UNREVISIT))
			flag = true;
		// System.out.println("在CustomerView.judgeIsFeedback中布尔值为："+flag);
		return flag;
	}

	/**
	 * 给每个任务的disable属性赋值，如果这个任务已经反馈了就不能继续反馈了
	 */
	/*
	 * public void initDisables() { disables = new ArrayList<>(); List<Task>
	 * tasks = findTasksByParttimeStaffId(); int length = tasks.size();
	 * 
	 * System.err.println("在CustomerView.initDisables()中任务的条数："+length);
	 * 
	 * for (int i = 0; i < length; i++) {
	 * 
	 * System.err.println("此时任务的信息："+tasks.get(i).toString());
	 * 
	 * if (tasks.get(i).getStatus() == Task.UNREVISIT) { disables.add(true);
	 * 
	 * System.err.println("添加了true");
	 * 
	 * } else if (tasks.get(i).getStatus() == Task.ASSIGNED) {
	 * disables.add(false);
	 * 
	 * System.err.println("添加了false");
	 * 
	 * } } }
	 */

	private List<Customer> selectedCustomersForTaskTemplate;

	public void setAllTaskList(List<Task> allTaskList) {
		this.allTaskList = allTaskList;
	}

	public List<Task> getAllTaskList() {
		return allTaskList;
	}

	public void setSelectedCustomersForTaskTemplate(List<Customer> selectedCustomersForTaskTemplate) {
		this.selectedCustomersForTaskTemplate = selectedCustomersForTaskTemplate;
	}

	public List<Customer> getSelectedCustomersForTaskTemplate() {
		return selectedCustomersForTaskTemplate;
	}
	/*
	 * ============================FZH=========================================
	 */

	/**
	 * 新增的客户
	 */
	private Customer newCustomer;
	private Date birthday;

	public void setNewCustomer(Customer newCustomer) {
		this.newCustomer = newCustomer;
	}

	public Customer getNewCustomer() {
		return newCustomer;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getBirthday() {
		return birthday;
	}

	public Task getTaskInfo() {
		return taskInfo;
	}

	public void setTaskInfo(Task taskInfo) {
		this.taskInfo = taskInfo;
	}

	/**
	 * 新增客户
	 */
	public void addCustomer() {
		if (newCustomer != null) {
			newCustomer.setBirthday(DateUtil.dateToString(this.birthday));
			customerService.addCustomer(newCustomer);
			showResultTip("添加成功", FacesMessage.SEVERITY_INFO);
			customerList.add(newCustomer);
			initNewCustomer();
		} else {
			showResultTip("添加失败", FacesMessage.SEVERITY_ERROR);
		}
	}

	public void initNewCustomer() {
		newCustomer = new Customer();
	}

}