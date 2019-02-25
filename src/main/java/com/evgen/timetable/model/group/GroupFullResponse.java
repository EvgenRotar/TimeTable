package com.evgen.timetable.model.group;

import java.util.Set;

import com.evgen.timetable.model.user.UserResponse;
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
public class GroupFullResponse {

  private String groupId;
  private String groupName;
  private Set<UserResponse> students;
  private Set<TimeTableResponse> timeTable;

}