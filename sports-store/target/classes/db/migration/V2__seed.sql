INSERT INTO users(email, full_name) VALUES
('alex@sports.com', 'Alex Runner'),
('maria@sports.com', 'Maria Fit');

INSERT INTO categories(name) VALUES
('Tops'),
('Bottoms');

INSERT INTO products(name, brand, price) VALUES
('DryFit T-Shirt', 'Nike', 59.99),
('Training Shorts', 'Adidas', 49.99);

INSERT INTO product_categories(product_id, category_id) VALUES
(1, 1),
(2, 2);

INSERT INTO inventories(product_id, size, available_qty, version) VALUES
(1, 'M', 1, 0),
(1, 'L', 5, 0),
(2, 'M', 10, 0);
