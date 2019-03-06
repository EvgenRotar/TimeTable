package com.evgen.timetable.service.impl;

import java.util.HashSet;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.evgen.timetable.mapper.TimeTableMapper;
import com.evgen.timetable.model.dto.lesson.LessonDayRequest;
import com.evgen.timetable.model.dto.timeTable.TimeTableRequest;
import com.evgen.timetable.model.dto.timeTable.TimeTableResponse;
import com.evgen.timetable.model.dto.workDay.WorkDayRequest;
import com.evgen.timetable.model.entity.LessonDay;
import com.evgen.timetable.model.entity.TimeTable;
import com.evgen.timetable.model.entity.WorkDay;
import com.evgen.timetable.model.name.DayName;
import com.evgen.timetable.repository.LessonDayRepository;
import com.evgen.timetable.repository.TimeTableRepository;
import com.evgen.timetable.repository.WorkDayRepository;
import com.evgen.timetable.service.api.TimeTableService;
import com.evgen.timetable.util.OptionalDaoUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Transactional
public class TimeTableServiceImpl implements TimeTableService {

  private final TimeTableRepository timeTableRepository;
  private final WorkDayRepository workDayRepository;
  private final LessonDayRepository lessonDayRepository;
  private final TimeTableMapper timeTableMapper;
  private final OptionalDaoUtil optionalDaoUtil;

  @Override
  @Transactional(readOnly = true)
  public TimeTableResponse getTimeTableById(Long id) {
    return timeTableMapper.timeTableToTimeTableResponse(optionalDaoUtil.getTimeTableByIdOrThrowException(id));
  }

  @Override
  public void deleteTimeTableById(Long id) {
    timeTableRepository.delete(optionalDaoUtil.getTimeTableByIdOrThrowException(id));
  }

  @Override
  public TimeTableResponse addTimeTable(TimeTableRequest timeTableRequest) {
    TimeTable timeTable = timeTableRepository.save(
        TimeTable.builder()
            .timeTableName(timeTableRequest.getTimeTableName())
            .group(optionalDaoUtil.getGroupOrThrowException(timeTableRequest.getGroupName()))
            .workDays(new HashSet<>())
            .build()
    );

    addWorkDaysToTimeTable(timeTableRequest, timeTable);

    return timeTableMapper.timeTableToTimeTableResponse(timeTable);
  }

  private void addWorkDaysToTimeTable(TimeTableRequest timeTableRequest, TimeTable timeTable) {
    timeTableRequest.getWorkDays().forEach(workDayRequest -> {
      WorkDay savedWorkDay = saveWorkDay(timeTable, workDayRequest.getDayName());
      timeTable.getWorkDays().add(savedWorkDay);
      addLessonsToWorkDay(workDayRequest, savedWorkDay);
    });
  }

  private void addLessonsToWorkDay(WorkDayRequest workDayRequest, WorkDay savedWorkDay) {
    workDayRequest.getLessons().forEach(
        lessonDayRequest -> savedWorkDay.getLessons().add(saveLessons(lessonDayRequest, savedWorkDay))
    );
  }

  private WorkDay saveWorkDay(TimeTable timeTable, DayName dayName) {
    return workDayRepository.save(
        WorkDay.builder()
            .timeTable(timeTable)
            .dayName(dayName)
            .lessons(new HashSet<>())
            .build()
    );
  }

  private LessonDay saveLessons(LessonDayRequest lessonDayRequest, WorkDay workDay) {
    return lessonDayRepository.save(
        LessonDay.builder()
            .lesson(optionalDaoUtil.getLessonByNameOrThrowException(lessonDayRequest.getLessonName()))
            .lessonPlace(lessonDayRequest.getLessonPlace())
            .lessonTime(lessonDayRequest.getLessonTime())
            .teacher(optionalDaoUtil
                .getTeacherOrThrowException(lessonDayRequest.getTeacherName(), lessonDayRequest.getTeacherSurname()))
            .workDay(workDay)
            .build()
    );
  }

}