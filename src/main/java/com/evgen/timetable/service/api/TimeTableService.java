package com.evgen.timetable.service.api;

import com.evgen.timetable.model.dto.timeTable.TimeTableRequest;
import com.evgen.timetable.model.dto.timeTable.TimeTableResponse;

public interface TimeTableService {

  TimeTableResponse getTimeTableById(Long id);

  TimeTableResponse addTimeTable(TimeTableRequest timeTableRequest);

  void deleteTimeTableById(Long id);

}