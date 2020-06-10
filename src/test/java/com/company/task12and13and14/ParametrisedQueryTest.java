package com.company.task12and13and14;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static com.company.task12and13and14.ParametrisedQuery.SELECT_PRODUCT_CONDITION;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class ParametrisedQueryTest {
    @Mock
    Connection connection;
    @Mock
    PreparedStatement preparedStatement;
    @Mock
    ResultSet resultSet;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void printProductsParametrisedQuery() throws SQLException {
        int price = 20;
        int quantity = 30;
        when(connection.prepareStatement(SELECT_PRODUCT_CONDITION)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        doNothing().when(preparedStatement).setInt(1, price);
        doNothing().when(preparedStatement).setInt(2, quantity);

        ParametrisedQuery.printProductsParametrisedQuery(connection, price, quantity);

        verify(connection, times(1)).prepareStatement(SELECT_PRODUCT_CONDITION);
        verify(preparedStatement, times(1)).setInt(1, price);
        verify(preparedStatement, times(1)).setInt(2, quantity);
        verify(preparedStatement, times(1)).executeQuery();
    }
}