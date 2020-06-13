package com.company.task12and13and14;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Batch {
    private static final Logger logger = LogManager.getRootLogger();
    public static final String UPDATE_PRODUCT_CATEGORY = "update product set category='Hobby' where id = ?";
    private final Print print;

    public Batch(Print print) {
        this.print = print;
    }

    public void applyBatchToProducts(Connection connection, Integer[] batchArgs) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                UPDATE_PRODUCT_CATEGORY)) {
            for (Integer batchArg : batchArgs) {
                preparedStatement.setInt(1, batchArg);
                preparedStatement.addBatch();
            }
            logger.info("Смотрим таблицу product перед командой executeBatch (preparedStatement заготовлен)");
            print.printAllProducts(connection);
            preparedStatement.executeBatch();
            logger.info("Смотрим таблицу product после команды executeBatch()");
        } catch (SQLException e) {
            logger.error("Произошла ошибка при батч-запросе SQL");
        }
        print.printAllProducts(connection);
    }
}