CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `province` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `zone` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `postCode` varchar(45) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `designer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `level` varchar(45) DEFAULT NULL,
  `priceByDay` float DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `designer_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) DEFAULT NULL,
  `designer_id` int(11) DEFAULT NULL,
  `expected_amount` float DEFAULT NULL,
  `actual_paid_amount` float DEFAULT NULL,
  `estimated_days` int(11) DEFAULT NULL,
  `area` float DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `abort_cause` varchar(200) DEFAULT NULL,
  `feedback_star` int(11) DEFAULT NULL,
  `feedback_description` varchar(200) DEFAULT NULL,
  `created_time` datetime DEFAULT now(),
  `updated_time` datetime DEFAULT now(),
  PRIMARY KEY (`id`),
  CONSTRAINT `customerId_fk` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `designerId_fk` FOREIGN KEY (`designer_id`) REFERENCES `designer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

INSERT INTO `customer` VALUES (1,'lorry','15809233559','陕西省','西安市','高新区','科技六路','710075',1);
INSERT INTO `designer` VALUES (1,'jessica','13384968373',30,'1',100,1);
INSERT INTO `designer_order` VALUES (1,1,1,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,'2018-10-03 07:21:58','2018-10-03 07:21:58');
-- INSERT INTO `designer_order` VALUES (2,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2018-10-03 07:21:58','2018-10-03 07:21:58');