DROP DATABASE IF EXISTS gestiune_studenti;
CREATE DATABASE gestiune_studenti;
USE gestiune_studenti;

CREATE TABLE persoane
(cnp char(13) not null unique primary key,
nume varchar(50) not null,
prenume varchar(50) not null,
adresa varchar(200) not null,
nr_telefon char(12) unique not null,
email varchar(50) unique not null,
iban varchar(30) unique not null,
nr_contract int auto_increment unique not null);

CREATE TABLE admini
(cnp char(13) not null unique primary key,
FOREIGN KEY (cnp) REFERENCES persoane(cnp) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE studenti
(cnp char(13) not null unique primary key,
an_studiu int not null,
nr_ore int not null,
FOREIGN KEY (cnp) REFERENCES persoane(cnp) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE profesori
(cnp char(13) not null unique primary key,
nr_ore_min int not null,
nr_ore_max int not null,
departament varchar(50) not null,
FOREIGN KEY (cnp) REFERENCES persoane(cnp) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE materii
(id int unique auto_increment not null primary key,
nume varchar(50) not null,
descriere varchar(250),
procent_curs int,
procent_seminar int,
procent_laborator int,
nr_max_studenti int not null,
recurenta_c int not null,
recurenta_s int not null,
recurenta_l int not null
);

CREATE TABLE materii_studenti
(id_materie int not null,
cnp_student char(13) not null,
categorie ENUM('Curs', 'Seminar', 'Laborator'),
nota DECIMAL(4,2),
CHECK (nota >= 1),
CHECK (nota <= 10),
FOREIGN KEY (cnp_student) REFERENCES persoane(cnp) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (id_materie) REFERENCES materii(id) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE materii_profesor
(id_materie int not null,
cnp_profesor char(13) not null,
FOREIGN KEY (cnp_profesor) REFERENCES persoane(cnp) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (id_materie) REFERENCES materii(id) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE calendar
(id int unique auto_increment primary key,
data_programarii datetime not null,
durata time not null,
id_materie int not null,
cnp_profesor char(13) not null,
categorie ENUM('Curs', 'Seminar', 'Laborator') not null,
nr_maxim int not null,
FOREIGN KEY (id_materie) REFERENCES materii(id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (cnp_profesor) REFERENCES persoane(cnp) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE calendar_studenti
(cnp_student char(13),
id_calendar int not null,
FOREIGN KEY (cnp_student) REFERENCES persoane(cnp) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (id_calendar) REFERENCES calendar(id) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE grup_studiu
(id int unique auto_increment primary key,
id_materie int not null,
FOREIGN KEY (id_materie) REFERENCES materii(id) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE grup_studiu_studenti
(id_grup int not null,
cnp_student char(13) not null,
PRIMARY KEY (id_grup, cnp_student),
FOREIGN KEY (id_grup) REFERENCES grup_studiu(id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (cnp_student) REFERENCES persoane(cnp) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE grup_studiu_mesaje
(id_grup int not null,
cnp_student char(13) not null,
mesaj varchar(250) not null,
data_ora_trimiterii datetime not null,
FOREIGN KEY (id_grup) REFERENCES grup_studiu(id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (cnp_student) REFERENCES persoane(cnp) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE grup_studiu_activitati
(id int unique auto_increment primary key,
id_grup int not null,
cnp_profesor char(13),
nume varchar(50) not null,
descriere varchar(250),
data_programarii datetime not null, 
durata time not null,
data_expirarii datetime not null,
numar_minim int not null,
FOREIGN KEY (id_grup) REFERENCES grup_studiu(id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (cnp_profesor) REFERENCES persoane(cnp) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE grup_studiu_activitati_studenti
(id_activitate int not null,
cnp_student char(13) not null,
FOREIGN KEY (id_activitate) REFERENCES grup_studiu_activitati(id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (cnp_student) REFERENCES persoane(cnp) ON DELETE CASCADE ON UPDATE CASCADE);

drop role if exists 'student', 'profesor', 'administrator', 'superadministrator';
CREATE ROLE 'student', 'profesor', 'administrator', 'superadministrator';

GRANT ALL ON gestiune_studenti.* TO 'superadministrator';
GRANT ALL ON gestiune_studenti.* TO 'administrator';
GRANT ALL ON gestiune_studenti.* TO 'profesor';
GRANT ALL ON gestiune_studenti.* TO 'student';

DROP USER IF EXISTS superadmin@localhost;
CREATE USER superadmin@localhost IDENTIFIED BY "12345";
GRANT 'superadministrator' TO 'superadmin'@'localhost';
SET DEFAULT ROLE ALL TO 'superadmin'@'localhost';
insert into persoane  values ("696969", "numescu", "prenumescu", "000000000", "00000000000", "superadmin", "000000000", null);
insert into admini values ("696969");

DELIMITER //

#Triggere-----------------------------------------------------------------------------------------------------------------------------------
CREATE TRIGGER materii_insert BEFORE INSERT ON materii FOR EACH ROW
BEGIN
	IF NEW.procent_curs + NEW.procent_seminar + NEW.procent_laborator != 100
    THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Nu se poate adauga inregistrarea in materii: suma ponderilor nu este 100';
	END IF;
END;//

CREATE TRIGGER materii_insert_grup AFTER INSERT ON materii FOR EACH ROW
BEGIN
	INSERT INTO grup_studiu VALUES (NULL, NEW.id);
END;//

CREATE TRIGGER materii_update BEFORE UPDATE ON materii FOR EACH ROW
BEGIN
	IF NEW.procent_curs + NEW.procent_seminar + NEW.procent_laborator != 100
    THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Nu se poate actualiza inregistrarea din materii: suma ponderilor nu este 100';
	END IF;
END;//

CREATE TRIGGER calendar_insert_verificare_data BEFORE INSERT ON calendar FOR EACH ROW
BEGIN
	IF CAST(NEW.data_programarii AS DATE) < current_timestamp()
    THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Nu se poate adauga inregistrarea in calendar: programare in trecut';
    END IF;
END;//

CREATE TRIGGER calendar_update_verificare_data BEFORE UPDATE ON calendar FOR EACH ROW
BEGIN
	IF CAST(NEW.data_programarii AS DATE) < current_timestamp()
    THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Nu se poate actualiza inregistrarea din calendar: programare in trecut';
    END IF;
END;//
//
#-------------------------------------------------------------------------------------------------------------------------------------------

#Toti---------------------------------------------------------------------------------------------------------------------------------------
CREATE PROCEDURE Vizualizare_date_personale(cnp char(13))
BEGIN
	SELECT * FROM persoane p WHERE p.cnp = cnp;
END; //

CREATE PROCEDURE Vizualizare_activitati(cnp char(13), tip bool)
BEGIN
	IF (tip = true) THEN
		SELECT gsa.nume, gsa.data_programarii, gsa.durata, gsa.numar_minim, "Extra" FROM grup_studiu_activitati gsa 
		JOIN grup_studiu gs ON gsa.id_grup=gs.id 
		JOIN grup_studiu_studenti gss ON gss.id_grup=gs.id 
		WHERE gss.cnp_student=cnp AND current_timestamp()<=gsa.data_programarii
		UNION ALL
		SELECT m.nume, c.data_programarii, c.durata, c.nr_maxim, c.categorie FROM calendar c
		JOIN materii m ON c.id_materie = m.id
		JOIN materii_studenti ms ON m.id = ms.id_materie
        WHERE ms.cnp_student=cnp AND current_timestamp()<=c.data_programarii
		ORDER by data_programarii;
	ELSE
		SELECT gsa.nume, gsa.data_programarii, gsa.durata, gsa.numar_minim, "Extra" FROM grup_studiu_activitati gsa 
		WHERE gsa.cnp_profesor=cnp AND current_timestamp()<=gsa.data_programarii
		UNION ALL
		SELECT m.nume, c.data_programarii, c.durata, c.nr_maxim, c.categorie FROM calendar c
        JOIN materii m ON c.id_materie = m.id
        JOIN materii_profesor mp ON m.id = mp.id_materie
		WHERE mp.cnp_profesor=cnp AND current_timestamp()<=c.data_programarii
		ORDER by data_programarii;
    END IF;
END; //

CREATE PROCEDURE Descarcare_activitati(cnp char(13), tip bool)
BEGIN
	IF (tip = true) THEN
		SELECT gsa.nume, gsa.data_programarii, gsa.durata, gsa.numar_minim, "Extra" FROM grup_studiu_activitati gsa 
		JOIN grup_studiu gs ON gsa.id_grup=gs.id 
		JOIN grup_studiu_studenti gss ON gss.id_grup=gs.id 
		WHERE gss.cnp_student=cnp AND current_timestamp()<=gsa.data_programarii
		UNION ALL
		SELECT m.nume, c.data_programarii, c.durata, c.nr_maxim, c.categorie FROM calendar c
		JOIN materii m ON c.id_materie = m.id
		JOIN materii_studenti ms ON m.id = ms.id_materie
        WHERE ms.cnp_student=cnp AND current_timestamp()<=c.data_programarii
		ORDER by data_programarii
        INTO OUTFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\calendarStudent.csv' 
		FIELDS TERMINATED BY ','  
		LINES TERMINATED BY '\r\n';
	ELSE
		SELECT gsa.nume, gsa.data_programarii, gsa.durata, gsa.numar_minim, "Extra" FROM grup_studiu_activitati gsa 
		WHERE gsa.cnp_profesor=cnp AND current_timestamp()<=gsa.data_programarii
		UNION ALL
		SELECT m.nume, c.data_programarii, c.durata, c.nr_maxim, c.categorie FROM calendar c
        JOIN materii m ON c.id_materie = m.id
        JOIN materii_profesor mp ON m.id = mp.id_materie
		WHERE mp.cnp_profesor=cnp AND current_timestamp()<=c.data_programarii
		ORDER by data_programarii
        INTO OUTFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\calendarProfesor.csv' 
		FIELDS TERMINATED BY ','  
		LINES TERMINATED BY '\r\n';
    END IF;
END; //
#-------------------------------------------------------------------------------------------------------------------------------------------

#Small random procedures-------------------------------------------------------------------------------------------------------------------------------------------
CREATE FUNCTION get_cnp(email varchar(50)) 
RETURNS CHAR(13)
NOT DETERMINISTIC
READS SQL DATA
BEGIN 
	DECLARE cnp char(13);
	SET cnp = (SELECT persoane.cnp FROM persoane WHERE persoane.email = email);
	RETURN cnp;
END//

CREATE FUNCTION find_user_type(cnp varchar(50)) 
RETURNS INT
NOT DETERMINISTIC
READS SQL DATA
BEGIN 
	DECLARE tip1, tip2, tip3 INT DEFAULT 0;
	SET tip1 = (SELECT 1 FROM admini WHERE admini.cnp = cnp);
    SET tip2 = (SELECT 1 FROM studenti WHERE studenti.cnp = cnp);
    SET tip3 = (SELECT 1 FROM profesori WHERE profesori.cnp = cnp);
    IF (tip1 = 1) THEN RETURN 1;
    ELSEIF (tip2 = 1) THEN RETURN 2;
    ELSEIF (tip3 = 1) THEN RETURN 3;
    ELSE RETURN 0;
    END IF;
END//
#-------------------------------------------------------------------------------------------------------------------------------------------

#Select all relevant data about user, materie, group or group activities grup -------------------------------------------------------------------------------------------------------------------------------------------
CREATE PROCEDURE all_user_data()
BEGIN 
	SELECT p1.cnp, p1.nume, p1.prenume, p1.adresa, p1.nr_telefon, p1.email, p1.iban, p1.nr_contract, p2.nr_ore_min, p2.nr_ore_max, p2.departament,s.an_studiu, s.nr_ore FROM persoane p1 LEFT JOIN profesori p2 ON p1.cnp = p2.cnp LEFT JOIN studenti s ON p1.cnp = s.cnp;
END;//

CREATE PROCEDURE all_materie_data()
BEGIN 
	SELECT * FROM materii;
END;//

CREATE PROCEDURE all_grup_data()
BEGIN 
	SELECT g.id, g.id_materie, m.* FROM grup_studiu g INNER JOIN materii m ON g.id_materie = m.id;
END;//

CREATE PROCEDURE all_group_activities()
BEGIN 
	SELECT * FROM grup_studiu_activitati;
END;//
#-------------------------------------------------------------------------------------------------------------------------------------------