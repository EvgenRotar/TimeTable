package com.evgen.timetable.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.evgen.timetable.mapper.UserMapper;
import com.evgen.timetable.model.group.Group;
import com.evgen.timetable.model.student.Student;
import com.evgen.timetable.model.student.StudentResponse;
import com.evgen.timetable.model.student.StudentUpdateRequest;
import com.evgen.timetable.repository.GroupRepository;
import com.evgen.timetable.repository.StudentRepository;
import com.evgen.timetable.service.api.StudentService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Transactional
public class StudentServiceImpl implements StudentService {

  private final StudentRepository studentRepository;
  private final GroupRepository groupRepository;
  private final UserMapper userMapper;

  private Student getStudentByIdOrThrowException(Long id) {
    return studentRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("student not found"));
  }

  private Group getGroupOrThrowException(String groupName) throws RuntimeException {
    return groupRepository.findByGroupName(groupName)
        .orElseThrow(() -> new RuntimeException(String.format("group with name %s not found", groupName)));
  }

  @Override
  @Transactional(readOnly = true)
  public StudentResponse getStudentById(Long id) {
    return userMapper.studentToStudentResponse(getStudentByIdOrThrowException(id));
  }

  @Override
  public void updateGroupStudentById(Long id, StudentUpdateRequest studentUpdateRequest) {
    Group group = getGroupOrThrowException(studentUpdateRequest.getGroupName());
    Student student = getStudentByIdOrThrowException(id);
    student.setGroup(group);
    studentRepository.save(student);
  }

}