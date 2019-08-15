package com.evgen.timetable.model.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "USER")
public class User extends BaseEntity {

  @Column(name = "USER_LOGIN", unique = true, nullable = false, length = 32, updatable = false)
  private String login;

  @Column(name = "USER_PASSWORD", nullable = false)
  private String password;

  @Column(name = "USER_NAME", nullable = false, length = 50)
  private String userName;

  @Column(name = "USER_SURNAME", nullable = false, length = 50)
  private String userSurname;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<UserRole> roles;

}