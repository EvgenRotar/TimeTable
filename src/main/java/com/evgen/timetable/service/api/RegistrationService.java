package com.evgen.timetable.service.api;

import com.evgen.timetable.model.dto.student.StudentRegistrationRequest;
import com.evgen.timetable.model.dto.student.StudentResponse;
import com.evgen.timetable.model.dto.teacher.TeacherRegistrationRequest;
import com.evgen.timetable.model.dto.teacher.TeacherResponse;

public interface RegistrationService {

  StudentResponse registrationStudent(StudentRegistrationRequest studentRegistrationRequest);

  TeacherResponse registrationTeacher(TeacherRegistrationRequest teacherRegistrationRequest);

}