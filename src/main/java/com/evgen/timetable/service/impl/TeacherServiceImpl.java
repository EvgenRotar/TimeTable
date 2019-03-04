package com.evgen.timetable.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.evgen.timetable.mapper.UserMapper;
import com.evgen.timetable.model.teacher.Teacher;
import com.evgen.timetable.model.teacher.TeacherResponse;
import com.evgen.timetable.model.timeTable.TimeTableName;
import com.evgen.timetable.model.timeTable.TimeTableResponse;
import com.evgen.timetable.model.workDay.DayName;
import com.evgen.timetable.model.workDay.LessonDay;
import com.evgen.timetable.model.workDay.LessonResponse;
import com.evgen.timetable.model.workDay.WorkDayResponse;
import com.evgen.timetable.repository.TeacherRepository;
import com.evgen.timetable.service.api.TeacherService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

  private final TeacherRepository teacherRepository;
  private final UserMapper userMapper;

  private Teacher getTeacherByIdOrThrowException(Long id) {
    return teacherRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("teacher not found"));
  }

  @Override
  public TeacherResponse getTeacherById(Long id) {
    Teacher teacher = getTeacherByIdOrThrowException(id);

    return mapTeacherResponse(teacher);
  }

  private TeacherResponse mapTeacherResponse(Teacher teacher) {
    TeacherResponse teacherResponse = new TeacherResponse();
    teacherResponse.setUserName(teacher.getUserName());
    teacherResponse.setUserSurname(teacher.getUserSurname());
    teacherResponse.setLogin(teacher.getLogin());
    teacherResponse.setTeacherId(teacher.getId());

    Set<TimeTableResponse> timeTableResponses = new HashSet<>();

    TimeTableResponse timeTableResponseUp = new TimeTableResponse();
    TimeTableResponse timeTableResponseDown = new TimeTableResponse();

    timeTableResponseUp.setTimeTableName(TimeTableName.UP);
    timeTableResponseDown.setTimeTableName(TimeTableName.DOWN);

    Map<DayName, WorkDayResponse> newWeekUp = buildNewWeek();
    Map<DayName, WorkDayResponse> newWeekDown = buildNewWeek();

    teacher.getTimeTables().forEach(lessonDay -> {
      if (lessonDay.getWorkDay().getTimeTable().getTimeTableName().equals(TimeTableName.UP)) {
        buildWorkDayResponses(newWeekUp, lessonDay);
      } else {
        buildWorkDayResponses(newWeekDown, lessonDay);
      }
    });

    timeTableResponseUp.setWorkDays(new HashSet<>(newWeekUp.values()));
    timeTableResponseDown.setWorkDays(new HashSet<>(newWeekDown.values()));
    timeTableResponses.add(timeTableResponseUp);
    timeTableResponses.add(timeTableResponseDown);
    teacherResponse.setTimeTables(timeTableResponses);
    return teacherResponse;
  }

  private void buildWorkDayResponses(Map<DayName, WorkDayResponse> newWeek, LessonDay lessonDay) {
    newWeek.get(lessonDay.getWorkDay().getDayName()).getLessons().add(buildLessonResponse(lessonDay));
  }

  private LessonResponse buildLessonResponse(LessonDay lessonDay) {
    LessonResponse lessonResponse = new LessonResponse();
    lessonResponse.setLessonTime(lessonDay.getLessonTime());
    lessonResponse.setLesson(lessonDay.getLesson());
    lessonResponse.setLessonPlace(lessonDay.getLessonPlace());
    lessonResponse.setTeacherName(lessonDay.getTeacher().getUserName());
    lessonResponse.setTeacherSurname(lessonDay.getTeacher().getUserSurname());

    return lessonResponse;
  }

  private Map<DayName, WorkDayResponse> buildNewWeek() {
    Map<DayName, WorkDayResponse> workDayResponses = new HashMap<>();
    workDayResponses.put(DayName.MONDAY, buildWorkDay(DayName.MONDAY));
    workDayResponses.put(DayName.TUESDAY, buildWorkDay(DayName.TUESDAY));
    workDayResponses.put(DayName.WEDNESDAY, buildWorkDay(DayName.WEDNESDAY));
    workDayResponses.put(DayName.THURSDAY, buildWorkDay(DayName.THURSDAY));
    workDayResponses.put(DayName.FRIDAY, buildWorkDay(DayName.FRIDAY));
    workDayResponses.put(DayName.SATURDAY, buildWorkDay(DayName.SATURDAY));

    return workDayResponses;
  }

  private WorkDayResponse buildWorkDay(DayName dayName) {
    WorkDayResponse workDayResponse = new WorkDayResponse();
    workDayResponse.setDayName(dayName);
    workDayResponse.setLessons(new HashSet<>());

    return workDayResponse;
  }

}