package com.evgen.timetable.initializer;

import java.util.Objects;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.evgen.timetable.model.group.GroupSaveRequest;
import com.evgen.timetable.model.role.Role;
import com.evgen.timetable.model.role.RoleName;
import com.evgen.timetable.model.student.StudentRegistrationRequest;
import com.evgen.timetable.model.student.StudentResponse;
import com.evgen.timetable.model.teacher.Teacher;
import com.evgen.timetable.model.teacher.TeacherRegistrationRequest;
import com.evgen.timetable.model.teacher.TeacherResponse;
import com.evgen.timetable.model.workDay.Lesson;
import com.evgen.timetable.model.workDay.LessonDay;
import com.evgen.timetable.model.workDay.WorkDay;
import com.evgen.timetable.repository.LessonDayRepository;
import com.evgen.timetable.repository.LessonRepository;
import com.evgen.timetable.repository.RoleRepository;
import com.evgen.timetable.repository.TeacherRepository;
import com.evgen.timetable.repository.WorkDayRepository;
import com.evgen.timetable.service.api.GroupService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Component
public class TestDataInitializer {

  private final RoleRepository roleRepository;
  private final WorkDayRepository workDayRepository;
  private final LessonRepository lessonRepository;
  private final TeacherRepository teacherRepository;
  private final LessonDayRepository lessonDayRepository;
  private final RestTemplate restTemplate = new RestTemplate();
  private final GroupService groupService;

  @EventListener(ApplicationStartedEvent.class)
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
    TeacherRegistrationRequest teacherRegistrationRequest = TeacherRegistrationRequest.builder()
        .login("maryaivana")
        .password("14881488")
        .userName("Marya")
        .userSurname("Ivanavna")
        .build();
    TeacherRegistrationRequest teacherRegistrationRequest1 = TeacherRegistrationRequest.builder()
        .login("zinaidasemena")
        .password("14881488")
        .userName("Zinaida")
        .userSurname("Semenaana")
        .build();
    TeacherRegistrationRequest teacherRegistrationRequest2 = TeacherRegistrationRequest.builder()
        .login("stanislavder")
        .password("14881488")
        .userName("Stanislav")
        .userSurname("Derechen")
        .build();

    registrationTeacher(teacherRegistrationRequest);
    registrationTeacher(teacherRegistrationRequest1);
    registrationTeacher(teacherRegistrationRequest2);
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

  private void initComplete() {
    log.info("==========================");
    log.info("INIT TEST DATA - COMPLETE");
    log.info("==========================");
  }

  private Lesson getLessonByIdOrThrowException(Long id) {
    return lessonRepository.findById(id)
        .orElseThrow(() -> new RuntimeException(String.format("lesson with id %d not found", id)));
  }

  private Teacher getTeacherByIdOrThrowException(Long id) {
    return teacherRepository.findById(id)
        .orElseThrow(() -> new RuntimeException(String.format("lesson with id %d not found", id)));
  }

  private WorkDay getWorkDayByIdOrThrowException(Long id) {
    return workDayRepository.findById(id)
        .orElseThrow(() -> new RuntimeException(String.format("day with id %d not found", id)));
  }

  private void initTimeTable() {
    Teacher teacher = getTeacherByIdOrThrowException(6L);
    WorkDay workDay = getWorkDayByIdOrThrowException(1L);
    buildAndSaveLesson(getLessonByIdOrThrowException(1L), teacher, "400/2", "11:00", workDay);
    buildAndSaveLesson(getLessonByIdOrThrowException(2L), teacher, "430/2", "13:00", workDay);
    buildAndSaveLesson(getLessonByIdOrThrowException(3L), teacher, "440/2", "15:00", workDay);
    buildAndSaveLesson(getLessonByIdOrThrowException(4L), teacher, "410/2", "17:00", workDay);

    workDay = getWorkDayByIdOrThrowException(2L);
    buildAndSaveLesson(getLessonByIdOrThrowException(4L), teacher, "200/2", "11:00", workDay);
    buildAndSaveLesson(getLessonByIdOrThrowException(3L), teacher, "230/2", "13:00", workDay);
    buildAndSaveLesson(getLessonByIdOrThrowException(2L), teacher, "240/2", "15:00", workDay);
    buildAndSaveLesson(getLessonByIdOrThrowException(1L), teacher, "210/2", "17:00", workDay);

    workDay = getWorkDayByIdOrThrowException(3L);
    buildAndSaveLesson(getLessonByIdOrThrowException(3L), teacher, "100/2", "11:00", workDay);
    buildAndSaveLesson(getLessonByIdOrThrowException(4L), teacher, "130/2", "13:00", workDay);
    buildAndSaveLesson(getLessonByIdOrThrowException(1L), teacher, "540/2", "15:00", workDay);
    buildAndSaveLesson(getLessonByIdOrThrowException(2L), teacher, "510/2", "17:00", workDay);
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
    StudentRegistrationRequest studentRegistrationRequest = StudentRegistrationRequest.builder()
        .login("evgenrotar")
        .password("14881488")
        .userName("Yauheni")
        .userSurname("Rotar")
        .groupName("МС-2")
        .build();
    StudentRegistrationRequest studentRegistrationRequest1 = StudentRegistrationRequest.builder()
        .login("vadimparaf")
        .password("14881488")
        .userName("Vadim")
        .userSurname("Paraf")
        .groupName("ИИ-14")
        .build();
    StudentRegistrationRequest studentRegistrationRequest2 = StudentRegistrationRequest.builder()
        .login("zhmishenkov")
        .password("14881488")
        .userName("Valera")
        .userSurname("Zmih")
        .groupName("МС-2")
        .build();
    StudentRegistrationRequest studentRegistrationRequest3 = StudentRegistrationRequest.builder()
        .login("gladvalakas")
        .password("14881488")
        .userName("Glad")
        .userSurname("Valakas")
        .groupName("МС-2")
        .build();
    StudentRegistrationRequest studentRegistrationRequest4 = StudentRegistrationRequest.builder()
        .login("tankist")
        .password("14881488")
        .userName("Staryi")
        .userSurname("Tankist")
        .groupName("ПЭ-17")
        .build();

    registrationStudent(studentRegistrationRequest);
    registrationStudent(studentRegistrationRequest1);
    registrationStudent(studentRegistrationRequest2);
    registrationStudent(studentRegistrationRequest3);
    registrationStudent(studentRegistrationRequest4);

  }

  private void initGroups() {
    GroupSaveRequest group = new GroupSaveRequest("МС-2");
    GroupSaveRequest group1 = new GroupSaveRequest("Э-52");
    GroupSaveRequest group2 = new GroupSaveRequest("ИИ-14");
    GroupSaveRequest group3 = new GroupSaveRequest("ПЭ-17");

    groupService.addGroup(group);
    groupService.addGroup(group1);
    groupService.addGroup(group2);
    groupService.addGroup(group3);
  }

  private Role saveRoleIfNotExists(RoleName roleName) {
    return roleRepository.findByRoleName(roleName)
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

}