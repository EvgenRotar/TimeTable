package com.evgen.timetable.service.api;

import com.evgen.timetable.exception.NotFoundException;
import com.evgen.timetable.model.dto.timeTable.TimeTableRequest;
import com.evgen.timetable.model.dto.timeTable.TimeTableResponse;

public interface TimeTableService {

  TimeTableResponse getTimeTableById(Long id) throws NotFoundException;

  TimeTableResponse addTimeTable(TimeTableRequest timeTableRequest) throws NotFoundException;

  void deleteTimeTableById(Long id) throws NotFoundException;

}