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

DROP USER IF EXISTS superadmin@localhost;
CREATE USER superadmin@localhost IDENTIFIED BY "12345";
GRANT 'superadministrator' TO 'superadmin'@'localhost';
SET DEFAULT ROLE ALL TO 'superadmin'@'localhost';
insert into persoane  values ("696969", "numescu", "prenumescu", "000000000", "00000000000", "superadmin", "000000000", null);
insert into admini values ("696969");

DELIMITER //

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

CREATE PROCEDURE cauta_user(tip int, nume char(50), prenume char(50))
BEGIN
	IF (tip = 0) THEN
		IF (nume = "" AND prenume = "") THEN
			SELECT * FROM persoane p;
        ELSEIF (nume != "" AND prenume = "") THEN   
			SELECT * FROM persoane p WHERE p.nume = nume;
		ELSEIF (nume = "" AND prenume != "") THEN
			SELECT * FROM persoane p WHERE p.prenume = prenume;
		ELSE
			SELECT * FROM persoane p WHERE p.nume = nume AND p.prenume = prenume;
        END IF;
	ELSEIF (tip = 1) THEN 
		IF (nume = "" AND prenume = "") THEN
			SELECT p.* FROM persoane p INNER JOIN admini a on p.cnp = a.cnp;
        ELSEIF (nume != "" AND prenume = "") THEN   
			SELECT p.* FROM persoane p INNER JOIN admini a on p.cnp = a.cnp WHERE p.nume = nume ;
		ELSEIF (nume = "" AND prenume != "") THEN
			SELECT p.* FROM persoane p INNER JOIN admini a on p.cnp = a.cnp WHERE p.prenume = prenume;
		ELSE
			SELECT p.* FROM persoane p INNER JOIN admini a on p.cnp = a.cnp WHERE p.nume = nume AND p.prenume = prenume;
        END IF;
	ELSEIF (tip = 2) THEN 
		IF (nume = "" AND prenume = "") THEN
			SELECT p.*, a.an_studiu, a.nr_ore FROM persoane p INNER JOIN studenti a on p.cnp = a.cnp;
        ELSEIF (nume != "" AND prenume = "") THEN   
			SELECT p.*, a.an_studiu, a.nr_ore FROM persoane p INNER JOIN studenti a on p.cnp = a.cnp WHERE p.nume = nume ;
		ELSEIF (nume = "" AND prenume != "") THEN
			SELECT p.*, a.an_studiu, a.nr_ore FROM persoane p INNER JOIN studenti a on p.cnp = a.cnp WHERE p.prenume = prenume;
		ELSE
			SELECT p.*, a.an_studiu, a.nr_ore FROM persoane p INNER JOIN studenti a on p.cnp = a.cnp WHERE p.nume = nume AND p.prenume = prenume;
        END IF;
	ELSEIF (tip = 3) THEN 
		IF (nume = "" AND prenume = "") THEN
			SELECT p.*, a.nr_ore_min, a.nr_ore_max, a.departament FROM persoane p INNER JOIN profesori a on p.cnp = a.cnp;
        ELSEIF (nume = "" AND prenume != "") THEN   
			SELECT p.*, a.nr_ore_min, a.nr_ore_max, a.departament FROM persoane p INNER JOIN profesori a on p.cnp = a.cnp WHERE p.nume = nume ;
		ELSEIF (nume != "" AND prenume = "") THEN
			SELECT p.*, a.nr_ore_min, a.nr_ore_max, a.departament FROM persoane p INNER JOIN profesori a on p.cnp = a.cnp WHERE p.prenume = prenume;
		ELSE
			SELECT p.*, a.nr_ore_min, a.nr_ore_max, a.departament FROM persoane p INNER JOIN profesori a on p.cnp = a.cnp WHERE p.nume = nume AND p.prenume = prenume;
        END IF;
    END IF;
END;//

#CRUD useri-------------------------------------------------------------------------------------------------------------------------------------------

