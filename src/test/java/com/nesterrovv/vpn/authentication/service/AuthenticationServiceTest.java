package com.nesterrovv.vpn.authentication.service;

import com.nesterrovv.vpn.authentication.dto.LoginDto;
import com.nesterrovv.vpn.authentication.dto.RegisterDto;
import com.nesterrovv.vpn.authentication.entity.Role;
import com.nesterrovv.vpn.authentication.entity.User;
import com.nesterrovv.vpn.authentication.mapper.UserCreateMapper;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthenticationServiceTest {

    private AuthenticationService authenticationService;

    @Mock
    private UserService userService;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private UserCreateMapper userCreateMapper;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authenticationService =
            new AuthenticationService(
                authenticationManager,
                userService,
                userCreateMapper,
                passwordEncoder,
                jwtService
            );
    }

    @Test
    void registerTest() {
        RegisterDto dto = new RegisterDto("test", "new", "test@mail.ru");
        ResponseEntity<?> response = ResponseEntity.ok(dto.getUsername());
        User user = new User("test", "new", "test@mail.ru");
        Mockito.when(userCreateMapper.dtoToEntity(dto)).thenReturn(user);
        Mockito.when(userService.createUser(user)).thenReturn(user);
        ResponseEntity<?> result = authenticationService.register(dto);
        assertEquals(response, result);
    }

    @Test
    void loginTest() {
        ResponseEntity<?> response = ResponseEntity.ok("4ab4");
        LoginDto dto = new LoginDto("first", "ras");
        Optional<User> user = Optional.of(new User(null, "first", "ras", null, Role.USER));
        Mockito.when(userService.findByUsername(dto.getUsername())).thenReturn(user);
        ResponseEntity<?> result = authenticationService.login(dto);
        assertEquals(response.getStatusCode(), result.getStatusCode());
    }

}
