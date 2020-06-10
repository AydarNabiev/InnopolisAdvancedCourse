package com.company.task12and13and14;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.sql.*;

import static com.company.task12and13and14.Print.PRINT_CLIENTS;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class PrintTest {
    @Mock
    Connection connection;
    @Mock
    Statement statement;
    @Mock
    ResultSet resultSet;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void printAllClients() throws SQLException {
        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(PRINT_CLIENTS)).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("name")).thenReturn("clientName");
        when(resultSet.getString("address")).thenReturn("clientAddress");
        when(resultSet.getString("phone")).thenReturn("clientPhone");
        when(resultSet.getString("email")).thenReturn("clientEmail");

        Print.printAllClients(connection);

        verify(resultSet, atLeast(1)).next();
        verify(resultSet, atLeast(1)).getInt("id");
        verify(resultSet, atLeast(1)).getString("name");
        verify(resultSet, atLeast(1)).getString("address");
        verify(resultSet, atLeast(1)).getString("phone");
        verify(resultSet, atLeast(1)).getString("email");
    }

    @Test
    void productContents() throws SQLException {
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("name")).thenReturn("productName");
        when(resultSet.getString("category")).thenReturn("productCategory");
        when(resultSet.getInt("price")).thenReturn(20);
        when(resultSet.getInt("quantity")).thenReturn(5);

        Print.productContents(resultSet);

        verify(resultSet, atLeast(1)).next();
        verify(resultSet, atLeast(1)).getInt("id");
        verify(resultSet, atLeast(1)).getString("name");
        verify(resultSet, atLeast(1)).getString("category");
        verify(resultSet, atLeast(1)).getInt("price");
        verify(resultSet, atLeast(1)).getInt("quantity");
    }
}