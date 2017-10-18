package com.meession.education.core.service;

import java.io.InputStream;
import java.util.List;

import org.hibernate.sql.Delete;

import com.meession.education.core.model.Course;


public interface CourseService {
	
	public void importCourses(InputStream input);
	
	public List<Course> listAllCourse();
	
	public void delete(Course course);
	
	public void saveCourse(Course course);
	
}
