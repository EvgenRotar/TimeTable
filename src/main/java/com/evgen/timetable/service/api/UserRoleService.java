package com.evgen.timetable.service.api;

import com.evgen.timetable.model.entity.UserRole;
import com.evgen.timetable.model.dto.user.UserRoleRequest;

public interface UserRoleService {

  UserRole addUserRole(UserRoleRequest userRoleRequest) throws RuntimeException;

  void deleteUserRole(Long id) throws RuntimeException;

}