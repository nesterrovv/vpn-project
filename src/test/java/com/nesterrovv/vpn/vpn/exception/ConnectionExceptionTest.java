package com.nesterrovv.vpn.vpn.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConnectionExceptionTest {

    @Test
    public void testConnectionExceptionMessage() {
        // Arrange
        String errorMessage = "Test Connection Exception Message";
        // Act
        ConnectionException connectionException = new ConnectionException(errorMessage);
        // Assert
        assertEquals(errorMessage, connectionException.getMessage());
    }

}
