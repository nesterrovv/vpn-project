package com.nesterrovv.vpn.authentication.controller;

import com.nesterrovv.vpn.authentication.entity.User;
import com.nesterrovv.vpn.authentication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Optional;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{username}")
    public Optional<User> findUserByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }

}
