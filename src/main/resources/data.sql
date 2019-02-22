INSERT INTO TIMETABLE.ROLE (ROLE_ID, ROLE_NAME)
VALUES ('1',  'STUDENT'),
       ('2',  'ADMIN');

INSERT INTO TIMETABLE.STUDENT_GROUP (GROUP_ID, GROUP_NAME)
VALUES ('1',  'MC-2'),
       ('2',  'II-14');


INSERT INTO TIMETABLE.STUDENT (STUDENT_ID, STUDENT_NAME, STUDENT_SURNAME, STUDENT_LOGIN, STUDENT_PASSWORD, GROUP_ID, ROLE_ID)
VALUES ('1',  'Test1', 'fromMs2', 'Test123', 'asdf', '1', '1'),
       ('2',  'Test2', 'fromII-14', 'Test123', 'asdf', '2', '1'),
       ('3',  'Test3', 'admin', 'admin123', 'admin', null, '2');

INSERT INTO TIMETABLE.TIME_TABLE (TIME_TABLE_ID, TIME_TABLE_NAME, GROUP_ID)
VALUES ('1',  'down', '1'),
       ('2',  'up', '1'),
       ('3',  'down', '2'),
       ('4',  'up', '2');

INSERT INTO TIMETABLE.WORK_DAY (DAY_ID, DAY_NAME, TIME_TABLE_ID)
VALUES ('1',  'Mon' ,  '1'),
       ('2',  'Tue' ,  '1'),
       ('3',  'Wed' ,  '1'),
       ('4',  'Thu' ,  '1'),
       ('5',  'Fri' ,  '1'),
       ('6',  'Sat' ,  '1'),
       ('7',  'Mon' ,  '2'),
       ('8',  'Tue' ,  '2'),
       ('9',  'Wed' ,  '2'),
       ('10',  'Thu' ,  '2'),
       ('11',  'Fri' ,  '2'),
       ('12',  'Sat' ,  '2'),
       ('13',  'Mon' ,  '3'),
       ('14',  'Tue' ,  '3'),
       ('15',  'Wed' ,  '3'),
       ('16',  'Thu' ,  '3'),
       ('17',  'Fri' ,  '3'),
       ('18',  'Sat' ,  '3'),
       ('19',  'Mon' ,  '4'),
       ('20',  'Tue' ,  '4'),
       ('21',  'Wed' ,  '4'),
       ('22',  'Thu' ,  '4'),
       ('23',  'Fri' ,  '4'),
       ('24',  'Sat' ,  '4');

INSERT INTO TIMETABLE.LESSON (LESSON_ID, LESSON_NAME)
VALUES ('1',  'Math'),
       ('2',  'English');

INSERT INTO TIMETABLE.LESSON_DAY (DAY_ID, LESSON_ID, LESSON_PLACE, LESSON_TIME)
VALUES ('1',  '1', '2/300', '13:30'),
       ('1',  '1', '2/300', '16:30'),
       ('1',  '2', '4/200', '16:40'),
       ('2',  '2', '4/200', '16:40'),
       ('20',  '1', '4/200', '16:40');
