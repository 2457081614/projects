package com.meession.education.account.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.bean.ManagedProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.meession.education.account.model.Role;
import com.meession.education.account.model.User;
import com.meession.education.account.repository.RoleRepository;
import com.meession.education.account.repository.UserRepository;
import com.meession.education.account.service.SecurityService;
import com.meession.education.account.service.UserService;
import com.meession.education.common.util.ExcelReader;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void save(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles(new HashSet<>(roleRepository.findAll()));
		userRepository.save(user);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	/*********************17.4.21***********************/
	@ManagedProperty("#{securityService}")
	private SecurityService securityService;
	
	public SecurityService getSecurityService() {
		return securityService;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}
	
	/**
	 * 得到登录者的角色
	 * @return
	 */
	@Override
	public String findTheLoggiedRole(){
		String username = securityService.findLoggedInUsername();
		String stringRole = null;
		User user = findByUsername(username);
		for(Role role:user.getRoles()){
			System.err.println("role : " + role.getName());
			if(role.getName().equalsIgnoreCase("ROLE_STUDENT")){
				stringRole = "ROLE_STUDENT";
			} 
			if (role.getName().equalsIgnoreCase("ROLE_TEACHER")) {
				stringRole = "ROLE_TEACHER";
			}
			if (role.getName().equalsIgnoreCase("ROLE_MANAGER")) {
				stringRole = "ROLE_MANAGER";
			}
		}
		System.err.println("      in userServiceImpl findTheLoggiedRole method role : "+stringRole );
		return stringRole;
	}
	
	
	/********************17.4.21************************/
	@Override
	public List<User> findAllUser() {
		return userRepository.findAll();
	}

	@Override
	public List<Role> findAllRole() {
		return roleRepository.findAll();
	}

	@Override
	public void importUsers(InputStream in) {
		try {
			ExcelReader excReader = new ExcelReader(in);
			String[][] strUsers = excReader.readExcel(0, 0, 1);
			Set<User> users = new HashSet<User>();
			for (int i = 0; i < strUsers.length; i++) {
				User u = userRepository.findByUsername(strUsers[i][0]);
				if (u == null) {
					u=new User();
					u.setUsername(strUsers[i][0]);
					u.setPassword(strUsers[i][1]);

					// 密码加密
					u.setPassword(bCryptPasswordEncoder.encode(u.getPassword()));
					
					// 设置角色
					Set<Role> roles = new HashSet<Role>();
					String roleName = strUsers[i][2];
					if (roleName != null) {
						Role role = roleRepository.findByName(roleName);
						if (role == null) {
							role = new Role();
							role.setName(roleName);
							roleRepository.save(role);
						}
						roles.add(role);
					}
					u.setRoles(roles);
					users.add(u);
				}
			}
			userRepository.save(users);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
