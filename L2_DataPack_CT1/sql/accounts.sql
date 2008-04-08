-- ---------------------------
-- Table structure for accounts
-- ---------------------------
CREATE TABLE IF NOT EXISTS `accounts` (
  `login` VARCHAR(45) NOT NULL default '',
  `password` VARCHAR(45) ,
  `lastactive` DECIMAL(20),
  `accesslevel` INT NOT NULL default '0',
  `lastIP` VARCHAR(20),
  PRIMARY KEY (`login`)
) DEFAULT CHARSET=utf8;