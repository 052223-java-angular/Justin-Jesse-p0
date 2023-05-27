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
DROP TABLE IF EXISTS USERS CASCADE;
DROP TABLE IF EXISTS CATEGORY CASCADE;
DROP TABLE IF EXISTS PRODUCT CASCADE;
DROP TABLE IF EXISTS CART CASCADE;
DROP TABLE IF EXISTS HISTORY CASCADE;
DROP TABLE IF EXISTS HISTORY_ITEMS CASCADE;
DROP TABLE IF EXISTS CART_ITEM CASCADE;
DROP TABLE IF EXISTS REVIEWSANDRATINGS CASCADE;
DROP TABLE IF EXISTS ROLES CASCADE;

CREATE TABLE ROLES(
role_ID VARCHAR(255) NOT NULL PRIMARY KEY,
rolename VARCHAR(255) NOT NULL
);

CREATE TABLE USERS(
user_ID VARCHAR(255) NOT NULL PRIMARY KEY,
username VARCHAR(255) NOT NULL,
password VARCHAR(255) NOT NULL,
role_ID VARCHAR(255) NOT NULL,
FOREIGN KEY (role_ID) REFERENCES ROLES(role_ID)
);



CREATE TABLE CATEGORY(
category_ID VARCHAR(255) NOT NULL PRIMARY KEY,
category_Name VARCHAR(255)
);

CREATE TABLE PRODUCT(
product_ID VARCHAR(255) NOT NULL PRIMARY KEY,
product_Name VARCHAR(255) NOT NULL,
category_ID VARCHAR(255) NOT NULL,
pricing int,
description VARCHAR(255),
FOREIGN KEY (category_ID) REFERENCES CATEGORY(category_ID)
);

CREATE TABLE CART(
cart_ID VARCHAR(255) NOT NULL PRIMARY KEY,
user_ID VARCHAR(255) NOT NULL,
amount_Spent VARCHAR (255) NOT NULL,
FOREIGN KEY (user_ID) REFERENCES USERS(user_ID)
);

CREATE TABLE CART_ITEM(
cart_Item_ID VARCHAR(255) NOT NULL PRIMARY KEY,
product_ID VARCHAR(255) NOT NULL,
cart_ID VARCHAR(255) NOT NULL,
quantity int,
price int,
FOREIGN KEY(product_ID) REFERENCES PRODUCT(product_ID),
FOREIGN KEY (cart_ID) REFERENCES CART(cart_ID)
);

CREATE TABLE HISTORY(
history_ID VARCHAR(255) NOT null PRIMARY KEY ,
user_ID VARCHAR(255) NOT NULL ,
total_Cost float,
FOREIGN KEY (user_ID) REFERENCES USERS(user_ID)
);

CREATE TABLE HISTORY_ITEMS(
    history_Items_ID VARCHAR(255) NOT NULL PRIMARY KEY,
    quantity int,
    price int,
    history_ID VARCHAR(255) NOT NULL,
    product_ID VARCHAR(255) NOT NULL,
    FOREIGN KEY (product_ID) REFERENCES PRODUCT(product_ID),
    FOREIGN KEY (history_ID) REFERENCES HISTORY(history_ID)
);

CREATE TABLE REVIEWSANDRATINGS(
review_ID VARCHAR(255) NOT NULL PRIMARY KEY,
user_ID VARCHAR(255) NOT NULL,
product_ID VARCHAR(255) NOT NULL,
rating int,
review VARCHAR,
FOREIGN KEY (user_ID) REFERENCES USERS(user_ID),
FOREIGN KEY (product_ID) REFERENCES PRODUCT(product_ID)
);

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
('C1', '3bcfb228-fa11-44c6-9152-4d6ed05a1b26', 1530),
('C2', '3bcd6c35-381a-4d03-b765-05e0acefbced', 6000);

insert into cart_item (cart_item_id, product_id, cart_id, quantity, price) values
('CI1', 'P1','C1', 1, 1500),
('CI2', 'P2','C1', 2, 30),
('CI3', 'P1','C2', 2, 3000);