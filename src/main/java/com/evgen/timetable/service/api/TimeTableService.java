package com.evgen.timetable.service.api;

import com.evgen.timetable.model.dto.timeTable.TimeTableRequest;
import com.evgen.timetable.model.dto.timeTable.TimeTableResponse;
import com.evgen.timetable.model.dto.timeTable.TimeTableUpdateRequest;

public interface TimeTableService {

  TimeTableResponse getTimeTableById(Long id);

  TimeTableResponse addTimeTable(TimeTableRequest timeTableRequest);

  void deleteTimeTableById(Long id);

  void updateTimeTableById(Long id, TimeTableUpdateRequest timeTableRequest);

}