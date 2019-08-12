package com.evgen.timetable.initializer;

import java.util.Objects;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.evgen.timetable.model.dto.group.GroupSaveRequest;
import com.evgen.timetable.model.dto.student.StudentRegistrationRequest;
import com.evgen.timetable.model.dto.student.StudentResponse;
import com.evgen.timetable.model.dto.teacher.TeacherRegistrationRequest;
import com.evgen.timetable.model.dto.teacher.TeacherResponse;
import com.evgen.timetable.model.entity.Lesson;
import com.evgen.timetable.model.entity.LessonDay;
import com.evgen.timetable.model.entity.Role;
import com.evgen.timetable.model.entity.Teacher;
import com.evgen.timetable.model.entity.WorkDay;
import com.evgen.timetable.model.name.RoleName;
import com.evgen.timetable.repository.LessonDayRepository;
import com.evgen.timetable.repository.LessonRepository;
import com.evgen.timetable.repository.RoleRepository;
import com.evgen.timetable.service.api.GroupService;
import com.evgen.timetable.util.OptionalDaoUtil;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
//@Component
public class TestDataInitializer {

  private final RoleRepository roleRepository;
  private final LessonRepository lessonRepository;
  private final LessonDayRepository lessonDayRepository;
  private final OptionalDaoUtil optionalDaoUtil;
  private final RestTemplate restTemplate = new RestTemplate();
  private final GroupService groupService;

 //@EventListener(ApplicationStartedEvent.class)
  public void onApplicationStartedEvent() {
    initStudentAndAdminRoles();
    initGroups();
    initStudents();
    initLessons();
    initTeacher();
    initTimeTable();
    initComplete();
  }

  private void initTeacher() {
    registrationTeacher(
        TeacherRegistrationRequest.builder()
            .login("maryaivana")
            .password("14881488")
            .userName("Marya")
            .userSurname("Ivanavna")
            .build()
    );
    registrationTeacher(
        TeacherRegistrationRequest.builder()
            .login("zinaidasemena")
            .password("14881488")
            .userName("Zinaida")
            .userSurname("Semenaana")
            .build()
    );
    registrationTeacher(
        TeacherRegistrationRequest.builder()
            .login("stanislavder")
            .password("14881488")
            .userName("Stanislav")
            .userSurname("Derechen")
            .build()
    );
  }

  private void initLessons() {
    buildAndSaveLesson("Math");
    buildAndSaveLesson("Literature");
    buildAndSaveLesson("Philosophy");
    buildAndSaveLesson("English");
  }

  private void buildAndSaveLesson(String lessonName) {
    Lesson lesson = new Lesson(lessonName);
    lessonRepository.save(lesson);
  }

  private void initTimeTable() {
    Teacher teacher = optionalDaoUtil.getTeacherByIdOrThrowException(6L);
    WorkDay workDay = optionalDaoUtil.getWorkDayByIdOrThrowException(1L);
    buildAndSaveLesson(optionalDaoUtil.getLessonByIdOrThrowException(1L), teacher, "400/2", "11:00", workDay);
    buildAndSaveLesson(optionalDaoUtil.getLessonByIdOrThrowException(2L), teacher, "430/2", "13:00", workDay);
    buildAndSaveLesson(optionalDaoUtil.getLessonByIdOrThrowException(3L), teacher, "440/2", "15:00", workDay);
    buildAndSaveLesson(optionalDaoUtil.getLessonByIdOrThrowException(4L), teacher, "410/2", "17:00", workDay);

    workDay = optionalDaoUtil.getWorkDayByIdOrThrowException(2L);
    buildAndSaveLesson(optionalDaoUtil.getLessonByIdOrThrowException(4L), teacher, "200/2", "11:00", workDay);
    buildAndSaveLesson(optionalDaoUtil.getLessonByIdOrThrowException(3L), teacher, "230/2", "13:00", workDay);
    buildAndSaveLesson(optionalDaoUtil.getLessonByIdOrThrowException(2L), teacher, "240/2", "15:00", workDay);
    buildAndSaveLesson(optionalDaoUtil.getLessonByIdOrThrowException(1L), teacher, "210/2", "17:00", workDay);

    workDay = optionalDaoUtil.getWorkDayByIdOrThrowException(3L);
    buildAndSaveLesson(optionalDaoUtil.getLessonByIdOrThrowException(3L), teacher, "100/2", "11:00", workDay);
    buildAndSaveLesson(optionalDaoUtil.getLessonByIdOrThrowException(4L), teacher, "130/2", "13:00", workDay);
    buildAndSaveLesson(optionalDaoUtil.getLessonByIdOrThrowException(1L), teacher, "540/2", "15:00", workDay);
    buildAndSaveLesson(optionalDaoUtil.getLessonByIdOrThrowException(2L), teacher, "510/2", "17:00", workDay);

    workDay = optionalDaoUtil.getWorkDayByIdOrThrowException(4L);
    buildAndSaveLesson(optionalDaoUtil.getLessonByIdOrThrowException(3L), teacher, "100/2", "11:00", workDay);
    buildAndSaveLesson(optionalDaoUtil.getLessonByIdOrThrowException(4L), teacher, "130/2", "13:00", workDay);
    buildAndSaveLesson(optionalDaoUtil.getLessonByIdOrThrowException(1L), teacher, "540/2", "15:00", workDay);
    buildAndSaveLesson(optionalDaoUtil.getLessonByIdOrThrowException(2L), teacher, "510/2", "17:00", workDay);

    workDay = optionalDaoUtil.getWorkDayByIdOrThrowException(5L);
    buildAndSaveLesson(optionalDaoUtil.getLessonByIdOrThrowException(3L), teacher, "100/2", "11:00", workDay);
    buildAndSaveLesson(optionalDaoUtil.getLessonByIdOrThrowException(4L), teacher, "130/2", "13:00", workDay);
    buildAndSaveLesson(optionalDaoUtil.getLessonByIdOrThrowException(1L), teacher, "540/2", "15:00", workDay);
    buildAndSaveLesson(optionalDaoUtil.getLessonByIdOrThrowException(2L), teacher, "510/2", "17:00", workDay);

    workDay = optionalDaoUtil.getWorkDayByIdOrThrowException(6L);
    buildAndSaveLesson(optionalDaoUtil.getLessonByIdOrThrowException(3L), teacher, "100/2", "11:00", workDay);
    buildAndSaveLesson(optionalDaoUtil.getLessonByIdOrThrowException(4L), teacher, "130/2", "13:00", workDay);
    buildAndSaveLesson(optionalDaoUtil.getLessonByIdOrThrowException(1L), teacher, "540/2", "15:00", workDay);
    buildAndSaveLesson(optionalDaoUtil.getLessonByIdOrThrowException(2L), teacher, "510/2", "17:00", workDay);
  }

