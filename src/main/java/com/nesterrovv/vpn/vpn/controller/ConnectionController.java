package com.nesterrovv.vpn.vpn.controller;

import com.nesterrovv.vpn.vpn.dto.TokenDto;
import com.nesterrovv.vpn.vpn.entity.Token;
import com.nesterrovv.vpn.vpn.mapper.TokenMapper;
import com.nesterrovv.vpn.vpn.service.TokenService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vpn/token")
@RequiredArgsConstructor
public class ConnectionController {

    private final TokenService tokenService;

    @GetMapping("/get/{id}")
    public Optional<Token> findTokenById(@PathVariable Long id) {
        return Optional.ofNullable(tokenService.findById(id));
    }

    @PostMapping("/generate")
    public TokenDto generateToken() {
        Token generatedToken = tokenService.generateToken();
        return new TokenMapper().entityToDto(generatedToken);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteToken(@PathVariable Long id) {
        tokenService.delete(id);
    }

}
