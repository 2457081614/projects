package com.meession.market.answer.view;

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

import org.apache.commons.lang.StringUtils;
import org.primefaces.context.RequestContext;

import com.meession.market.answer.entity.Answer;
import com.meession.market.answer.service.IAnswerService;
import com.meession.market.common.dateutil.DateUtil;
import com.meession.market.parttimestaff.entity.ParttimeStaff;
import com.meession.market.question.entity.Question;
import com.meession.market.task.entity.Task;
import com.meession.market.task.service.ITaskService;

@ManagedBean
@ViewScoped
public class AnswerView implements Serializable {

	private static final long serialVersionUID = 1L;
	@ManagedProperty(value = "#{answerService}")
	private IAnswerService answerService;

	/**
	 * 数据库中所有的答案集合
	 */
	private List<Answer> allAnswerList;

	private Answer questionAnswer;

	/**
	 * 保存问题答案的集合。内容在每次保存答案到数据库的时候会被清空。 长度根据问题的个数来决定。<br/>
	 * 这个集合是给兼职用的。<br/>
	 * Pts:ParttimeStaff的简称
	 */
	private List<Answer> answerListForPts;
	/**
	 * 给员工看的答案集合
	 */
	private List<Answer> answerListForStaff;

	@ManagedProperty(value = "#{taskService}")
	private ITaskService taskService;

	private Task taskInfo;

	private HttpSession session;

	@PostConstruct
	public void init() {
		selectDb();
		session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}

	/**
	 * 得到登录的兼职
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
	 * 根据指定长度兼职的初始化问题集合
	 * 
	 * @param length
	 */
	public void initAnswerList(long customerId, long taskId, List<Question> questions) {
		answerListForPts = new ArrayList<>();
		if (allAnswerList == null || allAnswerList.isEmpty()) {
			if (questions != null && !questions.isEmpty()) {
				for (int i = 0; i < questions.size(); i++) {
					answerListForPts.add(new Answer());
				}
			}
		} else {
			if (questions != null && !questions.isEmpty()) {
				int index = 0;
				for (int i = 0; i < allAnswerList.size(); i++) {
					Answer answer = allAnswerList.get(i);
					if (answer.getCustomer_id() == customerId && answer.getTask_id() == taskId) {
						answerListForPts.add(answer);
					}
				}
				if (answerListForPts.size() < questions.size()) {
					List<Answer> list = answerListForPts;
					answerListForPts = new ArrayList<>();
					index = 0;
					for (Question q : questions) {
						if (index < list.size() && list.get(index).getQuestion_id() == q.getId()) {
							answerListForPts.add(list.get(index));
						} else {
							answerListForPts.add(new Answer());
						}
					}
				}
			}

		}
	}

	/**
	 * dxl 找到任务id
	 * 
	 * @return
	 */
	public long findTaskIdByParttimeStaff() {
		long parttimeStaffId = findTheParttimeStaffId();
		long taskId = 0;
		List<Task> taskList = taskService.findAll();
		if (taskList != null) {
			for (Task task : taskList) {
				if (task != null && Task.ASSIGNED.equals(task.getStatus()) && task.getParttimeStaff() != null
						&& parttimeStaffId == task.getParttimeStaff().getId()) {
					taskId = task.getId();
				}
			}
		}
		return taskId;

	}

	/**
	 * dxl 改变状态
	 */
	public void modifyTaskStatus() {

		Task task = new Task();

		task = this.taskInfo;
		
		boolean submitted = false;// 是否已经提交过
		boolean isAvaliableToSubmit = false;
		
		System.err.println("在AnswerView.modifyTaskStatus()中任务信息：" + task.toString());
		if (Task.UNREVISIT.equals(task.getStatus()))
			submitted = true;
		else {
			int questionLength = task.getQuestions().size();
			int customerLength = task.getCustomers().size();
			int length = questionLength * customerLength;
			System.err.println(
					"questionLength : " + questionLength + "customerLength: " + customerLength + "length : " + length);
			List<Answer> allAnswers = answerService.findAllAnswers();
			int cnt = 0;
			for (Answer answer : allAnswers) {
				if (answer.getTask_id() == task.getId()) {
					cnt++;
					if (cnt == length) {
						isAvaliableToSubmit = true;
						break;
					}
				}
			}
			System.err.println("在AnswerView中cnt等于：" + cnt);
		}
		// task=taskService.findById(findTaskIdByParttimeStaff());
		if (submitted) {
			showResultTip("提交失败 !任务已经提交过", FacesMessage.SEVERITY_ERROR);
		} else if (isAvaliableToSubmit) {
			task.setStatus(Task.UNREVISIT);
			task.setFinishedDate(DateUtil.dateToString(new Date()));
			taskService.updateTask(task);
			showResultTip("任务提交成功", FacesMessage.SEVERITY_INFO);
		} else {
			showResultTip("有客户没有保存! 任务提交失败", FacesMessage.SEVERITY_ERROR);
		}

	}

