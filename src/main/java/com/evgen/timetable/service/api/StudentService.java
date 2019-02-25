package com.evgen.timetable.service.api;

import com.evgen.timetable.model.student.StudentResponse;

public interface StudentService {

  StudentResponse getStudentById(Long id);

}
