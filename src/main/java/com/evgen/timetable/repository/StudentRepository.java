package com.evgen.timetable.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evgen.timetable.model.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}