-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: exam
-- ------------------------------------------------------
-- Server version	5.5.19

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
-- Table structure for table `exam`
--

DROP TABLE IF EXISTS `exam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exam` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `testpaper_id` int(11) NOT NULL,
  `start_time` datetime DEFAULT NULL,
  `total_student_qty` int(11) NOT NULL DEFAULT '0',
  `exam_student_qty` int(11) NOT NULL DEFAULT '0',
  `end_time` datetime DEFAULT NULL,
  `finish_student_qty` int(11) NOT NULL DEFAULT '0',
  `status` varchar(45) NOT NULL,
  `is_deleted` bit(1) NOT NULL DEFAULT b'0',
  `total_score` int(11) DEFAULT '100',
  `avg_score` decimal(18,2) DEFAULT NULL,
  `type` varchar(45) DEFAULT '考试',
  `public_answer` varchar(45) DEFAULT NULL,
  `subject_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_testpaper_exam` (`testpaper_id`),
  CONSTRAINT `fk_testpaper_exam` FOREIGN KEY (`testpaper_id`) REFERENCES `testpaper` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exam`
--

LOCK TABLES `exam` WRITE;
/*!40000 ALTER TABLE `exam` DISABLE KEYS */;
INSERT INTO `exam` VALUES (6,'数学1',12,'2018-03-10 23:30:50',0,0,'2018-03-10 23:30:50',0,'未发布','',100,NULL,NULL,NULL,1),(7,'考试1',15,'2018-03-22 00:09:00',0,0,'2018-03-22 00:09:00',0,'未发布','\0',100,NULL,'考试',NULL,NULL);
/*!40000 ALTER TABLE `exam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grade`
--

DROP TABLE IF EXISTS `grade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `is_deleted` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grade`
--

LOCK TABLES `grade` WRITE;
/*!40000 ALTER TABLE `grade` DISABLE KEYS */;
INSERT INTO `grade` VALUES (1,'一年级','\0'),(2,'二年级','\0');
/*!40000 ALTER TABLE `grade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `name` varchar(2000) NOT NULL,
  `type` varchar(45) NOT NULL,
  `subject_id` int(11) DEFAULT NULL,
  `a` varchar(2000) DEFAULT NULL,
  `b` varchar(2000) DEFAULT NULL,
  `c` varchar(2000) DEFAULT NULL,
  `d` varchar(2000) DEFAULT NULL,
  `correct` varchar(2000) NOT NULL,
  `is_deleted` bit(1) NOT NULL DEFAULT b'0',
  `score` int(11) NOT NULL DEFAULT '0',
  `explain_text` varchar(2000) DEFAULT NULL,
  `difficult` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_question` (`user_id`),
  KEY `fk_subject_question` (`subject_id`),
  CONSTRAINT `fk_subject_question` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`),
  CONSTRAINT `fk_user_question` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (7,1,'选择题','选择题',1,'AAA','BBB','XCC','DDD','B','\0',0,'选B就对了',NULL),(8,1,'减法选择1','选择题',4,'11','22','33','44','A','\0',0,'答案是11',NULL),(9,1,'判断题','判断题',5,'','','','','正确','\0',0,'对的',NULL),(10,1,'除法','选择题',6,'AA','BB','CC','','B','\0',0,'选择B就对了',2),(11,1,'BBBB','判断题',1,'','','','','正确','\0',0,'这是对的',1);
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question_in_testpaper`
--

DROP TABLE IF EXISTS `question_in_testpaper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question_in_testpaper` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `testpaper_id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  `is_deleted` bit(1) NOT NULL DEFAULT b'0',
  `score` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_question_question_in_testpaper` (`question_id`),
  KEY `fk_testpaper_questions` (`testpaper_id`),
  CONSTRAINT `fk_question_question_in_testpaper` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`),
  CONSTRAINT `fk_testpaper_questions` FOREIGN KEY (`testpaper_id`) REFERENCES `testpaper` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_in_testpaper`
--

LOCK TABLES `question_in_testpaper` WRITE;
/*!40000 ALTER TABLE `question_in_testpaper` DISABLE KEYS */;
INSERT INTO `question_in_testpaper` VALUES (25,12,7,'\0',30),(26,12,8,'\0',30),(27,12,9,'\0',40),(28,13,7,'\0',25),(29,13,8,'\0',25),(30,13,9,'\0',50),(31,14,7,'\0',20),(32,14,11,'\0',40),(33,14,9,'\0',40),(34,15,7,'\0',40),(35,15,8,'\0',40),(36,15,11,'\0',20);
/*!40000 ALTER TABLE `question_in_testpaper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `school`
--

