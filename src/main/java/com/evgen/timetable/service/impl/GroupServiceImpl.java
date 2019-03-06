package com.evgen.timetable.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.evgen.timetable.builder.TimeTableBuilder;
import com.evgen.timetable.mapper.GroupMapper;
import com.evgen.timetable.model.dto.group.GroupFullResponse;
import com.evgen.timetable.model.dto.group.GroupResponse;
import com.evgen.timetable.model.dto.group.GroupSaveRequest;
import com.evgen.timetable.model.dto.group.GroupWithStudentsResponse;
import com.evgen.timetable.model.dto.group.GroupWithTimeTableResponse;
import com.evgen.timetable.model.entity.Group;
import com.evgen.timetable.repository.GroupRepository;
import com.evgen.timetable.service.api.GroupService;
import com.evgen.timetable.util.OptionalDaoUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Transactional
public class GroupServiceImpl implements GroupService {

  private final GroupRepository groupRepository;
  private final GroupMapper groupMapper;
  private final TimeTableBuilder timeTableBuilder;
  private final OptionalDaoUtil optionalDaoUtil;

  @Override
  @Transactional(readOnly = true)
  public List<GroupResponse> getAllGroups() {
    List<Group> groups = groupRepository.findAll();
    return groupMapper.groupsListToGroupResponse(groups);
  }

  @Override
  @Transactional(readOnly = true)
  public GroupWithStudentsResponse getGroupWithStudents(Long groupId) {
    return groupMapper.groupToGroupWithStudentsResponse(optionalDaoUtil.getGroupByIdOrThrowException(groupId));
  }

  @Override
  @Transactional(readOnly = true)
  public GroupWithTimeTableResponse getGroupWithTimeTableResponse(Long groupId) {
    return groupMapper.groupToGroupWithTimeTableResponse(optionalDaoUtil.getGroupByIdOrThrowException(groupId));
  }

  @Override
  @Transactional(readOnly = true)
  public GroupFullResponse getGroup(Long groupId) {
    return groupMapper.groupToGroupFullResponse(optionalDaoUtil.getGroupByIdOrThrowException(groupId));
  }

  @Override
  public GroupResponse addGroup(GroupSaveRequest groupSaveRequest) {
    Group group = new Group();
    groupMapper.mapGroupFromGroupSaveRequest(groupSaveRequest, group);
    group = groupRepository.save(group);
    timeTableBuilder.createEmptyTimeTables(group);

    return groupMapper.groupToGroupResponse(group);
  }

  @Override
  public void updateGroup(Long groupId, GroupSaveRequest groupSaveRequest) {
    Group group = optionalDaoUtil.getGroupByIdOrThrowException(groupId);
    groupMapper.mapGroupFromGroupSaveRequest(groupSaveRequest, group);
    groupRepository.save(group);
  }

  @Override
  public void deleteGroup(Long groupId) {
    groupRepository.delete(optionalDaoUtil.getGroupByIdOrThrowException(groupId));
  }

}