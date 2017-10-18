package com.meession.education.core.view;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.meession.education.account.model.Role;
import com.meession.education.account.model.User;
import com.meession.education.account.service.SecurityService;
import com.meession.education.account.service.UserService;

@ManagedBean
@ViewScoped
public class MenuView {

	@ManagedProperty("#{securityService}")
	private SecurityService securityService;
	
	@ManagedProperty("#{userService}")
	private UserService userService;

	private boolean roleStudent;

	private boolean roleTeacher;

	private boolean roleUser;

	private boolean roleManager;

	public boolean isRoleManager() {
		return roleManager;
	}

	public void setRoleManager(boolean roleManager) {
		this.roleManager = roleManager;
	}

	public SecurityService getSecurityService() {
		return securityService;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public boolean isRoleStudent() {
		return roleStudent;
	}

	public boolean isRoleUser() {
		return roleUser;
	}

	public void setRoleUser(boolean roleUser) {
		this.roleUser = roleUser;
	}

	public void setRoleStudent(boolean roleStudent) {
		this.roleStudent = roleStudent;
	}

	public boolean isRoleTeacher() {
		return roleTeacher;
	}

	public void setRoleTeacher(boolean roleTeacher) {
		this.roleTeacher = roleTeacher;
	}

	@PostConstruct
	public void init() {
		/*******17.4.21*******/
		//System.err.println(userService.findTheLoggiedRole());
		
		roleStudent = false;
		roleTeacher = false;
		roleUser = false;
		roleManager = false;
		String username = securityService.findLoggedInUsername();
		User user = userService.findByUsername(username);
		for (Role role : user.getRoles()) {

			System.err.println("role : " + role.getName());

			if (role.getName().equalsIgnoreCase("role_student")) {
				roleStudent = true;
			}
			if (role.getName().equalsIgnoreCase("role_teacher")) {
				roleTeacher = true;
			}
			if (role.getName().equalsIgnoreCase("role_user")) {
				roleUser = true;
			}
			if (role.getName().equalsIgnoreCase("role_manager")) {
				roleManager = true;
			}
		}
	}

	/**
	 * 退出登录
	 * 
	 * @return
	 */
	public String logoutUrl() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.
				getCurrentInstance().getExternalContext()
				.getRequest();
		String string = request.getContextPath() + "/logout";
		System.err.println(" logout URL :" + string);
		return string;
	}

}
