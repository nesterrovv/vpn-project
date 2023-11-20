package com.nesterrovv.vpn.authentication.service;

import com.nesterrovv.vpn.authentication.dto.JwtToken;
import com.nesterrovv.vpn.authentication.dto.LoginDto;
import com.nesterrovv.vpn.authentication.dto.RegisterDto;
import com.nesterrovv.vpn.authentication.entity.User;
import com.nesterrovv.vpn.authentication.exception.EmailAlreadyExistsException;
import com.nesterrovv.vpn.authentication.exception.UsernameAlreadyExistsException;
import com.nesterrovv.vpn.authentication.exception.UsernameOrPasswordException;
import com.nesterrovv.vpn.authentication.mapper.UserCreateMapper;
import com.nesterrovv.vpn.authentication.utils.JwtTokensUtil;
import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
    private final JwtTokensUtil jwtTokensUtil;

    public ResponseEntity<?> register(RegisterDto dto) {
        User user = userCreateMapper.dtoToEntity(dto);
        if (Optional.ofNullable(userService.findByUsername(user.getUsername())).isPresent()) {
            throw new UsernameAlreadyExistsException();
        }
        if (Optional.ofNullable(userService.findByEmail(user.getEmail())).isPresent()) {
            throw new EmailAlreadyExistsException();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser.getUsername());
    }

    public ResponseEntity<?> login(LoginDto dto) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    dto.getUsername(),
                    dto.getPassword()
                ));
        } catch (BadCredentialsException e) {
            throw new UsernameOrPasswordException();
        }
        User user = userService.findByUsername(dto.getUsername());
        JwtToken jwtToken = new JwtToken(jwtTokensUtil.generateToken(user));
        return ResponseEntity.ok(jwtToken);
    }

}
