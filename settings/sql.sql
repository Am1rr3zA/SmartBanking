-- phpMyAdmin SQL Dump
-- version 4.0.10.10
-- http://www.phpmyadmin.net
--
-- Host: 127.13.80.130:3306
-- Generation Time: Dec 02, 2015 at 12:55 AM
-- Server version: 5.5.45
-- PHP Version: 5.3.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `smart`
--
CREATE DATABASE IF NOT EXISTS `smart` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `smart`;

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
CREATE TABLE IF NOT EXISTS `account` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `pid` int(11) NOT NULL,
  `account_number` varchar(16) COLLATE utf8_bin NOT NULL DEFAULT '',
  `account_type` int(11) DEFAULT NULL,
  `description` text COLLATE utf8_bin,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=6 ;

--
-- Truncate table before insert `account`
--

TRUNCATE TABLE `account`;
--
-- Dumping data for table `account`
--

INSERT INTO `account` (`id`, `pid`, `account_number`, `account_type`, `description`) VALUES
(1, 1, '0100907846000', NULL, 'Checking'),
(2, 1, '0200217195008', NULL, 'Saving'),
(3, 1, '1235123123123123', 0, 'حساب طلایی'),
(4, 2, '123980123123', 0, 'دسته چک'),
(5, 2, '1239876987123', 0, 'تست 2');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
CREATE TABLE IF NOT EXISTS `login` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) COLLATE utf8_bin NOT NULL,
  `password` varchar(20) COLLATE utf8_bin NOT NULL,
  `client_id` varchar(11) COLLATE utf8_bin DEFAULT NULL,
  `client_secret` varchar(6) COLLATE utf8_bin DEFAULT NULL,
  `access_token` varchar(36) COLLATE utf8_bin NOT NULL,
  `accounts` text COLLATE utf8_bin,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=3 ;

--
-- Truncate table before insert `login`
--

TRUNCATE TABLE `login`;
--
-- Dumping data for table `login`
--

INSERT INTO `login` (`id`, `username`, `password`, `client_id`, `client_secret`, `access_token`, `accounts`) VALUES
(1, 'elnaz', 'eli', '1d9dcd9h14h', 'nb314t', 'd2709f67-ce5b-412e-bba7-202ed979ab18', '0100907846000,0200217195008'),
(2, 'Milad', 'mili', '1d9dcd9h15h', '123qwe', 'd2709f67-ce5b-412e-bba7-202ed979ab18', '0100907846000,0200217195008');

