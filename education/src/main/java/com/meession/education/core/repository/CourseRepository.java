package com.meession.education.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meession.education.core.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{

}
