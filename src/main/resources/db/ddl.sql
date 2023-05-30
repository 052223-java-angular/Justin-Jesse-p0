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
amount_Spent int NOT NULL,
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

