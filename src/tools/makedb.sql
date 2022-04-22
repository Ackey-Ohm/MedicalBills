CREATE DATABASE medical_bills_db CHARACTER SET utf8mb4
COLLATE utf8mb4_general_ci;

USE medical_bills_db;

CREATE TABLE members (
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(30) NOT NULL,
target INT NOT NULL
);

CREATE TABLE payees (
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(20) UNIQUE NOT NULL,
item_type INT NOT NULL,
relation INT,
description VARCHAR(100)
);

CREATE TABLE receipts (
id INT PRIMARY KEY AUTO_INCREMENT,
member INT NOT NULL,
payee INT NOT NULL,
amount INT NOT NULL,
date_use DATE NOT NULL,
item_type INT NOT NULL,
class1 INT,
class2 INT,
class3 INT,
class4 INT,
description VARCHAR(100)
);

INSERT INTO members VALUES
(NULL,'バカボンのパパ',1),
(NULL,'ママ',1),
(NULL,'バカボン',1),
(NULL,'はじめちゃん',1);

INSERT INTO payees VALUES
(NULL,'JR立川－お茶の水',1,NULL,'順天堂付属利用'),
(NULL,'順天堂大学付属医院',0,1,'眼科を利用'),
(NULL,'JR立川－新小久保',1,NULL,'新大久保内科利用'),
(NULL,'新小久保内科',0,3,'掛かりつけ'),
(NULL,'JR立川－高田馬場',1,NULL,'高田馬場歯科利用'),
(NULL,'高田馬場歯科',0,5,NULL),
(NULL,'大久保薬局',0,NULL,NULL),
(NULL,'馬場駅前ドラッグ',0,NULL,'高田馬場歯科近く');

INSERT INTO receipts VALUES
(NULL,1,2,2500, '2022-05-01',0,1,1,0,0,'定期健診'),
(NULL,1,1,1900, '2022-05-01',1,0,0,0,0,'順天堂付属医院まで、1人');
