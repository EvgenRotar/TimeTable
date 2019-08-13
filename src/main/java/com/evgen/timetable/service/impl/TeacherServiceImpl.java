package com.evgen.timetable.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.evgen.timetable.exception.NotFoundException;
import com.evgen.timetable.mapper.TeacherMapper;
import com.evgen.timetable.model.dto.teacher.TeacherResponse;
import com.evgen.timetable.service.api.TeacherService;
import com.evgen.timetable.util.OptionalDaoUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

  private final TeacherMapper teacherMapper;
  private final OptionalDaoUtil optionalDaoUtil;

  @Override
  @Transactional(readOnly = true)
  public TeacherResponse getTeacherById(Long id) throws NotFoundException {
    return teacherMapper.mapTeacherResponse(optionalDaoUtil.getTeacherByIdOrThrowException(id));
  }

}