package com.company.task12and13and14;

import java.sql.*;

public class Main {
    private final static Integer[] BATCHARGS = new Integer[]{1, 2, 3, 4, 5};

    public static void main(String[] args) throws SQLException {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/HomeWorkDatabase",
                "postgres",
                "1234")) {
            Reload.reloadDatabase(connection);
            Print.printProductTable(connection);
            System.out.println("=========================================");
            System.out.println("Параметризированный запрос к таблице товаров - цена выше " + 500 + " и кол-во ниже " + 50);
            ParametrisedQuery.printProductParametrisedQuery(connection, 500, 50);
            System.out.println("=========================================");
            System.out.println("Применение батчинга запросов (изменить категории всех товаров на Hobby)");
            Batch.applyBatchToProduct(connection, BATCHARGS);
            System.out.println("=========================================");
            System.out.println("Используем ручное управление транзакциями в таблице client, распечатаем изначальное состояние");
            Print.printClientTable(connection);
            System.out.println("Теперь применим ручное управление транзакциями с применением savepoint'а");
            ManualCommitSavepoint.transactionManualCommitAndSavepointShowcase(connection);
            Print.printClientTable(connection);
        }
    }
}