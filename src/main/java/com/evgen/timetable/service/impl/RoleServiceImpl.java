package com.evgen.timetable.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.evgen.timetable.model.role.Role;
import com.evgen.timetable.repository.RoleRepository;
import com.evgen.timetable.service.api.RoleService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

  private final RoleRepository roleRepository;

  @Override
  public List<Role> getAllRoles() {
    return roleRepository.findAll();
  }

}