INSERT INTO CATEGORY (category_ID, category_Name)
VALUES
('1', 'Electronics'),
('2', 'Food'),
('3', 'Books');

INSERT INTO PRODUCT (product_ID, product_Name, category_ID, pricing, description)
VALUES
('P1', 'Laptop', '1', 1500, 'High-performance laptop with advanced features'),
('P2', 'Apples', '2', 1, 'Bundle of Apples'),
('P3', 'Organic Apples', '2', 2, 'Bundle of Organic Apples. Great fiber value!'),
('P4', 'Bananas', '2', 2, 'Bundle of Bananas'),
('P5', 'Oragnic Bananas', '2', 3, 'Organic Bananas bundle of high nutrients'),
('P6', 'Watermelon', '2', 5, 'Freshly Grown Watermelons!'),
('P7', 'Hunger Games', '3', 15, 'Bestselling Novel!!!');

INSERT INTO ROLES (id, name) VALUES ('590716c5-9eea-4283-a3d3-e83d2f508297', 'ADMIN');
INSERT INTO ROLES (id, name) VALUES ('590716c5-9eea-4653-a3d3-e83d2f508297', 'USER');