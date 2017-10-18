package com.meession.education.core.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.meession.education.common.util.RANDOM;

/**
 * 课程信息
 * 
 * @author zy
 *
 */
@Entity
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	/**
	 * 课程号
	 */
	private String courseNo;
	/**
	 * 课程名称
	 */
	private String courseName;

	/**
	 * 开课时间
	 */
	private String courseTime;
	/**
	 * 上课地点
	 */
	private String courseAddress;
	/**
	 * 学时
	 */
	private int hours;
	/**
	 * 学分
	 */
	private int credit;
	/**
	 * 课程简介
	 */
	private String courseInfo;

	/**
	 * 课程是否被安排
	 */
	private int isAssigned=0;
	
	public int getIsAssigned() {
		return isAssigned;
	}

	public void setIsAssigned(int isAssigned) {
		this.isAssigned = isAssigned;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCourseNo() {
		return courseNo;
	}

	public void setCourseNo(String courseNo) {
		this.courseNo = courseNo;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseTime() {
		return courseTime;
	}

	public void setCourseTime(String courseTime) {
		this.courseTime = courseTime;
	}

	public String getCourseAddress() {
		return courseAddress;
	}

	public void setCourseAddress(String courseAddress) {
		this.courseAddress = courseAddress;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public String getCourseInfo() {
		return courseInfo;
	}

	public void setCourseInfo(String courseInfo) {
		this.courseInfo = courseInfo;
	}

	public static Course mock(){
		Course course = new Course();
		course.setCourseAddress("逸夫楼");
		course.setCourseName(RANDOM.randomCourse());
		course.setCredit(3);
		course.setHours(42);
		return course;
	}
	
}
