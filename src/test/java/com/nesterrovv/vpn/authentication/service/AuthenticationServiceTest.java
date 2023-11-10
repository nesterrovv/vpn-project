package com.nesterrovv.vpn.authentication.service;

import com.nesterrovv.vpn.authentication.dto.LoginDto;
import com.nesterrovv.vpn.authentication.dto.RegisterDto;
import com.nesterrovv.vpn.authentication.entity.User;
import com.nesterrovv.vpn.authentication.exception.EmailAlreadyExistsException;
import com.nesterrovv.vpn.authentication.exception.UsernameAlreadyExistsException;
import com.nesterrovv.vpn.authentication.exception.UsernameOrPasswordException;
import com.nesterrovv.vpn.authentication.mapper.UserCreateMapper;
import com.nesterrovv.vpn.authentication.utils.JwtTokensUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthenticationServiceTest {

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
    private JwtTokensUtil jwtTokensUtil;

    private static final RegisterDto REGISTER_DTO =
        RegisterDto.builder().username("username").password("password").email("username@mail.ru").build();

    private static final LoginDto LOGIN_DTO = LoginDto.builder().username("username").password("password").build();

    private static final User USER = new User("username", "password", "username@mail.ru");

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authenticationService =
            new AuthenticationService(
                authenticationManager,
                userService,
                userCreateMapper,
                passwordEncoder,
                jwtTokensUtil
            );
    }

    @Test
    void registerSuccessTest() {
        ResponseEntity<?> response = ResponseEntity.ok(REGISTER_DTO.getUsername());
        Mockito.when(userCreateMapper.dtoToEntity(REGISTER_DTO)).thenReturn(USER);
        Mockito.when(userService.createUser(USER)).thenReturn(USER);
        ResponseEntity<?> result = authenticationService.register(REGISTER_DTO);
        assertEquals(response, result);
    }

    @Test
    void registerUsernameExistsTest() {
        ResponseEntity<?> response =
            new ResponseEntity<>(new UsernameAlreadyExistsException().getMessage(), HttpStatus.BAD_REQUEST);
        Mockito.when(userCreateMapper.dtoToEntity(REGISTER_DTO)).thenReturn(USER);
        Mockito.when(userService.findByUsername(REGISTER_DTO.getUsername())).thenReturn(USER);
        ResponseEntity<?> result = authenticationService.register(REGISTER_DTO);
        assertEquals(response, result);
    }

    @Test
    void registerEmailExistsTest() {
        ResponseEntity<?> response =
            new ResponseEntity<>(new EmailAlreadyExistsException().getMessage(), HttpStatus.BAD_REQUEST);
        Mockito.when(userCreateMapper.dtoToEntity(REGISTER_DTO)).thenReturn(USER);
        Mockito.when(userService.findByEmail(REGISTER_DTO.getEmail())).thenReturn(USER);
        ResponseEntity<?> result = authenticationService.register(REGISTER_DTO);
        assertEquals(response, result);
    }

    @Test
    void loginSuccessTest() {
        ResponseEntity<?> response = ResponseEntity.ok("TOKEN_BY_USERNAME");
        Mockito.when(userService.findByUsername(LOGIN_DTO.getUsername())).thenReturn(USER);
        ResponseEntity<?> result = authenticationService.login(LOGIN_DTO);
        assertEquals(response.getStatusCode(), result.getStatusCode());
    }

    @Test
    void loginBadCredentialTest() {
        ResponseEntity<?> response =
            new ResponseEntity<>(new UsernameOrPasswordException().getMessage(), HttpStatus.BAD_REQUEST);
        Mockito.when(authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                LOGIN_DTO.getUsername(),
                LOGIN_DTO.getPassword()
            ))).thenThrow(BadCredentialsException.class);
        ResponseEntity<?> result = authenticationService.login(LOGIN_DTO);
        assertEquals(response, result);
    }

}
