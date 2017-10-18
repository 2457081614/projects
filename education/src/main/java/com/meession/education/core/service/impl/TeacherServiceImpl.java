package com.meession.education.core.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.meession.education.account.model.Role;
import com.meession.education.account.model.User;
import com.meession.education.account.repository.RoleRepository;
import com.meession.education.account.repository.UserRepository;
import com.meession.education.common.util.ExcelReader;

import com.meession.education.core.model.Teacher;
import com.meession.education.core.repository.TeacherRepository;
import com.meession.education.core.service.TeacherService;

@Service("teacherService")
public class TeacherServiceImpl implements TeacherService{

	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public TeacherRepository getTeacherRepository() {
		return teacherRepository;
	}

	public void setTeacherRepository(TeacherRepository teacherRepository) {
		this.teacherRepository = teacherRepository;
	}
	
	public RoleRepository getRoleRepository() {
		return roleRepository;
	}

	public void setRoleRepository(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public BCryptPasswordEncoder getbCryptPasswordEncoder() {
		return bCryptPasswordEncoder;
	}

	public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public void importTeachers(InputStream input) {
		try {
			//teacherRepository.deleteAll();
			ExcelReader excReader = new ExcelReader(input);
			String[][] strUsers = excReader.readExcel(0, 0, 1);
			Set<Teacher> teachers = new HashSet<Teacher>();
			for (int i = 0; i < strUsers.length; i++) {
				Teacher tea = teacherRepository.findByWorkerNo(strUsers[i][0]);
				if (tea == null) {
					tea=new Teacher();
					tea.setWorkerNo(strUsers[i][0]);
					tea.setName(strUsers[i][1]);
					tea.setTitle(strUsers[i][2]);
					tea.setEmail(strUsers[i][3]);
					tea.setCellPhone(strUsers[i][4]);
					tea.setHomeAddress(strUsers[i][5]);
					tea.setQq(strUsers[i][6]);
					tea.setRemark(strUsers[i][7]);
					teachers.add(tea);
					
					//set role
					Role roleTeacher = roleRepository.findByName("ROLE_TEACHER");
					Role roleUser = roleRepository.findByName("ROLE_USER");
					Set<Role> roles = new HashSet<Role>();
					roles.add(roleUser);
					roles.add(roleTeacher);
					
					//create user
					User user = userRepository.findByUsername(tea.getWorkerNo());
					if(null == user){
						user = new User();
						user.setUsername(tea.getWorkerNo());
						//默认密码为教师的职工号
						user.setPassword(bCryptPasswordEncoder.encode(tea.getWorkerNo()));
						user.setRoles(roles);
						userRepository.save(user);
					}
					
					tea.setUser(user);
					teachers.add(tea);
				}
			}
			teacherRepository.save(teachers);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Teacher> listAllTeachers() {
		return teacherRepository.findAll();
		
	}

	@Override
	public void saveTeacher(Teacher teacher) {
		teacherRepository.save(teacher);
	}
	

}
