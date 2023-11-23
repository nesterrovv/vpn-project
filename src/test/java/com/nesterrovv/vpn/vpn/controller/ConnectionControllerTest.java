package com.nesterrovv.vpn.vpn.controller;

import com.nesterrovv.vpn.vpn.entity.Token;
import com.nesterrovv.vpn.vpn.service.TokenService;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ConnectionControllerTest {

    @Mock
    private TokenService tokenService;

    @InjectMocks
    private ConnectionController connectionController;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(connectionController).build();
    }

    @Test
    void testFindTokenById() throws Exception {
        Long tokenId = 1L;
        Token token = new Token();
        when(tokenService.findById(tokenId)).thenReturn(Optional.of(token));

        mockMvc.perform(MockMvcRequestBuilders.get("/vpn/token/get/{id}", tokenId))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.token").value(token.getToken()));

        verify(tokenService, times(1)).findById(tokenId);
        verifyNoMoreInteractions(tokenService);
    }

    @Test
    void testFindTokenByIdWhenNotFound() throws Exception {
        Long tokenId = 1L;
        when(tokenService.findById(tokenId)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/vpn/token/get/{id}", tokenId))
            .andExpect(status().isNotFound());

        verify(tokenService, times(1)).findById(tokenId);
        verifyNoMoreInteractions(tokenService);
    }

    @Test
    void testGenerateToken() throws Exception {
        Token generatedToken = new Token();
        generatedToken.setToken("generated_token");
        when(tokenService.generateToken()).thenReturn(generatedToken);

        mockMvc.perform(MockMvcRequestBuilders.post("/vpn/token/generate"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.token").value(generatedToken.getToken()));

        verify(tokenService, times(1)).generateToken();
        verifyNoMoreInteractions(tokenService);
    }

    @Test
    void testDeleteToken() throws Exception {
        Long tokenId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/vpn/token/delete/{id}", tokenId))
            .andExpect(status().isOk());

        verify(tokenService, times(1)).delete(tokenId);
        verifyNoMoreInteractions(tokenService);
    }

}
