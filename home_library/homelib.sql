BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "authors" (
	"szid"	INTEGER UNIQUE,
	"name"	TEXT NOT NULL,
	"nationality"	TEXT,
	PRIMARY KEY("szid" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "books" (
	"kid"	INTEGER NOT NULL UNIQUE,
	"szid"	INTEGER NOT NULL,
	"title"	TEXT NOT NULL,
	"self"	TEXT NOT NULL,
	"genre"	TEXT,
	PRIMARY KEY("kid")
);
CREATE TABLE IF NOT EXISTS "selfs" (
	"fid"	INTEGER,
	"name"	TEXT NOT NULL,
	PRIMARY KEY("fid" AUTOINCREMENT)
);
INSERT INTO "authors" ("szid","name","nationality") VALUES (1,'Ernest Hemingway',''),
 (2,'Herman Wouk',''),
 (3,'Ken Follett',''),
 (4,'Jane Austen',''),
 (5,'Agatha Christie',''),
 (6,'Charles Dickens','');
INSERT INTO "books" ("kid","szid","title","self","genre") VALUES (1,1,'The old man and the sea','A1',''),
 (2,1,'The sun also rises','A1',''),
 (3,2,'The winds of war','A1',''),
 (4,2,'The Caine mutiny','A1',''),
 (5,3,'World without end','A1',''),
 (6,3,'Whiteout','A1',''),
 (7,4,'Pride and prejudice','A1',''),
 (8,4,'Emma','A1',''),
 (9,5,'Murder on the Orient Express','A1',''),
 (10,5,'The unexpected guest','A1',''),
 (11,6,'Bleak house','A1',''),
 (12,6,'Great expectations','A1','');
INSERT INTO "selfs" ("fid","name") VALUES (1,'A1'),
 (2,'A2'),
 (3,'A3'),
 (4,'A4'),
 (5,'A5'),
 (6,'B1'),
 (7,'B2'),
 (8,'B3'),
 (9,'B4'),
 (10,'B5'),
 (11,'C3'),
 (12,'C1'),
 (13,'C2'),
 (14,'D1'),
 (15,'D2'),
 (16,'D3'),
 (17,'D4'),
 (18,'D5'),
 (19,'D6'),
 (20,'E1'),
 (21,'E2'),
 (22,'E3'),
 (23,'E4'),
 (24,'E5'),
 (25,'E6'),
 (26,'F3'),
 (27,'F1'),
 (28,'F2'),
 (29,'G1'),
 (30,'G2'),
 (31,'G3'),
 (32,'G4'),
 (33,'G5'),
 (34,'G6'),
 (35,'H'),
 (37,'I');
COMMIT;
