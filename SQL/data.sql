USE gestiune_studenti;

call create_user (1, "1111", "numescu", "prenumescu", "Pomilor 34", "0770123567", "numescu@yahoo.com", "ROBTRL213124", "parola111", null, null, null);
call create_user (1, "2222", "frumusescu", "uratu", "Pomilor 12", "0770222222", "frumusescu@yahoo.com", "ROBTRL123124", "parola22", null, null, null);
call create_user (3, "3333", "grigorescu", "grigore", "Acadelelor 12", "0770333333", "grigorescu@gmail.com", "ROBTRL456124", "parola3333", 2, 20, "informatica");
call create_user (3, "4444", "gigel", "gigi", "Lalelelor 22", "07704444444", "gigel@yahoo.com", "ROBTRL264554", "parola4444", 2, 20, "informatica");
call create_user (3, "5555", "bula", "strula", "1848 22", "0770555555", "bula@yahoo.com", "ROBTRL233344", "parolabula", 2, 20, "informatica");
call create_user (3, "6666", "pasari lati lungila", "fomila", "Plopilor 19", "0770666666", "pasarila@gmail.com", "ROBTRL2333324", "parola5555", 2, 20, "informatica");
call create_user (2, "7777", "numescu", "antonescu", "Pomilor 34", "0770777777", "numescu2001@yahoo.com", "ROBTRL777777", "parola9999", 2, 5, null);
call create_user (2, "8888", "numescu", "prenumescu", "Pomilor 34", "0770888888", "numescu888@yahoo.com", "ROBTRL888888", "parola8888", 2, 6, null);
call create_user (2, "9999", "numescu", "prenumescu9", "Pomilor 34", "0770999999", "numescu999@yahoo.com", "ROBTRL999999", "parola9999", 2, 7, null);
call create_user (2, "10001", "numescu10", "prenumescu10", "Pomilor 34", "0770100001", "numescu1001@yahoo.com", "ROBTRL10001", "parola10001", 2, 9, null);
call create_user (1, "10002", "Kovacs", "Paul", "Pomilor 34", "0770321321", "admin", "ROBTRL31212", "12345", 2, 20, "informatica");
call create_user (2, "10003", "numescu", "studentescu", "Pomilor 38", "0770100003", "student", "ROBTRL32131", "12345", 2, 3, null);
call create_user (2, "10233", "numescu", "studentescu", "Pomilor 38", "0770103203", "student2", "ROBTRL37831", "12345", 2, 3, null);
call create_user (3, "10004", "Budiul", "Cristian", "Pomilor 39", "0770123423", "profesor", "ROBTRL42131", "12345", 2, 20, "informatica");

insert into materii values (null, "bd", "baze de date", 10, 30, 60, 150, 7, 7, 7);
insert into materii values (null, "poo", "programare orientata obiect", "10","30", "60", "150", 7, 7, 7);
insert into materii values (null, "can", "can", "10","30", "60", "150", 7, 7, 7);
insert into materii values (null, "af", "af", "10","30", "60", "150", 7, 7, 7);
insert into materii values (null, "ms", "matematici speciale", "10","30", "60", "150", 7, 7, 7);
insert into materii values (null, "alga", "algebra", "10","30", "60", "150", 7, 7, 7);
insert into materii values (null, "mes", "mes", "10","30", "60", "150", 7, 7, 7);
insert into materii values (null, "msi", "", "10","30", "60", "150", 7, 7, 7);
insert into materii values (null, "cpp", "better than c", "10","30", "60", "150", 7, 7, 7);
insert into materii values (null, "java", "why", "10","30", "60", "150", 7, 7, 7);
insert into materii values (null, "bd", "why", "10","30", "60", "150", 7, 7, 7);

call Programare_calendar("10004", "2022-01-10 08:00:00", "2022-05-12 08:00:00", "2:00:00", 1, "Curs", 150); 
call Programare_calendar("10004", "2022-01-10 08:00:00", "2022-05-12 08:00:00", "2:00:00", 1, "Seminar", 30); 
call Programare_calendar("10004", "2022-01-10 08:00:00", "2022-05-12 08:00:00", "2:00:00", 1, "Laborator", 15); 
#call Programare_calendar("10004", "2022-01-10 10:00:00", "2022-05-12 10:00:00", "2:00:00", 1, "Seminar", 30); 
#call Programare_calendar("10004", "2022-01-10 12:00:00", "2022-05-12 12:00:00", "2:00:00", 1, "Laborator", 15); 

insert into grup_studiu_activitati values (null, 1, "10004", "Activitate 1", "Activitate Grup BD", "2022-10-09 00:00:00", "2:00:00", "2022-10-09 00:00:00", 0);
insert into grup_studiu_activitati values (null, 1, null, "Activitate 2", "Activitate Grup BD", "2022-10-09 00:00:00", "2:00:00", "2022-10-09 00:00:00", 0);
insert into grup_studiu_activitati values (null, 1, null, "Activitate 3", "Activitate Grup BD", "2022-10-09 00:00:00", "2:00:00", "2022-10-09 00:00:00", 0);
insert into grup_studiu_activitati values (null, 1, null, "Activitate 4", "Activitate Grup BD", "2022-10-09 00:00:00", "2:00:00", "2022-10-09 00:00:00", 0);
insert into grup_studiu_activitati values (null, 2, null, "Activitate 1", "Activitate Grup POO", "2022-10-09 00:00:00", "2:00:00", "2022-10-09 00:00:00", 0);

insert into materii_studenti values (1, "7777", null, null);
insert into materii_studenti values (1, "9999", null, null);
insert into materii_studenti values (1, "8888", null, null);
insert into materii_studenti values (2, "8888", null, null);
insert into materii_studenti values (1, "10003", 'Curs', 10);
insert into materii_studenti values (1, "10003", 'Seminar', 10);
insert into materii_studenti values (1, "10003", 'Laborator', 10);
insert into materii_studenti values (2, "10003", 'Curs', 9);
insert into materii_studenti values (2, "10003", 'Laborator', null);

insert into materii_profesor values (1, "10004");
insert into grup_studiu values (null, 1);
insert into grup_studiu values (null, 2);

call Inscriere_calendar(1, "10003");