-- --------------------------------------------------------

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
CREATE TABLE IF NOT EXISTS `report` (
  `pid` int(11) DEFAULT NULL,
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `rid` int(11) NOT NULL,
  `reportdate` date DEFAULT NULL,
  `satisfy` int(1) DEFAULT NULL,
  `trigered` text COLLATE utf8_bin,
  `reject` text COLLATE utf8_bin,
  PRIMARY KEY (`id`),
  KEY `ridFK` (`rid`),
  KEY `pidForK` (`pid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=15 ;

--
-- Truncate table before insert `report`
--

TRUNCATE TABLE `report`;
--
-- Dumping data for table `report`
--

INSERT INTO `report` (`pid`, `id`, `rid`, `reportdate`, `satisfy`, `trigered`, `reject`) VALUES
(1, 13, 1, '2015-03-12', 0, NULL, NULL),
(1, 14, 1, '2015-03-12', 1, '2 1000*#4 YYYYMMDD*#', '4 YYYYMMDD-YYYYMMDD*#5 5*#');

-- --------------------------------------------------------

--
-- Table structure for table `rule`
--

DROP TABLE IF EXISTS `rule`;
CREATE TABLE IF NOT EXISTS `rule` (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NOT NULL,
  `condition` text COLLATE utf8_bin NOT NULL,
  `action` text COLLATE utf8_bin NOT NULL,
  `ruleName` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`rid`),
  KEY `login_fk` (`pid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=34 ;

--
-- Truncate table before insert `rule`
--

TRUNCATE TABLE `rule`;
--
-- Dumping data for table `rule`
--

INSERT INTO `rule` (`rid`, `pid`, `condition`, `action`, `ruleName`) VALUES
(1, 1, '1 1000*#date 20141120*#', 'acount 11111', 'موسسه محک'),
(2, 1, '1 100*#4 13940908-13940910*#', '0200217195008 20', 'خانه سالمندان'),
(3, 1, '11 15*#4 13940901-13940909*#', '0200217195008 50', 'کانون انسان شناسی و فرهنگ '),
(7, 1, '1 1000*#4 13941001*#', '0200217195008 100', 'کانون هواداران پرسپولیس'),
(8, 1, '9 100*#5 10*#', '0200217195008 50', 'موسسه خوارزمی'),
(9, 1, '8 6*#', '12039789123 1000000', ''),
(10, 1, '8 6*#', '12039789123 1000000', ''),
(11, 1, '8 6*#', '12039789123 1000000', ''),
(12, 1, '8 6*#', '12039789123 1000000', ''),
(13, 1, '8 6*#', '12039789123 1000000', ''),
(14, 1, '8 6*#', '12039789123 1000000', ''),
(15, 1, '8 6*#', '12039789123 1000000', ''),
(16, 1, '1 1000000*#4 13940506*#', '1236781823 10000000', '??? ???'),
(17, 1, '9 200000*#', '2309187213123 100000', ''),
(18, 1, '1 1000000*#5 7*#', '989872198 100000000', 'تست میلاد'),
(19, 1, '2 10000*#1 100000*#', '123123321 1000000000', 'قسط تست'),
(20, 1, '2 1000000*#', '989872198 567', ''),
(21, 1, '1 100000*#8 6*#5 54*#', '45345345345 1000000', 'واریزی پیچیده'),
(22, 1, '9 100000*#', '989872198 100000', 'واریز ماهانه'),
(23, 1, '1 100000*#', '1123123 10000000', ''),
(24, 1, '1 100000*#5 2000000*#', '989872198 10000000', ''),
(25, 2, '9 1000000*#4 13940106*#', '10000123123 1200', ''),
(28, 2, '9 1231*#', '11231 322', 'Milad K'),
(29, 1, '1 1000*#4 13940908-13940910*#', '989872198 100', ''),
(30, 1, '8 5*#', '0200217195008 100', ''),
(31, 1, '8 5*#', '0200217195008 100', ''),
(32, 1, '8 5*#', '0200217195008 20000', ''),
(33, 1, '1 100000*#4 13941001*#', '123123321 1000', '');

-- --------------------------------------------------------

--
-- Table structure for table `suggestion`
--

DROP TABLE IF EXISTS `suggestion`;
CREATE TABLE IF NOT EXISTS `suggestion` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `pid` int(11) NOT NULL,
  `account_number` varchar(16) COLLATE utf8_bin NOT NULL DEFAULT '',
  `suggestion_name` varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `suggestion_login_fk` (`pid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=7 ;

--
-- Truncate table before insert `suggestion`
--

TRUNCATE TABLE `suggestion`;
--
-- Dumping data for table `suggestion`
--

INSERT INTO `suggestion` (`id`, `pid`, `account_number`, `suggestion_name`) VALUES
(1, 1, '989872198', 'بنیاد دانش محوران جوان'),
(5, 1, '123123321', 'بنیاد ایتام امام حسین'),
(6, 2, '123123321', 'بنیاد ایتام امام حسین');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `report`
--
ALTER TABLE `report`
  ADD CONSTRAINT `pidForK` FOREIGN KEY (`pid`) REFERENCES `login` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `ridFK` FOREIGN KEY (`rid`) REFERENCES `rule` (`rid`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `rule`
--
ALTER TABLE `rule`
  ADD CONSTRAINT `pidFK` FOREIGN KEY (`pid`) REFERENCES `login` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `suggestion`
--
ALTER TABLE `suggestion`
  ADD CONSTRAINT `suggestion_login_fk` FOREIGN KEY (`pid`) REFERENCES `login` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
