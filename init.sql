DROP TABLE IF EXISTS DEPARTMENT;
DROP TABLE IF EXISTS FREE_TIME;
DROP TABLE IF EXISTS CHANGE_REC;
DROP TABLE IF EXISTS SCHEDULE;
DROP TABLE IF EXISTS STUDENT;
DROP TABLE IF EXISTS STU_DPT_REL;
DROP TABLE IF EXISTS ID_PASSWORD;
DROP TABLE IF EXISTS ADMIN_ID;
CREATE TABLE DEPARTMENT(
  dpt_id varchar(10) primary key,
  dpt_name varchar(80) unique
)ENGINE=InnoDB;
CREATE TABLE FREE_TIME(
  stu_id varchar(30),
  weekday varchar(4),
  time_slot varchar(20),
  primary key(stu_id, weekday, time_slot)
)ENGINE=InnoDB;
CREATE TABLE CHANGE_REC(
  stu_id varchar(30),
  dpt_id varchar(10),
  weekday varchar(4),
  time_slot varchar(20),
  rec_date date,
  primary key(stu_id, dpt_id, time_slot, rec_date)
)ENGINE=InnoDB;
CREATE TABLE SCHEDULE(
  stu_id varchar(30),
  dpt_id varchar(10),
  weekday varchar(4),
  time_slot varchar(20),
  primary key(stu_id, dpt_id, weekday, time_slot)
)ENGINE=InnoDB;
CREATE TABLE STUDENT(
  stu_id varchar(30) primary key,
  stu_name varchar(40)
)ENGINE=InnoDB;
CREATE TABLE STU_DPT_REL(
  stu_id varchar(30),
  dpt_id varchar(10),
  primary key(stu_id, dpt_id)
)ENGINE=InnoDB;
CREATE TABLE ID_PASSWORD(
  user_id varchar(30) primary key,
  user_password varchar(80) not null
)ENGINE=InnoDB;
CREATE TABLE ADMIN_ID(
  admin_id varchar(30) primary key
)ENGINE=InnoDB;
