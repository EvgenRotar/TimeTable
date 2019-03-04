package com.evgen.timetable.builder;

import org.springframework.stereotype.Component;

import com.evgen.timetable.model.group.Group;
import com.evgen.timetable.model.timeTable.TimeTable;
import com.evgen.timetable.model.timeTable.TimeTableName;
import com.evgen.timetable.model.workDay.DayName;
import com.evgen.timetable.model.workDay.WorkDay;
import com.evgen.timetable.repository.TimeTableRepository;
import com.evgen.timetable.repository.WorkDayRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class TimeTableBuilder {

  private final TimeTableRepository timeTableRepository;
  private final WorkDayRepository workDayRepository;

  public void createStudentTimeTable(Group group, TimeTableName timeTableName) {
    TimeTable studentTimeTable = new TimeTable();
    studentTimeTable.setTimeTableName(timeTableName);
    studentTimeTable.setGroup(group);

    studentTimeTable = timeTableRepository.save(studentTimeTable);
    addWorkDays(studentTimeTable);
  }

  private void addWorkDays(TimeTable timeTable) {
    saveWorkDay(timeTable, DayName.MONDAY);
    saveWorkDay(timeTable, DayName.TUESDAY);
    saveWorkDay(timeTable, DayName.WEDNESDAY);
    saveWorkDay(timeTable, DayName.THURSDAY);
    saveWorkDay(timeTable, DayName.FRIDAY);
    saveWorkDay(timeTable, DayName.SATURDAY);
  }

  private void saveWorkDay(TimeTable timeTable, DayName dayName) {
    WorkDay day = new WorkDay();
    day.setTimeTable(timeTable);
    day.setDayName(dayName);
    workDayRepository.save(day);
  }

}