	/**
	 * 保存答案
	 */
	public void saveAnswer(long customerId, long taskId, List<Question> questions) {
		Task task = new Task();
		task = taskService.findById(findTaskIdByParttimeStaff());

		//// zyzy
		// taskIdCopy = taskId;

		// System.err.println("\t\t在AnswerView.saveAnswer()中answerListForPts的长度："+answerListForPts.size());
		System.err.println("answerListForPts == null ? :" + (answerListForPts == null));

		if (questions != null) {
			if (answerListForPts == null || answerListForPts.isEmpty()) {// 系统设计问题
				showResultTip("保存失败，请重新登录后再尝试", FacesMessage.SEVERITY_ERROR);

				// zy
				// isAvaliableToSubmit = false;

				return;
			} else {
				boolean isEmpty = validateEmptyList(answerListForPts);
				if (isEmpty) {// 兼职没有填写答案
					showResultTip("答案内容全为空，保存失败", FacesMessage.SEVERITY_ERROR);

					// zy
					// isAvaliableToSubmit = false;
				} else {
					for (int i = 0; i < questions.size(); i++) {
						if (StringUtils.isBlank(answerListForPts.get(i).getAnswerContent()))
							continue;
						System.err.println("......AnswerView.......SaveAnswer....." + "customerId:" + customerId
								+ "   taskId :" + taskId + "   questionId:" + questions.get(i).getId());
						answerListForPts.get(i).setCustomer_id(customerId);
						answerListForPts.get(i).setTask_id(taskInfo.getId());
						answerListForPts.get(i).setQuestion_id(questions.get(i).getId());
						if (allAnswerList.contains(answerListForPts.get(i)))
							answerService.updateAnswer(answerListForPts.get(i));
						else {
							allAnswerList.add(answerListForPts.get(i));
							answerService.addAnswer(answerListForPts.get(i));
						}
					}

					showResultTip("保存成功", FacesMessage.SEVERITY_INFO);

					/*
					 * // zy for (Answer a : answerListForPts) { if
					 * (a.getAnswerContent() == null ||
					 * a.getAnswerContent().trim().equals("")) {
					 * isAvaliableToSubmit = false; break; } else {
					 * isAvaliableToSubmit = true; } }
					 */
				}
				answerListForPts.clear();
			}
		}
	}

	/**
	 * 验证指定的答案集合是否全为空
	 * 
	 * @param list
	 */
	private boolean validateEmptyList(List<Answer> list) {
		if (list != null && !list.isEmpty()) {
			Iterator<Answer> iterator = list.iterator();
			while (iterator.hasNext()) {
				if (!StringUtils.isBlank(iterator.next().getAnswerContent())) {
					return false;
				}
			}
			return true;
		} else {
			return true;
		}
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

	public void selectDb() {
		allAnswerList = answerService.findAllAnswers();
	}

	public void setAllAnswerList(List<Answer> allAnswerList) {
		this.allAnswerList = allAnswerList;
	}

	public List<Answer> getAllAnswerList() {
		return allAnswerList;
	}

	public List<Answer> getAnswerListForPts() {
		return answerListForPts;
	}

	public void setAnswerListForPts(List<Answer> answerListForPts) {
		this.answerListForPts = answerListForPts;
	}

	public List<Answer> getAnswerListForStaff() {
		return answerListForStaff;
	}

	public void setAnswerListForStaff(List<Answer> answerListForStaff) {
		this.answerListForStaff = answerListForStaff;
	}

	public IAnswerService getAnswerService() {
		return answerService;
	}

	public void setAnswerService(IAnswerService answerService) {
		this.answerService = answerService;
	}

	// zy
	/**
	 * 兼职界面能否提交问卷
	 */
	private boolean isAvaliableToSubmit = false;

	// zy
	public boolean isAvaliableToSubmit() {
		return isAvaliableToSubmit;
	}

	// zy
	public void setAvaliableToSubmit(boolean isAvaliableToSubmit) {
		this.isAvaliableToSubmit = isAvaliableToSubmit;
	}

	//// zyzy
	/**
	 * 把保存答案的任务的id先记下来
	 */
	private long taskIdCopy;

	private boolean isAvaliableToSubmitInsaveAnswer = false;

	//// zyzy
	public void saveAnswer(long id) {
		isAvaliableToSubmitInsaveAnswer = false;
		for (Answer answer : allAnswerList) {

			if (answer.getCustomer_id() == id && answer.getTask_id() == taskIdCopy) {
				isAvaliableToSubmitInsaveAnswer = true;
				break;
			}
		}
		// taskIdCopy=0;
	}

	public long getTaskIdCopy() {
		return taskIdCopy;
	}

	public void setTaskIdCopy(long taskIdCopy) {
		this.taskIdCopy = taskIdCopy;
	}

	public boolean isAvaliableToSubmitInsaveAnswer() {
		return isAvaliableToSubmitInsaveAnswer;
	}

	public void setAvaliableToSubmitInsaveAnswer(boolean isAvaliableToSubmitInsaveAnswer) {
		this.isAvaliableToSubmitInsaveAnswer = isAvaliableToSubmitInsaveAnswer;
	}

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

	public Answer getQuestionAnswer() {
		return questionAnswer;
	}

	public void setQuestionAnswer(Answer questionAnswer) {
		this.questionAnswer = questionAnswer;
	}

	public Task getTaskInfo() {
		return taskInfo;
	}

	public void setTaskInfo(Task taskInfo) {
		this.taskInfo = taskInfo;
	}

}