CREATE PROCEDURE create_user(tip int, cnp char(13), nume varchar(50), prenume varchar(50), adresa varchar(200), nr_telefon char(12), email varchar(50), iban varchar(30), parola char(13), intreg1 int, intreg2 int, departament char(50))
BEGIN

	IF (tip < 1 OR tip > 3) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Tip ultilizator nu exista';
	END IF;

	INSERT INTO persoane VALUES (cnp, nume, prenume, adresa, nr_telefon, email, iban, null);
    
    IF (tip = 1) THEN
		INSERT INTO admini VALUES (cnp);
    ELSEIF (tip = 2) THEN
		INSERT INTO studenti VALUES (cnp, intreg1, intreg2);
	ELSEIF (tip = 3) THEN
		INSERT INTO profesori VALUES (cnp, intreg1, intreg2, departament);
	END IF;
   
	SET @query1 = CONCAT('DROP USER IF EXISTS "', email, '"@"localhost"');
	PREPARE stmt FROM @query1; EXECUTE stmt; DEALLOCATE PREPARE stmt;
    
    SET @query2 = CONCAT('CREATE USER "', email, '"@"localhost" IDENTIFIED BY "', parola,'"');
	PREPARE stmt FROM @query2; EXECUTE stmt; DEALLOCATE PREPARE stmt;
	
	IF (tip = 1) THEN
		SET @queryadmin = CONCAT('GRANT administrator TO "', email, '"@"localhost"');
		PREPARE stmt FROM @queryadmin; EXECUTE stmt; DEALLOCATE PREPARE stmt;
	ELSEIF (tip = 2) THEN 
		SET @querystudent = CONCAT('GRANT student TO "', email, '"@"localhost"');
		PREPARE stmt FROM @querystudent; EXECUTE stmt; DEALLOCATE PREPARE stmt;
	ELSEIF (tip = 3) THEN
		SET @queryprofesor = CONCAT('GRANT profesor TO "', email, '"@"localhost"');
		PREPARE stmt FROM @queryprofesor; EXECUTE stmt; DEALLOCATE PREPARE stmt;
	END IF;
    
	SET @query3 = CONCAT('SET DEFAULT ROLE ALL TO "', email, '"@"localhost"');
	PREPARE stmt FROM @query3; EXECUTE stmt; DEALLOCATE PREPARE stmt;
END;//

CREATE PROCEDURE update_user(tip int, cnp char(13), nume varchar(50), prenume varchar(50), adresa varchar(200), nr_telefon char(12), email varchar(50), iban varchar(30), nr_contract int, parola char(13), intreg1 int, intreg2 int, departament char(50))
BEGIN
	DECLARE old_email VARCHAR(50);
	DECLARE old_cnp CHAR(13);
    DECLARE old_tip INT DEFAULT 0;
    DECLARE exista INT DEFAULT 0;
    
    SELECT 1 INTO exista FROM persoane p WHERE p.nr_contract = nr_contract;
	IF (exista = 0) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Ultilizator nu exista';
	END IF;

    SET old_email = (SELECT persoane.email from persoane where persoane.nr_contract = nr_contract);
    SET old_cnp = (SELECT persoane.cnp from persoane where persoane.nr_contract = nr_contract);
	SET old_tip = find_user_type(old_cnp);
    
	IF (tip < 1 OR tip >3) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Tip ultilizator nu exista';
	END IF;

	UPDATE persoane p set p.cnp = cnp, p.nume = nume, p.prenume = prenume, p.adresa = adresa, p.nr_telefon = nr_telefon, p.email = email, p.iban = iban WHERE p.nr_contract = nr_contract;
	
    IF (old_tip != tip) THEN
		DELETE FROM admini WHERE admini.cnp = cnp;
        DELETE FROM studenti WHERE studenti.cnp = cnp;
        DELETE FROM profesori WHERE profesori.cnp = cnp;
        
		IF (tip = 1) THEN
			INSERT INTO admini VALUES (cnp);
		ELSEIF (tip = 2) THEN
			INSERT INTO studenti VALUES (cnp, intreg1, intreg2);
		ELSEIF (tip = 3) THEN
			INSERT INTO profesori VALUES (cnp, intreg1, intreg2, departament);
		END IF;
        
    ELSE
		IF (tip = 2) THEN
			UPDATE studenti s SET s.an_studiu = intreg1, s.nr_ore = intreg2 WHERE s.cnp = cnp;
		ELSEIF (tip = 3) THEN
			UPDATE profesori p SET p.nr_ore_min = intreg1, p.nr_ore_max = intreg2, p.departament = departament WHERE p.cnp = cnp;
		END IF;
		
		IF (old_email != email) THEN
			SET @querye = CONCAT('RENAME USER "', old_email, '"@"localhost" TO "', email, '"@"localhost"' );
			PREPARE stmt FROM @querye; EXECUTE stmt; DEALLOCATE PREPARE stmt;
		END IF;
	END IF;
    
    IF (parola != null) THEN
		SET @query1 = CONCAT('DROP USER IF EXISTS "', old_email, '"@"localhost"');
		PREPARE stmt FROM @query1; EXECUTE stmt; DEALLOCATE PREPARE stmt;
		
		SET @query2 = CONCAT('CREATE USER "', email, '"@"localhost" IDENTIFIED BY "', parola,'"');
		PREPARE stmt FROM @query2; EXECUTE stmt; DEALLOCATE PREPARE stmt;

		IF (tip = 1) THEN
			SET @queryadmin = CONCAT('GRANT administrator TO "', email, '"@"localhost"');
			PREPARE stmt FROM @queryadmin; EXECUTE stmt; DEALLOCATE PREPARE stmt;
		ELSEIF (tip = 2) THEN 
			SET @querystudent = CONCAT('GRANT student TO "', email, '"@"localhost"');
			PREPARE stmt FROM @querystudent; EXECUTE stmt; DEALLOCATE PREPARE stmt;
		ELSEIF (tip = 3) THEN
			SET @queryprofesor = CONCAT('GRANT profesor TO "', email, '"@"localhost"');
			PREPARE stmt FROM @queryprofesor; EXECUTE stmt; DEALLOCATE PREPARE stmt;
		END IF;
		
		SET @query3 = CONCAT('SET DEFAULT ROLE ALL TO "', email, '"@"localhost"');
		PREPARE stmt FROM @query3; EXECUTE stmt; DEALLOCATE PREPARE stmt;
	END IF;
