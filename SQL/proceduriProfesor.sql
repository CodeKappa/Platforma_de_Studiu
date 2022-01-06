USE gestiune_studenti;

DELIMITER //
CREATE PROCEDURE Vizualizare_date_personale_profesor(cnp char(13))
BEGIN
	SELECT * FROM persoane p JOIN profesori pp ON p.cnp = pp.cnp WHERE p.cnp = cnp;
END;

CREATE PROCEDURE Vizualizare_activitati_studiu(cnp_profesor char(13))
BEGIN
	SELECT gsa.id, gsa.id_grup, gsa.cnp_profesor, gsa.nume, gsa.descriere, gsa.data_programarii, gsa.durata, gsa.data_expirarii, gsa.numar_minim FROM persoane p 
    JOIN materii_profesor mp ON p.cnp = mp.cnp_profesor
    JOIN materii m ON mp.id_materie = m.id
    JOIN grup_studiu gs ON m.id = gs.id_materie
    JOIN grup_studiu_activitati gsa ON gs.id = gsa.id_grup
    WHERE current_timestamp() < gsa.data_programarii;
END; //

CREATE PROCEDURE Inscriere_activitati_studiu(cnp_profesor char(13), id_gsa int)
BEGIN
	UPDATE grup_studiu_activitati gsa SET gsa.cnp_profesor = cnp_profesor WHERE gsa.id = id_gsa;
END; //

CREATE PROCEDURE Programare_calendar(cnp_profesor char(13), data_inceput varchar(50), data_final varchar(50), durata varchar(50), id_materie int, categorie enum('Curs','Seminar','Laborator'), nr_maxim int)
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
    
    SET current_datetime = CONVERT(data_inceput, datetime);
    WHILE current_datetime <= CONVERT(data_final,datetime) DO
		INSERT INTO calendar VALUES (null, CONVERT(current_datetime, datetime), CONVERT(durata,time), id_materie, cnp_profesor, categorie, nr_maxim);
        SET current_datetime = ADDDATE(current_datetime, recurenta);
	END WHILE;
END; //

CREATE PROCEDURE Vizualizare_ponderi(cnp_profesor char(13))
BEGIN
	SELECT nume, id, procent_curs, procent_seminar, procent_laborator 
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

CREATE PROCEDURE Vizualizare_studenti_inscrisi(cnp_profesor char(13))
BEGIN
	SELECT ms.id_materie, m.nume AS nume_materie, p.nume, p.prenume, p.cnp, s.an_studiu, p.nr_telefon, p.email FROM persoane p
    JOIN studenti s ON p.cnp = s.cnp
    JOIN materii_studenti ms ON p.cnp = ms.cnp_student
    JOIN materii m ON ms.id_materie = m.id
    WHERE ms.id_materie IN 
    (SELECT id_materie FROM materii_profesor mp WHERE mp.cnp_profesor = cnp_profesor)
    AND ms.nota IS NULL AND ms.categorie IS NULL
    ORDER BY m.nume, p.nume, p.prenume;
END; //

CREATE PROCEDURE Vizualizare_studenti_note(cnp_profesor char(13))
BEGIN
	SELECT ms.id_materie, m.nume AS nume_materie, p.nume, p.prenume, p.cnp, s.an_studiu, p.nr_telefon, p.email, ms.categorie, ms.nota FROM persoane p
    JOIN studenti s ON p.cnp = s.cnp
    JOIN materii_studenti ms ON p.cnp = ms.cnp_student
    JOIN materii m ON ms.id_materie = m.id
    WHERE ms.id_materie IN 
    (SELECT id_materie FROM materii_profesor mp WHERE mp.cnp_profesor = cnp_profesor)
    AND ms.nota IS NOT NULL AND ms.categorie IS NOT NULL
    ORDER BY m.nume, p.nume, p.prenume;
END; //

CREATE PROCEDURE Descarcare_studenti_materii(cnp_profesor char(13))
BEGIN
	SELECT ms.id_materie, m.nume AS nume_materie, p.nume, p.prenume, p.cnp, s.an_studiu, p.nr_telefon, p.email, ms.categorie, ms.nota FROM persoane p
    JOIN studenti s ON p.cnp = s.cnp
    JOIN materii_studenti ms ON p.cnp = ms.cnp_student
    JOIN materii m ON ms.id_materie = m.id
    WHERE ms.id_materie IN 
    (SELECT id_materie FROM materii_profesor mp WHERE mp.cnp_profesor = cnp_profesor)
    AND ms.nota IS NOT NULL AND ms.categorie IS NOT NULL
    ORDER BY m.nume, p.nume, p.prenume
    INTO OUTFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\catalog.csv' 
	FIELDS TERMINATED BY ','  
	LINES TERMINATED BY '\r\n';
END; //

CREATE PROCEDURE Vizualizare_materii_profesor(cnp_profesor char(13))
BEGIN
	SELECT m.id, m.nume, m.descriere, m.nr_max_studenti FROM materii m
    JOIN materii_profesor mp ON m.id = mp.id_materie
    WHERE mp.cnp_profesor = cnp_profesor;
END; //