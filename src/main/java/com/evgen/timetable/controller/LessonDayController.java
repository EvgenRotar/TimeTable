package com.evgen.timetable.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.evgen.timetable.model.lesson.LessonDayRequest;
import com.evgen.timetable.model.lesson.LessonDayResponse;
import com.evgen.timetable.model.lesson.LessonDaySaveRequest;
import com.evgen.timetable.service.api.LessonDayService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@CrossOrigin
@Controller
public class LessonDayController {

  private final LessonDayService lessonDayService;

  @GetMapping("/api/lessonDays/{id}")
  public ResponseEntity<LessonDayResponse> getLessonDayById(
      @PathVariable("id") Long lessonDayId) {
    return ResponseEntity.ok().body(lessonDayService.getLessonDayById(lessonDayId));
  }

  @PostMapping("/api/lessonDays")
  public ResponseEntity<LessonDayResponse> addLessonDay(
      @RequestBody @Valid LessonDaySaveRequest lessonDaySaveRequest) {
    LessonDayResponse response = lessonDayService.addLessonDay(lessonDaySaveRequest);
    UriComponents uriComponents = UriComponentsBuilder.fromPath("/api/lessonDays/{id}")
        .buildAndExpand(response.getId());
    return ResponseEntity.created(uriComponents.toUri()).body(response);
  }

  @DeleteMapping("/api/lessonDays/{id}")
  public ResponseEntity<Void> deleteLessonDayById(
      @PathVariable("id") Long lessonDayId) {
    lessonDayService.deleteLessonDayById(lessonDayId);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/api/lessonDays/{id}")
  public ResponseEntity<Void> updateLessonDayById(
      @PathVariable("id") Long lessonDayId,
      @RequestBody @Valid LessonDayRequest lessonDayRequest) {
    lessonDayService.updateLessonDay(lessonDayId, lessonDayRequest);
    return ResponseEntity.accepted().build();
  }

}