package com.company.task12and13and14;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static com.company.task12and13and14.Batch.UPDATE_PRODUCT_CATEGORY;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.MockitoAnnotations.initMocks;

class BatchTest {
    private final static Integer[] ARGS = new Integer[]{1, 2, 3, 4, 5};
    @Mock
    Connection connection;
    @Mock
    PreparedStatement preparedStatement;
    @Mock
    ResultSet resultSet;
    @Mock
    Print print;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void applyBatchToProducts() throws Exception {
        Batch batch = new Batch(print);
        when(connection.prepareStatement(UPDATE_PRODUCT_CATEGORY)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
        doNothing().when(print).printAllProducts(connection);

        batch.applyBatchToProducts(connection, ARGS);

        verify(connection, times(1)).prepareStatement(UPDATE_PRODUCT_CATEGORY);
        verify(preparedStatement, times(ARGS.length)).setInt(anyInt(), anyInt());
        verify(preparedStatement, times(ARGS.length)).addBatch();
        verify(preparedStatement, times(1)).executeBatch();
    }
}