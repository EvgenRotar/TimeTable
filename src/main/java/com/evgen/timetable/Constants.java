package com.evgen.timetable;

import java.util.Arrays;
import java.util.List;

import com.evgen.timetable.model.name.DayName;
import com.evgen.timetable.model.name.TimeTableName;

public class Constants {

  public static final String USERNAME_PATTERN = "^[a-z0-9_-]{6,30}$";

  public static final String PASSWORD_PATTERN = "^[a-zA-Z0-9]{8,30}$";

  public static final List<DayName> WEEK = Arrays
      .asList(DayName.MONDAY, DayName.TUESDAY, DayName.WEDNESDAY, DayName.THURSDAY, DayName.FRIDAY, DayName.SATURDAY);

  public static final List<TimeTableName> TIMETABLES = Arrays.asList(TimeTableName.FIRST, TimeTableName.SECOND);

}