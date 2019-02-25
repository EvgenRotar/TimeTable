package com.evgen.timetable.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evgen.timetable.model.timeTable.TimeTable;

public interface TimeTableRepository extends JpaRepository<TimeTable, Long> {
}