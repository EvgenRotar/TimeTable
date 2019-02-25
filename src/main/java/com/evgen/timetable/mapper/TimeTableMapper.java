package com.evgen.timetable.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.evgen.timetable.model.timeTable.TimeTable;
import com.evgen.timetable.model.timeTable.TimeTableResponse;

@Mapper(componentModel = "spring", uses = WorkDayMapper.class)
public interface TimeTableMapper {

  @Mapping(target = "timeTableId", source = "id")
  TimeTableResponse timeTableToTimeTableResponse(TimeTable timeTable);

}
