package com.evgen.timetable.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.evgen.timetable.mapper.TimeTableMapper;
import com.evgen.timetable.mapper.WorkDayMapper;
import com.evgen.timetable.model.group.Group;
import com.evgen.timetable.model.lesson.Lesson;
import com.evgen.timetable.model.lesson.LessonDay;
import com.evgen.timetable.model.lesson.LessonDayRequest;
import com.evgen.timetable.model.teacher.Teacher;
import com.evgen.timetable.model.timeTable.TimeTable;
import com.evgen.timetable.model.timeTable.TimeTableRequest;
import com.evgen.timetable.model.timeTable.TimeTableResponse;
import com.evgen.timetable.model.timeTable.TimeTableUpdateRequest;
import com.evgen.timetable.model.workDay.DayName;
import com.evgen.timetable.model.workDay.WorkDay;
import com.evgen.timetable.model.workDay.WorkDayRequest;
import com.evgen.timetable.repository.GroupRepository;
import com.evgen.timetable.repository.LessonDayRepository;
import com.evgen.timetable.repository.LessonRepository;
import com.evgen.timetable.repository.TeacherRepository;
import com.evgen.timetable.repository.TimeTableRepository;
import com.evgen.timetable.repository.WorkDayRepository;
import com.evgen.timetable.service.api.TimeTableService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Transactional
public class TimeTableServiceImpl implements TimeTableService {

  private final GroupRepository groupRepository;
  private final TimeTableRepository timeTableRepository;
  private final WorkDayRepository workDayRepository;
  private final LessonDayRepository lessonDayRepository;
  private final LessonRepository lessonRepository;
  private final TeacherRepository teacherRepository;
  private final TimeTableMapper timeTableMapper;
  private final WorkDayMapper workDayMapper;

  private TimeTable getTimeTableByIdOrThrowException(Long id) {
    return timeTableRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("TimeTable not found"));
  }

  private Teacher getTeacherOrThrowException(String teacherName, String teacherSurname) {
    return teacherRepository.findTeacherByUserNameAndUserSurname(teacherName, teacherSurname)
        .orElseThrow(() -> new RuntimeException("Teacher not found"));
  }

  private Group getGroupOrThrowException(String groupName) throws RuntimeException {
    return groupRepository.findByGroupName(groupName)
        .orElseThrow(() -> new RuntimeException(String.format("group with name %s not found", groupName)));
  }

  private Lesson getLessonByNameOrThrowException(String lessonName) throws RuntimeException {
    return lessonRepository.findByLessonName(lessonName)
        .orElseThrow(() -> new RuntimeException(String.format("lesson with name %s not found", lessonName)));
  }

  @Override
  @Transactional(readOnly = true)
  public TimeTableResponse getTimeTableById(Long id) {
    return timeTableMapper.timeTableToTimeTableResponse(getTimeTableByIdOrThrowException(id));
  }

  @Override
  public void deleteTimeTableById(Long id) {
    timeTableRepository.delete(getTimeTableByIdOrThrowException(id));
  }

  //TODO:remove
  @Override
  public void updateTimeTableById(Long id, TimeTableUpdateRequest timeTableUpdateRequest) {
    TimeTable timeTable = getTimeTableByIdOrThrowException(id);
    timeTableMapper.mapTimeTableFromTimeTableUpdateRequest(timeTableUpdateRequest, timeTable);
    timeTableRepository.save(timeTable);

    Set<WorkDay> workDays = timeTable.getWorkDays();
    workDayMapper.mapWorkDaySetFromWorkDaysRequestSet(timeTableUpdateRequest.getWorkDays(), workDays, teacherRepository,
        lessonRepository);
    workDays.forEach(workDay -> {
      workDayRepository.save(workDay);
      workDay.getLessons().forEach(lessonDayRepository::save);
    });

  }

  //TODO: clean-up
  @Override
  public TimeTableResponse addTimeTable(TimeTableRequest timeTableRequest) {
    Group group = getGroupOrThrowException(timeTableRequest.getGroupName());

    TimeTable timeTable = new TimeTable();
    timeTable.setTimeTableName(timeTableRequest.getTimeTableName());
    timeTable.setGroup(group);
    timeTable.setWorkDays(new HashSet<>());
    timeTable = timeTableRepository.save(timeTable);
    addWorkDays(timeTable, timeTableRequest.getWorkDays());
    return timeTableMapper.timeTableToTimeTableResponse(timeTable);
  }

  private void addWorkDays(TimeTable timeTable, Set<WorkDayRequest> workDays) {
    workDays.forEach(workDay -> saveWorkDay(timeTable, workDay.getDayName(), workDay.getLessons()));
  }

  private void saveWorkDay(TimeTable timeTable, DayName dayName, Set<LessonDayRequest> lessonDays) {
    WorkDay workDay = new WorkDay();
    workDay.setTimeTable(timeTable);
    workDay.setDayName(dayName);
    workDay.setLessons(new HashSet<>());
    workDayRepository.save(workDay);
    timeTable.getWorkDays().add(workDay);

    lessonDays.forEach(lessonDay -> buildAndSaveLesson(
        getLessonByNameOrThrowException(lessonDay.getLessonName()),
        getTeacherOrThrowException(lessonDay.getTeacherName(), lessonDay.getTeacherSurname()),
        lessonDay.getLessonPlace(),
        lessonDay.getLessonTime(),
        workDay)
    );
  }

  private void buildAndSaveLesson(Lesson lesson, Teacher teacher, String place, String time, WorkDay workDay) {
    LessonDay lessonDay = LessonDay.builder()
        .lesson(lesson)
        .lessonPlace(place)
        .lessonTime(time)
        .teacher(teacher)
        .workDay(workDay)
        .build();
    workDay.getLessons().add(lessonDay);
    lessonDayRepository.save(lessonDay);
  }

}