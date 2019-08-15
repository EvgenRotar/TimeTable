package com.evgen.timetable.model.dto.user;

import javax.validation.constraints.NotBlank;

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
public class UserRoleRequest {

  @NotBlank
  private Long userId;

  @NotBlank
  private Long roleId;

}