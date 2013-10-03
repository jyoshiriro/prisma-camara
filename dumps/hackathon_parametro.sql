CREATE DATABASE  IF NOT EXISTS `hackathon` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `hackathon`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: hackathon
-- ------------------------------------------------------
-- Server version	5.5.34

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
-- Table structure for table `parametro`
--

DROP TABLE IF EXISTS `parametro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parametro` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `descricao` varchar(1024) DEFAULT NULL,
  `sigla` varchar(30) NOT NULL,
  `valor` varchar(512) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parametro`
--

LOCK TABLES `parametro` WRITE;
/*!40000 ALTER TABLE `parametro` DISABLE KEYS */;
INSERT INTO `parametro` VALUES (1,0,'URL para chegar a página de Biografia de deputado(a)','url_biografia_deputado','http://www2.camara.leg.br/deputados/pesquisa/layouts_deputados_biografia?pk='),(2,0,'URL do servico que retorna o XML com todos os deputados atuais','url_listagem_deputados','http://www.camara.gov.br/SitCamaraWS/Deputados.asmx/ObterDeputados'),(3,0,'URL do serviço que retorna o XML com Proposições','url_listagem_proposicoes','http://www.camara.gov.br/SitCamaraWS/Proposicoes.asmx/ListarProposicoes?numero=&datApresentacaoIni=&datApresentacaoFim=&autor=&parteNomeAutor=&siglaPartidoAutor=&siglaUFAutor=&generoAutor=&codEstado=&codOrgaoEstado=&emTramitacao=&sigla=${sigla}&ano=${ano}'),(4,0,'URL do serviço que retorna o XML com os tipos de Proposições','url_listagem_tipos_proposicoes','http://www.camara.gov.br/SitCamaraWS/Proposicoes.asmx/ListarSiglasTipoProposicao'),(5,0,'URL do serviço que retorna o XML com as votações de uma Proposição','url_votacoes_proposicoes','http://www.camara.gov.br/SitCamaraWS/Proposicoes.asmx/ObterVotacaoProposicao?tipo=${tipo}&numero=${numero}&ano=${ano}');
/*!40000 ALTER TABLE `parametro` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-10-03 14:16:18
