-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: thong_so_ky_thuat
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `hang_hoa`
--

DROP TABLE IF EXISTS `hang_hoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hang_hoa` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ma` varchar(10) DEFAULT NULL,
  `ma_giam_gia` varchar(10) DEFAULT NULL,
  `mo_ta` longtext CHARACTER SET utf8 COLLATE utf8_general_ci,
  `phan_tram_giam_gia` float DEFAULT NULL,
  `ten_hang_hoa` varchar(255) NOT NULL,
  `tich_diem` int DEFAULT NULL,
  `url_hinh_anh_1` varchar(255) DEFAULT NULL,
  `url_hinh_anh_2` varchar(255) DEFAULT NULL,
  `url_hinh_anh_3` varchar(255) DEFAULT NULL,
  `xoa` tinyint(1) DEFAULT NULL,
  `nhom_hang_id` int DEFAULT NULL,
  `thuong_hieu_id` int DEFAULT NULL,
  `ma_vach` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `hang_hoa_ibfk01_idx` (`nhom_hang_id`),
  KEY `hang_hoa_Thuong_hieu_ibfk02_idx` (`thuong_hieu_id`),
  CONSTRAINT `hang_hoa_Nhom_hang_ibfk01` FOREIGN KEY (`nhom_hang_id`) REFERENCES `nhom_hang` (`id`),
  CONSTRAINT `hang_hoa_Thuong_hieu_ibfk02` FOREIGN KEY (`thuong_hieu_id`) REFERENCES `thuong_hieu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=127 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hang_hoa`
--

LOCK TABLES `hang_hoa` WRITE;
/*!40000 ALTER TABLE `hang_hoa` DISABLE KEYS */;
INSERT INTO `hang_hoa` VALUES (1,'V00001','SALE1002','Giảm giá',10,'Xe VIOS',NULL,NULL,NULL,NULL,0,1,1,NULL),(126,'V00002','SALE1004',NULL,0,'Xe Camry 2.4G',NULL,NULL,NULL,NULL,1,1,1,NULL);
/*!40000 ALTER TABLE `hang_hoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hang_hoa_thong_so`
--

DROP TABLE IF EXISTS `hang_hoa_thong_so`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hang_hoa_thong_so` (
  `id` int NOT NULL AUTO_INCREMENT,
  `hang_hoa_id` int DEFAULT NULL,
  `thong_so_ki_thuat_id` int DEFAULT NULL,
  `thong_so_chi_tiet_id` int DEFAULT NULL,
  `gia_tri` text,
  `xoa` bit(1) NOT NULL,
  PRIMARY KEY (`id`,`xoa`),
  KEY `hang_hoa_thong_so_Hang_hoa_ibfk01_idx` (`hang_hoa_id`),
  KEY `hang_hoa_thong_so_Thong_so_ki_thuat_ibfk01_idx` (`thong_so_ki_thuat_id`),
  KEY `hang_hoa_thong_so_Thong_so_chi_tiet_ibfk03_idx` (`thong_so_chi_tiet_id`),
  CONSTRAINT `hang_hoa_thong_so_Hang_hoa_ibfk01` FOREIGN KEY (`hang_hoa_id`) REFERENCES `hang_hoa` (`id`),
  CONSTRAINT `hang_hoa_thong_so_Thong_so_chi_tiet_ibfk03` FOREIGN KEY (`thong_so_chi_tiet_id`) REFERENCES `thong_so_chi_tiet` (`id`),
  CONSTRAINT `hang_hoa_thong_so_Thong_so_ki_thuat_ibfk01` FOREIGN KEY (`thong_so_ki_thuat_id`) REFERENCES `thong_so_ki_thuat` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hang_hoa_thong_so`
--

LOCK TABLES `hang_hoa_thong_so` WRITE;
/*!40000 ALTER TABLE `hang_hoa_thong_so` DISABLE KEYS */;
INSERT INTO `hang_hoa_thong_so` VALUES (1,1,1,1,'2550',_binary '\0'),(2,1,1,2,'4425 x 1730 x 1475',_binary '\0'),(3,1,1,3,'1895 x 1420 x 1205',_binary '\0'),(5,1,1,1,'3000',_binary '');
/*!40000 ALTER TABLE `hang_hoa_thong_so` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhom_hang`
--

DROP TABLE IF EXISTS `nhom_hang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhom_hang` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ma_nhom_hang` varchar(255) DEFAULT NULL,
  `ten_nhom_hang` varchar(255) DEFAULT NULL,
  `xoa` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhom_hang`
--

LOCK TABLES `nhom_hang` WRITE;
/*!40000 ALTER TABLE `nhom_hang` DISABLE KEYS */;
INSERT INTO `nhom_hang` VALUES (1,'004','Sedan',0),(49,'002','SUV',0),(50,'003','SUV',1);
/*!40000 ALTER TABLE `nhom_hang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhom_thong_so`
--

DROP TABLE IF EXISTS `nhom_thong_so`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhom_thong_so` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ten_nhom_thong_so` varchar(45) DEFAULT NULL,
  `hang_hoa_id` int NOT NULL,
  `ma_nhom_thong_so` varchar(45) DEFAULT NULL,
  `xoa` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `nhom_thong_so_Hang_hoa_ibfk01_idx` (`hang_hoa_id`),
  CONSTRAINT `nhom_thong_so_Hang_hoa_ibfk01` FOREIGN KEY (`hang_hoa_id`) REFERENCES `hang_hoa` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhom_thong_so`
--

LOCK TABLES `nhom_thong_so` WRITE;
/*!40000 ALTER TABLE `nhom_thong_so` DISABLE KEYS */;
INSERT INTO `nhom_thong_so` VALUES (1,'Động cơ & Khung xe',1,'DK-VIOS-01',_binary '\0'),(2,'Ngoại thất ',1,'NT-VIOS-01',_binary '\0'),(3,'Nội thất',1,'NOT-VIOS-01',_binary '\0');
/*!40000 ALTER TABLE `nhom_thong_so` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thong_so_chi_tiet`
--

DROP TABLE IF EXISTS `thong_so_chi_tiet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thong_so_chi_tiet` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ten` varchar(255) DEFAULT NULL,
  `gia_tri` varchar(45) DEFAULT NULL,
  `thong_so_ki_thuat_id` int DEFAULT NULL,
  `xoa` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `Thong_so_chi_tiet_Thong_so_ki_thuat_ibfk01_idx` (`thong_so_ki_thuat_id`),
  CONSTRAINT `Thong_so_chi_tiet_Thong_so_ki_thuat_ibfk01` FOREIGN KEY (`thong_so_ki_thuat_id`) REFERENCES `thong_so_ki_thuat` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thong_so_chi_tiet`
--

LOCK TABLES `thong_so_chi_tiet` WRITE;
/*!40000 ALTER TABLE `thong_so_chi_tiet` DISABLE KEYS */;
INSERT INTO `thong_so_chi_tiet` VALUES (1,'Chiều dài cơ sở (mm)',NULL,1,_binary '\0'),(2,'Kích thước tổng thể bên ngoài (D x R x C) (mm x mm x mm)',NULL,1,_binary '\0'),(3,'Kích thước tổng thể bên trong (D x R x C) (mm x mm x mm)',NULL,1,_binary '\0'),(5,'ffffdsd','fffff',1,_binary '');
/*!40000 ALTER TABLE `thong_so_chi_tiet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thong_so_ki_thuat`
--

DROP TABLE IF EXISTS `thong_so_ki_thuat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thong_so_ki_thuat` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ten_thong_so` varchar(255) DEFAULT NULL,
  `nhom_hang_id` int DEFAULT NULL,
  `xoa` bit(1) DEFAULT NULL,
  `nhom_thong_so_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `Thong_so_ki_thuat_Nhom_hang_idx` (`nhom_hang_id`),
  KEY `Thong_so_ki_thuat_Nhom_thong_so_ibfk02_idx` (`nhom_thong_so_id`),
  CONSTRAINT `Thong_so_ki_thuat_Nhom_hang` FOREIGN KEY (`nhom_hang_id`) REFERENCES `nhom_hang` (`id`),
  CONSTRAINT `Thong_so_ki_thuat_Nhom_thong_so_ibfk02` FOREIGN KEY (`nhom_thong_so_id`) REFERENCES `nhom_thong_so` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thong_so_ki_thuat`
--

LOCK TABLES `thong_so_ki_thuat` WRITE;
/*!40000 ALTER TABLE `thong_so_ki_thuat` DISABLE KEYS */;
INSERT INTO `thong_so_ki_thuat` VALUES (1,'Kích Thước',1,_binary '\0',1),(2,'Động cơ xăng',1,_binary '\0',1),(3,'Chế độ lái (công suất cao/tiết kiệm nhiên liệu)',1,_binary '\0',1),(5,'fasddfasdasdasd',1,_binary '',1);
/*!40000 ALTER TABLE `thong_so_ki_thuat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thuong_hieu`
--

DROP TABLE IF EXISTS `thuong_hieu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thuong_hieu` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ten_thuong_hieu` varchar(255) NOT NULL,
  `xoa` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thuong_hieu`
--

LOCK TABLES `thuong_hieu` WRITE;
/*!40000 ALTER TABLE `thuong_hieu` DISABLE KEYS */;
INSERT INTO `thuong_hieu` VALUES (1,'VIOS 1.5E MT',0),(2,'CAMRY 2.0 ',0),(76,'CAMRY 2.4G',0),(79,'Fortuner 2.0',0);
/*!40000 ALTER TABLE `thuong_hieu` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-03 20:40:52
