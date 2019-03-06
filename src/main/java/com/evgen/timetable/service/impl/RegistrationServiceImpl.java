package com.evgen.timetable.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.evgen.timetable.exception.NotFoundException;
import com.evgen.timetable.mapper.UserMapper;
import com.evgen.timetable.model.dto.student.StudentRegistrationRequest;
import com.evgen.timetable.model.dto.student.StudentResponse;
import com.evgen.timetable.model.dto.teacher.TeacherRegistrationRequest;
import com.evgen.timetable.model.dto.teacher.TeacherResponse;
import com.evgen.timetable.model.entity.Group;
import com.evgen.timetable.model.entity.Role;
import com.evgen.timetable.model.entity.Student;
import com.evgen.timetable.model.entity.Teacher;
import com.evgen.timetable.model.entity.User;
import com.evgen.timetable.model.entity.UserRole;
import com.evgen.timetable.model.name.RoleName;
import com.evgen.timetable.repository.UserRepository;
import com.evgen.timetable.repository.UserRoleRepository;
import com.evgen.timetable.service.api.RegistrationService;
import com.evgen.timetable.util.OptionalDaoUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Transactional
public class RegistrationServiceImpl implements RegistrationService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final UserRoleRepository userRoleRepository;
  private final UserMapper userMapper;
  private final OptionalDaoUtil optionalDaoUtil;

  private void saveUserRole(Role role, User user) {
    UserRole userRole = new UserRole();
    userRole.setUser(user);
    userRole.setRole(role);
    userRoleRepository.save(userRole);
  }

  @Override
  public StudentResponse registrationStudent(StudentRegistrationRequest studentRegistrationRequest) throws NotFoundException {
    Group group = optionalDaoUtil.getGroupOrThrowException(studentRegistrationRequest.getGroupName());
    Student student = new Student();
    userMapper.mapStudentFromStudentRegistrationRequest(studentRegistrationRequest, student);
    student.setPassword(bCryptPasswordEncoder.encode(student.getPassword()));
    student.setGroup(group);
    student = userRepository.save(student);

    Role studentRole = optionalDaoUtil.getRoleByNameOrThrowException(RoleName.STUDENT);
    saveUserRole(studentRole, student);

    return userMapper.studentToStudentResponse(student);
  }

  @Override
  public TeacherResponse registrationTeacher(TeacherRegistrationRequest teacherRegistrationRequest) throws NotFoundException {
    Teacher teacher = new Teacher();
    userMapper.mapTeacherFromTeacherRegistrationRequest(teacherRegistrationRequest, teacher);
    teacher.setPassword(bCryptPasswordEncoder.encode(teacher.getPassword()));
    teacher = userRepository.save(teacher);

    Role adminRole = optionalDaoUtil.getRoleByNameOrThrowException(RoleName.ADMIN);
    saveUserRole(adminRole, teacher);

    return userMapper.teacherToTeacherResponse(teacher);
  }

}