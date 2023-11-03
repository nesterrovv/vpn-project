package com.nesterrovv.vpn.authentication.repository;

import com.nesterrovv.vpn.authentication.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

}
