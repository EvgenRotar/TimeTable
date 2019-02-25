package com.evgen.timetable.service.api;

import java.util.List;

import com.evgen.timetable.model.user.UserResponse;
import com.evgen.timetable.model.user.UserUpdateRequest;

public interface UserService {

  List<UserResponse> findAllUsers();

  UserResponse getUserById(Long id);

  void deleteUser(Long id);

  void updateUser(Long userId, UserUpdateRequest user);

}