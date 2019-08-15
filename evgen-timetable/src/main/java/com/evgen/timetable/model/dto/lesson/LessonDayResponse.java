package com.evgen.timetable.model.dto.lesson;

import com.evgen.timetable.model.entity.Lesson;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LessonDayResponse {

  private Long id;
  private Lesson lesson;
  private String lessonTime;
  private String lessonPlace;
  private String teacherName;
  private String teacherSurname;

}