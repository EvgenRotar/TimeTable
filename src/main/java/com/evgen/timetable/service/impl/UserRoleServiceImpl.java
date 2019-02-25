package com.evgen.timetable.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.evgen.timetable.model.role.Role;
import com.evgen.timetable.model.user.User;
import com.evgen.timetable.model.user.UserRole;
import com.evgen.timetable.model.user.UserRoleRequest;
import com.evgen.timetable.repository.RoleRepository;
import com.evgen.timetable.repository.UserRepository;
import com.evgen.timetable.repository.UserRoleRepository;
import com.evgen.timetable.service.api.UserRoleService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

  private final UserRoleRepository userRoleRepository;
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;

  private UserRole getUserRoleByIdOrThrowException(Long id) {
    return userRoleRepository.findById(id)
        .orElseThrow(() -> new RuntimeException(String.format("role assignment with id %d not found", id)));
  }

  private User getUserByIdOrThrowException(Long id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException(String.format("user with id %d not found", id)));
  }

  public Role getRoleByIdOrThrowException(Long id) throws RuntimeException {
    return roleRepository.findById(id)
        .orElseThrow(() -> new RuntimeException(String.format("role with id %d not found", id)));
  }

  @Override
  public UserRole addUserRole(UserRoleRequest userRoleRequest) throws RuntimeException {
    UserRole userRole = new UserRole();

    User user = getUserByIdOrThrowException(userRoleRequest.getUserId());
    Role role = getRoleByIdOrThrowException(userRoleRequest.getRoleId());

    userRole.setUser(user);
    userRole.setRole(role);

    return userRoleRepository.save(userRole);
  }

  @Override
  public void deleteUserRole(Long id) throws RuntimeException {
    userRoleRepository.delete(getUserRoleByIdOrThrowException(id));
  }

}