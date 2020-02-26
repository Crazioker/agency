CREATE DATABASE  IF NOT EXISTS `GDUFS_Agency` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `GDUFS_Agency`;
-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: 120.78.219.119    Database: GDUFS_Agency
-- ------------------------------------------------------
-- Server version	5.7.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Acceptance`
--

DROP TABLE IF EXISTS `Acceptance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Acceptance` (
  `acceptId` varchar(11) NOT NULL,
  `indentId` int(11) NOT NULL,
  `acceptedTime` varchar(32) DEFAULT NULL,
  `finishedTime` varchar(32) DEFAULT NULL,
  `state` int(1) NOT NULL,
  PRIMARY KEY (`acceptId`,`indentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Comment`
--

DROP TABLE IF EXISTS `Comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Comment` (
  `commentId` int(11) NOT NULL AUTO_INCREMENT,
  `publishId` varchar(11) NOT NULL,
  `acceptId` varchar(11) NOT NULL,
  `indentId` int(11) NOT NULL,
  `content` varchar(100) NOT NULL,
  PRIMARY KEY (`commentId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Indent`
--

DROP TABLE IF EXISTS `Indent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Indent` (
  `indentId` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(1) NOT NULL,
  `price` float(11,0) NOT NULL,
  `description` varchar(32) NOT NULL,
  `address` varchar(32) NOT NULL,
  `state` int(1) NOT NULL,
  `publishId` varchar(32) NOT NULL,
  `publishTime` varchar(32) NOT NULL,
  `planTime` varchar(32) NOT NULL,
  PRIMARY KEY (`indentId`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `User` (
  `studentId` varchar(11) NOT NULL,
  `username` varchar(32) DEFAULT NULL,
  `password` varchar(32) NOT NULL,
  `phoneNum` varchar(32) DEFAULT NULL,
  `academy` varchar(32) DEFAULT NULL,
  `address` varchar(32) DEFAULT NULL,
  `balance` double(32,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`studentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-26 10:36:39
