package com.evgen.timetable.mapper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.evgen.timetable.Constants;
import com.evgen.timetable.model.dto.teacher.TeacherResponse;
import com.evgen.timetable.model.dto.timeTable.TimeTableResponse;
import com.evgen.timetable.model.dto.workDay.WorkDayResponse;
import com.evgen.timetable.model.entity.LessonDay;
import com.evgen.timetable.model.entity.Teacher;
import com.evgen.timetable.model.name.DayName;
import com.evgen.timetable.model.name.TimeTableName;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class TeacherMapper {

  private final UserMapper userMapper;
  private final LessonDayMapper lessonDayMapper;

  public TeacherResponse mapTeacherResponse(Teacher teacher) {
    TeacherResponse teacherResponse = userMapper.teacherToTeacherResponse(teacher);

    Map<DayName, WorkDayResponse> newWeekUp = buildNewWeekMap();
    Map<DayName, WorkDayResponse> newWeekDown = buildNewWeekMap();

    teacher.getTimeTables()
        .forEach(lessonDay -> {
          if (lessonDay.getWorkDay().getTimeTable().getTimeTableName().equals(TimeTableName.FIRST)) {
            buildWorkDayResponses(newWeekUp, lessonDay);
          } else {
            buildWorkDayResponses(newWeekDown, lessonDay);
          }
        });

    teacherResponse.setTimeTables(new HashSet<>(
        Arrays.asList(
            buildTimeTableResponse(newWeekUp, TimeTableName.FIRST),
            buildTimeTableResponse(newWeekDown, TimeTableName.SECOND)
        )
    ));

    return teacherResponse;
  }

  private void buildWorkDayResponses(Map<DayName, WorkDayResponse> newWeek, LessonDay lessonDay) {
    newWeek.get(lessonDay.getWorkDay().getDayName())
        .getLessons().add(lessonDayMapper.lessonDayToLessonDayResponse(lessonDay));
  }

  private Map<DayName, WorkDayResponse> buildNewWeekMap() {
    Map<DayName, WorkDayResponse> workDayResponses = new HashMap<>();
    Constants.WEEK.forEach(dayName -> workDayResponses.put(dayName, buildWorkDay(dayName)));

    return workDayResponses;
  }

  private TimeTableResponse buildTimeTableResponse(Map<DayName, WorkDayResponse> week, TimeTableName first) {
    return TimeTableResponse.builder()
        .timeTableName(first)
        .workDays(new HashSet<>(week.values()))
        .build();
  }

  private WorkDayResponse buildWorkDay(DayName dayName) {
    return WorkDayResponse.builder()
        .dayName(dayName)
        .lessons(new HashSet<>())
        .build();
  }
}
