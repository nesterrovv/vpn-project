package com.nesterrovv.vpn.authentication.mapper;

import com.nesterrovv.vpn.authentication.dto.UserResponseDto;
import com.nesterrovv.vpn.authentication.dto.RegisterDto;
import com.nesterrovv.vpn.authentication.entity.Role;
import com.nesterrovv.vpn.authentication.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDtoMapper {

    public User registerDtoToEntity(RegisterDto dto) {
        return User.builder()
            .username(dto.getUsername())
            .password(dto.getPassword())
            .email(dto.getEmail())
            .role(Role.USER)
            .build();
    }

    public UserResponseDto entityToResponseDto(User user) {
        return UserResponseDto.builder()
            .id(user.getId())
            .username(user.getUsername())
            .email(user.getEmail())
            .role(user.getRole())
            .build();
    }

}
