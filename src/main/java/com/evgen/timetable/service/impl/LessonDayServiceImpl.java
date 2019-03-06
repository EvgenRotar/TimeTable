package com.evgen.timetable.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.evgen.timetable.mapper.LessonDayMapper;
import com.evgen.timetable.model.dto.lesson.LessonDayRequest;
import com.evgen.timetable.model.dto.lesson.LessonDayResponse;
import com.evgen.timetable.model.dto.lesson.LessonDaySaveRequest;
import com.evgen.timetable.model.entity.Lesson;
import com.evgen.timetable.model.entity.LessonDay;
import com.evgen.timetable.model.entity.Teacher;
import com.evgen.timetable.model.entity.WorkDay;
import com.evgen.timetable.repository.LessonDayRepository;
import com.evgen.timetable.repository.LessonRepository;
import com.evgen.timetable.repository.TeacherRepository;
import com.evgen.timetable.service.api.LessonDayService;
import com.evgen.timetable.util.OptionalDaoUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Transactional
public class LessonDayServiceImpl implements LessonDayService {

  private final LessonDayRepository lessonDayRepository;
  private final OptionalDaoUtil optionalDaoUtil;
  private final LessonDayMapper lessonDayMapper;
  private final LessonRepository lessonRepository;
  private final TeacherRepository teacherRepository;

  @Override
  @Transactional(readOnly = true)
  public LessonDayResponse getLessonDayById(Long lessonDayId) {
    return lessonDayMapper.lessonDayToLessonDayResponse(optionalDaoUtil.getLessonDayByIdOrThrowException(lessonDayId));
  }

  @Override
  public LessonDayResponse addLessonDay(LessonDaySaveRequest lessonDaySaveRequest) {
    Teacher teacher = optionalDaoUtil.getTeacherOrThrowException(lessonDaySaveRequest.getTeacherName(),
        lessonDaySaveRequest.getTeacherSurname());

    WorkDay workDay = optionalDaoUtil.getWorkDayByIdOrThrowException(lessonDaySaveRequest.getWorkDayId());

    Lesson lesson = optionalDaoUtil.getLessonByNameOrThrowException(lessonDaySaveRequest.getLessonName());

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
    lessonDayRepository.delete(optionalDaoUtil.getLessonDayByIdOrThrowException(lessonDayId));
  }

  @Override
  //TODO: not working
  public void updateLessonDay(Long lessonDayId, LessonDayRequest lessonDayRequest) {
    LessonDay lessonDay = optionalDaoUtil.getLessonDayByIdOrThrowException(lessonDayId);
    lessonDayMapper.mapLessonDayFromLessonDayRequest(lessonDayRequest, lessonDay, teacherRepository, lessonRepository);
    lessonDayRepository.save(lessonDay);
  }

}