/*
SQLyog Ultimate v9.63 
MySQL - 5.5.5-10.1.8-MariaDB : Database - smartbanking
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`smartbanking` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `smartbanking`;

/*Table structure for table `login` */

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `login` */

insert  into `login`(`id`,`username`,`password`,`client_id`,`client_secret`,`access_token`,`accounts`) values (1,'Elnaz','eli','1d9dcd9h14h','nb314t','fbbbc849-e699-4ef3-a935-a62589593554','0100907846000,0200217195008');

/*Table structure for table `report` */

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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `report` */

insert  into `report`(`pid`,`id`,`rid`,`reportdate`,`satisfy`,`trigered`,`reject`) values (1,13,1,'Fri Nov 27 22:44:23 EST 2015',0,NULL,NULL);

/*Table structure for table `rule` */

DROP TABLE IF EXISTS `rule`;

CREATE TABLE `rule` (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NOT NULL,
  `condition` text COLLATE utf8_bin NOT NULL,
  `action` text COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`rid`),
  KEY `login_fk` (`pid`),
  CONSTRAINT `pidFK` FOREIGN KEY (`pid`) REFERENCES `login` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `rule` */

insert  into `rule`(`rid`,`pid`,`condition`,`action`) values (1,1,'balance>1000 And date,20141120','acount 11111');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
