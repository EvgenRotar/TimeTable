package com.evgen.timetable.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evgen.timetable.model.workDay.LessonDay;

public interface LessonDayRepository extends JpaRepository<LessonDay, Long> {

}
