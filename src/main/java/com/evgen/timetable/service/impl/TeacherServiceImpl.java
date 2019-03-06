package com.evgen.timetable.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.evgen.timetable.Constants;
import com.evgen.timetable.mapper.LessonDayMapper;
import com.evgen.timetable.mapper.UserMapper;
import com.evgen.timetable.model.dto.teacher.TeacherResponse;
import com.evgen.timetable.model.dto.timeTable.TimeTableResponse;
import com.evgen.timetable.model.dto.workDay.WorkDayResponse;
import com.evgen.timetable.model.entity.LessonDay;
import com.evgen.timetable.model.entity.Teacher;
import com.evgen.timetable.model.name.DayName;
import com.evgen.timetable.model.name.TimeTableName;
import com.evgen.timetable.service.api.TeacherService;
import com.evgen.timetable.util.OptionalDaoUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

  private final UserMapper userMapper;
  private final LessonDayMapper lessonDayMapper;
  private final OptionalDaoUtil optionalDaoUtil;

  @Override
  @Transactional(readOnly = true)
  public TeacherResponse getTeacherById(Long id) {
    return mapTeacherResponse(optionalDaoUtil.getTeacherByIdOrThrowException(id));
  }

  //Todo: clean-up
  private TeacherResponse mapTeacherResponse(Teacher teacher) {
    TeacherResponse teacherResponse = userMapper.teacherToTeacherResponse(teacher);

    Map<DayName, WorkDayResponse> newWeekUp = buildNewWeekMap();
    Map<DayName, WorkDayResponse> newWeekDown = buildNewWeekMap();

    teacher.getTimeTables().forEach(lessonDay -> {
      if (lessonDay.getWorkDay().getTimeTable().getTimeTableName().equals(TimeTableName.FIRST)) {
        buildWorkDayResponses(newWeekUp, lessonDay);
      } else {
        buildWorkDayResponses(newWeekDown, lessonDay);
      }
    });

    Set<TimeTableResponse> timeTableResponses = new HashSet<>();
    timeTableResponses.add(
        TimeTableResponse.builder()
            .timeTableName(TimeTableName.FIRST)
            .workDays(new HashSet<>(newWeekUp.values()))
            .build()
    );
    timeTableResponses.add(
        TimeTableResponse.builder()
        .timeTableName(TimeTableName.SECOND)
        .workDays(new HashSet<>(newWeekDown.values()))
        .build()
    );

    teacherResponse.setTimeTables(timeTableResponses);
    return teacherResponse;
  }

  private void buildWorkDayResponses(Map<DayName, WorkDayResponse> newWeek, LessonDay lessonDay) {
    newWeek.get(lessonDay.getWorkDay().getDayName()).getLessons()
        .add(lessonDayMapper.lessonDayToLessonDayResponse(lessonDay));
  }

  private Map<DayName, WorkDayResponse> buildNewWeekMap() {
    Map<DayName, WorkDayResponse> workDayResponses = new HashMap<>();
    Constants.WEEK.forEach(dayName -> workDayResponses.put(dayName, buildWorkDay(dayName)));

    return workDayResponses;
  }

  private WorkDayResponse buildWorkDay(DayName dayName) {
    return WorkDayResponse.builder()
        .dayName(dayName)
        .lessons(new HashSet<>())
        .build();
  }

}