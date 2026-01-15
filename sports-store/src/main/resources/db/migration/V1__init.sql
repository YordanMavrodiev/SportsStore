-- USERS
CREATE TABLE users (
  id BIGSERIAL PRIMARY KEY,
  email VARCHAR(255) NOT NULL UNIQUE,
  full_name VARCHAR(255) NOT NULL
);

-- CATEGORIES
CREATE TABLE categories (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL UNIQUE
);

-- PRODUCTS
CREATE TABLE products (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  brand VARCHAR(255) NOT NULL,
  price NUMERIC(19,2) NOT NULL
);

-- PRODUCT_CATEGORIES (M:N)
CREATE TABLE product_categories (
  product_id BIGINT NOT NULL,
  category_id BIGINT NOT NULL,
  CONSTRAINT pk_product_categories PRIMARY KEY (product_id, category_id),
  CONSTRAINT fk_pc_product FOREIGN KEY (product_id) REFERENCES products(id),
  CONSTRAINT fk_pc_category FOREIGN KEY (category_id) REFERENCES categories(id)
);

-- INVENTORIES
CREATE TABLE inventories (
  id BIGSERIAL PRIMARY KEY,
  product_id BIGINT NOT NULL,
  size VARCHAR(20) NOT NULL,
  available_qty INT NOT NULL,
  version BIGINT,
  CONSTRAINT fk_inventory_product FOREIGN KEY (product_id) REFERENCES products(id),
  CONSTRAINT uk_inventory_product_size UNIQUE (product_id, size)
);

-- INVENTORY_RESERVATIONS
CREATE TABLE inventory_reservations (
  id BIGSERIAL PRIMARY KEY,
  user_id BIGINT NOT NULL,
  product_id BIGINT NOT NULL,
  size VARCHAR(20) NOT NULL,
  qty INT NOT NULL,
  status VARCHAR(30) NOT NULL,
  expires_at TIMESTAMP NOT NULL,
  CONSTRAINT fk_res_user FOREIGN KEY (user_id) REFERENCES users(id),
  CONSTRAINT fk_res_product FOREIGN KEY (product_id) REFERENCES products(id)
);

-- ORDERS
CREATE TABLE orders (
  id BIGSERIAL PRIMARY KEY,
  user_id BIGINT NOT NULL,
  status VARCHAR(30) NOT NULL,
  created_at TIMESTAMP NOT NULL,
  total NUMERIC(19,2) NOT NULL,
  CONSTRAINT fk_orders_user FOREIGN KEY (user_id) REFERENCES users(id)
);

-- ORDER_ITEMS
CREATE TABLE order_items (
  id BIGSERIAL PRIMARY KEY,
  order_id BIGINT NOT NULL,
  product_id BIGINT NOT NULL,
  size VARCHAR(20) NOT NULL,
  quantity INT NOT NULL,
  price_at_purchase NUMERIC(19,2) NOT NULL,
  CONSTRAINT fk_oi_order FOREIGN KEY (order_id) REFERENCES orders(id),
  CONSTRAINT fk_oi_product FOREIGN KEY (product_id) REFERENCES products(id)
);
