package com.evgen.timetable.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.evgen.timetable.model.group.Group;
import com.evgen.timetable.model.group.GroupFullResponse;
import com.evgen.timetable.model.group.GroupResponse;
import com.evgen.timetable.model.group.GroupSaveRequest;
import com.evgen.timetable.model.group.GroupWithStudentsResponse;
import com.evgen.timetable.model.group.GroupWithTimeTableResponse;

@Mapper(componentModel = "spring", uses = {UserResponseMapper.class, TimeTableMapper.class})
public interface GroupMapper {

  @IterableMapping(elementTargetType = GroupResponse.class)
  List<GroupResponse> groupsListToGroupResponse(List<Group> groups);

  @Mapping(target = "groupId", source = "id")
  GroupResponse groupToGroupResponse(Group group);

  @Mapping(target = "groupId", source = "id")
  GroupWithStudentsResponse groupToGroupWithStudentsResponse(Group group);

  @Mapping(target = "groupId", source = "id")
  GroupWithTimeTableResponse groupToGroupWithTimeTableResponse(Group group);

  @Mapping(target = "groupId", source = "id")
  GroupFullResponse groupToGroupFullResponse(Group group);

  void mapGroupFromGroupSaveRequest(GroupSaveRequest groupSaveRequest, @MappingTarget Group group);

}