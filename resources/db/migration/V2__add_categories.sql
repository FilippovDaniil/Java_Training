CREATE TABLE categories
(id BIGINT AUTO_INCREMENT PRIMARY KEY,
 name VARCHAR(255) NOT NULL UNIQUE);

ALTER TABLE products ADD COLUMN category_id BIGINT;

ALTER TABLE products ADD CONSTRAINT fk_products_category
FOREIGN KEY (category_id) REFERENCES categories(id);