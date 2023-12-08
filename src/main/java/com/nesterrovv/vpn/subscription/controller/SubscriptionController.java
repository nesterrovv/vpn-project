package com.nesterrovv.vpn.subscription.controller;

import com.nesterrovv.vpn.subscription.entity.Subscription;
import com.nesterrovv.vpn.subscription.serivce.SubscriptionService;
import com.nesterrovv.vpn.vpn.entity.Token;
import com.nesterrovv.vpn.vpn.service.TokenService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vpn/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    private final TokenService tokenService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService, TokenService tokenService) {
        this.subscriptionService = subscriptionService;
        this.tokenService = tokenService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Subscription>> getAllSubscriptions() {
        List<Subscription> subscriptions = subscriptionService.getAllSubscriptions();
        return new ResponseEntity<>(subscriptions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subscription> getSubscriptionById(@PathVariable Long id) {
        Optional<Subscription> subscription = subscriptionService.getSubscriptionById(id);
        return subscription.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create")
    public ResponseEntity<Subscription> createSubscription(
        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date expirationDate) {
        Token token = tokenService.generateToken();
        if (token != null) {
            boolean isActive = expirationDate.after(new Date());
            Subscription newSubscription = subscriptionService.createSubscription(expirationDate, isActive, token);
            return new ResponseEntity<>(newSubscription, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subscription> updateSubscription(
        @PathVariable Long id,
        @RequestBody Map<String, Object> payload) {
        String expirationDateStr = (String) payload.get("expirationDate");
        if (expirationDateStr == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Date expirationDate;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            expirationDate = dateFormat.parse(expirationDateStr);
        } catch (ParseException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Subscription current = this.getSubscriptionById(id).getBody();
        Token oldToken = current.getToken();
        if (oldToken != null) {
            List<Subscription> subscriptionsWithThisToken =
                subscriptionService.getSubscriptionsByLinkedTokenId((long) oldToken.getId());
            if (!subscriptionsWithThisToken.isEmpty()) {
                subscriptionService.updateSubscription(id, expirationDate, false, null);
                tokenService.delete(oldToken.getId());
            }
        }
        Token newToken = tokenService.generateToken();
        if (newToken != null) {
            boolean isActive = expirationDate.after(new Date());
            Subscription updatedSubscription = subscriptionService
                .updateSubscription(id, expirationDate, isActive, newToken);
            return updatedSubscription != null
                ? new ResponseEntity<>(updatedSubscription, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubscription(@PathVariable Long id) {
        subscriptionService.deleteSubscription(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}/deactivate")
    public ResponseEntity<Subscription> deactivateSubscription(@PathVariable Long id) {
        Subscription deactivatedSubscription = subscriptionService.deactivateSubscription(id);
        return deactivatedSubscription != null
            ? new ResponseEntity<>(deactivatedSubscription, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("/{id}/extend")
    public ResponseEntity<Subscription> extendSubscription(@PathVariable Long id) {
        Subscription extendedSubscription = subscriptionService.extendSubscription(id);
        return extendedSubscription != null
            ? new ResponseEntity<>(extendedSubscription, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
