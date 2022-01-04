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
	IF CAST(NEW.data_programarii AS DATE) < GETDATE()
    THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Nu se poate adauga inregistrarea in calendar: programare in trecut';
    END IF;
END;//

CREATE TRIGGER calendar_update_verificare_data BEFORE UPDATE ON calendar FOR EACH ROW
BEGIN
	IF CAST(NEW.data_programarii AS DATE) < GETDATE()
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
		SELECT gsa.nume, gsa.data_programarii FROM grup_studiu_activitati gsa 
		JOIN grup_studiu gs ON gsa.id_grup=gs.id 
		JOIN grup_studiu_studenti gss ON gss.id_grup=gs.id 
		WHERE gss.cnp_student=cnp AND current_timestamp()<=gsa.data_programarii
		UNION ALL
		SELECT m.nume, c.data_programarii FROM calendar c
		JOIN materii m ON c.id_materie = m.id
		JOIN materii_studenti ms ON m.id = ms.id_materie
        WHERE ms.cnp_student=cnp AND current_timestamp()<=c.data_programarii
		ORDER by data_programarii;
	ELSE
		SELECT gsa.nume, gsa.data_programarii FROM grup_studiu_activitati gsa 
		WHERE gsa.cnp_profesor=cnp AND current_timestamp()<=gsa.data_programarii
		UNION ALL
		SELECT m.nume, c.data_programarii FROM calendar c
		WHERE m.cnp_profesor=cnp AND current_timestamp()<=c.data_programarii
		ORDER by data_programarii;
    END IF;
END; //
#-------------------------------------------------------------------------------------------------------------------------------------------

#Student------------------------------------------------------------------------------------------------------------------------------------
CREATE PROCEDURE Cautare_materie(materie varchar(50))
BEGIN
	SELECT * FROM materii WHERE nume=materie;
END; //

CREATE PROCEDURE Inscriere_materie(materie varchar(50), cnp_student char(13))
BEGIN
	DECLARE id_selectat int;
    
	CREATE TEMPORARY TABLE lista_inscrieri
	SELECT id_materie, COUNT(*) AS magnitude 
	FROM studenti_materie
	WHERE id_materie in (select id_materie from materii where nume = "bd")
	GROUP BY id_materie 
	ORDER BY magnitude ASC;
    
    CREATE TEMPORARY TABLE lista_inscrieri_curata
    SELECT li.id_materie, li.magnitude INTO id_selectat FROM lista_inscrieri li
    JOIN materii m ON li.id_materie = m.id 
    WHERE m.nr_max_studenti >= li.magnitude
    ORDER BY magnitude ASC
    LIMIT 1;
    
	SELECT id_materie INTO id_selectat FROM lista_inscrieri_curata;
    INSERT INTO materii_studenti VALUES(id_selectat, cnp_student, null, null);
END;//

CREATE PROCEDURE Renuntare_materie(id_materie int, cnp_student char(13))
BEGIN
	DELETE FROM materii_studenti ms WHERE ms.id_materie = id_materie AND ms.cnp_student = cnp_student;
END; //

CREATE PROCEDURE Vizualizare_calendar(cnp_student char(13))
BEGIN
	SELECT c.* FROM calendar c
    JOIN materii m ON c.id_materie = m.id
    JOIN materii_studenti ms ON m.id = ms.id_materie
    WHERE ms.cnp_student = cnp_student;
END; //

/*
CREATE PROCEDURE Inscriere_calendar(id_materie int, cnp_student char(13))
BEGIN
	IF(((SELECT COUNT(*) FROM calendar_studenti cs
    JOIN calendar c ON cs.id_calendar = c.id
    WHERE c.id_materie = id_materie) < (SELECT nr_maxim FROM calendar c WHERE c.id_materie = id_materie)) AND current_timestamp() < (SELECT data_programarii)) THEN
		
END; //
*/

CREATE PROCEDURE Vizualizare_note(cnp_student char(13))
BEGIN
	SELECT x.nume, m.nota FROM materii_studenti m JOIN materii x ON m.id_materie=x.id WHERE cnp_student = m.cnp_student;
