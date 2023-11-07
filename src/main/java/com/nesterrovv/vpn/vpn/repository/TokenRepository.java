package com.nesterrovv.vpn.vpn.repository;

import com.nesterrovv.vpn.vpn.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {

}
