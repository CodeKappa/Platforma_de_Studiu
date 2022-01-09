#procedures used by students
use gestiune_studenti;

DELIMITER //

#Proceduri legate de materie-------------------------------------------------------------------------------------------------------------------------------------------
CREATE PROCEDURE Materii_propri(cnp char(13))
BEGIN
	SELECT m.* FROM materii m INNER JOIN materii_studenti ms ON ms.id_materie = m.id WHERE ms.cnp_student = cnp GROUP BY m.id;
END; //

CREATE PROCEDURE Inscriere_materie(materie varchar(50), cnp_student char(13))
BEGIN 
	DECLARE id_select int;
   
	DROP TABLE IF EXISTS lista_inscrieri;
	CREATE TEMPORARY TABLE lista_inscrieri
    SELECT m.id, m.nume, m.nr_max_studenti, COUNT(*) AS magnitude from materii m LEFT JOIN materii_studenti ms on m.id = ms.id_materie
    WHERE m.nume = materie
    GROUP BY m.id
    ORDER BY magnitude ASC;
    
    DROP TABLE IF EXISTS lista1;
	CREATE TEMPORARY TABLE lista1
    SELECT * FROM lista_inscrieri WHERE nr_max_studenti > magnitude ORDER BY magnitude
    LIMIT 1;
    
    SELECT id INTO id_select from lista1;
    INSERT INTO materii_studenti VALUES (id_select, cnp_student, null, null);
END; //

CREATE PROCEDURE Renuntare_materie(id_materie int, cnp_student char(13))
BEGIN
	DELETE FROM materii_studenti ms WHERE ms.id_materie = id_materie AND ms.cnp_student = cnp_student;
END; //

CREATE PROCEDURE Vizualizare_calendar(cnp_student char(13))
BEGIN
	SELECT c.id, m.nume AS nume_materie, c.categorie, p.nume, p.prenume, c.data_programarii, c.durata FROM calendar c
    JOIN materii m ON c.id_materie = m.id
    JOIN persoane p ON p.cnp = c.cnp_profesor
    JOIN calendar_studenti cs ON c.id = cs.id_calendar
    WHERE cs.cnp_student = cnp_student
    GROUP BY c.id
    ORDER BY c.data_programarii;
END; //

#-------------------------------------------------------------------------------------------------------------------------------------------


CREATE PROCEDURE Inscriere_calendar(id_calendar int, cnp_student char(13))
BEGIN
	DECLARE nr_elevi, nr_elevi_max, exista_suprapuneri int default 0;
    DECLARE data_programarii, data_terminarii datetime;
    CREATE TEMPORARY TABLE date_programari
    SELECT c.data_programarii, addtime(c.data_programarii, c.durata) as data_finalizarii FROM calendar c WHERE c.id = id_calendar;
	SELECT COUNT(*) INTO nr_elevi FROM calendar_studenti cs WHERE cs.id_calendar = id_calendar;
    SELECT c.nr_maxim INTO nr_elevi_max FROM calendar c WHERE c.id = id_calendar;
    IF (nr_elevi < nr_elevi_max) THEN
		CREATE TEMPORARY TABLE programari_existente
        SELECT c.data_programarii, addtime(c.data_programarii, c.durata) as data_finalizarii from calendar c 
		JOIN calendar_studenti cs ON c.id = cs.id_calendar
		WHERE cs.cnp_student = cnp_student;
        SELECT 1 INTO exista_suprapuneri FROM programari_existente pe JOIN date_programari dp ON (dp.data_programarii < pe.data_finalizarii AND pe.data_programarii < dp.data_finalizarii);
        DROP TEMPORARY TABLE programari_existente;
		IF(exista_suprapuneri = 0) THEN
			INSERT INTO calendar_studenti VALUES (cnp_student, id_calendar);
        ELSE
			DROP TEMPORARY TABLE date_programari;
			SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Intersectie date programari!';
        END IF;
    ELSE
		DROP TEMPORARY TABLE date_programari;
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Numar maxim participanti atins!';
    END IF;
    DROP TEMPORARY TABLE date_programari;
END; //

CREATE PROCEDURE Vizualizare_note(cnp_student char(13))
BEGIN
	SELECT x.nume, m.categorie, m.nota FROM materii_studenti m JOIN materii x ON m.id_materie=x.id WHERE cnp_student = m.cnp_student;
END; //

CREATE PROCEDURE Vizualizare_grupuri()
BEGIN
	SELECT gs.id, m.nume FROM grup_studiu gs INNER JOIN materii m ON gs.id_materie = m.id; 
END; //

CREATE PROCEDURE Inscriere_grup(id_grup int, cnp_student char(13))
BEGIN
		INSERT INTO grup_studiu_studenti VALUES (id_grup, cnp_student);
END;//

