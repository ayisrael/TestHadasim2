http://localhost:8080/CoronaManagment/clientList.jsp

SELECT * FROM corona_db.client;
SELECT * FROM corona_db.recovering;
SELECT * FROM corona_db.manufacturer;
SELECT * FROM corona_db.vaccination;


SELECT * FROM corona_db.client WHERE clientID=?;
SELECT * FROM corona_db.recovering WHERE clientID=?;
SELECT * FROM corona_db.vaccination WHERE clientID=?;
SELECT * FROM corona_db.vaccination ,corona_db.manufacturer WHERE  corona_db.vaccination.manufacturerID = corona_db.manufacturer.manufacturerID AND clientID=?;
SELECT manufacturerID FROM corona_db.manufacturer WHERE  manufacturerID = ?;


UPDATE corona_db.client SET firstName = "Hani", lastName = "israel" WHERE clientID=120878700;
UPDATE corona_db.recovering SET recoveringDate='2021-03-09' WHERE clientID=120878700;


UPDATE corona_db.recovering SET recoveringDate='2021-03-20' WHERE clientID=12345 AND positiveTestDate='2021-03-12';
UPDATE corona_db.client SET  lastName= "choen" ,firstName= "yonatan" ,city="jerusalem" ,street="yaffo" , homeNum = 6,phone=678, mobilePhone=456  WHERE clientID=12001;


ALTER TABLE corona_db.client    
MODIFY dateOfBirth varchar(100); 

ALTER TABLE corona_db.recovering    
MODIFY positiveTestDate varchar(100); 


ALTER TABLE corona_db.recovering    
MODIFY recoveringDate varchar(20); 

ALTER TABLE corona_db.vaccination    
MODIFY dateVaccination varchar(100); 



ALTER TABLE corona_db.client
ADD dateOfBirth date;

ALTER TABLE corona_db.client
MODIFY mobilePhone varchar(20);

ALTER TABLE corona_db.recovering
DROP COLUMN ContactName;

DELETE FROM corona_db.recovering WHERE clientID=?;
DELETE FROM corona_db.vaccination  WHERE clientID=? ;
DELETE FROM corona_db.client  WHERE clientID=? ;


INSERT INTO corona_db.client
VALUES(12345,'choen', 'yonatan','2021-03-24', 'jerusalem','yaffo',12, 0256789, 054678996),
(12344,'choen', 'yonatan', '2021-03-24','jerusalem','Jeremiah',45, 0256456, 052378996),
(12346,'Israeli', 'Adi','2021-03-24', 'jerusalem','enachem',6, 0256842, 054836996),
(12347,'Levi', 'Miri', '2021-03-24', 'jerusalem','Tzfanya',2, 0296257, 054174897),
(12348,'Oren', 'Yakov','2021-03-24', 'jerusalem','Lazar',102, 0285789, 05048947),
(12349,'Salom', 'Dniel','2021-03-24', 'jerusalem','Emoraim',11, 0253689, 054678446);

INSERT INTO corona_db.recovering
VALUES(12345,'2021-03-12','2021-03-24'),
(12344,'2022-05-11','2022-05-16'), 
(12349,'2022-09-10','2021-09-17'),
(12348,'2022-19-10',null);
   
   
INSERT INTO corona_db.manufacturer
VALUES(1212,'Faizer'),
(1111,'Moderna');
     
INSERT INTO corona_db.vaccination
VALUES(12345,'2021-06-12', 1111),
(12344,'2022-08-02', 1212),
(12345,'2022-05-17', 1212);
(12349,'2022-08-02', 1212);



/* create*/ 
CREATE TABLE corona_db.client(
clientID int NOT NULL,
lastName VARCHAR(15) NOT NULL,
firstName VARCHAR(15) NOT NULL,
dateOfBirth VARCHAR(15),
city varchar(20),
street varchar(20),
homeNum int NOT NULL,
phone int,
mobilePhone int,
PRIMARY KEY (clientID)
);  

CREATE TABLE corona_db.manufacturer
(
  manufacturerID int NOT NULL,
  manufacturerName varchar(20),
  PRIMARY KEY (manufacturerID)
);

CREATE TABLE corona_db.recovering(
clientID int NOT NULL,
positiveTestDate date NOT NULL,
recoveringDate date,
PRIMARY KEY (positiveTestDate, clientID),
FOREIGN KEY (clientID) REFERENCES client(clientID));


CREATE TABLE corona_db.vaccination(
clientID int NOT NULL,
dateVaccination date NOT NULL,
manufacturerID int NOT NULL,
PRIMARY KEY (dateVaccination, clientID),
FOREIGN KEY (clientID) REFERENCES client(clientID),
FOREIGN KEY (manufacturerID) REFERENCES manufacturer(manufacturerID)
);













































 