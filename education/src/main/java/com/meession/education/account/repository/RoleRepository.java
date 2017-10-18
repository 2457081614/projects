package com.meession.education.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meession.education.account.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	 public Role findByName(String name);
}