CREATE PROCEDURE Parasire_grup(id_grup int, cnp_student char(13))
BEGIN
	DELETE FROM grup_studiu_studenti gss WHERE gss.id_grup = id_grup AND gss.cnp_student = cnp_student;
END;//

CREATE PROCEDURE Vizualizare_membrii_grup(id_grup int, cnp_student char(13))
BEGIN
	SELECT p.nume, p.prenume FROM grup_studiu_studenti gss JOIN persoane p on p.cnp = gss.cnp_student where gss.id_grup = id_grup;
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

CREATE PROCEDURE Sugestii_grupuri(cnp_student char(13))
BEGIN
	SELECT m.nume, m.id FROM materii m LEFT JOIN grup_studiu g on m.id = g.id_materie LEFT JOIN materii_studenti ms on m.id = ms.id_materie
    WHERE ms.cnp_student = cnp_student AND g.id NOT IN (SELECT id_grup FROM grup_studiu_studenti gss WHERE gss.cnp_student = cnp_student)
    GROUP BY m.id;
END; //

CREATE PROCEDURE Vizualizare_activitati_grupuri()
BEGIN
	SELECT * FROM grup_studiu_activitati; 
END; //

CREATE PROCEDURE Inscriere_activitate_grup(id_grup int, cnp_student char(13))
BEGIN
	INSERT INTO grup_studiu_activitati_studenti VALUES (id_grup, cnp_student);
END; //

CREATE PROCEDURE Mesaje_vizualizare(id int)
BEGIN
	SELECT p.nume, p.prenume, g.mesaj, g.data_ora_trimiterii FROM grup_studiu_mesaje g JOIN persoane p WHERE g.cnp_student=p.cnp AND id_grup=id ORDER BY g.data_ora_trimiterii;
END; //

CREATE PROCEDURE Mesaje_scriere(mesaj varchar(250), id_grup int, cnp_student char(13))
BEGIN
	INSERT INTO grup_studiu_mesaje(id_grup, cnp_student, mesaj, data_ora_trimiterii) VALUES (id_grup, cnp_student, mesaj, current_timestamp());
END; //

CREATE PROCEDURE Descarcare_activitati2(cnp_student char(13))
BEGIN
	SELECT * FROM grup_studiu_activitati gsa JOIN grup_studiu gs ON gsa.id_grup=gs.id JOIN grup_studiu_studenti gss ON gss.id_grup=gs.id WHERE gss.cnp_student=cnp_student AND CURRENT_TIMESTAMP()<=gsa.data_programarii
	ORDER by data_programarii
	INTO OUTFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\activitati.csv' 
	FIELDS TERMINATED BY ','  
	LINES TERMINATED BY '\r\n';
END; //

