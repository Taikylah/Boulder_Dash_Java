-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  mar. 28 mai 2019 à 07:17
-- Version du serveur :  5.7.24
-- Version de PHP :  7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `jpublankproject`
--

DELIMITER $$
--
-- Procédures
--
DROP PROCEDURE IF EXISTS `HelloworldByCode`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `HelloworldByCode` (IN `p_code` VARCHAR(2))  READS SQL DATA
    SQL SECURITY INVOKER
SELECT * FROM jpublankproject.helloworld where `code`=p_code$$

DROP PROCEDURE IF EXISTS `helloworldById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `helloworldById` (IN `p_id` INT)  READS SQL DATA
    SQL SECURITY INVOKER
SELECT * FROM helloworld WHERE id = p_id$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `helloworld`
--

DROP TABLE IF EXISTS `helloworld`;
CREATE TABLE IF NOT EXISTS `helloworld` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(2) NOT NULL,
  `message` varchar(500) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `helloworld`
--

INSERT INTO `helloworld` (`id`, `code`, `message`) VALUES
(1, 'UP', '1111111111;1000220201;1502002001;1002202001;1330030001;1200023231;1223000201;1000220301;1000022061;1023302221;1111111111'),
(2, 'DW', '11111111111111111111;10000003000003000001;10022200022200220201;15000030030000000001;10030022200020000001;10000000000032220001;11111111111111000301;10000002030000002001;10220000002200002201;10303000000000300001;10002200030002200001;10000000220000002201;10300022000300000001;10000001111111111111;10200000000000002001;10302002030022300001;10003000003000000061;10000002220000223001;10000000000000000001;11111111111111111111'),
(3, 'RI', '11111111111111111111;15000220030022000231;10020000220000020001;11111111111111111331;10210033000220301201;10213000223000221001;10000202000030003301;10311111111111111111;10000000202000021021;10000220030000021021;10003300030322003301;11111111111111111001;10010002202002200001;12012000000203300021;12030000003000020201;10311111111111111111;10300002002000020001;10020200300000000001;10200020000200320061;11111111111111111111'),
(4, 'LE', '11111111111111111111;15000222000000000001;10000333002000202001;10000000002002202201;10000203000000000321;10000022000232030201;11111111111111100001;10000000000000000001;10020002003000020001;10203000020002020001;10002000300302000201;10202300020000030001;12020230300000202001;10000011111111111111;10020020000200020001;10200320000220020001;10020000030300000001;10000032020000020301;10022000002030020061;11111111111111111111'),
(5, 'PP', '11111111111111111111;15002200000200001221;10302000303200021321;10000002200002021031;11030302200303000001;12100001111102230001;10011110020211200001;12200020022001002201;10200000203021303001;12030013003221000301;13020010230000200221;10302010023023200301;10003010200030002001;13200010002000301111;12020201100022213331;10003000211001100201;10300002201110202021;12030203003000002001;10202020030220000061;11111111111111111111');

-- --------------------------------------------------------

--
-- Structure de la table `maps`
--

DROP TABLE IF EXISTS `maps`;
CREATE TABLE IF NOT EXISTS `maps` (
  `ID_Map` int(11) DEFAULT NULL,
  `Code_Map` varchar(2000) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `maps`
--

INSERT INTO `maps` (`ID_Map`, `Code_Map`) VALUES
(1, '11111111111000220201150200200110022020011330030001120002323112230002011000220301100002206110233022211111111111');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
