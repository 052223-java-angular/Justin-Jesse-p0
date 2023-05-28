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

INSERT INTO ROLES (role_id, rolename) VALUES ('590716c5-9eea-4283-a3d3-e83d2f508297', 'ADMIN');
INSERT INTO ROLES (role_id, rolename) VALUES ('590716c5-9eea-4653-a3d3-e83d2f508297', 'USER');


<-------DML TO TEST CART---->

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

INSERT INTO ROLES (role_ID, rolename) VALUES ('590716c5-9eea-4283-a3d3-e83d2f508297', 'ADMIN');

INSERT INTO ROLES (role_ID, rolename) VALUES ('590716c5-9eea-4653-a3d3-e83d2f508297', 'USER');

INSERT into users (user_id, username, password, role_id) values
('3bcfb228-fa11-44c6-9152-4d6ed05a1b26', 'revature', 'revature1','590716c5-9eea-4653-a3d3-e83d2f508297'),

('3bcd6c35-381a-4d03-b765-05e0acefbced', 'revature1', 'revature1','590716c5-9eea-4653-a3d3-e83d2f508297'),
('3bcd6c35-381a-4d03-b765-05e0acefbcud', 'revature3', 'revature1','590716c5-9eea-4653-a3d3-e83d2f508297');


insert into cart (cart_id, user_id, amount_spent) values
('C1', '3bcfb228-fa11-44c6-9152-4d6ed05a1b26', 1501),
('C2', '3bcd6c35-381a-4d03-b765-05e0acefbced', 6000);

insert into cart_item (cart_item_id, product_id, cart_id, quantity, price) values
('CI1', 'P1','C1', 1, 1500),
('CI2', 'P2','C1', 2, 2),
('CI3', 'P1','C2', 2, 3000);
