package com.meession.education.account.view;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import com.meession.education.account.model.User;
import com.meession.education.account.service.UserService;
import com.meession.education.core.model.Teacher;

@ManagedBean
@ViewScoped
public class UserView {
	@ManagedProperty("#{userService}")
	private UserService userService;

	public List<User> userList;

	private UploadedFile uploadFile;

	//////
	public Teacher returnTeacherLoggied(){
		//Teacher teacher = 
		return null;
	}

	public Object returnTheLogginer() {
		Teacher teacher = new Teacher();
		return teacher;
	}

	////
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public UploadedFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(UploadedFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	@PostConstruct
	public void init() {
		userList = userService.findAllUser();
		System.out.println(userList.get(0).getRoles());
	}

	public void importUsers() {
		if (uploadFile == null) {
			FacesMessage message = new FacesMessage(" 导入失败", "用户文件上传失败！");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			try {
				userService.importUsers(uploadFile.getInputstream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public StreamedContent downloadTemplate() {
		InputStream stream = FacesContext.getCurrentInstance().getExternalContext()
				.getResourceAsStream("/files/excel/importUser.xlsx");
		StreamedContent sc = new DefaultStreamedContent(stream,
				"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "importUserTemplate.xlsx");
		return sc;
	}

}
