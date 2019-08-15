package com.evgen.timetable.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.evgen.timetable.model.dto.user.UserResponse;
import com.evgen.timetable.model.dto.user.UserUpdateRequest;
import com.evgen.timetable.service.api.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@CrossOrigin
@Controller
public class UserController {

  private final UserService userService;

  @GetMapping("/api/users")
  public ResponseEntity<List<UserResponse>> getAllUser() {
    return ResponseEntity.ok().body(userService.findAllUsers());
  }

  @GetMapping("/api/users/{id}")
  public ResponseEntity<UserResponse> getUser(
      @PathVariable("id") Long userId) {
    return ResponseEntity.ok().body(userService.getUserById(userId));
  }

  @DeleteMapping("/api/users/{id}")
  public ResponseEntity<Void> deleteUserById(
      @PathVariable("id") Long userId) {
    userService.deleteUser(userId);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/api/users/{id}")
  public ResponseEntity<Void> updateUser(
      @PathVariable("id") Long userId,
      @RequestBody @Valid UserUpdateRequest user) {
    userService.updateUser(userId, user);
    return ResponseEntity.accepted().build();
  }

}