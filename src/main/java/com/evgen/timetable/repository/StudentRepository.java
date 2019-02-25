package com.evgen.timetable.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evgen.timetable.model.student.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
