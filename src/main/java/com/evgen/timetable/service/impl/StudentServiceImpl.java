package com.evgen.timetable.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.evgen.timetable.exception.NotFoundException;
import com.evgen.timetable.mapper.UserMapper;
import com.evgen.timetable.model.dto.student.StudentResponse;
import com.evgen.timetable.model.dto.student.StudentUpdateRequest;
import com.evgen.timetable.model.entity.Group;
import com.evgen.timetable.model.entity.Student;
import com.evgen.timetable.repository.StudentRepository;
import com.evgen.timetable.service.api.StudentService;
import com.evgen.timetable.util.OptionalDaoUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Transactional
public class StudentServiceImpl implements StudentService {

  private final StudentRepository studentRepository;
  private final UserMapper userMapper;
  private final OptionalDaoUtil optionalDaoUtil;

  @Override
  @Transactional(readOnly = true)
  public StudentResponse getStudentById(Long id) throws NotFoundException {
    return userMapper.studentToStudentResponse(optionalDaoUtil.getStudentByIdOrThrowException(id));
  }

  @Override
  public void updateGroupStudentById(Long id, StudentUpdateRequest studentUpdateRequest) throws NotFoundException {
    Group group = optionalDaoUtil.getGroupOrThrowException(studentUpdateRequest.getGroupName());
    Student student = optionalDaoUtil.getStudentByIdOrThrowException(id);
    student.setGroup(group);
    studentRepository.save(student);
  }

}