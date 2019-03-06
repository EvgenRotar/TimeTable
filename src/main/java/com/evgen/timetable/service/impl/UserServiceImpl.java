package com.evgen.timetable.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.evgen.timetable.mapper.UserMapper;
import com.evgen.timetable.model.dto.user.UserResponse;
import com.evgen.timetable.model.dto.user.UserUpdateRequest;
import com.evgen.timetable.model.entity.User;
import com.evgen.timetable.repository.UserRepository;
import com.evgen.timetable.service.api.UserService;
import com.evgen.timetable.util.OptionalDaoUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Transactional
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final OptionalDaoUtil optionalDaoUtil;

  @Override
  @Transactional(readOnly = true)
  public List<UserResponse> findAllUsers() {
    return userMapper.userToUserListResponse(userRepository.findAll());
  }

  @Override
  @Transactional(readOnly = true)
  public UserResponse getUserById(Long id) {
    return userMapper.userToUserResponse(optionalDaoUtil.getUserByIdOrThrowException(id));
  }

  @Override
  public void deleteUser(Long id) {
    userRepository.delete(optionalDaoUtil.getUserByIdOrThrowException(id));
  }

  @Override
  public void updateUser(Long userId, UserUpdateRequest userUpdateRequest) {
    User currentUser = optionalDaoUtil.getUserByIdOrThrowException(userId);
    userMapper.mapUserFromUserUpdateRequest(userUpdateRequest, currentUser);
    userRepository.save(currentUser);
  }

}