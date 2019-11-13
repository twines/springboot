CREATE DATABASE  IF NOT EXISTS `lee` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `lee`;
-- MySQL dump 10.13  Distrib 8.0.12, for macos10.13 (x86_64)
--
-- Host: localhost    Database: lee
-- ------------------------------------------------------
-- Server version	8.0.12

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
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `company` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户id',
  `credit_code` varchar(255) DEFAULT NULL COMMENT '社会统一信用代码',
  `user_name` varchar(255) DEFAULT NULL COMMENT '法人姓名',
  `id_number` varchar(18) DEFAULT NULL COMMENT '法人身份证号',
  `bank_number` varchar(64) DEFAULT NULL COMMENT '企业银行卡号',
  `mobile` varchar(255) DEFAULT NULL COMMENT '联系电话',
  `card_a` varchar(255) DEFAULT NULL COMMENT '身份证正面',
  `card_b` varchar(255) DEFAULT NULL COMMENT '身份证反面',
  `business_license` varchar(255) DEFAULT NULL COMMENT '营业执照',
  `asset_proof` text COMMENT '企业资产证明，多个用英文逗号分割',
  `status` tinyint(1) DEFAULT '0' COMMENT '0未完善企业信息；1已经完善企业信息',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='企业基本信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `shipping`
--

DROP TABLE IF EXISTS `shipping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `shipping` (
  `id` int(255) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) unsigned NOT NULL COMMENT '用户id',
  `type` int(255) unsigned NOT NULL COMMENT '1货贷；2汇贷；3税贷；4结汇；5退税',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `shipping_images`
--

DROP TABLE IF EXISTS `shipping_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `shipping_images` (
  `id` int(255) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) unsigned NOT NULL COMMENT '用户id',
  `shipping_id` int(11) unsigned NOT NULL,
  `tax_bill` varchar(255) DEFAULT NULL COMMENT '税单',
  `customs_bill` varchar(255) DEFAULT NULL COMMENT '报关单',
  `logistics_bill` varchar(255) DEFAULT NULL COMMENT '物流单',
  `insurance_bill` varchar(255) DEFAULT NULL COMMENT '保险单\n',
  `trade_contract` varchar(255) DEFAULT NULL COMMENT '贸易合同',
  `exchange_img` varchar(255) DEFAULT NULL COMMENT '结汇证明\n',
  `tax_over` varchar(255) DEFAULT NULL COMMENT '完税证明\n',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `shipping_id` (`shipping_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='货贷列表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-13  9:56:37
