package com.evgen.timetable.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import com.evgen.timetable.model.role.Role;
import com.evgen.timetable.service.api.RoleService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@CrossOrigin
@Controller
public class RoleController {

  private final RoleService roleService;

  @GetMapping("/api/roles")
  public ResponseEntity<List<Role>> getRoles() {
    List<Role> response = roleService.getAllRoles();
    return ResponseEntity.ok().body(response);
  }

}