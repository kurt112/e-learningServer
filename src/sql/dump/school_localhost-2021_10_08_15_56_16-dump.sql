-- MariaDB dump 10.19  Distrib 10.5.12-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: school
-- ------------------------------------------------------
-- Server version	10.5.12-MariaDB-0ubuntu0.21.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `curriculum`
--

DROP TABLE IF EXISTS `curriculum`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curriculum` (
  `code` varchar(255) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`code`),
  UNIQUE KEY `curriculum_name_uindex` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curriculum`
--

LOCK TABLES `curriculum` WRITE;
/*!40000 ALTER TABLE `curriculum` DISABLE KEYS */;
INSERT INTO `curriculum` VALUES ('2aboT31ufPBeyE2UkMgwEL','2021-07-27 14:41:52.117000','asdasdasd','2 subject','2021-08-23 16:00:41.397000',1),('567fke8kq914yQSvjYpekt','2021-08-25 05:32:18.555000','dfasdfqweqwdxcv','asdfas','2021-08-25 05:32:18.555000',0),('cKYuh8Zg8Vo6bpo8sTrtrW','2021-09-04 04:54:33.681000','','Curriculum2 subject','2021-09-04 04:54:33.681000',1),('cw7R52JkNB9h7SUWdL5Lrd','2021-08-23 21:56:26.279000','This is sample\n','ICT - 101','2021-08-23 21:56:26.279000',0),('eNorc4mRvvnr7hUYGwpiwg','2021-08-25 05:33:39.169000','zxvsdfsadfasdfsadsdf','qweqweqweq','2021-08-25 05:33:39.169000',1),('iht2yRg4p6HFps25saEkeX','2021-07-26 09:53:38.608000','easdfsdfsdf','10 subject','2021-08-02 07:30:02.994000',1),('jdBGDnGnzHaZp6oa5vAx6a','2021-07-27 23:03:25.832000','hotdog','6 subject','2021-08-23 16:00:38.786000',1),('pxsnrQDJGAr4FvdrT2w7V3','2021-08-07 09:33:24.455000','This is only for john doe','John Doe (Subject)','2021-08-23 21:57:24.548000',1),('wSPkbJTg5D2y5Rino7jmGd','2021-08-25 05:32:28.817000','qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq','qqqqqqqqqqq','2021-08-25 05:32:28.817000',0);
/*!40000 ALTER TABLE `curriculum` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curriculum_subject`
--

DROP TABLE IF EXISTS `curriculum_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curriculum_subject` (
  `curriculum_id` varchar(255) NOT NULL,
  `subject_id` int(11) NOT NULL,
  KEY `FKm9sl8yn0n88pmtn090x55jnop` (`subject_id`),
  KEY `FK3w1qydlhmqkivoo233xpxwjek` (`curriculum_id`),
  CONSTRAINT `FK3w1qydlhmqkivoo233xpxwjek` FOREIGN KEY (`curriculum_id`) REFERENCES `curriculum` (`code`),
  CONSTRAINT `FKm9sl8yn0n88pmtn090x55jnop` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curriculum_subject`
--

LOCK TABLES `curriculum_subject` WRITE;
/*!40000 ALTER TABLE `curriculum_subject` DISABLE KEYS */;
INSERT INTO `curriculum_subject` VALUES ('jdBGDnGnzHaZp6oa5vAx6a',4),('jdBGDnGnzHaZp6oa5vAx6a',3),('jdBGDnGnzHaZp6oa5vAx6a',5),('jdBGDnGnzHaZp6oa5vAx6a',6),('jdBGDnGnzHaZp6oa5vAx6a',7),('jdBGDnGnzHaZp6oa5vAx6a',8),('2aboT31ufPBeyE2UkMgwEL',2),('2aboT31ufPBeyE2UkMgwEL',5),('iht2yRg4p6HFps25saEkeX',4),('iht2yRg4p6HFps25saEkeX',5),('iht2yRg4p6HFps25saEkeX',3),('iht2yRg4p6HFps25saEkeX',7),('iht2yRg4p6HFps25saEkeX',6),('iht2yRg4p6HFps25saEkeX',8),('iht2yRg4p6HFps25saEkeX',9),('iht2yRg4p6HFps25saEkeX',10),('iht2yRg4p6HFps25saEkeX',1),('iht2yRg4p6HFps25saEkeX',2),('pxsnrQDJGAr4FvdrT2w7V3',2),('pxsnrQDJGAr4FvdrT2w7V3',1),('pxsnrQDJGAr4FvdrT2w7V3',5),('pxsnrQDJGAr4FvdrT2w7V3',6),('pxsnrQDJGAr4FvdrT2w7V3',32),('pxsnrQDJGAr4FvdrT2w7V3',18);
/*!40000 ALTER TABLE `curriculum_subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dashboard`
--

DROP TABLE IF EXISTS `dashboard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dashboard` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `classes_count` int(11) DEFAULT NULL,
  `room_count` int(11) DEFAULT NULL,
  `student_count` int(11) DEFAULT NULL,
  `subject_count` int(11) DEFAULT NULL,
  `teacher_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dashboard`
--

LOCK TABLES `dashboard` WRITE;
/*!40000 ALTER TABLE `dashboard` DISABLE KEYS */;
INSERT INTO `dashboard` VALUES (1,86,4,26,73,25);
/*!40000 ALTER TABLE `dashboard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room` (
  `room_id` varchar(255) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `room_name` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `room_time_end` varchar(255) DEFAULT NULL,
  `room_time_start` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES ('9jigGvSGaMKLgddXPwCQMV','2021-08-04 05:43:12.579000','I am updated',0,'06:22','16:23','2021-08-30 19:42:29.093000'),('bTk8YBzqfkp73eNkoEshYw','2021-08-17 21:09:52.264000','I am Updated',0,'15:55','09:55','2021-08-30 19:42:15.576000'),('eJChycTzSn6erpqoo5cp5f','2021-08-03 01:28:16.836000','dasdasd',0,'01:30','01:30','2021-08-30 19:42:33.324000'),('hcA5Nz6XC5ZvGbEF8xnLoA','2021-08-01 07:47:50.557000','hotdog',1,'07:49','09:47','2021-08-30 21:54:52.338000'),('jEPXmBK31YX12CSdeEGkbt','2021-08-17 21:10:53.267000','dasdasdasdqweqwqweqweq',0,'13:10','22:10','2021-08-30 19:42:08.227000'),('kFQM63DYdsuPkA8H8b8Buh','2021-08-30 23:22:51.807000','wqeqw',1,'13:22','23:24','2021-08-30 23:22:51.807000'),('soAbLZneBGoFp4Ppcu8awu','2021-08-08 05:18:28.751000','qweqw',0,'10:18','05:22','2021-08-30 19:42:22.504000');
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_shift`
--

DROP TABLE IF EXISTS `room_shift`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room_shift` (
  `id` varchar(255) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `grade` varchar(255) DEFAULT NULL,
  `room_shift_name` varchar(255) DEFAULT NULL,
  `section` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `time_end` varchar(255) DEFAULT NULL,
  `time_start` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `curriculum` varchar(255) DEFAULT NULL,
  `room_id` varchar(255) DEFAULT NULL,
  `adviser` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKelva0b4ffct0m4aqnq6guf8b8` (`curriculum`),
  KEY `FKf10tu5cqdvvpxaocyrn0lj6mr` (`room_id`),
  KEY `FKp9lecybj9u7khjywmlu90xdxc` (`adviser`),
  CONSTRAINT `FKelva0b4ffct0m4aqnq6guf8b8` FOREIGN KEY (`curriculum`) REFERENCES `curriculum` (`code`),
  CONSTRAINT `FKf10tu5cqdvvpxaocyrn0lj6mr` FOREIGN KEY (`room_id`) REFERENCES `room` (`room_id`),
  CONSTRAINT `FKp9lecybj9u7khjywmlu90xdxc` FOREIGN KEY (`adviser`) REFERENCES `teacher` (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_shift`
--

LOCK TABLES `room_shift` WRITE;
/*!40000 ALTER TABLE `room_shift` DISABLE KEYS */;
INSERT INTO `room_shift` VALUES ('2fBEEcgZ2ebanVQBiosVgH','2021-08-03 01:07:06.624000','11','First','ded',1,'03:06','03:06','2021-08-30 21:54:51.702000','iht2yRg4p6HFps25saEkeX','hcA5Nz6XC5ZvGbEF8xnLoA','testasdf'),('8QzTqm6AXaHJwtu8jYJbuA','2021-08-07 09:20:33.441000','11','First','Dekk',0,'10:19','09:23','2021-08-30 19:42:29.099000','2aboT31ufPBeyE2UkMgwEL','9jigGvSGaMKLgddXPwCQMV','testasdf'),('a4xcUzRoVyKyN8xXbg1Cxe','2021-08-03 01:24:54.329000','ddasd','Third','asdasda',1,'01:26','01:26','2021-08-30 21:54:51.874000','iht2yRg4p6HFps25saEkeX','hcA5Nz6XC5ZvGbEF8xnLoA','hotdog'),('r9abCNS6veCxmBq3a5iCZc','2021-08-03 01:32:12.234000','12','Third','asdfasdfasdasdasd',0,'01:33','01:34','2021-08-30 19:42:33.329000','iht2yRg4p6HFps25saEkeX','eJChycTzSn6erpqoo5cp5f','qweasdasd');
/*!40000 ALTER TABLE `room_shift` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_shift_classes`
--

DROP TABLE IF EXISTS `room_shift_classes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room_shift_classes` (
  `room_classes_id` varchar(255) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `room_classes_day` varchar(255) DEFAULT NULL,
  `room_classes_end_time` varchar(255) DEFAULT NULL,
  `room_classes_start_time` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `room_classes_shift` varchar(255) DEFAULT NULL,
  `room_classes_subject` int(11) DEFAULT NULL,
  `room_classes_teacher_fk` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`room_classes_id`),
  KEY `FKoh9wyd0gic2pvxgmec5jrjdr4` (`room_classes_shift`),
  KEY `FK9hc2fu9gnd3i2qi91we8c2ouy` (`room_classes_subject`),
  KEY `FKp44fmi44atiq56sg9a03yjk7n` (`room_classes_teacher_fk`),
  CONSTRAINT `FK9hc2fu9gnd3i2qi91we8c2ouy` FOREIGN KEY (`room_classes_subject`) REFERENCES `subject` (`id`),
  CONSTRAINT `FKoh9wyd0gic2pvxgmec5jrjdr4` FOREIGN KEY (`room_classes_shift`) REFERENCES `room_shift` (`id`),
  CONSTRAINT `FKp44fmi44atiq56sg9a03yjk7n` FOREIGN KEY (`room_classes_teacher_fk`) REFERENCES `teacher` (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_shift_classes`
--

LOCK TABLES `room_shift_classes` WRITE;
/*!40000 ALTER TABLE `room_shift_classes` DISABLE KEYS */;
INSERT INTO `room_shift_classes` VALUES ('11105f802asdfasdfasd','2021-08-03 01:32:12.246000',NULL,'08:57','21:57',0,'2021-08-30 19:42:33.371000','r9abCNS6veCxmBq3a5iCZc',3,'testTeacher'),('1dEY28e7TRvsLGrTt4nqT6','2021-08-07 09:29:10.714000','MTWTHF','09:30','09:30',0,'2021-08-30 19:42:29.177000','8QzTqm6AXaHJwtu8jYJbuA',1,'testTeacher'),('cbZJYB1ujdg6sfgA1xSuB5','2021-08-17 21:39:53.513000','TTH','14:39','21:41',1,'2021-08-30 21:54:51.708000','2fBEEcgZ2ebanVQBiosVgH',9,'testTeacher'),('ddasd219de92asdasda','2021-08-03 01:24:54.342000',NULL,NULL,NULL,1,'2021-08-30 21:54:51.917000','a4xcUzRoVyKyN8xXbg1Cxe',7,'testTeacher');
/*!40000 ALTER TABLE `room_shift_classes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_shift_classes_students`
--

DROP TABLE IF EXISTS `room_shift_classes_students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room_shift_classes_students` (
  `room_class_id` varchar(255) NOT NULL,
  `student_id` varchar(255) NOT NULL,
  PRIMARY KEY (`room_class_id`,`student_id`),
  KEY `FKjhkh5p8oy6nk3tn2i9qicgvf0` (`student_id`),
  CONSTRAINT `FK98ws62y1bv1aln62e7xmyh19t` FOREIGN KEY (`room_class_id`) REFERENCES `room_shift_classes` (`room_classes_id`),
  CONSTRAINT `FKjhkh5p8oy6nk3tn2i9qicgvf0` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_shift_classes_students`
--

LOCK TABLES `room_shift_classes_students` WRITE;
/*!40000 ALTER TABLE `room_shift_classes_students` DISABLE KEYS */;
INSERT INTO `room_shift_classes_students` VALUES ('1dEY28e7TRvsLGrTt4nqT6','asdff'),('1dEY28e7TRvsLGrTt4nqT6','sdfsfqwe'),('cbZJYB1ujdg6sfgA1xSuB5','asdff');
/*!40000 ALTER TABLE `room_shift_classes_students` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_shift_students`
--

DROP TABLE IF EXISTS `room_shift_students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room_shift_students` (
  `room_shift_id` varchar(255) NOT NULL,
  `student_id` varchar(255) NOT NULL,
  KEY `FK336lju8yshm5q71ixtqrou54k` (`student_id`),
  KEY `FK3vtwbwnre4q11y89sdjqmufsu` (`room_shift_id`),
  CONSTRAINT `FK336lju8yshm5q71ixtqrou54k` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`),
  CONSTRAINT `FK3vtwbwnre4q11y89sdjqmufsu` FOREIGN KEY (`room_shift_id`) REFERENCES `room_shift` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_shift_students`
--

LOCK TABLES `room_shift_students` WRITE;
/*!40000 ALTER TABLE `room_shift_students` DISABLE KEYS */;
INSERT INTO `room_shift_students` VALUES ('8QzTqm6AXaHJwtu8jYJbuA','sdfsfqwe'),('8QzTqm6AXaHJwtu8jYJbuA','asdff');
/*!40000 ALTER TABLE `room_shift_students` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `student_id` varchar(255) NOT NULL,
  `student_user` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`student_id`),
  KEY `FKcxrykxct21gl8hdvwsg55o07o` (`student_user`),
  CONSTRAINT `FKcxrykxct21gl8hdvwsg55o07o` FOREIGN KEY (`student_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('123112',207,0),('1231223',208,0),('12331',206,0),('231231244',210,0),('3344112',209,0),('asdff',139,1),('sdfsfqwe',173,0);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_assignment`
--

DROP TABLE IF EXISTS `student_assignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_assignment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `grade` double DEFAULT NULL,
  `student` varchar(255) DEFAULT NULL,
  `assignment` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `submitted_at` datetime(6) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `response` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhhpvhafwrditl3e9rddcf1rne` (`student`),
  KEY `FKd5rpn57ofbt971hslnsa3lrnk` (`assignment`),
  CONSTRAINT `FKd5rpn57ofbt971hslnsa3lrnk` FOREIGN KEY (`assignment`) REFERENCES `teacher_assignment` (`code`),
  CONSTRAINT `FKhhpvhafwrditl3e9rddcf1rne` FOREIGN KEY (`student`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_assignment`
--

LOCK TABLES `student_assignment` WRITE;
/*!40000 ALTER TABLE `student_assignment` DISABLE KEYS */;
INSERT INTO `student_assignment` VALUES (1,'2021-08-19 02:55:10.447000',100,'asdff','representative-pink-fly',1,'2021-08-31 03:12:14.137000','fcecd1d8-3e80-47cb-888e-69598a71a56e.pdf','Late'),(2,'2021-08-19 02:55:10.447000',0,'asdff','representative-pink-fly',-1,NULL,'',''),(3,'2021-08-19 02:55:10.447000',0,'asdff','representative-pink-fly',-1,NULL,'',''),(4,'2021-08-25 08:09:18.817000',100,'asdff','brown-aqua-basilisk',1,'2021-08-31 03:12:31.605000','e2a7af01-5cf8-4fa5-9615-826e22c4ac07.png','Late'),(5,'2021-08-25 08:09:18.829000',0,'sdfsfqwe','brown-aqua-basilisk',-1,'2021-08-25 08:09:18.829000','',NULL);
/*!40000 ALTER TABLE `student_assignment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_class_attendance`
--

DROP TABLE IF EXISTS `student_class_attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_class_attendance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `classes` varchar(255) DEFAULT NULL,
  `student` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK44fsgbcouhevnm8opc4f2qhrb` (`classes`),
  KEY `FKkxn27h25rknsvtoijy6exo3ge` (`student`),
  CONSTRAINT `FK44fsgbcouhevnm8opc4f2qhrb` FOREIGN KEY (`classes`) REFERENCES `room_shift_classes` (`room_classes_id`),
  CONSTRAINT `FKkxn27h25rknsvtoijy6exo3ge` FOREIGN KEY (`student`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_class_attendance`
--

LOCK TABLES `student_class_attendance` WRITE;
/*!40000 ALTER TABLE `student_class_attendance` DISABLE KEYS */;
INSERT INTO `student_class_attendance` VALUES (3,'2021-08-31 07:08:44.124000','cbZJYB1ujdg6sfgA1xSuB5','asdff');
/*!40000 ALTER TABLE `student_class_attendance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_exam`
--

DROP TABLE IF EXISTS `student_exam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_exam` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `grade` double DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `response` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `submitted_at` datetime(6) DEFAULT NULL,
  `exam` varchar(255) DEFAULT NULL,
  `student` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnt6uwp2tl7xwncaeou9b4mu57` (`exam`),
  KEY `FKc2g2sjlowhc2d1s7t1e9jp129` (`student`),
  CONSTRAINT `FKc2g2sjlowhc2d1s7t1e9jp129` FOREIGN KEY (`student`) REFERENCES `student` (`student_id`),
  CONSTRAINT `FKnt6uwp2tl7xwncaeou9b4mu57` FOREIGN KEY (`exam`) REFERENCES `teacher_exams` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_exam`
--

LOCK TABLES `student_exam` WRITE;
/*!40000 ALTER TABLE `student_exam` DISABLE KEYS */;
INSERT INTO `student_exam` VALUES (2,'2021-08-26 05:38:47.780000',100,'69d81642-5a2e-4d74-b98f-a12ea9c1ef45.docx','Late',1,'2021-08-31 03:10:49.061000','intelligent-purple-condor','asdff'),(3,'2021-08-26 05:38:47.791000',0,NULL,NULL,-1,'2021-08-26 05:38:47.791000','intelligent-purple-condor','sdfsfqwe'),(4,'2021-08-28 18:04:14.056000',100,'e7270b37-50d7-4d09-9a54-233320ae1545.xlsx','Late',1,'2021-08-28 18:04:14.056000','helpless-lime-vole','asdff'),(5,'2021-08-31 05:25:31.471000',0,NULL,NULL,-1,'2021-08-31 05:25:31.471000','aware-salmon-perch','asdff'),(6,'2021-08-31 05:25:50.234000',0,NULL,NULL,-1,'2021-08-31 05:25:50.234000','uptight-coral-leopard','asdff'),(7,'2021-08-31 05:26:41.211000',0,NULL,NULL,-1,'2021-08-31 05:26:41.211000','psychological-scarlet-whale','asdff');
/*!40000 ALTER TABLE `student_exam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_grade`
--

DROP TABLE IF EXISTS `student_grade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `grade` double DEFAULT NULL,
  `class` varchar(255) DEFAULT NULL,
  `student` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKikwxmw47093ceqf62p6miw6eo` (`class`),
  KEY `FKagcph287t8otqhx9wredyln7e` (`student`),
  CONSTRAINT `FKagcph287t8otqhx9wredyln7e` FOREIGN KEY (`student`) REFERENCES `student` (`student_id`),
  CONSTRAINT `FKikwxmw47093ceqf62p6miw6eo` FOREIGN KEY (`class`) REFERENCES `room_shift_classes` (`room_classes_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_grade`
--

LOCK TABLES `student_grade` WRITE;
/*!40000 ALTER TABLE `student_grade` DISABLE KEYS */;
INSERT INTO `student_grade` VALUES (5,100,'cbZJYB1ujdg6sfgA1xSuB5','asdff');
/*!40000 ALTER TABLE `student_grade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_quiz`
--

DROP TABLE IF EXISTS `student_quiz`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_quiz` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `grade` double DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `response` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `submitted_at` datetime(6) DEFAULT NULL,
  `quiz` varchar(255) DEFAULT NULL,
  `student` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1boq2hsvg7gqe133reo19t99a` (`quiz`),
  KEY `FK7xal7wjoj791degunhdyikp7k` (`student`),
  CONSTRAINT `FK1boq2hsvg7gqe133reo19t99a` FOREIGN KEY (`quiz`) REFERENCES `teacher_quizzes` (`code`),
  CONSTRAINT `FK7xal7wjoj791degunhdyikp7k` FOREIGN KEY (`student`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_quiz`
--

LOCK TABLES `student_quiz` WRITE;
/*!40000 ALTER TABLE `student_quiz` DISABLE KEYS */;
INSERT INTO `student_quiz` VALUES (1,'2021-08-31 00:43:05.040000',100,'a126f05a-f557-4198-84b1-758f54474fea.png','Late',1,'2021-08-31 00:43:22.951000','total-aquamarine-quail','asdff');
/*!40000 ALTER TABLE `student_quiz` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_resources`
--

DROP TABLE IF EXISTS `student_resources`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_resources` (
  `resources` varchar(255) NOT NULL,
  `student` varchar(255) NOT NULL,
  KEY `FKm4ync54tbnbnmami91no6dt2b` (`student`),
  KEY `FK410y9oinac3ve861igjwo4ovv` (`resources`),
  CONSTRAINT `FK410y9oinac3ve861igjwo4ovv` FOREIGN KEY (`resources`) REFERENCES `teacher_resources` (`code`),
  CONSTRAINT `FKm4ync54tbnbnmami91no6dt2b` FOREIGN KEY (`student`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_resources`
--

LOCK TABLES `student_resources` WRITE;
/*!40000 ALTER TABLE `student_resources` DISABLE KEYS */;
/*!40000 ALTER TABLE `student_resources` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subject` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `subject_code` varchar(255) DEFAULT NULL,
  `subject_major` varchar(255) DEFAULT NULL,
  `subject_name` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `subject_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (1,'2021-07-17 11:00:25.725000','ABM-APPECO','Major','Applied Economics','2021-07-17 11:00:25.725000',0),(2,'2021-07-17 11:00:49.269000','ABM-BESM','Major','Business Enterprise Simulation','2021-07-17 11:00:49.269000',0),(3,'2021-07-17 11:00:40.297000','ABM-BESR','Major','Business Ethics and Social Responsibility','2021-07-17 11:00:40.297000',0),(4,'2021-07-17 10:43:10.201000','ABM-BUFI','Major','Business Finance','2021-07-17 10:43:10.201000',1),(5,'2021-07-17 10:42:59.537000','ABM-BUMA','Major','Business Marketing','2021-07-17 10:42:59.537000',1),(6,'2021-07-17 10:42:59.537000','ABM-BUMATH','Major','Business Math qweqw','2021-07-17 11:02:48.502000',1),(7,'2021-07-17 10:42:48.082000','ABM-FABM','Major','Fundamentals of  Acctg  Business and Mgmt. 1','2021-07-17 10:42:48.082000',1),(8,'2021-07-17 10:58:14.202000','ABM-FABM2','Major','Fundamentals of  Acctg  Business and Mgmt. 2','2021-07-17 10:58:14.202000',0),(9,'2021-07-17 10:58:05.088000','ABM-ORGM','Major','Organization and Mgmt','2021-07-17 10:58:05.088000',0),(10,'2021-07-17 10:58:36.705000','ANIM-ENMA','Major','Environment and Market','2021-07-17 10:58:36.705000',0),(11,'2021-07-17 10:42:19.756000','ANIM-MHEP','Major','Maintain Handtools Equipment and Paraphernalia','2021-07-17 10:42:19.756000',1),(12,'2021-07-17 11:01:49.407000','ANIM-PCUIBD','Major','Providing Cleaned-Up and In-Between Drawings','2021-07-17 11:01:49.407000',0),(13,'2021-07-17 10:58:28.584000','ANIM-PERC','Major','Personal Entrepreneurial Competencies','2021-07-17 10:58:28.584000',0),(14,'2021-07-17 10:42:38.507000','ANIM-PITD','Major','Prepare and Interpret Technical Drawing','2021-07-17 10:42:38.507000',1),(15,'2021-07-17 10:42:28.197000','ANIM-PMSC','Major','Perform Mensuration and Calc.','2021-07-17 10:42:28.197000',1),(16,'2021-07-17 11:00:58.962000','ANIM-POHSP','Major','Practice Occupational Health and Safety Procedure','2021-07-17 11:00:58.962000',0),(17,'2021-07-17 10:58:51.971000','ANIM-USE','Major','Use Handtools and Equipments','2021-07-17 10:58:51.971000',0),(18,'2021-07-15 01:48:00.841000','CS101','Minor','Mathematics for Modern World','2021-07-15 01:48:00.841000',1),(19,'2021-07-17 10:46:40.364000','CT-CPJ2','Major','Computer Programming Java 2','2021-07-17 10:46:40.364000',0),(20,'2021-07-17 10:45:09.428000','GEN-12PE1','Minor','Physical Education and Health G12 1','2021-07-17 10:45:09.428000',0),(21,'2021-07-17 10:45:23.277000','GEN-12PE2','Minor','Physical Education and Health G12 2','2021-07-17 10:45:23.277000',0),(22,'2021-07-17 10:34:56.615000','GEN-1SPE11','Minor','Physical Education and Health DANCE 1','2021-07-17 10:34:56.615000',1),(23,'2021-07-17 10:37:13.744000','GEN-21LIT','Minor','21st Century Literature from the Philippines and the World','2021-07-17 10:37:13.744000',1),(24,'2021-07-17 10:44:59.832000','GEN-2SPE11','Minor','Physical Education and Health G11  2','2021-07-17 10:44:59.832000',1),(25,'2021-07-17 10:36:17.084000','GEN-CPAR','Minor','Contemporary Philippine Arts from the Regions','2021-07-17 10:36:17.084000',1),(26,'2021-07-17 10:34:23.041000','GEN-ELS','Minor','Earth and Life Science','2021-07-17 10:34:23.041000',1),(27,'2021-07-17 10:36:35.435000','GEN-EMT','Minor','Empowerment Technologies','2021-07-17 10:36:35.436000',1),(28,'2021-07-17 10:36:26.980000','GEN-ENGAP','Minor','English for Academic and Professional Purposes','2021-07-17 10:36:26.980000',1),(29,'2021-07-17 10:59:12.208000','GEN-ENTREP','Minor','Entrepreneurship','2021-07-17 10:59:12.208000',0),(30,'2021-07-17 10:37:30.499000','GEN-FILPL','Minor','Filipino sa Piling Larangan','2021-07-17 10:37:30.499000',0),(31,'2021-07-17 10:44:33.916000','GEN-FILPPIT','Minor','Pagbasa at Pagsusuri ng Iba\'t Ibang Teksto Tungo sa Pananaliksik','2021-07-17 10:44:33.916000',1),(32,'2021-07-17 10:34:10.203000','GEN-GENM','Minor','General Mathematics','2021-07-17 10:34:10.203000',1),(33,'2021-07-17 10:59:20.898000','GEN-INQVM','Minor','Inquiries Investigations and Immersion','2021-07-17 10:59:20.898000',0),(34,'2021-07-17 10:36:43.154000','GEN-IPHIL','Minor','Introduction to the Philosophy of the Human Person','2021-07-17 10:36:43.154000',1),(35,'2021-07-17 10:33:50.561000','GEN-KPWKP','Minor','Komunikasyon at  Pananaliksik sa Wika at  Kulturang Pilipino','2021-07-17 10:33:50.561000',1),(36,'2021-07-17 10:39:55.136000','GEN-MAL','Minor','Media and Information','2021-07-17 10:39:55.136000',1),(37,'2021-07-17 10:43:58.750000','GEN-MSTATPR','Minor','Statistics and Probability','2021-07-17 10:43:58.750000',1),(38,'2021-07-17 10:59:30.823000','GEN-OJT','Minor','Work Immersion','2021-07-17 10:59:30.823000',0),(39,'2021-07-17 10:33:24.797000','GEN-ORCOM','Minor','Oral Communication','2021-07-17 10:33:24.797000',1),(40,'2021-07-17 11:02:36.861000','GEN-PHYSCI','Minor','Physical Science','2021-07-17 11:02:36.861000',0),(41,'2021-07-17 10:38:59.800000','GEN-PRAC1','Minor','Practical Research 1','2021-07-17 10:38:59.800000',1),(42,'2021-07-17 10:45:31.494000','GEN-PRAC2','Minor','Practical Research 2','2021-07-17 10:45:31.494000',0),(43,'2021-07-17 10:34:45.856000','GEN-PRDV','Minor','Personal Development','2021-07-17 10:34:45.856000',1),(44,'2021-07-17 10:43:38.389000','GEN-REWRI','Minor','Reading and Writing','2021-07-17 10:43:38.389000',1),(45,'2021-07-17 10:34:32.748000','GEN-UCSP','Minor','Understanding Culture Society and Politics Society and Politics','2021-07-17 10:34:32.748000',1),(46,'2021-07-17 11:02:18.639000','HS-CESC','Major','Community Engagement Solidarity and Citizenship','2021-07-17 11:02:18.639000',0),(47,'2021-07-17 11:01:59.066000','HS-CNFLE','Major','Creative Non Fiction The Literacy Essay','2021-07-17 11:01:59.066000',0),(48,'2021-07-17 10:40:38.918000','HS-CTWR','Major','Creative Writing','2021-07-17 10:40:38.918000',1),(49,'2021-07-17 11:02:28.290000','HS-CUACT','Major','Culminating Activities','2021-07-17 11:02:28.290000',0),(50,'2021-07-17 10:42:10.541000','HS-DISS','Minor','Disciplines and Ideas in  in the Applied Social Sciences','2021-07-17 10:42:10.541000',1),(51,'2021-07-17 10:59:00.760000','HS-IWRB','Major','Introduction to World Religions and Belief Systems','2021-07-17 10:59:00.760000',0),(52,'2021-07-17 10:40:48.174000','HS-PPAG','Major','Philippines Politics and Governance','2021-07-17 10:40:48.174000',1),(54,'2021-07-17 10:36:01.480000','ICT-CPJ1','Major','Computer Programming Java 1','2021-07-17 10:36:01.480000',1),(55,'2021-07-17 10:39:42.465000','ICT-CPJ3','Major','Computer Programming Java  3','2021-07-17 10:39:42.465000',1),(56,'2021-07-17 10:35:20.294000','ICT-CPN1','Major','Computer Programming .Net Technology 1','2021-07-17 10:35:20.294000',1),(57,'2021-07-17 10:46:31.034000','ICT-CPN2','Major','Computer Programming .Net Technology 2','2021-07-17 10:46:31.034000',1),(58,'2021-07-17 10:39:32.945000','ICT-CPN3','Major','Computer Programming .Net Technology  3','2021-07-17 10:39:32.945000',1),(59,'2021-07-17 10:59:44.407000','ICT-CPN4','Major','Computer Programming .Net Technology 3','2021-07-17 10:59:44.407000',0),(60,'2021-07-17 10:39:17.381000','ICT-SQL','Major','Structured Query Language','2021-07-17 10:39:17.381000',1),(61,'2021-07-17 10:57:48.065000','STM-BASC','Major','Basic Calculus','2021-07-17 10:57:48.065000',1),(62,'2021-07-17 10:57:36.896000','STM-DIRR','Major','Disaster Readiness and Risk Reduction','2021-07-17 10:57:36.896000',0),(63,'2021-07-17 10:43:28.914000','STM-GENBIO1','Major','General Biology 1','2021-07-17 10:43:28.914000',1),(64,'2021-07-17 11:00:02.745000','STM-GENBIO2','Major','General Biology 2','2021-07-17 11:00:02.745000',0),(65,'2021-07-17 10:57:56.763000','STM-GENCHEM1','Major','General Chemistry 1','2021-07-17 10:57:56.763000',1),(66,'2021-07-17 11:00:12.080000','STM-GENCHEM2','Major','General Chemistry 2','2021-07-17 11:00:12.080000',0),(67,'2021-07-17 10:43:20.106000','STM-GENPHY1','Major','General Pyshics 1','2021-07-17 10:43:20.106000',1),(68,'2021-07-17 10:59:54.188000','STM-GENPHY2','Major','General Pyshics 2','2021-07-17 10:59:54.188000',0),(69,'2021-07-17 10:37:01.004000','STM-PREC','Major','Pre-Calculus','2021-07-17 10:37:01.004000',1),(70,'2021-07-17 10:47:18.220000','ICT-CPJ4','Major','Computer Programming Java 4','2021-07-17 10:47:18.220000',1),(71,'2021-07-17 10:42:59.537000','qqweqwe','Minor','dasdasd','2021-07-27 21:28:34.322000',1),(74,'2021-07-17 10:42:59.537000','dqweqwe','Minor','dqweqweqweqweqw','2021-08-04 06:01:14.192000',0),(75,'2021-08-08 04:19:28.015000','asdasd','Minor','qweqweqwe','2021-08-08 04:19:28.015000',0),(77,'2021-08-23 16:30:36.087000','asdfasdfsfdf','Major','asdfsadf','2021-08-23 16:30:36.087000',0);
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher` (
  `teacher_id` varchar(255) NOT NULL,
  `teacher_link` varchar(255) DEFAULT NULL,
  `teacher_user` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`teacher_id`),
  KEY `FKmcsbivraugh0nf2x73j1cb0t` (`teacher_user`),
  CONSTRAINT `FKmcsbivraugh0nf2x73j1cb0t` FOREIGN KEY (`teacher_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES ('12311','?',183,0),('12312',NULL,191,0),('123123',NULL,188,0),('12312312',NULL,190,0),('12345','?',145,1),('21312312312',NULL,186,0),('23123123',NULL,192,0),('asdf','?',161,0),('asdfqweqweqweqweq','?',181,0),('hotdog','?',145,1),('JhXpaH','?',132,1),('NngSZe','?',133,1),('q','?',137,1),('qweasdasd','?',141,1),('qweqwe','?',140,1),('qweqweqwe','?',147,1),('sW9Dtv','?',134,1),('testasdf','?',136,1),('testTeacher','?',172,1);
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher_assignment`
--

DROP TABLE IF EXISTS `teacher_assignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher_assignment` (
  `code` varchar(255) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `deadline` datetime(6) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `high_grade` double DEFAULT NULL,
  `low_grade` double DEFAULT NULL,
  `quarter` int(11) DEFAULT NULL,
  `sem` int(11) DEFAULT NULL,
  `resource` varchar(255) DEFAULT NULL,
  `room_shift_class` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`code`),
  KEY `FK4bgdrdxfdbxoo6x6o09eafb6o` (`resource`),
  KEY `FKeahlgp73xpxp1jj3gl2lyy3sk` (`room_shift_class`),
  CONSTRAINT `FK4bgdrdxfdbxoo6x6o09eafb6o` FOREIGN KEY (`resource`) REFERENCES `teacher_resources` (`code`),
  CONSTRAINT `FKeahlgp73xpxp1jj3gl2lyy3sk` FOREIGN KEY (`room_shift_class`) REFERENCES `room_shift_classes` (`room_classes_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher_assignment`
--

LOCK TABLES `teacher_assignment` WRITE;
/*!40000 ALTER TABLE `teacher_assignment` DISABLE KEYS */;
INSERT INTO `teacher_assignment` VALUES ('brown-aqua-basilisk','2021-08-25 08:09:18.800000','2021-08-25 08:09:18.800000','asdfasdfasdfasdfasdfdsfasdfasdfasdfasdfasdfasdfasdfasdfasdf',100,0,1,1,'central-yellow-jackal','1dEY28e7TRvsLGrTt4nqT6'),('representative-pink-fly','2021-08-19 02:55:10.436000','2021-08-19 02:55:10.436000','qwweqweqweqwezcvvd',100,0,1,1,'central-yellow-jackal','cbZJYB1ujdg6sfgA1xSuB5');
/*!40000 ALTER TABLE `teacher_assignment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher_class_attendance`
--

DROP TABLE IF EXISTS `teacher_class_attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher_class_attendance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `classes` varchar(255) DEFAULT NULL,
  `teacher` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsi2y0laauj11w3mhveafg3g6m` (`classes`),
  KEY `FKmawqtdwhgfl125k3079fqm8po` (`teacher`),
  CONSTRAINT `FKmawqtdwhgfl125k3079fqm8po` FOREIGN KEY (`teacher`) REFERENCES `teacher` (`teacher_id`),
  CONSTRAINT `FKsi2y0laauj11w3mhveafg3g6m` FOREIGN KEY (`classes`) REFERENCES `room_shift_classes` (`room_classes_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher_class_attendance`
--

LOCK TABLES `teacher_class_attendance` WRITE;
/*!40000 ALTER TABLE `teacher_class_attendance` DISABLE KEYS */;
INSERT INTO `teacher_class_attendance` VALUES (6,'2021-08-31 07:06:30.006000','cbZJYB1ujdg6sfgA1xSuB5','testTeacher');
/*!40000 ALTER TABLE `teacher_class_attendance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher_exams`
--

DROP TABLE IF EXISTS `teacher_exams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher_exams` (
  `code` varchar(255) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `deadline` datetime(6) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `high_grade` double DEFAULT NULL,
  `low_grade` double DEFAULT NULL,
  `quarter` int(11) DEFAULT NULL,
  `sem` int(11) DEFAULT NULL,
  `resource` varchar(255) DEFAULT NULL,
  `room_shift_class` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`code`),
  KEY `FKa9lbckujvm347h888twtgdsph` (`resource`),
  KEY `FK9fp1e1276p89dcvq27kq1gjx3` (`room_shift_class`),
  CONSTRAINT `FK9fp1e1276p89dcvq27kq1gjx3` FOREIGN KEY (`room_shift_class`) REFERENCES `room_shift_classes` (`room_classes_id`),
  CONSTRAINT `FKa9lbckujvm347h888twtgdsph` FOREIGN KEY (`resource`) REFERENCES `teacher_resources` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher_exams`
--

LOCK TABLES `teacher_exams` WRITE;
/*!40000 ALTER TABLE `teacher_exams` DISABLE KEYS */;
INSERT INTO `teacher_exams` VALUES ('aware-salmon-perch','2021-08-31 05:25:31.458000','2021-08-31 05:25:31.458000','asdfasdfassadfasdfasdfas',100,0,1,1,'helpful-jade-earwig','cbZJYB1ujdg6sfgA1xSuB5'),('helpless-lime-vole','2021-08-28 18:04:14.044000','2021-08-28 18:04:14.044000','asdfasdfqweadsfasdfzxcvawdfasdsdf',100,0,1,1,'cruel-violet-hedgehog','cbZJYB1ujdg6sfgA1xSuB5'),('intelligent-purple-condor','2021-08-26 05:38:47.765000','2021-08-26 05:38:47.765000','1dfgsdfgdsfgdsfg',100,0,1,2,'helpful-jade-earwig','1dEY28e7TRvsLGrTt4nqT6'),('psychological-scarlet-whale','2021-08-31 05:26:41.208000','2021-08-31 05:26:41.208000','dfgdsfgsdfgsdfgsdfgsdfgsdfg',100,0,1,1,'helpful-jade-earwig','cbZJYB1ujdg6sfgA1xSuB5'),('uptight-coral-leopard','2021-08-31 05:25:50.231000','2021-08-31 05:25:50.231000','asdfasfasdfasdfasdfffasddasd',100,0,1,1,'cruel-violet-hedgehog','cbZJYB1ujdg6sfgA1xSuB5');
/*!40000 ALTER TABLE `teacher_exams` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher_lecture`
--

DROP TABLE IF EXISTS `teacher_lecture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher_lecture` (
  `code` varchar(255) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `quarter` int(11) DEFAULT NULL,
  `sem` int(11) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `class` varchar(255) DEFAULT NULL,
  `resource` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`code`),
  KEY `FKrhx2sws0atsyq8uli29kjo72u` (`class`),
  KEY `FKe1bjosarrhlriqwuru6qct579` (`resource`),
  CONSTRAINT `FKe1bjosarrhlriqwuru6qct579` FOREIGN KEY (`resource`) REFERENCES `teacher_resources` (`code`),
  CONSTRAINT `FKrhx2sws0atsyq8uli29kjo72u` FOREIGN KEY (`class`) REFERENCES `room_shift_classes` (`room_classes_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher_lecture`
--

LOCK TABLES `teacher_lecture` WRITE;
/*!40000 ALTER TABLE `teacher_lecture` DISABLE KEYS */;
INSERT INTO `teacher_lecture` VALUES ('critical-moccasin-firefly','2021-08-31 09:55:49.501000','fasdfasdfasqwedasdfadsfas',1,1,'2021-08-31 09:55:49.501000','cbZJYB1ujdg6sfgA1xSuB5','excess-pink-booby'),('famous-cyan-bovid','2021-08-07 08:57:41.768000','qweqweqweqweqw',1,1,'2021-08-07 08:57:41.768000','11105f802asdfasdfasd','medieval-gold-pelican'),('identical-coral-camel','2021-08-31 04:07:54.173000','wdsfasdfasdfasdfasdf',1,1,'2021-08-31 04:07:54.173000','cbZJYB1ujdg6sfgA1xSuB5','excess-pink-booby');
/*!40000 ALTER TABLE `teacher_lecture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher_quizzes`
--

DROP TABLE IF EXISTS `teacher_quizzes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher_quizzes` (
  `code` varchar(255) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `deadline` datetime(6) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `high_grade` double DEFAULT NULL,
  `low_grade` double DEFAULT NULL,
  `quarter` int(11) DEFAULT NULL,
  `sem` int(11) DEFAULT NULL,
  `resource` varchar(255) DEFAULT NULL,
  `room_shift_class` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`code`),
  KEY `FKdhfgjia67omkd2fg7m63jtsyl` (`resource`),
  KEY `FK1lee0r02rqq2r01n4ndswarjy` (`room_shift_class`),
  CONSTRAINT `FK1lee0r02rqq2r01n4ndswarjy` FOREIGN KEY (`room_shift_class`) REFERENCES `room_shift_classes` (`room_classes_id`),
  CONSTRAINT `FKdhfgjia67omkd2fg7m63jtsyl` FOREIGN KEY (`resource`) REFERENCES `teacher_resources` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher_quizzes`
--

LOCK TABLES `teacher_quizzes` WRITE;
/*!40000 ALTER TABLE `teacher_quizzes` DISABLE KEYS */;
INSERT INTO `teacher_quizzes` VALUES ('light-jade-rabbit','2021-08-31 00:19:52.528000','2021-09-09 00:19:00.000000','asdfasdfahaskdbfjkasdf',100,0,1,1,'operational-sapphire-raccoon','1dEY28e7TRvsLGrTt4nqT6'),('naughty-black-centipede','2021-08-31 00:22:50.161000','2021-09-09 00:24:00.000000','asdfsadfasdfsfasdfsad',100,0,1,1,'operational-sapphire-raccoon','ddasd219de92asdasda'),('total-aquamarine-quail','2021-08-31 00:43:05.029000','2021-08-31 00:42:00.000000','asdfasdfajsdvgashdfkjasvdjfjasdfqweqweqwe',100,0,1,1,'operational-sapphire-raccoon','cbZJYB1ujdg6sfgA1xSuB5');
/*!40000 ALTER TABLE `teacher_quizzes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher_resources`
--

DROP TABLE IF EXISTS `teacher_resources`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher_resources` (
  `code` varchar(255) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `teacher` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`code`),
  KEY `FK57g7v84peopsajkr6ajwxpwxy` (`teacher`),
  CONSTRAINT `FK57g7v84peopsajkr6ajwxpwxy` FOREIGN KEY (`teacher`) REFERENCES `teacher` (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher_resources`
--

LOCK TABLES `teacher_resources` WRITE;
/*!40000 ALTER TABLE `teacher_resources` DISABLE KEYS */;
INSERT INTO `teacher_resources` VALUES ('artistic-rose-shark','2021-08-25 08:08:05.513000','sdfasdfasdsdfasdfasdfasdfasdf','276e6cc3-48d2-4fbc-9c0b-cd57363110fb.odt','fasdfasdf','Assignment','2021-08-25 08:08:05.514000','testTeacher',0),('central-yellow-jackal','2021-08-19 02:54:31.021000','asdfsdfqweqweqsdfasfdasdf','2dd14bb6-2ff1-4873-9348-ca7dbccb0fee.svg','sample','Assignment','2021-08-19 02:54:31.021000','testTeacher',1),('cruel-violet-hedgehog','2021-08-26 05:37:22.961000','sdfxcvzxvczxcvawfawewaerwer','b90b4512-cc29-425f-93cb-26a981526049.uml','qweqweasdf','Exam','2021-08-28 18:04:14.067000','testTeacher',1),('excess-pink-booby','2021-08-31 04:07:42.215000','asdfasdfasdfasdfasdf','7eff6efe-56d4-4352-902e-aeebbedc4419.xlsx','ffsdf','Lecture','2021-08-31 04:07:54.174000','testTeacher',1),('helpful-jade-earwig','2021-08-26 05:36:59.611000','asdfasdfasdfasdfasdfasdf','4d6a511f-8a0a-4ad3-a474-8751c47d600b.uml','asdfasdf','Exam','2021-08-26 05:38:47.796000','testTeacher',1),('medieval-gold-pelican','2021-08-07 08:56:30.366000','sfsaf','0ed5a60c-1267-4a9d-91a0-9f2c5fc9bffa.png','asdf','Lecture','2021-08-07 08:56:30.366000','qweqwe',1),('nuclear-harlequin-nightingale','2021-08-07 07:56:26.823000','asdfasdfqwedfasdfasdfaqweqw','50d8d43c-9c34-4af5-a747-fa4224088875.png','asdfasdf','Quiz','2021-08-07 07:56:26.823000','qweqwe',1),('operational-sapphire-raccoon','2021-08-19 02:53:58.394000','qweqwedfgsfgsdfgsdwer','f3d66198-ae85-4513-a6df-b283d2beaa9b.pdf','asdfasdf','Quiz','2021-08-26 05:57:57.775000','testTeacher',1),('original-green-dog','2021-08-28 18:03:54.667000','asdfasdfasasdfasdfasdfasdfasdfsdf','59e15876-0af0-4ddb-baf0-cc46447b427a.png','dasd','Exam','2021-08-28 18:03:54.667000','testTeacher',0),('outstanding-gold-scorpion','2021-08-07 08:57:01.211000','werwerwerwer','e7d06ed5-bffe-4c4c-ba0f-deb7822aa0e1.png','asdfsdfsdf','Assignment','2021-08-07 08:57:01.211000','qweqwe',1),('shocked-bronze-albatross','2021-08-20 16:09:48.984000','test Exam\n','0235fdb3-9f3f-4974-9947-d7842c58f04b.xlsx','fasdfsd','Exam','2021-08-20 16:09:48.984000','testTeacher',1),('solid-chocolate-antlion','2021-08-20 16:11:40.456000','asdfasdfasdfasdfasd','aa552afe-7392-42d1-a714-0cb6dc9b8a20.pdf','asdasd','Quiz','2021-08-20 16:11:40.456000','testTeacher',1),('tall-beige-rook','2021-08-07 09:42:16.664000','This is lecture','99fe4244-63ad-44a5-ae72-d716d58065fd.png','smaple','Lecture','2021-08-07 09:42:16.665000','12345',1),('visiting-lavender-dog','2021-08-07 08:56:45.856000','asdfsadfasdfasdf','d267a754-cfe8-4097-95c1-474c37b05c80.png','asdasd','Exam','2021-08-07 08:56:45.856000','qweqwe',1);
/*!40000 ALTER TABLE `teacher_resources` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_birthdate` datetime(6) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  `user_first_name` varchar(255) DEFAULT NULL,
  `user_gender` varchar(255) DEFAULT NULL,
  `is_account_not_expired` bit(1) DEFAULT NULL,
  `is_account_not_locked` bit(1) DEFAULT NULL,
  `is_credential_not_expired` bit(1) DEFAULT NULL,
  `is_enable` bit(1) DEFAULT NULL,
  `user_last_name` varchar(255) DEFAULT NULL,
  `user_middle_name` varchar(255) DEFAULT NULL,
  `user_password` varchar(255) DEFAULT NULL,
  `picture` text DEFAULT NULL,
  `user_sufix` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `user_role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=211 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (132,'2021-06-28 20:00:00.000000','2021-08-01 06:54:37.667000','ellyky2121@gmail.com','dina muliasdasd','Female','','','','','asdf','?','$2a$12$wUBzrDP72YHpEvBDbJmmSOVxyEGeFu9YkFjcFhTilhmeOW3LEiob2','30989909-8a04-48e6-a6d6-f64e543409d7.png','?','2021-08-04 08:11:40.870000','ADMIN'),(133,'2021-07-31 20:00:00.000000','2021-08-01 06:54:37.696000','kurt@email.com','test','Male','','','','','test','asdfasdfa','$2a$10$KSLKlewLQyLpz/STXAbloevh13c/ysZhqEl4nuU2r8S3xfZzO9z.q','ecd2ed85-8702-4daa-9b4f-8bb48877b7c9.png','','2021-08-23 22:03:54.106000','ADMIN'),(134,'2021-08-01 06:54:37.635000','2021-08-01 06:54:37.705000','evematuran2@gmail.com','?','Male','','','','','?','?','$2y$12$kyy1y35jLNMpdrKDaeoPpOImzEJ2OIg4t/5V4OU2Fi3l0nI92/tme','30989909-8a04-48e6-a6d6-f64e543409d7.png','','2021-08-01 06:54:37.705000','ADMIN'),(135,'2021-08-01 07:02:07.023000','2021-08-01 07:02:07.044000','akosigeorge1011@gmail.com','sampple','Male','','','','','sampple','sampple','$2y$12$uHtgKts9cur7/3Can1d1VuQ1BpePn7vOjIaJZaiKe1PHCFyjY66I2','4be33a68-c78b-4a7a-922b-8196bacb0e85.jpg','asd','2021-08-01 07:07:09.592000','TEACHER'),(136,'2021-08-01 07:08:20.308000','2021-08-01 07:08:20.317000','asdf@email.com','asdf','Female','','','','','asdfsdf','asdfasdf','$2y$12$uHtgKts9cur7/3Can1d1VuQ1BpePn7vOjIaJZaiKe1PHCFyjY66I2','4be33a68-c78b-4a7a-922b-8196bacb0e85.jpg','asdf','2021-08-01 07:12:38.947000','TEACHER'),(137,'2021-08-01 07:14:08.920000','2021-08-01 07:14:08.921000','email@email.com','asdf','Male','','','','','sdfasdfsdfasdf','adfa','$2y$12$uHtgKts9cur7/3Can1d1VuQ1BpePn7vOjIaJZaiKe1PHCFyjY66I2','4be33a68-c78b-4a7a-922b-8196bacb0e85.jpg','sdf','2021-08-01 07:18:33.106000','TEACHER'),(138,'2021-08-01 07:18:58.719000','2021-08-01 07:18:58.720000','gmail@gmail.com','asdfas','Male','','','','','sfsdfas','dfasdfa','$2y$12$uHtgKts9cur7/3Can1d1VuQ1BpePn7vOjIaJZaiKe1PHCFyjY66I2','4be33a68-c78b-4a7a-922b-8196bacb0e85.jpg','asdfa','2021-08-01 07:19:34.281000','TEACHER'),(139,'2021-07-31 20:00:00.000000','2021-08-01 07:29:24.223000','student@email.com','student','Male','','','','','student','aaa','$2a$12$y2o20hT/ZO2lcjpHUTx1aOw2cmv75F4s90jCExOPJa3ZIwcsl6A6C','2089b62d-8e80-4630-ba49-7a8d5ffe67c1.svg','student','2021-08-25 05:23:50.664000','STUDENT'),(140,'2021-08-01 07:37:09.093000','2021-08-01 07:37:09.094000','asff@gmail.com','Kurt Lupin Orioque','Male','','','','','Orioque','Concepcion','$2a$10$tOQiDcJ6pPf0ZWfx1mfY/.dRaT1V3Dhl3UfFIDya5.sWsXr775RNW','30989909-8a04-48e6-a6d6-f64e543409d7.png','sdf','2021-08-12 03:37:05.948000','TEACHER'),(141,'2021-08-03 02:07:07.712000','2021-08-03 02:07:07.719000','qweasdasd','?','Male','\0','\0','\0','\0','?','?','?','30989909-8a04-48e6-a6d6-f64e543409d7.png','?','2021-08-03 02:07:07.719000','TEACHER'),(144,'2021-08-07 09:31:52.607000','2021-08-07 09:31:52.611000','s@email.com','student','Male','','','','','studetn','student','$2a$12$9kSGKHnPkE1qL2xNwvcKG.8WIBRETQZKjnVZgXfIjPn585IBeJi8m','c588ef80-d9c0-4005-a32c-f134be5b110e.png','','2021-08-07 09:38:14.768000','STUDENT'),(145,'2021-08-07 09:32:50.025000','2021-08-07 09:32:50.025000','teacher@email.com','teacher','Female','','','','','teacher','teacher','$2a$12$KSYFfEjsTVOOocUNxxwlWO0NfmxRuSMNOWddl4rr8wlD8WBVtJBAG','c588ef80-d9c0-4005-a32c-f134be5b110e.png','?','2021-08-07 09:41:18.450000','TEACHER'),(147,'2021-08-08 04:49:37.539000','2021-08-08 04:49:37.544000','qweqweqwe','?','','\0','\0','\0','\0','?','?','?','e63e6508-0bfe-4203-af11-8112289ea180.png','?','2021-08-08 04:49:37.544000','TEACHER'),(149,'2021-08-08 04:52:46.303000','2021-08-08 04:52:46.308000','zxcvzxcv','?','','\0','\0','\0','\0','?','?','?','e63e6508-0bfe-4203-af11-8112289ea180.png','?','2021-08-08 04:52:46.308000','TEACHER'),(152,'2021-08-08 19:31:24.861000','2021-08-08 19:31:24.868000','12312312312','?','','\0','\0','\0','\0','?','?','','e63e6508-0bfe-4203-af11-8112289ea180.png','','2021-08-08 19:31:24.868000','STUDENT'),(153,'2021-08-08 21:31:55.349000','2021-08-08 21:31:55.350000','asdasdasdasd','?','','\0','\0','\0','\0','?','?','','f26baa8b-c3ab-4742-a9cf-562f793ce03e.png','','2021-08-08 21:31:55.350000','STUDENT'),(154,'2021-08-08 21:32:02.105000','2021-08-08 21:32:02.106000','asdasdasdasdas','?','','\0','\0','\0','\0','?','?','','f26baa8b-c3ab-4742-a9cf-562f793ce03e.png','','2021-08-08 21:32:02.106000','STUDENT'),(155,'2021-08-09 09:53:30.513000','2021-08-09 09:53:30.526000','asdaqq','?','','\0','\0','\0','\0','?','?','','f26baa8b-c3ab-4742-a9cf-562f793ce03e.png','','2021-08-09 09:53:30.526000','STUDENT'),(159,'2021-08-17 01:04:37.054000','2021-08-17 01:04:37.067000','a@gmail.com','kurt','Male','','','','\0','asf','asdf','$2a$12$7Hdn.ZWLU2Tj629kPDrWQ.3Oa4QyDj61hgRbw4ciAOZylNUMIbvE6','?','sdf','2021-08-17 01:05:17.776000','TEACHER'),(160,'2021-08-17 02:54:31.719000','2021-08-17 02:54:31.731000','a','sadf','Male','','','','\0','asdfasdf','sdfasdf','$2a$12$G634lIMliFsXZNJOynDleO4GbbKx7Oy3JyJBbSz3meiF3zw.3e1U6','?','sadfsadf','2021-08-17 02:54:56.031000','TEACHER'),(161,'2021-08-17 03:12:44.564000','2021-08-17 03:12:44.574000','asdf','?','','\0','\0','\0','\0','?','?','?','?','?','2021-08-17 03:12:44.574000','TEACHER'),(164,'2021-08-17 09:23:09.511000','2021-08-17 09:23:09.520000','qeasdf@email.com','?','','','','','\0','?','?','$2a$12$9aNZQjnLmTlBtYMVY0JtF.HXt3.pN8YxwxhSCH/cxcJFm2/VlDuWC','?','?','2021-08-17 09:23:09.520000','ADMIN'),(165,'2021-08-17 09:28:16.441000','2021-08-17 09:28:16.442000','qweqweq@email.com','?','','','','','\0','?','?','$2a$12$9aNZQjnLmTlBtYMVY0JtF.HXt3.pN8YxwxhSCH/cxcJFm2/VlDuWC','?','?','2021-08-17 09:28:16.442000','ADMIN'),(166,'2021-08-17 09:28:54.659000','2021-08-17 09:28:54.659000','qweqwe@email.com','?','','','','','\0','?','?','$2a$12$9aNZQjnLmTlBtYMVY0JtF.HXt3.pN8YxwxhSCH/cxcJFm2/VlDuWC','?','?','2021-08-17 09:28:54.659000','ADMIN'),(168,'2021-08-17 15:20:40.663000','2021-08-17 15:20:40.668000','asfasd@email.com','?','','','\0','','\0','?','?','$2a$12$9aNZQjnLmTlBtYMVY0JtF.HXt3.pN8YxwxhSCH/cxcJFm2/VlDuWC','?','?','2021-08-22 23:05:19.793000','ADMIN'),(172,'2021-08-18 20:00:00.000000','2021-08-19 02:32:37.512000','testTeacher@gmail.com','test','Male','','','','','teacher','teacher','$2a$12$AQCjJ9fTgy3WizDNGFuBte1NIFywUb48rWBnDXmPEdxtIekP9MtdK','?','f','2021-08-25 05:21:38.500000','TEACHER'),(173,'2021-08-21 13:54:01.857000','2021-08-21 13:54:01.866000','sdfsfqwe','?','','\0','\0','\0','\0','?','?','','?','','2021-08-21 13:54:01.866000','STUDENT'),(180,'2021-08-24 21:32:01.057000','2021-08-24 21:32:01.076000','adfasdf','?','','\0','\0','\0','\0','?','?','','?','','2021-08-24 21:32:01.076000','STUDENT'),(181,'2021-08-25 05:02:12.313000','2021-08-25 05:02:12.313000','asdfqweqweqweqweq','?','','\0','\0','\0','\0','?','?','?','?','?','2021-08-25 05:02:12.313000','TEACHER'),(183,'2021-08-29 21:33:07.445000','2021-08-29 21:33:07.446000','12311','?','','\0','\0','\0','\0','?','?','?','?','?','2021-08-29 21:33:07.446000','TEACHER'),(185,NULL,'2021-09-04 06:01:59.980000','sfadf@email.com','asdf','Male','','','','\0','fas','asdf','$2a$12$troyA55y/GQ5KP733SKFsebMaHyRPeKjyHhmn7Zf8LhCZH2UnWTc.',NULL,'f','2021-09-04 06:01:59.980000',NULL),(186,NULL,'2021-09-04 06:02:56.008000','sdff@emaud.com','sadfsd','Male','','','','\0','sdf','sadf','$2a$12$Wr1cIbsdCxTtSGdqs0xAmO67vJhluS5KovTlEbCuWMBpilVDUxV8G',NULL,'f','2021-09-04 06:02:56.008000',NULL),(187,NULL,'2021-09-04 06:04:29.768000','asffa@email.com','sadf','Male','','','','\0','asdfasdf','sf','$2a$12$5Au50qCbEvLcethuXNf0Qu1jaaQzT6roeYLWfbaPns/JZdWAHo7eS',NULL,'f','2021-09-04 06:04:29.768000',NULL),(188,NULL,'2021-09-04 06:08:07.289000','kurt@email.comd','asfd','Male','','','','\0','asdf','asdf','$2a$12$uxrLf4PD.PODGo9dmevLYu2CmlLgJtXUL9pEApP9D1gGrZDvIFyWC',NULL,'f','2021-09-04 06:08:07.289000',NULL),(189,NULL,'2021-09-04 06:09:19.139000','adqsdf@email.com','sadf','Male','','','','\0','asdf','adf','$2a$12$VZHZCnSZaN4kUTYBZhQ.cuVzHdp1eUIR/50Vn3s26uKpSDSzW7wa6',NULL,'sdf','2021-09-04 06:09:19.139000',NULL),(190,NULL,'2021-09-04 06:12:46.549000','asdfasdfas@email.com','asdfasdf','Male','','','','\0','adasf','sdfasf','$2a$12$v97reXm6rBem1QfWlV5HMOBekXXwtKhQJdFK//hjYExiDC9OMwElm',NULL,'q','2021-09-04 06:12:46.549000','TEACHER'),(191,NULL,'2021-09-04 06:14:57.489000','asfsafd@eamail.com','asdf','Male','','','','\0','asdf','asdf','$2a$12$LrILjgbXpQwyru3L.3tyHOSejX92X0sJk3bRvBYDSvggy0UWpPPDi',NULL,'f','2021-09-04 06:14:57.489000','TEACHER'),(192,'2011-09-01 00:00:00.000000','2021-09-04 06:16:09.747000','afsdfasdf@email.com','asdfasdf','Male','','','','\0','asdfq','asdf','$2a$12$sHakaznc0Y0pEaxLxFtbK.xZuSv8.H7K4Euyc9fx.hHVvaDZBZSBC',NULL,'f','2021-09-04 06:16:09.747000','TEACHER'),(206,'2021-09-04 08:13:22.816000','2021-09-04 08:13:22.827000','fasdf@email.com','asdf','?','','','','\0','fdf','','$2a$10$RYcsjzGN8hBLSJPbxD4ik.FSXWgLemp8lVofTiAvcWJv4uSPXYcJK',NULL,'','2021-09-04 08:13:22.828000','STUDENT'),(207,'2021-09-04 08:13:26.756000','2021-09-04 08:13:26.757000','qqqq@email.com','asdf','?','','','','\0','fffasf','','$2a$10$gXWksI3lHIR6n3oqBqcbx.KfQrRR1IDaEHjLXg8CHV6yHCxD.11Hq',NULL,'','2021-09-04 08:13:26.757000','STUDENT'),(208,'2021-09-04 08:13:30.373000','2021-09-04 08:13:30.374000','asdasdq@email.com','asdfsa','?','','','','\0','asdfs','','$2a$10$qnx6MNG6gyv/d74IQFB94.7qm1peBd2qjzLD8hVCZjxOLikc8Kqaa',NULL,'','2021-09-04 08:13:30.374000','STUDENT'),(209,'2021-09-04 08:13:33.999000','2021-09-04 08:13:34.000000','asdqfa@email.com','asdfasdfas','?','','','','\0','sadf','','$2a$10$WK.b85sBjr23q2Lg7pmUNuERVeRnzktpCYNq8q9lEfL6Y2IvSQAO2',NULL,'','2021-09-04 08:13:34.000000','STUDENT'),(210,'2021-09-04 08:13:37.562000','2021-09-04 08:13:37.563000','qweqwe@email.com','asdfasdf','?','','','','\0','asdfas','','$2a$10$gpVfQXuY6/4i/KCo.i2Sn.3iIHlvXz2RUOrDHu2FPANbRQe/yqI4i',NULL,'','2021-09-04 08:13:37.563000','STUDENT');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-10-08 15:56:16
