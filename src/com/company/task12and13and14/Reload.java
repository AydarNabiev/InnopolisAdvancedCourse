package com.company.task12and13and14;

import java.sql.*;

public class Reload {

    public static void reloadDatabase(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("-- Database: HomeWorkDatabase\n"
                    + "DROP TABLE IF EXISTS client, product, courier;"
                    + "\n"
                    + "CREATE TABLE client (\n"
                    + "    id bigserial primary key,\n"
                    + "    name varchar(100) NOT NULL,\n"
                    + "    address varchar(100) NOT NULL,\n"
                    + "    phone varchar(100) NOT NULL,\n"
                    + "    email varchar(100) NOT NULL);"
                    + "\n"
                    + "INSERT INTO client (name, address, phone, email)\n"
                    + "VALUES\n"
                    + "   ('Ivan', 'Ufa', '555-031', 'ivan@gmail.com'),\n"
                    + "   ('Sasha', 'Moscow', '555-045', 'sasha90@mail.ru'),\n"
                    + "   ('John', 'New-York', '555-073', 'heresjohny@gmail.com'),\n"
                    + "   ('Tanya', 'Kazan', '555-214', 'tankaz@yandex.ru'),\n"
                    + "   ('Neo', 'Nowhere', '555-555', 'theone@gmail.com');"
                    + "\n"
                    + "CREATE TABLE product (\n"
                    + "    id bigserial primary key,\n"
                    + "    name varchar(100) NOT NULL,\n"
                    + "    category varchar(100) NOT NULL,\n"
                    + "    price integer NOT NULL,\n"
                    + "    quantity integer NOT NULL);"
                    + "\n"
                    + "INSERT INTO product (name, category, price, quantity)\n"
                    + "VALUES\n"
                    + "   ('Manchester United T-shirt', 'Clothes', 500, 200),\n"
                    + "   ('iPhone', 'Electronics', 40000, 20),\n"
                    + "   ('Oral B toothbrush', 'Hygiene', 1000, 50),\n"
                    + "   ('Movie Poster collection', 'Hobby', 500, 50),\n"
                    + "   ('Philips TV Set', 'Electronics', 100, 50000);"
                    + "\n"
                    + "CREATE TABLE courier (\n"
                    + "    id bigserial primary key,\n"
                    + "    name varchar(100) NOT NULL,\n"
                    + "    phone varchar(100) NOT NULL,\n"
                    + "    rate float(2) NOT NULL);"
                    + "\n"
                    + "INSERT INTO courier (name, phone, rate)\n"
                    + "VALUES\n"
                    + "   ('Bridges', '555-7459', 0.1),\n"
                    + "   ('Delivery RF', '555-3145', 0.3),\n"
                    + "   ('Instant Delivery Ltd', '555-2451', 0.2);"
                    + "\n");
        }
    }
}