package com.evgen.timetable.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.evgen.timetable.model.role.Role;
import com.evgen.timetable.model.user.UserRole;

@Mapper(componentModel = "spring")
public interface UserRoleMapper {

  @Mapping(target = "roleName", source = "role.roleName")
  Role userRoleToRole(UserRole userRole);

}