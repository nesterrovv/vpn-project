package com.nesterrovv.vpn.subscription.dto;

import com.nesterrovv.vpn.vpn.entity.Token;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class SubscriptionDtoTest {

    @Test
    void testDefaultConstructor() {
        SubscriptionDto subscriptionDto = new SubscriptionDto();
        assertNull(subscriptionDto.getExpirationDate());
        assertFalse(subscriptionDto.isActive());
        assertNull(subscriptionDto.getToken());
    }

    @Test
    void testParameterizedConstructor() {
        Date expirationDate = new Date();
        Token token = new Token();
        SubscriptionDto subscriptionDto = new SubscriptionDto(expirationDate, true, token);
        assertEquals(expirationDate, subscriptionDto.getExpirationDate());
        assertTrue(subscriptionDto.isActive());
        assertEquals(token, subscriptionDto.getToken());
    }

    @Test
    void testGettersAndSetters() {
        SubscriptionDto subscriptionDto = new SubscriptionDto();
        Date expirationDate = new Date();
        Token token = new Token();

        subscriptionDto.setExpirationDate(expirationDate);
        subscriptionDto.setActive(true);
        subscriptionDto.setToken(token);

        assertEquals(expirationDate, subscriptionDto.getExpirationDate());
        assertTrue(subscriptionDto.isActive());
        assertEquals(token, subscriptionDto.getToken());
    }

    @Test
    void testEqualsAndHashCode() {
        Date expirationDate1 = new Date();
        Date expirationDate2 = new Date();
        Token token1 = new Token();
        Token token2 = new Token();

        SubscriptionDto subscriptionDto1 = new SubscriptionDto(expirationDate1, true, token1);
        SubscriptionDto subscriptionDto2 = new SubscriptionDto(expirationDate1, true, token1);
        SubscriptionDto subscriptionDto3 = new SubscriptionDto(expirationDate2, true, token1);
        SubscriptionDto subscriptionDto4 = new SubscriptionDto(expirationDate1, false, token1);
        SubscriptionDto subscriptionDto5 = new SubscriptionDto(expirationDate1, true, token2);

        assertEquals(subscriptionDto1, subscriptionDto2);
        assertEquals(subscriptionDto1, subscriptionDto3);
        assertNotEquals(subscriptionDto1, subscriptionDto4);
        assertEquals(subscriptionDto1, subscriptionDto5);

        assertEquals(subscriptionDto1.hashCode(), subscriptionDto2.hashCode());
    }
}
