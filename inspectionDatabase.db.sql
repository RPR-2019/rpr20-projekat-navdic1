BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS `user` (
	`user_id`	INTEGER NOT NULL,
	`username`	TEXT,
	`password`	INTEGER,
	PRIMARY KEY(`user_id` AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS `owner` (
	`owner_id`	INTEGER NOT NULL,
	`name`	TEXT,
	`surname`	TEXT,
	`serial_number`	TEXT,
	PRIMARY KEY(`owner_id` AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS `vehicle` (
	`vehicle_id`	INTEGER NOT NULL,
	`vehicle_category`	TEXT,
	`brand`	TEXT,
	`motor_number`	TEXT,
	`registration_plate`	INTEGER,
	`fuel`	TEXT,
	`year`	INTEGER,
	PRIMARY KEY(`vehicle_id` AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS `inspection` (
	`inspection_id`	INTEGER NOT NULL,
	`owner`	INTEGER,
	`vehicle`	INTEGER,
	`type`	TEXT,
	`evaluation`	TEXT,
	PRIMARY KEY(`inspection_id` AUTOINCREMENT),
	FOREIGN KEY(`owner`) REFERENCES `owner`(`owner_id`),
	FOREIGN KEY(`vehicle`) REFERENCES `vehicle`(`vehicle_id`)
);

INSERT INTO `user` VALUES (1,'user','user');


INSERT INTO `owner` VALUES (1,'Ime1','Prezime1','Licna1');
INSERT INTO `owner` VALUES (2,'Ime2','Prezime2','Licna2');

INSERT INTO `vehicle` VALUES (1,'TESKA','auto1','brojMotora1','tablice1','BENSINE',2010);
INSERT INTO `vehicle` VALUES (2,'LAKA','auto2','brojMotora2','tablice2','DIESEL',2015);
INSERT INTO `inspection` VALUES (1,1,1,'REGULAR','NO_DATA');
INSERT INTO `inspection` VALUES (2,2,2,'PREVENTIVE','NO_DATA');

COMMIT;
