INSERT IGNORE INTO dream_shop_products (`name`, `sku`, `price`, `count`)
VALUES
  ('Product-012-13 Simple', 'sku-012-13', 0.66, 3),
  ('Product-022-26 Modern', 'sku-022-26', 34.5, 1),
  ('Product-003-37 Black', 'sku-003-37', 40.0, 12),
  ('Product-113-11 Long', 'sku-113-11', 17.0, 5),
  ('Product-000-87 Bevies', 'sku-000-87', 7.0, 5);


INSERT IGNORE INTO dream_shop_addresses (`zip`, `street`, `house`, `flat`)
VALUES
  ('90039', 'Rowena Ave Left', '9-A', '1/2'),
  ('90023', 'Rowena Ave Right', '14', '-1'),
  ('90014', 'Rowena Ave front', '9', '12'),
  ('90009', 'Rowena Ave back', '9/3', '5');

INSERT IGNORE INTO dream_shop_customers (`name`, `email`, `credit`)
VALUES
  ('John', 'test-john@email.com', 50.0),
  ('Smith', 'smith@ohn.com', 40.0),
  ('Emma', 'emma@emma.com', 150.0),
  ('Elvis', 'elvis@email.org', 250.0);