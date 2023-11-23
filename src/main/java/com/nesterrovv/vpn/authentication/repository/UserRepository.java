package com.nesterrovv.vpn.authentication.repository;

import com.nesterrovv.vpn.authentication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    User findByEmail(String email);

}
