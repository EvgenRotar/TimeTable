package com.evgen.timetable.util;

import org.springframework.stereotype.Component;

import com.evgen.timetable.model.entity.Group;
import com.evgen.timetable.model.entity.Lesson;
import com.evgen.timetable.model.entity.LessonDay;
import com.evgen.timetable.model.entity.Role;
import com.evgen.timetable.model.entity.Student;
import com.evgen.timetable.model.entity.Teacher;
import com.evgen.timetable.model.entity.TimeTable;
import com.evgen.timetable.model.entity.User;
import com.evgen.timetable.model.entity.UserRole;
import com.evgen.timetable.model.entity.WorkDay;
import com.evgen.timetable.model.name.RoleName;
import com.evgen.timetable.repository.GroupRepository;
import com.evgen.timetable.repository.LessonDayRepository;
import com.evgen.timetable.repository.LessonRepository;
import com.evgen.timetable.repository.RoleRepository;
import com.evgen.timetable.repository.StudentRepository;
import com.evgen.timetable.repository.TeacherRepository;
import com.evgen.timetable.repository.TimeTableRepository;
import com.evgen.timetable.repository.UserRepository;
import com.evgen.timetable.repository.UserRoleRepository;
import com.evgen.timetable.repository.WorkDayRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class OptionalDaoUtil {

  private final LessonDayRepository lessonDayRepository;
  private final WorkDayRepository workDayRepository;
  private final LessonRepository lessonRepository;
  private final TeacherRepository teacherRepository;
  private final GroupRepository groupRepository;
  private final StudentRepository studentRepository;
  private final RoleRepository roleRepository;
  private final TimeTableRepository timeTableRepository;
  private final UserRepository userRepository;
  private final UserRoleRepository userRoleRepository;

  //  User
  public User getUserByIdOrThrowException(Long id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException(String.format("User with id %d not found", id)));
  }

  //Student
  public Student getStudentByIdOrThrowException(Long id) {
    return studentRepository.findById(id)
        .orElseThrow(() -> new RuntimeException(String.format("Student with id %d not found", id)));
  }

  //Teacher
  public Teacher getTeacherOrThrowException(String teacherName, String teacherSurname) {
    return teacherRepository.findTeacherByUserNameAndUserSurname(teacherName, teacherSurname)
        .orElseThrow(() -> new RuntimeException(
            String.format("Teacher with name %s %s not found", teacherName, teacherSurname)));
  }

  public Teacher getTeacherByIdOrThrowException(Long id) {
    return teacherRepository.findById(id)
        .orElseThrow(() -> new RuntimeException(String.format("Teacher with id %d not found", id)));
  }

  //Role
  public Role getRoleByIdOrThrowException(Long id) throws RuntimeException {
    return roleRepository.findById(id)
        .orElseThrow(() -> new RuntimeException(String.format("Role with id %d not found", id)));
  }

  public Role getRoleByNameOrThrowException(RoleName roleName) throws RuntimeException {
    return roleRepository.findByRoleName(roleName)
        .orElseThrow(() -> new RuntimeException(String.format("Role with name %s not found", roleName)));
  }

  //UserRole
  public UserRole getUserRoleByIdOrThrowException(Long id) {
    return userRoleRepository.findById(id)
        .orElseThrow(() -> new RuntimeException(String.format("UserRole with id %d not found", id)));
  }

  //Lesson
  public Lesson getLessonByNameOrThrowException(String lessonName) {
    return lessonRepository.findByLessonName(lessonName)
        .orElseThrow(() -> new RuntimeException(String.format("Lesson with name %s not found", lessonName)));
  }

  public Lesson getLessonByIdOrThrowException(Long id) {
    return lessonRepository.findById(id)
        .orElseThrow(() -> new RuntimeException(String.format("Lesson with id %d not found", id)));
  }

  //LessonDay
  public LessonDay getLessonDayByIdOrThrowException(Long id) {
    return lessonDayRepository.findById(id)
        .orElseThrow(() -> new RuntimeException(String.format("LessonDay with id %d not found", id)));
  }

  //WorkDay
  public WorkDay getWorkDayByIdOrThrowException(Long id) {
    return workDayRepository.findById(id)
        .orElseThrow(() -> new RuntimeException(String.format("WorkDay with id %d not found", id)));
  }

  //Group
  public Group getGroupOrThrowException(String groupName) throws RuntimeException {
    return groupRepository.findByGroupName(groupName)
        .orElseThrow(() -> new RuntimeException(String.format("Group with name %s not found", groupName)));
  }
  public Group getGroupByIdOrThrowException(Long id) {
    return groupRepository.findById(id)
        .orElseThrow(() -> new RuntimeException(String.format("Group with id %d not found", id)));
  }

  //TimeTable
  public TimeTable getTimeTableByIdOrThrowException(Long id) {
    return timeTableRepository.findById(id)
        .orElseThrow(() -> new RuntimeException(String.format("TimeTable with id %d not found", id)));
  }

}