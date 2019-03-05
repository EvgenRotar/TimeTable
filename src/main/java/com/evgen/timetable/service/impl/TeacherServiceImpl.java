package com.evgen.timetable.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.evgen.timetable.mapper.LessonDayMapper;
import com.evgen.timetable.mapper.UserMapper;
import com.evgen.timetable.model.lesson.LessonDay;
import com.evgen.timetable.model.lesson.LessonDayResponse;
import com.evgen.timetable.model.teacher.Teacher;
import com.evgen.timetable.model.teacher.TeacherResponse;
import com.evgen.timetable.model.timeTable.TimeTableName;
import com.evgen.timetable.model.timeTable.TimeTableResponse;
import com.evgen.timetable.model.workDay.DayName;
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
  private final LessonDayMapper lessonDayMapper;

  private Teacher getTeacherByIdOrThrowException(Long id) {
    return teacherRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("teacher not found"));
  }

  @Override
  @Transactional(readOnly = true)
  public TeacherResponse getTeacherById(Long id) {
    Teacher teacher = getTeacherByIdOrThrowException(id);

    return mapTeacherResponse(teacher);
  }

  //Todo: clean-up
  private TeacherResponse mapTeacherResponse(Teacher teacher) {

    TeacherResponse teacherResponse = userMapper.teacherToTeacherResponse(teacher);
//        new TeacherResponse();
//    teacherResponse.setUserName(teacher.getUserName());
//    teacherResponse.setUserSurname(teacher.getUserSurname());
//    teacherResponse.setLogin(teacher.getLogin());
//    teacherResponse.setTeacherId(teacher.getId());

    Set<TimeTableResponse> timeTableResponses = new HashSet<>();

    TimeTableResponse timeTableResponseUp = new TimeTableResponse();
    TimeTableResponse timeTableResponseDown = new TimeTableResponse();

    timeTableResponseUp.setTimeTableName(TimeTableName.FIRST);
    timeTableResponseDown.setTimeTableName(TimeTableName.SECOND);

    Map<DayName, WorkDayResponse> newWeekUp = buildNewWeek();
    Map<DayName, WorkDayResponse> newWeekDown = buildNewWeek();

    teacher.getTimeTables().forEach(lessonDay -> {
      if (lessonDay.getWorkDay().getTimeTable().getTimeTableName().equals(TimeTableName.FIRST)) {
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
    newWeek.get(lessonDay.getWorkDay().getDayName()).getLessons().add(lessonDayMapper.lessonDayToLessonDayResponse(lessonDay));
  }

  //TODO:remove
  private LessonDayResponse buildLessonDayResponse(LessonDay lessonDay) {
    LessonDayResponse lessonDayResponse = new LessonDayResponse();
    lessonDayResponse.setLessonTime(lessonDay.getLessonTime());
    lessonDayResponse.setLesson(lessonDay.getLesson());
    lessonDayResponse.setLessonPlace(lessonDay.getLessonPlace());
    lessonDayResponse.setTeacherName(lessonDay.getTeacher().getUserName());
    lessonDayResponse.setTeacherSurname(lessonDay.getTeacher().getUserSurname());

    return lessonDayResponse;
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