package com.company.task12and13and14;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class MainDB {
    private static final Logger logger = LogManager.getLogger(MainDB.class);
    private final static Integer[] BATCHARGS = new Integer[]{1, 2, 3, 4, 5};

    public static void main(String[] args) throws SQLException {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/HomeWorkDatabase",
                "postgres",
                "1234")) {
            Reload.reloadLogDatabase(connection);
            Reload.reloadDatabase(connection);
            Print.printAllProducts(connection);
            logger.info("=========================================");
            logger.info("Параметризированный запрос к таблице товаров - цена выше " + 500 + " и кол-во ниже " + 50);
            ParametrisedQuery.printProductsParametrisedQuery(connection, 500, 50);
            logger.info("=========================================");
            logger.info("Применение батчинга запросов (изменить категории всех товаров на Hobby)");
            Batch.applyBatchToProducts(connection, BATCHARGS);
            logger.info("=========================================");
            logger.info("Используем ручное управление транзакциями в таблице client, распечатаем изначальное состояние");
            Print.printAllClients(connection);
            logger.info("Теперь применим ручное управление транзакциями с применением savepoint'а");
            ManualCommitSavepoint.transactionManualCommitAndSavepointShowcase(connection);
            Print.printAllClients(connection);
        }
    }
}