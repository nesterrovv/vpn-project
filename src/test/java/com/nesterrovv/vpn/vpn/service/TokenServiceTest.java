package com.nesterrovv.vpn.vpn.service;

import com.nesterrovv.vpn.authentication.service.UserService;
import com.nesterrovv.vpn.vpn.entity.Token;
import com.nesterrovv.vpn.vpn.repository.TokenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.annotation.Annotation;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TokenServiceTest {

    @Mock
    private TokenRepository tokenRepository;

    @InjectMocks
    private TokenService tokenService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        tokenService = new TokenService(tokenRepository);
    }

    @Test
    void testGenerateToken() {
        when(tokenRepository.save(any(Token.class))).thenReturn(new Token());
        String outlineApiUrl = "https://example.com/api";
        String postfix = "/generate";
        System.setProperty("OUTLINE_API_URL", outlineApiUrl);
        System.setProperty("POSTFIX", postfix);
        Token generatedToken = tokenService.generateToken();
        verify(tokenRepository).save(any(Token.class));
        assertNotNull(generatedToken);
    }

    @Test
    void testSave() {
        when(tokenRepository.save(any(Token.class))).thenReturn(new Token());
        Token token = new Token();
        Token savedToken = tokenService.save(token);
        verify(tokenRepository).save(any(Token.class));
        assertNotNull(savedToken);
    }

    @Test
    void testFindById() {
        when(tokenRepository.findById(1L)).thenReturn(Optional.of(new Token()));
        Optional<Token> foundToken = tokenService.findById(1L);
        verify(tokenRepository).findById(1L);
        assertTrue(foundToken.isPresent());
    }

    @Test
    void testDelete() {
        tokenService.delete(1L);
        verify(tokenRepository).deleteById(1L);
    }

}
