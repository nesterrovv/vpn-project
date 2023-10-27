package com.nesterrovv.vpn.authentication.service;

import com.nesterrovv.vpn.authentication.dto.AuthenticationResponse;
import com.nesterrovv.vpn.authentication.dto.LoginDto;
import com.nesterrovv.vpn.authentication.dto.RegisterDto;
import com.nesterrovv.vpn.authentication.entity.User;
import com.nesterrovv.vpn.authentication.mapper.UserCreateMapper;
import com.nesterrovv.vpn.authentication.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final UserCreateMapper userCreateMapper;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationResponse register(RegisterDto dto) {
        User user = userCreateMapper.dtoToEntity(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return new AuthenticationResponse("Ok", "a41h43");
    }

    public AuthenticationResponse login(LoginDto dto) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                dto.getUsername(),
                dto.getPassword()
            ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new AuthenticationResponse("User signed success!", "4ab4");
    }

    public List<User> listAll() {
        return userRepository.findAll();
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findById(Integer id) {
        return userRepository.findById(id).get();
    }

    public boolean delete(String username) {
        return userRepository.findByUsername(username)
            .map(entity -> {
                userRepository.delete(entity);
                return true;
            })
            .orElse(false);
    }
}
