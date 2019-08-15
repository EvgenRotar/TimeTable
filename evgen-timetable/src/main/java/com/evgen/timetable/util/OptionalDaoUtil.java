package com.evgen.timetable.util;

import org.springframework.stereotype.Component;

import com.evgen.timetable.exception.NotFoundException;
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
  public User getUserByIdOrThrowException(Long id) throws NotFoundException {
    return userRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(String.format("User with id %d not found", id)));
  }

  //Student
  public Student getStudentByIdOrThrowException(Long id) throws NotFoundException {
    return studentRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(String.format("Student with id %d not found", id)));
  }

  //Teacher
  public Teacher getTeacherOrThrowException(String teacherName, String teacherSurname) throws NotFoundException {
    return teacherRepository.findTeacherByUserNameAndUserSurname(teacherName, teacherSurname)
        .orElseThrow(() -> new NotFoundException(
            String.format("Teacher with name %s %s not found", teacherName, teacherSurname)));
  }

  public Teacher getTeacherByIdOrThrowException(Long id) throws NotFoundException {
    return teacherRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(String.format("Teacher with id %d not found", id)));
  }

  //Role
  public Role getRoleByIdOrThrowException(Long id) throws NotFoundException {
    return roleRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(String.format("Role with id %d not found", id)));
  }

  public Role getRoleByNameOrThrowException(RoleName roleName) throws NotFoundException {
    return roleRepository.findByRoleName(roleName)
        .orElseThrow(() -> new NotFoundException(String.format("Role with name %s not found", roleName)));
  }

  //UserRole
  public UserRole getUserRoleByIdOrThrowException(Long id) throws NotFoundException {
    return userRoleRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(String.format("UserRole with id %d not found", id)));
  }

  //Lesson
  public Lesson getLessonByNameOrThrowException(String lessonName) throws NotFoundException {
    return lessonRepository.findByLessonName(lessonName)
        .orElseThrow(() -> new NotFoundException(String.format("Lesson with name %s not found", lessonName)));
  }

  public Lesson getLessonByIdOrThrowException(Long id) throws NotFoundException {
    return lessonRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(String.format("Lesson with id %d not found", id)));
  }

  //LessonDay
  public LessonDay getLessonDayByIdOrThrowException(Long id) throws NotFoundException {
    return lessonDayRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(String.format("LessonDay with id %d not found", id)));
  }

  //WorkDay
  public WorkDay getWorkDayByIdOrThrowException(Long id) throws NotFoundException {
    return workDayRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(String.format("WorkDay with id %d not found", id)));
  }

  //Group
  public Group getGroupOrThrowException(String groupName) throws NotFoundException {
    return groupRepository.findByGroupName(groupName)
        .orElseThrow(() -> new NotFoundException(String.format("Group with name %s not found", groupName)));
  }

  public Group getGroupByIdOrThrowException(Long id) throws NotFoundException {
    return groupRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(String.format("Group with id %d not found", id)));
  }

  //TimeTable
  public TimeTable getTimeTableByIdOrThrowException(Long id) throws NotFoundException {
    return timeTableRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(String.format("TimeTable with id %d not found", id)));
  }

}