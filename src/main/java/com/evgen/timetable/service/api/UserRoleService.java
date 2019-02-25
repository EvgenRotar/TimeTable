package com.evgen.timetable.service.api;

import com.evgen.timetable.model.user.UserRole;
import com.evgen.timetable.model.user.UserRoleRequest;

public interface UserRoleService {

  UserRole addUserRole(UserRoleRequest userRoleRequest) throws RuntimeException;

  void deleteUserRole(Long id) throws RuntimeException;

}