package com.nesterrovv.vpn.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthenticationResponse {

    private String responseStatus;
    private String token;

    public AuthenticationResponse(String responseStatus) {
        this.responseStatus = responseStatus;
    }

}