END; //

CREATE PROCEDURE Vizualizare_grupuri()
BEGIN
	SELECT gs.id, m.nume, p.nume, p.prenume FROM grup_studiu_studenti gss JOIN persoane p ON gss.cnp_student=p.cnp JOIN grup_studiu gs ON gss.id_grup=gs.id JOIN materii m ON gs.id_materie=m.id
ORDER BY gs.id;
END; //

CREATE PROCEDURE Inscriere_grup(id_grup int, cnp_student char(13))
BEGIN
	SELECT gss.id_grup INTO @id FROM grup_studiu_studenti gss WHERE gss.id_grup = id_grup;
    IF(@id != null)
    THEN
		INSERT INTO grup_studiu_studenti VALUES (id_grup, cnp_stdent);
    ELSE
		SIGNAL SQLSTATE '45000' SET message_text='Grupul nu exista';
    END IF;
END;//

CREATE PROCEDURE Parasire_grup(id_grup int, cnp_student char(13))
BEGIN
	DELETE FROM grup_studiu_studenti gss WHERE gss.id_grup = id_grup AND gss.cnp_student = cnp_student;
END;//

CREATE PROCEDURE Vizualizare_membrii_grup(id_grup int, cnp_student char(13))
BEGIN
	SELECT cnp_student INTO @cnp FROM grup_studiu_studenti gss WHERE gss.id_grup = id_grup AND gss.cnp_student = cnp_student;
    IF(@cnp != null)
    THEN
		SELECT p.nume, p.prenume FROM grup_studiu_studenti gss JOIN persoane p WHERE gss.cnp_student = p.cnp;
	END IF;
END; //

CREATE PROCEDURE Adaugare_activitate_grup(id_grup int, nume varchar(50), descriere varchar(250), durata time, durata_expirare time, numar_minim int)
BEGIN
	SELECT 1 INTO @cgs FROM grup_studiu gs WHERE gs.id = id_grup;
    IF(@cgs = 1)
    THEN
		INSERT INTO grup_studiu_activitati VALUES (id_grup, nume, descriere, current_timestamp(), durata, current_timestamp() + durata_expirare, numar_minim);
    ELSE
		SIGNAL SQLSTATE '45000' SET message_text='Grupul nu exista';
    END IF;
END; //

CREATE PROCEDURE Vizualizare_activitati_grupuri(cnp_student char(13))
BEGIN
	
END; //

CREATE PROCEDURE Inscriere_activitate_grup(cnp_student char(13))
BEGIN
	
END; //

CREATE PROCEDURE Mesaje_vizualizare(id int)
BEGIN
	SELECT p.nume, p.prenume, g.mesaj, g.data_ora_trimiterii FROM grup_studiu_mesaje g JOIN persoane p WHERE g.cnp_student=p.cnp AND id_grup=id ORDER BY g.data_ora_trimiterii;
END; //

CREATE PROCEDURE Mesaje_scriere(mesaj varchar(250), id_grup int, cnp_student char(13))
BEGIN
	INSERT INTO grup_studiu_mesaje(id_grup, cnp_student, mesaj, data_ora_trimiterii) VALUES (id_grup, cnp_student, mesaj, current_timestamp());
END; //

CREATE PROCEDURE Descarcare_activitati(cnp_student char(13))
BEGIN
	SELECT * FROM grup_studiu_activitati gsa JOIN grup_studiu gs ON gsa.id_grup=gs.id JOIN grup_studiu_studenti gss ON gss.id_grup=gs.id WHERE gss.cnp_student=cnp_student AND CURRENT_TIMESTAMP()<=gsa.data_programarii
	ORDER by data_programarii
	INTO OUTFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\activitati.csv' 
	FIELDS TERMINATED BY ','  
	LINES TERMINATED BY '\r\n';
END; //
#-------------------------------------------------------------------------------------------------------------------------------------------

#profesor-----------------------------------------------------------------------------------------------------------------------------------

