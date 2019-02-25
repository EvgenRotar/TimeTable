package com.evgen.timetable.model.timeTable;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.evgen.timetable.model.BaseEntity;
import com.evgen.timetable.model.group.Group;
import com.evgen.timetable.model.teacher.Teacher;
import com.evgen.timetable.model.workDay.WorkDay;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "TIME_TABLE")
public class TimeTable extends BaseEntity {

  @Enumerated(EnumType.STRING)
  @Column(name = "TIME_TABLE_NAME", nullable = false, length = 32)
  private TimeTableName TimeTableName;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "GROUP_ID", updatable = false)
  private Group group;

  @OneToMany(mappedBy = "timeTable", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<WorkDay> workDays;

}