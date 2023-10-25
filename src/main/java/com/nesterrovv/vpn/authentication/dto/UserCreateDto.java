package com.nesterrovv.vpn.authentication.dto;

import lombok.Value;

@Value
public class UserCreateDto {

    String username;

    String password;

    String email;
}
