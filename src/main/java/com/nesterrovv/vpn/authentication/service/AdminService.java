package com.nesterrovv.vpn.authentication.service;

import com.nesterrovv.vpn.authentication.dto.UserResponseDto;
import com.nesterrovv.vpn.authentication.entity.User;
import com.nesterrovv.vpn.authentication.exception.EmailAlreadyExistsException;
import com.nesterrovv.vpn.authentication.exception.UsernameAlreadyExistsException;
import com.nesterrovv.vpn.authentication.mapper.UserDtoMapper;
import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserDtoMapper userDtoMapper;

    public ResponseEntity<?> getUserList(Integer offset, Integer limit) {
        return ResponseEntity.ok(userService.listAll(offset, limit));
    }

    public ResponseEntity<?> createNewUser(User user) {
        if (Optional.ofNullable(userService.findByUsername(user.getUsername())).isPresent()) {
            throw new UsernameAlreadyExistsException();
        }
        if (Optional.ofNullable(userService.findByEmail(user.getEmail())).isPresent()) {
            throw new EmailAlreadyExistsException();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User createdUser = userService.createUser(user);
        UserResponseDto createdDto = userDtoMapper.entityToResponseDto(createdUser);
        return new ResponseEntity<>(createdDto, HttpStatus.CREATED);
    }

}
