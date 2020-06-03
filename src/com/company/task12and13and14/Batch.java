package com.company.task12and13and14;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Batch {

    public static void applyBatchToProduct(Connection connection, Integer[] batchArgs) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "update product set category='Hobby' where id = ?")) {
            for (Integer batchArg : batchArgs) {
                preparedStatement.setInt(1, batchArg);
                preparedStatement.addBatch();
            }
            System.out.println("Смотрим таблицу product перед командой executeBatch (preparedStatement заготовлен)");
            Print.printProductTable(connection);
            preparedStatement.executeBatch();
            System.out.println("Смотрим таблицу product после команды executeBatch()");
        } catch (SQLException e) {
            connection.rollback();
            System.out.println("Произошла ошибка при батч-запросе SQL");
        }
        Print.printProductTable(connection);
    }
}