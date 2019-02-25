package com.evgen.timetable.model.teacher;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.evgen.timetable.Constants;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TeacherRegistrationRequest {

  @Pattern(regexp = Constants.USERNAME_PATTERN, message = "username have to consist from: a-z characters, 0-9 figures, symbols _, -; and have length 6-15")
  private String login;

  @Pattern(regexp = Constants.PASSWORD_PATTERN, message = "password have to consist from: a-z characters, A-Z characters, 0-9 figures; and have length 8-15")
  private String password;

  @NotBlank
  private String userName;

  @NotBlank
  private String userSurname;

}
