package com.evgen.timetable.model.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class Student extends User {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "GROUP_ID", updatable = false)
  private Group group;

}