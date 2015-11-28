# ************************************************************
# Sequel Pro SQL dump
# Version 4499
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.5.42)
# Database: smartBanking
# Generation Time: 2015-11-28 04:36:33 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table login
# ------------------------------------------------------------

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) COLLATE utf8_bin NOT NULL,
  `password` varchar(20) COLLATE utf8_bin NOT NULL,
  `client_id` varchar(11) COLLATE utf8_bin DEFAULT NULL,
  `client_secret` varchar(6) COLLATE utf8_bin DEFAULT NULL,
  `access_token` varchar(36) COLLATE utf8_bin NOT NULL,
  `accounts` text COLLATE utf8_bin,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;

INSERT INTO `login` (`id`, `username`, `password`, `client_id`, `client_secret`, `access_token`, `accounts`)
VALUES
	(1,X'456C6E617A',X'656C69',X'3164396463643968313468',X'6E6233313474',X'66626262633834392D653639392D346566332D613933352D613632353839353933353534',X'303130303930373834363030302C30323030323137313935303038');

/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table report
# ------------------------------------------------------------

DROP TABLE IF EXISTS `report`;

CREATE TABLE `report` (
  `pid` int(11) DEFAULT NULL,
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `rid` int(11) NOT NULL,
  `reportdate` text COLLATE utf8_bin,
  `satisfy` int(1) DEFAULT NULL,
  `trigered` text COLLATE utf8_bin,
  `reject` text COLLATE utf8_bin,
  PRIMARY KEY (`id`),
  KEY `ridFK` (`rid`),
  KEY `pidForK` (`pid`),
  CONSTRAINT `pidForK` FOREIGN KEY (`pid`) REFERENCES `login` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ridFK` FOREIGN KEY (`rid`) REFERENCES `rule` (`rid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;

INSERT INTO `report` (`pid`, `id`, `rid`, `reportdate`, `satisfy`, `trigered`, `reject`)
VALUES
	(1,13,1,X'467269204E6F762032372032323A34343A3233204553542032303135',0,NULL,NULL);

/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table rule
# ------------------------------------------------------------

DROP TABLE IF EXISTS `rule`;

CREATE TABLE `rule` (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NOT NULL,
  `condition` text COLLATE utf8_bin NOT NULL,
  `action` text COLLATE utf8_bin NOT NULL,
  `ruleName` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`rid`),
  KEY `login_fk` (`pid`),
  CONSTRAINT `pidFK` FOREIGN KEY (`pid`) REFERENCES `login` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

LOCK TABLES `rule` WRITE;
/*!40000 ALTER TABLE `rule` DISABLE KEYS */;

INSERT INTO `rule` (`rid`, `pid`, `condition`, `action`, `ruleName`)
VALUES
	(1,1,X'3120313030302A23646174652032303134313132302A23',X'61636F756E74203131313131',X'446F6E6174696F6E'),
	(2,1,X'3220313030302A233420595959594D4D44442A233420595959594D4D44442D595959594D4D44442A233520352A23',X'6163636F756E74206F722063617274206E756D62657220313233323133',NULL);

/*!40000 ALTER TABLE `rule` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
