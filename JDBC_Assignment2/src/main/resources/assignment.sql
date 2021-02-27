CREATE SCHEMA `psims`;
USE `psims`;
-- ----------------------------
-- Table structure for password
-- ----------------------------
DROP TABLE IF EXISTS `password`;
CREATE TABLE `password` (
    `pwd` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of password
-- ----------------------------
BEGIN;
INSERT INTO `password` VALUES ('123');
COMMIT;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
                           `product_id` int NOT NULL AUTO_INCREMENT,
                           `product_no` varchar(20) DEFAULT NULL,
                           `name` varchar(100) DEFAULT NULL,
                           `catogery` int DEFAULT NULL,
                           `price` double DEFAULT NULL,
                           `pur_price` double DEFAULT NULL,
                           `stock_date` text,
                           `storage` int DEFAULT NULL,
                           `alarm_storage` int DEFAULT NULL,
                           PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
BEGIN;
INSERT INTO `product` VALUES (1, '10001', 'Tea', 1, 3.5, 2.3, '2021-02-17', 20, 5);
INSERT INTO `product` VALUES (2, '10002', 'Juice', 1, 3.5, 2.3, '2021-02-17', 70, 5);
INSERT INTO `product` VALUES (3, '20001', 'Chocolate', 2, 4, 3.2, '2021-02-17', 108, 5);
INSERT INTO `product` VALUES (4, '10003', 'Coffee', 1, 5.5, 3.8, '2021-02-17', 21, 10);
INSERT INTO `product` VALUES (5, '40001', 'Marlboro', 4, 10.5, 7.4, '2021-02-17', 239, 10);
INSERT INTO `product` VALUES (6, '30001', 'Red Wine', 3, 35, 24.8, '2021-02-17', 10, 5);
INSERT INTO `product` VALUES (7, '10004', 'Cola', 1, 3, 1.8, '2021-02-17', 38, 10);
INSERT INTO `product` VALUES (8, '30002', 'White wine', 3, 30, 20, '2021-02-18', 50, 5);
INSERT INTO `product` VALUES (9, '50001', 'Crisps', 5, 7, 5, '2021-02-18', 100, 5);
INSERT INTO `product` VALUES (10, '60001', 'Toilet paper', 6, 5, 4, '2021-02-18', 150, 5);
INSERT INTO `product` VALUES (11, '50002', 'Potato chips', 5, 2, 1.7, '2021-02-18', 200, 5);
INSERT INTO `product` VALUES (12, '60002', 'Mop', 6, 10, 9, '2021-02-18', 150, 5);
INSERT INTO `product` VALUES (13, '40002', 'Winston', 4, 20, 15, '2021-02-18', 40, 5);
COMMIT;

-- ----------------------------
-- Table structure for sell_history
-- ----------------------------
DROP TABLE IF EXISTS `sell_history`;
CREATE TABLE `sell_history` (
                                `sh_id` int NOT NULL AUTO_INCREMENT,
                                `product_id` int DEFAULT NULL,
                                `sell_date` text,
                                `quantity` int DEFAULT NULL,
                                PRIMARY KEY (`sh_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sell_history
-- ----------------------------
BEGIN;
INSERT INTO `sell_history` VALUES (1, 5, '2021-02-17 17:52', 3);
INSERT INTO `sell_history` VALUES (2, 4, '2021-02-17 14:18', 1);
INSERT INTO `sell_history` VALUES (3, 4, '2021-02-17 11:37', 2);
INSERT INTO `sell_history` VALUES (4, 6, '2021-02-17 11:38', 1);
INSERT INTO `sell_history` VALUES (5, 3, '2021-02-17 10:05', 3);
INSERT INTO `sell_history` VALUES (6, 3, '2021-02-17 10:41', 2);
INSERT INTO `sell_history` VALUES (7, 1, '2021-02-17 10:42', 1);
INSERT INTO `sell_history` VALUES (8, 1, '2021-02-17 10:45', 1);
INSERT INTO `sell_history` VALUES (9, 3, '2021-02-17 10:45', 2);
INSERT INTO `sell_history` VALUES (10, 2, '2021-02-17 10:46', 2);
INSERT INTO `sell_history` VALUES (11, 4, '2021-02-17 10:46', 1);
INSERT INTO `sell_history` VALUES (12, 1, '2021-02-17 11:40', 1);
INSERT INTO `sell_history` VALUES (13, 8, '2021-02-17 11:41', 2);
INSERT INTO `sell_history` VALUES (14, 4, '2021-02-17 15:39', 2);
INSERT INTO `sell_history` VALUES (15, 1, '2021-02-17 15:39', 1);
INSERT INTO `sell_history` VALUES (16, 3, '2021-02-17 15:57', 3);
INSERT INTO `sell_history` VALUES (17, 8, '2021-02-17 22:04', 2);
INSERT INTO `sell_history` VALUES (18, 8, '2021-02-17 22:05', 2);
INSERT INTO `sell_history` VALUES (19, 4, '2021-02-17 22:05', 2);
INSERT INTO `sell_history` VALUES (20, 1, '2021-02-18 21:34', 20);
COMMIT;

-- ----------------------------
-- Table structure for stock_history
-- ----------------------------
DROP TABLE IF EXISTS `stock_history`;
CREATE TABLE `stock_history` (
                                 `sh_id` int NOT NULL AUTO_INCREMENT,
                                 `product_id` int DEFAULT NULL,
                                 `stock_date` text,
                                 `quantity` int DEFAULT NULL,
                                 PRIMARY KEY (`sh_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stock_history
-- ----------------------------
BEGIN;
INSERT INTO `stock_history` VALUES (1, 3, '2021-02-17', 30);
INSERT INTO `stock_history` VALUES (2, 4, '2021-02-17', 25);
INSERT INTO `stock_history` VALUES (3, 3, '2021-02-17', 20);
INSERT INTO `stock_history` VALUES (4, 2, '2021-02-17', 50);
INSERT INTO `stock_history` VALUES (5, 5, '2021-02-17', 19);
INSERT INTO `stock_history` VALUES (6, 1, '2021-02-17', 10);
INSERT INTO `stock_history` VALUES (7, 6, '2021-02-17', 8);
COMMIT;

-- ----------------------------
-- Triggers structure for table sell_history
-- ----------------------------
DROP TRIGGER IF EXISTS `testtwo`;
delimiter ;;
CREATE TRIGGER `testtwo` AFTER INSERT ON `sell_history` FOR EACH ROW BEGIN
    UPDATE product SET storage = storage - NEW.quantity WHERE product_id = NEW.product_id;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table stock_history
-- ----------------------------
DROP TRIGGER IF EXISTS `testone`;
delimiter ;;
CREATE TRIGGER `testone` AFTER INSERT ON `stock_history` FOR EACH ROW BEGIN
    UPDATE product SET storage = storage + NEW.quantity WHERE product_id = NEW.product_id;
END
;;
delimiter ;