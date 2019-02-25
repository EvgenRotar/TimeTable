package com.evgen.timetable.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.evgen.timetable.model.workDay.LessonDay;
import com.evgen.timetable.model.workDay.LessonResponse;

@Mapper(componentModel = "spring")
public interface LessonMapper {

  @Mapping(target = "teacherName", source = "teacher.userName")
  @Mapping(target = "teacherSurname", source = "teacher.userSurname")
  LessonResponse lessonSetToLessonResponse(LessonDay lessonDay);

}