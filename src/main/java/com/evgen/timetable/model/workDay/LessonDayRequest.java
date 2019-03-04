package com.evgen.timetable.model.workDay;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

  @NotNull
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
