package com.nesterrovv.vpn.authentication.service;

import com.nesterrovv.vpn.VpnApplication;
import com.nesterrovv.vpn.authentication.dto.RegisterDto;
import com.nesterrovv.vpn.authentication.entity.User;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = VpnApplication.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void listAllTest() {
        List<User> result = userService.listAll();
        for (User user : result) {
            System.out.println(user);
        }
        assertEquals(2, result.size());
    }

    @Test
    void createTest() {
        int startValue = userService.listAll().size();
        userService.register(new RegisterDto("test", "new", "test@mail.ru"));
        assertEquals(startValue + 1, userService.listAll().size());
    }

}
