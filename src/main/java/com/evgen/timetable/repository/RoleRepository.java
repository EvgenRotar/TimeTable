package com.evgen.timetable.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evgen.timetable.model.entity.Role;
import com.evgen.timetable.model.name.RoleName;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleName(RoleName name);

}