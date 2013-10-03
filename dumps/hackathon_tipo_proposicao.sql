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
-- Table structure for table `tipo_proposicao`
--

DROP TABLE IF EXISTS `tipo_proposicao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_proposicao` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `ativo` bit(1) NOT NULL,
  `descricao` varchar(70) NOT NULL,
  `genero` varchar(1) NOT NULL,
  `sigla` varchar(8) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=147 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_proposicao`
--

LOCK TABLES `tipo_proposicao` WRITE;
/*!40000 ALTER TABLE `tipo_proposicao` DISABLE KEYS */;
INSERT INTO `tipo_proposicao` VALUES (1,0,'','Autógrafo','o','AA'),(2,0,'','Adendo','o','ADD'),(3,0,'','Anteprojeto','o','APJ'),(4,0,'','Ato Convocatório','o','ATC'),(5,0,'\0','Ato do Presidente','o','ATOP'),(6,0,'','Aviso','o','AV'),(7,0,'','Aviso (CN)','o','AVN'),(8,0,'','Comunicado de alteração do controle societário','o','CAC'),(9,0,'','Relatório de Atividades','o','CAE'),(10,0,'','Consulta do Congresso Nacional','a','CCN'),(11,0,'','Relatório do COI','o','COI'),(12,0,'','Consulta','a','CON'),(13,0,'\0','CST','o','CST'),(14,0,'','Complementação de Voto','a','CVO'),(15,0,'','Contestação ao Voto do Relator','a','CVR'),(16,0,'','Denúncia por crime de responsabilidade','a','DCR'),(17,0,'','Decisão','a','DEC'),(18,0,'','Denúncia','a','DEN'),(19,0,'','Discurso','o','DIS'),(20,0,'','Documentos internos','o','DOC'),(21,0,'','Destaque','o','DTQ'),(22,0,'','Declaração de Voto','a','DVT'),(23,0,'','Emenda Substitutiva Aglutinativa Global','a','EAG'),(24,0,'','Emenda Aglutinativa de Plenário','a','EMA'),(25,0,'','Emenda na Comissão','a','EMC'),(26,0,'','Emenda','a','EMD'),(27,0,'','Emenda à LDO','a','EML'),(28,0,'','Emenda ao Orçamento','a','EMO'),(29,0,'','Emenda de Plenário','a','EMP'),(30,0,'','Emenda de Relator','a','EMR'),(31,0,'','Emenda de Relator Parcial','a','EMRP'),(32,0,'','Emenda/Substitutivo do Senado','a','EMS'),(33,0,'','Emenda ao Plano Plurianual','a','EPP'),(34,0,'','Emenda de Redação','a','ERD'),(35,0,'','Errata','a','ERR'),(36,0,'','Emenda ao Substitutivo','a','ESB'),(37,0,'','Emenda Substitutiva de Plenário','a','ESP'),(38,0,'\0','Exposição','o','EXP'),(39,0,'\0','IAN','o','IAN'),(40,0,'','Indicação de Autoridade','a','INA'),(41,0,'','Indicação','a','INC'),(42,0,'','Manifestação do Relator','a','MAN'),(43,0,'','Mensagem (CN)','o','MCN'),(44,0,'','Mensagem do Ministério Público da União.','a','MMP'),(45,0,'','Medida Provisória','a','MPV'),(46,0,'','Mensagem','a','MSC'),(47,0,'','Mensagem (SF)','a','MSF'),(48,0,'','Mensagem (CN)','a','MSG'),(49,0,'','Mensagem do Supremo Tribunal Federal','a','MST'),(50,0,'','Mensagem do Tribunal de Contas da União','a','MTC'),(51,0,'','Norma Interna','a','NIC'),(52,0,'\0','Não Informada','o','NINF'),(53,0,'','Objeto de Deliberação','a','OBJ'),(54,0,'','Ofício','o','OF'),(55,0,'','Ofício Externo','o','OF.'),(56,0,'','Ofício (CN)','o','OFN'),(57,0,'','Ofício do Senado Federal','o','OFS'),(58,0,'\0','OFT','o','OFT'),(59,0,'','Parecer (CD)','o','P.C'),(60,0,'','Parecer de Comissão','o','PAR'),(61,0,'','Parecer de Comissão para Redação Final','o','PARF'),(62,0,'\0','PCA','o','PCA'),(63,0,'\0','PDA','o','PDA'),(64,0,'','Projeto de Decreto Legislativo','o','PDC'),(65,0,'','Projeto de Decreto Legislativo (CN)','o','PDN'),(66,0,'','Projeto de Decreto Legislativo (SF)','o','PDS'),(67,0,'','Parecer à Emenda Aglutinativa','o','PEA'),(68,0,'','Proposta de Emenda à Constituição','a','PEC'),(69,0,'','Parecer às Emendas de Plenario','o','PEP'),(70,0,'','Parecer às emendas apresentadas ao Substitutivo do Relator','o','PES'),(71,0,'','Petição','a','PET'),(72,0,'','Proposta de Fiscalização e Controle','a','PFC'),(73,0,'','Projeto de Lei','o','PL'),(74,0,'','Projeto de Lei da Câmara dos Deputados (SF)','o','PLC'),(75,0,'','Projeto de Lei (CN)','o','PLN'),(76,0,'\0','Projeto de lei orçamentária Anual','o','PLOA'),(77,0,'','Projeto de Lei Complementar','o','PLP'),(78,0,'','Projeto de Lei do Senado Federal','o','PLS'),(79,0,'','Projeto de Lei de Conversão','o','PLV'),(80,0,'','Parecer Proferido em Plenário','o','PPP'),(81,0,'','Parecer Reformulado de Plenário','o','PPR'),(82,0,'\0','PRA','o','PRA'),(83,0,'','Projeto de Resolução','o','PRC'),(84,0,'','Projeto de Resolução do Senado Federal','o','PRF'),(85,0,'','Parecer do Relator','o','PRL'),(86,0,'','Projeto de Resolução do Congresso Nacional','o','PRN'),(87,0,'','Proposta','a','PRO'),(88,0,'','Parecer do Relator Parcial','o','PRP'),(89,0,'','Parecer Reformulado','o','PRR'),(90,0,'','Parecer à Redação para o Segundo Turno','o','PRST'),(91,0,'','Parecer Técnico','o','PRT'),(92,0,'','Parecer Vencedor','o','PRV'),(93,0,'','Proposta de Redação do Vencido em Primeiro Turno','o','PRVP'),(94,0,'','Parecer às Emendas ou ao Substitutivo do Senado - Notas Taquigráficas','o','PSS'),(95,0,'','Recurso do Congresso Nacional','o','R.C'),(96,0,'','Relatório Setorial','o','RAT'),(97,0,'\0','RCM','o','RCM'),(98,0,'','Requerimento de Instituição de CPI','o','RCP'),(99,0,'','Redação Final','a','RDF'),(100,0,'','Redação do Vencido','a','RDV'),(101,0,'','Recurso','o','REC'),(102,0,'','Relatório','a','REL'),(103,0,'','Reclamação','a','REM'),(104,0,'','Representação','a','REP'),(105,0,'','Requerimento','o','REQ'),(106,0,'\0','Refomulação de Parecer - art. 130, parágrafo único do RICD.','a','RFP'),(107,0,'','Requerimento de Informação','o','RIC'),(108,0,'','Requerimento de Resolução Interna','a','RIN'),(109,0,'','Relatório Final','o','RLF'),(110,0,'','Relatório Prévio','o','RLP'),(111,0,'','Relatório Prévio Reformulado','o','RLP(R)'),(112,0,'','Relatório Prévio Vencedor','o','RLP(V)'),(113,0,'','Relatório Parcial','o','RPA'),(114,0,'','Relatório Preliminar','o','RPL'),(115,0,'','Relatório Preliminar com Emendas','o','RPLE'),(116,0,'\0','Representação','a','RPR'),(117,0,'\0','RQA','o','RQA'),(118,0,'\0','RQC','o','RQC'),(119,0,'\0','Requerimento do Congresso Nacional','o','RQN'),(120,0,'','Requerimento de Plenário','o','RQP'),(121,0,'','Relatório de Receita','o','RRC'),(122,0,'','Relatório do Relator','o','RRL'),(123,0,'','Redação para o segundo turno','a','RST'),(124,0,'\0','Mensagem de Rádio e Televisão','a','RTV'),(125,0,'\0','Subemenda Aglutinativa Substitutiva de Plenário','a','SAP'),(126,0,'','Subemenda','a','SBE'),(127,0,'','Substitutivo','o','SBT'),(128,0,'','Sugestão de Emenda à LDO - CLP','a','SDL'),(129,0,'\0','Ofício da Mesa','o','SGM'),(130,0,'','Solicitação de Informação ao TCU','a','SIT'),(131,0,'','Sugestão de Emenda à LDO - Comissões','a','SLD'),(132,0,'','Sugestão de Emenda ao Orçamento - CLP','a','SOA'),(133,0,'','Sugestão de Emenda ao Orçamento - Comissões','a','SOR'),(134,0,'','Sugestão de Emenda ao PPA - CLP','a','SPA'),(135,0,'','Sugestão de Emenda ao PPA - revisão (CLP)','a','SPA-R'),(136,0,'','Sugestão de Emenda ao PPA - Comissões','a','SPP'),(137,0,'','Sugestão de Emenda ao PPA - revisão (Comissões)','o','SPP-R'),(138,0,'\0','Sugestão de Requerimento de Audiência Pública','o','SRAP'),(139,0,'','Sugestão de Emenda a Relatório','a','SRL'),(140,0,'','Subemenda Substitutiva de Plenário','a','SSP'),(141,0,'\0','Ofício','o','STF'),(142,0,'','Sugestão a Projeto de Consolidação de Leis','a','SUC'),(143,0,'','Sugestão','a','SUG'),(144,0,'','Súmula','a','SUM'),(145,0,'','Termo de Implementação','o','TER'),(146,0,'','Voto em Separado','o','VTS');
/*!40000 ALTER TABLE `tipo_proposicao` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-10-03 14:16:21
