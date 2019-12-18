-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: emarket
-- ------------------------------------------------------
-- Server version	8.0.18

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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `category_id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Mac','mac.jpg'),(2,'iPhone','iphone.jpg'),(3,'iPad','ipad.jpeg'),(4,'Accessories','1.jpg');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `customer_id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `city_region` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `cc_number` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_order`
--

DROP TABLE IF EXISTS `customer_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_order` (
  `order_id` int(11) NOT NULL,
  `amount` double DEFAULT NULL,
  `date_created` date DEFAULT NULL,
  `confirmation_number` int(11) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `customer_id_idx` (`customer_id`),
  CONSTRAINT `customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_order`
--

LOCK TABLES `customer_order` WRITE;
/*!40000 ALTER TABLE `customer_order` DISABLE KEYS */;
INSERT INTO `customer_order` VALUES (1,504,'2003-12-15',760995850,3),(2,504,'2003-12-15',312205968,4),(3,504,'2003-12-15',898886796,5),(4,504,'2003-12-15',22072900,6),(5,504,'2003-12-15',191171242,7);
/*!40000 ALTER TABLE `customer_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordered_product`
--

DROP TABLE IF EXISTS `ordered_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordered_product` (
  `order_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_id`,`product_id`),
  KEY `product_id_idx` (`product_id`),
  CONSTRAINT `order_id` FOREIGN KEY (`order_id`) REFERENCES `customer_order` (`order_id`),
  CONSTRAINT `product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordered_product`
--

LOCK TABLES `ordered_product` WRITE;
/*!40000 ALTER TABLE `ordered_product` DISABLE KEYS */;
INSERT INTO `ordered_product` VALUES (1,5,1),(2,5,1),(3,5,1),(4,5,1),(5,5,1);
/*!40000 ALTER TABLE `ordered_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `product_id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `price` double DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_update` date DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `thumb_image` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `description_detail` varchar(1000) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  KEY `category_id_idx` (`category_id`),
  CONSTRAINT `category_id` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (5,'Mac Mini MGEM2',499,'Garanty 2 years. Full Box and accessories','2015-11-26',1,'2.jpg','thumb2.jpg','Mac mini is an affordable powerhouse that packs the entire Mac experience into a 7.7-inch-square frame. Just connect your own display, keyboard and mouse and you\'re ready to make big things happen.'),(6,'iPhone 6S',399,'Garanty 1 year. Full Box. Made in USA','2015-11-25',2,'3.jpg','thumb3.jpg','The moment you use iPhone 6s, you know you\'re never felt anything like it. With a single press, 3D Touch lets you do more than ever before. Live Photos bring your memories to life in a powerful vivid way.'),(7,'Macbook Air 11 inch',899,'Garanty 1 year. Up to 2 year with 10$. Full box.','2015-11-25',1,'4.jpg','thumb4.jpg','The 11-inch MacBook Air lasts up to 9 hours between charges and the 13-inch model lasts up to an incredible 12 hours. So from your morning coffee till your evening commute, you can work unplugged. When it’s time to kick back and relax, you can get up to 10 hours of iTunes movie playback on the 11-inch model and up to 12 hours on the 13-inch model.'),(8,'iPod',199,'Using iPod for listening music. Garanty 1 year.','2015-11-25',4,'5.jpg','thumb5.jpg','iPod touch is the perfect way to carry your music collection in your pocket. With the iTunes Store — the world’s largest music catalog — you can load up your iPod touch with your favorite songs. And thanks to iCloud, everything you purchase through the iTunes Store is automatically available on all your devices for free.'),(9,'USB SuperDrive',79,'USB Disk. Garanty 1 year. Full Box. Made in China Apple.','2015-11-25',4,'1.jpg','thumb1.jpg','Whether you’re at the office or on the road, you can play and burn both CDs and DVDs with the Apple USB SuperDrive. It’s perfect when you want to watch a DVD movie, install software, create backup discs, and more.'),(10,'iMac 21.5 inch',1099,'Brand new. Full box. Garanty 2 years.','2015-11-27',1,'imac-2012-1.jpg',NULL,'21.5‑inch (diagonal) LED‑backlit display. 1.6GHz dual-core or 2.8GHz quad-core Intel Core i5 processor Turbo Boost up to 3.3GHz. 1TB 5400‑rpm hard drive; 1TB or 2TB Fusion Drive; or 256GB flash storage (SSD). Magic Keyboard and Magic Mouse 2 or Magic Trackpad 2'),(11,'Ipad Mini 2013',499,'Every iPad mini comes with complimentary telephone technical support for 90 days from your iPad mini purchase date and a one-year limited warranty','2015-11-26',3,'ipad-mn-2.jpg',NULL,'The powerful and power-efficient A7 chip with 64-bit architecture makes everything remarkably responsive — while still delivering up to 10 hours of battery life'),(12,'ipad mini 4',499,'The new ipad version 2014, with three colors: white, silver and black. Full box and warranty 1 year.','2015-11-29',3,'ipad-mn-1.png',NULL,NULL),(13,'Iphone 6',600,'The iphone 6','2015-12-03',1,'iphone-6s-rose-gold-1.jpg',NULL,NULL),(14,'123',123,'12','3902-03-03',1,'12',NULL,'12');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_detail`
--

DROP TABLE IF EXISTS `product_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_detail` (
  `product_id` int(11) NOT NULL,
  `information` varchar(1000) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `image1` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `image2` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `image3` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `image4` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `image5` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `accessories` varchar(2000) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `guaranty` varchar(2000) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  CONSTRAINT `product_detail_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_detail`
--

LOCK TABLES `product_detail` WRITE;
/*!40000 ALTER TABLE `product_detail` DISABLE KEYS */;
INSERT INTO `product_detail` VALUES (5,'CPU: 2.6GHz dual-core Intel Core i5 (Turbo Boost up to 3.1GHz) with 3MB on-chip shared L3 cache. HDD: 1TB (5400-rpm) hard drive. RAM: 8GB of 1600MHz LPDDR3 memory. ','mac_mini_1.jpg','mac_mini_2.jpg','mac_mini_3.jpg','mac_mini_4.png','mac_mini_5.jpg','Full box','2 years all over the world.'),(6,'Available in gold, silver, space gray, and rose gold, iPhone 6s features an A9 chip, 3D Touch, ultrafast LTE Advanced wireless, Touch ID, a 12MP iSight camera, and iOS 9. 4.7-inch (diagonal) LED-backlit widescreen next-generation Multi‑Touch display with IPS technology and Taptic Engine.1334-by-750-pixel resolution at 326 ppi. ','iphone-6s-rose-gold-1.jpg','iphone-6s-rose-gold-2.jpeg','iphone-6s-rose-gold-3.jpg','iphone-6s-rose-gold-4.jpg','iphone-6s-rose-gold-5.jpg','Full box','1 year.'),(7,'12‑inch (diagonal) LED‑backlit Retina display. 1.1GHz, 1.2GHz, or 1.3GHz dual‑core Intel Core M processor\nTurbo Boost up to 2.9GHz.Up to 9 hours battery life. Up to 512GB flash storage. 2.03 pounds.','mac-air-1.jpg','mac-air-2.jpg','mac-air-3.jpg','mac-air-4.jpg','mac-air-5.jpg','Full box.',NULL),(8,'iPod touch is the perfect way to carry your music collection in your pocket. With the iTunes Store — the world’s largest music catalog — you can load up your iPod touch with your favorite songs. And thanks to iCloud, everything you purchase through the iTunes Store is automatically available on all your devices for free.','ipod-classic-1.jpeg','ipod-classic-2.jpg','ipod-classic-3.jpg','ipod-classic-4.jpg','ipod-classic-5.jpg','Full box',NULL),(9,'The sleek, compact USB SuperDrive. Only slightly bigger than a CD case, the Apple USB SuperDrive slips easily into your travel bag when you hit the road and takes up little space on your desk or tray table when you’re working. You’ll never have to worry about lost cables with the Apple USB SuperDrive. It connects to your MacBook Pro with Retina display, MacBook Air, iMac, or Mac mini with a single USB cable that’s built into the SuperDrive. There’s no separate power adapter, and it works whether your Mac is plugged in or running on battery power. ','usb-1.jpg','usb-2.jpg','usb-3.jpg','usb-4.jpg','usb-5.jpeg','Full box','1 year'),(10,'21.5‑inch (diagonal) LED‑backlit display. 1.6GHz dual-core or 2.8GHz quad-core Intel Core i5 processor Turbo Boost up to 3.3GHz. 1TB 5400‑rpm hard drive; 1TB or 2TB Fusion Drive; or 256GB flash storage (SSD). Magic Keyboard and Magic Mouse 2 or Magic Trackpad 2.','imac-2012-1.jpg','imac-2012-2.jpg','imac-2012-3.jpg','imac-2012-4.jpg','imac-2012-5.jpg','Full box','2 years'),(11,'Everything runs better on iPad mini 4. And thanks to the A8 chip, graphics‑intensive apps are, well, intense. Video editing, modeling and design apps, and games — especially games — come to life, with incredible responsiveness, fluid motion, and fleet performance quality.','ipad-mn-1.png','ipad-mn-2.jpg','ipad-mn-3.jpg','ipad-mn-4.jpg','ipad-mn-5.jpg','Full box.','1 year.'),(12,'<p><em>Everything runs better on iPad mini 4. And thanks to the A8 chip, graphics, intensive apps are, well, intense.&nbsp;</em></p>\r\n\r\n<p><em>Video editing, modeling and design apps, and games &mdash; especially games &mdash; come to life, with incredible responsiveness, fluid motion, and fleet performance quality.</em></p>\r\n\r\n<p><em>The powerful and power-efficient A8&nbsp;chip with 64-bit architecture makes everything remarkably responsive &mdash; while still delivering up to 10 hours of battery life</em></p>\r\n','ipad-mn-1.png','ipad-mn-2.jpg','ipad-mn-3.jpg','ipad-mn-4.jpg','ipad-mn-5.jpg','<ul style=\"list-style-type:none\">\r\n	<li>1 year at Apple Store</li>\r\n	<li>Brand new and Full Box</li>\r\n</ul>\r\n','<ul style=\"list-style-type:none\">\r\n	<li>ipad mini 4</li>\r\n	<li>Charging cable</li>\r\n	<li>Adapter</li>\r\n</ul>\r\n'),(13,'<p>fdfdffd</p>\r\n','iphone-6s-rose-gold-1.jpg','iphone-6s-rose-gold-2.jpg','iphone-6s-rose-gold-3.jpg','iphone-6s-rose-gold-4.jpg','iphone-6s-rose-gold-5.jpg','','');
/*!40000 ALTER TABLE `product_detail` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-15 23:37:20
