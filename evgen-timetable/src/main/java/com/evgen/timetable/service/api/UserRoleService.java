package com.evgen.timetable.service.api;

import com.evgen.timetable.exception.NotFoundException;
import com.evgen.timetable.model.dto.user.UserRoleRequest;
import com.evgen.timetable.model.dto.user.UserRoleResponse;

public interface UserRoleService {

  UserRoleResponse addUserRole(UserRoleRequest userRoleRequest) throws NotFoundException;

  void deleteUserRole(Long id) throws NotFoundException;

}