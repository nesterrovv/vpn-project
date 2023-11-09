package com.nesterrovv.vpn.authentication.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class LoginDto {

    @Size(min = 5, message = "the username cannot be shortest than 5 characters!")
    @Size(max = 32, message = "the username cannot be longer than 32 characters!")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "username can contain only letters!")
    private String username;

    @NotBlank(message = "The password cannot be empty!")
    private String password;

}
