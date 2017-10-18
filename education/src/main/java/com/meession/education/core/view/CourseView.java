package com.meession.education.core.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import com.meession.education.core.model.Course;
import com.meession.education.core.model.Score;
import com.meession.education.core.model.Student;
import com.meession.education.core.service.CourseService;

@ManagedBean
@ViewScoped
public class CourseView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{courseService}")
	private CourseService courseService;

	@ManagedProperty(value = "#{studentView}")
	private StudentView studentView;
	// @ManagedProperty(value="{teacherView}")
	// private TeacherView teacherView;

	/**
	 * 当点击查看，修改，删除时被操作的对象
	 */
	private Course course = new Course();

	/**
	 * 课程信息
	 */
	// private Course courseInfo;

	/**
	 * 所有课程的列表
	 */
	private List<Course> courseList;

	/**
	 * 给学生界面的course列表
	 */
	private List<Course> courseListForStudentList = new ArrayList<Course>();

	/**
	 * 给学生界面的已选course列表
	 */
	private List<Course> selectedCourseListForStudentList = new ArrayList<Course>();
	
	/**
	 * 给教师界面的course列表
	 */
	private List<Course> courseListForTeacher = new ArrayList<Course>();
	
	/**
	 * 使用过滤条件得到的若干条数据
	 */
	private List<Course> filteredCourseList;

	@PostConstruct
	public void init() {
		courseList = courseService.listAllCourse();
		// 学生界面显示的都是安排了教师的课程
		for (Course c : courseList) {
			if (c.getIsAssigned() == 1)
				courseListForStudentList.add(c);
		}
		initSelectedCourseListForStudentList();
	}

	
	public void initSelectedCourseListForStudentList() {
		Student student = studentView.getTheLoggedStudent();
		if (student == null)
			System.err.println("in courseView initSelectedCourseListForStudentList method student is null");
		else {
			Set<Score> scores = student.getScores();
			for (Score score : scores) {
				selectedCourseListForStudentList.add(score.getCourse());
			}
		}
	}

	public boolean courseIsSelected(Course c) {
		System.err.println(
				"in courseView courseIsSelected method course : " + c.getCourseName() + "    " + c.getCourseNo());
		Student student = studentView.getTheLoggedStudent();
		if (student == null)
			System.err.println("in courseView courseIsSelected method student is null ");
		else {
			System.err.println("in courseView courseIsSelected method studen : "+ student.getStuNo());
			Set<Score> scores = student.getScores();
			for (Score score : scores) {
				if (score.getCourse().getCourseNo().equals(c.getCourseNo())) {
					System.err.println(" boolean : true");
					return true;
				}
			}
		}
		return false;
	}

	public void testDelete() {
		System.err.println("           ------    >>>>    delete a course in courseView.testDelete method");
		System.err.println(course.getCourseName() + "\t" + course.getCourseNo());
		courseService.delete(course);
		showResultTip("删除课程成功！",FacesMessage.SEVERITY_INFO);
	}

	public CourseService getCourseService() {
		return courseService;
	}

	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public List<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}

	public List<Course> getFilteredCourseList() {
		return filteredCourseList;
	}

	public void setFilteredCourseList(List<Course> filteredCourseList) {
		this.filteredCourseList = filteredCourseList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Course> getCourseListForStudentList() {
		return courseListForStudentList;
	}

	public void setCourseListForStudentList(List<Course> courseListForStudentList) {
		this.courseListForStudentList = courseListForStudentList;
	}

	public StudentView getStudentView() {
		return studentView;
	}

	public void setStudentView(StudentView studentView) {
		this.studentView = studentView;
	}

	/*
	 * public Course getCourseInfo() { return courseInfo; }
	 * 
	 * public void setCourseInfo(Course courseInfo) { this.courseInfo =
	 * courseInfo; }
	 */

	/*
	 * public TeacherView getTeacherView() { return teacherView; }
	 * 
	 * public void setTeacherView(TeacherView teacherView) { this.teacherView =
	 * teacherView; }
	 */
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

	public List<Course> getSelectedCourseListForStudentList() {
		return selectedCourseListForStudentList;
	}

	public void setSelectedCourseListForStudentList(List<Course> selectedCourseListForStudentList) {
		this.selectedCourseListForStudentList = selectedCourseListForStudentList;
	}

	public List<Course> getCourseListForTeacher() {
		return courseListForTeacher;
	}

	public void setCourseListForTeacher(List<Course> courseListForTeacher) {
		this.courseListForTeacher = courseListForTeacher;
	}
	
}
