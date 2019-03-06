package com.evgen.timetable.model.dto.workDay;

import java.util.Set;

import javax.validation.constraints.NotNull;

import com.evgen.timetable.model.dto.lesson.LessonDayRequest;
import com.evgen.timetable.model.name.DayName;

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