package com.evgen.timetable.service.api;

import java.util.List;

import com.evgen.timetable.model.dto.group.GroupFullResponse;
import com.evgen.timetable.model.dto.group.GroupResponse;
import com.evgen.timetable.model.dto.group.GroupSaveRequest;
import com.evgen.timetable.model.dto.group.GroupWithStudentsResponse;
import com.evgen.timetable.model.dto.group.GroupWithTimeTableResponse;

public interface GroupService {

  List<GroupResponse> getAllGroups();

  GroupWithStudentsResponse getGroupWithStudents(Long groupId);

  GroupWithTimeTableResponse getGroupWithTimeTableResponse(Long groupId);

  GroupFullResponse getGroup(Long groupId);

  GroupResponse addGroup(GroupSaveRequest groupSaveRequest);

  void updateGroup(Long groupId, GroupSaveRequest groupSaveRequest);

  void deleteGroup(Long groupId);

}