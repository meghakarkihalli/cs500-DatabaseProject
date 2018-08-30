drop table record;
drop table medicine;
drop table appointment;
drop table patient;
drop table insurance;
drop table nurse;
drop table admin;
drop table doctors;
drop table departments;
drop table employees;

create table employees (eid integer primary key, username varchar(128) not null, password text not null, ename varchar(128) not null, DOB date not null, gender varchar(2) not null, phone bigint, Address varchar(128) not null, did integer not null, salary bigint not null);

create table departments (did integer primary key, dname varchar(128) not null, hod varchar(128) not null, eid integer not null, foreign key (eid) references employees (eid) );

insert into employees (eid, username, password,ename,dob,gender,phone,address, did,salary) values ('102','mc1000','test1','Molly Clock','3/15/1974','f','5154328900','3616 S 13th street, Philadelphia, PA','55','10000');
insert into employees (eid, username, password,ename,dob,gender,phone,address, did,salary) values ('103','bk8622','test2','Bob Kel','4/20/1972','m','5132148900','4007 Chestnut street, Philadelphia, PA','22','8000');
insert into employees (eid, username, password,ename,dob,gender,phone,address, did,salary) values ('104','rk9177','test3','Raj Kapoor','4/27/1959','m','3132148934','1543 Market street, Philadelphia, PA','11','22000');
insert into employees (eid, username, password,ename,dob,gender,phone,address, did,salary) values ('106','mj3445','test5','Maria Jacob','12/6/1982','f','2672143543','107 S Pearl street, Philadelphia, PA','44','15000');
insert into employees (eid, username, password,ename,dob,gender,phone,address, did,salary) values ('105','nw5678','test4','Nancy Wen','10/7/1970','f','3132143245','4423 Walnut street, Philadelphia, PA','33','10000');

alter table departments add constraint distfk foreign key (did) references departments (did);

insert into departments (did,dname,hod,eid) values ('11','Doctors','Raj Kapoor','104');
insert into departments (did,dname,hod,eid) values ('22','House Keeping','Bob Kel','103');
insert into departments (did,dname,hod,eid) values ('33','Nurse','Nancy Wen','105');
insert into departments (did,dname,hod,eid) values ('44','Admin','Maria Jacon','106');
insert into departments (did,dname,hod,eid) values ('55','Security','Molly Clock','102');


update employees set did='55' where eid='102';
update employees set did='22' where eid='103';
update employees set did='11' where eid='104';
update employees set did='33' where eid='105';
update employees set did='44' where eid='106';


insert into employees values ('101','jd2345','test0','John Dorian','2/2/1960','m','3126549870','654 N 21st street, Philadelphia, PA','11','20000');
insert into employees values ('107','er4512','test6','Emma Rose','10/7/1969','f','2153458766','6943 Walnut street, Philadelphia, PA','22','3000');
insert into employees values ('108','sr5690','test7','Steve Row','10/7/1977','m','2157684094','3828 Baltimore AVE, Philadelphia, PA','22','3500');
insert into employees values ('109','nm7608','test8','Nora Mat','06/30/1985','f','5235098765','388 N 33rd Street, Philadelphia, PA','33','8000');
insert into employees values ('110','kr7684','test9','Keith Reid','10/31/1983','m','5151888765','108 S 43rd Street, Philadelphia, PA','33','8000');
insert into employees values ('111','tq1024','test10','Todd Quin','03/19/1989','m','5235098765','5633 Chestnut Street, Philadelphia, PA','33','7000');
insert into employees values ('112','mt6783','test11','Mary Turk','12/15/1985','f','5152345678','113 S 53rd Street, Philadelphia, PA','11','17000');
insert into employees values ('113','ec5589','test12','Ellie Cox','12/15/1985','f','2671765734','143 N 22nd Street, Philadelphia, PA','11','19000');
insert into employees values ('114','ck4318','test13','Christopher Kelso','8/30/1975','m','2151764567','3930 Walnut Street, Philadelphia, PA','11','20000');


create table doctors as (select eid, ename from employees where did=11);
alter table doctors add specialization varchar(128);
alter table doctors add qualification varchar(128);
ALTER TABLE doctors ADD PRIMARY KEY (eid);

update doctors set specialization='surgeon' where eid=101;
update doctors set specialization='Oncology' where eid=104;
update doctors set specialization='orthopedics' where eid=114;
update doctors set specialization='pediatrics' where eid=113;
update doctors set specialization='gynocology' where eid=112;

update doctors set qualification='M.D' where eid=101;
update doctors set qualification='M.D' where eid=104;
update doctors set qualification='M.D' where eid=114;
update doctors set qualification='M.D' where eid=113;
update doctors set qualification='M.D' where eid=112;



create table admin (ename varchar(128) not null, eid integer primary key, shift varchar(128) not null, isAnurse boolean not null, foreign key (eid) references employees (eid));

