package com.company.task12and13and14;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

public class ManualCommitSavepoint {
    private static final Logger logger = LogManager.getRootLogger();
    public static final String INSERT_INTO_CLIENT = "INSERT INTO client (name, address, phone, email)\n";

    public static void transactionManualCommitAndSavepointShowcase(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            for (int i = 1; i <= 2; i++) {
                statement.executeUpdate(
                        INSERT_INTO_CLIENT
                                + "VALUES\n"
                                + "   ('Patrick" + i + "', " + i + ", '555','thisispatrick@gmail.com');"
                );
            }
            Savepoint savepoint = connection.setSavepoint();
            try {throw new SQLException();} catch (SQLException e) {
                connection.rollback(savepoint);
                logger.warn("Поймали исключение, откатываемся до savepoint'а (два Патрика всё равно должны появиться)");
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            logger.error("Поймали SQLException, откатываемся до начала изменений");
        }
    }
}