package com.nesterrovv.vpn.authentication.service;

import com.nesterrovv.vpn.authentication.entity.Role;
import com.nesterrovv.vpn.authentication.entity.User;
import com.nesterrovv.vpn.authentication.repository.UserRepository;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private static final User USER =
        User.builder().id(1).username("username").password("password").email("username@mail.ru").role(Role.USER).build();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    void listAllTest() {
        List<User> list = new LinkedList<>();
        list.add(USER);
        list.add(new User(2, "second", "dva", "second@mail.ru", Role.USER));
        Mockito.when(userRepository.findAll()).thenReturn(list);
        List<User> result = userService.listAll();
        assertEquals(2, result.size());
        assertEquals(1, result.getFirst().getId());
        assertEquals("second", result.getLast().getUsername());
    }

    @Test
    void findByUsernameTest() {
        User user = USER;
        Mockito.when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
        Optional<User> result = Optional.ofNullable(userService.findByUsername(user.getUsername()));
        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }

    @Test
    void createUser() {
        User user = USER;
        Mockito.when(userRepository.save(user)).thenReturn(user);
        User result = userService.createUser(user);
        assertEquals(user, result);
    }

    @Test
    void findByEmailTest() {
        User user = USER;
        Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
        Optional<User> result = Optional.ofNullable(userService.findByEmail(user.getEmail()));
        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }

    @Test
    void loadUserByUsernameTest() {
        User user = USER;
        Mockito.when(userService.loadUserByUsername(user.getUsername())).thenReturn(user);
        UserDetails result = userService.loadUserByUsername(user.getUsername());
        assertEquals(user, result);
    }

}
