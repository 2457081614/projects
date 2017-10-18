package com.meession.market.common.view;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


import com.meession.market.parttimestaff.entity.ParttimeStaff;
import com.meession.market.staff.entity.Staff;


@ManagedBean
@ViewScoped
public class PagesView implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String dynamicaPagesInclude;
	private static Map<String, String> pages;
	private HttpSession session;
	private Object loginedUser;
	/**
	 * 称呼:先生、女士
	 */
	private String appellation;
	/**
	 * 时间段:上午、下午
	 */
	private String timePeriod;

	static {
		pages = new HashMap<>();
		pages.put("welcome", "/pages/common/welcome.xhtml");
		pages.put("index", "index.xhtml");
		pages.put("bulletin", "bulletin.xhtml");
		pages.put("add_staff", "add_staff.xhtml");
		pages.put("staff_management", "staff_management.xhtml");
		pages.put("customer_management", "customer_management.xhtml");
		pages.put("add_customer", "add_customer.xhtml");
		pages.put("parttimeStaff_management", "parttimeStaff_management.xhtml");
		pages.put("add_parttimeStaff", "add_parttimeStaff.xhtml");
		pages.put("bulletin_management", "bulletin_management.xhtml");
		pages.put("create_bulletin", "create_bulletin.xhtml");
		pages.put("recycle_bin", "recycle_bin.xhtml");
		pages.put("more", "more.xhtml");
		pages.put("task_management", "task_management.xhtml");
		pages.put("create_task", "create_task.xhtml");
		pages.put("customerFeedback", "customerFeedback.xhtml");
		pages.put("taskList", "taskList.xhtml");
		pages.put("revisit", "revisit.xhtml");
		pages.put("modify_password", "modify_password.xhtml");
		pages.put("modify_info", "modify_info.xhtml");
		pages.put("question_management", "question_management.xhtml");
		pages.put("create_question", "create_question.xhtml");
		pages.put("export_data", "export_data.xhtml");

	}

	public void changePage(String pageName) {
		System.err.println("页面文件名:" + pageName);
		if (pageName == null || "".equals(pageName) || !pages.containsKey(pageName)) {
			dynamicaPagesInclude = "index.xhtml";
		} else {
			dynamicaPagesInclude = pages.get(pageName);
		}
	}


	@PostConstruct
	public void init() {
		session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		initLoginedUser(session);
	}

	/**
	 * 从session中获取当前session中登录的对象， 然后把该登录对象的值显示出来
	 * 
	 * @param session
	 */
	@SuppressWarnings("all")
	public void initLoginedUser(HttpSession session) {
		Object object = session.getAttribute("loginedUser");
		String gender = null;
		if (object != null) {
			if (object instanceof ParttimeStaff) {
				this.loginedUser = (ParttimeStaff) object;
				ParttimeStaff parttimeStaff = (ParttimeStaff) object;
				gender = parttimeStaff.getGender();
			} else if (object instanceof Staff) {
				this.loginedUser = (Staff) object;
				Staff staff = (Staff) object;
				gender = staff.getGender();
			}
			if (gender != null) {
				switch (gender) {
				case "男":
					this.appellation = "先生";
					break;

				case "女":
					this.appellation = "女士";
					break;

				default:
					this.appellation = "贵宾";
					break;
				}
			}
			if (new Date().getHours() < 6)
				this.timePeriod = "凌晨";
			else if (new Date().getHours() >= 6 && new Date().getHours() < 8)
				this.timePeriod = "早晨";
			else if (new Date().getHours() >= 8 && new Date().getHours() < 12)
				this.timePeriod = "上午";
			else if (new Date().getHours() >= 12 && new Date().getHours() < 14)
				this.timePeriod = "中午";
			else if (new Date().getHours() >= 14 && new Date().getHours() < 18)
				this.timePeriod = "下午";
			else if (new Date().getHours() >= 18 && new Date().getHours() < 24)
				this.timePeriod = "晚上";
		}
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public Object getLoginedUser() {
		return loginedUser;
	}

	public void setLoginedUser(Object loginedUser) {
		this.loginedUser = loginedUser;
	}

	public String getDynamicaPagesInclude() {
		return dynamicaPagesInclude;
	}

	public void setDynamicaPagesInclude(String dynamicaPagesInclude) {
		this.dynamicaPagesInclude = dynamicaPagesInclude;
	}

	public String getAppellation() {
		return appellation;
	}

	public void setAppellation(String appellation) {
		this.appellation = appellation;
	}

	public String getTimePeriod() {
		return timePeriod;
	}

	public void setTimePeriod(String timePeriod) {
		this.timePeriod = timePeriod;
	}

}
