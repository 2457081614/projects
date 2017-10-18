package com.meession.education.core.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import com.meession.education.account.service.SecurityService;
import com.meession.education.account.service.UserService;
import com.meession.education.core.model.Student;
import com.meession.education.core.service.StudentService;

@ManagedBean
@ViewScoped
public class StudentView implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{studentService}")
	private StudentService studentService;

	@ManagedProperty(value = "#{userService}")
	private UserService userService;
	
	@ManagedProperty(value="#{securityService}")
	private SecurityService securityService;
	/**
	 * 一个学生对象，当点击查看，修改，删除时被操作的对象
	 */
	private Student student = new Student();

	/**
	 * 所有的学生list
	 */
	private List<Student> studentList;

	/**
	 * 给教师界面的学生列表
	 */
	private List<Student> studentListForTeacher = new ArrayList<Student>();
	
	/**
	 * 使用过滤条件时得到的若干条数据
	 */
	private List<Student> filteredStudentList;

	@PostConstruct
	public void init() {
		studentList = studentService.listAllStudent();
		// filteredStudentList.clear();//
		//studentListForTeacher=studentList;
	}

	public void initStudentListForTeacher(){
		
	}
	
	/**
	 * 得到当前登录的学生对象
	 * @return
	 */
	public Student getTheLoggedStudent(){
		Student s=null;
		String userName = securityService.findLoggedInUsername();
		System.err.println("in studentView getTheLoggedStudent method  loggend student :  "+userName);
		for(Student student:studentList){
			if(student.getStuNo().equals(userName))
				s=student;
		}
		return s;
	}

	public void delete() {
		System.err.println("  delete   one   student   ----------   >>>>>>   ");
		studentService.deleteStudent(student);
		showResultTip("删除成功！", FacesMessage.SEVERITY_INFO);
	}

	public StudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Student> getFilteredStudentList() {
		return filteredStudentList;
	}

	public void setFilteredStudentList(List<Student> filteredStudentList) {
		this.filteredStudentList = filteredStudentList;
	}

	public void showStudent() {
		if (student != null) {
			System.err.println(student.getStuName() + "  " + student.getStuNo());
		} else {
			System.out.println("null  of   student ");
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

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public SecurityService getSecurityService() {
		return securityService;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

	public List<Student> getStudentListForTeacher() {
		return studentListForTeacher;
	}

	public void setStudentListForTeacher(List<Student> studentListForTeacher) {
		this.studentListForTeacher = studentListForTeacher;
	}
}
