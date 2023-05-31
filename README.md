## Introduction

This is a Java-based command-line interface (CLI) eCommerce application. The application was primarily built using Java and utilized a PostgreSQL database to store product and user information.
Within the app users can access features such as viewing products, adding to cart and viewing order history. Check out all the [features](#features).
## Table of Contents

- [Built with](#built-with)
- [Installation](#installation)
- [Dependencies](#dependencies)
- [Tech Stacks](#tech-stacks)
- [Features](#features)
## Built with

- **Java**: The main programming language used for building the application.
- **PostgreSQL**: Used as the database to store user, product, and order data.
- **Maven or Gradle**: Used for managing project dependencies.
- **JUnit**: A testing framework for Java applications, used to ensure our code works as expected.
- **Log4j**: A logging utility for debugging purposes.
- **JDBC (Java Database Connectivity)**: An API for connecting and executing queries on the database.
- **BCrypt**: A Java library for hashing and checking passwords for security.
- **JUnit, Mockito, and PowerMock**: Used for unit and integration testing.
- **Git and GitHub**: Used for version control.
### Installation {#installation}
- Pre-requisites
    - IDE such as Intellij, VCStudio,etc.
    - PostGresql
    - Docker
    - Getting started
- Clone this repository
    - $ git clone https://github.com/052223-java-angular/Justin-Jesse-p0.git
- Access from your repository
    - cd Justin-Jess-p0
      -Install the following [dependencies](#dependencies) using maven
- Create an application.properties file and insert the following
  - docker run --name some-postgres -e POSTGRES_PASSWORD=mysecretpassword -p 5432:5432 -d postgres
-Once your docker is connected to your database 
- Run the eCommerce class file to launch the application 
## Dependencies

- JUnit: 4.13.2
    - Group: junit
    - Artifact: junit
    - Scope: test

- Mockito Core: 4.5.1
    - Group: org.mockito
    - Artifact: mockito-core
    - Scope: test

- jBCrypt: 0.4
    - Group: org.mindrot
    - Artifact: jbcrypt

- Lombok: 1.18.26
    - Group: org.projectlombok
    - Artifact: lombok
    - Scope: provided

- Log4j Core: 2.20.0
    - Group: org.apache.logging.log4j
    - Artifact: log4j-core

- PostgreSQL Driver: 42.6.0
    - Group: org.postgresql
    - Artifact: postgresql

- JUnit Jupiter API: 5.9.2
    - Group: org.junit.jupiter
    - Artifact: junit-jupiter-api
    - Scope: test



## Tech Stacks

- **Java**: The main programming language used for building the application.
- **PostgreSQL**: Used as the database to store user, product, and order data.
- **Maven or Gradle**: Used for managing project dependencies.
- **JUnit**: A testing framework for Java applications, used to ensure our code works as expected.
- **Log4j**: A logging utility for debugging purposes.
- **JDBC (Java Database Connectivity)**: An API for connecting and executing queries on the database.
- **BCrypt**: A Java library for hashing and checking passwords for security.
- **JUnit, Mockito, and PowerMock**: Used for unit and integration testing.
- **Git and GitHub**: Used for version control.

## Features
Within this project you can do the following
- **Register**: Before accessing anything a new user will have create an account
    - This program has implements username and password validation such as:
        - Requiring a unique username
        - Usernames must be 8-20 characters
        - Passwords require a number
        - Password security by hashing using BCrypt
- **LogIn**: Once registering an account, you can log in to access all the features this application has to offer. Just remember to use the same Username and Password you registered!
- **Browsing Products**: Once logged in you can start off by browsing products available in the eCommerce store. When you see something you like add it to your cart!
    - You can view all products at once
        - Search by:
        - Product name
        - Categories
        - Price in a range of min - max
- **Product Reviews**: Not sure if you want to add a product? Check out the reviews!
    - Users can see all the reviews left on product from others
    - Ratings are left with every review
    - You can also leave a review and rating as well!

- **Cart**: Once you are satisfied with the items in you cart you have 3 options
    - Check out - You can go through our secure payment processing and complete your order
    - Change quantity - Adjust the amount of an item you need
    - Delete an item - You can remove any item from your cart

- **Order History**: Once you have checked out you can view your previously bought items from every order
