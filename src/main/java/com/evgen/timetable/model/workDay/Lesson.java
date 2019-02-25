package com.evgen.timetable.model.workDay;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.evgen.timetable.model.BaseEntity;
import com.evgen.timetable.model.teacher.Teacher;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "LESSON")
public class Lesson extends BaseEntity {

  @Column(name = "LESSON_NAME", unique = true, nullable = false, length = 32, updatable = false)
  private String lessonName;

}