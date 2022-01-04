#procedures used by students
use gestiune_studenti;

DELIMITER //

#Proceduri legate de materie-------------------------------------------------------------------------------------------------------------------------------------------
CREATE PROCEDURE Materii_propri(cnp char(13))
BEGIN
	SELECT m.* FROM materii m INNER JOIN materii_studenti ms ON ms.id_materie = m.id WHERE ms.cnp_student = cnp GROUP BY m.id;
END; //

DELIMITER ///
DROP PROCEDURE IF EXISTS Inscriere_materie;
#call Inscriere_materie("bd", 10003);
CREATE PROCEDURE Inscriere_materie(materie varchar(50), cnp_student char(13))
BEGIN
	DECLARE id_selectat int;
    
	DROP TABLE IF EXISTS lista_inscrieri;
	CREATE TEMPORARY TABLE lista_inscrieri
	SELECT id_materie, COUNT(*) AS magnitude 
	FROM materii_studenti
	#WHERE id_materie in (select id_materie from materii where nume = "bd")
	GROUP BY id_materie 
	ORDER BY magnitude ASC;
    
    SELECT * from lista_inscrieri_curata;
    
    DROP TABLE IF EXISTS lista_inscrieri_curata;
    CREATE TEMPORARY TABLE lista_inscrieri_curata
    SELECT li.id_materie, li.magnitude INTO id_selectat FROM lista_inscrieri li
    JOIN materii m ON li.id_materie = m.id 
    WHERE m.nr_max_studenti >= li.magnitude
    ORDER BY magnitude ASC;
    #LIMIT 1;

	SELECT * from lista_inscrieri_curata;
	#SELECT id_materie INTO id_selectat FROM lista_inscrieri_curata;
    #INSERT INTO materii_studenti VALUES(id_selectat, cnp_student, null, null);
END;///

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

#-------------------------------------------------------------------------------------------------------------------------------------------

/*
CREATE PROCEDURE Inscriere_calendar(id_materie int, cnp_student char(13))
BEGIN
	IF(((SELECT COUNT(*) FROM calendar_studenti cs
    JOIN calendar c ON cs.id_calendar = c.id
    WHERE c.id_materie = id_materie) < (SELECT nr_maxim FROM calendar c WHERE c.id_materie = id_materie)) AND current_timestamp() < (SELECT data_programarii)) THEN
		
END; //
*/

#Vizualizare note-------------------------------------------------------------------------------------------------------------------------------------------
CREATE PROCEDURE Vizualizare_note(cnp_student char(13))
BEGIN
	SELECT x.nume, m.categorie, m.nota FROM materii_studenti m JOIN materii x ON m.id_materie=x.id WHERE cnp_student = m.cnp_student;
END; //
#-------------------------------------------------------------------------------------------------------------------------------------------

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