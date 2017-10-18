package com.meession.education.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meession.education.account.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
