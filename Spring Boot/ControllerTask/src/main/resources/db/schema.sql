SET FOREIGN_KEY_CHECKS =0;
DROP TABLE if exists User;
set foreign_key_checks =1;

CREATE table User(username varchar(255), age int, id int PRIMARY KEY );

INSERT INTO User (age, id, username) VALUES (21, 101, "Siva"),(20, 102, "Sai"),(20, 109, "Sai"),(17, 103, "Sivasai"),(16, 104, "SivaP"),(22, 105, "SaiP");