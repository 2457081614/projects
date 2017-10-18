package com.meession.market.question.view;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;

import com.meession.market.common.util.ExcelReader;
import com.meession.market.question.entity.Question;
import com.meession.market.question.service.IQuestionService;
import com.meession.market.task.entity.Task;
import com.meession.market.task.view.TaskView;

@ManagedBean
@ViewScoped
public class QuestionView implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{questionService}")
	private IQuestionService questionService;
	/**
	 * 问题:储存待删、改、查对象的信息
	 */
	private Question questionInfo;
	/**
	 * 新问题
	 */
	private Question newQuestion;
	/**
	 * 问题库中的所有问题
	 */
	private List<Question> questionList;
	private List<Question> newQuestionList = new ArrayList<Question>();

	private List<Question> filteredQuestionList;
	private List<Question> selectedQuestionList;
	private int questionNo;
	/**
	 * 阶段问题集合 用来放具体阶段的问题
	 */
	private List<Question> questionStageList = new ArrayList<Question>();
	/**
	 * 任务阶段集合
	 */
	private List<String> taskStages;

	private Task newTask = new Task();

	/*------------------------Written by FZH----------------------------------------*/
	/**
	 * 初始化新问题集合的长度
	 */
	private static final int INITIALIZED_CAPACITY = 2;

	@PostConstruct
	public void init() {
		reSelect();
		initQuestionInfo();
		initTaskStages();
		/* initNewQuestionList(); */
		initQuestionStages();
		/* initQuestionStageList(); */

	}

	/*
	 * public void initNewQuestionList() { for (int i = 0; i
	 * <selectedQuestionList.size(); i++) { selectedQuestionList.add(new
	 * Question()); }
	 * 
	 * }
	 */

	/**
	 * 创建任务时增加新问题,并且讲新创建的问题加到集合newQuestionList中
	 * 
	 * @return newQuestionList
	 */
	public List<Question> addNewQuestionToNQL() {
		if (null != questionInfo && questionInfo.getQuestionContent() != null) {
			if(Task.FIRST_STAGE.equals(newTask.getStage())){
			     questionInfo.setStage(Question.FIRST_STAGE);
			}else if(Task.SECOND_STAGE.equals(newTask.getStage())){
			     questionInfo.setStage(Question.SECOND_STAGE);
			}else if(Task.THIRD_STAGE.equals(newTask.getStage())){
			     questionInfo.setStage(Question.THIRD_STAGE);
			}else if(Task.FOURTH_STAGE.equals(newTask.getStage())){
			     questionInfo.setStage(Question.FOURTH_STAGE);
			}
		     System.err.println("xxxxx"+newQuestion);
			questionService.add(questionInfo);
			newQuestionList.add(questionInfo);
			questionInfo = new Question();
			showResultTip("添加成功", FacesMessage.SEVERITY_INFO);
		} else {
			showResultTip("添加失败", FacesMessage.SEVERITY_ERROR);
		}

		return newQuestionList;
	}

	/**
	 * 判断是那一阶段的问题
	 */
	public List<Question> judgeQuestionStage() {
		System.err.println("进入了judge方法xxxxxxxxxxxx" + questionList.size()
				+ "xxxxx" + newTask.getStage());
		
			questionStageList.clear();
		
		switch (newTask.getStage()) {
		case Task.FIRST_STAGE:
			for (Question question : questionList) {
				if (Question.FIRST_STAGE.equals(question.getStage())) {
					questionStageList.add(question);
				}
			}
			break;
		case Task.SECOND_STAGE:
			for (Question question : questionList) {
				if (Question.SECOND_STAGE.equals(question.getStage())) {
					questionStageList.add(question);
				}
			}
			break;
		case Task.THIRD_STAGE:
			for (Question question : questionList) {
				if (Question.THIRD_STAGE.equals(question.getStage())) {
					questionStageList.add(question);
				}
			}
			break;
		case Task.FOURTH_STAGE:
			for (Question question : questionList) {
				if (Question.FOURTH_STAGE.equals(question.getStage())) {
					questionStageList.add(question);
				}
			}
			break;
		}
		return questionStageList;
	
	}

	/**
	 * 初始化任务阶段集合。
	 * 
	 * @param taskStages
	 */
	public void initTaskStages() {
		taskStages = new ArrayList<>();
		taskStages.add(Task.FIRST_STAGE);
		taskStages.add(Task.SECOND_STAGE);
		taskStages.add(Task.THIRD_STAGE);
		taskStages.add(Task.FOURTH_STAGE);
	}

	/**
	 * 从JS函数中得到参数
	 */
	public void getParams() {
		String qno = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("qno");
		this.questionNo = Integer.parseInt(qno);
	}

	public void openQTDlg(ActionEvent e) {
		System.out.println("size is"+questionStageList.size());
		if (null != questionStageList) {
			questionStageList.clear();
		}
	}

	/**
	 * 选择问题
	 */
	public List<Question> chooseQuestion() {

		System.err.println("xxxxxxxxxxxx进入选择问题xxxxxxxxx");
		if (null != selectedQuestionList) {
			for (Question newQuestion : selectedQuestionList) {
				System.err.println(newQuestion);
				newQuestionList.add(newQuestion);
				selectedQuestionList.set(questionNo,
						Question.cloneQusetion(newQuestion));
				if (questionList != null && questionList.contains(newQuestion)) {
					questionList.remove(newQuestion);
				}
				if (filteredQuestionList != null)
					filteredQuestionList.clear();
			}
			selectedQuestionList.clear();
			questionStageList.clear();
		} else {
			showResultTip("添加失败", FacesMessage.SEVERITY_ERROR);
		}
		return newQuestionList;

	}

	/**
	 * 清空当前行的问题内容
	 */
	/*
	 * public void clearQuestionContent() { if (newQuestion != null) { if
	 * (!StringUtils.isBlank(newQuestion.getQuestionContent())) {
	 * questionList.add(newQuestion);
	 * selectedQuestionList.set(selectedQuestionList.indexOf(newQuestion), new
	 * Question()); } else { showResultTip("没有可清除的内容",
	 * FacesMessage.SEVERITY_WARN); } } else { showResultTip("清除失败，请联系系统运维人员",
	 * FacesMessage.SEVERITY_ERROR); } }
	 */
	/**
	 * 扩展新增问题集合的长度
	 */
	/*
	 * public void lengthenList() {
	 * 
	 * if (selectedQuestionList != null) { final int primaryListSize
	 * =selectedQuestionList.size();
	 * System.err.println("xxxxxxxxx"+primaryListSize); int i = 0; for (i = 0; i
	 * < primaryListSize; i++) { Question q =selectedQuestionList.get(i); if
	 * (StringUtils.isBlank(q.getQuestionContent())) { String tip = "请先选择或编辑问题"
	 * + (i + 1); showResultTip(tip, FacesMessage.SEVERITY_ERROR); break; } } if
	 * (i == primaryListSize) { selectedQuestionList.add(new Question()); } }
	 * else { showResultTip("操作失败", FacesMessage.SEVERITY_ERROR); } }
	 */
	/**
	 * 行选择事件
	 * 
	 * @param event
	 */
	public void onRowSelect(SelectEvent event) {
		questionInfo = (Question) event.getObject();
	}

	/*------------------------Written by FZH----------------------------------------*/
	/**
	 * 重新查询数据库，更新questionList
	 */
	public void reSelect() {
		this.questionList = questionService.findAllQuestions();
		/* removeRepetition(); */
	}

	/**
	 * 从所有问题集合中，去除有外键的题目，得到没有外键的问题库
	 */
	public void removeRepetition() {
		// Iterator<Question> iterator = questionList.iterator();
		// while (iterator.hasNext()) {
		// Question question = iterator.next();
		// if (question.getTask() != null) {
		// iterator.remove();
		// }
		// }
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
			realText = "<div style='text-align:center;width:"
					+ (tip.length() * eachWordSize)
					+ "px;'><span style='color:red;font-size:18px;'>" + tip
					+ "</span>";
		} else if (s != null && s == FacesMessage.SEVERITY_INFO) {
			realText = "<div style='text-align:center;width:"
					+ (tip.length() * eachWordSize)
					+ "px;'><span style='color:#003a6c;font-size:18px;'>" + tip
					+ "</span>";
		} else if (s != null && s == FacesMessage.SEVERITY_WARN) {
			realText = "<div style='text-align:center;width:"
					+ (tip.length() * eachWordSize)
					+ "px;'><span style='color:orange;font-size:18px;'>" + tip
					+ "</span>";
		} else if (s != null && s == FacesMessage.SEVERITY_FATAL) {
			realText = "<div style='text-align:center;width:"
					+ (tip.length() * eachWordSize)
					+ "px;'><span style='color:red;font-weight:bold;font-size:18px;'>"
					+ tip + "</span>";
		}
		FacesMessage message = new FacesMessage(s, "提示", realText);
		RequestContext.getCurrentInstance().showMessageInDialog(message);
	}

	/*------------------------------Below YLX AND DXL-----------------------------------*/
	/**
	 * 批量导入问题
	 * 
	 * @param event
	 */
	public void addBatch(FileUploadEvent event) {
		try {
			InputStream is = event.getFile().getInputstream();
			int sheetIndex = 0;
			int offsetX = 0;
			int offsetY = 0;
			String[][] sourceData = new ExcelReader(is).readExcel(sheetIndex,
					offsetX, offsetY);
			questionService.addBatch(Question.getQuestions(sourceData));
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
	 * 修改问题
	 */
	public void updateQuestion() {
		System.err.println(questionInfo);
		questionService.updateQuestion(questionInfo);
		showResultTip("修改成功", FacesMessage.SEVERITY_INFO);
	}

	/**
	 * 删除单个问题
	 */
	public void deleteQuestion() {
		questionList.remove(questionInfo);
		questionService.deleteQuestionById(questionInfo.getId());

		showResultTip("删除成功", FacesMessage.SEVERITY_INFO);
		reSelect();
	}

	/**
	 * 批量删除
	 */
	public void deleteBatch() {
		List<Long> ids = new ArrayList<>();
		if (selectedQuestionList != null && selectedQuestionList.size() > 0) {
			for (Question question : selectedQuestionList) {
				questionList.remove(question);
				ids.add(question.getId());
			}

			showResultTip("删除成功", FacesMessage.SEVERITY_INFO);
			questionService.deleteBatch(ids);
			this.selectedQuestionList.clear();
		} else {
			showResultTip("请至少选择一个问题", FacesMessage.SEVERITY_ERROR);
		}
	}

	/**
	 * 创建问题
	 */
	public void createQuestion() {
		if (this.questionInfo.getPurpose() != null
				&& this.questionInfo.getStage() != null
				&& this.questionInfo.getQuestionContent() != null) {
			questionList.add(questionInfo);
			questionService.add(questionInfo);
			showResultTip("创建成功", FacesMessage.SEVERITY_INFO);
		} else {
			showResultTip("创建失败", FacesMessage.SEVERITY_ERROR);
		}

	}

	private List<String> questionStages;

	public void setQuestionStages(List<String> questionStages) {
		this.questionStages = questionStages;
	}

	public List<String> getQuestionStages() {
		return questionStages;
	}

	public void initQuestionStages() {
		questionStages = new ArrayList<>();
		questionStages.add(Question.FIRST_STAGE);
		questionStages.add(Question.SECOND_STAGE);
		questionStages.add(Question.THIRD_STAGE);
		questionStages.add(Question.FOURTH_STAGE);
	}

	public void initQuestionInfo() {
		questionInfo = new Question();
	}

	/*------------------------------upper YLX AND DXL-----------------------------------*/

	public IQuestionService getQuestionService() {
		return questionService;
	}

	public void setQuestionService(IQuestionService questionService) {
		this.questionService = questionService;
	}

	public Question getQuestionInfo() {
		return questionInfo;
	}

	public void setQuestionInfo(Question questionInfo) {
		this.questionInfo = questionInfo;
	}

	public Question getNewQuestion() {
		return newQuestion;
	}

	public void setNewQuestion(Question newQuestion) {
		this.newQuestion = newQuestion;
	}

	public List<Question> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}

	public List<Question> getFilteredQuestionList() {
		return filteredQuestionList;
	}

	public void setFilteredQuestionList(List<Question> filteredQuestionList) {
		this.filteredQuestionList = filteredQuestionList;
	}

	public void setQuestionNo(int questionNo) {
		this.questionNo = questionNo;
	}

	public int getQuestionNo() {
		return questionNo;
	}

	public List<Question> getSelectedQuestionList() {
		return selectedQuestionList;
	}

	public void setSelectedQuestionList(List<Question> selectedQuestionList) {
		this.selectedQuestionList = selectedQuestionList;
	}

	public List<Question> getNewQuestionList() {
		return newQuestionList;
	}

	public void setNewQuestionList(List<Question> newQuestionList) {
		this.newQuestionList = newQuestionList;
	}

	public List<Question> getQuestionStageList() {
		return questionStageList;
	}

	public void setQuestionStageList(List<Question> questionStageList) {
		this.questionStageList = questionStageList;
	}

	public List<String> getTaskStages() {
		return taskStages;
	}

	public void setTaskStages(List<String> taskStages) {
		this.taskStages = taskStages;
	}

	public Task getNewTask() {
		return newTask;
	}

	public void setNewTask(Task newTask) {
		this.newTask = newTask;
	}

}
