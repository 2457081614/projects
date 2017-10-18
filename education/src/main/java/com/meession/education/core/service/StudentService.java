package com.meession.education.core.service;

import java.io.InputStream;
import java.util.List;

import com.meession.education.core.model.Student;

public interface StudentService {
	
	public void importStudents(InputStream input);
	
	public List<Student> listAllStudent();
	
	public void  saveStudent(Student student);
	
	public void deleteStudent(Student student);

}