package com.evgen.timetable.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.evgen.timetable.model.dto.student.StudentRegistrationRequest;
import com.evgen.timetable.model.dto.student.StudentResponse;
import com.evgen.timetable.model.dto.teacher.TeacherRegistrationRequest;
import com.evgen.timetable.model.dto.teacher.TeacherResponse;
import com.evgen.timetable.service.api.RegistrationService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@CrossOrigin
@Controller
public class RegistrationController {

  private final RegistrationService registrationService;

  @PostMapping(value = "/api/sign-up", params = "projection=student")
  public ResponseEntity<StudentResponse> registrationStudent(
      @RequestBody @Valid StudentRegistrationRequest student) {
    return ResponseEntity.ok().body(registrationService.registrationStudent(student));
  }

  @PostMapping(value = "/api/sign-up", params = "projection=teacher")
  public ResponseEntity<TeacherResponse> registrationTeacher(
      @RequestBody @Valid TeacherRegistrationRequest teacherRegistrationRequest) {
    return ResponseEntity.ok().body(registrationService.registrationTeacher(teacherRegistrationRequest));
  }

}