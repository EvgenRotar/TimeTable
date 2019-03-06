package com.evgen.timetable.service.api;

import java.util.List;

import com.evgen.timetable.model.dto.user.UserResponse;
import com.evgen.timetable.model.dto.user.UserUpdateRequest;

public interface UserService {

  List<UserResponse> findAllUsers();

  UserResponse getUserById(Long id);

  void deleteUser(Long id);

  void updateUser(Long userId, UserUpdateRequest user);

}