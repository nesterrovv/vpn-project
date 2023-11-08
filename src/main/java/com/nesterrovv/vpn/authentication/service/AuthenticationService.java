package com.nesterrovv.vpn.authentication.service;

import com.nesterrovv.vpn.authentication.dto.JwtToken;
import com.nesterrovv.vpn.authentication.dto.LoginDto;
import com.nesterrovv.vpn.authentication.dto.RegisterDto;
import com.nesterrovv.vpn.authentication.entity.User;
import com.nesterrovv.vpn.authentication.mapper.UserCreateMapper;
import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final UserCreateMapper userCreateMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public ResponseEntity<?> register(RegisterDto dto) {
        User user = userCreateMapper.dtoToEntity(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser.getUsername());
    }

    public ResponseEntity<?> login(LoginDto dto) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                dto.getUsername(),
                dto.getPassword()
            ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Optional<User> user = userService.findByUsername(dto.getUsername());
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        JwtToken jwtToken = new JwtToken(jwtService.generateToken(user.get()));
        return ResponseEntity.ok(jwtToken);
    }

}
