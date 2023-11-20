package com.nesterrovv.vpn.vpn.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConnectionExceptionTest {

    @Test
    void testConnectionExceptionMessage() {
        // Arrange
        String errorMessage = "Test Connection Exception Message";
        // Act
        ConnectionException connectionException = new ConnectionException(errorMessage);
        // Assert
        assertEquals(errorMessage, connectionException.getMessage());
    }

}
