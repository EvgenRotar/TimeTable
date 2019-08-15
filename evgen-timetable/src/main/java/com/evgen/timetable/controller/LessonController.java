package com.evgen.timetable.controller;

import java.util.List;

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

import com.evgen.timetable.model.entity.Lesson;
import com.evgen.timetable.model.dto.lesson.LessonRequest;
import com.evgen.timetable.service.api.LessonService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@CrossOrigin
@Controller
public class LessonController {

  private final LessonService lessonService;

  @GetMapping("/api/lessons")
  public ResponseEntity<List<Lesson>> getAllLessons() {
    return ResponseEntity.ok().body(lessonService.getAllLessons());
  }

  @GetMapping("/api/lessons/{id}")
  public ResponseEntity<Lesson> getLessonById(
      @PathVariable("id") Long lessonId) {
    return ResponseEntity.ok().body(lessonService.getLessonById(lessonId));
  }

  @PostMapping("/api/lessons")
  public ResponseEntity<Lesson> addLesson(
      @RequestBody @Valid LessonRequest lessonRequest) {
    Lesson lesson = lessonService.addLesson(lessonRequest);
    UriComponents uriComponents = UriComponentsBuilder.fromPath("/api/groups/{id}")
        .buildAndExpand(lesson.getId());
    return ResponseEntity.created(uriComponents.toUri()).body(lesson);
  }

  @DeleteMapping("/api/lessons/{id}")
  public ResponseEntity<Void> deleteLessonById(
      @PathVariable("id") Long lessonId) {
    lessonService.deleteLessonById(lessonId);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/api/lessons/{id}")
  public ResponseEntity<Void> updateLessonById(
      @PathVariable("id") Long lessonId,
      @RequestBody @Valid LessonRequest lessonRequest) {
    lessonService.updateLessonById(lessonId, lessonRequest);
    return ResponseEntity.accepted().build();
  }

}