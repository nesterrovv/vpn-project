package com.nesterrovv.vpn.subscription.serivce;

import com.nesterrovv.vpn.subscription.entity.Subscription;
import com.nesterrovv.vpn.subscription.repository.SubscriptionRepository;
import com.nesterrovv.vpn.vpn.entity.Token;
import com.nesterrovv.vpn.vpn.repository.TokenRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final TokenRepository tokenRepository;

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository, TokenRepository tokenRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.tokenRepository = tokenRepository;
    }

    public Subscription createSubscription(Date expirationDate, boolean isActive, Token token) {
        Subscription subscription = new Subscription(expirationDate, isActive, token);
        return save(subscription);
    }

    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    public Optional<Subscription> getSubscriptionById(Long id) {
        return subscriptionRepository.findById(id);
    }

    public Subscription updateSubscription(Long id, Date expirationDate, boolean isActive, Token token) {
        Optional<Subscription> optionalSubscription = getSubscriptionById(id);
        if (optionalSubscription.isPresent()) {
            Subscription subscription = optionalSubscription.get();
            subscription.setExpirationDate(expirationDate);
            subscription.setActive(isActive);
            subscription.setToken(token);
            return save(subscription);
        }
        return null;
    }

    public void deleteSubscription(Long id) {
        this.deactivateSubscription(id);
        subscriptionRepository.deleteById(id);
    }

    public Subscription deactivateSubscription(Long id) {
        Optional<Subscription> optionalSubscription = getSubscriptionById(id);
        if (optionalSubscription.isPresent()) {
            Subscription subscription = optionalSubscription.get();
            Token token = subscription.getToken();
            tokenRepository.delete(token);
            subscription.setActive(false);
            return save(subscription);
        }
        return null;
    }


    @SuppressWarnings("MagicNumber")
    public Subscription extendSubscription(Long id) {
        long plusMonth = 30 * 24 * 60 * 60 * 1000L;
        Optional<Subscription> optionalSubscription = getSubscriptionById(id);
        if (optionalSubscription.isPresent()) {
            Subscription subscription = optionalSubscription.get();
            Date currentExpirationDate = subscription.getExpirationDate();
            Date newExpirationDate = new Date(currentExpirationDate.getTime() + plusMonth);
            subscription.setExpirationDate(newExpirationDate);
            return save(subscription);
        }
        return null;
    }

    private Subscription save(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    public List<Subscription> getSubscriptionsByLinkedTokenId(Long tokenId) {
        return subscriptionRepository.findSubscriptionsByTokenId(tokenId);
    }

}
