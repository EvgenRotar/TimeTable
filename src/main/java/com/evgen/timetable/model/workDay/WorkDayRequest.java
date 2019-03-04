package com.evgen.timetable.model.workDay;

import java.util.Set;

import javax.validation.constraints.NotNull;

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
public class WorkDayRequest {

  @NotNull
  private DayName dayName;

  @NotNull
  private Set<LessonDayRequest> lessons;

}
