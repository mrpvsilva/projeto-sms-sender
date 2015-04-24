CREATE DATABASE  IF NOT EXISTS `siga` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `siga`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: siga
-- ------------------------------------------------------
-- Server version	5.5.25a

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
-- Table structure for table `telefones`
--

DROP TABLE IF EXISTS `telefones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `telefones` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ddd` varchar(2) NOT NULL,
  `numero` varchar(10) NOT NULL,
  `operadora` varchar(20) NOT NULL,
  `TYPE` varchar(31) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `telefones`
--

LOCK TABLES `telefones` WRITE;
/*!40000 ALTER TABLE `telefones` DISABLE KEYS */;
/*!40000 ALTER TABLE `telefones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eventos`
--

DROP TABLE IF EXISTS `eventos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eventos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(40) NOT NULL,
  `tipo` varchar(45) NOT NULL,
  `numeroconvidados` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `datacriacao` datetime NOT NULL,
  `dataevento` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nome_UNIQUE` (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eventos`
--

LOCK TABLES `eventos` WRITE;
/*!40000 ALTER TABLE `eventos` DISABLE KEYS */;
/*!40000 ALTER TABLE `eventos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clienteseventos`
--

DROP TABLE IF EXISTS `clienteseventos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clienteseventos` (
  `idevento` int(11) NOT NULL,
  `idcliente` int(11) NOT NULL,
  PRIMARY KEY (`idevento`,`idcliente`),
  KEY `fk_eventos_has_clientes_clientes1_idx` (`idcliente`),
  KEY `fk_eventos_has_clientes_eventos1_idx` (`idevento`),
  CONSTRAINT `fk_eventos_has_clientes_clientes1` FOREIGN KEY (`idcliente`) REFERENCES `clientes` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_eventos_has_clientes_eventos1` FOREIGN KEY (`idevento`) REFERENCES `eventos` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clienteseventos`
--

LOCK TABLES `clienteseventos` WRITE;
/*!40000 ALTER TABLE `clienteseventos` DISABLE KEYS */;
/*!40000 ALTER TABLE `clienteseventos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientesitensextras`
--

DROP TABLE IF EXISTS `clientesitensextras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clientesitensextras` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `quantidade` int(11) NOT NULL,
  `iditem` int(11) NOT NULL,
  `idevento` int(11) NOT NULL,
  `idcliente` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_unique` (`iditem`,`idevento`,`idcliente`),
  KEY `fk_itensextraseventos_itens1_idx` (`iditem`),
  KEY `fk_clientesitensextras_clienteseventos1_idx` (`idevento`,`idcliente`),
  CONSTRAINT `fk_clientesitensextras_clienteseventos1` FOREIGN KEY (`idevento`, `idcliente`) REFERENCES `clienteseventos` (`idevento`, `idcliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_itensextraseventos_itens1` FOREIGN KEY (`iditem`) REFERENCES `itens` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientesitensextras`
--

LOCK TABLES `clientesitensextras` WRITE;
/*!40000 ALTER TABLE `clientesitensextras` DISABLE KEYS */;
/*!40000 ALTER TABLE `clientesitensextras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tiposservicos`
--

DROP TABLE IF EXISTS `tiposservicos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tiposservicos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `ativo` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nome_UNIQUE` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tiposservicos`
--

LOCK TABLES `tiposservicos` WRITE;
/*!40000 ALTER TABLE `tiposservicos` DISABLE KEYS */;
INSERT INTO `tiposservicos` VALUES (1,'Fotografia',1);
/*!40000 ALTER TABLE `tiposservicos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `debito`
--

DROP TABLE IF EXISTS `debito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `debito` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idevento` int(11) NOT NULL,
  `idcliente` int(11) NOT NULL,
  `valor` decimal(12,2) DEFAULT NULL,
  `operacao` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_debito_clienteseventos1_idx` (`idevento`,`idcliente`),
  CONSTRAINT `fk_debito_clienteseventos1` FOREIGN KEY (`idevento`, `idcliente`) REFERENCES `clienteseventos` (`idevento`, `idcliente`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `debito`
--

LOCK TABLES `debito` WRITE;
/*!40000 ALTER TABLE `debito` DISABLE KEYS */;
/*!40000 ALTER TABLE `debito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fornecedoreseventos`
--

DROP TABLE IF EXISTS `fornecedoreseventos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fornecedoreseventos` (
  `idfornecedor` int(11) NOT NULL,
  `idevento` int(11) NOT NULL,
  PRIMARY KEY (`idfornecedor`,`idevento`),
  KEY `fk_fornecedores_has_eventos_eventos1_idx` (`idevento`),
  KEY `fk_fornecedores_has_eventos_fornecedores1_idx` (`idfornecedor`),
  CONSTRAINT `fk_fornecedores_has_eventos_eventos1` FOREIGN KEY (`idevento`) REFERENCES `eventos` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_fornecedores_has_eventos_fornecedores1` FOREIGN KEY (`idfornecedor`) REFERENCES `fornecedores` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fornecedoreseventos`
--

LOCK TABLES `fornecedoreseventos` WRITE;
/*!40000 ALTER TABLE `fornecedoreseventos` DISABLE KEYS */;
/*!40000 ALTER TABLE `fornecedoreseventos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eventositens`
--

DROP TABLE IF EXISTS `eventositens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eventositens` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idevento` int(11) NOT NULL,
  `iditem` int(11) NOT NULL,
  `quantidade` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_index` (`idevento`,`iditem`),
  KEY `fk_eventos_has_itens_itens1_idx` (`iditem`),
  KEY `fk_eventos_has_itens_eventos1_idx` (`idevento`),
  CONSTRAINT `fk_eventos_has_itens_eventos1` FOREIGN KEY (`idevento`) REFERENCES `eventos` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_eventos_has_itens_itens1` FOREIGN KEY (`iditem`) REFERENCES `itens` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eventositens`
--

LOCK TABLES `eventositens` WRITE;
/*!40000 ALTER TABLE `eventositens` DISABLE KEYS */;
/*!40000 ALTER TABLE `eventositens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `formapagamento`
--

DROP TABLE IF EXISTS `formapagamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `formapagamento` (
  `idformapagamento` int(11) NOT NULL AUTO_INCREMENT,
  `forma` varchar(45) NOT NULL,
  `ativo` tinyint(4) NOT NULL,
  PRIMARY KEY (`idformapagamento`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `formapagamento`
--

LOCK TABLES `formapagamento` WRITE;
/*!40000 ALTER TABLE `formapagamento` DISABLE KEYS */;
INSERT INTO `formapagamento` VALUES (1,'debito',1),(2,'credito',1),(3,'boleto',1);
/*!40000 ALTER TABLE `formapagamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `itens`
--

DROP TABLE IF EXISTS `itens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `itens` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `descricao` longtext NOT NULL,
  `valorcusto` decimal(12,2) NOT NULL,
  `valorcomercial` decimal(12,2) NOT NULL,
  `ativo` tinyint(1) NOT NULL,
  `idtipoitem` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nomeitem_UNIQUE` (`nome`),
  KEY `fk_itens_tipoitens1_idx` (`idtipoitem`),
  CONSTRAINT `fk_itens_tipoitens1` FOREIGN KEY (`idtipoitem`) REFERENCES `tipoitens` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itens`
--

LOCK TABLES `itens` WRITE;
/*!40000 ALTER TABLE `itens` DISABLE KEYS */;
INSERT INTO `itens` VALUES (1,'teste','arua		',1.23,2.22,1,1),(2,'asd','asd',1.11,22.22,1,3),(3,'jjjj','kkkk',0.01,0.01,1,1);
/*!40000 ALTER TABLE `itens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clientes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nomecompleto` varchar(60) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `rg` varchar(45) NOT NULL,
  `cpfcnpj` varchar(14) NOT NULL,
  `nomeresponsavel` varchar(45) DEFAULT NULL,
  `tipo` varchar(45) NOT NULL,
  `convidadosextras` int(11) NOT NULL,
  `nomeguerramilitar` varchar(45) DEFAULT NULL,
  `endereco` varchar(200) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cpfcnpjcliente_UNIQUE` (`cpfcnpj`),
  UNIQUE KEY `rgcliente_UNIQUE` (`rg`),
  UNIQUE KEY `nomecliente_UNIQUE` (`nomecompleto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipoitens`
--

DROP TABLE IF EXISTS `tipoitens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipoitens` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `ativo` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nome_UNIQUE` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipoitens`
--

LOCK TABLES `tipoitens` WRITE;
/*!40000 ALTER TABLE `tipoitens` DISABLE KEYS */;
INSERT INTO `tipoitens` VALUES (1,'Chop',1),(2,'Cigarro',1),(3,'Saco',1),(4,'teste',1),(5,'ddd',1);
/*!40000 ALTER TABLE `tipoitens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enderecosfornecedores`
--

DROP TABLE IF EXISTS `enderecosfornecedores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `enderecosfornecedores` (
  `idendereco` int(11) NOT NULL,
  `idfornecedor` int(11) NOT NULL,
  UNIQUE KEY `idendereco_UNIQUE` (`idendereco`),
  KEY `fk_enderecosfornecedores_enderecos1_idx` (`idendereco`),
  KEY `fk_enderecosfornecedores_fornecedores1_idx` (`idfornecedor`),
  CONSTRAINT `fk_enderecosfornecedores_enderecos1` FOREIGN KEY (`idendereco`) REFERENCES `enderecos` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_enderecosfornecedores_fornecedores1` FOREIGN KEY (`idfornecedor`) REFERENCES `fornecedores` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enderecosfornecedores`
--

LOCK TABLES `enderecosfornecedores` WRITE;
/*!40000 ALTER TABLE `enderecosfornecedores` DISABLE KEYS */;
/*!40000 ALTER TABLE `enderecosfornecedores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usuario` varchar(20) NOT NULL,
  `senha` varchar(20) NOT NULL,
  `nomecompleto` varchar(60) NOT NULL,
  `cpf` varchar(11) NOT NULL,
  `perfil` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `usuario_UNIQUE` (`usuario`),
  UNIQUE KEY `cpf_UNIQUE` (`cpf`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'arua','arua','arua','1','admin'),(2,'teste','teste','teste','2','opera'),(3,'pericles','10239012930','qoiodiasd','10239012930','VENDEDOR');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fornecedores`
--

DROP TABLE IF EXISTS `fornecedores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fornecedores` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `valorservico` decimal(12,2) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `endereco` varchar(50) DEFAULT NULL,
  `cpfcnpj` varchar(14) DEFAULT NULL,
  `rg` varchar(20) DEFAULT NULL,
  `site` varchar(45) DEFAULT NULL,
  `idtiposervico` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_fornecedores_tiposervico1_idx` (`idtiposervico`),
  CONSTRAINT `fk_fornecedores_tiposervico1` FOREIGN KEY (`idtiposervico`) REFERENCES `tiposservicos` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fornecedores`
--

LOCK TABLES `fornecedores` WRITE;
/*!40000 ALTER TABLE `fornecedores` DISABLE KEYS */;
/*!40000 ALTER TABLE `fornecedores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lembretes`
--

DROP TABLE IF EXISTS `lembretes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lembretes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `texto` text NOT NULL,
  `datahora` datetime NOT NULL,
  `titulo` varchar(45) NOT NULL,
  `idremetente` int(11) NOT NULL,
  `iddestinatario` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_lembretes_usuarios1_idx` (`idremetente`),
  KEY `fk_lembretes_usuarios2_idx` (`iddestinatario`),
  CONSTRAINT `fk_lembretes_usuarios1` FOREIGN KEY (`idremetente`) REFERENCES `usuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_lembretes_usuarios2` FOREIGN KEY (`iddestinatario`) REFERENCES `usuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lembretes`
--

LOCK TABLES `lembretes` WRITE;
/*!40000 ALTER TABLE `lembretes` DISABLE KEYS */;
/*!40000 ALTER TABLE `lembretes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enderecos`
--

DROP TABLE IF EXISTS `enderecos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `enderecos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `endereco` varchar(50) NOT NULL,
  `bairro` varchar(40) NOT NULL,
  `cep` int(8) NOT NULL,
  `complemento` varchar(60) DEFAULT NULL,
  `cidade` varchar(50) DEFAULT NULL,
  `TYPE` varchar(31) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enderecos`
--

LOCK TABLES `enderecos` WRITE;
/*!40000 ALTER TABLE `enderecos` DISABLE KEYS */;
/*!40000 ALTER TABLE `enderecos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parcela`
--

DROP TABLE IF EXISTS `parcela`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parcela` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `iddebito` int(11) NOT NULL,
  `valor` decimal(12,2) DEFAULT NULL,
  `formapagamento` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `datapagamento` datetime DEFAULT NULL,
  `datavencimento` datetime DEFAULT NULL,
  `entrada` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_parcela_debito1_idx` (`iddebito`),
  CONSTRAINT `fk_parcela_debito1` FOREIGN KEY (`iddebito`) REFERENCES `debito` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parcela`
--

LOCK TABLES `parcela` WRITE;
/*!40000 ALTER TABLE `parcela` DISABLE KEYS */;
/*!40000 ALTER TABLE `parcela` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `telefonesclientes`
--

DROP TABLE IF EXISTS `telefonesclientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `telefonesclientes` (
  `idcliente` int(11) NOT NULL,
  `idtelefone` int(11) NOT NULL,
  UNIQUE KEY `idtelefone_UNIQUE` (`idtelefone`),
  KEY `fk_telefonescliente_clientes1_idx` (`idcliente`),
  KEY `fk_telefonescliente_telefones1_idx` (`idtelefone`),
  CONSTRAINT `fk_telefonescliente_clientes1` FOREIGN KEY (`idcliente`) REFERENCES `clientes` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_telefonescliente_telefones1` FOREIGN KEY (`idtelefone`) REFERENCES `telefones` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `telefonesclientes`
--

LOCK TABLES `telefonesclientes` WRITE;
/*!40000 ALTER TABLE `telefonesclientes` DISABLE KEYS */;
/*!40000 ALTER TABLE `telefonesclientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipopagamento`
--

DROP TABLE IF EXISTS `tipopagamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipopagamento` (
  `idtipopagamento` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(45) NOT NULL,
  `ativo` tinyint(4) NOT NULL,
  PRIMARY KEY (`idtipopagamento`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipopagamento`
--

LOCK TABLES `tipopagamento` WRITE;
/*!40000 ALTER TABLE `tipopagamento` DISABLE KEYS */;
INSERT INTO `tipopagamento` VALUES (1,'boleto',1),(2,'cartão',1);
/*!40000 ALTER TABLE `tipopagamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `telefonesfornecedores`
--

DROP TABLE IF EXISTS `telefonesfornecedores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `telefonesfornecedores` (
  `idfornecedor` int(11) NOT NULL,
  `idtelefone` int(11) NOT NULL,
  UNIQUE KEY `idtelefone_UNIQUE` (`idtelefone`),
  KEY `fk_telefonesfornecedor_fornecedores1_idx` (`idfornecedor`),
  KEY `fk_telefonesfornecedor_telefones1_idx` (`idtelefone`),
  CONSTRAINT `fk_telefonesfornecedor_fornecedores1` FOREIGN KEY (`idfornecedor`) REFERENCES `fornecedores` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_telefonesfornecedor_telefones1` FOREIGN KEY (`idtelefone`) REFERENCES `telefones` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `telefonesfornecedores`
--

LOCK TABLES `telefonesfornecedores` WRITE;
/*!40000 ALTER TABLE `telefonesfornecedores` DISABLE KEYS */;
/*!40000 ALTER TABLE `telefonesfornecedores` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-04-01 15:28:33
