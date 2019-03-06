package com.evgen.timetable.model.dto.user;

import com.evgen.timetable.model.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class UserRoleResponse {

  private Long userRoleId;

  private Role role;

  private UserResponse user;

}