package com.evgen.timetable.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.evgen.timetable.mapper.UserMapper;
import com.evgen.timetable.model.user.User;
import com.evgen.timetable.model.user.UserResponse;
import com.evgen.timetable.model.user.UserUpdateRequest;
import com.evgen.timetable.repository.UserRepository;
import com.evgen.timetable.service.api.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Transactional
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @Override
  @Transactional(readOnly = true)
  public List<UserResponse> findAllUsers() {
    return userMapper.userToUserListResponse(userRepository.findAll());
  }

  @Override
  public UserResponse getUserById(Long id) {
    return userMapper.userToUserResponse(getUserByIdOrThrowException(id));
  }

  @Override
  public void deleteUser(Long id) {
    userRepository.delete(getUserByIdOrThrowException(id));
  }

  @Override
  public void updateUser(Long userId, UserUpdateRequest userUpdateRequest) {
    User currentUser = getUserByIdOrThrowException(userId);

    userMapper.mapUserFromUserUpdateRequest(userUpdateRequest, currentUser);
    userRepository.save(currentUser);
  }

  private User getUserByIdOrThrowException(Long id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("user not found"));
  }

}