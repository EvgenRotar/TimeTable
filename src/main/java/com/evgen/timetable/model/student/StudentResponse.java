package com.evgen.timetable.model.student;

import java.util.Set;

import com.evgen.timetable.model.timeTable.TimeTableResponse;

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
public class StudentResponse {

  private Long studentId;
  private String login;
  private String userName;
  private String userSurname;
  private String groupName;
  private Set<TimeTableResponse> timeTable;

}