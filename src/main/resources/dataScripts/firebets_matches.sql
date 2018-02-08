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
-- Table structure for table `matches`
--

DROP TABLE IF EXISTS `matches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `matches` (
  `idMatches` int(11) NOT NULL AUTO_INCREMENT,
  `Team1` varchar(45) NOT NULL COMMENT 'Команда 1',
  `Team2` varchar(45) NOT NULL COMMENT 'Команда 2',
  `DateTime` datetime(6) NOT NULL COMMENT 'Дата и время проведения матча',
  `goalsTeam1` int(11) DEFAULT NULL,
  `goalsTeam2` int(11) DEFAULT NULL,
  PRIMARY KEY (`idMatches`),
  KEY `Teams_idx` (`Team1`,`Team2`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='Матчи';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matches`
--

LOCK TABLES `matches` WRITE;
/*!40000 ALTER TABLE `matches` DISABLE KEYS */;
INSERT INTO `matches` VALUES (4,'Арсенал','Ливерпуль','2017-12-22 22:45:00.542000',3,2),(5,'Эвертон','Челси','2017-12-23 15:30:00.809000',1,2),(6,'Брайтон','Уотфорд','2017-12-23 18:00:00.647000',1,2),(7,'Лестер','Манчестер Юнайтед','2017-12-23 23:45:00.519000',13,2),(8,'Тоттенхем','Саутгемптон','2017-12-26 15:30:00.782000',3,1),(9,'Манчестер Юнайтед','Бернли','2018-12-26 15:30:00.320000',NULL,NULL);
/*!40000 ALTER TABLE `matches` ENABLE KEYS */;
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
