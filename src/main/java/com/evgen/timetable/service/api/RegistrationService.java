package com.evgen.timetable.service.api;

import com.evgen.timetable.model.student.StudentRegistrationRequest;
import com.evgen.timetable.model.student.StudentResponse;
import com.evgen.timetable.model.teacher.TeacherRegistrationRequest;
import com.evgen.timetable.model.teacher.TeacherResponse;

public interface RegistrationService {

  StudentResponse registrationStudent(StudentRegistrationRequest studentRegistrationRequest);

  TeacherResponse registrationTeacher(TeacherRegistrationRequest teacherRegistrationRequest);

}