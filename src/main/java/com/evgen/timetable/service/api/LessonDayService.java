package com.evgen.timetable.service.api;

import com.evgen.timetable.exception.NotFoundException;
import com.evgen.timetable.model.dto.lesson.LessonDayRequest;
import com.evgen.timetable.model.dto.lesson.LessonDayResponse;
import com.evgen.timetable.model.dto.lesson.LessonDaySaveRequest;

public interface LessonDayService {

  LessonDayResponse getLessonDayById(Long lessonDayId) throws NotFoundException;

  LessonDayResponse addLessonDay(LessonDaySaveRequest lessonDaySaveRequest) throws NotFoundException;

  void deleteLessonDayById(Long lessonDayId) throws NotFoundException;

  void updateLessonDay(Long lessonDayId, LessonDayRequest lessonDayRequest) throws NotFoundException;

}