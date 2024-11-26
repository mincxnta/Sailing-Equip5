--Creació BD i usuari amb privilegis (DCL)

CREATE DATABASE sailing;
CREATE USER 'aaron'@'localhost' IDENTIFIED BY 'aaron';

--Creació objectes (DDL)

CREATE TABLE `usuari` (
  `id_usuari` varchar(50) NOT NULL,
  `contrasenya` varchar(100) NOT NULL,
  `nom` varchar(30) DEFAULT NULL,
  `cognoms` varchar(60) DEFAULT NULL,
  `telefon` char(9) DEFAULT NULL,
  `rol` enum('admin','client') NOT NULL DEFAULT 'client',
  CONSTRAINT `pk_usuari` PRIMARY KEY (`id_usuari`)
);

CREATE TABLE `viatge` (
  `id_viatge` varchar(4) NOT NULL,
  `titol` varchar(50) NOT NULL,
  `descripcio` varchar(600) NOT NULL,
  `preu` decimal(5,2) UNSIGNED NOT NULL,
  `places` int(3) UNSIGNED NOT NULL,
  `durada` int(3) UNSIGNED NOT NULL,
  `categoria` enum('privat','grup') NOT NULL DEFAULT 'grup',
  CONSTRAINT `pk_viatge` PRIMARY KEY (`id_viatge`)
);


CREATE TABLE `hores` (
  `viatge` varchar(4) NOT NULL,
  `hora` time NOT NULL,
   CONSTRAINT `pk_hores` PRIMARY KEY (`hora`, `viatge`),
   CONSTRAINT `fk_viatge_hores` FOREIGN KEY (`viatge`) REFERENCES `viatge`(`id_viatge`)
);

CREATE TABLE `reserva` (
  `id_reserva` varchar(9) NOT NULL,
  `usuari` varchar(50) NOT NULL,
  `viatge` varchar(4) NOT NULL,
  `hora` time NOT NULL,
  `data` date NOT NULL,
  `assistents` int(3) UNSIGNED NOT NULL,
  CONSTRAINT `pk_reserva` PRIMARY KEY (`id_reserva`),
  CONSTRAINT `fk_usuari_reserva` FOREIGN KEY (`usuari`) REFERENCES `usuari`(`id_usuari`),
  CONSTRAINT `fk_viatge_reserva` FOREIGN KEY (`viatge`) REFERENCES `viatge`(`id_viatge`)
);

CREATE TABLE `seguiment` (
  `id_accio` int(9) NOT NULL,
  `tipus` enum('reservada','replanificada','cancelada','finalitzada') DEFAULT 'reservada',
  `data` datetime NOT NULL,
  `reserva` varchar(9) NOT NULL,
  `usuari` varchar(50) NOT NULL,
  `comentaris` varchar(200) NOT NULL,
  `nova_data` date NOT NULL,
  `nova_hora` time NOT NULL,
   CONSTRAINT `pk_seguiment` PRIMARY KEY (`id_accio`),
   CONSTRAINT `fk_usuari` FOREIGN KEY (`usuari`) REFERENCES `usuari`(`id_usuari`),
   CONSTRAINT `fk_reserva` FOREIGN KEY (`reserva`) REFERENCES `reserva`(`id_reserva`)

); 

--Inserció dades de prova (DML)

INSERT INTO `usuari` (`id_usuari`, `contrasenya`, `nom`, `cognoms`, `telefon`, `rol`) VALUES
('aaron', 'aaron', NULL, NULL, NULL, 'admin'),
('dwajo', 'dwajo', 'Dwayne', 'Johnson', '666666666', 'client');

INSERT INTO `viatge` (`id_viatge`, `titol`, `descripcio`, `preu`, `places`, `durada`, `categoria`) VALUES
('0001', 'Sail Like a Dragon', 'Embárcate en un viaje en balsa por los canales iluminados de una vibrante ciudad japonesa, donde el bullicio urbano se mezcla con la serenidad del agua. Disfruta de las vistas de rascacielos y neones reflejados en el río, mientras saboreas delicias locales en el camino. Este recorrido es una experiencia única que resonará en el corazón de quienes conocen la cultura urbana japonesa. ¡Una aventura que no querrás perderte!', '200.00', 12, 60, 'privat'),
('0002', 'Descubre Venecia', 'Navega por los encantadores canales de Venecia en una balsa, donde cada remada te sumerge en la magia de esta ciudad única. Admira los majestuosos palacios y puentes que se asoman al agua, mientras el suave murmullo de las olas acompaña tu travesía. Disfruta de la brisa fresca y el aroma del café que flota desde las terrazas cercanas. Escucha las historias de gondoleros y locales, y déjate llevar por la belleza de los atardeceres venecianos, creando recuerdos que perdurarán para siempre.', '30.00', 9, 60, 'grup');

INSERT INTO `hores` (`viatge`, `hora`) VALUES
('0002', '09:30:00'),
('0002', '11:30:00'),
('0002', '13:30:00');


INSERT INTO `reserva` (`id_reserva`, `usuari`, `viatge`, `hora`, `data`, `assistents`) VALUES
('000000001', 'dwajo', '0002', '13:30:00', '2024-11-29', 2),
('000000002', 'dwajo', '0001', '16:19:00', '2024-11-29', 10);


INSERT INTO `seguiment` (`id_accio`, `tipus`, `data`, `reserva`, `usuari`, `comentaris`, `nova_data`, `nova_hora`) VALUES
(1, 'reservada', '2024-11-21 20:21:00', '000000001', 'dwajo', '', NULL, NULL),
(2, 'replanificada', '2024-11-21 20:22:00', '000000002', 'aaron', 'Modificat per risc meteorològic.', '2024-12-11', '09:10:00'),
(3, 'finalitzada', '2024-11-29 20:11:00', '000000001', 'aaron', 'Al vaixell li ha costat una mica arrencar, revisar el motor.', NULL, NULL),
(4, 'cancelada', '2024-11-22 08:43:00', '000000002', 'dwajo', 'Em fa una por gegant la pluja i no seré capaç de gaudir la meva escapada familiar, ho sento moltíssim.', NULL, NULL);

