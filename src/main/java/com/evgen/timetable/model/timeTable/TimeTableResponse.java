package com.evgen.timetable.model.timeTable;

import java.util.Set;

import com.evgen.timetable.model.workDay.WorkDayResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TimeTableResponse {

  private String timeTableId;
  private TimeTableName timeTableName;
  private Set<WorkDayResponse> workDays;

}