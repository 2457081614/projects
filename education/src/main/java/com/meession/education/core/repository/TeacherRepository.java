package com.meession.education.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meession.education.core.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
	
	public Teacher findByWorkerNo(String name);
	
}
