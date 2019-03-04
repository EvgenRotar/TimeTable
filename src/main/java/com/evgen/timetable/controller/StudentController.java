package com.evgen.timetable.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.evgen.timetable.model.student.StudentResponse;
import com.evgen.timetable.model.student.StudentUpdateRequest;
import com.evgen.timetable.service.api.StudentService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@CrossOrigin
@Controller
public class StudentController {

  private final StudentService studentService;

  @GetMapping("/api/students/{id}")
  public ResponseEntity<StudentResponse> getStudent(
      @PathVariable("id") Long studentId) {
    return ResponseEntity.ok().body(studentService.getStudentById(studentId));
  }

  @PutMapping("/api/students/{id}")
  public ResponseEntity<Void> updateStudentGroup(
      @PathVariable("id") Long studentId,
      @RequestBody @Valid StudentUpdateRequest studentUpdateRequest) {
    studentService.updateGroupStudentById(studentId, studentUpdateRequest);
    return ResponseEntity.accepted().build();
  }

}