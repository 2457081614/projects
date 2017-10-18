package com.meession.market.revisit.view;

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
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.primefaces.context.RequestContext;

import com.meession.market.customer.entity.Customer;
import com.meession.market.customer.service.ICustomerService;
import com.meession.market.parttimestaff.entity.ParttimeStaff;
import com.meession.market.parttimestaff.service.IParttimeStaffService;
import com.meession.market.revisit.entity.Revisit;
import com.meession.market.revisit.service.IRevisitService;
import com.meession.market.task.entity.Task;
import com.meession.market.task.service.ITaskService;

@ManagedBean
@ViewScoped
public class RevisitView implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 回访者选择的多选框里的内容。
	 */
	private String[] impressions;
	private List<String> staticContents;
	@ManagedProperty(value = "#{revisitService}")
	private IRevisitService revisitService;
	@ManagedProperty(value = "#{customerService}")
	private ICustomerService customerService;
	@ManagedProperty(value = "#{parttimeStaffService}")
	private IParttimeStaffService parttimeStaffService;
	@ManagedProperty(value = "#{taskService}")
	private ITaskService taskService;

	/**
	 * 所有的回访集合
	 */
	private List<Revisit> revisitList;
	/**
	 * 被过滤的回访集合
	 */
	private List<Revisit> filteredRevisitList;
	/**
	 * 编辑某一个回访时，存储被编辑的回访
	 */
	private Revisit revisit;

	private Customer customer;

	private ParttimeStaff parttimeStaff;

	private List<Boolean> disableds;

	private HttpSession session;

	/**
	 * 登录的兼职
	 */
	private ParttimeStaff loginedParttimeStaff;

	@PostConstruct
	public void init() {
		session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		reselect();
		initStaticContents();
	}

	/**
	 * 从session中得到登录的兼职
	 */
	public void initLoginedPtsFromSession() {
		Object obj = session.getAttribute("loginedUser");
		if (obj instanceof ParttimeStaff) {
			loginedParttimeStaff = (ParttimeStaff) obj;
		} else {
			loginedParttimeStaff = new ParttimeStaff();
		}
	}

	/**
	 * 提交评价
	 */
	public void commitRevisit() {
		if (revisit != null) {
			if (StringUtils.isBlank(revisit.getRealness())) {
				showResultTip("提交失败，校园代理的工作真实性必须填写", FacesMessage.SEVERITY_ERROR);
			} else if ((impressions == null || impressions.length == 0)
					&& StringUtils.isBlank(revisit.getImpressionFromCustomer())) {
				showResultTip("提交失败，客户对校园代理的评价必须填写", FacesMessage.SEVERITY_ERROR);
			} else {
				String checkboxContent = getCheckboxContent(impressions);
				String inputTextareaContent = revisit.getImpressionFromCustomer();
				if (inputTextareaContent == null)
					inputTextareaContent = "";
				revisit.setImpressionFromCustomer(checkboxContent + inputTextareaContent);
				revisit.setHasAppraised(Revisit.APPRAISED);
				disableds.set((int) revisit.getId() - 1, true);
				revisitService.updateRevisitRecord(revisit);
				changeTaskStatusByRevisit(revisit);
				showResultTip("提交成功", FacesMessage.SEVERITY_INFO);
			}
		} else {
			showResultTip("提交失败", FacesMessage.SEVERITY_ERROR);
		}
	}
	
	
	/**
	 * 通过指定的回访改变任务状态
	 * @param revisit
	 */
	public void changeTaskStatusByRevisit(Revisit revisit){
		final long taskId = revisit.getTask_id();
		Task task = taskService.findById(taskId);
		if(task != null){
			task.setStatus(Task.FINISHED);
			taskService.updateTask(task);
		}
	}

	/**
	 * 得到复选框中的内容
	 * 
	 * @param impressions
	 * @return
	 */
	public String getCheckboxContent(String[] impressions) {
		StringBuffer result = new StringBuffer();
		if (impressions != null && impressions.length != 0) {
			for (String s : impressions) {
				if (!StringUtils.isBlank(s)) {
					result.append(s + "，");
				}
			}
			// 去掉最后一个词的逗号加个句号
			return result.toString().substring(0, result.length() - 1) + "。";
		}
		return result.toString();
	}

	/**
	 * 通过指定的回访得到客户的id
	 * 
	 * @param revisit
	 */
	public Customer getCustomerByRvtId(Revisit revisit) {
		if (revisit != null) {
			customer = customerService.findById(revisit.getCustomerId());
		} else {
			customer = new Customer();
		}
		return customer;
	}

	/**
	 * 通过制定的回访得到兼职
	 * 
	 * @param revisit
	 */
	public ParttimeStaff getParttimeStaffByRvtId(Revisit revisit) {
		if (revisit != null) {
			parttimeStaff = parttimeStaffService.findParttimeStaffById(revisit.getParttimeStaffId());
		} else {
			parttimeStaff = new ParttimeStaff();
		}
		return parttimeStaff;
	}

	/**
	 * 初始化静态内容:即评价多选框。
	 */
	public void initStaticContents() {
		staticContents = new ArrayList<>();
		staticContents.add("积极主动");
		staticContents.add("懒惰拖沓");
		staticContents.add("善于沟通");
		staticContents.add("不善交际");
		staticContents.add("委婉圆滑");
		staticContents.add("直来直去");
		staticContents.add("平易近人");
		staticContents.add("敬而远之");
		staticContents.add("心平气和");
		staticContents.add("心性急躁");
	}

	/**
	 * 重新查询数据库
	 */
	public void reselect() {
		revisitList = revisitService.findAll();
		initAbleds(revisitList);
		getLoginedPtsRevisits();
	}

	/**
	 * 得到登录的兼职的要做的回访
	 */
	public void getLoginedPtsRevisits() {
		if (revisitList != null) {
			initLoginedPtsFromSession();
			Iterator<Revisit> iterator = revisitList.iterator();
			while (iterator.hasNext()) {
				if (iterator.next().getRevisiterId() != loginedParttimeStaff.getId()) {
					iterator.remove();
				}
			}
		}
	}

	public void initAbleds(List<Revisit> revisits) {
		disableds = new ArrayList<>();
		if (revisits != null && !revisits.isEmpty()) {
			for (Revisit r : revisits) {
				if (r.getHasAppraised() == Revisit.APPRAISED) {
					disableds.add(true);
				} else {
					disableds.add(false);
				}
			}
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

	public void setImpressions(String[] impressions) {
		this.impressions = impressions;
	}

	public String[] getImpressions() {
		return impressions;
	}

	public List<String> getStaticContents() {
		return staticContents;
	}

	public void setStaticContents(List<String> staticContents) {
		this.staticContents = staticContents;
	}

	public IRevisitService getRevisitService() {
		return revisitService;
	}

	public void setRevisitService(IRevisitService revisitService) {
		this.revisitService = revisitService;
	}

	public List<Revisit> getRevisitList() {
		return revisitList;
	}

	public void setRevisitList(List<Revisit> revisitList) {
		this.revisitList = revisitList;
	}

	public List<Revisit> getFilteredRevisitList() {
		return filteredRevisitList;
	}

	public void setFilteredRevisitList(List<Revisit> filteredRevisitList) {
		this.filteredRevisitList = filteredRevisitList;
	}

	public Revisit getRevisit() {
		return revisit;
	}

	public void setRevisit(Revisit revisit) {
		this.customer = getCustomerByRvtId(revisit);
		this.parttimeStaff = getParttimeStaffByRvtId(revisit);
		this.revisit = revisit;
	}

	public ICustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomerService customerService) {
		this.customerService = customerService;
	}

	public IParttimeStaffService getParttimeStaffService() {
		return parttimeStaffService;
	}

	public void setParttimeStaffService(IParttimeStaffService parttimeStaffService) {
		this.parttimeStaffService = parttimeStaffService;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public ParttimeStaff getParttimeStaff() {
		return parttimeStaff;
	}

	public void setParttimeStaff(ParttimeStaff parttimeStaff) {
		this.parttimeStaff = parttimeStaff;
	}

	public List<Boolean> getDisableds() {
		return disableds;
	}

	public void setDisableds(List<Boolean> disableds) {
		this.disableds = disableds;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public ParttimeStaff getLoginedParttimeStaff() {
		return loginedParttimeStaff;
	}

	public void setLoginedParttimeStaff(ParttimeStaff loginedParttimeStaff) {
		this.loginedParttimeStaff = loginedParttimeStaff;
	}

	/**************************************************************************/

	/**
	 * 员工界面的回访对象
	 */
	private Revisit revisitForStaff = new Revisit();
	/**
	 * 选择的四字用一个字符串显示
	 */
	private String evaluationOfSelectedString;

	public String getEvaluationOfSelectedString() {
		return evaluationOfSelectedString;
	}

	public void setEvaluationOfSelectedString(String evaluationOfSelectedString) {
		this.evaluationOfSelectedString = evaluationOfSelectedString;
	}

	public Revisit getRevisitForStaff() {
		return revisitForStaff;
	}

	public void setRevisitForStaff(Revisit revisitForStaff) {
		this.revisitForStaff = revisitForStaff;
	}
	

	public ITaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(ITaskService taskService) {
		this.taskService = taskService;
	}

	/**
	 * 对兼职回访记录中的文字输入
	 */
	private String evaluationOfWords;

	public String getEvaluationOfWords() {
		return evaluationOfWords;
	}

	public void setEvaluationOfWords(String evaluationOfWords) {
		this.evaluationOfWords = evaluationOfWords;
	}

	/**
	 * 对兼职的回访记录的评价进行处理
	 */
	public void findTheImpression() {
		String impression = this.revisitForStaff.getImpressionFromCustomer();
		String[] ss = impression.split("，");
		for (int i = 0; i < ss.length - 2; i++) {
			this.evaluationOfSelectedString += ss[i];
			this.evaluationOfSelectedString += "，";
		}
		String end = ss[ss.length - 1];
		String[] strings = end.split("。");

		this.evaluationOfSelectedString += strings[0];
		this.evaluationOfSelectedString += ".";
		this.evaluationOfWords = strings[1];
	}

	/**
	 * 通过任务的id得到这个任务的回访记录对象给revisitForStaff
	 * 
	 * @param taskId
	 * @return
	 */
	public void findTheRevistOfTask(long taskId) {

		this.evaluationOfWords = "";
		this.evaluationOfSelectedString = "";
		this.revisitForStaff = null;
		List<Revisit> allRevisitList = revisitService.findAll();
		for (Revisit r : allRevisitList) {
			if (r.getTask_id() == taskId) {
				this.revisitForStaff = r;
				break;
			}
		}
		if (this.revisitForStaff != null) {
			findTheImpression();
		}
	}
	/**************************************************************************/
}