insert into admin values('Steve Row', '108', 'night', TRUE);
insert into admin values('Keith Reid', '110', 'day', FALSE);
insert into admin values('Todd Quin', '111', 'day', TRUE);
insert into admin values('Nora Mat', '109', 'night', TRUE);
insert into admin values('Maria Jacon','106','day', False);

create table nurse(eid integer primary key, ename varchar(128) not null, shift varchar(128), ward_num varchar(128) not null); 

insert into nurse values('108', 'Steve Row', 'night', 2);
insert into nurse values('111', 'Todd Quin', 'night', 3);
insert into nurse values('109', 'Nora Mat', 'night', 4);
insert into nurse values('105', 'Nancy Wen', 'day',5);

create table insurance(iid varchar(128) primary key, iname varchar(128) not null, coverage numrange not null, policy varchar(128) not null);

insert into insurance values('i7869', 'Metlife', numrange(500,25000), 'Met Plus');
insert into insurance values('i2239', 'Metlife', numrange(500,50000), 'Met Ultra');
insert into insurance values('i3567', 'Metlife', numrange(500,100000), 'Met Excelsior');

create table patient(pid varchar(128) primary key, pname  varchar(128) not null, address varchar(128) not null, dob date not null, gender varchar(2), nominee varchar(128), iid varchar(128), password text not null, foreign key(iid) references insurance(iid));

insert into patient values('p1008', 'John McClane', '453 N 31st street, Philadelphia, PA', '1995-02-16','m', 'Sam McClane', 'i7869', 'patient1');
insert into patient values('p1567', 'Mark lee', '444 53rd street, Philadelphia, PA', '1985-09-26','m', 'Marsha Lee', 'i2239', 'patient2');
insert into patient values('p1570', 'Joy Cutty', '567 Chestnut Street, Philadelphia, PA', '1980-12-03','f', 'Cynthiya Cutty', 'i7869', 'patient3');
insert into patient values('p1573', 'Gregory House', '231 Baltimore AVE, Philadelphia, PA', '1970-03-13','m', 'Ken House', 'i2239', 'patient4');
insert into patient values('p1579', 'Lee Jones', '789 Walnut Street, Philadelphia, PA', '1970-03-23','m', 'Kathy Jones', 'i7869', 'patient5');
insert into patient values('p1589', 'Yu lin', '456 34th Street, Philadelphia, PA', '1970-03-23','f', 'Xi Lin', 'i3567', 'patient6');
insert into patient values('p1598', 'Vivan Oberoi', '421 24th Street, Philadelphia, PA', '1990-03-23','m', 'Rudra Oberoi', 'i3567', 'patient7');


create table appointment(eid integer not null, pid varchar(128) not null, datentime timestamp not null, foreign key (eid) references doctors(eid), foreign key (pid) references patient(pid));

insert into appointment values(101, 'p1008', '2001-02-16 20:30:00');
insert into appointment values(112, 'p1567', '2001-03-01 18:40:00');
insert into appointment values(112, 'p1570', '2001-03-02 11:15:00');
insert into appointment values(114, 'p1573', '2001-03-03 12:20:00');
insert into appointment values(104, 'p1579', '2001-03-04 15:35:00');
insert into appointment values(113, 'p1589', '2001-03-05 13:40:00');
insert into appointment values(112, 'p1598', '2001-03-06 17:30:00');

create table medicine(mid varchar(128) primary key, medname varchar(128) not null, price integer not null);

insert into medicine values('m12467', 'Demorol', 5);
insert into medicine values('m234', 'Paracetamol', 4);
insert into medicine values('m456', 'Thyroxin', 15);
insert into medicine values('m345', 'Insulin', 15);
insert into medicine values('m545', 'Isotronin', 15);
insert into medicine values('m523', 'Epinephrine', 15);
insert into medicine values('m657', 'Xanax', 15);

create table record(recID varchar(128) primary key, pid varchar(128) not null, date date not null, ailment varchar(128) not null, tests varchar(128), mid varchar(128), medQuantity integer, sideeffects varchar(128), foreign key (mid) references medicine(mid), foreign key (pid) references patient(pid));

insert into record values('r4567', 'p1008','2001-02-16', 'Flu', 'blood test', 'm234', 25, 'rash');
insert into record values('r2462', 'p1567','2001-03-01', 'Thyroid', 'blood test', 'm456', 100);
insert into record values('r2463', 'p1570','2001-03-02', 'Excema', 'blood test', 'm345', 20);
insert into record values('r2464', 'p1573','2001-03-03', 'Tennis Elbow', 'X-Ray', 'm545', 20);
insert into record values('r2465', 'p1579','2001-03-04', 'Allergic Reaction', 'blood test', 'm523', 20);
insert into record values('r2466', 'p1589','2001-03-05', 'Headache', 'blood test', 'm657', 20);
insert into record values('r2467', 'p1598','2001-03-06', 'Headache', 'blood test', 'm657', 20);


