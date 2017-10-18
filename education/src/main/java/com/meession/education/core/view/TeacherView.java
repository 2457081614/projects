package com.meession.education.core.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import com.meession.education.account.service.SecurityService;
import com.meession.education.core.model.Course;
import com.meession.education.core.model.Score;
import com.meession.education.core.model.Student;
import com.meession.education.core.model.Teacher;
import com.meession.education.core.service.ScoreService;
import com.meession.education.core.service.TeacherService;

@ManagedBean
@ViewScoped
public class TeacherView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{scoreService}")
	private ScoreService scoreService;

	@ManagedProperty(value = "#{teacherService}")
	private TeacherService teacherService;

	@ManagedProperty(value = "#{courseView}")
	private CourseView courseView;

	@ManagedProperty(value = "#{studentView}")
	private StudentView studentView;

	@ManagedProperty(value = "#{securityService}")
	private SecurityService securityService;
	/**
	 * 一个老师对象，当点击查看，修改，删除时被操作的对象
	 */
	private Teacher teacher = new Teacher();

	/**
	 * 分数
	 */
	private int grade = 0;

	/**
	 * 所有的教师列表
	 */
	private List<Teacher> teacherList;

	/**
	 * 使用过滤条件时得到的若干条数据
	 */
	private List<Teacher> filteredTeacherList;

	/**
	 * 课程分配是选择的老师们
	 */
	private List<Teacher> selectedTeacherList;

	/**
	 * 给教师界面的course列表
	 */
	private List<Course> courseListForTeacher = new ArrayList<Course>();

	/**
	 * 给教师界面的学生列表
	 */
	private List<Student> studentListForTeacher;

	public String getCourseName() {
		return courseView.getCourse().getCourseName();
	}

	@PostConstruct
	public void init() {
		teacherList = teacherService.listAllTeachers();
		initCourseListForTeacher();
	}

	/**
	 * 得到登录的教师的每门课的学生列表
	 */
	public void initStudentListForTeacher() {
		studentListForTeacher = new ArrayList<Student>();
		Teacher teacher = getTheLoggedTeacher();// 当前登录的教师对象
		Course course = courseView.getCourse();// 当前点击的课程对象
		System.err.println("in teacherView initStudentListForTeacher : ");
		System.err.println("coure : " + course.getCourseName() + "  " + course.getCourseNo());
		System.err.println("teacher : " + teacher.getName() + "   " + teacher.getWorkerNo());
		List<Student> list = studentView.getStudentList();
		System.err.println("in teacherView initStudentListForTeacher studentList length : " + list.size());
		for (Student s : list) {
			for (Score score : s.getScores()) {
				System.err.println(
						"in teacherView initStudentListForTeacher cousre  ： " + score.getCourse().getCourseNo());
				if (score.getCourse().getCourseNo().equals(course.getCourseNo())) {
					studentListForTeacher.add(s);
					System.err.println("in teacherView initStudentListForTeacher student " + s.getStuName()
							+ "  by the course " + course.getCourseName());
					break;
				}
			}
		}
		if (studentListForTeacher != null)
			System.err.println("in teacherView initStudentListForTeacher the studentList length : "
					+ studentListForTeacher.size());

	}

	/**
	 * 初始化教师界面的course列表
	 */
	public void initCourseListForTeacher() {
		Teacher teacher = getTheLoggedTeacher();
		if (teacher == null)
			System.err.println("in teacherView initCourseListForTeacher method teacher is null");
		else {
			System.err.println("in teacherView initCourseListForTeacher method teacher : " + teacher.getWorkerNo()
					+ "   " + teacher.getName());

			List<Course> list = new ArrayList<Course>();
			for (Course c : teacher.getCourses())
				list.add(c);
			System.err.println("in teacherView initCourseListForTeacher method list size : " + list.size());
			courseListForTeacher = list;
			System.err.println(" length : " + courseListForTeacher.size());
		}
	}

	/**
	 * 得到当前登录的老师对象
	 * 
	 * @return
	 */
	public Teacher getTheLoggedTeacher() {
		Teacher t = null;
		String userName = securityService.findLoggedInUsername();
		System.err.println("in studentView getTheLoggedStudent method  loggend student :  " + userName);
		for (Teacher teacher : teacherList) {
			if (teacher.getWorkerNo().equals(userName))
				t = teacher;
		}
		return t;
	}

	/**
	 * 删除一个teacher
	 */
	public void delete() {
		System.err.println("           delete  a  teacher  ---- >>>   in teacherView.delete method");
		System.err.println(teacher.getName() + "\t" + teacher.getWorkerNo());
		showResultTip("测试删除成功！", FacesMessage.SEVERITY_INFO);
	}

	/**
	 * 通过一个课程对象得到教这门课的其中一个老师
	 * 
	 * @param course
	 * @return
	 */
	public Teacher getTeacherByCourse(Course course) {
		Teacher teacher = null;
		for (Teacher t : teacherList) {
			for (Course c : t.getCourses()) {
				if (c.getCourseNo().equals(course.getCourseNo()))
					return teacher = t;
			}
		}
		return teacher;
	}

	public Teacher getTeacherByCourse() {
		Teacher teacher = null;
		Course course = courseView.getCourse();
		for (Teacher t : teacherList) {
			for (Course c : t.getCourses()) {
				if (c.getCourseNo().equals(course.getCourseNo()))
					return teacher = t;
			}
		}
		return teacher;
	}

	public void testSelect() {
		Course course = courseView.getCourse();
		Student student = studentView.getTheLoggedStudent();
		if (student == null)
			showResultTip("选课失败,未登录！", FacesMessage.SEVERITY_ERROR);
		else {
			System.err.println("in teacherView testSelect method the teacher information : "
					+ getTeacherByCourse(course).getName());

			System.err.println("in teacherView testSelect method the student information : " + student.getStuName()
					+ "   " + student.getStuNo());
			System.err.println("  in teacherView testSelect cousre information ：" + course.getCourseNo() + "     "
					+ course.getCourseName());
			System.err.println("            ------      >>>  selected a course in courseView.tesSelect method");
			System.err.println("            ------      >>>  the selected course information : " + course.getCourseNo()
					+ "\t" + course.getCourseName());
			// 选课 start

			Score score = new Score();
			score.setCourse(course);
			score.setTeacher(getTeacherByCourse(course));
			scoreService.saveScore(score);

			Set<Score> scores = new HashSet<Score>();
			scores = student.getScores();
			scores.add(score);
			student.setScores(scores);
			studentView.getStudentService().saveStudent(student);

			// 选课 end
			showResultTip("选课成功！", FacesMessage.SEVERITY_INFO);
		}
	}

	/**
	 * 给学生打分
	 */
	public void valuate(Student student) {
		Teacher teacher = getTheLoggedTeacher();// 当前登录的教师对象
		Course course = courseView.getCourse();// 当前点击的课程对象
		System.err.println("in teacherView valuate :");
		System.err.println("cousre : " + course.getCourseName() + " " + course.getCourseNo() + "    teacher : "
				+ teacher.getWorkerNo() + "   student : " + student.getStuName());
		String s = "您打了" + grade + "分";
		showResultTip(s, FacesMessage.SEVERITY_INFO);
		// grade = 0;
	}
	
	/**
	 * 得到分数，学生界面的
	 * @param course
	 * @return
	 */
	public int getTheGrade(Course course){
		int a=0;
		Student student = studentView.getTheLoggedStudent();
		System.err.println("in teacherView getTheGrade :");
		System.err.println("student : "+ student.getStuNo());
		System.err.println("course : "+ course.getCourseNo()+ "  "+ course.getCourseName());
		for(Score s:student.getScores()){
			if(s.getCourse().getCourseNo().equals(course.getCourseNo())){
				return s.getScore();
			}
		}
		return a;
	}

	//没用
	public void putGrage(ActionEvent e){
		Score score = (Score) e.getComponent().getAttributes().get("score");
		int a = score.getScore();
		
	}
	
	public int getTheStudentGrade(Student student) {
		int g = 0;
		for (Score sc : student.getScores()) {
			Teacher teacher = getTheLoggedTeacher();// 当前登录的教师对象
			Course course = courseView.getCourse();// 当前点击的课程对象
			if (sc.getTeacher().getWorkerNo().equals(teacher.getWorkerNo())
					&& sc.getCourse().getCourseNo().equals(course.getCourseNo())) {
					g=sc.getScore();
					return g;
			}
		}
		return g;
	}

	/**
	 * 给课程分配一个或者多个教师
	 */
	public void assignCourseForTeacherList() {
		Course course = courseView.getCourse();
		Set<Course> courses = new HashSet<Course>();
		courses.add(course);
		System.err.println(
				"course information    in   teacherView   assignCourseForTeacherList  method  course information : "
						+ course.getCourseName() + "\t" + course.getCourseNo());
		List<Teacher> list = null;
		list = selectedTeacherList;
		System.err.println("teacher list length : " + selectedTeacherList.size());
		if (course != null) {
			System.err.println("course information in teacherView.assignCourseForTeacherList method : \n"
					+ course.getCourseName() + "\t" + course.getCourseNo());
			for (Teacher teacher : list) {
				course.setIsAssigned(1);
				courses.addAll(teacher.getCourses());// 把teacher之前教的课也加入进来，否则只会有最新安排的课
				teacher.setCourses(courses);
				courseView.getCourseService().saveCourse(course);
				teacherService.saveTeacher(teacher);
				System.err.println(teacher.getName() + "\t" + teacher.getWorkerNo());
			}
		}
		selectedTeacherList = null;
		showResultTip("分配成功!", FacesMessage.SEVERITY_INFO);
	}

	public boolean getTheDisableOfCourse(Course course) {
		if (course != null)
			System.err.println("in the teacherView getTheDisableOfCourse method course imformation  :   "
					+ course.getCourseName() + "    " + course.getIsAssigned());
		if (course.getIsAssigned() == 1)
			return true;
		else
			return false;
	}

	public TeacherService getTeacherService() {
		return teacherService;
	}

	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public List<Teacher> getTeacherList() {
		return teacherList;
	}

	public void setTeacherList(List<Teacher> teacherList) {
		this.teacherList = teacherList;
	}

	public List<Teacher> getFilteredTeacherList() {
		return filteredTeacherList;
	}

	public void setFilteredTeacherList(List<Teacher> filteredTeacherList) {
		this.filteredTeacherList = filteredTeacherList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Teacher> getSelectedTeacherList() {
		return selectedTeacherList;
	}

	public void setSelectedTeacherList(List<Teacher> selectedTeacherList) {
		this.selectedTeacherList = selectedTeacherList;
	}

	public CourseView getCourseView() {
		return courseView;
	}

	public void setCourseView(CourseView courseView) {
		this.courseView = courseView;
	}

	public StudentView getStudentView() {
		return studentView;
	}

	public void setStudentView(StudentView studentView) {
		this.studentView = studentView;
	}

	public SecurityService getSecurityService() {
		return securityService;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
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

	public ScoreService getScoreService() {
		return scoreService;
	}

	public void setScoreService(ScoreService scoreService) {
		this.scoreService = scoreService;
	}

	public List<Course> getCourseListForTeacher() {
		return courseListForTeacher;
	}

	public void setCourseListForTeacher(List<Course> courseListForTeacher) {
		this.courseListForTeacher = courseListForTeacher;
	}

	public List<Student> getStudentListForTeacher() {
		return studentListForTeacher;
	}

	public void setStudentListForTeacher(List<Student> studentListForTeacher) {
		this.studentListForTeacher = studentListForTeacher;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

}
