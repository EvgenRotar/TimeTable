package com.evgen.timetable.service.api;

import java.util.List;

import com.evgen.timetable.exception.NotFoundException;
import com.evgen.timetable.model.entity.Lesson;
import com.evgen.timetable.model.dto.lesson.LessonRequest;

public interface LessonService {

  List<Lesson> getAllLessons();

  Lesson getLessonById(Long id) throws NotFoundException;

  Lesson addLesson(LessonRequest lessonRequest);

  void deleteLessonById(Long id) throws NotFoundException;

  void updateLessonById(Long id, LessonRequest lessonRequest) throws NotFoundException;

}