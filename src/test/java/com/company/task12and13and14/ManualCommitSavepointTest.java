package com.company.task12and13and14;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.sql.*;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class ManualCommitSavepointTest {
    @Mock
    Connection connection;
    @Mock
    Statement statement;
    @Mock
    Savepoint savepoint;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void transactionManualCommitAndSavepointShowcase() throws SQLException {
        when(connection.createStatement()).thenReturn(statement);
        when(connection.setSavepoint()).thenReturn(savepoint);

        ManualCommitSavepoint.transactionManualCommitAndSavepointShowcase(connection);

        verify(statement, times(2)).executeUpdate(anyString());
        verify(connection, times(1)).setAutoCommit(false);
        verify(connection, times(1)).commit();
        verify(connection, times(1)).setSavepoint();
        verify(connection, times(1)).rollback(savepoint);
    }
}