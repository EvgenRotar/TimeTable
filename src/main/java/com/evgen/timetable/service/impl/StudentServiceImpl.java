package com.evgen.timetable.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.evgen.timetable.mapper.UserMapper;
import com.evgen.timetable.model.student.Student;
import com.evgen.timetable.model.student.StudentResponse;
import com.evgen.timetable.repository.StudentRepository;
import com.evgen.timetable.service.api.StudentService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Transactional
public class StudentServiceImpl implements StudentService {

  private final StudentRepository studentRepository;
  private final UserMapper userMapper;

  private Student getStudentByIdOrThrowException(Long id) {
    return studentRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("student not found"));
  }

  @Override
  public StudentResponse getStudentById(Long id) {
    return userMapper.studentToStudentResponse(getStudentByIdOrThrowException(id));

  }

}