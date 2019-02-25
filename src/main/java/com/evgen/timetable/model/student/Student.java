package com.evgen.timetable.model.student;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.evgen.timetable.model.group.Group;
import com.evgen.timetable.model.user.User;

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
@Table(name = "USER")
public class Student extends User {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "GROUP_ID", updatable = false)
  private Group group;

}