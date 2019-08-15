package com.evgen.timetable.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Builder
@Table(name = "LESSON_DAY")
public class LessonDay extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "WORK_DAY_ID", nullable = false, updatable = false)
  private WorkDay workDay;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "LESSON_ID", nullable = false)
  private Lesson lesson;

  @Column(name = "LESSON_TIME", nullable = false, length = 32)
  private String lessonTime;

  @Column(name = "LESSON_PLACE", nullable = false, length = 32)
  private String lessonPlace;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "TEACHER_ID")
  private Teacher teacher;

}