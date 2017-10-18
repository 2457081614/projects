package com.meession.education.core.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.meession.education.AbstractSpringTest;
import com.meession.education.account.model.Role;
import com.meession.education.account.model.User;
import com.meession.education.account.repository.RoleRepository;
import com.meession.education.account.repository.UserRepository;
import com.meession.education.core.repository.CourseRepository;
import com.meession.education.core.repository.StudentRepository;
import com.meession.education.core.repository.TeacherRepository;

public class RepositoryTest extends AbstractSpringTest {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public CourseRepository getCourseRepository() {
		return courseRepository;
	}

	public void setCourseRepository(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	public StudentRepository getStudentRepository() {
		return studentRepository;
	}

	public void setStudentRepository(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	/**
	 * 添加15的课程，课程名字随机生产，编号为101000-101014
	 */
	@Test
	public void testSaveCourse() {
		// List<Course> courses = new ArrayList<Course>();
		int courseNo=101000;
		for (int i = 0; i < 15; i++) {
			Course course = Course.mock();
			course.setCourseNo(courseNo+""+(i+1));
			System.out.println(course);
			// courses.add(course);
			courseRepository.save(course);
		}
	}

	/**
	 * 添加15老师，默认登录密码为职工号，职工号为10100-10114（职工号自己想怎么定义就定义）
	 */
	@Test
	public void testSaveTeacher() {
		int workNo = 10100;
		List<Teacher> teachers = new  ArrayList<Teacher>();
		for (int i = 0; i < 15; i++) {
			Teacher teacher = Teacher.mock();
			teacher.setWorkerNo(i + workNo + "");
			// set role
			Role roleTeacher = roleRepository.findByName("ROLE_TEACHER");
			Role roleUser = roleRepository.findByName("ROLE_USER");
			Set<Role> roles = new HashSet<Role>();
			roles.add(roleUser);
			roles.add(roleTeacher);
			// create user
			User user = userRepository.findByUsername(teacher.getWorkerNo());
			if (null == user) {
				user = new User();
				user.setUsername(teacher.getWorkerNo());
				// 默认密码为教师的职工号
				user.setPassword(bCryptPasswordEncoder.encode(teacher.getWorkerNo()));
				user.setRoles(roles);
				userRepository.save(user);
			}
			teacher.setUser(user);
			teachers.add(teacher);
		}
		teacherRepository.save(teachers);
	}

	/**
	 * 添加30个学生，默认登录密码为学号，学号为2014550000-2014550029
	 */
	@Test
	public void testSaveStudent() {
		List<Student> students = new ArrayList<Student>();
		int stuNo = 2014550000;
		for (int i = 0; i < 20; i++) {
			Student student = Student.mock();
			student.setStuNo(stuNo + i + "");
			System.out.println(student);

			// set role
			Role roleStudent = roleRepository.findByName("ROLE_STUDENT");
			Role roleUser = roleRepository.findByName("ROLE_USER");
			Set<Role> roles = new HashSet<Role>();
			roles.add(roleUser);
			roles.add(roleStudent);

			User user = userRepository.findByUsername(student.getStuNo());
			if (null == user) {
				user = new User();
				user.setUsername(student.getStuNo());
				user.setPassword(bCryptPasswordEncoder.encode(student.getStuNo()));// 默认密码为学号
				user.setRoles(roles);
				userRepository.save(user);
			}
			student.setUser(user);
			students.add(student);
		}
		studentRepository.save(students);
	}

	/**
	 * 添加管理员账号：root   密码：root
	 * 管理员是可以给老师分配课程的，不可能老师自己选要教的课，只能是学校（管理员）安排
	 */
	@Test
	public void addManager() {
		User managerUser = new User();
		//managerUser.setUsername("sam123");
		 managerUser.setUsername("root");

		// set role
		Role roleManger = roleRepository.findByName("ROLE_MANAGER");
		Role roleUser = roleRepository.findByName("ROLE_USER");
		Set<Role> roles = new HashSet<Role>();
		roles.add(roleUser);
		roles.add(roleManger);

		User user = userRepository.findByUsername(managerUser.getUsername());
		if (null == user) {
			user = new User();
			user.setUsername(managerUser.getUsername());
			user.setPassword(bCryptPasswordEncoder.encode(managerUser.getUsername()));// 管理员默认密码为用户名
			user.setRoles(roles);
			userRepository.save(user);
		}
	}

}
