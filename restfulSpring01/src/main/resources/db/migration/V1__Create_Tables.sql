DROP TABLE IF EXISTS `payment_option_t`;
DROP TABLE IF EXISTS `user_t`;

CREATE TABLE `user_t` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `payment_option_t` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `max_value` double DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `user_id` bigint NOT NULL,
  `credit_limit` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2sa7k572ubw7vufvmo3loccqb` (`user_id`),
  CONSTRAINT `FK2sa7k572ubw7vufvmo3loccqb` FOREIGN KEY (`user_id`) REFERENCES `user_t` (`id`)
);

