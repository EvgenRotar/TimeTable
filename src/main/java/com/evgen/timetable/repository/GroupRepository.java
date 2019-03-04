package com.evgen.timetable.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evgen.timetable.model.group.Group;
import com.evgen.timetable.model.group.GroupFullResponse;

public interface GroupRepository extends JpaRepository<Group, Long> {

  Optional<Group> findByGroupName(String groupName);

}