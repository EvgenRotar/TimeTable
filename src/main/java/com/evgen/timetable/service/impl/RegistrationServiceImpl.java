package com.evgen.timetable.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.evgen.timetable.mapper.UserMapper;
import com.evgen.timetable.model.entity.Group;
import com.evgen.timetable.model.entity.Role;
import com.evgen.timetable.model.name.RoleName;
import com.evgen.timetable.model.entity.Student;
import com.evgen.timetable.model.dto.student.StudentRegistrationRequest;
import com.evgen.timetable.model.dto.student.StudentResponse;
import com.evgen.timetable.model.entity.Teacher;
import com.evgen.timetable.model.dto.teacher.TeacherRegistrationRequest;
import com.evgen.timetable.model.dto.teacher.TeacherResponse;
import com.evgen.timetable.model.entity.User;
import com.evgen.timetable.model.entity.UserRole;
import com.evgen.timetable.repository.GroupRepository;
import com.evgen.timetable.repository.RoleRepository;
import com.evgen.timetable.repository.UserRepository;
import com.evgen.timetable.repository.UserRoleRepository;
import com.evgen.timetable.service.api.RegistrationService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Transactional
public class RegistrationServiceImpl implements RegistrationService {

  private final UserRepository userRepository;
  private final GroupRepository groupRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final UserRoleRepository userRoleRepository;
  private final RoleRepository roleRepository;
  private final UserMapper userMapper;

  private Role getUserRoleOrThrowException() throws RuntimeException {
    return roleRepository.findByRoleName(RoleName.STUDENT)
        .orElseThrow(() -> new RuntimeException("name with Student name not found"));
  }

  private Role getAdminRoleOrThrowException() throws RuntimeException {
    return roleRepository.findByRoleName(RoleName.ADMIN)
        .orElseThrow(() -> new RuntimeException("name with Admin name not found"));
  }

  private Group getGroupOrThrowException(String groupName) throws RuntimeException {
    return groupRepository.findByGroupName(groupName)
        .orElseThrow(() -> new RuntimeException(String.format("group with name %s not found", groupName)));
  }

  private void saveUserRole(Role role, User user) {
    UserRole userRole = new UserRole();

    userRole.setUser(user);
    userRole.setRole(role);

    userRoleRepository.save(userRole);
  }

  @Override
  public StudentResponse registrationStudent(StudentRegistrationRequest studentRegistrationRequest) {
    Group group = getGroupOrThrowException(studentRegistrationRequest.getGroupName());

    Student student = new Student();
    userMapper.mapStudentFromStudentRegistrationRequest(studentRegistrationRequest, student);
    student.setPassword(bCryptPasswordEncoder.encode(student.getPassword()));
    student.setGroup(group);
    student = userRepository.save(student);

    Role studentRole = getUserRoleOrThrowException();

    saveUserRole(studentRole, student);

    return userMapper.studentToStudentResponse(student);
  }

  @Override
  public TeacherResponse registrationTeacher(TeacherRegistrationRequest teacherRegistrationRequest) {
    Teacher teacher = new Teacher();
    userMapper.mapTeacherFromTeacherRegistrationRequest(teacherRegistrationRequest, teacher);
    teacher.setPassword(bCryptPasswordEncoder.encode(teacher.getPassword()));
    teacher = userRepository.save(teacher);

    Role adminRole = getAdminRoleOrThrowException();

    saveUserRole(adminRole, teacher);

    return userMapper.teacherToTeacherResponse(teacher);
  }

}