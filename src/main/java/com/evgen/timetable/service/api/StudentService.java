package com.evgen.timetable.service.api;

import com.evgen.timetable.model.student.StudentResponse;
import com.evgen.timetable.model.student.StudentUpdateRequest;

public interface StudentService {

  StudentResponse getStudentById(Long id);

  void updateGroupStudentById(Long id, StudentUpdateRequest studentUpdateRequest);

}