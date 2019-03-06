package com.evgen.timetable.service.api;

import java.util.List;

import com.evgen.timetable.exception.NotFoundException;
import com.evgen.timetable.model.dto.group.GroupFullResponse;
import com.evgen.timetable.model.dto.group.GroupResponse;
import com.evgen.timetable.model.dto.group.GroupSaveRequest;
import com.evgen.timetable.model.dto.group.GroupWithStudentsResponse;
import com.evgen.timetable.model.dto.group.GroupWithTimeTableResponse;

public interface GroupService {

  List<GroupResponse> getAllGroups();

  GroupWithStudentsResponse getGroupWithStudents(Long groupId) throws NotFoundException;

  GroupWithTimeTableResponse getGroupWithTimeTableResponse(Long groupId) throws NotFoundException;

  GroupFullResponse getGroup(Long groupId) throws NotFoundException;

  GroupResponse addGroup(GroupSaveRequest groupSaveRequest);

  void updateGroup(Long groupId, GroupSaveRequest groupSaveRequest) throws NotFoundException;

  void deleteGroup(Long groupId) throws NotFoundException;

}