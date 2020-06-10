package com.company.task12and13and14;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.sql.Connection;
import java.sql.SQLException;

import static org.mockito.MockitoAnnotations.initMocks;

class MainDBTest {
    @Mock
    Connection connection;
    @Mock
    Reload reload;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void checkAllMethods() throws SQLException {

    }
}