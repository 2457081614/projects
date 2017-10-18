package com.meession.education.core.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.meession.education.account.model.User;
import com.meession.education.common.util.RANDOM;

/**
 * 老师信息，是一个用户
 * @author sam
 *
 */
@Entity
public class Teacher {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * 职工号
	 */
	private String workerNo;
	/**
	 * 教师姓名
	 */
	private String name;
	/**
	 * 职称
	 */
	private String title;
	/**
	 * 邮箱地址
	 */
	private String email;
	/**
	 * 电话号码
	 */
	private String cellPhone;
	/**
	 * 家庭住址
	 */
	private String homeAddress;
	/**
	 * qq
	 */
	private String qq;
	/**
	 * 备注信息
	 */
	private String remark;
	/**
	 * 
	 */
	@ManyToMany(fetch=FetchType.EAGER) //set cann't 
	@JoinTable(name="teacher_course", joinColumns={@JoinColumn(name="t_id")},
	inverseJoinColumns={@JoinColumn(name="c_id")})
	private Set<Course> courses;
	
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;
	
	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWorkerNo() {
		return workerNo;
	}

	public void setWorkerNo(String workerNo) {
		this.workerNo = workerNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}
	
	public static Teacher mock(){
		Teacher teacher = new Teacher();
		teacher.setName(RANDOM.randomName());
		teacher.setCellPhone("13"+RANDOM.randomQQ());
		teacher.setEmail(RANDOM.randomQQ()+"@qq.com");
		teacher.setHomeAddress(RANDOM.randomHometown());
		teacher.setQq(RANDOM.randomQQ());
		return teacher;
	}

}
