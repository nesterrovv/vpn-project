package com.nesterrovv.vpn.authentication.controller;

import com.nesterrovv.vpn.authentication.dto.LoginDto;
import com.nesterrovv.vpn.authentication.dto.RegisterDto;
import com.nesterrovv.vpn.authentication.exception.EmailAlreadyExistsException;
import com.nesterrovv.vpn.authentication.exception.UsernameAlreadyExistsException;
import com.nesterrovv.vpn.authentication.exception.UsernameOrPasswordException;
import com.nesterrovv.vpn.authentication.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthenticationControllerTest {

    private AuthenticationController controller;

    @Mock
    private AuthenticationService authenticationService;

    private static final RegisterDto REGISTER_DTO =
        RegisterDto.builder().username("username").password("password").email("username@mail.ru").build();

    private static final LoginDto LOGIN_DTO = LoginDto.builder().username("username").password("password").build();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new AuthenticationController(authenticationService);
    }

    @Test
    void registerSuccessTest() {
        ResponseEntity<?> response = ResponseEntity.ok(REGISTER_DTO.getUsername());
        Mockito.when(authenticationService.register(REGISTER_DTO)).thenAnswer(invocationOnMock -> response);
        ResponseEntity<?> result = controller.register(REGISTER_DTO);
        assertEquals(response, result);
    }

    @Test
    void registerUsernameExists() {
        ResponseEntity<?> response =
            new ResponseEntity<>(new UsernameAlreadyExistsException().getMessage(), HttpStatus.BAD_REQUEST);
        Mockito.when(authenticationService.register(REGISTER_DTO)).thenAnswer(invocationOnMock -> response);
        ResponseEntity<?> result = controller.register(REGISTER_DTO);
        assertEquals(response, result);
    }

    @Test
    void registerEmailExistsTest() {
        ResponseEntity<?> response =
            new ResponseEntity<>(new EmailAlreadyExistsException().getMessage(), HttpStatus.BAD_REQUEST);
        Mockito.when(authenticationService.register(REGISTER_DTO)).thenAnswer(invocationOnMock -> response);
        ResponseEntity<?> result = controller.register(REGISTER_DTO);
        assertEquals(response, result);
    }

    @Test
    void loginSuccessTest() {
        ResponseEntity<?> response = ResponseEntity.ok("JWT_TOKEN_BY_USERNAME");
        Mockito.when(authenticationService.login(LOGIN_DTO)).thenAnswer(invocationOnMock -> response);
        ResponseEntity<?> result = controller.login(LOGIN_DTO);
        assertEquals(response, result);
    }

    @Test
    void loginUsernameOrPasswordExceptionTest() {
        ResponseEntity<?> response =
            new ResponseEntity<>(new UsernameOrPasswordException().getMessage(), HttpStatus.BAD_REQUEST);
        Mockito.when(authenticationService.login(LOGIN_DTO)).thenAnswer(invocationOnMock -> response);
        ResponseEntity<?> result = controller.login(LOGIN_DTO);
        assertEquals(response, result);
    }

}
