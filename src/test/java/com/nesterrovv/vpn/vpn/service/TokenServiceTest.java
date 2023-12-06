package com.nesterrovv.vpn.vpn.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.nesterrovv.vpn.vpn.entity.Token;
import com.nesterrovv.vpn.vpn.repository.TokenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;

class TokenServiceTest {

    private TokenService tokenService;

    @Mock
    private TokenRepository tokenRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        tokenService = new TokenService(tokenRepository);
    }

    @Test
    void testGenerateToken() {
        when(tokenRepository.save(any(Token.class))).thenAnswer(invocation -> invocation.getArgument(0));
        Token generatedToken = tokenService.generateToken();
        assertNotNull(generatedToken);
        assertNotNull(generatedToken.getToken());
        verify(tokenRepository, times(1)).save(any(Token.class));
    }

    @Test
    void testSave() {
        Token tokenToSave = new Token();
        when(tokenRepository.save(tokenToSave)).thenReturn(tokenToSave);

        Token savedToken = tokenService.save(tokenToSave);

        assertNotNull(savedToken);
        verify(tokenRepository, times(1)).save(tokenToSave);
    }

    @Test
    void testFindById() {
        Long tokenId = 1L;
        when(tokenRepository.findById(tokenId)).thenReturn(Optional.of(new Token()));

        Optional<Token> foundToken = tokenService.findById(tokenId);

        assertTrue(foundToken.isPresent());
        verify(tokenRepository, times(1)).findById(tokenId);
    }

    @Test
    void testDelete() {
        Long tokenId = 1L;

        // Mocking repository deleteById method
        doNothing().when(tokenRepository).deleteById(tokenId);

        tokenService.delete(tokenId);

        verify(tokenRepository, times(1)).deleteById(tokenId);
    }

}
