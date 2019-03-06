package com.evgen.timetable.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.evgen.timetable.mapper.LessonDayMapper;
import com.evgen.timetable.model.entity.Lesson;
import com.evgen.timetable.model.entity.LessonDay;
import com.evgen.timetable.model.dto.lesson.LessonDayRequest;
import com.evgen.timetable.model.dto.lesson.LessonDayResponse;
import com.evgen.timetable.model.dto.lesson.LessonDaySaveRequest;
import com.evgen.timetable.model.entity.Teacher;
import com.evgen.timetable.model.entity.WorkDay;
import com.evgen.timetable.repository.LessonDayRepository;
import com.evgen.timetable.repository.LessonRepository;
import com.evgen.timetable.repository.TeacherRepository;
import com.evgen.timetable.repository.WorkDayRepository;
import com.evgen.timetable.service.api.LessonDayService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Transactional
public class LessonDayServiceImpl implements LessonDayService {

  private final LessonDayRepository lessonDayRepository;
  private final WorkDayRepository workDayRepository;
  private final LessonDayMapper lessonDayMapper;
  private final LessonRepository lessonRepository;
  private final TeacherRepository teacherRepository;

  private LessonDay getLessonDayByIdOrThrowException(Long id) {
    return lessonDayRepository.findById(id)
        .orElseThrow(() -> new RuntimeException(String.format("lessonDay with id %d not found", id)));
  }

  private Lesson getLessonByNameOrThrowException(String lessonName) {
    return lessonRepository.findByLessonName(lessonName)
        .orElseThrow(() -> new RuntimeException(String.format("lesson with name %s not found", lessonName)));
  }

  private WorkDay getWorkDayByIdOrThrowException(Long id) {
    return workDayRepository.findById(id)
        .orElseThrow(() -> new RuntimeException(String.format("workDay with id %d not found", id)));
  }

  private Teacher getTeacherOrThrowException(String teacherName, String teacherSurname) {
    return teacherRepository.findTeacherByUserNameAndUserSurname(teacherName, teacherSurname)
        .orElseThrow(() -> new RuntimeException("Teacher not found"));
  }

  @Override
  @Transactional(readOnly = true)
  public LessonDayResponse getLessonDayById(Long lessonDayId) {
    return lessonDayMapper.lessonDayToLessonDayResponse(getLessonDayByIdOrThrowException(lessonDayId));
  }

  @Override
  //TODO:clean-up
  public LessonDayResponse addLessonDay(LessonDaySaveRequest lessonDaySaveRequest) {
    Teacher teacher = getTeacherOrThrowException(lessonDaySaveRequest.getTeacherName(),
        lessonDaySaveRequest.getTeacherSurname());
    WorkDay workDay = getWorkDayByIdOrThrowException(lessonDaySaveRequest.getWorkDayId());
    Lesson lesson = getLessonByNameOrThrowException(lessonDaySaveRequest.getLessonName());
    LessonDay lessonDay = LessonDay.builder()
        .lesson(lesson)
        .lessonPlace(lessonDaySaveRequest.getLessonPlace())
        .lessonTime(lessonDaySaveRequest.getLessonTime())
        .teacher(teacher)
        .workDay(workDay)
        .build();
    return lessonDayMapper.lessonDayToLessonDayResponse(lessonDayRepository.save(lessonDay));
  }

  @Override
  public void deleteLessonDayById(Long lessonDayId) {
    lessonDayRepository.delete(getLessonDayByIdOrThrowException(lessonDayId));
  }

  @Override
  public void updateLessonDay(Long lessonDayId, LessonDayRequest lessonDayRequest) {
    LessonDay lessonDay = getLessonDayByIdOrThrowException(lessonDayId);
    lessonDayMapper.mapLessonDayFromLessonDayRequest(lessonDayRequest, lessonDay, teacherRepository, lessonRepository);
    lessonDayRepository.save(lessonDay);
  }

}