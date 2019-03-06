package com.evgen.timetable.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.evgen.timetable.mapper.UserRoleMapper;
import com.evgen.timetable.model.dto.user.UserRoleRequest;
import com.evgen.timetable.model.dto.user.UserRoleResponse;
import com.evgen.timetable.model.entity.UserRole;
import com.evgen.timetable.repository.UserRoleRepository;
import com.evgen.timetable.service.api.UserRoleService;
import com.evgen.timetable.util.OptionalDaoUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

  private final UserRoleRepository userRoleRepository;
  private final OptionalDaoUtil optionalDaoUtil;
  private final UserRoleMapper userRoleMapper;

  @Override
  public UserRoleResponse addUserRole(UserRoleRequest userRoleRequest) throws RuntimeException {
    UserRole userRole = UserRole.builder()
        .user(optionalDaoUtil.getUserByIdOrThrowException(userRoleRequest.getUserId()))
        .role(optionalDaoUtil.getRoleByIdOrThrowException(userRoleRequest.getRoleId()))
        .build();

    return userRoleMapper.userRoleToUserRoleResponse(userRoleRepository.save(userRole));
  }

  @Override
  public void deleteUserRole(Long id) throws RuntimeException {
    userRoleRepository.delete(optionalDaoUtil.getUserRoleByIdOrThrowException(id));
  }

}