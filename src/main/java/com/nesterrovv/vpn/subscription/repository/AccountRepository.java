package com.nesterrovv.vpn.subscription.repository;

import com.nesterrovv.vpn.subscription.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}

