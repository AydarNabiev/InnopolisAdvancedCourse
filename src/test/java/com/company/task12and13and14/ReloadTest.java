package com.company.task12and13and14;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static com.company.task12and13and14.Reload.RELOAD_DATABASE;
import static com.company.task12and13and14.Reload.RELOAD_LOG_DATABASE;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class ReloadTest {
    @Mock
    Connection connection;
    @Mock
    Statement statement;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void reloadDatabase() throws SQLException {
        when(connection.createStatement()).thenReturn(statement);
        when(statement.execute(RELOAD_DATABASE)).thenReturn(true);

        Reload.reloadDatabase(connection);

        verify(statement, times(1)).execute(RELOAD_DATABASE);
    }

    @Test
    void reloadLogDatabase() throws SQLException {
        when(connection.createStatement()).thenReturn(statement);
        when(statement.execute(RELOAD_LOG_DATABASE)).thenReturn(true);

        Reload.reloadLogDatabase(connection);

        verify(statement, times(1)).execute(RELOAD_LOG_DATABASE);
    }
}