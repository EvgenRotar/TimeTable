package com.evgen.timetable.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.evgen.timetable.model.dto.timeTable.TimeTableResponse;
import com.evgen.timetable.model.entity.TimeTable;

@Mapper(componentModel = "spring", uses = WorkDayMapper.class)
public interface TimeTableMapper {

  @Mapping(target = "timeTableId", source = "id")
  TimeTableResponse timeTableToTimeTableResponse(TimeTable timeTable);

}