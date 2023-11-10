package com.nesterrovv.vpn.subscription.repository;

import com.nesterrovv.vpn.subscription.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

}

