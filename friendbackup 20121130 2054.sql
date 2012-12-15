-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.5.28


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema friendbackup2
--

CREATE DATABASE IF NOT EXISTS friendbackup2;
USE friendbackup2;

--
-- Definition of table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `password` varchar(32) NOT NULL,
  `accesscode` varchar(45) DEFAULT NULL,
  `linkedinaccess` text,
  `qqaccess` text,
  `renrenaccess` text,
  `weiboaccess` text,
  `doubanaccess` text,
  `regtime` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`,`email`,`password`,`accesscode`,`linkedinaccess`,`qqaccess`,`renrenaccess`,`weiboaccess`,`doubanaccess`,`regtime`) VALUES 
 (41,'yjy910323@gmail.com','f5bb0c8de146c67b44babbf4e6584cc0',NULL,'¬í\0sr\0org.scribe.model.Token	ì1Üös\0L\0rawResponset\0Ljava/lang/String;L\0secretq\0~\0L\0tokenq\0~\0xpt\0oauth_token=8f591038-a08a-4e45-aa9b-c61f0a8e7c64&oauth_token_secret=8cab340d-3946-4558-922d-56445b2ba246&oauth_expires_in=0&oauth_authorization_expires_in=0t\0$8cab340d-3946-4558-922d-56445b2ba246t\0$8f591038-a08a-4e45-aa9b-c61f0a8e7c64',NULL,NULL,NULL,NULL,'2012-11-30 18:10:52'),
 (42,'123@123.com','f5bb0c8de146c67b44babbf4e6584cc0',NULL,'¬í\0sr\0org.scribe.model.Token	ì1Üös\0L\0rawResponset\0Ljava/lang/String;L\0secretq\0~\0L\0tokenq\0~\0xpt\0oauth_token=8f591038-a08a-4e45-aa9b-c61f0a8e7c64&oauth_token_secret=8cab340d-3946-4558-922d-56445b2ba246&oauth_expires_in=0&oauth_authorization_expires_in=0t\0$8cab340d-3946-4558-922d-56445b2ba246t\0$8f591038-a08a-4e45-aa9b-c61f0a8e7c64',NULL,NULL,NULL,NULL,'2012-11-30 19:37:22');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
