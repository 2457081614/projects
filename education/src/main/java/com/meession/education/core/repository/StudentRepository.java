package com.meession.education.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meession.education.core.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{
	
	public Student findByStuNo(String name);
	
}
