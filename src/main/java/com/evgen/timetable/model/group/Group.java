package com.evgen.timetable.model.group;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.evgen.timetable.model.BaseEntity;
import com.evgen.timetable.model.student.Student;
import com.evgen.timetable.model.timeTable.TimeTable;

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
@Table(name = "STUDENT_GROUP")
public class Group extends BaseEntity {

  @Column(name = "GROUP_NAME", unique = true, nullable = false, length = 32, updatable = false)
  private String groupName;

  @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Student> students;

  @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<TimeTable> timeTable;

}