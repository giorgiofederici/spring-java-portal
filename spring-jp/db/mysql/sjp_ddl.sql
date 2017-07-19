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
-- Table `sjp`.`sjp_users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sjp`.`sjp_users` ;

CREATE TABLE IF NOT EXISTS `sjp`.`sjp_users` (
  `username` VARCHAR(50) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `enabled` TINYINT(1) NOT NULL,
  PRIMARY KEY (`username`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sjp`.`sjp_authorities`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sjp`.`sjp_authorities` ;

CREATE TABLE IF NOT EXISTS `sjp`.`sjp_authorities` (
  `username` VARCHAR(50) NOT NULL,
  `authority` VARCHAR(50) NOT NULL,
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  INDEX `fk_authorities_users_idx` (`username` ASC),
  UNIQUE INDEX `ix_auth_username on authorities` (`username` ASC, `authority` ASC),
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_authorities_users`
    FOREIGN KEY (`username`)
    REFERENCES `sjp`.`sjp_users` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sjp`.`sjp_groups`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sjp`.`sjp_groups` ;

CREATE TABLE IF NOT EXISTS `sjp`.`sjp_groups` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `group_name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sjp`.`sjp_group_authorities`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sjp`.`sjp_group_authorities` ;

CREATE TABLE IF NOT EXISTS `sjp`.`sjp_group_authorities` (
  `group_id` BIGINT NOT NULL,
  `authority` VARCHAR(50) NOT NULL,
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  INDEX `fk_group_authorities_group_idx` (`group_id` ASC),
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_group_authorities_group`
    FOREIGN KEY (`group_id`)
    REFERENCES `sjp`.`sjp_groups` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sjp`.`sjp_group_members`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sjp`.`sjp_group_members` ;

CREATE TABLE IF NOT EXISTS `sjp`.`sjp_group_members` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL,
  `group_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_group_members_group_idx` (`group_id` ASC),
  CONSTRAINT `fk_group_members_group`
    FOREIGN KEY (`group_id`)
    REFERENCES `sjp`.`sjp_groups` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sjp`.`sjp_persistent_logins`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sjp`.`sjp_persistent_logins` ;

CREATE TABLE IF NOT EXISTS `sjp`.`sjp_persistent_logins` (
  `series` VARCHAR(64) NOT NULL,
  `username` VARCHAR(64) NOT NULL,
  `token` VARCHAR(64) NOT NULL,
  `last_used` TIMESTAMP NOT NULL,
  PRIMARY KEY (`series`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sjp`.`sjp_user_connections`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sjp`.`sjp_user_connections` ;

CREATE TABLE IF NOT EXISTS `sjp`.`sjp_user_connections` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(255) NOT NULL,
  `provider_id` VARCHAR(255) NOT NULL,
  `provider_user_id` VARCHAR(255) NULL,
  `rank` INT NOT NULL,
  `display_name` VARCHAR(255) NULL,
  `profile_url` VARCHAR(512) NULL,
  `img_url` VARCHAR(512) NULL,
  `access_token` VARCHAR(512) NOT NULL,
  `secret` VARCHAR(512) NULL,
  `refresh_token` VARCHAR(512) NULL,
  `expire_time` BIGINT NULL,
  UNIQUE INDEX `user_connection_run` (`user_id` ASC, `provider_id` ASC, `provider_user_id` ASC),
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sjp`.`ocs_categories`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sjp`.`ocs_categories` ;

CREATE TABLE IF NOT EXISTS `sjp`.`ocs_categories` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sjp`.`ocs_products`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sjp`.`ocs_products` ;

CREATE TABLE IF NOT EXISTS `sjp`.`ocs_products` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `price` DOUBLE(10,2) NOT NULL,
  `stock` INT NOT NULL,
  `category` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_CATALOG_CATEGORY_ID_idx` (`category` ASC),
  CONSTRAINT `FK_CATALOG_CATEGORY_ID`
    FOREIGN KEY (`category`)
    REFERENCES `sjp`.`ocs_categories` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sjp`.`ocs_addresses`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sjp`.`ocs_addresses` ;

CREATE TABLE IF NOT EXISTS `sjp`.`ocs_addresses` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `door_number` VARCHAR(25) NOT NULL,
  `street_name` VARCHAR(25) NOT NULL,
  `area_name` VARCHAR(25) NULL,
  `state` VARCHAR(25) NOT NULL,
  `country` VARCHAR(25) NOT NULL,
  `zip` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sjp`.`ocs_customer_accounts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sjp`.`ocs_customer_accounts` ;

CREATE TABLE IF NOT EXISTS `sjp`.`ocs_customer_accounts` (
  `username` VARCHAR(50) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `mid_name` VARCHAR(45) NOT NULL,
  `birth_date` DATE NOT NULL,
  `address` VARCHAR(255) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `mobile` VARCHAR(45) NOT NULL,
  `start_date` DATE NOT NULL,
  `billing_address_id` INT NOT NULL,
  INDEX `FK_CUSTOMER_ACCOUNT_USER_idx` (`username` ASC),
  PRIMARY KEY (`username`),
  INDEX `FK_CUSTOMER_ACCOUNT_ADDRESS_idx` (`billing_address_id` ASC),
  CONSTRAINT `FK_CUSTOMER_ACCOUNT_USER`
    FOREIGN KEY (`username`)
    REFERENCES `sjp`.`sjp_users` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CUSTOMER_ACCOUNT_ADDRESS`
    FOREIGN KEY (`billing_address_id`)
    REFERENCES `sjp`.`ocs_addresses` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sjp`.`ocs_shipping_details`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sjp`.`ocs_shipping_details` ;

CREATE TABLE IF NOT EXISTS `sjp`.`ocs_shipping_details` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(25) NOT NULL,
  `shipping_date` DATE NOT NULL,
  `shipping_address_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_SHIPPING_DETAILS_ADDRESS_idx` (`shipping_address_id` ASC),
  CONSTRAINT `FK_SHIPPING_DETAILS_ADDRESS`
    FOREIGN KEY (`shipping_address_id`)
    REFERENCES `sjp`.`ocs_addresses` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sjp`.`ocs_billing_statements`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sjp`.`ocs_billing_statements` ;

CREATE TABLE IF NOT EXISTS `sjp`.`ocs_billing_statements` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `transaction_id` INT NOT NULL,
  `catalog_id` INT NOT NULL,
  `qty` INT NOT NULL,
  `total` DECIMAL(10,2) NOT NULL,
  `payment_type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sjp`.`ocs_cart`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sjp`.`ocs_cart` ;

CREATE TABLE IF NOT EXISTS `sjp`.`ocs_cart` (
  `id` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sjp`.`ocs_customers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sjp`.`ocs_customers` ;

CREATE TABLE IF NOT EXISTS `sjp`.`ocs_customers` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(25) NOT NULL,
  `phone_number` VARCHAR(25) NOT NULL,
  `billing_address_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_CUSTOMER_ADDRESS_ID_idx` (`billing_address_id` ASC),
  CONSTRAINT `FK_CUSTOMER_ADDRESS_ID`
    FOREIGN KEY (`billing_address_id`)
    REFERENCES `sjp`.`ocs_addresses` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sjp`.`ocs_orders`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sjp`.`ocs_orders` ;

CREATE TABLE IF NOT EXISTS `sjp`.`ocs_orders` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cart_id` VARCHAR(255) NOT NULL,
  `customer_id` INT NOT NULL,
  `shipping_detail_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_ORDERS_CART_ID_idx` (`cart_id` ASC),
  INDEX `FK_ORDERS_SHIPPING_DETAILS_ID_idx` (`shipping_detail_id` ASC),
  INDEX `FK_ORDERS_CUSTOMER_ID_idx` (`customer_id` ASC),
  CONSTRAINT `FK_ORDERS_CART_ID`
    FOREIGN KEY (`cart_id`)
    REFERENCES `sjp`.`ocs_cart` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_ORDERS_CUSTOMER_ID`
    FOREIGN KEY (`customer_id`)
    REFERENCES `sjp`.`ocs_customers` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_ORDERS_SHIPPING_DETAILS_ID`
    FOREIGN KEY (`shipping_detail_id`)
    REFERENCES `sjp`.`ocs_shipping_details` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sjp`.`sjp_user_profile_images`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sjp`.`sjp_user_profile_images` ;

CREATE TABLE IF NOT EXISTS `sjp`.`sjp_user_profile_images` (
  `username` VARCHAR(50) NOT NULL,
  `type` VARCHAR(100) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `content` LONGBLOB NOT NULL,
  INDEX `FK_USER_PROFILE_IMAGES_USER_idx` (`username` ASC),
  PRIMARY KEY (`username`),
  CONSTRAINT `FK_USER_PROFILE_IMAGES_USER`
    FOREIGN KEY (`username`)
    REFERENCES `sjp`.`sjp_users` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sjp`.`ocs_cart_item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sjp`.`ocs_cart_item` ;

CREATE TABLE IF NOT EXISTS `sjp`.`ocs_cart_item` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `product_id` INT NOT NULL,
  `cart_id` VARCHAR(255) NOT NULL,
  `quantity` INT NOT NULL,
  INDEX `CART_ITEM_PK` (`id` ASC, `cart_id` ASC),
  INDEX `FK_CART_ITEM_PRODUCT_ID_idx` (`product_id` ASC),
  INDEX `FK_CART_ITEM_CART_ID_idx` (`cart_id` ASC),
  CONSTRAINT `FK_CART_ITEM_PRODUCT_ID`
    FOREIGN KEY (`product_id`)
    REFERENCES `sjp`.`ocs_products` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CART_ITEM_CART_ID`
    FOREIGN KEY (`cart_id`)
    REFERENCES `sjp`.`ocs_cart` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Data for table `sjp`.`ocs_categories`
-- -----------------------------------------------------
START TRANSACTION;
USE `sjp`;
INSERT INTO `sjp`.`ocs_categories` (`id`, `name`) VALUES (1, 'Beauty');
INSERT INTO `sjp`.`ocs_categories` (`id`, `name`) VALUES (2, 'Beer, Wine & Spirits');
INSERT INTO `sjp`.`ocs_categories` (`id`, `name`) VALUES (3, 'Books');
INSERT INTO `sjp`.`ocs_categories` (`id`, `name`) VALUES (4, 'Camera & Accessories');
INSERT INTO `sjp`.`ocs_categories` (`id`, `name`) VALUES (5, 'Camping & Hiking');
INSERT INTO `sjp`.`ocs_categories` (`id`, `name`) VALUES (6, 'Car & Motorbike');
INSERT INTO `sjp`.`ocs_categories` (`id`, `name`) VALUES (7, 'Clothing');
INSERT INTO `sjp`.`ocs_categories` (`id`, `name`) VALUES (8, 'Electronics');
INSERT INTO `sjp`.`ocs_categories` (`id`, `name`) VALUES (9, 'Home & Kitchen');
INSERT INTO `sjp`.`ocs_categories` (`id`, `name`) VALUES (10, 'Musical Instruments');
INSERT INTO `sjp`.`ocs_categories` (`id`, `name`) VALUES (11, 'PC & Video Games');
INSERT INTO `sjp`.`ocs_categories` (`id`, `name`) VALUES (12, 'Software');
INSERT INTO `sjp`.`ocs_categories` (`id`, `name`) VALUES (13, 'Watches');

COMMIT;


-- -----------------------------------------------------
-- Data for table `sjp`.`ocs_products`
-- -----------------------------------------------------
START TRANSACTION;
USE `sjp`;
INSERT INTO `sjp`.`ocs_products` (`id`, `name`, `price`, `stock`, `category`) VALUES (1, 'Final Fantasy XV', 34.00, 100, 11);
INSERT INTO `sjp`.`ocs_products` (`id`, `name`, `price`, `stock`, `category`) VALUES (2, 'Fifa 17', 39.89, 100, 11);
INSERT INTO `sjp`.`ocs_products` (`id`, `name`, `price`, `stock`, `category`) VALUES (3, 'Horizon Zero Dawn', 69.00, 100, 11);
INSERT INTO `sjp`.`ocs_products` (`id`, `name`, `price`, `stock`, `category`) VALUES (4, 'For Honor', 29.99, 100, 11);

COMMIT;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
