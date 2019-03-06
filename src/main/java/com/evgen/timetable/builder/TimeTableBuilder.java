package com.evgen.timetable.builder;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.evgen.timetable.model.entity.Group;
import com.evgen.timetable.model.entity.TimeTable;
import com.evgen.timetable.model.entity.WorkDay;
import com.evgen.timetable.model.name.DayName;
import com.evgen.timetable.model.name.TimeTableName;
import com.evgen.timetable.repository.TimeTableRepository;
import com.evgen.timetable.repository.WorkDayRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class TimeTableBuilder {

  private final TimeTableRepository timeTableRepository;
  private final WorkDayRepository workDayRepository;

  private final List<DayName> WEEK = Arrays
      .asList(DayName.MONDAY, DayName.TUESDAY, DayName.WEDNESDAY, DayName.THURSDAY, DayName.FRIDAY, DayName.SATURDAY);
  private final List<TimeTableName> TIMETABLES = Arrays.asList(TimeTableName.FIRST, TimeTableName.SECOND);

  public void createEmptyTimeTables(Group group) {
    TIMETABLES.forEach(timeTableName -> {
      TimeTable timeTable = timeTableRepository.save(
          TimeTable.builder()
              .timeTableName(timeTableName)
              .group(group)
              .build()
      );
      WEEK.forEach(dayName -> workDayRepository.save(
          WorkDay.builder()
              .timeTable(timeTable)
              .dayName(dayName)
              .build())
      );
    });
  }
}
