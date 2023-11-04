package com.nesterrovv.vpn.authentication.controller;

import com.nesterrovv.vpn.authentication.dto.AuthenticationResponse;
import com.nesterrovv.vpn.authentication.dto.LoginDto;
import com.nesterrovv.vpn.authentication.dto.RegisterDto;
import com.nesterrovv.vpn.authentication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    @PostMapping("/register")
    public AuthenticationResponse register(@RequestBody RegisterDto request) {
        return userService.register(request);
    }

    @PostMapping("/login")
    public AuthenticationResponse register(@RequestBody LoginDto request) {
        return userService.login(request);
    }

}