  private void buildAndSaveLesson(Lesson lesson, Teacher teacher, String place, String time, WorkDay workDay) {
    LessonDay lessonDay = LessonDay.builder()
        .lesson(lesson)
        .lessonPlace(place)
        .lessonTime(time)
        .teacher(teacher)
        .workDay(workDay)
        .build();

    lessonDayRepository.save(lessonDay);
  }

  private Long registrationStudent(StudentRegistrationRequest studentRegistrationRequest) {
    ResponseEntity<StudentResponse> response = restTemplate
        .postForEntity("http://localhost:8080/api/sign-up?projection=student", studentRegistrationRequest,
            StudentResponse.class);
    return Objects.requireNonNull(response.getBody()).getStudentId();
  }

  private Long registrationTeacher(TeacherRegistrationRequest teacherRegistrationRequest) {
    ResponseEntity<TeacherResponse> response = restTemplate
        .postForEntity("http://localhost:8080/api/sign-up?projection=teacher", teacherRegistrationRequest,
            TeacherResponse.class);
    return Objects.requireNonNull(response.getBody()).getTeacherId();
  }

  private void initStudents() {
    registrationStudent(
        StudentRegistrationRequest.builder()
            .login("evgenrotar")
            .password("14881488")
            .userName("Yauheni")
            .userSurname("Rotar")
            .groupName("МС-2")
            .build()
    );
    registrationStudent(
        StudentRegistrationRequest.builder()
            .login("vadimparaf")
            .password("14881488")
            .userName("Vadim")
            .userSurname("Paraf")
            .groupName("ИИ-14")
            .build()
    );
    registrationStudent(
        StudentRegistrationRequest.builder()
            .login("zhmishenkov")
            .password("14881488")
            .userName("Valera")
            .userSurname("Zmih")
            .groupName("МС-2")
            .build()
    );
    registrationStudent(
        StudentRegistrationRequest.builder()
            .login("gladvalakas")
            .password("14881488")
            .userName("Glad")
            .userSurname("Valakas")
            .groupName("МС-2")
            .build()
    );
    registrationStudent(
        StudentRegistrationRequest.builder()
            .login("tankist")
            .password("14881488")
            .userName("Staryi")
            .userSurname("Tankist")
            .groupName("ПЭ-17")
            .build()
    );
  }

  private void initGroups() {
    groupService.addGroup(new GroupSaveRequest("МС-2"));
    groupService.addGroup(new GroupSaveRequest("Э-52"));
    groupService.addGroup(new GroupSaveRequest("ИИ-14"));
    groupService.addGroup(new GroupSaveRequest("ПЭ-17"));
  }

  private void saveRoleIfNotExists(RoleName roleName) {
    roleRepository.findByRoleName(roleName)
        .orElseGet(() -> {
          Role roleToSave = new Role();
          roleToSave.setRoleName(roleName);
          return roleRepository.save(roleToSave);
        });
  }

  private void initStudentAndAdminRoles() {
    saveRoleIfNotExists(RoleName.STUDENT);
    saveRoleIfNotExists(RoleName.ADMIN);
  }

  private void initComplete() {
    log.info("==========================");
    log.info("INIT TEST DATA - COMPLETE");
    log.info("==========================");
  }

}