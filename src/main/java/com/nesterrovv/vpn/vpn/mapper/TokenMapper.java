package com.nesterrovv.vpn.vpn.mapper;

import com.nesterrovv.vpn.vpn.dto.TokenDto;
import com.nesterrovv.vpn.vpn.entity.Token;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TokenMapper {

    public TokenDto entityToDto(Token token) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(token, TokenDto.class);
    }

    public Token dtoToEntity(TokenDto userCreateDto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userCreateDto, Token.class);
    }

}
