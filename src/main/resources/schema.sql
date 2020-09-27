CREATE TABLE valute
(
 id SERIAL  NOT NULL,
 charCode varchar(100) DEFAULT NULL,
 name varchar(100) NOT NULL,
 value varchar(40) NOT NULL ,
 date date DEFAULT NULL,
 PRIMARY KEY (id)
);
CREATE SEQUENCE my_seq_gen START 1;