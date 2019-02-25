package com.evgen.timetable.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.evgen.timetable.model.user.UserRole;
import com.evgen.timetable.model.user.UserRoleRequest;
import com.evgen.timetable.service.api.UserRoleService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@CrossOrigin
@Controller
public class UserRoleController {

  private UserRoleService userRoleService;

  @PostMapping("/api/user-role")
  public ResponseEntity<UserRole> addRoleAssignment(
      @RequestBody @Valid UserRoleRequest userRoleRequest) {
    UserRole response = userRoleService.addUserRole(userRoleRequest);
    UriComponents uriComponents = UriComponentsBuilder.fromPath("/api/user-role/{id}")
        .buildAndExpand(response.getId());
    return ResponseEntity.created(uriComponents.toUri()).body(response);
  }

  @DeleteMapping("/api/user-role/{id}")
  public ResponseEntity<Void> deleteRoleAssignment(
      @PathVariable("id") Long roleAssignmentId) {
    userRoleService.deleteUserRole(roleAssignmentId);
    return ResponseEntity.ok().build();
  }

}