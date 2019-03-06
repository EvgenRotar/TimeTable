package com.evgen.timetable.model.dto.teacher;

import java.util.Set;

import com.evgen.timetable.model.dto.timeTable.TimeTableResponse;

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
public class TeacherResponse {

  private Long teacherId;
  private String login;
  private String userName;
  private String userSurname;
  private Set<TimeTableResponse> timeTables;

}