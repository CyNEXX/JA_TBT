-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: tbtsimple
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `clienti`
--
DROP DATABASE IF EXISTS `tbtsimple`;
CREATE DATABASE `tbtsimple`;
USE `tbtsimple`;
DROP TABLE IF EXISTS `clienti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clienti` (
  `id_client` int(11) NOT NULL AUTO_INCREMENT,
  `prenume_client` varchar(45) NOT NULL,
  `nume_client` varchar(45) NOT NULL,
  `adresa_client` varchar(45) NOT NULL,
  `telefon_client` varchar(45) NOT NULL,
  `nrVanzari` int(11) DEFAULT '0',
  `nrProduse` int(11) DEFAULT '0',
  `cifraVanzari` double DEFAULT '0',
  `client_activ` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`id_client`),
  FULLTEXT KEY `idx_nume_prenume` (`nume_client`,`prenume_client`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clienti`
--

LOCK TABLES `clienti` WRITE;
/*!40000 ALTER TABLE `clienti` DISABLE KEYS */;
INSERT INTO `clienti` VALUES (1,'Alexandru','Raileanu','Bucuresti','0784123231',0,0,0,1),(2,'Razvan','Dragomir','Pitesti','0768123321',0,0,0,1),(3,'Gabriel','Olteanu','Braila','0754456988',0,0,0,1),(4,'Lavinia','Stefanescu','Vaslui','0723568945',0,0,0,1),(5,'Ramona','Contstantin','Bucuresti','0789785612',0,0,0,1),(6,'Ion','Ionescu','Timisoara','0745568794',0,0,0,1),(7,'Bogdan','Iordache','Prahova','0723124578',0,0,0,1),(8,'Veronica','Lungu','Timis','0789457143',0,0,0,1),(9,'Georgiana','Alexandrescu','Buhusi','0744546512',0,0,0,1),(10,'Ramona','Romanescu','Roman','0788597513',0,0,0,1),(11,'Zarneanu','Ilie','Olt','0744558430',0,0,0,1),(12,'Eugen','Rotaru','Hunedoara','0755431268',0,0,0,1),(13,'Cub','Ogre','Pestera','0741257955',0,0,0,1);
/*!40000 ALTER TABLE `clienti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produse`
--

DROP TABLE IF EXISTS `produse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produse` (
  `id_produs` int(11) NOT NULL AUTO_INCREMENT,
  `nume_produs` varchar(45) NOT NULL,
  `descriere_produs` varchar(45) DEFAULT NULL,
  `stoc_produs` int(11) DEFAULT '0',
  `pret` double NOT NULL DEFAULT '0',
  `totalVandute` int(11) DEFAULT '0',
  `produs_activ` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`id_produs`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produse`
--

LOCK TABLES `produse` WRITE;
/*!40000 ALTER TABLE `produse` DISABLE KEYS */;
INSERT INTO `produse` VALUES (1,'Bormasina','cu percutie',7,500,0,1),(2,'Pistol de zlipit','cu adeziv',15,50,0,1),(3,'Telemetru','cu acumulator',10,525,0,1),(4,'Motostivuitor','cu cromozomi xx',1982,60000,0,1),(5,'Ciorapi de drama','trainici',15,10,0,1),(6,'Lanterna','cu acumulator',16,100,0,1),(7,'Masina de taiat frunze','pentru caini',11,2000,0,1),(8,'Cutit ascutit','taiat maioneza',3,80,0,1),(9,'Flota','toata',2,2,0,1),(10,'Costum gastronaut','pentru peripetiile din bucatarie',9,100,0,1),(11,'Bicicleta','pliabila',10,1200,0,1),(12,'Caine de paza rau','ca e cu acumulator',52,30,0,1),(13,'NVR','calitatea I',39,130,0,1),(14,'Teava rectangulara','L= 2000mm 50x30x3 mm',7,150,0,1),(15,'Aparat de tantar','ii musca',101,25,0,1),(16,'Carbit','Mult',10,50,0,1),(17,'Spirit Sanitar','cu Albastru de Voronet',10,50,0,1),(18,'Aspirator','Cu sac',10,500,0,1);
/*!40000 ALTER TABLE `produse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vanzari`
--

DROP TABLE IF EXISTS `vanzari`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vanzari` (
  `id_vanzare` int(11) NOT NULL AUTO_INCREMENT,
  `id_client` int(11) NOT NULL,
  `id_produs` int(11) NOT NULL,
  `id_comanda` int(11) NOT NULL,
  `cantitate_vanzare` int(11) NOT NULL,
  `pret_produs` double NOT NULL,
  `total_produs` double NOT NULL,
  PRIMARY KEY (`id_vanzare`),
  KEY `fk_id_produs_idx` (`id_produs`),
  KEY `fk_id_client_idx` (`id_client`),
  CONSTRAINT `fk_id_client` FOREIGN KEY (`id_client`) REFERENCES `clienti` (`id_client`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_id_produs` FOREIGN KEY (`id_produs`) REFERENCES `produse` (`id_produs`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vanzari`
--

LOCK TABLES `vanzari` WRITE;
/*!40000 ALTER TABLE `vanzari` DISABLE KEYS */;
/*!40000 ALTER TABLE `vanzari` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-24 21:27:37
