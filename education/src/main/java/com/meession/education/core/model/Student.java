package com.meession.education.core.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.meession.education.account.model.User;
import com.meession.education.common.util.RANDOM;

/**
 * 学生信息
 * 
 * @author zy
 */
@Entity
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	/**
	 * 学号
	 */
	private String stuNo;
	/**
	 * 学生姓名
	 */
	private String stuName;
	/**
	 * 性别
	 */
	private String gendar;
	/**
	 * 邮箱地址
	 */
	private String email;
	/**
	 * 电话号码
	 */
	private String cellPhone;
	/**
	 * qq
	 */
	private String qq;

	/**
	 * 微信
	 */
	private String weixin;
	
	/**
	 * 家庭住址
	 */
	private String homeAddress;
	
	/**
	 * 年级
	 */
	private int grade;
	
	/**
	 * 职务
	 */
	private String post="无";
	
	/**
	 * 学院名称
	 */
	private String school="湘潭大学";

	/**
	 * 系
	 */
	private String department;

	/**
	 * 专业信息
	 */
	private String major;

	/**
	 * 班级信息
	 */
	private String classInfo;
	
	/**
	 * 宿舍
	 */
	private String domitory;

	/**
	 * 备注信息
	 */
	private String remark="无";
	
	/**
	 * 学生选课
	 */
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="student_id")
	private Set<Score> scores;
	
	public Set<Score> getScores() {
		return scores;
	}

	public void setScores(Set<Score> scores) {
		this.scores = scores;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@OneToOne
	@JoinColumn(name="user_id")
	private User user;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStuNo() {
		return stuNo;
	}

	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getGendar() {
		return gendar;
	}

	public void setGendar(String gendar) {
		this.gendar = gendar;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	
	
	public static Student mock(){
		Student student = new Student();
		student.setStuName(RANDOM.randomName());
		student.setWeixin(RANDOM.randomQQ());
		student.setHomeAddress(RANDOM.randomHometown());
		student.setEmail(RANDOM.randomQQ()+"@qq.com");
		return student;
		
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getClassInfo() {
		return classInfo;
	}

	public void setClassInfo(String classInfo) {
		this.classInfo = classInfo;
	}

	public String getDomitory() {
		return domitory;
	}

	public void setDomitory(String domitory) {
		this.domitory = domitory;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
