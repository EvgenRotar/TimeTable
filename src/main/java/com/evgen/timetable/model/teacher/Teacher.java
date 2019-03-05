package com.evgen.timetable.model.teacher;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.evgen.timetable.model.user.User;
import com.evgen.timetable.model.lesson.LessonDay;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
public class Teacher extends User {

  @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<LessonDay> timeTables;

}