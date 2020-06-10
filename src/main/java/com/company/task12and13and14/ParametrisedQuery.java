package com.company.task12and13and14;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ParametrisedQuery {
    private static final Logger logger = LogManager.getRootLogger();
    public static final String SELECT_PRODUCT_CONDITION = "SELECT * FROM product WHERE price > ? and quantity < ?";

    public static void printProductsParametrisedQuery(Connection connection, int priceLimit, int quantityLimit) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                SELECT_PRODUCT_CONDITION)) {
            preparedStatement.setInt(1, priceLimit);
            preparedStatement.setInt(2, quantityLimit);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                Print.productContents(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Произошла ошибка при параметризированном SQL запросе таблицы Product");
        }
    }
}