-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 06. Jun 2022 um 10:37
-- Server-Version: 10.4.21-MariaDB
-- PHP-Version: 8.0.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `supervision`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `admin`
--

CREATE TABLE `admin` (
  `email` varchar(255) NOT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `firstAccess` datetime DEFAULT NULL,
  `lastAccess` datetime DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `admin`
--

INSERT INTO `admin` (`email`, `firstname`, `password`, `firstAccess`, `lastAccess`, `lastname`) VALUES
('admin2@gmail.com', 'Margit', 'test', '2022-06-05 20:14:51', '2022-06-05 20:18:02', 'Mandl'),
('admin@gmail.com', 'admin', 'test', '2022-06-03 20:41:39', '2022-06-05 20:17:27', 'test');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `admin_bachelor`
--

CREATE TABLE `admin_bachelor` (
  `admin_email` varchar(255) NOT NULL,
  `bachelors_tid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `admin_master`
--

CREATE TABLE `admin_master` (
  `admin_email` varchar(255) NOT NULL,
  `masters_tid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `admin_project`
--

CREATE TABLE `admin_project` (
  `admin_email` varchar(255) NOT NULL,
  `projects_tid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `assistant`
--

CREATE TABLE `assistant` (
  `email` varchar(255) NOT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `firstAccess` datetime DEFAULT NULL,
  `lastAccess` datetime DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `bachelorLimit` int(11) NOT NULL,
  `masterLimit` int(11) NOT NULL,
  `projectLimit` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `assistant`
--

INSERT INTO `assistant` (`email`, `firstname`, `password`, `firstAccess`, `lastAccess`, `lastname`, `bachelorLimit`, `masterLimit`, `projectLimit`) VALUES
('assistant@gmail.com', 'Harald', 'test', '2022-06-04 11:12:43', '2022-06-06 08:57:41', 'Mandl', 5, 3, 10);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `assistant_bachelor`
--

CREATE TABLE `assistant_bachelor` (
  `assistant_email` varchar(255) NOT NULL,
  `bachelors_tid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `assistant_bachelor`
--

INSERT INTO `assistant_bachelor` (`assistant_email`, `bachelors_tid`) VALUES
('assistant@gmail.com', 4259842),
('assistant@gmail.com', 4292609);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `assistant_master`
--

CREATE TABLE `assistant_master` (
  `assistant_email` varchar(255) NOT NULL,
  `masters_tid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `assistant_master`
--

INSERT INTO `assistant_master` (`assistant_email`, `masters_tid`) VALUES
('assistant@gmail.com', 4259843),
('assistant@gmail.com', 4259844);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `assistant_project`
--

CREATE TABLE `assistant_project` (
  `assistant_email` varchar(255) NOT NULL,
  `projects_tid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `assistant_project`
--

INSERT INTO `assistant_project` (`assistant_email`, `projects_tid`) VALUES
('assistant@gmail.com', 4259840),
('assistant@gmail.com', 4292608);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `bachelor`
--

CREATE TABLE `bachelor` (
  `tid` int(11) NOT NULL,
  `deadline` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mark` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `submit` datetime DEFAULT NULL,
  `assistant_mail` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `bachelor`
--

INSERT INTO `bachelor` (`tid`, `deadline`, `description`, `email`, `mark`, `name`, `submit`, `assistant_mail`) VALUES
(4259842, '0030-11-02 00:00:00', '', NULL, 2, 'Bachelor2', NULL, 'assistant@gmail.com'),
(4292609, '0002-11-30 00:00:00', '', 'harald.mandl@gmail.com', 3, 'Bachelor', NULL, 'assistant@gmail.com');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `hibernate_sequences`
--

CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) DEFAULT NULL,
  `sequence_next_hi_value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `hibernate_sequences`
--

INSERT INTO `hibernate_sequences` (`sequence_name`, `sequence_next_hi_value`) VALUES
('task', 132);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `master`
--

CREATE TABLE `master` (
  `tid` int(11) NOT NULL,
  `deadline` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mark` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `submit` datetime DEFAULT NULL,
  `assistant_mail` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `master`
--

INSERT INTO `master` (`tid`, `deadline`, `description`, `email`, `mark`, `name`, `submit`, `assistant_mail`) VALUES
(4259843, '0002-11-30 00:00:00', '', NULL, 0, 'Master', NULL, 'assistant@gmail.com'),
(4259844, '0002-11-30 00:00:00', '', 'harald.mandl@gmail.com', 0, 'Master2', NULL, 'assistant@gmail.com');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `project`
--

CREATE TABLE `project` (
  `tid` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `assistant_mail` varchar(255) DEFAULT NULL,
  `deadline` datetime DEFAULT NULL,
  `Mark` int(11) NOT NULL,
  `submit` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `project`
--

INSERT INTO `project` (`tid`, `description`, `email`, `name`, `assistant_mail`, `deadline`, `Mark`, `submit`) VALUES
(4259840, '', 'harald.mandl@gmail.com', 'MyProject2', 'assistant@gmail.com', '0030-11-02 00:00:00', 3, NULL),
(4292608, '', 'harald.mandl@gmail.com', 'Project', 'assistant@gmail.com', '0030-11-02 00:00:00', 5, NULL);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `registration`
--

CREATE TABLE `registration` (
  `email` varchar(255) NOT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `problem` varchar(255) DEFAULT NULL,
  `secondname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `student`
--

CREATE TABLE `student` (
  `email` varchar(255) NOT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `vintage` datetime DEFAULT NULL,
  `firstAccess` datetime DEFAULT NULL,
  `lastAccess` datetime DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `student`
--

INSERT INTO `student` (`email`, `firstname`, `password`, `vintage`, `firstAccess`, `lastAccess`, `lastname`) VALUES
('harald.mandl@gmail.com', 'Harald', 'test', '2022-05-24 19:59:01', '2022-05-24 19:59:01', '2022-06-06 08:57:59', 'Mandl'),
('no@gmail.com', 'no', 'no', NULL, '2022-06-05 20:18:12', '2022-06-05 20:18:12', 'no');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `student_bachelor`
--

CREATE TABLE `student_bachelor` (
  `student_email` varchar(255) NOT NULL,
  `bachelors_tid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `student_bachelor`
--

INSERT INTO `student_bachelor` (`student_email`, `bachelors_tid`) VALUES
('harald.mandl@gmail.com', 4292609);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `student_master`
--

CREATE TABLE `student_master` (
  `student_email` varchar(255) NOT NULL,
  `masters_tid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `student_master`
--

INSERT INTO `student_master` (`student_email`, `masters_tid`) VALUES
('harald.mandl@gmail.com', 4259844);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `student_project`
--

CREATE TABLE `student_project` (
  `student_email` varchar(255) NOT NULL,
  `projects_tid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `student_project`
--

INSERT INTO `student_project` (`student_email`, `projects_tid`) VALUES
('harald.mandl@gmail.com', 4259840),
('harald.mandl@gmail.com', 4292608);

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`email`);

--
-- Indizes für die Tabelle `admin_bachelor`
--
ALTER TABLE `admin_bachelor`
  ADD UNIQUE KEY `bachelors_tid` (`bachelors_tid`),
  ADD KEY `FK93E4F69E6E68FDFB` (`bachelors_tid`),
  ADD KEY `FK93E4F69E72D01849` (`admin_email`);

--
-- Indizes für die Tabelle `admin_master`
--
ALTER TABLE `admin_master`
  ADD UNIQUE KEY `masters_tid` (`masters_tid`),
  ADD KEY `FKEABA9D52EA31CC7B` (`masters_tid`),
  ADD KEY `FKEABA9D5272D01849` (`admin_email`);

--
-- Indizes für die Tabelle `admin_project`
--
ALTER TABLE `admin_project`
  ADD UNIQUE KEY `projects_tid` (`projects_tid`),
  ADD KEY `FK281136295F3A74EB` (`projects_tid`),
  ADD KEY `FK2811362972D01849` (`admin_email`);

--
-- Indizes für die Tabelle `assistant`
--
ALTER TABLE `assistant`
  ADD PRIMARY KEY (`email`);

--
-- Indizes für die Tabelle `assistant_bachelor`
--
ALTER TABLE `assistant_bachelor`
  ADD UNIQUE KEY `bachelors_tid` (`bachelors_tid`),
  ADD KEY `FK8AA497EFED50F927` (`assistant_email`),
  ADD KEY `FK8AA497EF6E68FDFB` (`bachelors_tid`);

--
-- Indizes für die Tabelle `assistant_master`
--
ALTER TABLE `assistant_master`
  ADD UNIQUE KEY `masters_tid` (`masters_tid`),
  ADD KEY `FKC4E45EE3EA31CC7B` (`masters_tid`),
  ADD KEY `FKC4E45EE3ED50F927` (`assistant_email`);

--
-- Indizes für die Tabelle `assistant_project`
--
ALTER TABLE `assistant_project`
  ADD UNIQUE KEY `projects_tid` (`projects_tid`),
  ADD KEY `FK931FA6B8ED50F927` (`assistant_email`),
  ADD KEY `FK931FA6B85F3A74EB` (`projects_tid`);

--
-- Indizes für die Tabelle `bachelor`
--
ALTER TABLE `bachelor`
  ADD PRIMARY KEY (`tid`),
  ADD KEY `FK7E487FAE280E5CA4` (`assistant_mail`),
  ADD KEY `FK7E487FAEFFFACCA5` (`email`);

--
-- Indizes für die Tabelle `master`
--
ALTER TABLE `master`
  ADD PRIMARY KEY (`tid`),
  ADD KEY `FKBF8D2A62280E5CA4` (`assistant_mail`),
  ADD KEY `FKBF8D2A62FFFACCA5` (`email`);

--
-- Indizes für die Tabelle `project`
--
ALTER TABLE `project`
  ADD PRIMARY KEY (`tid`),
  ADD KEY `FKED904B19280E5CA4` (`assistant_mail`),
  ADD KEY `FKED904B19FFFACCA5` (`email`);

--
-- Indizes für die Tabelle `registration`
--
ALTER TABLE `registration`
  ADD PRIMARY KEY (`email`);

--
-- Indizes für die Tabelle `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`email`);

--
-- Indizes für die Tabelle `student_bachelor`
--
ALTER TABLE `student_bachelor`
  ADD UNIQUE KEY `bachelors_tid` (`bachelors_tid`),
  ADD KEY `FKA9CE8132D6BBD9A1` (`student_email`),
  ADD KEY `FKA9CE81326E68FDFB` (`bachelors_tid`);

--
-- Indizes für die Tabelle `student_master`
--
ALTER TABLE `student_master`
  ADD UNIQUE KEY `masters_tid` (`masters_tid`),
  ADD KEY `FKC0EDBCE6EA31CC7B` (`masters_tid`),
  ADD KEY `FKC0EDBCE6D6BBD9A1` (`student_email`);

--
-- Indizes für die Tabelle `student_project`
--
ALTER TABLE `student_project`
  ADD UNIQUE KEY `projects_tid` (`projects_tid`),
  ADD KEY `FK184209155F3A74EB` (`projects_tid`),
  ADD KEY `FK18420915D6BBD9A1` (`student_email`);

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `admin_bachelor`
--
ALTER TABLE `admin_bachelor`
  ADD CONSTRAINT `FK93E4F69E6E68FDFB` FOREIGN KEY (`bachelors_tid`) REFERENCES `bachelor` (`tid`),
  ADD CONSTRAINT `FK93E4F69E72D01849` FOREIGN KEY (`admin_email`) REFERENCES `admin` (`email`);

--
-- Constraints der Tabelle `admin_master`
--
ALTER TABLE `admin_master`
  ADD CONSTRAINT `FKEABA9D5272D01849` FOREIGN KEY (`admin_email`) REFERENCES `admin` (`email`),
  ADD CONSTRAINT `FKEABA9D52EA31CC7B` FOREIGN KEY (`masters_tid`) REFERENCES `master` (`tid`);

--
-- Constraints der Tabelle `admin_project`
--
ALTER TABLE `admin_project`
  ADD CONSTRAINT `FK281136295F3A74EB` FOREIGN KEY (`projects_tid`) REFERENCES `project` (`tid`),
  ADD CONSTRAINT `FK2811362972D01849` FOREIGN KEY (`admin_email`) REFERENCES `admin` (`email`);

--
-- Constraints der Tabelle `assistant_bachelor`
--
ALTER TABLE `assistant_bachelor`
  ADD CONSTRAINT `FK8AA497EF6E68FDFB` FOREIGN KEY (`bachelors_tid`) REFERENCES `bachelor` (`tid`),
  ADD CONSTRAINT `FK8AA497EFED50F927` FOREIGN KEY (`assistant_email`) REFERENCES `assistant` (`email`);

--
-- Constraints der Tabelle `assistant_master`
--
ALTER TABLE `assistant_master`
  ADD CONSTRAINT `FKC4E45EE3EA31CC7B` FOREIGN KEY (`masters_tid`) REFERENCES `master` (`tid`),
  ADD CONSTRAINT `FKC4E45EE3ED50F927` FOREIGN KEY (`assistant_email`) REFERENCES `assistant` (`email`);

--
-- Constraints der Tabelle `assistant_project`
--
ALTER TABLE `assistant_project`
  ADD CONSTRAINT `FK931FA6B85F3A74EB` FOREIGN KEY (`projects_tid`) REFERENCES `project` (`tid`),
  ADD CONSTRAINT `FK931FA6B8ED50F927` FOREIGN KEY (`assistant_email`) REFERENCES `assistant` (`email`);

--
-- Constraints der Tabelle `bachelor`
--
ALTER TABLE `bachelor`
  ADD CONSTRAINT `FK7E487FAE280E5CA4` FOREIGN KEY (`assistant_mail`) REFERENCES `assistant` (`email`),
  ADD CONSTRAINT `FK7E487FAEFFFACCA5` FOREIGN KEY (`email`) REFERENCES `student` (`email`);

--
-- Constraints der Tabelle `master`
--
ALTER TABLE `master`
  ADD CONSTRAINT `FKBF8D2A62280E5CA4` FOREIGN KEY (`assistant_mail`) REFERENCES `assistant` (`email`),
  ADD CONSTRAINT `FKBF8D2A62FFFACCA5` FOREIGN KEY (`email`) REFERENCES `student` (`email`);

--
-- Constraints der Tabelle `project`
--
ALTER TABLE `project`
  ADD CONSTRAINT `FKED904B19280E5CA4` FOREIGN KEY (`assistant_mail`) REFERENCES `assistant` (`email`),
  ADD CONSTRAINT `FKED904B19FFFACCA5` FOREIGN KEY (`email`) REFERENCES `student` (`email`);

--
-- Constraints der Tabelle `student_bachelor`
--
ALTER TABLE `student_bachelor`
  ADD CONSTRAINT `FKA9CE81326E68FDFB` FOREIGN KEY (`bachelors_tid`) REFERENCES `bachelor` (`tid`),
  ADD CONSTRAINT `FKA9CE8132D6BBD9A1` FOREIGN KEY (`student_email`) REFERENCES `student` (`email`);

--
-- Constraints der Tabelle `student_master`
--
ALTER TABLE `student_master`
  ADD CONSTRAINT `FKC0EDBCE6D6BBD9A1` FOREIGN KEY (`student_email`) REFERENCES `student` (`email`),
  ADD CONSTRAINT `FKC0EDBCE6EA31CC7B` FOREIGN KEY (`masters_tid`) REFERENCES `master` (`tid`);

--
-- Constraints der Tabelle `student_project`
--
ALTER TABLE `student_project`
  ADD CONSTRAINT `FK184209155F3A74EB` FOREIGN KEY (`projects_tid`) REFERENCES `project` (`tid`),
  ADD CONSTRAINT `FK18420915D6BBD9A1` FOREIGN KEY (`student_email`) REFERENCES `student` (`email`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
