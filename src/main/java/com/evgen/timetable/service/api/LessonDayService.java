package com.evgen.timetable.service.api;

import com.evgen.timetable.model.dto.lesson.LessonDayRequest;
import com.evgen.timetable.model.dto.lesson.LessonDayResponse;
import com.evgen.timetable.model.dto.lesson.LessonDaySaveRequest;

public interface LessonDayService {

  LessonDayResponse getLessonDayById(Long lessonDayId);

  LessonDayResponse addLessonDay(LessonDaySaveRequest lessonDaySaveRequest);

  void deleteLessonDayById(Long lessonDayId);

  void updateLessonDay(Long lessonDayId, LessonDayRequest lessonDayRequest);

}
