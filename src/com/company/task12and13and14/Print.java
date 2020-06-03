package com.company.task12and13and14;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Print {

    public static void printClientTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM client")) {
            while (resultSet.next()) {
                System.out.print("id=" + resultSet.getInt("id"));
                System.out.print("; name=" + resultSet.getString("name"));
                System.out.print("; address=" + resultSet.getString("address"));
                System.out.print("; phone=" + resultSet.getString("phone"));
                System.out.println("; email=" + resultSet.getString("email"));
            }
        } catch (SQLException e) {
            connection.rollback();
            System.out.println("Произошла ошибка при SQL запросе вызова содержимого таблицы Client");
        }
    }

    public static void printProductTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM product")) {
            productContents(resultSet);
        } catch (SQLException e) {
            connection.rollback();
            System.out.println("Произошла ошибка при SQL запросе вызова содержимого таблицы Product");
        }
    }

    static void productContents(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            System.out.print("id=" + resultSet.getInt("id"));
            System.out.print("; name=" + resultSet.getString("name"));
            System.out.print("; category=" + resultSet.getString("category"));
            System.out.print("; price=" + resultSet.getInt("price"));
            System.out.println("; quantity=" + resultSet.getInt("quantity"));
        }
    }
}