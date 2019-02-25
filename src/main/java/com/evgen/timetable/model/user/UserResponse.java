package com.evgen.timetable.model.user;

import java.util.Set;

import com.evgen.timetable.model.role.Role;

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
public class UserResponse {

  private Long userId;
  private String login;
  private String userName;
  private String userSurname;
  private Set<Role> roles;

}