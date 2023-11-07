package com.nesterrovv.vpn.vpn.controller;

import com.nesterrovv.vpn.vpn.dto.TokenDto;
import com.nesterrovv.vpn.vpn.entity.Token;
import com.nesterrovv.vpn.vpn.service.TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ConnectionControllerTest {

    private ConnectionController connectionController;

    @Mock
    private TokenService tokenService;

    @BeforeEach
    void setUp() {
        // Инициализация mock объектов и контроллера
        MockitoAnnotations.openMocks(this);
        connectionController = new ConnectionController(tokenService);
    }

    @Test
    void testFindTokenById() {
        // Arrange
        Long tokenId = 1L;
        Token expectedToken = new Token();
        Mockito.when(tokenService.findById(tokenId)).thenReturn(expectedToken);
        // Act
        Optional<Token> result = connectionController.findTokenById(tokenId);
        // Assert
        assertEquals(Optional.of(expectedToken), result);
    }

    @Test
    void testGenerateToken() {
        // Arrange
        Token generatedToken = new Token();
        Mockito.when(tokenService.generateToken()).thenReturn(generatedToken);
        // Act
        TokenDto result = connectionController.generateToken();
        // Assert
        assertNotNull(result);
        assertEquals(generatedToken.getToken(), result.getToken());
    }

    @Test
    void testDeleteToken() {
        // Arrange
        Long tokenId = 1L;
        // Act
        connectionController.deleteToken(tokenId);
        // Assert
        Mockito.verify(tokenService).delete(tokenId);
    }

}
