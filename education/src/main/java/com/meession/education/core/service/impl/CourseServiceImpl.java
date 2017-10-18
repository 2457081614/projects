package com.meession.education.core.service.impl;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meession.education.core.model.Course;
import com.meession.education.core.repository.CourseRepository;
import com.meession.education.core.service.CourseService;

@Service("courseService")
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepository courseRepository;

	@Override
	public void importCourses(InputStream input) {
		// ...

	}

	@Override
	public List<Course> listAllCourse() {
		// TODO Auto-generated method stub
		return courseRepository.findAll();
	}

	public CourseRepository getCourseRepository() {
		return courseRepository;
	}

	public void setCourseRepository(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	@Override
	public void saveCourse(Course course) {
		courseRepository.save(course);
	}

	@Override
	public void delete(Course course) {
		courseRepository.delete(course);
	}

}
