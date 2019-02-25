package com.evgen.timetable.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evgen.timetable.model.user.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}