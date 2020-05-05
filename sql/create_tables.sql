DROP TABLE LASKU;
DROP TABLE MAKSAJA;

CREATE TABLE MAKSAJA
(id int auto_increment not null,
nimi varchar(20) not null,
primary key(id)
)engine=InnoDB;

CREATE TABLE LASKU
(id int auto_increment not null,
kuvaus varchar(300) not null,
hinta decimal(7,2) not null,
pvm date not null,
maksaja int not null,
primary key(id),
foreign key(maksaja) references MAKSAJA(id)
)engine=InnoDB;