package com.nesterrovv.vpn.vpn.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TokenTest {

    private Token token1;
    private Token token2;

    @BeforeEach
    public void setUp() {
        token1 = new Token();
        token1.setId(1L);
        token1.setToken("token1");

        token2 = new Token();
        token2.setId(2L);
        token2.setToken("token2");
    }

    @Test
    public void testEquals() {
        // Arrange
        Token sameToken = new Token();
        sameToken.setId(1L);
        sameToken.setToken("token1");
        // Act
        boolean result1 = token1.equals(token2);
        boolean result2 = token1.equals(sameToken);
        // Assert
        assertFalse(result1);
        assertTrue(result2);
    }

    @Test
    public void testHashCode() {
        // Arrange
        Token sameToken = new Token();
        sameToken.setId(1L);
        sameToken.setToken("token1");
        // Act
        int hashCode1 = token1.hashCode();
        int hashCode2 = sameToken.hashCode();
        // Assert
        assertNotEquals(hashCode1, token2.hashCode());
        assertEquals(hashCode1, hashCode2);
    }

}
