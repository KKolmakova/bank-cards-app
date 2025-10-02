CREATE DATABASE  IF NOT EXISTS `bankdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `bankdb`;
-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bankdb
-- ------------------------------------------------------
-- Server version	8.4.6

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `block_card_request`
--

DROP TABLE IF EXISTS `block_card_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `block_card_request` (
  `id` int NOT NULL AUTO_INCREMENT,
  `card_id` int NOT NULL,
  `user_id` int NOT NULL,
  `status_id` int NOT NULL,
  `creation_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_request_card` (`card_id`),
  KEY `fk_request_user` (`user_id`),
  KEY `fk_request_status` (`status_id`),
  CONSTRAINT `fk_request_card` FOREIGN KEY (`card_id`) REFERENCES `card` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_request_status` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`),
  CONSTRAINT `fk_request_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `block_card_request`
--

LOCK TABLES `block_card_request` WRITE;
/*!40000 ALTER TABLE `block_card_request` DISABLE KEYS */;
INSERT INTO `block_card_request` VALUES (4,1,2,6,'2025-09-29 21:47:06'),(5,2,2,4,'2025-09-29 21:56:04'),(6,3,3,5,'2025-09-29 21:56:04'),(7,4,4,4,'2025-09-29 21:56:04'),(8,5,5,5,'2025-09-29 21:56:04'),(9,6,6,5,'2025-09-29 21:56:04'),(10,7,7,4,'2025-09-29 21:56:04'),(11,8,8,5,'2025-09-29 21:56:04'),(12,9,9,6,'2025-09-29 21:56:04'),(13,10,10,5,'2025-09-29 21:56:04'),(14,11,2,5,'2025-09-29 21:56:04'),(15,12,3,5,'2025-09-29 21:56:04'),(16,13,4,5,'2025-09-29 21:56:04'),(17,14,5,4,'2025-09-29 21:56:04'),(18,15,6,5,'2025-09-29 21:56:04'),(19,16,7,4,'2025-09-29 21:56:04'),(20,17,8,5,'2025-09-29 21:56:04');
/*!40000 ALTER TABLE `block_card_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `card`
--

DROP TABLE IF EXISTS `card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `card` (
  `id` int NOT NULL AUTO_INCREMENT,
  `number` text,
  `balance` decimal(10,2) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `expire` text,
  `status_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `card_users_FK` (`user_id`),
  KEY `card_status_FK` (`status_id`),
  CONSTRAINT `card_status_FK` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`),
  CONSTRAINT `card_users_FK` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `card`
--

LOCK TABLES `card` WRITE;
/*!40000 ALTER TABLE `card` DISABLE KEYS */;
INSERT INTO `card` VALUES (1,'1234567891234567',676.00,2,'05/27',2),(2,'4532789123456781',5115.45,2,'10/2025',1),(3,'5379123487654320',2340.12,3,'11/2025',2),(4,'6012345678901234',9876.55,4,'12/2025',3),(5,'4532765498761230',1200.00,5,'01/2026',1),(6,'5278123498764501',450.50,6,'02/2026',2),(7,'6011122233334445',7890.78,7,'03/2026',3),(8,'4532765498123456',3500.20,8,'04/2026',1),(9,'5379123487654321',670.00,9,'05/2026',1),(10,'6012345678904321',890.90,10,'06/2026',3),(11,'4532789123451234',4300.45,2,'07/2026',1),(12,'5379123487651234',2750.33,3,'08/2026',2),(13,'6012345678905678',9980.12,4,'09/2026',3),(14,'4532765498765678',150.45,5,'10/2026',1),(15,'5278123498765678',670.89,6,'10/2026',2),(16,'6011122233335678',7200.00,7,'12/2026',3),(17,'4532765498125678',3050.75,8,'01/2027',1),(18,'5379123487656789',880.88,9,'02/2027',2),(19,'6012345678906789',9999.99,10,'03/2027',3),(20,'4532789123456789',1238.12,2,'04/2027',1),(21,'5379123487654322',4500.50,3,'05/2027',2),(22,'6012345678904322',780.78,4,'06/2027',3),(23,'4532765498123457',6700.20,5,'07/2027',1),(24,'5278123498764502',340.00,6,'08/2027',2),(25,'6011122233334446',1234.56,7,'09/2027',3),(26,'4532765498123458',5600.20,8,'10/2027',1),(27,'5379123487654323',890.12,9,'11/2027',2),(28,'6012345678904323',4300.00,10,'12/2027',3),(29,'4532789123456782',230.45,2,'01/2028',1),(30,'5379123487654324',780.67,3,'02/2028',2),(31,'6012345678904324',999.99,4,'03/2028',3),(35,'4532999988887777',1000.00,2,'12/2028',1);
/*!40000 ALTER TABLE `card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'admin'),(2,'user');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'activ'),(2,'blocked'),(3,'expired'),(4,'pending'),(5,'approved'),(6,'rejected');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_id` int DEFAULT NULL,
  `name` text,
  `password` text,
  `enabled` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `users_role_FK` (`role_id`),
  CONSTRAINT `users_role_FK` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,1,'admin','$2a$10$9eQ/40k6CoxHfzimHCyot.lqCNyH3ShZ3nAt41jX6G8an1e8x4pGe',1),(2,2,'ksenia','$2a$10$nIZAdkU9fUNeqn91lTjJwuz2TNWN9pycqQOxUFca0CdsausJB1F3K',1),(3,2,'anna','$2a$10$p2TDb1swX9LaFspp5.GpxudoofSgpFRmlIR3rIpvNZ3Xqwc7zOGBa',1),(4,2,'ivan','$2a$10$nxXLqfdklB4VmeNntdDHium.cQHWo.0jA/7wQurG0M9Rt2AvjpMLO',1),(5,2,'olga','$2a$10$Vp5GVbrdwe8/EVbpKaY/GO5i4oZyHtW8tBS1LpT8Li43Lf/xrynem',1),(6,2,'petr','$2a$10$R1sMJoQDLvZlSYd2aQh2pOvKcP7W50OJfHE6xglyYy88o./Jmotr6',0),(7,2,'nina','$2a$10$kyoWLbtx4Zq5uiVhdBuLx./xMO1SJM4XNCrosD3mnNoHAuO5ze6Q6',1),(8,2,'igor','$2a$10$hSXxfH4RrzlicSufXhKzXeqospuTIJgboJX3pj/Onk92UGXXj20Li',1),(9,2,'lena','$2a$10$jpM8V7ytOe.dkFD2JmVu3uL4i4I3FW3WdgBTfmxGEXdo6V8bnv0e6',1),(10,2,'kirill','$2a$10$4LB7fBW2YGBqoCG8af4ksul5AZI1G4/h/vamRSxbu.s4uYxxMc/qK',1),(19,2,'tima','$2a$10$.lw7rDvti1ZZWX918RHaPezxXWIG.7dItGR1AdotRqmuZ6xETX/S6',1),(20,2,'oleg','$2a$10$wVrY6DwGEM7gXNLHnRse6uqH4pdFV3MrWNwZgYLZtgi8TJIUjdtgS',1);
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

-- Dump completed on 2025-10-02  3:46:22
