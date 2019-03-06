package com.evgen.timetable.model.dto.workDay;

import java.util.Set;

import com.evgen.timetable.model.dto.lesson.LessonDayResponse;
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
public class WorkDayResponse {

  private Long workDayId;
  private DayName dayName;
  private Set<LessonDayResponse> lessons;

}
