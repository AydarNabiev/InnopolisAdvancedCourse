package com.company.task12and13and14;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ParametrisedQuery {

    public static void printProductParametrisedQuery(Connection connection, int priceLimit, int quantityLimit) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM product WHERE price > ? and quantity < ?")) {
            preparedStatement.setInt(1, priceLimit);
            preparedStatement.setInt(2, quantityLimit);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                Print.productContents(resultSet);
            }
        } catch (SQLException e) {
            connection.rollback();
            System.out.println("Произошла ошибка при параметризированном SQL запросе таблицы Product");
        }
    }
}