CREATE PROCEDURE Vizualizare_date_personale_profesor(cnp char(13))
BEGIN
	SELECT * FROM persoane p JOIN profesori pp ON p.cnp = pp.cnp WHERE p.cnp = cnp;
END;

CREATE PROCEDURE Vizualizare_activitati_studiu(cnp_profesor char(13))
BEGIN
	SELECT * FROM persoane p 
    JOIN materii_profesori mp ON p.cnp = mp.cnp_profesor
    JOIN materii m ON mp.id_materie = m.id
    JOIN grup_studiu gs ON m.id = gs.id_materie
    JOIN grup_studiu_activitati gsa ON gs.id = gsa.id_grup
    WHERE current_timestamp() < gsa.data_programarii();
END; //

CREATE PROCEDURE Inscriere_activitati_studiu(cnp_profesor char(13), id_gsa int)
BEGIN
	UPDATE grup_studiu_activitati gsa SET gsa.cnp_profesor = cnp_profesor WHERE gsa.id = id_gsa;
END; //

CREATE PROCEDURE Programare_calendar(cnp_profesor char(13), data_inceput datetime, data_final datetime, durata time, id_materie int, categorie enum('Curs','Seminar','Laborator'), nr_maxim int)
BEGIN
	DECLARE recurenta int;
    DECLARE current_datetime datetime;
	IF (categorie = 'Curs') THEN
		SELECT recurenta_c INTO recurenta FROM materii m WHERE m.id = id_materie;
	ELSEIF(categorie = 'Seminar') THEN
		SELECT recurenta_s INTO recurenta FROM materii m WHERE m.id = id_materie;
    ELSE
		SELECT recurenta_l INTO recurenta FROM materii m WHERE m.id = id_materie;
    END IF;
    
    WHILE current_datetime < data_final DO
		INSERT INTO calendar VALUES (current_datetime, durata, id_materie, cnp_profesor, categorie, nr_maxim);
        SET current_datetime = ADDDATE(current_datetime, recurenta);
	END WHILE;
END; //

CREATE PROCEDURE Vizualizare_ponderi(cnp_profesor char(13))
BEGIN
	SELECT nume, procent_curs, procent_seminar, procent_laborator 
    FROM materii m 
    JOIN materii_profesor mp ON m.id = mp.id_materie
    WHERE mp.cnp_profesor = cnp_profesor;
END; //

CREATE PROCEDURE Actualizare_ponderi(id_materie int, p_c int, p_s int, p_l int)
BEGIN
	UPDATE materii SET procent_curs = p_c, procent_seminar = p_s, procent_laborator = p_l WHERE id = id_materie;  
END; //

CREATE PROCEDURE Notare_studenti(id_materie int, cnp_student char(13), categorie enum('Curs','Seminar','Laborator'), nota decimal(4,2))
BEGIN
	INSERT INTO materii_studenti VALUES (id_materie, cnp_student, categorie, nota);
END; //

CREATE PROCEDURE Vizualizare_studenti_materie(id_materie int)
BEGIN
	SELECT p.nume, p.prenume, s.an_studiu, p.nr_telefon, p.email, ms.nota FROM persoane p
    JOIN studenti s ON p.cnp = s.cnp
    JOIN materii_studenti ms ON p.cnp = ms.cnp_student
    WHERE id_materie = ms.id_materie;
END; //

CREATE PROCEDURE Descarcare_studenti_materie(id_materie int)
BEGIN
	SELECT p.nume, p.prenume, s.an_studiu, p.nr_telefon, p.email, ms.nota FROM persoane p
    JOIN studenti s ON p.cnp = s.cnp
    JOIN materii_studenti ms ON p.cnp = ms.cnp_student
    WHERE id_materie = ms.id_materie
    INTO OUTFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\catalog.csv' 
	FIELDS TERMINATED BY ','  
	LINES TERMINATED BY '\r\n';
END; //
#-------------------------------------------------------------------------------------------------------------------------------------------

#admin--------------------------------------------------------------------------------------------------------------------------------------

#-------------------------------------------------------------------------------------------------------------------------------------------