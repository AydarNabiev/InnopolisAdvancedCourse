package com.company.task12and13and14;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

public class ManualCommitSavepoint {

    public static void transactionManualCommitAndSavepointShowcase(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            for (int i = 1; i <= 2; i++) {
                statement.executeUpdate(
                        "INSERT INTO client (name, address, phone, email)\n"
                                + "VALUES\n"
                                + "   ('Patrick" + i + "', " + i + ", '555','thisispatrick@gmail.com');"
                );
            }
            Savepoint savepoint = connection.setSavepoint();
            try {
                for (int i = 3; i <= 4; i++) {
                    statement.executeUpdate(
                            "INSERT INTO client (bad parameters that will cause exception)\n"
                                    + "VALUES\n"
                                    + "   ('badparams');"
                    );
                }
            } catch (SQLException e) {
                connection.rollback(savepoint);
                System.out.println("Поймали исключение, откатываемся до savepoint'а (два патрика всё равно должны появиться)");
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            System.out.println("Поймали SQLException, откатываемся до начала изменений");
        }
    }
}