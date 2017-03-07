DROP TABLE IF EXISTS `dream_shop_order_products`;
CREATE TABLE `dream_shop_order_products` (
  `order_id`   INT(11) NOT NULL,
  `product_id` INT(11) NOT NULL,
  PRIMARY KEY (`order_id`, `product_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
