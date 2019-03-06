package com.evgen.timetable.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.evgen.timetable.model.entity.Lesson;
import com.evgen.timetable.model.dto.lesson.LessonRequest;
import com.evgen.timetable.repository.LessonRepository;
import com.evgen.timetable.service.api.LessonService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Transactional
public class LessonServiceImpl implements LessonService {

  private final LessonRepository lessonRepository;

  private Lesson getLessonByIdOrThrowException(Long id) {
    return lessonRepository.findById(id)
        .orElseThrow(() -> new RuntimeException(String.format("lesson with id %d not found", id)));
  }

  @Override
  @Transactional(readOnly = true)
  public List<Lesson> getAllLessons() {
    return lessonRepository.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  public Lesson getLessonById(Long id) {
    return getLessonByIdOrThrowException(id);
  }

  @Override
  public Lesson addLesson(LessonRequest lessonRequest) {
    return lessonRepository.save(new Lesson(lessonRequest.getLessonName()));
  }

  @Override
  public void deleteLessonById(Long id) {
    lessonRepository.delete(getLessonByIdOrThrowException(id));
  }

  @Override
  public void updateLessonById(Long id, LessonRequest lessonRequest) {
    Lesson lesson = getLessonByIdOrThrowException(id);
    lesson.setLessonName(lessonRequest.getLessonName());
    lessonRepository.save(lesson);
  }

}