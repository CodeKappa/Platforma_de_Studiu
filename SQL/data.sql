USE gestiune_studenti;

insert into persoane  values ("1341", "numescu", "prenumescu", "Pomilor 34", "0770123567", "numescu@yahoo.com", "ROBTRL213124", null);
insert into persoane values ("1235", "frumusescu", "uratu", "Pomilor 12", "0770666666", "frumusescu@yahoo.com", "ROBTRL123124", null);
insert into persoane values ("2124", "grigorescu", "grigore", "Acadelelor 12", "0770999999", "grigorescu@gmail.com", "ROBTRL456124", null);
insert into persoane values ("5123", "gigel", "gigi", "Lalelelor 22", "0770111111", "gigel@yahoo.com", "ROBTRL264554", null);
#insert into persoane values ("6123", "bula", "strula", "1848 22", "0770444222", "bula@yahoo.com", "ROBTRL233344", null);
call create_user (3,"6123", "bula", "strula","1848 22", "0770444222", "bula@yahoo.com", "ROBTRL233344", "parolabula", 2, 20, "informatica");
insert into persoane values ("1234", "pasari lati lungila", "fomila", "Plopilor 19", "0770111333", "pasarila@gmail.com", "ROBTRL2333324", null);
insert into persoane values ("2523", "numescu", "antonescu","Pomilor 34", "0770133123", "numescu2001@yahoo.com", "ROBTRL2166624", null);

insert into studenti values ("1235", 2, 0);
insert into studenti values ("2523", 1, 0);
insert into admini values ("2124");
insert into admini values ("5123");
insert into profesori values ("6123", 2, 20, "informatica");

insert into materii values (null, "bd", "baze de date", "10","30", "60", "150", 7, 7, 7);
insert into materii values (null, "poo", "programare orientata obiect", "10","30", "60", "150", 7, 7, 7);
insert into materii values (null, "can", "can", "10","30", "60", "150", 7, 7, 7);
insert into materii values (null, "af", "af", "10","30", "60", "150", 7, 7, 7);
insert into materii values (null, "ms", "matematici speciale", "10","30", "60", "150", 7, 7, 7);
insert into materii values (null, "alga", "algebra", "10","30", "60", "150", 7, 7, 7);
insert into materii values (null, "mes", "mes", "10","30", "60", "150", 7, 7, 7);
insert into materii values (null, "msi", "", "10","30", "60", "150", 7, 7, 7);
insert into materii values (null, "cpp", "better than c", "10","30", "60", "150", 7, 7, 7);
insert into materii values (null, "java", "why", "10","30", "60", "150", 7, 7, 7);