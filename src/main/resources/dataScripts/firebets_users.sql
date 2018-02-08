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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `Login` varchar(30) NOT NULL,
  `Password` varchar(45) NOT NULL,
  `Name` varchar(45) NOT NULL,
  `SurName` varchar(45) NOT NULL,
  `Role` varchar(45) NOT NULL,
  `Balance` decimal(10,3) DEFAULT NULL,
  `Email` varchar(45) NOT NULL,
  PRIMARY KEY (`Login`),
  KEY `Login_idx` (`Login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Всё пользователи';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('admin','admin','Влад','Скорый','admin',20.481,'Skoriy.97@gmail.com'),('bookmaker','bookmaker','Наталия','Блошук','bookmaker',30.000,'bookmaker@gmail.com'),('Leshka','12345678','Alexei','Staskevich','player',10.000,'Leshka228@gmail.com'),('myakish','12345678','Yana','Myakinkaya','player',10.000,'yana.myak@gmail.com'),('Myxa','12345678','Dima','Myhin','player',10.000,'myhinDima@gmail.com'),('StormRage','12345678','Vlad','Skoriy','bookmaker',10.000,'stormqop322@list.ru'),('StormRage2','admin','Vlad','Skoriy','admin',5.000,'Skoriy.97@gmail.com'),('Vlad','123456789Lol','Vlad','Skoriy','player',10.000,'Skoriy.97@gmail.com'),('Vlad1','12345678','Vlad','Skoriy','player',10.000,'Skoriy.97@gmail.com'),('Vlad2','12345678','Vlad','Skoriy','player',10.000,'Skoriy.97@gmail.com'),('Vlad3','12345678','Vlad','Skoriy','player',10.000,'Skoriy.97@gmail.com'),('Vlad4','12345679','Vlad','Skoriy','player',10.000,'Skoriy.97@gmail.com'),('yaroshAA','12345678','Alexander','Yarosh','player',10.000,'Yarosh.97@gmail.com');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
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
