package com.evgen.timetable.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.evgen.timetable.model.workDay.WorkDay;
import com.evgen.timetable.model.workDay.WorkDayResponse;

@Mapper(componentModel = "spring", uses = LessonMapper.class)
public interface WorkDayMapper {

  @Mapping(target = "workDayId", source = "id")
  WorkDayResponse workDaySetToWorkDayResponse(WorkDay workDay);

}