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

import com.evgen.timetable.model.dto.timeTable.TimeTableRequest;
import com.evgen.timetable.model.dto.timeTable.TimeTableResponse;
import com.evgen.timetable.model.dto.timeTable.TimeTableUpdateRequest;
import com.evgen.timetable.service.api.TimeTableService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@CrossOrigin
@Controller
public class TimeTableController {

  private final TimeTableService timeTableService;

  @GetMapping("/api/timeTables/{id}")
  public ResponseEntity<TimeTableResponse> getTimeTableById(
      @PathVariable("id") Long timeTableId) {
    return ResponseEntity.ok().body(timeTableService.getTimeTableById(timeTableId));
  }

  @PostMapping("/api/timeTables")
  public ResponseEntity<TimeTableResponse> addTimeTable(
      @RequestBody @Valid TimeTableRequest timeTableRequest) {
    TimeTableResponse timeTableResponse = timeTableService.addTimeTable(timeTableRequest);
    UriComponents uriComponents = UriComponentsBuilder.fromPath("/api/timeTables/{id}")
        .buildAndExpand(timeTableResponse.getTimeTableId());
    return ResponseEntity.created(uriComponents.toUri()).body(timeTableResponse);
  }

  @DeleteMapping("/api/timeTables/{id}")
  public ResponseEntity<Void> deleteTameTableById(
      @PathVariable("id") Long timeTableId) {
    timeTableService.deleteTimeTableById(timeTableId);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/api/timeTables/{id}")
  public ResponseEntity<Void> updateTimeTableById(
      @PathVariable("id") Long timeTableId,
      @RequestBody @Valid TimeTableUpdateRequest timeTableUpdateRequest) {
    timeTableService.updateTimeTableById(timeTableId, timeTableUpdateRequest);
    return ResponseEntity.accepted().build();
  }

}
