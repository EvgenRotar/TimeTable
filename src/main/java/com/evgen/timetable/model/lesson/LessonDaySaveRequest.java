package com.evgen.timetable.model.lesson;

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
public class LessonDaySaveRequest {

  @NotNull
  private Long workDayId;

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
