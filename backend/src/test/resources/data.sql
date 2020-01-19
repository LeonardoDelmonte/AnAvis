/* CREATE TABLE `utente` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `ruolo` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_gxvq4mjswnupehxnp35vawmo2` (`email`)
);

CREATE TABLE `sede_avis` (
  `comune` varchar(255) DEFAULT NULL,
  `denominazione` varchar(255) DEFAULT NULL,
  `indirizzo` varchar(255) DEFAULT NULL,
  `provincia` varchar(255) DEFAULT NULL,
  `regione` varchar(255) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKkpgbirivn7kvl3dgawfwbesdp` FOREIGN KEY (`id`) REFERENCES `utente` (`id`)
); 

CREATE TABLE `centro_trasfusione` (
  `comune` varchar(255) DEFAULT NULL,
  `denominazione` varchar(255) DEFAULT NULL,
  `indirizzo` varchar(255) DEFAULT NULL,
  `provincia` varchar(255) DEFAULT NULL,
  `regione` varchar(255) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKgvn4ces0sj5wmtefjnxjgs40p` FOREIGN KEY (`id`) REFERENCES `utente` (`id`)
);

CREATE TABLE `modulo` (
  `id` bigint(20) NOT NULL,
  `fumatore` varchar(255) DEFAULT NULL,
  `gruppo_sanguigno` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `donatore` (
  `abilitazione_donazione` tinyint(4) DEFAULT NULL,
  `cognome` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `id_modulo` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5hrthsd5hrug8y2vypm4gj5wj` (`id_modulo`),
  CONSTRAINT `FK5hrthsd5hrug8y2vypm4gj5wj` FOREIGN KEY (`id_modulo`) REFERENCES `modulo` (`id`),
  CONSTRAINT `FKkdyyc2y471qmkjdrl7iy2wyyh` FOREIGN KEY (`id`) REFERENCES `utente` (`id`)
);

CREATE TABLE `emergenza` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_emergenza` datetime DEFAULT NULL,
  `gruppo_sanguigno` varchar(255) NOT NULL,
  `id_centro_trasfusione` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKi4ekuu9vvmcthk9euuhqht381` (`id_centro_trasfusione`),
  CONSTRAINT `FKi4ekuu9vvmcthk9euuhqht381` FOREIGN KEY (`id_centro_trasfusione`) REFERENCES `centro_trasfusione` (`id`)
);

CREATE TABLE `prenotazione` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `id_donatore` bigint(20) DEFAULT NULL,
  `id_sede_avis` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtibbe6r9cm92nkikjqeccwt8b` (`id_donatore`),
  KEY `FK1fu0q1v0yj56g35dity5pmhho` (`id_sede_avis`),
  CONSTRAINT `FK1fu0q1v0yj56g35dity5pmhho` FOREIGN KEY (`id_sede_avis`) REFERENCES `sede_avis` (`id`),
  CONSTRAINT `FKtibbe6r9cm92nkikjqeccwt8b` FOREIGN KEY (`id_donatore`) REFERENCES `donatore` (`id`)
); */