package com.evgen.timetable.model.workDay;

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
public class LessonResponse {

  private Lesson lesson;
  private String lessonTime;
  private String lessonPlace;
  private String teacherName;
  private String teacherSurname;

}