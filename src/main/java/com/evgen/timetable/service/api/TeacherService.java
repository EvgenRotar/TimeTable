package com.evgen.timetable.service.api;

import com.evgen.timetable.model.teacher.TeacherResponse;

public interface TeacherService {

  TeacherResponse getTeacherById(Long id);

}