package com.evgen.timetable.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.evgen.timetable.model.entity.User;
import com.evgen.timetable.model.dto.user.UserResponse;

@Mapper(componentModel = "spring", uses = UserRoleMapper.class)
public interface UserResponseMapper {

  @Mapping(target = "userId", source = "id")
  UserResponse userToUserResponse(User user);

}