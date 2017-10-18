package com.meession.education.core.service;

import java.io.InputStream;
import java.util.List;


import com.meession.education.core.model.Teacher;

public interface TeacherService {
	public void importTeachers(InputStream input);

	public List<Teacher> listAllTeachers();

	public void saveTeacher(Teacher teacher);
}
