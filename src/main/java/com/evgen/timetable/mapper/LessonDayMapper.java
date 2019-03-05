package com.evgen.timetable.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import com.evgen.timetable.model.lesson.Lesson;
import com.evgen.timetable.model.lesson.LessonDay;
import com.evgen.timetable.model.lesson.LessonDayRequest;
import com.evgen.timetable.model.lesson.LessonDayResponse;
import com.evgen.timetable.model.teacher.Teacher;
import com.evgen.timetable.repository.LessonRepository;
import com.evgen.timetable.repository.TeacherRepository;

@Mapper(componentModel = "spring")
public interface LessonDayMapper {

  @Mapping(target = "teacherName", source = "teacher.userName")
  @Mapping(target = "teacherSurname", source = "teacher.userSurname")
  LessonDayResponse lessonDayToLessonDayResponse(LessonDay lessonDay);

  @Mapping(target = "lesson", source = "lessonDayRequest", qualifiedByName = "lessonMap")
  @Mapping(target = "teacher", source = "lessonDayRequest", qualifiedByName = "teacherMap")
  LessonDay lessonDayRequestToLessonDay(
      LessonDayRequest lessonDayRequest,
      @Context TeacherRepository teacherRepository,
      @Context LessonRepository lessonRepository);

  @Named("teacherMap")
  default Teacher lessonDayRequestToTeacher(LessonDayRequest lessonDayRequest,
      @Context TeacherRepository teacherRepository) {
    return teacherRepository
        .findTeacherByUserNameAndUserSurname(lessonDayRequest.getTeacherName(), lessonDayRequest.getTeacherSurname())
        .orElseThrow(() -> new RuntimeException("Teacher not found"));
  }

  @Named("lessonMap")
  default Lesson lessonDayRequestToLesson(LessonDayRequest lessonDayRequest,
      @Context LessonRepository lessonRepository) {
    return lessonRepository.findByLessonName(lessonDayRequest.getLessonName())
        .orElseThrow(() -> new RuntimeException("Lesson not found"));
  }

  @Mapping(target = "lesson", source = "lessonDayRequest", qualifiedByName = "lessonMap")
  @Mapping(target = "teacher", source = "lessonDayRequest", qualifiedByName = "teacherMap")
  void mapLessonDayFromLessonDayRequest(LessonDayRequest lessonDayRequest,
      @MappingTarget LessonDay lessonDay,
      @Context TeacherRepository teacherRepository,
      @Context LessonRepository lessonRepository);

}