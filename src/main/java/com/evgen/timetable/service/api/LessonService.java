package com.evgen.timetable.service.api;

import java.util.List;

import com.evgen.timetable.model.lesson.Lesson;
import com.evgen.timetable.model.lesson.LessonRequest;

public interface LessonService {

  List<Lesson> getAllLessons();

  Lesson getLessonById(Long id);

  Lesson addLesson(LessonRequest lessonRequest);

  void deleteLessonById(Long id);

  void updateLessonById(Long id, LessonRequest lessonRequest);

}
