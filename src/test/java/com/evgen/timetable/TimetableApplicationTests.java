package com.evgen.timetable;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.evgen.timetable.model.entity.Group;
import com.evgen.timetable.model.entity.Role;
import com.evgen.timetable.model.name.RoleName;
import com.evgen.timetable.repository.GroupRepository;
import com.evgen.timetable.repository.RoleRepository;
import com.evgen.timetable.repository.UserRepository;
import com.evgen.timetable.repository.UserRoleRepository;
import com.evgen.timetable.service.api.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TimetableApplicationTests {

  @Autowired
  UserRepository userRepository;
  @Autowired
  RoleRepository roleRepository;
  @Autowired
  UserRoleRepository userRoleRepository;
  @Autowired
  GroupRepository groupRepository;

  @Autowired
  UserService userService;

  @Test
  public void contextLoads() {
    Optional<Role> optRole = roleRepository.findByRoleName(RoleName.ADMIN);
    optRole.ifPresent(role -> System.out.println(role.getRoleName()));
  }

  @Test
  public void userTest() {

    Group group = new Group();
    group.setGroupName("MS-2");

  }

}