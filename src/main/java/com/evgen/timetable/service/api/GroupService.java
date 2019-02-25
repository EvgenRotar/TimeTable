package com.evgen.timetable.service.api;

import java.util.List;

import com.evgen.timetable.model.group.GroupFullResponse;
import com.evgen.timetable.model.group.GroupResponse;
import com.evgen.timetable.model.group.GroupSaveRequest;
import com.evgen.timetable.model.group.GroupWithStudentsResponse;
import com.evgen.timetable.model.group.GroupWithTimeTableResponse;

public interface GroupService {

  List<GroupResponse> getAllGroups();

  GroupWithStudentsResponse getGroupWithStudents(Long groupId);

  GroupWithTimeTableResponse groupWithTimeTableResponse(Long groupId);

  GroupFullResponse getGroup(Long groupId);

  GroupResponse addGroup(GroupSaveRequest groupSaveRequest);

  void updateGroup(Long groupId, GroupSaveRequest groupSaveRequest);

  void deleteGroup(Long groupId);

}