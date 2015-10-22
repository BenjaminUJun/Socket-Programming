-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: localhost    Database: id26346702
-- ------------------------------------------------------
-- Server version	5.6.23

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
-- Table structure for table `booker1`
--

DROP TABLE IF EXISTS `booker1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `booker1` (
  `bookerID` varchar(255) NOT NULL,
  `bookerName` varchar(45) NOT NULL,
  `telePhone` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `passwd` varchar(45) NOT NULL,
  PRIMARY KEY (`bookerID`),
  UNIQUE KEY `bookerID_UNIQUE` (`bookerID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booker1`
--

LOCK TABLES `booker1` WRITE;
/*!40000 ALTER TABLE `booker1` DISABLE KEYS */;
INSERT INTO `booker1` VALUES ('26346702','yujun','18662219952','yujunshe@qq.com','123456');
/*!40000 ALTER TABLE `booker1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booker2`
--

DROP TABLE IF EXISTS `booker2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `booker2` (
  `bookerID` varchar(255) NOT NULL,
  `bookerName` varchar(45) NOT NULL,
  `telePhone` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `passwd` varchar(45) NOT NULL,
  PRIMARY KEY (`bookerID`),
  UNIQUE KEY `idbooker2_UNIQUE` (`bookerID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booker2`
--

LOCK TABLES `booker2` WRITE;
/*!40000 ALTER TABLE `booker2` DISABLE KEYS */;
/*!40000 ALTER TABLE `booker2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booker3`
--

DROP TABLE IF EXISTS `booker3`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `booker3` (
  `bookerID` varchar(255) NOT NULL,
  `bookerName` varchar(45) NOT NULL,
  `telePhone` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `passwd` varchar(45) NOT NULL,
  PRIMARY KEY (`bookerID`),
  UNIQUE KEY `idbooker2_UNIQUE` (`bookerID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booker3`
--

LOCK TABLES `booker3` WRITE;
/*!40000 ALTER TABLE `booker3` DISABLE KEYS */;
/*!40000 ALTER TABLE `booker3` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel1`
--

DROP TABLE IF EXISTS `hotel1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hotel1` (
  `hotelID` int(11) NOT NULL,
  `citystr` varchar(45) NOT NULL,
  `location` varchar(45) NOT NULL,
  `bandstr` varchar(45) NOT NULL,
  `vacantrooms` int(11) NOT NULL,
  `roomrate` double NOT NULL,
  `roomNo` int(11) NOT NULL,
  PRIMARY KEY (`hotelID`),
  UNIQUE KEY `hotelID_UNIQUE` (`hotelID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel1`
--

LOCK TABLES `hotel1` WRITE;
/*!40000 ALTER TABLE `hotel1` DISABLE KEYS */;
INSERT INTO `hotel1` VALUES (1,'NanJing','Jiangsu','hilton',10,235.99,10),(2,'Ningbo','Zhejiang','hilton',10,235.99,10),(3,'Suzhou','Jiangsu','hilton',10,235.99,10),(4,'Hangzhou','Zhejiang','hilton',10,235.99,10),(5,'Melbourne','Victoria','hilton',8,235.99,10),(6,'Sydney','NSW','hilton',10,235.99,10),(7,'Brisbane','Queensland','hilton',10,235.99,10),(8,'Adelaide','SA','hilton',10,235.99,10);
/*!40000 ALTER TABLE `hotel1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel2`
--

DROP TABLE IF EXISTS `hotel2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hotel2` (
  `hotelID` int(11) NOT NULL,
  `citystr` varchar(45) NOT NULL,
  `location` varchar(45) NOT NULL,
  `bandstr` varchar(45) NOT NULL,
  `vacantrooms` int(11) NOT NULL,
  `roomrate` double NOT NULL,
  `roomNo` int(11) NOT NULL,
  PRIMARY KEY (`hotelID`),
  UNIQUE KEY `hotelID_UNIQUE` (`hotelID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel2`
--

LOCK TABLES `hotel2` WRITE;
/*!40000 ALTER TABLE `hotel2` DISABLE KEYS */;
INSERT INTO `hotel2` VALUES (1,'NanJing','Jiangsu','chevron',20,100.01,20),(2,'Ningbo','Zhejiang','chevron',20,100.01,20),(3,'Suzhou','Jiangsu','chevron',20,100.01,20),(4,'Hangzhou','Zhejiang','chevron',20,100.01,20),(5,'Melbourne','Victoria','chevron',20,100.01,20),(6,'Sydney','NSW','chevron',20,100.01,20),(7,'Brisbane','Queensland','chevron',20,100.01,20),(8,'Adelaide','SA','chevron',20,100.01,20);
/*!40000 ALTER TABLE `hotel2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel3`
--

DROP TABLE IF EXISTS `hotel3`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hotel3` (
  `hotelID` int(11) NOT NULL,
  `citystr` varchar(45) NOT NULL,
  `location` varchar(45) NOT NULL,
  `bandstr` varchar(45) NOT NULL,
  `vacantrooms` int(11) NOT NULL,
  `roomrate` double NOT NULL,
  `roomNo` int(11) NOT NULL,
  PRIMARY KEY (`hotelID`),
  UNIQUE KEY `hotelID_UNIQUE` (`hotelID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel3`
--

LOCK TABLES `hotel3` WRITE;
/*!40000 ALTER TABLE `hotel3` DISABLE KEYS */;
INSERT INTO `hotel3` VALUES (1,'NanJing','Jiangsu','regent',20,150.98,20),(2,'Ningbo','Zhejiang','regent',20,150.98,20),(3,'Suzhou','Jiangsu','regent',20,150.98,20),(4,'Hangzhou','Zhejiang','regent',20,150.98,20),(5,'Melbourne','Victoria','regent',20,150.98,20),(6,'Sydney','NSW','regent',20,150.98,20),(7,'Brisbane','Queensland','regent',20,150.98,20),(8,'Adelaide','SA','regent',20,150.98,20);
/*!40000 ALTER TABLE `hotel3` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room1`
--

DROP TABLE IF EXISTS `room1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room1` (
  `roomID` int(11) NOT NULL,
  `checkinDate` date NOT NULL,
  `checkoutDate` date NOT NULL,
  `hotelID` int(11) NOT NULL,
  `isavail` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`roomID`),
  UNIQUE KEY `roomID_UNIQUE` (`roomID`),
  KEY `fk_hotel_idx` (`hotelID`),
  CONSTRAINT `fk_hotelID` FOREIGN KEY (`hotelID`) REFERENCES `hotel1` (`hotelID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room1`
--

LOCK TABLES `room1` WRITE;
/*!40000 ALTER TABLE `room1` DISABLE KEYS */;
INSERT INTO `room1` VALUES (10,'2015-03-10','2015-03-10',1,0),(11,'2015-03-11','2015-03-11',1,0),(12,'2015-03-12','2015-03-12',1,0),(13,'2015-03-13','2015-03-13',1,0),(14,'2015-03-14','2015-03-14',1,0),(15,'2015-03-15','2015-03-15',1,0),(16,'2015-03-16','2015-03-16',1,0),(17,'2015-03-17','2015-03-17',1,0),(18,'2015-03-18','2015-03-18',1,0),(19,'2015-03-19','2015-03-19',1,0),(20,'2015-03-10','2015-03-10',2,0),(21,'2015-03-11','2015-03-11',2,0),(22,'2015-03-12','2015-03-12',2,0),(23,'2015-03-13','2015-03-13',2,0),(24,'2015-03-14','2015-03-14',2,0),(25,'2015-03-15','2015-03-15',2,0),(26,'2015-03-16','2015-03-16',2,0),(27,'2015-03-17','2015-03-17',2,0),(28,'2015-03-18','2015-03-18',2,0),(29,'2015-03-19','2015-03-19',2,0),(30,'2015-03-10','2015-03-10',3,0),(31,'2015-03-11','2015-03-11',3,0),(32,'2015-03-12','2015-03-12',3,0),(33,'2015-03-13','2015-03-13',3,0),(34,'2015-03-14','2015-03-14',3,0),(35,'2015-03-15','2015-03-15',3,0),(36,'2015-03-16','2015-03-16',3,0),(37,'2015-03-17','2015-03-17',3,0),(38,'2015-03-18','2015-03-18',3,0),(39,'2015-03-19','2015-03-19',3,0),(40,'2015-03-10','2015-03-10',4,0),(41,'2015-03-11','2015-03-11',4,0),(42,'2015-03-12','2015-03-12',4,0),(43,'2015-03-13','2015-03-13',4,0),(44,'2015-03-14','2015-03-14',4,0),(45,'2015-03-15','2015-03-15',4,0),(46,'2015-03-16','2015-03-16',4,0),(47,'2015-03-17','2015-03-17',4,0),(48,'2015-03-18','2015-03-18',4,0),(49,'2015-03-19','2015-03-19',4,0),(50,'2015-03-10','2015-03-10',5,0),(51,'2015-03-11','2015-03-11',5,0),(52,'2015-03-12','2015-03-12',5,0),(53,'2015-03-13','2015-03-13',5,0),(54,'2015-03-14','2015-03-14',5,0),(55,'2015-03-15','2015-03-15',5,0),(56,'2015-02-11','2015-02-13',5,1),(57,'2015-02-13','2015-02-15',5,1),(58,'2015-03-18','2015-03-18',5,0),(59,'2015-03-19','2015-03-19',5,0),(60,'2015-03-10','2015-03-10',6,0),(61,'2015-03-11','2015-03-11',6,0),(62,'2015-03-12','2015-03-12',6,0),(63,'2015-03-13','2015-03-13',6,0),(64,'2015-03-14','2015-03-14',6,0),(65,'2015-03-15','2015-03-15',6,0),(66,'2015-03-16','2015-03-16',6,0),(67,'2015-03-17','2015-03-17',6,0),(68,'2015-03-18','2015-03-18',6,0),(69,'2015-03-19','2015-03-19',6,0),(70,'2015-03-10','2015-03-10',7,0),(71,'2015-03-11','2015-03-11',7,0),(72,'2015-03-12','2015-03-12',7,0),(73,'2015-03-13','2015-03-13',7,0),(74,'2015-03-14','2015-03-14',7,0),(75,'2015-03-15','2015-03-15',7,0),(76,'2015-03-16','2015-03-16',7,0),(77,'2015-03-17','2015-03-17',7,0),(78,'2015-03-18','2015-03-18',7,0),(79,'2015-03-19','2015-03-19',7,0),(80,'2015-03-10','2015-03-10',8,0),(81,'2015-03-11','2015-03-11',8,0),(82,'2015-03-12','2015-03-12',8,0),(83,'2015-03-13','2015-03-13',8,0),(84,'2015-03-14','2015-03-14',8,0),(85,'2015-03-15','2015-03-15',8,0),(86,'2015-03-16','2015-03-16',8,0),(87,'2015-03-17','2015-03-17',8,0),(88,'2015-03-18','2015-03-18',8,0),(89,'2015-03-19','2015-03-19',8,0);
/*!40000 ALTER TABLE `room1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room2`
--

DROP TABLE IF EXISTS `room2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room2` (
  `roomID` int(11) NOT NULL,
  `checkinDate` date NOT NULL,
  `checkoutDate` date NOT NULL,
  `hotelID` int(11) NOT NULL,
  `isavail` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`roomID`),
  UNIQUE KEY `roomID_UNIQUE` (`roomID`),
  KEY `fk_hotelID_idx` (`hotelID`),
  CONSTRAINT `fk_hotelID2` FOREIGN KEY (`hotelID`) REFERENCES `hotel2` (`hotelID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room2`
--

LOCK TABLES `room2` WRITE;
/*!40000 ALTER TABLE `room2` DISABLE KEYS */;
INSERT INTO `room2` VALUES (10,'2015-03-10','2015-03-10',1,0),(11,'2015-03-11','2015-03-11',1,0),(12,'2015-03-12','2015-03-12',1,0),(13,'2015-03-13','2015-03-13',1,0),(14,'2015-03-14','2015-03-14',1,0),(15,'2015-03-15','2015-03-15',1,0),(16,'2015-03-16','2015-03-16',1,0),(17,'2015-03-17','2015-03-17',1,0),(18,'2015-03-18','2015-03-18',1,0),(19,'2015-03-19','2015-03-19',1,0),(20,'2015-03-10','2015-03-10',2,0),(21,'2015-03-11','2015-03-11',2,0),(22,'2015-03-12','2015-03-12',2,0),(23,'2015-03-13','2015-03-13',2,0),(24,'2015-03-14','2015-03-14',2,0),(25,'2015-03-15','2015-03-15',2,0),(26,'2015-03-16','2015-03-16',2,0),(27,'2015-03-17','2015-03-17',2,0),(28,'2015-03-18','2015-03-18',2,0),(29,'2015-03-19','2015-03-19',2,0),(30,'2015-03-10','2015-03-10',3,0),(31,'2015-03-11','2015-03-11',3,0),(32,'2015-03-12','2015-03-12',3,0),(33,'2015-03-13','2015-03-13',3,0),(34,'2015-03-14','2015-03-14',3,0),(35,'2015-03-15','2015-03-15',3,0),(36,'2015-03-16','2015-03-16',3,0),(37,'2015-03-17','2015-03-17',3,0),(38,'2015-03-18','2015-03-18',3,0),(39,'2015-03-19','2015-03-19',3,0),(40,'2015-03-10','2015-03-10',4,0),(41,'2015-03-11','2015-03-11',4,0),(42,'2015-03-12','2015-03-12',4,0),(43,'2015-03-13','2015-03-13',4,0),(44,'2015-03-14','2015-03-14',4,0),(45,'2015-03-15','2015-03-15',4,0),(46,'2015-03-16','2015-03-16',4,0),(47,'2015-03-17','2015-03-17',4,0),(48,'2015-03-18','2015-03-18',4,0),(49,'2015-03-19','2015-03-19',4,0),(50,'2015-03-10','2015-03-10',5,0),(51,'2015-03-11','2015-03-11',5,0),(52,'2015-03-12','2015-03-12',5,0),(53,'2015-03-13','2015-03-13',5,0),(54,'2015-03-14','2015-03-14',5,0),(55,'2015-03-15','2015-03-15',5,0),(56,'2015-03-16','2015-03-16',5,0),(57,'2015-03-17','2015-03-17',5,0),(58,'2015-03-18','2015-03-18',5,0),(59,'2015-03-19','2015-03-19',5,0),(60,'2015-03-10','2015-03-10',6,0),(61,'2015-03-11','2015-03-11',6,0),(62,'2015-03-12','2015-03-12',6,0),(63,'2015-03-13','2015-03-13',6,0),(64,'2015-03-14','2015-03-14',6,0),(65,'2015-03-15','2015-03-15',6,0),(66,'2015-03-16','2015-03-16',6,0),(67,'2015-03-17','2015-03-17',6,0),(68,'2015-03-18','2015-03-18',6,0),(69,'2015-03-19','2015-03-19',6,0),(70,'2015-03-10','2015-03-10',7,0),(71,'2015-03-11','2015-03-11',7,0),(72,'2015-03-12','2015-03-12',7,0),(73,'2015-03-13','2015-03-13',7,0),(74,'2015-03-14','2015-03-14',7,0),(75,'2015-03-15','2015-03-15',7,0),(76,'2015-03-16','2015-03-16',7,0),(77,'2015-03-17','2015-03-17',7,0),(78,'2015-03-18','2015-03-18',7,0),(79,'2015-03-19','2015-03-19',7,0),(80,'2015-03-10','2015-03-10',8,0),(81,'2015-03-11','2015-03-11',8,0),(82,'2015-03-12','2015-03-12',8,0),(83,'2015-03-13','2015-03-13',8,0),(84,'2015-03-14','2015-03-14',8,0),(85,'2015-03-15','2015-03-15',8,0),(86,'2015-03-16','2015-03-16',8,0),(87,'2015-03-17','2015-03-17',8,0),(88,'2015-03-18','2015-03-18',8,0),(89,'2015-03-19','2015-03-19',8,0);
/*!40000 ALTER TABLE `room2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room3`
--

DROP TABLE IF EXISTS `room3`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room3` (
  `roomID` int(11) NOT NULL,
  `checkinDate` date NOT NULL,
  `checkoutDate` date NOT NULL,
  `hotelID` int(11) NOT NULL,
  `isavail` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`roomID`),
  UNIQUE KEY `roomID_UNIQUE` (`roomID`),
  KEY `fk_hotelID3_idx` (`hotelID`),
  CONSTRAINT `fk_hotelID3` FOREIGN KEY (`hotelID`) REFERENCES `hotel3` (`hotelID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room3`
--

LOCK TABLES `room3` WRITE;
/*!40000 ALTER TABLE `room3` DISABLE KEYS */;
INSERT INTO `room3` VALUES (10,'2015-03-10','2015-03-10',1,0),(11,'2015-03-11','2015-03-11',1,0),(12,'2015-03-12','2015-03-12',1,0),(13,'2015-03-13','2015-03-13',1,0),(14,'2015-03-14','2015-03-14',1,0),(15,'2015-03-15','2015-03-15',1,0),(16,'2015-03-16','2015-03-16',1,0),(17,'2015-03-17','2015-03-17',1,0),(18,'2015-03-18','2015-03-18',1,0),(19,'2015-03-19','2015-03-19',1,0),(20,'2015-03-10','2015-03-10',2,0),(21,'2015-03-11','2015-03-11',2,0),(22,'2015-03-12','2015-03-12',2,0),(23,'2015-03-13','2015-03-13',2,0),(24,'2015-03-14','2015-03-14',2,0),(25,'2015-03-15','2015-03-15',2,0),(26,'2015-03-16','2015-03-16',2,0),(27,'2015-03-17','2015-03-17',2,0),(28,'2015-03-18','2015-03-18',2,0),(29,'2015-03-19','2015-03-19',2,0),(30,'2015-03-10','2015-03-10',3,0),(31,'2015-03-11','2015-03-11',3,0),(32,'2015-03-12','2015-03-12',3,0),(33,'2015-03-13','2015-03-13',3,0),(34,'2015-03-14','2015-03-14',3,0),(35,'2015-03-15','2015-03-15',3,0),(36,'2015-03-16','2015-03-16',3,0),(37,'2015-03-17','2015-03-17',3,0),(38,'2015-03-18','2015-03-18',3,0),(39,'2015-03-19','2015-03-19',3,0),(40,'2015-03-10','2015-03-10',4,0),(41,'2015-03-11','2015-03-11',4,0),(42,'2015-03-12','2015-03-12',4,0),(43,'2015-03-13','2015-03-13',4,0),(44,'2015-03-14','2015-03-14',4,0),(45,'2015-03-15','2015-03-15',4,0),(46,'2015-03-16','2015-03-16',4,0),(47,'2015-03-17','2015-03-17',4,0),(48,'2015-03-18','2015-03-18',4,0),(49,'2015-03-19','2015-03-19',4,0),(50,'2015-03-10','2015-03-10',5,0),(51,'2015-03-11','2015-03-11',5,0),(52,'2015-03-12','2015-03-12',5,0),(53,'2015-03-13','2015-03-13',5,0),(54,'2015-03-14','2015-03-14',5,0),(55,'2015-03-15','2015-03-15',5,0),(56,'2015-03-16','2015-03-16',5,0),(57,'2015-03-17','2015-03-17',5,0),(58,'2015-03-18','2015-03-18',5,0),(59,'2015-03-19','2015-03-19',5,0),(60,'2015-03-10','2015-03-10',6,0),(61,'2015-03-11','2015-03-11',6,0),(62,'2015-03-12','2015-03-12',6,0),(63,'2015-03-13','2015-03-13',6,0),(64,'2015-03-14','2015-03-14',6,0),(65,'2015-03-15','2015-03-15',6,0),(66,'2015-03-16','2015-03-16',6,0),(67,'2015-03-17','2015-03-17',6,0),(68,'2015-03-18','2015-03-18',6,0),(69,'2015-03-19','2015-03-19',6,0),(70,'2015-03-10','2015-03-10',7,0),(71,'2015-03-11','2015-03-11',7,0),(72,'2015-03-12','2015-03-12',7,0),(73,'2015-03-13','2015-03-13',7,0),(74,'2015-03-14','2015-03-14',7,0),(75,'2015-03-15','2015-03-15',7,0),(76,'2015-03-16','2015-03-16',7,0),(77,'2015-03-17','2015-03-17',7,0),(78,'2015-03-18','2015-03-18',7,0),(79,'2015-03-19','2015-03-19',7,0),(80,'2015-03-10','2015-03-10',8,0),(81,'2015-03-11','2015-03-11',8,0),(82,'2015-03-12','2015-03-12',8,0),(83,'2015-03-13','2015-03-13',8,0),(84,'2015-03-14','2015-03-14',8,0),(85,'2015-03-15','2015-03-15',8,0),(86,'2015-03-16','2015-03-16',8,0),(87,'2015-03-17','2015-03-17',8,0),(88,'2015-03-18','2015-03-18',8,0),(89,'2015-03-19','2015-03-19',8,0);
/*!40000 ALTER TABLE `room3` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction1`
--

DROP TABLE IF EXISTS `transaction1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction1` (
  `tranID` varchar(255) NOT NULL,
  `roomID` int(11) NOT NULL,
  `bookDT` date NOT NULL,
  `bookerID` varchar(255) NOT NULL,
  `creditNO` varchar(255) NOT NULL,
  `hotelID` int(11) NOT NULL,
  PRIMARY KEY (`tranID`),
  UNIQUE KEY `TID_UNIQUE` (`tranID`),
  KEY `fk_roomID_idx` (`roomID`),
  KEY `fk_bookerID_idx` (`bookerID`),
  KEY `fk_hotelID_idx` (`hotelID`),
  CONSTRAINT `fk_hoteID` FOREIGN KEY (`hotelID`) REFERENCES `hotel1` (`hotelID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_roomID` FOREIGN KEY (`roomID`) REFERENCES `room1` (`roomID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction1`
--

LOCK TABLES `transaction1` WRITE;
/*!40000 ALTER TABLE `transaction1` DISABLE KEYS */;
INSERT INTO `transaction1` VALUES ('2015-02-112015-02-1355626346702',56,'2015-04-02','26346702','1234567890123456',5),('2015-02-132015-02-1555726346702',57,'2015-04-02','26346702','1234567890123456',5);
/*!40000 ALTER TABLE `transaction1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction2`
--

DROP TABLE IF EXISTS `transaction2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction2` (
  `tranID` varchar(255) NOT NULL,
  `roomID` int(11) NOT NULL,
  `bookDT` date NOT NULL,
  `bookerID` varchar(255) NOT NULL,
  `creditNO` varchar(255) NOT NULL,
  `hotelID` int(11) NOT NULL,
  PRIMARY KEY (`tranID`),
  UNIQUE KEY `tranID_UNIQUE` (`tranID`),
  KEY `fk_bookerID_idx` (`bookerID`),
  KEY `fk_hoteID_idx` (`hotelID`),
  KEY `fk_roomID_idx` (`roomID`),
  CONSTRAINT `fk_hoteID2` FOREIGN KEY (`hotelID`) REFERENCES `hotel2` (`hotelID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_roomID2` FOREIGN KEY (`roomID`) REFERENCES `room2` (`roomID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction2`
--

LOCK TABLES `transaction2` WRITE;
/*!40000 ALTER TABLE `transaction2` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaction2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction3`
--

DROP TABLE IF EXISTS `transaction3`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction3` (
  `tranID` varchar(255) NOT NULL,
  `roomID` int(11) NOT NULL,
  `bookDT` date NOT NULL,
  `bookerID` varchar(255) NOT NULL,
  `creditNO` varchar(255) NOT NULL,
  `hotelID` int(11) NOT NULL,
  PRIMARY KEY (`tranID`),
  UNIQUE KEY `tranID_UNIQUE` (`tranID`),
  KEY `fk_bookerID3_idx` (`bookerID`),
  KEY `fk_hoteID3_idx` (`hotelID`),
  KEY `fk_roomID3_idx` (`roomID`),
  CONSTRAINT `fk_hoteID3` FOREIGN KEY (`hotelID`) REFERENCES `hotel3` (`hotelID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_roomID3` FOREIGN KEY (`roomID`) REFERENCES `room3` (`roomID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction3`
--

LOCK TABLES `transaction3` WRITE;
/*!40000 ALTER TABLE `transaction3` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaction3` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-04-02 22:41:14
