package com.evgen.timetable.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.evgen.timetable.exception.NotFoundException;
import com.evgen.timetable.mapper.LessonDayMapper;
import com.evgen.timetable.model.dto.lesson.LessonDayRequest;
import com.evgen.timetable.model.dto.lesson.LessonDayResponse;
import com.evgen.timetable.model.dto.lesson.LessonDaySaveRequest;
import com.evgen.timetable.model.entity.LessonDay;
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
  public LessonDayResponse getLessonDayById(Long lessonDayId) throws NotFoundException {
    return lessonDayMapper.lessonDayToLessonDayResponse(optionalDaoUtil.getLessonDayByIdOrThrowException(lessonDayId));
  }

  @Override
  public LessonDayResponse addLessonDay(LessonDaySaveRequest lessonDaySaveRequest) throws NotFoundException {
    LessonDay lessonDay = LessonDay.builder()
        .lesson(optionalDaoUtil.getLessonByNameOrThrowException(lessonDaySaveRequest.getLessonName()))
        .lessonPlace(lessonDaySaveRequest.getLessonPlace())
        .lessonTime(lessonDaySaveRequest.getLessonTime())
        .teacher(optionalDaoUtil.getTeacherOrThrowException(lessonDaySaveRequest.getTeacherName(),
            lessonDaySaveRequest.getTeacherSurname()))
        .workDay(optionalDaoUtil.getWorkDayByIdOrThrowException(lessonDaySaveRequest.getWorkDayId()))
        .build();

    return lessonDayMapper.lessonDayToLessonDayResponse(lessonDayRepository.save(lessonDay));
  }

  @Override
  public void deleteLessonDayById(Long lessonDayId) throws NotFoundException {
    lessonDayRepository.delete(optionalDaoUtil.getLessonDayByIdOrThrowException(lessonDayId));
  }

  @Override
  public void updateLessonDay(Long lessonDayId, LessonDayRequest lessonDayRequest) throws NotFoundException {
    LessonDay lessonDay = optionalDaoUtil.getLessonDayByIdOrThrowException(lessonDayId);
    lessonDayMapper.mapLessonDayFromLessonDayRequest(lessonDayRequest, lessonDay, teacherRepository, lessonRepository);
    lessonDayRepository.save(lessonDay);
  }

}