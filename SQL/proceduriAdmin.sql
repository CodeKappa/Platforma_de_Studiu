#procedures used by admins
use gestiune_studenti;

DELIMITER //

#Cautare si filtrare user-------------------------------------------------------------------------------------------------------------------------------------------
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
#-------------------------------------------------------------------------------------------------------------------------------------------

#Cautare materie in functie de materie si cautare studenti la o materie specificata-------------------------------------------------------------------------------------------------------------------------------------------
CREATE PROCEDURE cauta_materie(nume varchar(50))
BEGIN
	IF (nume = "") THEN
		SELECT * FROM materii;
	ELSE 
		SELECT * FROM materii m WHERE m.nume = nume;
	END IF;
END;//

CREATE PROCEDURE studentiLaMaterie(nume varchar(50))
BEGIN
	IF (nume = "") THEN
		SELECT m.id, m.nume as nume_materie, p.cnp, p.nume, p.prenume FROM materii m INNER JOIN materii_studenti ms ON m.id = ms.id_materie INNER JOIN persoane p ON ms.cnp_student = p.cnp;
	ELSE 
		SELECT m.id, m.nume as nume_materie, p.cnp, p.nume, p.prenume FROM materii m INNER JOIN materii_studenti ms ON m.id = ms.id_materie INNER JOIN persoane p ON ms.cnp_student = p.cnp WHERE m.nume = nume;
	END IF;
END;//
#-------------------------------------------------------------------------------------------------------------------------------------------

#Asignare profesor la materie-------------------------------------------------------------------------------------------------------------------------------------------
CREATE PROCEDURE asigneaza_profesor(id_profesor int, id_materie int)
BEGIN
	INSERT INTO materii_profesor VALUES (id_materie, id_profesor);
END;//
#-------------------------------------------------------------------------------------------------------------------------------------------

#Adaugare activitati-------------------------------------------------------------------------------------------------------------------------------------------
CREATE PROCEDURE adauga_activitati(id_grup int, cnp_profesor char(13), nume varchar(50), descriere varchar(250), data_programarii varchar(30), durata varchar(30), data_expirarii varchar(30), numar_minim int)
BEGIN
	INSERT INTO grup_studiu_activitati VALUES (null, id_grup, cnp_profesor, nume, descriere, CONVERT(data_programarii,DATETIME), CONVERT(durata,TIME), CONVERT(data_expirarii,DATETIME), numar_minim);
END;//

CREATE PROCEDURE read_activitate (id int)
BEGIN
	IF (id = "") THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Activitate nu exista';
	END IF;
	SELECT * FROM grup_studiu_activitati g WHERE g.id = id;
END//
#-------------------------------------------------------------------------------------------------------------------------------------------

#CRUD superadmin useri-------------------------------------------------------------------------------------------------------------------------------------------
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

#CRUD admin useri-------------------------------------------------------------------------------------------------------------------------------------------
CREATE PROCEDURE admin_create_user(tip int, cnp char(13), nume varchar(50), prenume varchar(50), adresa varchar(200), nr_telefon char(12), email varchar(50), iban varchar(30), parola char(13), intreg1 int, intreg2 int, departament char(50))
BEGIN

	IF (tip < 1 OR tip > 3) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Tip ultilizator nu exista';
	END IF;
    
	IF (tip = 1) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Nu ai drepturi pentru a executa aceasta operatie';
	END IF;
    
	INSERT INTO persoane VALUES (cnp, nume, prenume, adresa, nr_telefon, email, iban, null);
    
    IF (tip = 2) THEN
		INSERT INTO studenti VALUES (cnp, intreg1, intreg2);
	ELSEIF (tip = 3) THEN
		INSERT INTO profesori VALUES (cnp, intreg1, intreg2, departament);
	END IF;
   
	SET @query1 = CONCAT('DROP USER IF EXISTS "', email, '"@"localhost"');
	PREPARE stmt FROM @query1; EXECUTE stmt; DEALLOCATE PREPARE stmt;
    
    SET @query2 = CONCAT('CREATE USER "', email, '"@"localhost" IDENTIFIED BY "', parola,'"');
	PREPARE stmt FROM @query2; EXECUTE stmt; DEALLOCATE PREPARE stmt;
	
	IF (tip = 2) THEN 
		SET @querystudent = CONCAT('GRANT student TO "', email, '"@"localhost"');
		PREPARE stmt FROM @querystudent; EXECUTE stmt; DEALLOCATE PREPARE stmt;
	ELSEIF (tip = 3) THEN
		SET @queryprofesor = CONCAT('GRANT profesor TO "', email, '"@"localhost"');
		PREPARE stmt FROM @queryprofesor; EXECUTE stmt; DEALLOCATE PREPARE stmt;
	END IF;
    
	SET @query3 = CONCAT('SET DEFAULT ROLE ALL TO "', email, '"@"localhost"');
	PREPARE stmt FROM @query3; EXECUTE stmt; DEALLOCATE PREPARE stmt;
