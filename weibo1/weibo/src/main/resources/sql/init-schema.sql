CREATE SCHEMA `weibo` DEFAULT CHARACTER SET utf8;
CREATE TABLE `weibo`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `salt` VARCHAR(45) NOT NULL,
  `head_url` VARCHAR(255) NULL,
  `email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

ALTER TABLE `weibo`.`user`
  CHANGE COLUMN `salt` `salt` VARCHAR(45) NULL ,
  CHANGE COLUMN `email` `email` VARCHAR(45) NULL ;


CREATE TABLE `weibo`.`login_ticket` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(45) NOT NULL,
  `ticket` VARCHAR(15) NOT NULL,
  `expired` DATETIME NOT NULL,
  `status` INT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `ticket_UNIQUE` (`ticket` ASC));

