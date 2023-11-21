package com.nesterrovv.vpn.authentication.service;

import com.nesterrovv.vpn.authentication.dto.UpdateUserRoleDto;
import com.nesterrovv.vpn.authentication.dto.UserResponseDto;
import com.nesterrovv.vpn.authentication.entity.User;
import com.nesterrovv.vpn.authentication.mapper.UserDtoMapper;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserDtoMapper userDtoMapper;

    public List<UserResponseDto> getUserList(Integer offset, Integer limit) {
        return userService.listAll(offset, limit);
    }

    public UserResponseDto createNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User createdUser = userService.createUser(user);
        return userDtoMapper.entityToResponseDto(createdUser);
    }

    public UserResponseDto updateRole(UpdateUserRoleDto updateUserRoleDto) {
        User user = userService.updateRole(updateUserRoleDto);
        return userDtoMapper.entityToResponseDto(user);
    }

    public String deleteUser(String username) {
        userService.deleteUser(username);
        return "The user was successfully deleted.";
    }

}
