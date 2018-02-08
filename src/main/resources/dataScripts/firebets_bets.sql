CREATE DATABASE  IF NOT EXISTS `firebets` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `firebets`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: firebets
-- ------------------------------------------------------
-- Server version	5.7.20-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bets`
--

DROP TABLE IF EXISTS `bets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bets` (
  `Users_Login` varchar(30) NOT NULL COMMENT 'Логин пользователя создавшего ставку',
  `Matches_idMatches` int(11) NOT NULL COMMENT 'ID матча на который стоит ставка',
  `Size` decimal(10,3) NOT NULL COMMENT 'размер ставки',
  `Type` varchar(45) NOT NULL COMMENT 'тип ставки (победа команды 1, ничья и т.д.)',
  `goalsTeam1` int(11) DEFAULT NULL,
  `goalsTeam2` int(11) DEFAULT NULL,
  `status` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`Users_Login`,`Matches_idMatches`),
  KEY `fk_Bets_Matches1_idx` (`Matches_idMatches`),
  KEY `Users_Login_idx` (`Users_Login`),
  CONSTRAINT `fk_Bets_Matches1` FOREIGN KEY (`Matches_idMatches`) REFERENCES `matches` (`idMatches`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Bets_Users1` FOREIGN KEY (`Users_Login`) REFERENCES `users` (`Login`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Ставки пользователей на матчи';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bets`
--

LOCK TABLES `bets` WRITE;
/*!40000 ALTER TABLE `bets` DISABLE KEYS */;
INSERT INTO `bets` VALUES ('admin',6,3.000,'Team1',NULL,NULL,'lose'),('admin',9,1.000,'Team1',NULL,NULL,NULL),('Leshka',6,2.000,'Team1',NULL,NULL,'lose');
/*!40000 ALTER TABLE `bets` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-08  2:23:54
