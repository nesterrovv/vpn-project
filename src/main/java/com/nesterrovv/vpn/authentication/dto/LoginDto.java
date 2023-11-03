package com.nesterrovv.vpn.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class LoginDto {

    private String username;

    private String password;

}