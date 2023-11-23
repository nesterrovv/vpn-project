package com.nesterrovv.vpn.vpn.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TokenTest {

    @Test
    void testGetId() {
        // Arrange
        Token token = new Token();
        Long expectedId = 1L;

        // Act
        token.setId(expectedId);

        // Assert
        assertEquals(expectedId, token.getId());
    }

    @Test
    void testSetId() {
        // Arrange
        Token token = new Token();
        Long expectedId = 1L;

        // Act
        token.setId(expectedId);

        // Assert
        assertEquals(expectedId, token.getId());
    }

    @Test
    void testGetToken() {
        // Arrange
        Token token = new Token();
        String expectedToken = "test_token";

        // Act
        token.setToken(expectedToken);

        // Assert
        assertEquals(expectedToken, token.getToken());
    }

    @Test
    void testSetToken() {
        // Arrange
        Token token = new Token();
        String expectedToken = "test_token";

        // Act
        token.setToken(expectedToken);

        // Assert
        assertEquals(expectedToken, token.getToken());
    }

    @Test
    void testEquals() {
        // Arrange
        Token token1 = new Token();
        token1.setId(1L);
        token1.setToken("test_token");

        Token token2 = new Token();
        token2.setId(1L);
        token2.setToken("test_token");

        Token token3 = new Token();
        token3.setId(2L);
        token3.setToken("different_token");

        // Act & Assert
        assertTrue(token1.equals(token2));
        assertFalse(token1.equals(token3));
    }

    @Test
    void testHashCode() {
        // Arrange
        Token token1 = new Token();
        token1.setId(1L);
        token1.setToken("test_token");

        Token token2 = new Token();
        token2.setId(1L);
        token2.setToken("test_token");

        Token token3 = new Token();
        token3.setId(2L);
        token3.setToken("different_token");

        // Act & Assert
        assertEquals(token1.hashCode(), token2.hashCode());
        assertNotEquals(token1.hashCode(), token3.hashCode());
    }

}
