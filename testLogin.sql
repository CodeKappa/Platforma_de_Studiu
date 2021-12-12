drop database test;
create database test;
use test;

create table persoane
(
  cnp char(13) unique,
  nume varchar(30) NOT NULL,
  parola varchar(50) NOT NULL,
  adresa varchar(50) NOT NULL,
  tel char(10) unique NOT NULL,
  email varchar(100) unique NOT NULL,
  iban char(34) NOT NULL,
  nr_contract int unique auto_increment,
  primary key (cnp)
);

insert into persoane (cnp, nume, parola, adresa, tel, email, iban) values ("1341", "numescu", "1234", "Pomilor 34", "0770123567", "numescu@yahoo.com", "ROBTRL213124");
insert into persoane (cnp, nume, parola, adresa, tel, email, iban) values ("1235", "frumusescu", "4213", "Pomilor 12", "0770666666", "frumusescu@yahoo.com", "ROBTRL123124");
insert into persoane (cnp, nume, parola, adresa, tel, email, iban) values ("2124", "grigorescu", "666", "Acadelelor 12", "0770999999", "grigorescu@gmail.com", "ROBTRL456124");
insert into persoane (cnp, nume, parola, adresa, tel, email, iban) values ("5123", "gigel", "23312", "Lalelelor 22", "0770111111", "gigel@yahoo.com", "ROBTRL264554");
insert into persoane (cnp, nume, parola, adresa, tel, email, iban) values ("6123", "bula", "parola", "1848 22", "0770444222", "bula@yahoo.com", "ROBTRL233344");
insert into persoane (cnp, nume, parola, adresa, tel, email, iban) values ("1234", "pasari lati lungila", "fomila", "Plopilor 19", "0770111333", "pasarila@gmail.com", "ROBTRL2333324");
insert into persoane (cnp, nume, parola, adresa, tel, email, iban) values ("2523", "numescu", "1234", "Pomilor 34", "0770133123", "numescu2001@yahoo.com", "ROBTRL2166624");

select * from persoane order by nr_contract