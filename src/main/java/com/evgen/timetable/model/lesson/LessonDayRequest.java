package com.evgen.timetable.model.lesson;

import javax.validation.constraints.NotBlank;

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
public class LessonDayRequest {

  @NotBlank
  private String lessonName;

  @NotBlank
  private String lessonTime;

  @NotBlank
  private String lessonPlace;

  @NotBlank
  private String teacherName;

  @NotBlank
  private String teacherSurname;

}