CREATE PROCEDURE Generare_orar(cnp_student char(13))
BEGIN
	DECLARE nr_activitati, i, j int default 0;
    
    SELECT COUNT(DISTINCT c.id_materie, c.categorie)
    INTO nr_activitati
	FROM calendar c 
	JOIN materii m ON c.id_materie = m.id
	JOIN materii_studenti ms ON m.id = ms.id_materie
	WHERE ms.cnp_student = "10003" AND adddate(current_timestamp(), 7) > data_programarii;
	
    CREATE TEMPORARY TABLE lista_ore
    SELECT c.id, c.id_materie, c.data_programarii, addtime(c.data_programarii, c.durata) as data_terminarii, c.categorie
    FROM calendar c 
    JOIN materii m ON c.id_materie = m.id
    JOIN materii_studenti ms ON m.id = ms.id_materie
    WHERE ms.cnp_student = cnp_student AND adddate(current_timestamp(), 7) > data_programarii
    GROUP BY id;
    IF nr_activitati = 1 THEN
		INSERT INTO calendar_studenti VALUES (cnp_student, (SELECT id FROM lista_ore LIMIT 1));
    ELSEIF nr_activitati > 1 THEN
		CREATE TEMPORARY TABLE self
        SELECT id AS id0, id_materie AS id_materie0, data_programarii AS data_programarii0, data_terminarii AS data_terminarii0, categorie AS categorie0 FROM lista_ore;
        WHILE i+1 < nr_activitati DO
			CREATE TEMPORARY TABLE lo
			SELECT * FROM self;
            CREATE TEMPORARY TABLE lo2
			SELECT * FROM lista_ore;
			DROP TEMPORARY TABLE self;
            SET i = i + 1;
            SET @id = CONCAT("id", i);
            SET @id_materie = CONCAT("id_materie", i);
            SET @data_programarii = CONCAT("data_programarii", i);
            SET @data_terminarii = CONCAT("data_terminarii", i);
            SET @categorie = CONCAT("categorie", i);
            SET @s = CONCAT("CREATE TEMPORARY TABLE self SELECT lo.*, lo2.id AS ", @id, ", lo2.id_materie AS ", @id_materie, ", lo2.data_programarii AS ", @data_programarii, ", lo2.data_terminarii AS ", @data_terminarii, ", lo2.categorie AS ", @categorie, " FROM lo JOIN lo2");
            PREPARE stmt FROM @s;
            EXECUTE stmt;
            DROP TEMPORARY TABLE lo;
            DROP TEMPORARY TABLE lo2;
		END WHILE;
        SET i = 0;
        SET SQL_SAFE_UPDATES = 0;
        WHILE i < nr_activitati - 1 DO
			SET j = i + 1;
            WHILE j < nr_activitati DO
				SET @idi = CONCAT("id", i);
                SET @idj = CONCAT("id", j);
                SET @data_programariii = CONCAT("data_programarii", i);
                SET @data_programariij = CONCAT("data_programarii", j);
                SET @data_terminariii = CONCAT("data_terminarii", i);
				SET @data_terminariij = CONCAT("data_terminarii", j);
                SET @id_materiei = CONCAT("id_materie", i);
                SET @id_materiej = CONCAT("id_materie", j);
                SET @categoriei = CONCAT("categorie", i);
                SET @categoriej = CONCAT("categorie", j);
                SET @s = CONCAT("DELETE FROM self WHERE 
								(", @idi, " = ", @idj, ") OR
								(", @data_programariii, " < ", @data_terminariij, " AND ", @data_programariij, " < ", @data_terminariii, ") OR
								(", @id_materiei, " = ", @id_materiej, " AND ", @categoriei, " = ", @categoriej, ")");
				PREPARE stmt FROM @s;
                EXECUTE stmt;
                SET j = j + 1;
			END WHILE;
            SET i = i + 1;
        END WHILE;
        SET SQL_SAFE_UPDATES = 1;
        SET j = 0;
        SELECT COUNT(*) INTO j FROM self;
        IF j != 0 THEN 
			SET i = 0;
			DELETE FROM calendar_studenti cs WHERE cs.cnp_student = cnp_student; 
			WHILE i < nr_activitati DO
				SET @idi = CONCAT("id", i);
				SET @s = CONCAT("SELECT ", @idi, " INTO @temp FROM self LIMIT 1");
				PREPARE stmt FROM @s;
				EXECUTE stmt;
				INSERT INTO calendar_studenti VALUES (cnp_student, @temp);
				SET i = i + 1;
			END WHILE;
		ELSE
			DROP TEMPORARY TABLE self;
			DROP TEMPORARY TABLE lista_ore;
			SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Nu s-a gasit o varianta de orar!';
		END IF;
        DROP TEMPORARY TABLE self;
	END IF;
    DROP TEMPORARY TABLE lista_ore;
END; //

CREATE PROCEDURE Renuntare_calendar_activitate(cnp_student char(13), id_calendar int)
BEGIN
	DELETE FROM calendar_studenti cs WHERE cs.cnp_student = cnp_student AND cs.id_calendar = id_calendar;
END; //

CREATE PROCEDURE Renuntare_calendar(cnp_student char(13))
BEGIN
	DELETE FROM calendar_studenti cs WHERE cs.cnp_student = cnp_student;
END; //

CREATE PROCEDURE Vizualizare_ore_disponibile(cnp_student char(13))
BEGIN
	SELECT c.id, m.nume AS nume_materie, c.categorie, p.nume, p.prenume, c.data_programarii, addtime(c.data_programarii, c.durata) as data_terminarii FROM calendar c
    JOIN materii m ON c.id_materie = m.id
    JOIN materii_studenti ms ON m.id = ms.id_materie
    JOIN persoane p ON p.cnp = c.cnp_profesor
    WHERE ms.cnp_student = cnp_student AND adddate(current_timestamp(), 7) > data_programarii
    GROUP BY id;
END; //

CREATE PROCEDURE Generare_ferestre(cnp_student char(13))
BEGIN
	DECLARE nr_activitati int;
    
    SELECT COUNT(*) INTO nr_activitati FROM calendar_studenti cs WHERE cs.cnp_student = cnp_student; 
    
	CREATE TEMPORARY TABLE lista_ore 
    SELECT ROW_NUMBER() OVER(ORDER BY data_programarii) idx, c.data_programarii, addtime(c.data_programarii, c.durata) AS data_terminarii FROM calendar_studenti cs
    JOIN calendar c ON cs.id_calendar = c.id
    WHERE cs.cnp_student = cnp_student
    ORDER BY data_programarii;
    
    INSERT INTO lista_ore VALUES (nr_activitati+1, adddate(current_timestamp(), 7), adddate(current_timestamp(), 7));
    
    CREATE TEMPORARY TABLE lista_ore2
    SELECT * FROM lista_ore;
    
    SELECT lo1.data_terminarii, lo2.data_programarii FROM lista_ore lo1 JOIN lista_ore2 lo2 ON lo1.idx = lo2.idx-1;
    DROP TEMPORARY TABLE lista_ore;
    DROP TEMPORARY TABLE lista_ore2;
END; //