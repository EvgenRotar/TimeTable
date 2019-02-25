package com.evgen.timetable.model.role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.evgen.timetable.model.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "ROLE")
public class Role extends BaseEntity {

  @Enumerated(EnumType.STRING)
  @Column(name = "ROLE_NAME", nullable = false, length = 32)
  private RoleName roleName;

}