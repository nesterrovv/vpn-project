package com.nesterrovv.vpn.vpn.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TokenDtoTest {

    private TokenDto tokenDto1;
    private TokenDto tokenDto2;

    @BeforeEach
    void setUp() {
        tokenDto1 = new TokenDto("token1");
        tokenDto2 = new TokenDto("token2");
    }

    @Test
    void testEquals() {
        // Arrange
        TokenDto sameTokenDto = new TokenDto("token1");
        // Act
        boolean result1 = tokenDto1.equals(tokenDto2);
        boolean result2 = tokenDto1.equals(sameTokenDto);
        // Assert
        assertFalse(result1);
        assertTrue(result2);
    }

    @Test
    void testHashCode() {
        // Arrange
        TokenDto sameTokenDto = new TokenDto("token1");
        TokenDto sameObjectTokenDto = tokenDto1;
        TokenDto nullToken = null;
        // Act
        int hashCode1 = tokenDto1.hashCode();
        int hashCode2 = sameTokenDto.hashCode();
        // Assert
        assertNotEquals(hashCode1, tokenDto2.hashCode());
        assertEquals(hashCode1, hashCode2);
        assertEquals(sameObjectTokenDto, tokenDto1);
        assertNotEquals(nullToken, tokenDto1);
    }

    @Test
    void testGetToken() {
        assertEquals("token1", tokenDto1.getToken());
    }

    @Test
    void testSetToken() {
        // Arrange
        TokenDto tokenDto = new TokenDto();
        String expectedToken = "test_token";
        // Act
        tokenDto.setToken(expectedToken);
        // Assert
        assertEquals(expectedToken, tokenDto.getToken());
    }

}
