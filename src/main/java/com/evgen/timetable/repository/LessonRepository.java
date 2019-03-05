package com.evgen.timetable.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evgen.timetable.model.lesson.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

  Optional<Lesson> findByLessonName(String lessonName);

}