END;//

CREATE PROCEDURE admin_update_user(tip int, cnp char(13), nume varchar(50), prenume varchar(50), adresa varchar(200), nr_telefon char(12), email varchar(50), iban varchar(30), nr_contract int, parola char(13), intreg1 int, intreg2 int, departament char(50))
BEGIN
	DECLARE old_email VARCHAR(50);
	DECLARE old_cnp CHAR(13);
    DECLARE old_tip, tip1 INT DEFAULT 0;
    DECLARE exista INT DEFAULT 0;
    
    SELECT 1 INTO exista FROM persoane p WHERE p.nr_contract = nr_contract;
	IF (exista = 0) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Ultilizator nu exista';
	END IF;
    
	IF (tip = 1) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Nu ai drepturi pentru a executa aceasta operatie';
	END IF;
    
    SET old_email = (SELECT persoane.email from persoane where persoane.nr_contract = nr_contract);
    SET old_cnp = (SELECT persoane.cnp from persoane where persoane.nr_contract = nr_contract);
	SET old_tip = find_user_type(old_cnp);

	
	SELECT 1 INTO tip1 FROM admini WHERE admini.cnp = old_cnp;
	IF (tip1 = 1) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Nu ai drepturi pentru a executa aceasta operatie';
	END IF;

	IF (tip < 1 OR tip >3) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Tip ultilizator nu exista';
	END IF;

	UPDATE persoane p set p.cnp = cnp, p.nume = nume, p.prenume = prenume, p.adresa = adresa, p.nr_telefon = nr_telefon, p.email = email, p.iban = iban WHERE p.nr_contract = nr_contract;
	
    IF (old_tip != tip) THEN
        DELETE FROM studenti WHERE studenti.cnp = cnp;
        DELETE FROM profesori WHERE profesori.cnp = cnp;
        
		IF (tip = 2) THEN
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

		IF (tip = 2) THEN 
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

CREATE PROCEDURE admin_read_user(cnp char(13))
BEGIN
	DECLARE tip1, tip2, tip3 INT DEFAULT 0;
    
    SET tip1 = (SELECT 1 FROM admini WHERE admini.cnp = cnp);
    SET tip2 = (SELECT 1 FROM studenti WHERE studenti.cnp = cnp);
    SET tip3 = (SELECT 1 FROM profesori WHERE profesori.cnp = cnp);
    
	IF (tip1 = 1) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Nu ai drepturi pentru a executa aceasta operatie';
	END IF;
    
	IF (tip2 = 1) THEN
		SELECT * FROM persoane p INNER JOIN studenti s ON p.cnp = s.cnp  WHERE p.cnp = cnp;
	ELSEIF (tip3 = 1) THEN
		SELECT * FROM persoane p1 INNER JOIN profesori p2 ON p1.cnp = p2.cnp  WHERE p1.cnp = cnp;
	ELSE
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Utilizatorul nu exita';
	END IF;
END;//

CREATE PROCEDURE admin_delete_user(cnp char(13))
BEGIN
	DECLARE exista, tip1 INT DEFAULT 0;
    DECLARE email VARCHAR(50); 
	SET exista = (SELECT 1 FROM persoane WHERE persoane.cnp = cnp);
    SET email = (SELECT persoane.email FROM persoane where persoane.cnp = cnp);
    
	SET @query1 = CONCAT('DROP USER IF EXISTS "', email, '"@"localhost"');
	PREPARE stmt FROM @query1; EXECUTE stmt; DEALLOCATE PREPARE stmt;
    
	IF (exista = 0) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Tip ultilizator nu exista';
	END IF;

	SET tip1 = (SELECT 1 FROM admini WHERE admini.cnp = cnp);
	IF (tip1 = 1) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Nu ai drepturi pentru a executa aceasta operatie';
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
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Materia nu exista';
	END IF;
	UPDATE materii m set m.nume = nume, m.descriere = descriere, m.procent_curs = procent_curs, m.procent_seminar = procent_seminar, m.procent_laborator = procent_laborator,m.nr_max_studenti = nr_max_studenti, m.recurenta_c = recurenta_c, m.recurenta_s = recurenta_s, m.recurenta_l = recurenta_l WHERE m.id = id;
END//

CREATE PROCEDURE read_materie (id int)
BEGIN
	IF (id = "") THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Materia nu exista';
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