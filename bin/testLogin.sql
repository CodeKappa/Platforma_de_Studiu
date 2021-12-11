drop database test;
create database test;
use test;

create table persoane
(
  id int unique,
  nume varchar(30),
  parola varchar(50),
  
  primary key (id)
);

insert into persoane values (1,"numescu", "1234");
insert into persoane values (2,"frumusescu", "1234");
insert into persoane values (3,"grigorescu", "2144");
insert into persoane values (4,"gigel", "5555");
insert into persoane values (5,"bula", "2234");
insert into persoane values (6,"pasari lati lungila", "2342342");
insert into persoane values (7,"numescu", "1234");