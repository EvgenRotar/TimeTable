package com.evgen.timetable.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.evgen.timetable.model.student.Student;
import com.evgen.timetable.model.student.StudentRegistrationRequest;
import com.evgen.timetable.model.student.StudentResponse;
import com.evgen.timetable.model.teacher.Teacher;
import com.evgen.timetable.model.teacher.TeacherRegistrationRequest;
import com.evgen.timetable.model.teacher.TeacherResponse;
import com.evgen.timetable.model.user.User;
import com.evgen.timetable.model.user.UserResponse;
import com.evgen.timetable.model.user.UserUpdateRequest;

@Mapper(componentModel = "spring", uses = {UserRoleMapper.class, TimeTableMapper.class})
public interface UserMapper {

  @Mapping(target = "userId", source = "id")
  UserResponse userToUserResponse(User users);

  @Mapping(target = "teacherId", source = "id")
  TeacherResponse teacherToTeacherResponse(Teacher teacher);

  @Mapping(target = "studentId", source = "id")
  @Mapping(target = "groupName", source = "group.groupName")
  @Mapping(target = "timeTable", source = "group.timeTable")
  StudentResponse studentToStudentResponse(Student student);

  @IterableMapping(elementTargetType = UserResponse.class)
  List<UserResponse> userToUserListResponse(List<User> users);

  void mapUserFromUserUpdateRequest(UserUpdateRequest userUpdateRequest, @MappingTarget User user);

  void mapStudentFromStudentRegistrationRequest(StudentRegistrationRequest studentRegistrationRequest,
      @MappingTarget Student student);

  void mapTeacherFromTeacherRegistrationRequest(TeacherRegistrationRequest teacherRegistrationRequest,
      @MappingTarget Teacher teacher);

}