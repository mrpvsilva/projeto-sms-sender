-- MySQL Script generated by MySQL Workbench
-- 03/31/15 18:45:54
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema siga
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema siga
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `siga` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `siga` ;

-- -----------------------------------------------------
-- Table `siga`.`tipoitens`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siga`.`tipoitens` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `ativo` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nome_UNIQUE` (`nome` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `siga`.`itens`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siga`.`itens` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `descricao` LONGTEXT NOT NULL,
  `valorcusto` DECIMAL(12,2) NOT NULL,
  `valorcomercial` DECIMAL(12,2) NOT NULL,
  `ativo` TINYINT(1) NOT NULL,
  `idtipoitem` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nomeitem_UNIQUE` (`nome` ASC),
  INDEX `fk_itens_tipoitens1_idx` (`idtipoitem` ASC),
  CONSTRAINT `fk_itens_tipoitens1`
    FOREIGN KEY (`idtipoitem`)
    REFERENCES `siga`.`tipoitens` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `siga`.`telefones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siga`.`telefones` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `ddd` VARCHAR(2) NOT NULL,
  `numero` VARCHAR(10) NOT NULL,
  `operadora` VARCHAR(20) NOT NULL,
  `TYPE` VARCHAR(31) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `siga`.`eventos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siga`.`eventos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(40) NOT NULL,
  `tipo` VARCHAR(45) NOT NULL,
  `numeroconvidados` INT NOT NULL,
  `nome` VARCHAR(100) NOT NULL,
  `datacriacao` DATETIME NOT NULL,
  `dataevento` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nome_UNIQUE` (`nome` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `siga`.`eventositens`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siga`.`eventositens` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idevento` INT NOT NULL,
  `iditem` INT NOT NULL,
  `quantidade` INT NOT NULL,
  INDEX `fk_eventos_has_itens_itens1_idx` (`iditem` ASC),
  INDEX `fk_eventos_has_itens_eventos1_idx` (`idevento` ASC),
  PRIMARY KEY (`id`),
  UNIQUE INDEX `unique_index` (`idevento` ASC, `iditem` ASC),
  CONSTRAINT `fk_eventos_has_itens_eventos1`
    FOREIGN KEY (`idevento`)
    REFERENCES `siga`.`eventos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_eventos_has_itens_itens1`
    FOREIGN KEY (`iditem`)
    REFERENCES `siga`.`itens` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `siga`.`clientes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siga`.`clientes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nomecompleto` VARCHAR(60) NOT NULL,
  `email` VARCHAR(45) NULL,
  `rg` VARCHAR(45) NOT NULL,
  `cpfcnpj` VARCHAR(14) NOT NULL,
  `nomeresponsavel` VARCHAR(45) NULL,
  `tipo` VARCHAR(45) NOT NULL,
  `convidadosextras` INT NOT NULL,
  `nomeguerramilitar` VARCHAR(45) NULL,
  `endereco` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `cpfcnpjcliente_UNIQUE` (`cpfcnpj` ASC),
  UNIQUE INDEX `rgcliente_UNIQUE` (`rg` ASC),
  UNIQUE INDEX `nomecliente_UNIQUE` (`nomecompleto` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `siga`.`clienteseventos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siga`.`clienteseventos` (
  `idevento` INT NOT NULL,
  `idcliente` INT NOT NULL,
  PRIMARY KEY (`idevento`, `idcliente`),
  INDEX `fk_eventos_has_clientes_clientes1_idx` (`idcliente` ASC),
  INDEX `fk_eventos_has_clientes_eventos1_idx` (`idevento` ASC),
  CONSTRAINT `fk_eventos_has_clientes_eventos1`
    FOREIGN KEY (`idevento`)
    REFERENCES `siga`.`eventos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_eventos_has_clientes_clientes1`
    FOREIGN KEY (`idcliente`)
    REFERENCES `siga`.`clientes` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `siga`.`tiposservicos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siga`.`tiposservicos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `ativo` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nome_UNIQUE` (`nome` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `siga`.`fornecedores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siga`.`fornecedores` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  `valorservico` DECIMAL(12,2) NOT NULL,
  `email` VARCHAR(45) NULL,
  `endereco` VARCHAR(50) NULL,
  `cpfcnpj` VARCHAR(14) NULL,
  `rg` VARCHAR(20) NULL,
  `site` VARCHAR(45) NULL,
  `idtiposervico` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_fornecedores_tiposervico1_idx` (`idtiposervico` ASC),
  CONSTRAINT `fk_fornecedores_tiposervico1`
    FOREIGN KEY (`idtiposervico`)
    REFERENCES `siga`.`tiposservicos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `siga`.`fornecedoreseventos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siga`.`fornecedoreseventos` (
  `idfornecedor` INT NOT NULL,
  `idevento` INT NOT NULL,
  PRIMARY KEY (`idfornecedor`, `idevento`),
  INDEX `fk_fornecedores_has_eventos_eventos1_idx` (`idevento` ASC),
  INDEX `fk_fornecedores_has_eventos_fornecedores1_idx` (`idfornecedor` ASC),
  CONSTRAINT `fk_fornecedores_has_eventos_fornecedores1`
    FOREIGN KEY (`idfornecedor`)
    REFERENCES `siga`.`fornecedores` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_fornecedores_has_eventos_eventos1`
    FOREIGN KEY (`idevento`)
    REFERENCES `siga`.`eventos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `siga`.`clientesitensextras`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siga`.`clientesitensextras` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `quantidade` INT NOT NULL,
  `iditem` INT NOT NULL,
  `idevento` INT NOT NULL,
  `idcliente` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_itensextraseventos_itens1_idx` (`iditem` ASC),
  INDEX `fk_clientesitensextras_clienteseventos1_idx` (`idevento` ASC, `idcliente` ASC),
  UNIQUE INDEX `index_unique` (`iditem` ASC, `idevento` ASC, `idcliente` ASC),
  CONSTRAINT `fk_itensextraseventos_itens1`
    FOREIGN KEY (`iditem`)
    REFERENCES `siga`.`itens` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_clientesitensextras_clienteseventos1`
    FOREIGN KEY (`idevento` , `idcliente`)
    REFERENCES `siga`.`clienteseventos` (`idevento` , `idcliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `siga`.`usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siga`.`usuarios` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `usuario` VARCHAR(20) NOT NULL,
  `senha` VARCHAR(20) NOT NULL,
  `nomecompleto` VARCHAR(60) NOT NULL,
  `cpf` VARCHAR(11) NOT NULL,
  `perfil` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `usuario_UNIQUE` (`usuario` ASC),
  UNIQUE INDEX `cpf_UNIQUE` (`cpf` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `siga`.`lembretes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siga`.`lembretes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `texto` TEXT NOT NULL,
  `datahora` DATETIME NOT NULL,
  `titulo` VARCHAR(45) NOT NULL,
  `idremetente` INT NOT NULL,
  `iddestinatario` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_lembretes_usuarios1_idx` (`idremetente` ASC),
  INDEX `fk_lembretes_usuarios2_idx` (`iddestinatario` ASC),
  CONSTRAINT `fk_lembretes_usuarios1`
    FOREIGN KEY (`idremetente`)
    REFERENCES `siga`.`usuarios` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_lembretes_usuarios2`
    FOREIGN KEY (`iddestinatario`)
    REFERENCES `siga`.`usuarios` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `siga`.`telefonesfornecedores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siga`.`telefonesfornecedores` (
  `idfornecedor` INT NOT NULL,
  `idtelefone` INT NOT NULL,
  INDEX `fk_telefonesfornecedor_fornecedores1_idx` (`idfornecedor` ASC),
  INDEX `fk_telefonesfornecedor_telefones1_idx` (`idtelefone` ASC),
  UNIQUE INDEX `idtelefone_UNIQUE` (`idtelefone` ASC),
  CONSTRAINT `fk_telefonesfornecedor_fornecedores1`
    FOREIGN KEY (`idfornecedor`)
    REFERENCES `siga`.`fornecedores` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_telefonesfornecedor_telefones1`
    FOREIGN KEY (`idtelefone`)
    REFERENCES `siga`.`telefones` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `siga`.`telefonesclientes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siga`.`telefonesclientes` (
  `idcliente` INT NOT NULL,
  `idtelefone` INT NOT NULL,
  INDEX `fk_telefonescliente_clientes1_idx` (`idcliente` ASC),
  INDEX `fk_telefonescliente_telefones1_idx` (`idtelefone` ASC),
  UNIQUE INDEX `idtelefone_UNIQUE` (`idtelefone` ASC),
  CONSTRAINT `fk_telefonescliente_clientes1`
    FOREIGN KEY (`idcliente`)
    REFERENCES `siga`.`clientes` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_telefonescliente_telefones1`
    FOREIGN KEY (`idtelefone`)
    REFERENCES `siga`.`telefones` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `siga`.`debito`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siga`.`debito` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idevento` INT NOT NULL,
  `idcliente` INT NOT NULL,
  `valor` DECIMAL(12,2) NULL,
  `operacao` VARCHAR(10) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_debito_clienteseventos1_idx` (`idevento` ASC, `idcliente` ASC),
  CONSTRAINT `fk_debito_clienteseventos1`
    FOREIGN KEY (`idevento` , `idcliente`)
    REFERENCES `siga`.`clienteseventos` (`idevento` , `idcliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `siga`.`parcela`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siga`.`parcela` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `iddebito` INT NOT NULL,
  `valor` DECIMAL(12,2) NULL,
  `formapagamento` VARCHAR(45) NULL,
  `status` VARCHAR(45) NULL,
  `datapagamento` DATETIME NULL,
  `datavencimento` DATETIME NULL,
  `entrada` TINYINT(1) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_parcela_debito1_idx` (`iddebito` ASC),
  CONSTRAINT `fk_parcela_debito1`
    FOREIGN KEY (`iddebito`)
    REFERENCES `siga`.`debito` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `siga`.`enderecos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siga`.`enderecos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `endereco` VARCHAR(50) NOT NULL,
  `bairro` VARCHAR(40) NOT NULL,
  `cep` INT(8) NOT NULL,
  `complemento` VARCHAR(60) NULL,
  `cidade` VARCHAR(50) NULL,
  `TYPE` VARCHAR(31) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `siga`.`enderecosfornecedores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siga`.`enderecosfornecedores` (
  `idendereco` INT NOT NULL,
  `idfornecedor` INT NOT NULL,
  INDEX `fk_enderecosfornecedores_enderecos1_idx` (`idendereco` ASC),
  UNIQUE INDEX `idendereco_UNIQUE` (`idendereco` ASC),
  INDEX `fk_enderecosfornecedores_fornecedores1_idx` (`idfornecedor` ASC),
  CONSTRAINT `fk_enderecosfornecedores_enderecos1`
    FOREIGN KEY (`idendereco`)
    REFERENCES `siga`.`enderecos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_enderecosfornecedores_fornecedores1`
    FOREIGN KEY (`idfornecedor`)
    REFERENCES `siga`.`fornecedores` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
