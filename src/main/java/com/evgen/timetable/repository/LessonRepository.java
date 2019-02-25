package com.evgen.timetable.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evgen.timetable.model.workDay.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}