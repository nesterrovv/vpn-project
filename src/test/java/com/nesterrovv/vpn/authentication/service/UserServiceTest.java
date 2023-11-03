package com.nesterrovv.vpn.authentication.service;

import com.nesterrovv.vpn.VpnApplication;
import com.nesterrovv.vpn.authentication.dto.AuthenticationResponse;
import com.nesterrovv.vpn.authentication.dto.LoginDto;
import com.nesterrovv.vpn.authentication.dto.RegisterDto;
import com.nesterrovv.vpn.authentication.entity.Role;
import com.nesterrovv.vpn.authentication.entity.User;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = VpnApplication.class)
public class UserServiceTest {

    @Mock
    private UserService userService;

    @Test
    void listAllTest() {
        List<User> list = new LinkedList<>();
        list.add(new User(1, "first", "ras", "first@mail.ru", Role.USER));
        list.add(new User(2, "second", "dva", "second@mail.ru", Role.USER));
        Mockito.when(userService.listAll()).thenReturn(list);
        List<User> result = userService.listAll();
        assertEquals(2, result.size());
        assertEquals(1, result.getFirst().getId());
        assertEquals("second", result.getLast().getUsername());
    }

    @Test
    void registerTest() {
        AuthenticationResponse response = new AuthenticationResponse("Ok", "a41h43");
        RegisterDto dto = new RegisterDto("test", "new", "test@mail.ru");
        Mockito.when(userService.register(dto)).thenReturn(response);
        AuthenticationResponse result = userService.register(dto);
        assertEquals(response, result);
    }

    @Test
    void loginTest() {
        AuthenticationResponse response = new AuthenticationResponse("User signed success!", "4ab4");
        LoginDto dto = new LoginDto("first", "ras");
        Mockito.when(userService.login(dto)).thenReturn(response);
        AuthenticationResponse result = userService.login(dto);
        assertEquals(response, result);
    }

    @Test
    void findByUsernameTest() {
        User user = new User(1, "first", "ras", "first@mail.ru", Role.USER);
        Mockito.when(userService.findByUsername("first")).thenReturn(Optional.of(user));
        Optional<User> result = userService.findByUsername("first");
        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }

}
