package com.nesterrovv.vpn.authentication.mapper;

import com.nesterrovv.vpn.authentication.dto.UserCreateDto;
import com.nesterrovv.vpn.authentication.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserCreateMapper {
    public UserCreateDto entityToDto(User user) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, UserCreateDto.class);
    }

    public User dtoToEntity(UserCreateDto userCreateDto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userCreateDto, User.class);
    }
}
