package com.evgen.timetable.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evgen.timetable.model.teacher.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
