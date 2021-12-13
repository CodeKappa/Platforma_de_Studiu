DROP DATABASE IF EXISTS gestiune_studenti;
CREATE DATABASE gestiune_studenti;
USE gestiune_studenti;

CREATE TABLE persoane
(cnp char(13) not null unique primary key,
nume varchar(50) not null,
prenume varchar(50) not null,
adresa varchar(200) not null,
nr_telefon char(12) not null,
email varchar(50) not null,
iban varchar(30) not null,
nr_contract int unique not null,
isAdmin bool not null);

CREATE TABLE studenti
(cnp char(13) not null unique primary key,
an_studiu int not null,
nr_ore int not null,
FOREIGN KEY (cnp) REFERENCES persoane(cnp));

CREATE TABLE profesori
(cnp char(13) not null unique primary key,
nr_ore_min int not null,
nr_ore_max int not null,
departament varchar(50) not null,
FOREIGN KEY (cnp) REFERENCES persoane(cnp));

CREATE TABLE materii
(id int not null unique auto_increment primary key,
nume varchar(50) not null,
descriere varchar(250),
procent_curs int not null,
procent_seminar int not null,
procent_laborator int not null,
nr_max_studenti int not null,
recurenta int not null);

CREATE TABLE materii_studenti
(id_materie int not null,
cnp_student char(13) not null,
categorie ENUM('Curs', 'Seminar', 'Laborator'),
nota DECIMAL(4,2),
FOREIGN KEY (cnp_student) REFERENCES persoane(cnp),
FOREIGN KEY (id_materie) REFERENCES materii(id));

CREATE TABLE materii_profesor
(id_materie int not null,
cnp_profesor char(13) not null,
FOREIGN KEY (cnp_profesor) REFERENCES persoane(cnp),
FOREIGN KEY (id_materie) REFERENCES materii(id));

CREATE TABLE calendar
(id int unique auto_increment primary key,
data_programarii datetime,
durata time,
id_materie int,
categorie ENUM('Curs', 'Seminar', 'Laborator'),
nr_maxim int,
FOREIGN KEY (id_materie) REFERENCES materii(id));

CREATE TABLE calendar_studenti
(cnp_student char(13),
id_calendar int,
FOREIGN KEY (cnp_student) REFERENCES persoane(cnp),
FOREIGN KEY (id_calendar) REFERENCES calendar(id));

CREATE TABLE grup_studiu
(id int unique auto_increment primary key,
id_materie int,
FOREIGN KEY (id_materie) REFERENCES materii(id));

CREATE TABLE grup_studiu_studenti
(id_grup int,
cnp_student char(13),
FOREIGN KEY (id_grup) REFERENCES grup_studiu(id),
FOREIGN KEY (cnp_student) REFERENCES persoane(cnp));

CREATE TABLE grup_studiu_mesaje
(id_grup int,
cnp_student char(13),
mesaj varchar(250),
FOREIGN KEY (id_grup) REFERENCES grup_studiu(id),
FOREIGN KEY (cnp_student) REFERENCES persoane(cnp));

CREATE TABLE grup_studiu_activitati
(id_grup int,
cnp_profesor char(13),
nume varchar(50),
descriere varchar(250),
data_programarii datetime,
durata time,
data_expirarii datetime,
numar_minim int,
FOREIGN KEY (id_grup) REFERENCES grup_studiu(id),
FOREIGN KEY (cnp_profesor) REFERENCES persoane(cnp));

DELIMITER //

CREATE TRIGGER materii_insert_verificare_procentaje BEFORE INSERT ON materii FOR EACH ROW
BEGIN
	IF NEW.procent_curs + NEW.procent_seminar + NEW.procent_laborator != 100
    THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Nu se poate adauga inregistrarea in materii: suma ponderilor nu este 100';
	END IF;
END;//

CREATE TRIGGER materii_update_verificare_procentaje BEFORE UPDATE ON materii FOR EACH ROW
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


CREATE PROCEDURE Cautare_materie(materie varchar(50))
BEGIN
select * from materii where nume=materie;

END; //

CREATE PROCEDURE Inscriere(id_materie int, cnp_student char(13))
BEGIN

select id into @x  from materii where id=id_curs;

if(x!=null)
then 
INSERT INTO materii_studenti(id_materie, cnp_student) VALUES(id_materie, cnp_student);
else 
signal sqlstate '45000' set message_text= 'Materia nu exista';
END IF;

END;//

CREATE PROCEDURE Vizualizare_note(cnp_student char(13))
BEGIN

select x.nume, m.nota from materii_studenti m join materii x on m.id_materie=x.id;
END;




