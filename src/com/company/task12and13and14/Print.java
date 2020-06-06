package com.company.task12and13and14;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Print {
    private static final Logger logger = LogManager.getRootLogger();

    public static void printAllClients(Connection connection) {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM client")) {
            while (resultSet.next()) {
                String client = "id=" + resultSet.getInt("id") +
                        "; name=" + resultSet.getString("name") +
                        "; address=" + resultSet.getString("address") +
                        "; phone=" + resultSet.getString("phone") +
                        "; email=" + resultSet.getString("email");
                logger.info(client);
            }
        } catch (SQLException e) {
            logger.error("Произошла ошибка при SQL запросе вызова содержимого таблицы Client");
        }
    }

    public static void printAllProducts(Connection connection) {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM product")) {
            productContents(resultSet);
        } catch (SQLException e) {
            logger.error("Произошла ошибка при SQL запросе вызова содержимого таблицы Product");
        }
    }

    static void productContents(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            String product = "id=" + resultSet.getInt("id") +
                    "; name=" + resultSet.getString("name") +
                    "; category=" + resultSet.getString("category") +
                    "; price=" + resultSet.getInt("price") +
                    "; quantity=" + resultSet.getInt("quantity");
            logger.info(product);
        }
    }
}