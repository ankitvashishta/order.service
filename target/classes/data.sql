DROP TABLE IF EXISTS order_info;
 
CREATE TABLE order_info (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  customer VARCHAR(250) NOT NULL,
  order_date DATE(50) NOT NULL,
  shipping_address VARCHAR(250) NOT NULL,
  order_items VARCHAR(250) NOT NULL,
  total DOUBLE(250) DEFAULT NULL
);
 
