package com.evgen.timetable.model.workDay;

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
import com.evgen.timetable.model.timeTable.TimeTable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "WORK_DAY")
public class WorkDay extends BaseEntity {

  @Enumerated(EnumType.STRING)
  @Column(name = "DAY_NAME", nullable = false, length = 32, updatable = false)
  private DayName dayName;

  @OneToMany(mappedBy = "workDay", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<LessonDay> lessons;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "TIME_TABLE_ID", updatable = false)
  private TimeTable timeTable;

}