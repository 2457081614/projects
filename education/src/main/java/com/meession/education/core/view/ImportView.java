package com.meession.education.core.view;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import com.meession.education.core.model.Student;
import com.meession.education.core.model.Teacher;
import com.meession.education.core.service.StudentService;
import com.meession.education.core.service.TeacherService;

@ManagedBean
public class ImportView {

	@ManagedProperty("#{studentService}")
	private StudentService studentService;

	@ManagedProperty("#{teacherService}")
	private TeacherService teacherService;

	private UploadedFile uploadStudentFile;

	private UploadedFile uploadTeacherFile;

	// 自己添加
	// 返回所有学生集合
	private List<Student> allStudentList;

	// 返回所有老师集合
	private List<Teacher> allTeacherList;

	public List<Teacher> getAllTeacherList() {
		return allTeacherList;
	}

	public void setAllTeacherList(List<Teacher> allTeacherList) {
		this.allTeacherList = allTeacherList;
	}

	public StudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	public List<Student> getAllStudentList() {
		return allStudentList;
	}

	public void setAllStudentList(List<Student> allStudentList) {
		this.allStudentList = allStudentList;
	}

	public UploadedFile getUploadStudentFile() {
		return uploadStudentFile;
	}

	public void setUploadStudentFile(UploadedFile uploadStudentFile) {
		this.uploadStudentFile = uploadStudentFile;
	}

	public UploadedFile getUploadTeacherFile() {
		return uploadTeacherFile;
	}

	public void setUploadTeacherFile(UploadedFile uploadTeacherFile) {
		this.uploadTeacherFile = uploadTeacherFile;
	}

	public TeacherService getTeacherService() {
		return teacherService;
	}

	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	@PostConstruct
	public void init() {
		allStudentList = studentService.listAllStudent();
		allTeacherList = teacherService.listAllTeachers();
	}

	public void importStudents() {
		if (uploadStudentFile == null) {
			FacesMessage message = new FacesMessage("用户文件上传失败！", "导入失败");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			try {
				studentService.importStudents(uploadStudentFile.getInputstream());
				allStudentList = studentService.listAllStudent();
				System.err.println(allStudentList);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//showResultTip("上传成功", FacesMessage.SEVERITY_INFO);
	}

	public void importTeachers() {
		if (uploadTeacherFile == null) {
			FacesMessage message = new FacesMessage("用户文件上传失败！", "导入失败");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			try {
				teacherService.importTeachers(uploadTeacherFile.getInputstream());
				allTeacherList = teacherService.listAllTeachers();
				System.err.println(allTeacherList);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public StreamedContent downloadTemplate() {
		InputStream stream = FacesContext.getCurrentInstance().getExternalContext()
				.getResourceAsStream("/files/excel/importStudents.xlsx");
		StreamedContent sc = new DefaultStreamedContent(stream,
				"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "importUserTemplate.xlsx");
		return sc;
	}

	public StreamedContent downloadTemplate1() {
		InputStream stream = FacesContext.getCurrentInstance().getExternalContext()
				.getResourceAsStream("/files/excel/importTeachers.xlsx");
		StreamedContent sc = new DefaultStreamedContent(stream,
				"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "importUserTemplate.xlsx");
		return sc;
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
}