DROP TABLE IF EXISTS password;
CREATE TABLE password
(
    pwd varchar(20)
);
INSERT INTO password
VALUES ('abcd');

DROP TABLE IF EXISTS product;
CREATE TABLE product
(
    product_id    integer primary key,
    product_no    varchar(20),
    name          varchar(100),
    catogery      int,
    price         double,
    pur_price     double,
    stock_date    text,
    storage       int,
    alarm_storage integer
);
INSERT INTO product
VALUES (1, 3878012, '午后红茶', 1, 3.5, 2.3, '2016-05-21', 29, 5);
INSERT INTO product
VALUES (2, 8900412, '统一绿茶', 1, 3.5, 2.3, '2016-05-21', 70, 5);
INSERT INTO product
VALUES (3, 202113, '康师傅红烧牛肉面', 2, 4.0, 3.2, '2016-05-21', 108, 5);
INSERT INTO product
VALUES (4, 102129, '雀巢丝滑咖啡', 1, 5.5, 3.8, '2016-05-21', 23, 10);
INSERT INTO product
VALUES (5, 400213, '硬盒双喜', 4, 10.5, 7.4, '2016-05-21', 242, 10);
INSERT INTO product
VALUES (6, 302773, '长城干红', 3, 35.0, 24.8, '2016-05-07', 2, 5);
INSERT INTO product
VALUES (8, 102339, '可口可乐', 1, 3.0, 1.8, '2016-05-21', 42, 10);

DROP TABLE IF EXISTS sell_history;
CREATE TABLE sell_history
(
    sh_id      integer primary key,
    product_id int,
    sell_date  text,
    quantity   int
);
INSERT INTO sell_history
VALUES (1, 5, '2016-05-02 17:52', 3);
INSERT INTO sell_history
VALUES (2, 4, '2016-05-04 14:18', 1);
INSERT INTO sell_history
VALUES (3, 4, '2016-05-07 11:37', 2);
INSERT INTO sell_history
VALUES (4, 6, '2016-05-07 11:38', 1);
INSERT INTO sell_history
VALUES (5, 3, '2016-05-14 10:05', 3);
INSERT INTO sell_history
VALUES (6, 3, '2016-05-14 10:41', 2);
INSERT INTO sell_history
VALUES (7, 1, '2016-05-14 10:42', 1);
INSERT INTO sell_history
VALUES (8, 1, '2016-05-14 10:45', 1);
INSERT INTO sell_history
VALUES (9, 3, '2016-05-14 10:45', 2);
INSERT INTO sell_history
VALUES (10, 2, '2016-05-14 10:46', 2);
INSERT INTO sell_history
VALUES (11, 4, '2016-05-14 10:46', 1);
INSERT INTO sell_history
VALUES (12, 1, '2016-05-14 11:40', 1);
INSERT INTO sell_history
VALUES (13, 8, '2016-05-14 11:41', 2);
INSERT INTO sell_history
VALUES (14, 4, '2016-05-21 15:39', 2);
INSERT INTO sell_history
VALUES (15, 1, '2016-05-21 15:39', 1);
INSERT INTO sell_history
VALUES (16, 3, '2016-05-21 15:57', 3);

DROP TABLE IF EXISTS stock_history;
CREATE TABLE stock_history
(
    sh_id      integer primary key,
    product_id integer,
    stock_date text,
    quantity   integer
);
INSERT INTO stock_history
VALUES (1, 3, '2016-05-02', 30);
INSERT INTO stock_history
VALUES (2, 4, '2016-05-04', 25);
INSERT INTO stock_history
VALUES (3, 3, '2016-05-07', 20);
INSERT INTO stock_history
VALUES (4, 2, '2016-05-07', 50);

DROP TRIGGER IF EXISTS testone
CREATE TRIGGER testone
    AFTER INSERT stock_history FOR EACH ROW
BEGIN
UPDATE product SET storage = storage + NEW.quantity WHERE product_id = NEW.product_id;
END;

DROP TRIGGER IF EXISTS testtwo
CREATE TRIGGER testtwo
    AFTER INSERT sell_history FOR EACH ROW
BEGIN
UPDATE product SET storage = storage - NEW.quantity WHERE product_id = NEW.product_id;
END;
