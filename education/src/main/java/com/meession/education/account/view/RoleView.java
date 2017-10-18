package com.meession.education.account.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.UploadedFile;

import com.meession.education.account.model.Role;
import com.meession.education.account.service.UserService;

@ManagedBean
@ViewScoped
public class RoleView {
	@ManagedProperty("#{userService}")
	private UserService userService;

	private UploadedFile uploadFile;

	private List<Role> roleList;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public UploadedFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(UploadedFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	@PostConstruct
	public void init() {
		roleList = userService.findAllRole();
	}
}
