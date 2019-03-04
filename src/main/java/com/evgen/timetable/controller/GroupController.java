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

import com.evgen.timetable.model.group.GroupFullResponse;
import com.evgen.timetable.model.group.GroupResponse;
import com.evgen.timetable.model.group.GroupSaveRequest;
import com.evgen.timetable.model.group.GroupWithStudentsResponse;
import com.evgen.timetable.model.group.GroupWithTimeTableResponse;
import com.evgen.timetable.service.api.GroupService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@CrossOrigin
@Controller
public class GroupController {

  private final GroupService groupService;

  @GetMapping("/api/groups")
  public ResponseEntity<List<GroupResponse>> getAllGroups() {
    return ResponseEntity.ok().body(groupService.getAllGroups());
  }

  @GetMapping(value = "/api/groups/{id}", params = "projection=StudentsInfo")
  public ResponseEntity<GroupWithStudentsResponse> getGroupWithStudents(
      @PathVariable("id") Long groupId) {
    return ResponseEntity.ok().body(groupService.getGroupWithStudents(groupId));
  }

  @GetMapping(value = "/api/groups/{id}", params = "projection=TimeTableInfo")
  public ResponseEntity<GroupWithTimeTableResponse> getGroupWithTimeTable(
      @PathVariable("id") Long groupId) {
    return ResponseEntity.ok().body(groupService.getGroupWithTimeTableResponse(groupId));
  }

  @GetMapping("/api/groups/{id}")
  public ResponseEntity<GroupFullResponse> getGroup(
      @PathVariable("id") Long groupId) {
    return ResponseEntity.ok().body(groupService.getGroup(groupId));
  }

  @PostMapping("/api/groups")
  public ResponseEntity<GroupResponse> addGroup(
      @RequestBody @Valid GroupSaveRequest groupSaveRequest) {
    GroupResponse response = groupService.addGroup(groupSaveRequest);
    UriComponents uriComponents = UriComponentsBuilder.fromPath("/api/groups/{id}")
        .buildAndExpand(response.getGroupId());
    return ResponseEntity.created(uriComponents.toUri()).body(response);
  }

  @PutMapping("/api/groups/{id}")
  public ResponseEntity<Void> updateGroup(
      @RequestBody @Valid GroupSaveRequest groupSaveRequest,
      @PathVariable("id") Long groupId) {
    groupService.updateGroup(groupId, groupSaveRequest);
    return ResponseEntity.accepted().build();
  }

  @DeleteMapping("/api/groups/{id}")
  public ResponseEntity<Void> deleteGroupById(
      @PathVariable("id") Long groupId) {
    groupService.deleteGroup(groupId);
    return ResponseEntity.ok().build();
  }

}