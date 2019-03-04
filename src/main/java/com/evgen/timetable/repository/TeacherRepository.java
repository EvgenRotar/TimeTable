package com.evgen.timetable.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evgen.timetable.model.teacher.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

  Optional<Teacher> findTeacherByUserNameAndUserSurname(String userName, String userSurname);

}
