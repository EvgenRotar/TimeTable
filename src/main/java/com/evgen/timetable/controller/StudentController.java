package com.evgen.timetable.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.evgen.timetable.model.student.StudentResponse;
import com.evgen.timetable.service.api.StudentService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@CrossOrigin
@Controller
public class StudentController {

  private final StudentService studentService;

  @GetMapping("/api/students/{id}")
  public ResponseEntity<StudentResponse> getStudent(
      @PathVariable("id") Long userId) {
    return ResponseEntity.ok().body(studentService.getStudentById(userId));
  }

}