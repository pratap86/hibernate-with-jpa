insert into course(id, name, created_date, last_updated_date) values
(10001, 'Hibernate in 100 steps', sysdate(), sysdate()),
(10002, 'Spring in 100 steps', sysdate(), sysdate()),
(10003, 'Microservices', sysdate(), sysdate()),
(10004, 'Docker', sysdate(), sysdate()),
(10005, 'Kubernetes', sysdate(), sysdate());

insert into passport(id, passport_number) values
(40001, 'E123456'),
(40002, 'N123457'),
(40003, 'L123458');

insert into student(id, name, passport_id) values
(20001, 'Sankalp', 40001),
(20002, 'Rahul', 40002),
(20003, 'Ravi', 40003);

insert into review(id, rating, description, course_id) values
(50001, 5, 'Great Course', 10001),
(50002, 4, 'Wonderful Course', 10003),
(50003, 5, 'Awesome Course', 10001);

insert into STUDENT_COURSE(student_id, course_id) values
(20001, 10002),
(20002, 10002),
(20003, 10002),
(20001, 10003);

