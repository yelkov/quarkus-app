DROP DATABASE IF EXISTS quarkusapp;
CREATE DATABASE IF NOT EXISTS quarkusapp;
USE quarkusapp;

DROP TABLE IF EXISTS USUARIAS;
CREATE TABLE IF NOT EXISTS USUARIAS
(
    NOMBRE_USUARIA VARCHAR(50) NOT NULL,
    DESTREZA INT NOT NULL,
    /****************************************/
    PRIMARY KEY(NOMBRE_USUARIA)
)ENGINE INNODB;

DROP TABLE IF EXISTS ITEMS;
CREATE TABLE IF NOT EXISTS ITEMS
(
    NOMBRE_ITEM VARCHAR(50) NOT NULL,
    CALIDAD INT,
    TIPO VARCHAR(50),
    /****************************************/
    PRIMARY KEY(NOMBRE_ITEM)
)ENGINE INNODB;

DROP TABLE IF EXISTS ORDENES;
CREATE TABLE IF NOT EXISTS ORDENES
(
	ID_ORDEN INT AUTO_INCREMENT NOT NULL,
    USUARIA VARCHAR(50) NOT NULL,
    ITEM VARCHAR(50) NOT NULL,
    /****************************************/
	PRIMARY KEY(ID_ORDEN),
    FOREIGN KEY(USUARIA) REFERENCES USUARIAS(NOMBRE_USUARIA)
		ON DELETE CASCADE
        ON UPDATE CASCADE,
	INDEX FK_USUARIA(USUARIA),
	FOREIGN KEY(ITEM) REFERENCES ITEMS(NOMBRE_ITEM)
		ON DELETE CASCADE
        ON UPDATE CASCADE,
	INDEX FK_ITEM(ITEM)
)ENGINE INNODB;

INSERT INTO USUARIAS
(NOMBRE_USUARIA,DESTREZA)
VALUES
(
	"Doobey",15
),
(
	"Hermione",100
);

INSERT INTO ITEMS
(NOMBRE_ITEM,CALIDAD,TIPO)
VALUES
(
	"+5 Dexterity Vest",20,"NormalItem"
),
(
	"Elixir of the Mongoose",7,"NormalItem"
),
(
	"AgedBrie",10,"NormalItem"
);
INSERT INTO ORDENES
(ID_ORDEN,USUARIA,ITEM)
VALUES
(
	100,"Doobey","Elixir of the Mongoose"
),
(
	200,"Hermione","AgedBrie"
);
