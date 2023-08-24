create schema if not exists dev;
drop table if exists dev.account;
CREATE TABLE dev.account (
	id int8 NOT NULL,
	accountNumber varchar(255) NULL,
	type varchar(255) NULL,
	activatedDate date NULL,
	CONSTRAINT account_pkey PRIMARY KEY (id)
);

INSERT INTO dev.account (id,type,activatedDate,accountNumber) VALUES(1,'loan','2022-12-20','456-768');
INSERT INTO dev.account (id,type,activatedDate,accountNumber) VALUES(2,'savings','2022-10-20','903-574');
INSERT INTO dev.account (id,type,activatedDate,accountNumber) VALUES(3,'investment','2022-12-11','245-168');
INSERT INTO dev.account (id,type,activatedDate,accountNumber) VALUES(4,'loan','2022-11-15','245-397');