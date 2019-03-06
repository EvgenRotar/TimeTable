package com.evgen.timetable.mapper;

import java.util.Set;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.evgen.timetable.model.entity.WorkDay;
import com.evgen.timetable.model.dto.workDay.WorkDayRequest;
import com.evgen.timetable.model.dto.workDay.WorkDayResponse;
import com.evgen.timetable.repository.LessonRepository;
import com.evgen.timetable.repository.TeacherRepository;

@Mapper(componentModel = "spring", uses = LessonDayMapper.class)
public interface WorkDayMapper {

  @Mapping(target = "workDayId", source = "id")
  WorkDayResponse workDaySetToWorkDayResponse(WorkDay workDay);

  void mapWorkDaySetFromWorkDaysRequestSet(Set<WorkDayRequest> workDayRequest,
      @MappingTarget Set<WorkDay> workDay,
      @Context TeacherRepository teacherRepository,
      @Context LessonRepository lessonRepository);

  //@Mapping(target = "id", source = "workDayId")
  WorkDay workDayRequestToWorkDay(WorkDayRequest workDayRequest,
      @Context TeacherRepository teacherRepository,
      @Context LessonRepository lessonRepository);
}