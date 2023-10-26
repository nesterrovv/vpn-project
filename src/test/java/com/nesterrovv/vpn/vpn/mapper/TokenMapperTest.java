package com.nesterrovv.vpn.vpn.mapper;

import com.nesterrovv.vpn.vpn.dto.TokenDto;
import com.nesterrovv.vpn.vpn.entity.Token;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TokenMapperTest {

    @Test
    public void testEntityToDto() {
        // Arrange
        Token token = new Token();
        token.setToken("testToken");
        TokenMapper tokenMapper = new TokenMapper();
        // Act
        TokenDto tokenDto = tokenMapper.entityToDto(token);
        // Assert
        assertEquals(token.getToken(), tokenDto.getToken());
    }

    @Test
    public void testDtoToEntity() {
        // Arrange
        TokenDto tokenDto = new TokenDto("testToken");
        TokenMapper tokenMapper = new TokenMapper();
        // Act
        Token token = tokenMapper.dtoToEntity(tokenDto);
        // Assert
        assertEquals(tokenDto.getToken(), token.getToken());
    }

}

