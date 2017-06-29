-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema sjp
-- -----------------------------------------------------
-- Spring Java Portal Database
DROP SCHEMA IF EXISTS `sjp` ;

-- -----------------------------------------------------
-- Schema sjp
--
-- Spring Java Portal Database
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sjp` DEFAULT CHARACTER SET utf8 ;
USE `sjp` ;

-- -----------------------------------------------------
-- Table `sjp`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sjp`.`users` ;

CREATE TABLE IF NOT EXISTS `sjp`.`users` (
  `username` VARCHAR(50) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `enabled` TINYINT(1) NOT NULL,
  PRIMARY KEY (`username`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sjp`.`authorities`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sjp`.`authorities` ;

CREATE TABLE IF NOT EXISTS `sjp`.`authorities` (
  `username` VARCHAR(50) NOT NULL,
  `authority` VARCHAR(50) NOT NULL,
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  INDEX `fk_authorities_users_idx` (`username` ASC),
  UNIQUE INDEX `ix_auth_username on authorities` (`username` ASC, `authority` ASC),
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_authorities_users`
    FOREIGN KEY (`username`)
    REFERENCES `sjp`.`users` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sjp`.`groups`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sjp`.`groups` ;

CREATE TABLE IF NOT EXISTS `sjp`.`groups` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `group_name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sjp`.`group_authorities`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sjp`.`group_authorities` ;

CREATE TABLE IF NOT EXISTS `sjp`.`group_authorities` (
  `group_id` BIGINT NOT NULL,
  `authority` VARCHAR(50) NOT NULL,
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  INDEX `fk_group_authorities_group_idx` (`group_id` ASC),
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_group_authorities_group`
    FOREIGN KEY (`group_id`)
    REFERENCES `sjp`.`groups` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sjp`.`group_members`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sjp`.`group_members` ;

CREATE TABLE IF NOT EXISTS `sjp`.`group_members` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL,
  `group_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_group_members_group_idx` (`group_id` ASC),
  CONSTRAINT `fk_group_members_group`
    FOREIGN KEY (`group_id`)
    REFERENCES `sjp`.`groups` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sjp`.`persistent_logins`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sjp`.`persistent_logins` ;

CREATE TABLE IF NOT EXISTS `sjp`.`persistent_logins` (
  `series` VARCHAR(64) NOT NULL,
  `username` VARCHAR(64) NOT NULL,
  `token` VARCHAR(64) NOT NULL,
  `last_used` TIMESTAMP NOT NULL,
  PRIMARY KEY (`series`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
