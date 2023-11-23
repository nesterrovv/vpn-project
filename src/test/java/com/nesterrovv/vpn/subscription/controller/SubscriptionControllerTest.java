package com.nesterrovv.vpn.subscription.controller;

import com.nesterrovv.vpn.subscription.entity.Subscription;
import com.nesterrovv.vpn.subscription.serivce.SubscriptionService;
import com.nesterrovv.vpn.vpn.entity.Token;
import com.nesterrovv.vpn.vpn.service.TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class SubscriptionControllerTest {

    @Mock
    private SubscriptionService subscriptionService;

    @Mock
    private TokenService tokenService;

    @InjectMocks
    private SubscriptionController subscriptionController;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(subscriptionController).build();
    }

    @Test
    void testGetAllSubscriptions() {
        when(subscriptionService.getAllSubscriptions()).thenReturn(Collections.emptyList());
        ResponseEntity<List<Subscription>> responseEntity = subscriptionController.getAllSubscriptions();
        verify(subscriptionService).getAllSubscriptions();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    void testGetSubscriptionById() {
        when(subscriptionService.getSubscriptionById(1L)).thenReturn(Optional.of(new Subscription()));
        ResponseEntity<Subscription> responseEntity = subscriptionController.getSubscriptionById(1L);
        verify(subscriptionService).getSubscriptionById(1L);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    void testGetSubscriptionByIdNotFound() {
        when(subscriptionService.getSubscriptionById(1L)).thenReturn(Optional.empty());
        ResponseEntity<Subscription> responseEntity = subscriptionController.getSubscriptionById(1L);
        verify(subscriptionService).getSubscriptionById(1L);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

    @Test
    void testCreateSubscription() {
        when(tokenService.generateToken()).thenReturn(new Token());
        when(subscriptionService.createSubscription(any(Date.class), anyBoolean(), any(Token.class)))
            .thenReturn(new Subscription());
        ResponseEntity<Subscription> responseEntity = subscriptionController.createSubscription(new Date());
        verify(tokenService).generateToken();
        verify(subscriptionService).createSubscription(any(Date.class), anyBoolean(), any(Token.class));
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    void testDeleteSubscription() {
        ResponseEntity<Void> responseEntity = subscriptionController.deleteSubscription(1L);
        verify(subscriptionService).deleteSubscription(1L);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    void testDeactivateSubscription() {
        when(subscriptionService.deactivateSubscription(anyLong())).thenReturn(new Subscription());
        ResponseEntity<Subscription> responseEntity = subscriptionController.deactivateSubscription(1L);
        verify(subscriptionService).deactivateSubscription(1L);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    void testExtendSubscription() {
        when(subscriptionService.extendSubscription(anyLong())).thenReturn(new Subscription());
        ResponseEntity<Subscription> responseEntity = subscriptionController.extendSubscription(1L);
        verify(subscriptionService).extendSubscription(1L);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

}
