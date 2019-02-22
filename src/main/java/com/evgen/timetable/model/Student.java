package com.evgen.timetable.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "STUDENT")
public class Student extends BaseEntity {

  private String login;
  private String studentName;
  private String studentSurname;

}
