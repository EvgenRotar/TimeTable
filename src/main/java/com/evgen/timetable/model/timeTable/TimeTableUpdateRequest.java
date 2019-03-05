package com.evgen.timetable.model.timeTable;

import java.util.Set;

import javax.validation.constraints.NotNull;

import com.evgen.timetable.model.workDay.WorkDayRequest;

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
public class TimeTableUpdateRequest {

  @NotNull
  private TimeTableName timeTableName;

  @NotNull
  private Set<WorkDayRequest> workDays;

}