DROP TABLE IF EXISTS `school`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `school` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `is_deleted` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `school`
--

LOCK TABLES `school` WRITE;
/*!40000 ALTER TABLE `school` DISABLE KEYS */;
INSERT INTO `school` VALUES (1,'清华大学','\0');
/*!40000 ALTER TABLE `school` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subject` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `is_deleted` bit(1) NOT NULL DEFAULT b'0',
  `school_id` int(11) DEFAULT NULL,
  `intro` varchar(2000) DEFAULT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  `grade_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_School_Subject` (`school_id`),
  KEY `FK_Subject_Teacher` (`teacher_id`),
  KEY `fk_grade_subject_idx` (`grade_id`),
  CONSTRAINT `fk_grade_subject` FOREIGN KEY (`grade_id`) REFERENCES `grade` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_School_Subject` FOREIGN KEY (`school_id`) REFERENCES `school` (`id`),
  CONSTRAINT `FK_Subject_Teacher` FOREIGN KEY (`teacher_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (1,'加法','\0',1,'',NULL,1),(4,'减法','\0',1,'',NULL,1),(5,'乘法','\0',1,NULL,NULL,2),(6,'除法','\0',1,NULL,NULL,2);
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `testpaper`
--

DROP TABLE IF EXISTS `testpaper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `testpaper` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `code` varchar(45) DEFAULT NULL,
  `minutes` int(11) NOT NULL,
  `total_score` int(11) NOT NULL DEFAULT '100',
  `subject_id` int(11) DEFAULT NULL,
  `is_deleted` bit(1) NOT NULL DEFAULT b'0',
  `grade_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_subject_testpaper` (`subject_id`),
  KEY `fk_grade_testpaper_idx` (`grade_id`),
  CONSTRAINT `fk_subject_testpaper` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`),
  CONSTRAINT `fk_grade_testpaper` FOREIGN KEY (`grade_id`) REFERENCES `grade` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `testpaper`
--

LOCK TABLES `testpaper` WRITE;
/*!40000 ALTER TABLE `testpaper` DISABLE KEYS */;
INSERT INTO `testpaper` VALUES (12,'0001试卷','0001',2,100,1,'\0',NULL),(13,'0002加法','0002',20,100,1,'\0',NULL),(14,'AAA','0003',1,100,1,'\0',NULL),(15,'考试1','0004',20,100,NULL,'\0',1);
/*!40000 ALTER TABLE `testpaper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `no` varchar(45) DEFAULT NULL,
  `name` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `realname` varchar(45) DEFAULT NULL,
  `role` varchar(45) NOT NULL,
  `is_deleted` bit(1) NOT NULL DEFAULT b'0',
  `school_id` int(11) DEFAULT NULL,
  `reg_password` varchar(45) DEFAULT NULL,
  `face` varchar(255) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `college` varchar(45) DEFAULT NULL,
  `grade` varchar(45) DEFAULT NULL,
  `profession` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_School_User` (`school_id`),
  CONSTRAINT `FK_School_User` FOREIGN KEY (`school_id`) REFERENCES `school` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'','Admin','Admin','管理员','管理员','\0',1,'','http://localhost:8080/webtest//upload/-549145968.jpg',NULL,NULL,NULL,NULL),(12,'','S1','S1','张三','学生','\0',1,'','http://localhost:8080/webtest//upload/-931627483.jpg',NULL,NULL,NULL,NULL),(13,NULL,'S2','S2','李四','学生','\0',1,NULL,'http://localhost:8080/webtest//upload/-583662383.jpg',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_exam`
--

DROP TABLE IF EXISTS `user_exam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_exam` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) NOT NULL,
  `exam_id` int(11) NOT NULL,
  `time` datetime NOT NULL,
  `right_qty` int(11) NOT NULL DEFAULT '0',
  `error_qty` int(11) NOT NULL DEFAULT '0',
  `score` int(11) NOT NULL DEFAULT '0',
  `is_deleted` bit(1) NOT NULL DEFAULT b'0',
  `comment` varchar(2000) DEFAULT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `reg_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Teacher_UserExam` (`teacher_id`),
  KEY `fk_exam_user_exam` (`exam_id`),
  KEY `fk_user_user_exam` (`student_id`),
  CONSTRAINT `fk_exam_user_exam` FOREIGN KEY (`exam_id`) REFERENCES `exam` (`id`),
  CONSTRAINT `FK_Teacher_UserExam` FOREIGN KEY (`teacher_id`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_user_user_exam` FOREIGN KEY (`student_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_exam`
--

LOCK TABLES `user_exam` WRITE;
/*!40000 ALTER TABLE `user_exam` DISABLE KEYS */;
INSERT INTO `user_exam` VALUES (20,12,6,'2018-03-10 23:31:20',1,2,30,'\0',NULL,NULL,'已参加',NULL),(21,13,6,'2018-03-10 23:32:37',0,3,0,'\0',NULL,NULL,'已参加',NULL),(22,12,7,'2018-03-22 00:16:15',1,2,40,'\0',NULL,NULL,'已参加',NULL);
/*!40000 ALTER TABLE `user_exam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_exam_question`
--

DROP TABLE IF EXISTS `user_exam_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_exam_question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userexam_id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  `score` int(11) NOT NULL DEFAULT '0',
  `correct_answer` varchar(2000) DEFAULT NULL,
  `answer` varchar(2000) DEFAULT NULL,
  `is_right` bit(1) NOT NULL DEFAULT b'0',
  `is_deleted` bit(1) NOT NULL DEFAULT b'0',
  `is_mark` bit(1) NOT NULL DEFAULT b'0',
  `total_qty` int(11) NOT NULL DEFAULT '0',
  `answer_qty` int(11) NOT NULL DEFAULT '0',
  `explain_text` varchar(2000) DEFAULT NULL,
  `qscore` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_question_user_exam_question` (`question_id`),
  KEY `fk_userexm_question` (`userexam_id`),
  CONSTRAINT `fk_question_user_exam_question` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`),
  CONSTRAINT `fk_userexm_question` FOREIGN KEY (`userexam_id`) REFERENCES `user_exam` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_exam_question`
--

LOCK TABLES `user_exam_question` WRITE;
/*!40000 ALTER TABLE `user_exam_question` DISABLE KEYS */;
INSERT INTO `user_exam_question` VALUES (64,20,7,30,'B','B','','\0','\0',1,1,'选B就对了',30),(65,20,8,0,'空','sdfsdf','\0','\0','\0',1,0,'填“空”',30),(66,20,9,0,'正确','错误','\0','\0','\0',1,0,'对的',40),(67,21,7,0,'B','A','\0','\0','\0',1,0,'选B就对了',30),(68,21,8,0,'空','ddddd','\0','\0','\0',1,0,'填“空”',30),(69,21,9,0,'正确','错误','\0','\0','\0',1,0,'对的',40),(70,22,7,40,'B','B','','\0','\0',1,1,'选B就对了',40),(71,22,8,0,'A','B','\0','\0','\0',1,0,'答案是11',40),(72,22,11,0,'正确','错误','\0','\0','\0',1,0,'这是对的',20);
/*!40000 ALTER TABLE `user_exam_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_subject`
--

DROP TABLE IF EXISTS `user_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_subject` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `subject_id` int(11) NOT NULL,
  `time` datetime NOT NULL,
  `is_deleted` bit(1) NOT NULL DEFAULT b'0',
  `is_exam` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `FK_Subject_UserSubject` (`subject_id`),
  KEY `FK_User_UserSubject` (`user_id`),
  CONSTRAINT `FK_Subject_UserSubject` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`),
  CONSTRAINT `FK_User_UserSubject` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_subject`
--

LOCK TABLES `user_subject` WRITE;
/*!40000 ALTER TABLE `user_subject` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_subject` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-22 17:44:10