END;//

CREATE PROCEDURE read_user(cnp char(13))
BEGIN
	DECLARE tip1, tip2, tip3 INT DEFAULT 0;
    
    SET tip1 = (SELECT 1 FROM admini WHERE admini.cnp = cnp);
    SET tip2 = (SELECT 1 FROM studenti WHERE studenti.cnp = cnp);
    SET tip3 = (SELECT 1 FROM profesori WHERE profesori.cnp = cnp);
    
	IF (tip1 = 1) THEN
		SELECT * FROM persoane WHERE persoane.cnp = cnp;
	ELSEIF (tip2 = 1) THEN
		SELECT * FROM persoane p INNER JOIN studenti s ON p.cnp = s.cnp  WHERE p.cnp = cnp;
	ELSEIF (tip3 = 1) THEN
		SELECT * FROM persoane p1 INNER JOIN profesori p2 ON p1.cnp = p2.cnp  WHERE p1.cnp = cnp;
	ELSE
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Utilizatorul nu exita';
	END IF;
END;//

CREATE PROCEDURE delete_user(cnp char(13))
BEGIN
	DECLARE exista INT DEFAULT 0;
    DECLARE email VARCHAR(50); 
	SET exista = (SELECT 1 FROM persoane WHERE persoane.cnp = cnp);
    SET email = (SELECT persoane.email FROM persoane where persoane.cnp = cnp);
    
	SET @query1 = CONCAT('DROP USER IF EXISTS "', email, '"@"localhost"');
	PREPARE stmt FROM @query1; EXECUTE stmt; DEALLOCATE PREPARE stmt;
    
	IF (exista = 0) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Tip ultilizator nu exista';
	END IF;

	DELETE FROM persoane WHERE persoane.cnp = cnp;
END;//
#-------------------------------------------------------------------------------------------------------------------------------------------

#CRUD materii-------------------------------------------------------------------------------------------------------------------------------------------

CREATE PROCEDURE create_materie (nume varchar(50), descriere varchar(250), procent_curs int, procent_seminar int, procent_laborator int, nr_max_studenti int, recurenta_c int, recurenta_s int, recurenta_l int)
BEGIN
	INSERT INTO materii VALUES (null, nume, descriere, procent_curs, procent_seminar, procent_laborator, nr_max_studenti, recurenta_c, recurenta_s, recurenta_l);
