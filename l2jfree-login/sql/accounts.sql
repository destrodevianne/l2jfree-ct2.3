CREATE TABLE IF NOT EXISTS `accounts` (
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `lastactive` BIGINT UNSIGNED,
  `accessLevel` SMALLINT(4) NOT NULL DEFAULT 0,
  `lastServerId` TINYINT(2) UNSIGNED NOT NULL DEFAULT 0,
  `birthYear` SMALLINT(4) UNSIGNED NOT NULL DEFAULT 1900,
  `birthMonth` TINYINT(2) UNSIGNED NOT NULL DEFAULT 1,
  `birthDay` TINYINT(2) UNSIGNED NOT NULL DEFAULT 1,
  `lastIP` VARCHAR(20),
  PRIMARY KEY (`login`)
) DEFAULT CHARSET=utf8;
