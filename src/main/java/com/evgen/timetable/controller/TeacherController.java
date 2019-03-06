package com.evgen.timetable.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.evgen.timetable.model.dto.teacher.TeacherResponse;
import com.evgen.timetable.service.api.TeacherService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@CrossOrigin
@Controller
public class TeacherController {

  private final TeacherService teacherService;

  @GetMapping("/api/teachers/{id}")
  public ResponseEntity<TeacherResponse> getTeacher(
      @PathVariable("id") Long teacherId) {
    return ResponseEntity.ok().body(teacherService.getTeacherById(teacherId));
  }

}
