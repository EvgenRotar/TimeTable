package com.evgen.timetable.service.api;

import java.util.List;

import com.evgen.timetable.exception.NotFoundException;
import com.evgen.timetable.model.dto.user.UserResponse;
import com.evgen.timetable.model.dto.user.UserUpdateRequest;

public interface UserService {

  List<UserResponse> findAllUsers();

  UserResponse getUserById(Long id) throws NotFoundException;

  void deleteUser(Long id) throws NotFoundException;

  void updateUser(Long userId, UserUpdateRequest user) throws NotFoundException;

}