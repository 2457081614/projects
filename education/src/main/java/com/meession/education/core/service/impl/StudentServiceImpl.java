package com.meession.education.core.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.meession.education.account.model.Role;
import com.meession.education.account.model.User;
import com.meession.education.account.repository.RoleRepository;
import com.meession.education.account.repository.UserRepository;
import com.meession.education.common.util.ExcelReader;
import com.meession.education.core.model.Student;
import com.meession.education.core.repository.StudentRepository;
import com.meession.education.core.service.StudentService;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder; 
	
	
	
	public BCryptPasswordEncoder getbCryptPasswordEncoder() {
		return bCryptPasswordEncoder;
	}

	public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public RoleRepository getRoleRepository() {
		return roleRepository;
	}

	public void setRoleRepository(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	public StudentRepository getStudentRepository() {
		return studentRepository;
	}

	public void setStudentRepository(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@Override
	public void importStudents(InputStream input) {

		try {
			//studentRepository.deleteAll();
			ExcelReader excReader = new ExcelReader(input);
			String[][] strUsers = excReader.readExcel(0, 0, 1);
			Set<Student> students = new HashSet<Student>();
			for (int i = 0; i < strUsers.length; i++) {
				Student stu = studentRepository.findByStuNo(strUsers[i][0]);
				if (stu == null) {
					stu = new Student();
                    stu.setStuNo(strUsers[i][0]);
					stu.setStuName(strUsers[i][1]);
					stu.setGendar(strUsers[i][2]);
					stu.setEmail(strUsers[i][3]);
					stu.setCellPhone(strUsers[i][4]);
					stu.setQq(strUsers[i][5]);
					stu.setWeixin(strUsers[i][6]);
					stu.setHomeAddress(strUsers[i][7]);
					stu.setGrade(Integer.valueOf(strUsers[i][8]));
					stu.setPost(strUsers[i][9]);
					stu.setSchool(strUsers[i][10]);
					stu.setDepartment(strUsers[i][11]);
					stu.setMajor(strUsers[i][12]);
					stu.setClassInfo(strUsers[i][13]);
					stu.setDomitory(strUsers[i][14]);
					stu.setRemark(strUsers[i][15]);
					//set role
					Role roleStudent = roleRepository.findByName("ROLE_STUDENT");
					Role roleUser = roleRepository.findByName("ROLE_USER");
					Set<Role> roles = new HashSet<Role>();
					roles.add(roleUser);
					roles.add(roleStudent);
					
					//create user
					User user = userRepository.findByUsername(stu.getStuNo());
					if(null == user){
						user = new User();
						user.setUsername(stu.getStuNo());
						user.setPassword(bCryptPasswordEncoder.encode(stu.getStuNo()));//默认密码为学号
						user.setRoles(roles);
						userRepository.save(user);
					}
					
					stu.setUser(user);
					students.add(stu);
				}
			}
			studentRepository.save(students);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Student> listAllStudent() {
		 return studentRepository.findAll();
	}

	@Override
	public void saveStudent(Student student) {
		studentRepository.save(student);
	}

	@Override
	public void deleteStudent(Student student) {
		studentRepository.delete(student);
	}

	

}