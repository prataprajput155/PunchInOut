-- MySQL dump 10.13  Distrib 5.7.28, for Linux (x86_64)
--
-- Host: localhost    Database: punchInOut
-- ------------------------------------------------------
-- Server version	5.7.28

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
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'prataprajput155@gmail.com','pratap'),(2,'amitagarwal@ongraph.com','amit');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_daily_punch_data`
--

DROP TABLE IF EXISTS `employee_daily_punch_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee_daily_punch_data` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `punch_count` int(11) DEFAULT NULL,
  `punch_day` date DEFAULT NULL,
  `emp_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKi3g6bkexl0ugndbp1c9gjlb19` (`emp_id`),
  CONSTRAINT `FKi3g6bkexl0ugndbp1c9gjlb19` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_daily_punch_data`
--

LOCK TABLES `employee_daily_punch_data` WRITE;
/*!40000 ALTER TABLE `employee_daily_punch_data` DISABLE KEYS */;
INSERT INTO `employee_daily_punch_data` VALUES (1,8,'2019-12-31',2),(2,8,'2019-12-31',1),(3,8,'2020-01-02',1),(4,8,'2020-01-02',2);
/*!40000 ALTER TABLE `employee_daily_punch_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `punch_clock_data`
--

DROP TABLE IF EXISTS `punch_clock_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `punch_clock_data` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `punch_day` date DEFAULT NULL,
  `punch_time` datetime(6) DEFAULT NULL,
  `shift` int(11) DEFAULT NULL,
  `emp_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKharqeee65ogrkm9eb1m8qcmye` (`emp_id`),
  CONSTRAINT `FKharqeee65ogrkm9eb1m8qcmye` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `punch_clock_data`
--

LOCK TABLES `punch_clock_data` WRITE;
/*!40000 ALTER TABLE `punch_clock_data` DISABLE KEYS */;
INSERT INTO `punch_clock_data` VALUES (1,'2019-12-31','2019-12-31 08:33:06.815000',1,2),(2,'2019-12-31','2019-12-31 08:33:08.151000',1,2),(3,'2019-12-31','2019-12-31 08:33:09.312000',1,2),(4,'2019-12-31','2019-12-31 08:33:10.416000',1,2),(5,'2019-12-31','2019-12-31 08:33:42.342000',2,2),(6,'2019-12-31','2019-12-31 08:33:43.759000',2,2),(7,'2019-12-31','2019-12-31 08:33:44.967000',2,2),(8,'2019-12-31','2019-12-31 08:33:46.123000',2,2),(9,'2019-12-31','2019-12-31 08:44:59.231000',1,1),(10,'2019-12-31','2019-12-31 08:45:21.217000',1,1),(11,'2019-12-31','2019-12-31 08:45:23.934000',1,1),(13,'2019-12-31','2019-12-31 08:48:29.511000',1,1),(14,'2019-12-31','2019-12-31 09:45:46.327000',2,1),(15,'2019-12-31','2019-12-31 09:45:54.630000',2,1),(16,'2019-12-31','2019-12-31 09:45:57.451000',2,1),(17,'2019-12-31','2019-12-31 09:56:10.189000',2,1),(18,'2020-01-02','2020-01-02 07:50:54.588000',1,1),(19,'2020-01-02','2020-01-02 08:17:18.979000',1,1),(20,'2020-01-02','2020-01-02 08:26:16.955000',1,1),(21,'2020-01-02','2020-01-02 08:26:19.661000',1,1),(22,'2020-01-02','2020-01-02 08:35:25.422000',2,1),(23,'2020-01-02','2020-01-02 08:35:38.580000',2,1),(24,'2020-01-02','2020-01-02 08:35:39.820000',2,1),(25,'2020-01-02','2020-01-02 08:35:41.015000',2,1),(26,'2020-01-02','2020-01-02 10:53:55.668000',1,2),(27,'2020-01-02','2020-01-02 10:55:52.060000',1,2),(28,'2020-01-02','2020-01-02 10:56:04.210000',1,2),(29,'2020-01-02','2020-01-02 10:56:12.612000',1,2),(30,'2020-01-02','2020-01-02 10:57:24.176000',2,2),(31,'2020-01-02','2020-01-02 10:59:16.869000',2,2),(32,'2020-01-02','2020-01-02 10:59:40.078000',2,2),(33,'2020-01-02','2020-01-02 12:59:20.628000',2,2);
/*!40000 ALTER TABLE `punch_clock_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `work_hours`
--

DROP TABLE IF EXISTS `work_hours`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `work_hours` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `day` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `emp_id` bigint(20) DEFAULT NULL,
  `shift` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcdabigq9p0c8q95b26ed1e1tr` (`emp_id`),
  CONSTRAINT `FKcdabigq9p0c8q95b26ed1e1tr` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `work_hours`
--

LOCK TABLES `work_hours` WRITE;
/*!40000 ALTER TABLE `work_hours` DISABLE KEYS */;
INSERT INTO `work_hours` VALUES (1,'MON','9:00-10:00',1,1),(2,'TUE','10:00-14:00',1,1),(3,'WED','08:10-10:00',1,1),(4,'THU','10:00-14:00',1,1),(5,'FRI','12:00-08:00',1,1),(6,'SAT','',1,1),(7,'SUN','10:00-15:00',1,1),(8,'MON','15:30-20:00',1,2),(9,'TUE','15:00-21:47',1,2),(10,'WED','14:02-22:30',1,2),(11,'THU','14:02-22:30',1,2),(12,'FRI','15:00-21:30',1,2),(13,'SAT','',1,2),(14,'SUN','20:00-23:30',1,2),(15,'MON','9:00-14:00',2,1),(16,'TUE','10:00-14:00',2,1),(17,'WED','08:10-10:00',2,1),(18,'THU','',2,1),(19,'FRI','12:00-08:00',2,1),(20,'SAT','',2,1),(21,'SUN','10:00-15:00',2,1),(22,'MON','15:30-20:00',2,2),(23,'TUE','14:00-21:47',2,2),(24,'WED','17:30-22:30',2,2),(25,'THU','16:00-22:30',2,2),(26,'FRI','15:00-21:30',2,2),(27,'SAT','',2,2),(28,'SUN','20:00-23:30',2,2);
/*!40000 ALTER TABLE `work_hours` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-01-02 18:46:14