END//

CREATE PROCEDURE update_materie (id int, nume varchar(50), descriere varchar(250), procent_curs int, procent_seminar int, procent_laborator int, nr_max_studenti int, recurenta_c int, recurenta_s int, recurenta_l int)
BEGIN
	DECLARE exista INT DEFAULT 0;
	SELECT 1 INTO exista FROM materii m WHERE m.id = id;
	IF (exista = 0) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Ultilizator nu exista';
	END IF;
	UPDATE materii m set m.nume = nume, m.descriere = descriere, m.procent_curs = procent_curs, m.procent_seminar = procent_seminar, m.procent_laborator = procent_laborator, m.recurenta_c = recurenta_c, m.recurenta_s = recurenta_s, m.recurenta_l = recurenta_l WHERE m.id = id;
END//

CREATE PROCEDURE read_materie (id int)
BEGIN
	IF (id = NULL) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Ultilizator nu exista';
	END IF;
	SELECT * FROM materii where materii.id = id;
END//

CREATE PROCEDURE delete_materie (id int)
BEGIN
	DECLARE exista INT DEFAULT 0;
	SELECT 1 INTO exista FROM materii m WHERE m.id = id;
	IF (exista = 0) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Ultilizator nu exista';
	END IF;
	DELETE FROM materii where materii.id = id;
END//
#-------------------------------------------------------------------------------------------------------------------------------------------

#CRUD grupuri de studiu-------------------------------------------------------------------------------------------------------------------------------------------
CREATE PROCEDURE create_grup (id_materie int)
BEGIN
	INSERT INTO grup_studiu VALUES (null, id_materie);
END//

CREATE PROCEDURE update_grup (id int, id_materie int)
BEGIN
	UPDATE grup_studiu g set g.id_materie = id_materie where g.id = id;
END//

CREATE PROCEDURE read_grup (id int)
BEGIN
	SELECT * FROM grup_studiu where grup_studiu.id = id;
END//

CREATE PROCEDURE delete_grup (id int)
BEGIN
	DELETE FROM grup_studiu where grup_studiu.id = id;
END//
#-------------------------------------------------------------------------------------------------------------------------------------------

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

CREATE PROCEDURE Vizualizare_activitati(cnp char(13))
BEGIN
	SELECT * FROM grup_studiu_activitati gsa 
    JOIN grup_studiu gs ON gsa.id_grup=gs.id 
    JOIN grup_studiu_studenti gss ON gss.id_grup=gs.id 
    WHERE gss.cnp_student=cnp_student AND CURRENT_TIMESTAMP()<=gsa.data_programarii
	ORDER by data_programarii;
END; //
#-------------------------------------------------------------------------------------------------------------------------------------------

#Student------------------------------------------------------------------------------------------------------------------------------------
CREATE PROCEDURE Cautare_materie(materie varchar(50))
BEGIN
	SELECT * FROM materii WHERE nume=materie;
END; //

CREATE PROCEDURE Inscriere_materie(id_materie int, cnp_student char(13))
BEGIN
	SELECT id INTO @id FROM materii WHERE id=id_materie;
	IF(@id!=null)
	THEN
		SELECT 1 INTO @nexists FROM materii_studenti ms WHERE ms.id_materie = id_materie AND ms.cnp_student = cnp_student;
        IF(@nexists = 1)
        THEN
			INSERT INTO materii_studenti(id_materie, cnp_student) VALUES(id_materie, cnp_student);
		ELSE
			SIGNAL SQLSTATE '45000' SET message_text='Sunteti deja inscris';
		END IF;
	ELSE
		SIGNAL SQLSTATE '45000' SET message_text='Materia nu exista';
	END IF;
END;//

CREATE PROCEDURE Renuntare_materie(id_materie int, cnp_student char(13))
BEGIN
	DELETE FROM materii_studenti ms WHERE ms.id_materie = id_materie AND ms.cnp_student = cnp_student;
END; //

CREATE PROCEDURE Inscriere_calendar(id_materie int, cnp_student char(13))
BEGIN
	
END; //

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