package com.company.task12and13and14;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Print {
    private static final Logger LOGGER = LogManager.getRootLogger();
    public static final String PRINT_CLIENTS = "SELECT * FROM client";
    public static final String PRINT_PRODUCTS = "SELECT * FROM product";

    public static void printAllClients(Connection connection) {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(PRINT_CLIENTS)) {
            while (resultSet.next()) {
                String client = "id=" + resultSet.getInt("id") +
                        "; name=" + resultSet.getString("name") +
                        "; address=" + resultSet.getString("address") +
                        "; phone=" + resultSet.getString("phone") +
                        "; email=" + resultSet.getString("email");
                LOGGER.info(client);
            }
        } catch (SQLException e) {
            LOGGER.error("Произошла ошибка при SQL запросе вызова содержимого таблицы Client");
        }
    }

    public void printAllProducts(Connection connection) {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(PRINT_PRODUCTS)) {
            productContents(resultSet);
        } catch (SQLException e) {
            LOGGER.error("Произошла ошибка при SQL запросе вызова содержимого таблицы Product");
        }
    }

    static void productContents(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            String product = "id=" + resultSet.getInt("id") +
                    "; name=" + resultSet.getString("name") +
                    "; category=" + resultSet.getString("category") +
                    "; price=" + resultSet.getInt("price") +
                    "; quantity=" + resultSet.getInt("quantity");
            LOGGER.info(product);
        }
    }
}