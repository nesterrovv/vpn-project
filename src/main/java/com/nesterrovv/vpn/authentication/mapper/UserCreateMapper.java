package com.nesterrovv.vpn.authentication.mapper;

import com.nesterrovv.vpn.authentication.dto.UserCreateDto;
import com.nesterrovv.vpn.authentication.entity.Role;
import com.nesterrovv.vpn.authentication.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCreateMapper {

    public User dtoToEntity(UserCreateDto dto) {
        return User.builder()
            .username(dto.getUsername())
            .password(dto.getPassword())
            .email(dto.getEmail())
            .role(Role.USER).build();
    }
}
