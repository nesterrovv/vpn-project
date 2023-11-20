package com.nesterrovv.vpn.authentication.controller;

import com.nesterrovv.vpn.authentication.entity.User;
import com.nesterrovv.vpn.authentication.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/user_list")
    public ResponseEntity<?> getUserList(@RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit) {
        return adminService.getUserList(offset, limit);
    }

    @PostMapping("/create_user")
    public ResponseEntity<?> createNewUser(@RequestBody User user) {
        return adminService.createNewUser(user);
    }

}
