package com.nesterrovv.vpn.authentication.service;

import com.nesterrovv.vpn.authentication.dto.AuthenticationResponse;
import com.nesterrovv.vpn.authentication.dto.LoginDto;
import com.nesterrovv.vpn.authentication.dto.RegisterDto;
import com.nesterrovv.vpn.authentication.entity.Role;
import com.nesterrovv.vpn.authentication.entity.User;
import com.nesterrovv.vpn.authentication.mapper.UserCreateMapper;
import com.nesterrovv.vpn.authentication.repository.UserRepository;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private UserCreateMapper userCreateMapper;
    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(authenticationManager, userRepository, userCreateMapper, passwordEncoder);
    }

    @Test
    void listAllTest() {
        List<User> list = new LinkedList<>();
        list.add(new User(1, "first", "ras", "first@mail.ru", Role.USER));
        list.add(new User(2, "second", "dva", "second@mail.ru", Role.USER));
        Mockito.when(userRepository.findAll()).thenReturn(list);
        List<User> result = userService.listAll();
        assertEquals(2, result.size());
        assertEquals(1, result.getFirst().getId());
        assertEquals("second", result.getLast().getUsername());
    }

    @Test
    void registerTest() {
        AuthenticationResponse response = new AuthenticationResponse("Ok", "a41h43");
        RegisterDto dto = new RegisterDto("test", "new", "test@mail.ru");
        User user = new User("test", "new", "test@mail.ru");
        Mockito.when(userCreateMapper.dtoToEntity(dto)).thenReturn(user);
        AuthenticationResponse result = userService.register(dto);
        assertEquals(response, result);
    }

    @Test
    void loginTest() {
        AuthenticationResponse response = new AuthenticationResponse("User signed success!", "4ab4");
        LoginDto dto = new LoginDto("first", "ras");
        AuthenticationResponse result = userService.login(dto);
        assertEquals(response, result);
    }

    @Test
    void findByUsernameTest() {
        User user = new User(1, "first", "ras", "first@mail.ru", Role.USER);
        Mockito.when(userRepository.findByUsername("first")).thenReturn(Optional.of(user));
        Optional<User> result = userService.findByUsername("first");
        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }

}
