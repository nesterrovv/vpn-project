package com.nesterrovv.vpn.vpn.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TokenDtoTest {

    private TokenDto tokenDto1;
    private TokenDto tokenDto2;

    @BeforeEach
    public void setUp() {
        tokenDto1 = new TokenDto("token1");
        tokenDto2 = new TokenDto("token2");
    }

    @Test
    public void testEquals() {
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
    public void testHashCode() {
        // Arrange
        TokenDto sameTokenDto = new TokenDto("token1");
        // Act
        int hashCode1 = tokenDto1.hashCode();
        int hashCode2 = sameTokenDto.hashCode();
        // Assert
        assertNotEquals(hashCode1, tokenDto2.hashCode());
        assertEquals(hashCode1, hashCode2);
    }

}
